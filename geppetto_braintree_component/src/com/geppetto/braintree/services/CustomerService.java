package com.geppetto.braintree.services;

import org.springframework.stereotype.Service;

import com.geppetto.braintree.domain.core.Customer;
import com.geppetto.braintree.interfaces.services.ICustomerService;

@Service("CustomerService")
public class CustomerService implements ICustomerService{

	@Override
	public Customer find_customer(long customer_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
