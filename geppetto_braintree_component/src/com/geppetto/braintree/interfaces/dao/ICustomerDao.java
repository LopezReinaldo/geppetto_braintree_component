package com.geppetto.braintree.interfaces.dao;

import com.geppetto.braintree.dto.Customer;
/**
 * 
 * @author Reinaldo</br> 
 * Date Created: 10/09/2015</br>
 *
 *        The purpose of this interface is to declare the standard db operations
 *        required</br> for the customers</p>
 *
 */
public interface ICustomerDao {

	public Customer get_customer_by_id(long customer_id) throws Exception;
}
