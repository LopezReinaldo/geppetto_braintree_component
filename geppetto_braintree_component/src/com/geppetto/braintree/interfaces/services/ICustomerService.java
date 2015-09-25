package com.geppetto.braintree.interfaces.services;

import com.geppetto.braintree.dto.Customer;

public interface ICustomerService {
	
	public Customer find_customer(long customer_id) throws Exception;

}
