package ecore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

/**
 * Represents recycling machine
 * @author Kelsey
 *
 */
public class RCM extends Observable{
	
	/**
	 * Status options for RCM
	 * Default: INACTIVE
	 * @author Kelsey
	 *
	 */
	public enum Status {
		ACTIVE, INACTIVE;
	}
	
	/**
	 * State options for RCM
	 * Default: OPERATING
	 * @author Kelsey
	 *
	 */
	public enum State {
		FULL, NEAR_CAPACITY, NEED_CASH, NEED_COUPONS, OPERATING;
	}
	
	private String id;
	private String location;
	private Status status;
	private State state;
	private double capacity, weight, cash;
	private int couponPaper;
	private Date timeLastEmptied;
	/**
	 * shared array list of accepted items
	 */
	private static ArrayList<Item> acceptedItems = new ArrayList<Item>();
	
	public RCM(String location){
		id = String.valueOf(Math.random() * ( 9999 - 1000 ));
		this.location = location;
		status = Status.ACTIVE;
		state = State.OPERATING;
		capacity = 250;
		weight = 0;
		cash = 500;
		couponPaper= 100;
		timeLastEmptied = new Date();
	}
	
	public RCM(){
		id = "423423";
		location = "not set";
		status = Status.INACTIVE;
		state = State.OPERATING;
		capacity = 250;
		weight = 0;
		cash = 500;
		couponPaper= 100;
		timeLastEmptied = new Date();
	}
	
	public String getID(){
		return id;
	}
	
	public String getLocation(){
		return location;
	}
	
	
	public State getState(){
		return state;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public double getCapacity(){
		return capacity;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public double getCash(){
		return cash;
	}
	
	public int getCouponPaper(){
		return couponPaper;
	}
	
	public Date getTimeLastEmptied(){
		return timeLastEmptied;
	}
	
	public ArrayList<Item> getAcceptedItems(){
		return acceptedItems;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}

}
