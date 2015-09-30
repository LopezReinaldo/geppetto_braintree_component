package com.geppetto.braintree.dto;

/***
 * @author Reinaldo<br>
 * Created Date: 10/09/2015<br>
 * 
 * This class is used to support the data from products
 *
 */
public class Product {
	
	private int id;
	private String name;
	private String description;
	private double price;
	private String image;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}		

}
