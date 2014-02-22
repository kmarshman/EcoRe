package rmos;

public class RMOS {
	
	private Manager[] managers = new Manager[2];
	
	public RMOS(){
		Manager manager1 = new Manager("admin", "pass");
		Manager manager2 = new Manager("manager", "password");
		
		managers[0] = manager1;
		managers[1] = manager2;
	}
	
	public boolean authenticate(String username, String password){
		for (Manager m: managers){ 
			if(m.getUsername().equals(username) && m.getPassword().equals(password)) return true;
		}
		return false;
	}

}
