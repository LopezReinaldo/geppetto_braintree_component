package com.geppetto.braintree.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.geppetto.braintree.dto.Item;
import com.geppetto.braintree.dto.Order;
import com.geppetto.braintree.dto.OrderDetail;
import com.geppetto.braintree.dto.PaymentDetail;
import com.geppetto.braintree.dto.Product;
import com.geppetto.braintree.services.CustomerService;
import com.geppetto.braintree.services.OrderService;
import com.geppetto.braintree.services.ProductService;
import com.geppetto.braintree.services.TransactionService;

/**
 * 
 * @author Geppetto Generated Code</br>
 * Date Created: </br>
 * @since  </br>
   build:   </p> 
 *
 * code was generated by the Geppetto System </br>
 * Gepppetto system Copyright - Geppetto LLC 2014 - 2015</br>
 * The generated code is free to use by anyone</p>
 *
 *
 *
*/
@Controller("CheckoutController")
@RequestMapping(value = "/checkout")
public class CheckoutController {
	
	public static BraintreeGateway gateway = new BraintreeGateway(
			  Environment.SANDBOX,
			  "58dt92zw2c6zzx79",
			  "w7jjzw585hnmc9kj",
			  "4b840dbf358eb84ebd4bacd12d4b4a7a"
			);

	private static final Logger LOG = LoggerFactory.getLogger(CheckoutController.class);
	
	private CustomerService customer_service;	
	private ProductService product_service;		
	private OrderService order_service;
	private TransactionService transaction_service;
	
	public OrderService getOrder_service() {
		return order_service;
	}
	@Resource(name = "OrderService")
	public void setOrder_service(OrderService order_service) {
		this.order_service = order_service;
	}
	public ProductService getProduct_service() {
		return product_service;
	}
	@Resource(name = "ProductService")
	public void setProduct_service(ProductService product_service) {
		this.product_service = product_service;
	}
	public CustomerService getCustomer_service() {
		return customer_service;
	}
	@Resource(name = "CustomerService")
	public void setCustomer_service(CustomerService customer_service) {
		this.customer_service = customer_service;
	}	
	public TransactionService getTransaction_service() {
		return transaction_service;
	}
	@Resource(name = "TransactionService")
	public void setTransaction_service(TransactionService transaction_service) {
		this.transaction_service = transaction_service;
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
		LOG.debug("proccesPayment total : "+paymentDetail.getTotal());
		
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
			/*
			SubscriptionRequest subscriptionRequest = new SubscriptionRequest()
					.paymentMethodToken(token).planId("plus123"); // plan created in the sandbox account

			Result<Subscription> result = gateway.subscription().create(
					subscriptionRequest);
			*/
			
			
			//Process Sale
			TransactionRequest transactionRequest = new TransactionRequest()
					.amount(new BigDecimal(paymentDetail.getTotal())).paymentMethodToken(token);

			Result<Transaction> result = gateway.transaction().sale(
					transactionRequest);					

			if (result.isSuccess()) {
				LOG.debug("Success! Transaction ID: "
						+ result.getTarget().getId());
				
				Transaction transaction = result.getTarget();				
				transaction_service.add_transaction(transaction, c);				
				order_service.proccess_order();
				
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
	
	@RequestMapping(value = "/getCart", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<Item> getCart() throws Exception {
		System.out.println("CheckoutController - getCart");
		
		LOG.debug("CheckoutController - getCart");
		
		Order order = this.order_service.get_order();
		
		List<Item> cart = new ArrayList<Item>();
		
		if(order != null)
			for (OrderDetail detail : order.getOrder_detail()) {
				Product product = this.product_service.find_product_by_id(detail.getProduct_id());
				Item item = new Item();
				item.setProduct(product);
				item.setQuantity(detail.getQuantity());
			
				cart.add(item);
			}
		
		return cart;
	}	
	
	@RequestMapping(method = RequestMethod.POST, value = "/removeProduct", headers = "Accept=application/json")
	@ResponseBody
	public void removeProduct(@RequestBody Product product) throws Exception {
		System.out.println("removing product from cart! "+product.getId());		
		
		 this.order_service.remove_product(product.getId());
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateQuantity", headers = "Accept=application/json")
	@ResponseBody
	public void updateQuantity(@RequestBody OrderDetail order_detail) throws Exception {			
		LOG.debug("updating quantity of product! quantity="+order_detail.getQuantity());
		
		this.order_service.update_quantity(order_detail.getProduct_id(), order_detail.getQuantity());
	}


}