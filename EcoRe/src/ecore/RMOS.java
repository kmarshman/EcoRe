package ecore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import ecore.RCM.Status;

/**
 * Represents recyling management system
 * @author Kelsey
 *
 */
public class RMOS extends Observable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Manager[] managers = new Manager[2];
	private ArrayList<RCM> rcmGroup = new ArrayList<RCM>();
	private ItemType[] itemTypes = new ItemType[2];
	private ArrayList<Item> acceptedItems = new ArrayList<Item>();
	
	private double totalAluminum;
	private double totalGlass;
	
	private transient String metric;
	private transient String timeframe;
	private transient String chartTitle;
	
	/**
	 * Creates new RMOS with default managers and item types
	 */
	public RMOS(){
		managers[0] = new Manager("admin", "pass");
		managers[1] = new Manager("manager", "password");
		
		itemTypes[0] = new ItemType("Glass", 1.00);
		itemTypes[1] = new ItemType("Aluminum", 0.75);
		
		totalAluminum = 80;
		totalGlass = 20;
		
		metric = "Value";
		timeframe = "Day";
		chartTitle = "Value By Day";
	}
	
	/**
	 * Verifies username and password
	 * @param username
	 * @param password
	 * @return True if username and password match a manager in the system, False otherwise
	 */
	public boolean authenticate(String username, String password){
		for (Manager m: managers){ 
			if(m.getUsername().equals(username) && m.getPassword().equals(password)) return true;
		}
		return false;
	}
	
	public ArrayList<RCM> getRCMGroup(){
		return rcmGroup;
	}
	
	/**
	 * Adds rcm to RCM group
	 * @param rcm
	 */
	public void addRCM(RCM rcm){
		rcmGroup.add(rcm);
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Remvoes rcm with given id from RCM group
	 * @param id
	 */
	public void removeRCM(String id){
		RCM rcmToRemove = null;
		for(RCM m : rcmGroup){
			if (m.getID().equals(id)) rcmToRemove = m;
		}
		rcmGroup.remove(rcmToRemove);
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Changes status of RCM with given id
	 * @param id
	 * @param status new status
	 */
	public void changeRCMstatus(String id, Status status){
		int index = 0;
		for(RCM m : rcmGroup){
			if (m.getID().equals(id)) break;
			index++;
		}
		if (index < rcmGroup.size()){
			rcmGroup.get(index).setStatus(status);
			setChanged();
			notifyObservers(this);
		}
	}
	
	/**
	 * Calculates number of active RCMs in RCM group
	 * @return
	 */
	public int getNumActiveRCMs(){
		int num = 0;
		for (RCM machine: rcmGroup){
			if (machine.getStatus() == Status.ACTIVE) num++;
		}
		return num;
	}
	
	public ArrayList<Item> getAcceptedItems(){
		return acceptedItems;
	}
	
	/**
	 * Adds item to accepted items list
	 * @param newItem
	 */
	public void addItem(Item newItem){
		acceptedItems.add(newItem);
		rcmGroup.get(0).getAcceptedItems().add(newItem);
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Removes item from accepted items list
	 * @param name
	 */
	public void removeItem(String name){
		Item itemToRemove = null;
		for(Item i : acceptedItems){
			if (i.getName().equals(name)) itemToRemove = i;
		}
		acceptedItems.remove(itemToRemove);
		rcmGroup.get(0).getAcceptedItems().remove(itemToRemove);
		setChanged();
		notifyObservers(this);
	}
	
	public ItemType getGlass(){
		return itemTypes[0];
	}
	
	public ItemType getAluminum(){
		return itemTypes[1];
	}
	
	public ItemType[] getItemTypes(){
		return itemTypes;
	}
	
	/**
	 * Changes price of glass items
	 * @param price
	 */
	public void setGlassPrice(double price){
		itemTypes[0].setPrice(price);
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Changes price of aluminum items
	 * @param price
	 */
	public void setAluminumPrice(double price){
		itemTypes[1].setPrice(price);
		setChanged();
		notifyObservers(this);
	}
	
	public double getTotalAluminumWeight(){
		return totalAluminum;
	}
	
	public double getTotalGlassWeight(){
		return totalGlass;
	}
	
	public void setItemStatistics(){
		totalAluminum = 60;
		totalGlass = 40;
		setChanged();
		notifyObservers(this);
	}

	public String getMetric() {
		return metric;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public String getChartTitle() {
		return chartTitle;
	}
	
	public void setChart(String title, String metric, String timeframe){
		chartTitle = title;
		this.metric = metric;
		this.timeframe = timeframe;
		setChanged();
		notifyObservers(this);
	}
	
	public ArrayList<String> getIDs(){
		ArrayList<String> ids = new ArrayList<String>();
		for(RCM m: rcmGroup){
			ids.add(m.getID());
		}
		return ids;
	}
	
	public ArrayList<Double> getValues(){
		ArrayList<Double> values = new ArrayList<Double>();
		for(RCM m: rcmGroup){
			values.add(1000 + Double.parseDouble(m.getID()));
		}
		return values;
	}

}
