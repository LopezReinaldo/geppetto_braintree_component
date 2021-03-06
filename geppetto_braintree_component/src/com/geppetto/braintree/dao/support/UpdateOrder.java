package com.geppetto.braintree.dao.support;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

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
public class UpdateOrder extends SqlUpdate {
	
	public static String SQL_UPDATE_ORDER = "";

	public UpdateOrder(DataSource dataSource) {
		super(dataSource, SQL_UPDATE_ORDER);

		super.declareParameter(new SqlParameter("id", Types.BIGINT));
		super.declareParameter(new SqlParameter("customer_id", Types.BIGINT));
		super.declareParameter(new SqlParameter("order_date", Types.VARCHAR));
		super.declareParameter(new SqlParameter("status", Types.VARCHAR));
	}

}
