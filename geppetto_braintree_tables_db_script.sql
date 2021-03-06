/**
 * 
 * @author Geppetto Generated Code</br>
 * Date Created: </br>
 * @since  </br>
   build:   </p> 
 *
 * code was generated by the Geppetto System </br>
 * Gepppetto system Copyright - Geppetto LLC </br>
 * The generated code is free to use by anyone</p>
 *
 *
 *
*/


CREATE TABLE geppetto_braintree.customer (
	id bigint(19) NOT NULL auto_increment,
	first_name varchar(250),
	last_name varchar(250),
	company varchar(250),
	email varchar(250),
	phone varchar(250),
	fax varchar(250),
	website varchar(250),
	number_of_payment_methods int(10),
	number_of_transactions int(10),
	created_date date,
	last_updated_date date,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE geppetto_braintree.`order` (
	id bigint(19) NOT NULL auto_increment,
	customer_id int(10),	
	order_date TIMESTAMP,
	status varchar(250),
	PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE geppetto_braintree.order_detail (
	id bigint(19) NOT NULL auto_increment,
	order_id int(10),
	product_id int(10),
	quantity int(10),
	PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE geppetto_braintree.product (
	id bigint(19) NOT NULL auto_increment,
	name varchar(250),
	description varchar(250),
	price double,
	image varchar(250),
	PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE geppetto_braintree.subscription (
	id bigint(19) NOT NULL auto_increment,
	customer_id int(10),
	payment_method_token varchar(50),
	plan_id varchar(50),
	plan_name varchar(250),
	status_merchant_account varchar(50),
	descriptor_name varchar(250),
	descriptor_phone varchar(50),
	descriptor_url varchar(250),
	subscription_price double,
	subscription_currency varchar(50),
	trial_period varchar(250),
	first_bill_date date,
	current_billing_period varchar(250),
	paid_through_date varchar(250),
	next_bill_date date,
	balance double,
	currency varchar(250),
	next_billing_period_amount varchar(250),
	number_of_transactions int(10),
	number_of_addons int(10),
	number_of_discounts int(10),
	number_of_billing_cycles int(10),
	PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE geppetto_braintree.`transaction`(
	id bigint(19) NOT NULL auto_increment,
	transaction_id varchar(50) NOT NULL,
	transaction_date TIMESTAMP,
	transaction_type varchar(250),
	status varchar(250),
	customer_id bigint(19),
	credit_card varchar(250),
	credit_card_type varchar(250),
	amount double,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
INSERT INTO geppetto_braintree.customer(id, first_name, last_name, company, email, phone, fax, website, number_of_payment_methods, number_of_transactions, created_date, last_updated_date) VALUES (1, 'Reinaldo', 'Lopez', 'geppetto', 'lopezreinaldoernesto@gmail.com', '023439439', '90434920', '', 0, 0, '2015-09-22', '2015-09-22');
INSERT INTO geppetto_braintree.product (id, name, description, price, image) VALUES (1, 'Eget elementum vel', '', 102.00, 'pro1.jpg');
INSERT INTO geppetto_braintree.product (id, name, description, price, image) VALUES (2, 'Ut tellus dolor dapibus', '', 57.00, 'pro2.jpg');
INSERT INTO geppetto_braintree.product (id, name, description, price, image) VALUES (3, 'Cursus eleifend elit aenean', '', 99.00, 'pro3.jpg');
INSERT INTO geppetto_braintree.product (id, name, description, price, image) VALUES (4, 'Aliquam erat volutpat', '', 36.00, 'pro4.jpg');
INSERT INTO geppetto_braintree.product (id, name, description, price, image) VALUES (5, 'Eget elementum vel', '', 102.00, 'pro5.jpg');
INSERT INTO geppetto_braintree.product (id, name, description, price, image) VALUES (6, 'Ut tellus dolor dapibus', '', 57.00, 'pro6.jpg');
INSERT INTO geppetto_braintree.product (id, name, description, price, image) VALUES (7, 'Cursus eleifend elit aenean', '', 99.00, 'pro7.jpg');
INSERT INTO geppetto_braintree.product (id, name, description, price, image) VALUES (8, 'Aliquam erat volutpat', '', 38.00, 'pro8.jpg');
