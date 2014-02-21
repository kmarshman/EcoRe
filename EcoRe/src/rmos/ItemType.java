package rmos;

public class ItemType {

	private String name;
	private double price;
	
	public ItemType(String name, double price){
		this.name = name;
		this.price = price;
	}
	
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
