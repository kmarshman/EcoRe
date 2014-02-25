package rmos;

import java.util.ArrayList;
import java.util.Observable;

import rcm.RCM;
import rcm.RCM.Status;

public class RMOS extends Observable{
	
	private Manager[] managers = new Manager[2];
	private ArrayList<RCM> rcmGroup = new ArrayList<RCM>();
	private ItemType[] itemTypes = new ItemType[2];
	private ArrayList<Item> acceptedItems = new ArrayList<Item>();
	
	public RMOS(){
		managers[0] = new Manager("admin", "pass");
		managers[1] = new Manager("manager", "password");
		
		itemTypes[0] = new ItemType("Glass", 1.00);
		itemTypes[1] = new ItemType("Aluminum", 0.75);
	}
	
	public boolean authenticate(String username, String password){
		for (Manager m: managers){ 
			if(m.getUsername().equals(username) && m.getPassword().equals(password)) return true;
		}
		return false;
	}
	
	public ArrayList<RCM> getRCMGroup(){
		return rcmGroup;
	}
	
	public void addRCM(RCM rcm){
		rcmGroup.add(rcm);
		setChanged();
		notifyObservers(this);
	}
	
	public void removeRCM(String id){
		RCM rcmToRemove = null;
		for(RCM m : rcmGroup){
			if (m.getID().equals(id)) rcmToRemove = m;
		}
		rcmGroup.remove(rcmToRemove);
		setChanged();
		notifyObservers(this);
	}
	
	public void changeRCMstatus(String id, Status status){
		int index = 0;
		for(RCM m : rcmGroup){
			if (m.getID().equals(id)) break;
			index++;
		}
		rcmGroup.get(index).setStatus(status);
		setChanged();
		notifyObservers(this);
	}
	
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
	
	public void addItem(Item newItem){
		acceptedItems.add(newItem);
		setChanged();
		notifyObservers(this);
	}
	
	public void removeItem(String name){
		Item itemToRemove = null;
		for(Item i : acceptedItems){
			if (i.getName().equals(name)) itemToRemove = i;
		}
		acceptedItems.remove(itemToRemove);
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
	
	public void setGlassPrice(double price){
		itemTypes[0].setPrice(price);
		setChanged();
		notifyObservers(this);
	}
	
	public void setAluminumPrice(double price){
		itemTypes[1].setPrice(price);
		setChanged();
		notifyObservers(this);
	}

}
