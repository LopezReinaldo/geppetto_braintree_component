package com.geppetto.braintree.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.geppetto.braintree.domain.core.Customer;
import com.geppetto.braintree.interfaces.dao.ICustomerDao;

@Repository("CustomerDao")
public class CustomerDao implements ICustomerDao{

	@Value("${find_customer_by_id.sql}")
	private String find_customer_by_id_sql;
	
	@Override
	public Customer get_customer_by_id(long customer_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
