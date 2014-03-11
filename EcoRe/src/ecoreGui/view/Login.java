package ecoreGui.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ecore.RMOS;

/**
 * Login panel for RMOS system
 * @author Kelsey
 *
 */
public class Login extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private RMOS rmos;
	private JTextField username;
	private JPasswordField password;
	
	/**
	 * Default Constructor
	 */
	public Login(){
		new Login(new RMOS(), new CardLayout(), new JPanel());
	}
	
	/**
	 * Creates login panel for RMOS
	 * @param rmos
	 */
	public Login(RMOS rmos, final CardLayout cards, final JPanel cardPanel){
		this.rmos = rmos;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(148, 255, 123));
		
		JPanel inputs = new JPanel();
		inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
		
		JPanel name = new JPanel(new FlowLayout());
		name.setBackground(new Color(148, 255, 123));
		JLabel usernameLabel = new JLabel("Username:");
		username = new JTextField(20);
		username.setBackground(Color.WHITE);
		name.setFont(new Font("Sans Serif", Font.BOLD, 14));
		name.add(usernameLabel);
		name.add(username);
		
		JPanel pass = new JPanel(new FlowLayout());
		pass.setBackground(new Color(148, 255, 123));
		JLabel passwordLabel = new JLabel("Password:");
		password = new JPasswordField(18);
		password.setBackground(Color.WHITE);
		password.setFont(new Font("Sans Serif", Font.BOLD, 14));
		password.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(authenticate()){
					cards.next(cardPanel);
				}else{
					loginFailed();
				}
			}
		});
		pass.add(passwordLabel);
		pass.add(password);
		
		
		inputs.add(name);
		inputs.add(pass);
		
		add(inputs);
	}
	
	/**
	 * Checks if username and password
	 * @return True if user is verified, False otherwise
	 */
	public boolean authenticate(){
		return rmos.authenticate(username.getText().trim(), new String(password.getPassword())); 
	}
	
	/**
	 * Called if username and password are not found
	 */
	public void loginFailed(){
		JOptionPane.showMessageDialog(this, "Username and/or password is incorrect. Please try again", "Login Failed", JOptionPane.ERROR_MESSAGE);
		password.setText("");
	}
	
	public void clear(){
		password.setText("");
		username.setText("");
	}
}
