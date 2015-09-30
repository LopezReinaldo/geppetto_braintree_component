package com.geppetto.braintree.dto;

/***
 * @author Reinaldo<br>
 * Created Date: 10/09/2015<br>
 * 
 * This class is used to support the data from payments
 *
 */
public class PaymentDetail {
 
	private String nonce;		

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}			

}
