package ecore;

/**
 * Represents recyclable item
 * @author Kelsey
 *
 */
public class Item {
	
	private String name;
	private ItemType type;
	/**
	 * weight of item in oz
	 */
	private double weight;
	
	/**
	 * Creates new item with given fields
	 * @param name
	 * @param type
	 * @param weight
	 */
	public Item(String name, ItemType type, double weight){
		this.name = name;
		this.type = type;
		this.weight = weight;
	}
	
	/**
	 * Default Constructor
	 */
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
	
	public double getWeightInKg(){
		return weight * 28.3495;
	}
	
	/**
	 * Calculates value of item using its weight and the price per lb for its type
	 * Weight is converted to lb from oz
	 * @return
	 */
	public double getValue(){
		return Math.round((weight/16) * type.getPrice() * 100.0) / 100.0;
	}

}
