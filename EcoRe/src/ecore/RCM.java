package ecore;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import usageDataIO.UsageDataIO;

/**
 * Represents recycling machine
 * @author Kelsey
 *
 */
public class RCM implements Serializable{
	
	private static final long serialVersionUID = 1L;

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
		OPERATIONAL, NONOPERATIONAL;
	}
	
	private String id;
	private String location;
	private Status status;
	private State state;
	private double capacity, weight, cash;
	private int couponPaper;
	private Calendar timeLastEmptied;
	
	private String maintenanceKey;
	private double sessionAluminumWeight;
	private double sessionGlassWeight;
	private double sessionValue;
	private ArrayList<Item> sessionItems;
	private transient UsageDataIO fileIO;
	private transient UsageDataIO emptyIO;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss a");
	
	public RCM(String location, String id){
		this.id = id;
		this.location = location;
		status = Status.ACTIVE;
		state = State.OPERATIONAL;
		capacity = 250;
		weight = 0;
		cash = 500;
		couponPaper= 100;
		timeLastEmptied = Calendar.getInstance();
		maintenanceKey = "1234";
		sessionValue = 0;
		sessionAluminumWeight = 0;
		sessionGlassWeight = 0;
		sessionItems = new ArrayList<Item>();
		fileIO = new UsageDataIO("usage.txt");
		emptyIO = new UsageDataIO("empty.txt");
	}
	
	public RCM(){
		new RCM("not set", "-1");
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
	
	public Calendar getTimeLastEmptied(){
		return timeLastEmptied;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
	
	public void addCash(double cash){
		this.cash += cash;
		if(weight <= capacity) state = State.OPERATIONAL;
	}
	
	public void recycleItem(double value, double weight, String type){
		sessionValue += value;
		if(type.equals("Aluminum")){
			sessionAluminumWeight += (weight/16);
		}else{
			sessionGlassWeight += (weight/16);	
		}
		this.weight += (weight/16);
		if(this.weight >= capacity)  state = State.NONOPERATIONAL;
	}
	
	public void finishSession(){
		fileIO.open();
		String timestamp = dateFormat.format(Calendar.getInstance().getTime());
		String sessionData = getID() + "," + timestamp + "," + sessionValue + "," + sessionAluminumWeight/16 + "," + sessionGlassWeight/16 + ";";
		fileIO.write(sessionData);
		fileIO.close();
		
		sessionValue = 0;
		sessionAluminumWeight = 0;
		sessionGlassWeight = 0;
		sessionItems = new ArrayList<Item>();
	}
	
	public boolean aunthenticateWorker(String key){
		return key.equals(maintenanceKey);
	}
	
	public void empty(){
		setTimeLastEmptied(Calendar.getInstance());
		emptyIO.write(getID() + "," + dateFormat.format(timeLastEmptied.getTime()) + ";");
		weight = 0;
		if(cash >= 0 || couponPaper >= 0) state = State.OPERATIONAL;
	}
	
	public void addCoupon(int coupon){
		couponPaper += coupon;
		if(weight < capacity) state = State.OPERATIONAL;
	}
	
	public double getSessionAluminumWeight(){
		return sessionAluminumWeight;
	}
	
	public double getSessionGlassWeight(){
		return sessionGlassWeight;
	}
	
	public double getSessionValue(){
		return sessionValue;
	}
	
	public void setTimeLastEmptied(Calendar emptied){
		timeLastEmptied = emptied;
	}
	
	public void setIO(){
		fileIO = new UsageDataIO("usage.txt");
		emptyIO = new UsageDataIO("empty.txt");
	}
	
	public String printCash(){
		double temp = sessionValue;
		cash -= sessionValue;
		if(cash <= 0){
			state = State.NONOPERATIONAL;
		}

		int ten, five, one, quarter, dime, nickel, penny;
		ten = (int) (sessionValue/10);
		sessionValue = sessionValue%10;

		five = (int) (sessionValue/5);
		sessionValue = sessionValue%5;

		one = (int) (sessionValue/1);
		sessionValue = sessionValue%1;

		quarter = (int) (sessionValue/.25);
		sessionValue = sessionValue%.25;

		dime = (int) (sessionValue/.10);
		sessionValue = sessionValue%.10;

		nickel = (int) (sessionValue/.05);
		sessionValue = sessionValue%.05;

		penny = (int) (sessionValue/.01);
		sessionValue = sessionValue%.01;
		
		sessionValue = temp;
		return "<html>" + ten + " tens <br>" + five + " fives<br>"+ one + " ones<br>" + quarter + " quarters<br>" + dime + " dimes<br>"+ nickel + " nickels<br>" + penny + " pennys</html>";
	}
	
	public String printCoupon(){
		couponPaper --;
		if(couponPaper <= 0){
			state = State.NONOPERATIONAL;
		}
		return "<html><center>This $" + sessionValue + " coupon is redeemable at common grocery outlets.<br>Visit ecore.org to see a complete list.</center<</html>";
	}
	
	public ArrayList<Item> getSessionItems(){
		return sessionItems;
	}

}
