package com.geppetto.braintree.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.geppetto.braintree.dto.Customer;
import com.geppetto.braintree.interfaces.dao.ICustomerDao;
import com.geppetto.braintree.interfaces.services.ICustomerService;

@Service("CustomerService")
public class CustomerService implements ICustomerService{
		
	private ICustomerDao customer_dao;

	public ICustomerDao getCustomer_dao() {
		return customer_dao;
	}

	@Resource(name="CustomerDao")
	public void setCustomer_dao(ICustomerDao customer_dao) {
		this.customer_dao = customer_dao;
	}


	@Override
	public Customer find_customer(long customer_id) throws Exception {
				
		return this.customer_dao.get_customer_by_id(customer_id);
	}

}
