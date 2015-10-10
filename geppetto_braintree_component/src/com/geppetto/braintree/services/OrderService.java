package com.geppetto.braintree.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.geppetto.braintree.dto.Customer;
import com.geppetto.braintree.dto.Order;
import com.geppetto.braintree.dto.OrderDetail;
import com.geppetto.braintree.interfaces.dao.IOrderDao;
import com.geppetto.braintree.interfaces.services.IOrderService;

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
@Service("OrderService")
public class OrderService implements IOrderService {
	
	private IOrderDao order_dao;
	
	private CustomerService customer_service;			

	public CustomerService getCustomer_service() {
		return customer_service;
	}

	@Resource(name = "CustomerService")
	public void setCustomer_service(CustomerService customer_service) {
		this.customer_service = customer_service;
	}

	public IOrderDao getOrder_dao() {
		return order_dao;
	}

	@Resource(name="OrderDao")
	public void setOrder_dao(IOrderDao order_dao) {
		this.order_dao = order_dao;
	}

	@Override
	public OrderDetail add_order(OrderDetail order_detail) throws Exception {
			
		Customer customer = customer_service.find_customer(1); //replace for the id of the user logged			
		
		Order order_added = this.get_order();
		
		if(order_added == null){
			Order order = new Order();
			order.setCustomer(customer);
			order.setOrder_detail(new ArrayList<OrderDetail>());
			order_added = this.order_dao.insert_order(order);			
		}
		
		order_detail.setOrder_id(order_added.getId());		
								
		return this.order_dao.insert_order_detail(order_detail);
	}

	@Override
	public Order get_order() throws Exception {				
		
		return this.order_dao.get_order();
	}

	@Override
	public void proccess_order() throws Exception {		
		
		this.order_dao.update_order();;
	}

	@Override
	public void remove_product(long product_id) throws Exception {
		
		this.order_dao.remove_product(product_id);		
	}

	@Override
	public void update_quantity(long product_id, int quantity) throws Exception {

		this.order_dao.update_order_detail(product_id, quantity);
		
	}

}
