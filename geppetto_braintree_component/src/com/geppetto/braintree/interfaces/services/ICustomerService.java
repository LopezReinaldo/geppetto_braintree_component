package com.geppetto.braintree.interfaces.services;

import com.geppetto.braintree.domain.core.Customer;

public interface ICustomerService {
	
	public Customer find_customer(long customer_id) throws Exception;

}
