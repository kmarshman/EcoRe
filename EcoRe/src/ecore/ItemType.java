package ecore;

import java.io.Serializable;

/**
 * Represents type or class of recyclable
 * @author Kelsey
 *
 */
public class ItemType implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	
	/**
	 * Creates new item type with given fields
	 * @param name
	 * @param price
	 */
	public ItemType(String name, double price){
		this.name = name;
		this.price = price;
	}
	
	/**
	 * Default constructor
	 */
	public ItemType(){
		name = "not set";
		price = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
}
