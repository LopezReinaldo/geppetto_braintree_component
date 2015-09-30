package com.geppetto.braintree.dto;

import java.util.Date;
/***
 * @author Reinaldo<br>
 * Created Date: 10/09/2015<br>
 * 
 * This class is used to support the data from customers
 *
 */
public class Customer {		
			
	private long id;
	private String first_name;
	private String last_name;
	private String company;
	private String email;
	private String phone;
	private String fax;
	private String website;
	private int number_of_payment_methods;
	private int number_of_transactions;
	private Date created_date;
	private Date last_updated_date;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getNumber_of_payment_methods() {
		return number_of_payment_methods;
	}
	public void setNumber_of_payment_methods(int number_of_payment_methods) {
		this.number_of_payment_methods = number_of_payment_methods;
	}	
	public int getNumber_of_transactions() {
		return number_of_transactions;
	}
	public void setNumber_of_transactions(int number_of_transactions) {
		this.number_of_transactions = number_of_transactions;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getLast_updated_date() {
		return last_updated_date;
	}
	public void setLast_updated_date(Date last_updated_date) {
		this.last_updated_date = last_updated_date;
	}	

}
