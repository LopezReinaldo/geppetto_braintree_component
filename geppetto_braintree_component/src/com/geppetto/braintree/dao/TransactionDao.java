package com.geppetto.braintree.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.geppetto.braintree.dao.support.InsertTransaction;
import com.geppetto.braintree.dto.Transaction;
import com.geppetto.braintree.interfaces.dao.ITransactionDao;

@Repository("TransactionDao")
public class TransactionDao implements ITransactionDao {
	
	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private InsertTransaction insert_transaction;
	
	@Value("${insert_transaction.sql}")
	private String insert_transaction_sql;
	
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
	public Transaction insert_transaction(Transaction aTransaction)
			throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("transaction_id", aTransaction.getTransaction_id());
		paramMap.put("transaction_date", aTransaction.getTransaction_date());
		paramMap.put("transaction_type", aTransaction.getTransaction_type());
		paramMap.put("status", aTransaction.getStatus());
		paramMap.put("customer_id", aTransaction.getCustomer().getId());
		paramMap.put("credit_card", aTransaction.getCredit_card());
		paramMap.put("credit_card_type", aTransaction.getCredit_card_type());
		paramMap.put("amount", aTransaction.getAmount());
							
		KeyHolder keyHolder = new GeneratedKeyHolder();
		InsertTransaction.SQL_INSERT_TRANSACTION =  insert_transaction_sql;
		this.insert_transaction = new InsertTransaction(this.dataSource);
		this.insert_transaction.updateByNamedParam(paramMap, keyHolder);
		aTransaction.setId(keyHolder.getKey().longValue());
		System.out.println("The transaction id is: " + aTransaction.getId());

		return aTransaction;
	}

}
