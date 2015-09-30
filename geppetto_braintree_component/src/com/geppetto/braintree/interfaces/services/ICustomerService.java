package com.geppetto.braintree.interfaces.services;

import com.geppetto.braintree.dto.Customer;
/**
 * 
 * @author Reinaldo</br> 
 * Creation Date: 10/09/2015</br>
 * 
 * This interface will be implemented by a service that handles customers
 *
 */
public interface ICustomerService {
	
	public Customer find_customer(long customer_id) throws Exception;

}
