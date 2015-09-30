package com.geppetto.braintree.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geppetto.braintree.dto.Customer;
/**
 * 
 * @author Reinaldo</br> 
 * Date Created: 10/09/2015</br>
 *
 * Standard mapper used in get data from the customer table</p>
 *
 *
 */
public class CustomerMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Customer dto = new Customer();
		
		dto.setId(rs.getLong("CUSTOMER_ID"));
		dto.setFirst_name(rs.getString("FIRST_NAME"));
		dto.setLast_name(rs.getString("LAST_NAME"));
		dto.setCompany(rs.getString("COMPANY"));
		dto.setEmail(rs.getString("EMAIL"));
		dto.setPhone(rs.getString("PHONE"));
		dto.setFax(rs.getString("FAX"));
		dto.setWebsite(rs.getString("WEBSITE"));
		dto.setNumber_of_payment_methods(rs.getInt("NUMBER_OF_PAYMENT_METHODS"));
		dto.setNumber_of_transactions(rs.getInt("NUMBER_OF_TRANSACTIONS"));
		dto.setCreated_date(rs.getTimestamp("CREATED_DATE"));
		dto.setLast_updated_date(rs.getTimestamp("LAST_UPDATED_DATE"));
		
		
		return dto;
	}

}
