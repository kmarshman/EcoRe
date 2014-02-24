package rmos;

public class Item {
	
	private String name;
	private ItemType type;
	private double weight;
	
	public Item(String name, ItemType type, double weight){
		this.name = name;
		this.type = type;
		this.weight = weight;
	}
	
	public Item(){
		name = "not set";
		type = null;
		weight = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public ItemType getType(){
		return type;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public double getValue(){
		return Math.round(weight * type.getPrice() * 100.0) / 100.0;
	}

}
