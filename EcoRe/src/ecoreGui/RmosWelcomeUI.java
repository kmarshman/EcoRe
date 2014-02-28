package ecoreGui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ecore.RMOS;
import view.Login;

public class RmosWelcomeUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Login panel for RMOS system
	 */
	private final Login login;

	public RmosWelcomeUI(RMOS rmos, final CardLayout cards, final JPanel cardPanel){
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
		
		cons.gridx = 2;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.PAGE_START;
		JLabel welcome = new JLabel("<html><center>Welcome to EcoRe!<br>Enter your login informaiton to get started.<center><html>");
		welcome.setFont(new Font("Serif", Font.BOLD, 14));
		add(welcome, cons);
		
		cons.gridx = 2;
		cons.gridy = 1;
		cons.anchor = GridBagConstraints.LINE_END;
		login = new Login(rmos, cards, cardPanel);
		login.setBorder(new EmptyBorder(10, 10, 0, 10));
		add(login, cons);
		
		JButton go = new JButton("Login");
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(login.authenticate()){
					cards.next(cardPanel);
				}else{
					login.loginFailed();
				}
			}
		});
		cons.gridx = 2;
		cons.gridy =2;
		cons.anchor = GridBagConstraints.LINE_END;
		add(go,cons);
	}

}
