package com.geppetto.braintree.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.geppetto.braintree.dto.Customer;
import com.geppetto.braintree.dto.Transaction;
import com.geppetto.braintree.interfaces.dao.ITransactionDao;
import com.geppetto.braintree.interfaces.services.ITransactionService;

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
@Service("TransactionService")
public class TransactionService implements ITransactionService {
	
	ITransactionDao transaction_dao;

	public ITransactionDao getTransaction_dao() {
		return transaction_dao;
	}
	@Resource(name="TransactionDao")
	public void setTransaction_dao(ITransactionDao transaction_dao) {
		this.transaction_dao = transaction_dao;
	}

	@Override
	public Transaction add_transaction(
			com.braintreegateway.Transaction transaction, Customer customer) throws Exception {			
		
		Transaction aTransaction = new Transaction();
		
		aTransaction.setTransaction_id(transaction.getId());
		aTransaction.setTransaction_date(transaction.getCreatedAt().getTime());
		aTransaction.setTransaction_type(transaction.getType().toString());
		aTransaction.setStatus(transaction.getStatus().toString());
		aTransaction.setCustomer(customer);
		aTransaction.setCredit_card(transaction.getCreditCard().getMaskedNumber());
		aTransaction.setCredit_card_type(transaction.getCreditCard().getCardType());
		aTransaction.setAmount(transaction.getAmount());						
		
		return transaction_dao.insert_transaction(aTransaction);
	}

}
