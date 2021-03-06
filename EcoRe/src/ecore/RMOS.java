package ecore;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;

import usageDataIO.UsageDataIO;
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
	
	private transient UsageDataIO fileIO;
	private transient UsageDataIO emptyIO;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss a");
	
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
		
		totalAluminum = 0;
		totalGlass = 0;
		
		fileIO = new UsageDataIO("usage.txt");
		emptyIO = new UsageDataIO("empty.txt");
		
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
	
	public void setTotalWeights(){
		fileIO.open();
		String data = fileIO.read();
		fileIO.close();
		
		Scanner scanner = new Scanner(data);
		Scanner scan = scanner.useDelimiter(",|;");
		
		ArrayList<String> values = new ArrayList<String>();
		while(scan.hasNext()){
			values.add(scan.next());
		}
		
		totalAluminum = 0;
		for(int i = 3; i < values.size(); i+=5){
			totalAluminum += Double.parseDouble(values.get(i));
		}
		
		totalGlass = 0;
		for(int i = 4; i < values.size(); i+=5){
			totalGlass += Double.parseDouble(values.get(i));
		}
		
		scanner.close();
		scan.close();
	}
	
	public double getTotalAluminumWeight(){		
		return totalAluminum;
	}
	
	public double getTotalGlassWeight(){		
		return totalGlass;
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
	
	public void setIO(){
		fileIO = new UsageDataIO("usage.txt");
		emptyIO = new UsageDataIO("empty.txt");
		for(RCM m: rcmGroup){
			m.setIO();
		}
	}
	
	public HashMap<String, Double> getValues(){	
		fileIO.open();
		String data = fileIO.read();
		fileIO.close();
		
		Scanner scanner = new Scanner(data);
		Scanner scan = scanner.useDelimiter(";");
		
		HashMap<String, Double> map = new HashMap<String, Double>();
		if(metric.equals("Value")){
			while(scan.hasNext()){
				String line = scan.next();
				String[] fields = line.split(",");
				if(fields.length >= 5){
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(dateFormat.parse(fields[1]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Calendar today = Calendar.getInstance();
					boolean add = false;
					switch(timeframe){
					case "Day":
						if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR) && date.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) add = true; 
						break;
					case "Week":
						if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR) && date.get(Calendar.WEEK_OF_YEAR) == today.get(Calendar.WEEK_OF_YEAR)) add = true; 
						break;
					case "Month":
						if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR) && date.get(Calendar.MONTH) == today.get(Calendar.MONTH)) add = true; 
						break;
					}
					if(add){
						Double old = 0.0;
						if(map.containsKey(fields[0])){
							old = map.get(fields[0]);
						}
						map.put(fields[0], old + Double.parseDouble(fields[2]));
					}
				}
			}
		}else{
			while(scan.hasNext()){
				String[] fields = scan.next().split(",");
				if(fields.length >= 5){
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(dateFormat.parse(fields[1]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Calendar today = Calendar.getInstance();
					boolean add = false;
					switch(timeframe){
					case "Day":
						if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR) && date.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) add = true; 
						break;
					case "Week":
						if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR) && date.get(Calendar.WEEK_OF_YEAR) == today.get(Calendar.WEEK_OF_YEAR)) add = true; 
						break;
					case "Month":
						if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR) && date.get(Calendar.MONTH) == today.get(Calendar.MONTH)) add = true; 
						break;
					}
					if(add){
						Double old = 0.0;
						if(map.containsKey(fields[0])){
							old = map.get(fields[0]);
						}
						map.put(fields[0], old + Double.parseDouble(fields[3]) + Double.parseDouble(fields[4]));
					}
				}
			}			
		}

		scanner.close();
		scan.close();
		return map;
	}
	
	public HashMap<String, Integer> getWeekEmptyData(){
		emptyIO.open();
		String data = emptyIO.read();
		emptyIO.close();
		
		Scanner scanner = new Scanner(data);
		Scanner scan = scanner.useDelimiter(";");
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
			while(scan.hasNext()){
				String[] fields = scan.next().split(",");
				if(fields.length >= 2){
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(dateFormat.parse(fields[1]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Calendar today = Calendar.getInstance();
					if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR) && date.get(Calendar.WEEK_OF_YEAR) == today.get(Calendar.WEEK_OF_YEAR)) {
						int old = 0;
						if(map.containsKey(fields[0])){
							old = map.get(fields[0]);
						}
						map.put(fields[0], old + 1);
					}
				}
			}			

		scanner.close();
		scan.close();
		return map;
	}
	
	public HashMap<String, Integer> getMonthEmptyData(){
		emptyIO.open();
		String data = emptyIO.read();
		emptyIO.close();
		
		Scanner scanner = new Scanner(data);
		Scanner scan = scanner.useDelimiter(";");
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
			while(scan.hasNext()){
				String[] fields = scan.next().split(",");
				if(fields.length >= 2){
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(dateFormat.parse(fields[1]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Calendar today = Calendar.getInstance();
						if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR) && date.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
						int old = 0;
						if(map.containsKey(fields[0])){
							old = map.get(fields[0]);
						}
						map.put(fields[0], old + 1);
					}
				}
			}			

		scanner.close();
		scan.close();
		return map;	
	}
	
	public HashMap<String, Integer> getYearEmptyData(){
		emptyIO.open();
		String data = emptyIO.read();
		emptyIO.close();
		
		Scanner scanner = new Scanner(data);
		Scanner scan = scanner.useDelimiter(";");
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
			while(scan.hasNext()){
				String[] fields = scan.next().split(",");
				if(fields.length >= 2){
					Calendar date = Calendar.getInstance();
					try {
						date.setTime(dateFormat.parse(fields[1]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Calendar today = Calendar.getInstance();
						if(date.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
						int old = 0;
						if(map.containsKey(fields[0])){
							old = map.get(fields[0]);
						}
						map.put(fields[0], old + 1);
					}
				}
			}			

		scanner.close();
		scan.close();
		return map;
	}
	
	public void rcmUpdate(){
		setChanged();
		notifyObservers(this);
	}

}
