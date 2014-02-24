package rmos;

import java.util.ArrayList;

import rcm.RCM;
import rcm.RCM.Status;

public class RMOS {
	
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

}
