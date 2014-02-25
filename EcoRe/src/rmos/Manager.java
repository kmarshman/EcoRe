package rmos;

/**
 * Represents an RMOS manager
 * @author Kelsey
 *
 */
public class Manager {
	
	private String username;
	private String password;
	
	public Manager(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public Manager(){
		username = "Jane Doe";
		password = "password";
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}

}
