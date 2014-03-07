package ecoreGui;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ecore.RCM;
import ecore.RMOS;

public class MaintenanceUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private RCM rcmObj;
	private JTextField maintanenceKey;
	
	public MaintenanceUI(RMOS rmos, final CardLayout cards, final JPanel cardPanel)
	{
	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel name = new JPanel(new FlowLayout());		
		JLabel keyInfo = new JLabel("Please enter your 4 digit key to access this machine.:");
		maintanenceKey = new JTextField(15);		
		name.add(keyInfo);
		name.add(maintanenceKey);
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(authenticate())
				{
					cards.next(cardPanel);
					
				}
				else
				{
					loginFailed();
				}
				
			}
			
		});
		
		mainPanel.add(name);
		mainPanel.add(doneButton);
		add(mainPanel);
		}
		public boolean authenticate()
		{
			return rcmObj.aunthenticateWorker(maintanenceKey.getText());
		}
		public void loginFailed(){
			JOptionPane.showMessageDialog(this, "The key entered is wrong. Please try again", "Login Failed", JOptionPane.ERROR_MESSAGE);
			maintanenceKey.setText("");
		}
		
		public void setRCM(RCM rcm){
			rcmObj = rcm;
		}
		
}