package com.geppetto.braintree.dto;

import java.util.Date;
/***
 * @author Reinaldo<br>
 * Created Date: 10/09/2015<br>
 * 
 * This class is used to support the data from orders
 *
 */
public class Order {
	
	private int id;
	private Customer customer;
	private Date order_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}	
	
}
