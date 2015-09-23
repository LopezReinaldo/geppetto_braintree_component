package com.geppetto.braintree.interfaces.dao;

import com.geppetto.braintree.domain.core.Customer;

public interface ICustomerDao {

	public Customer get_customer_by_id(long customer_id) throws Exception;
}
