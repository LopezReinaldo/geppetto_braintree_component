package com.geppetto.braintree.dto;

import java.util.Date;

public class Subscription {
	
	private int id;
	private int customer_id;
	private String payment_method_token;
	private String plan_id;
	private String plan_name;
	private String status_merchant_account;
	private String descriptor_name;
	private String descriptor_phone;
	private String descriptor_url;
	private double price;
	private String subscription_currency;
	private String trial_period;
	private Date first_bill_date;
	private String current_billing_period;
	private String paid_through_date;
	private Date next_bill_date;
	private double balance;
	private String currency;
	private String next_billing_period_amount;
	private int number_of_transaccions;
	private int number_of_addons;
	private int number_of_discounts;
	private int number_of_billing_cycles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getPayment_method_token() {
		return payment_method_token;
	}
	public void setPayment_method_token(String payment_method_token) {
		this.payment_method_token = payment_method_token;
	}
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public String getStatus_merchant_account() {
		return status_merchant_account;
	}
	public void setStatus_merchant_account(String status_merchant_account) {
		this.status_merchant_account = status_merchant_account;
	}
	public String getDescriptor_name() {
		return descriptor_name;
	}
	public void setDescriptor_name(String descriptor_name) {
		this.descriptor_name = descriptor_name;
	}
	public String getDescriptor_phone() {
		return descriptor_phone;
	}
	public void setDescriptor_phone(String descriptor_phone) {
		this.descriptor_phone = descriptor_phone;
	}
	public String getDescriptor_url() {
		return descriptor_url;
	}
	public void setDescriptor_url(String descriptor_url) {
		this.descriptor_url = descriptor_url;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSubscription_currency() {
		return subscription_currency;
	}
	public void setSubscription_currency(String subscription_currency) {
		this.subscription_currency = subscription_currency;
	}
	public String getTrial_period() {
		return trial_period;
	}
	public void setTrial_period(String trial_period) {
		this.trial_period = trial_period;
	}
	public Date getFirst_bill_date() {
		return first_bill_date;
	}
	public void setFirst_bill_date(Date first_bill_date) {
		this.first_bill_date = first_bill_date;
	}
	public String getCurrent_billing_period() {
		return current_billing_period;
	}
	public void setCurrent_billing_period(String current_billing_period) {
		this.current_billing_period = current_billing_period;
	}
	public String getPaid_through_date() {
		return paid_through_date;
	}
	public void setPaid_through_date(String paid_through_date) {
		this.paid_through_date = paid_through_date;
	}
	public Date getNext_bill_date() {
		return next_bill_date;
	}
	public void setNext_bill_date(Date next_bill_date) {
		this.next_bill_date = next_bill_date;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getNext_billing_period_amount() {
		return next_billing_period_amount;
	}
	public void setNext_billing_period_amount(String next_billing_period_amount) {
		this.next_billing_period_amount = next_billing_period_amount;
	}
	public int getNumber_of_transaccions() {
		return number_of_transaccions;
	}
	public void setNumber_of_transaccions(int number_of_transaccions) {
		this.number_of_transaccions = number_of_transaccions;
	}
	public int getNumber_of_addons() {
		return number_of_addons;
	}
	public void setNumber_of_addons(int number_of_addons) {
		this.number_of_addons = number_of_addons;
	}
	public int getNumber_of_discounts() {
		return number_of_discounts;
	}
	public void setNumber_of_discounts(int number_of_discounts) {
		this.number_of_discounts = number_of_discounts;
	}
	public int getNumber_of_billing_cycles() {
		return number_of_billing_cycles;
	}
	public void setNumber_of_billing_cycles(int number_of_billing_cycles) {
		this.number_of_billing_cycles = number_of_billing_cycles;
	}			

}
