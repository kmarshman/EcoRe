package rmos;

import java.awt.*;
import javax.swing.*;


public class Login extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private RMOS rmos;
	private JTextField username;
	private JPasswordField password;
	
	public Login(RMOS rmos){
		this.rmos = rmos;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel inputs = new JPanel();
		inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
		
		JPanel name = new JPanel(new FlowLayout());		
		JLabel usernameLabel = new JLabel("Username:");
		username = new JTextField(20);
		name.add(usernameLabel);
		name.add(username);
		
		JPanel pass = new JPanel(new FlowLayout());
		JLabel passwordLabel = new JLabel("Password:");
		password = new JPasswordField(20);
		pass.add(passwordLabel);
		pass.add(password);
		
		inputs.add(name);
		inputs.add(pass);
		
		add(inputs);
	}
	
	public boolean authenticate(){
		return rmos.authenticate(username.getText().trim(), new String(password.getPassword())); 
	}
	
	public void loginFailed(){
		JOptionPane.showMessageDialog(this, "Username and/or password is incorrect. Please try again", "Login Failed", JOptionPane.ERROR_MESSAGE);
		password.setText("");
	}
}
