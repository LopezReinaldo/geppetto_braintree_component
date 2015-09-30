package com.geppetto.braintree.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geppetto.braintree.dao.support.CustomerMapper;
import com.geppetto.braintree.dto.Customer;
import com.geppetto.braintree.interfaces.dao.ICustomerDao;
/**
 * 
 * @author Reinaldo</br> 
 * Date Created: 10/09/2015</br>
 *
 * The purpose of this class is to interact with the db for the basic
 * search</br> and CRUD operations for the customers</p>
 *
 */

@Repository("CustomerDao")
public class CustomerDao implements ICustomerDao{
	
	private Log log = LogFactory.getLog(getClass());
	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Value("${find_customer_by_id.sql}")
	private String find_customer_by_id_sql;
	
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Override
	public Customer get_customer_by_id(long customer_id) throws Exception {
		
		MapSqlParameterSource parameters;
		parameters = new MapSqlParameterSource();
		parameters.addValue("customer_id", customer_id);
		CustomerMapper the_mapper = new CustomerMapper();
		List<Customer> dto_list = this.namedParameterJdbcTemplate.query(
				find_customer_by_id_sql, parameters, the_mapper);
		
		if (dto_list.size() < 1) {
			throw new Exception("There is no customer for id   " + customer_id);

		}

		return dto_list.get(0);
		
	}

}
