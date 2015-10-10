package com.geppetto.braintree.interfaces.dao;

import com.geppetto.braintree.dto.Order;
import com.geppetto.braintree.dto.OrderDetail;

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
public interface IOrderDao {
	
	public Order insert_order(Order order) throws Exception;

	public OrderDetail insert_order_detail(OrderDetail order_detail) throws Exception;
	
	public Order get_order() throws Exception;
	
	public void update_order() throws Exception;
	
	public void remove_product(long product_id) throws Exception;
	
	public void update_order_detail(long product_id, int quantity) throws Exception;
}