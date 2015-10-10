package com.geppetto.braintree.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.geppetto.braintree.dto.Product;
import com.geppetto.braintree.interfaces.dao.IProductDao;
import com.geppetto.braintree.interfaces.services.IProductService;


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
@Service("ProductService")
public class ProductService implements IProductService {

	private IProductDao product_dao;		
	
	public IProductDao getProduct_dao() {
		return product_dao;
	}

	@Resource(name="ProductDao")
	public void setProduct_dao(IProductDao product_dao) {
		this.product_dao = product_dao;
	}


	@Override
	public ArrayList<Product> list_products() throws Exception {
		
		ArrayList<Product> the_list = this.product_dao.get_products();
		
		return the_list;
	}

	@Override
	public Product find_product_by_id(long id) throws Exception {		
		
		return this.product_dao.get_product_by_id(id); 
	}

}
