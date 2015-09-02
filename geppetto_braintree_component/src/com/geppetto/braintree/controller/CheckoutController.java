package com.geppetto.braintree.controller;

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

@Controller("CheckoutController")
@RequestMapping(value = "/checkout")
public class CheckoutController {
	
	public static BraintreeGateway gateway = new BraintreeGateway(Environment.SANDBOX,
			"your_merchant_id",
			"your_public_key",
			"your_private_key"
			);

	private static final Logger LOG = LoggerFactory.getLogger(CheckoutController.class);
	
	@RequestMapping(value = "/getClientToken", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String getClientToken(){		
		String clientToken = "{\"token\" : \"" + gateway.clientToken().generate() + "\"}";		
		LOG.debug("getClientToken Controller "+clientToken);
		return clientToken;
	}	
	
	@RequestMapping(value = "/processPayment", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String proccesPayment(@RequestBody PaymentDetail paymentDetail){
		
		LOG.debug("proccesPayment payment_method_nonce : "+paymentDetail.getNonce());				
						
		CustomerRequest crequest = new CustomerRequest()
		.firstName("First Name").lastName("Last Name") // information of the user logged
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