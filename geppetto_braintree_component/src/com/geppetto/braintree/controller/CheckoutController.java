package com.geppetto.braintree.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Subscription;
import com.braintreegateway.SubscriptionRequest;
import com.geppetto.braintree.dto.PaymentDetail;
import com.geppetto.braintree.services.CustomerService;

@Controller("CheckoutController")
@RequestMapping(value = "/checkout")
public class CheckoutController {
	
	public static BraintreeGateway gateway = new BraintreeGateway(
			  Environment.SANDBOX,
			  "",
			  "",
			  ""
			);

	private static final Logger LOG = LoggerFactory.getLogger(CheckoutController.class);
	
	private CustomerService customer_service;

	public CustomerService getCustomer_service() {
		return customer_service;
	}

	@Resource(name = "CustomerService")
	public void setCustomer_service(CustomerService customer_service) {
		this.customer_service = customer_service;
	}

	@RequestMapping(value = "/getClientToken", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String getClientToken(){		
		String clientToken = "{\"token\" : \"" + gateway.clientToken().generate() + "\"}";		
		LOG.debug("getClientToken Controller "+clientToken);
		return clientToken;
	}	
	
	@RequestMapping(value = "/processPayment", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String proccesPayment(@RequestBody PaymentDetail paymentDetail) throws Exception{
		
		LOG.debug("proccesPayment payment_method_nonce : "+paymentDetail.getNonce());
		
		com.geppetto.braintree.dto.Customer c;
		c = customer_service.find_customer(1); //replace for the id of the user logged						
		
		CustomerRequest crequest = new CustomerRequest()
		.firstName(c.getFirst_name()).lastName(c.getLast_name()) // information of the user logged
	    .paymentMethodNonce(paymentDetail.getNonce());

		Result<Customer> cresult = gateway.customer().create(crequest);
		
		if(cresult.isSuccess()){

			Customer customer = cresult.getTarget();
			customer.getId();

			String token = customer.getPaymentMethods().get(0).getToken();

			// Process Subscription
			SubscriptionRequest subscriptionRequest = new SubscriptionRequest()
					.paymentMethodToken(token).planId("plus123"); // plan created in the sandbox account

			Result<Subscription> result = gateway.subscription().create(
					subscriptionRequest);

			if (result.isSuccess()) {
				LOG.debug("Success! Transaction ID: "
						+ result.getTarget().getId());
				return "{ \"id\": \"" + result.getTarget().getId() + "\"}";
			} else {
				LOG.debug("Error: " + result.getMessage());
				return "{ \"error\": \"" + result.getMessage() + "\"}";
			}
		} else {
			LOG.debug("Error: " + cresult.getMessage());
			return "{ \"error\": \"" + cresult.getMessage() + "\"}";
		}
	}


}