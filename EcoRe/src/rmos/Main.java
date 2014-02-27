package rmos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import rcm.*;

/**
 * 
 * @author Kelsey
 *
 */
public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private final JPanel cardPanel;
	/**
	 * Login panel for RMOS system
	 */
	private final Login login;
	private final CardLayout cards;
	
	/**
	 * Creates main window split into 2 panels: left for RMOS and right for RCMs
	 * @param rmos instance of model represented by UI
	 */
	public Main(RMOS rmos){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("EcoRe");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension((screenSize.width/8)*7, screenSize.height/5*4));
		setLocationRelativeTo(null);
		
		
		setLayout(new GridLayout(1,2));
		
		RecycleUI rcm = new RecycleUI();
		
		cardPanel = new JPanel();
		cards = new CardLayout();
		cardPanel.setLayout(cards);
		
		JPanel loginContainer = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
		
		cons.gridx = 2;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.PAGE_START;
		JLabel welcome = new JLabel("<html><center>Welcome to EcoRe!<br>Enter your login informaiton to get started.<center><html>");
		welcome.setFont(new Font("Serif", Font.BOLD, 14));
		loginContainer.add(welcome, cons);
		
		cons.gridx = 2;
		cons.gridy = 1;
		cons.anchor = GridBagConstraints.LINE_END;
		login = new Login(rmos);
		login.setBorder(new EmptyBorder(10, 10, 0, 10));
		loginContainer.add(login, cons);
		
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
		loginContainer.add(go,cons);
		
		DashboardUI dashboard = new DashboardUI(rmos);
		MachineManagerUI machineManager = new MachineManagerUI(rmos);
		RecyclablesManagerUI recyclablesManager = new RecyclablesManagerUI(rmos);
		
    	JTabbedPane tabbedPane = new JTabbedPane();
    	tabbedPane.addTab("Home", dashboard);
    	tabbedPane.addTab("Manage Machines", machineManager);
    	tabbedPane.addTab("Manage Recyclables", recyclablesManager);
    	
    	cardPanel.add(loginContainer, "Login");
    	cardPanel.add(tabbedPane, "Tabs");
    	
    	getContentPane().add(cardPanel);
    	getContentPane().add(rcm);
    	
    	setVisible(true);
	}
	
	/**
	 * TODO: RCMs and Items are hardcoded for testing purposes
	 * @param args
	 */
	public static void main(String[] args) {
		RMOS rmos = new RMOS();
		
		rmos.addRCM(new RCM("test location"));
		rmos.addRCM(new RCM("test location2"));
		rmos.addRCM(new RCM("test location3"));
		rmos.addRCM(new RCM());
		
		rmos.addItem(new Item("Soda Can", rmos.getAluminum(), .5));
		rmos.addItem(new Item("Pop-top Soup Can", rmos.getAluminum(), .53));
		rmos.addItem(new Item("Jam Jar", rmos.getGlass(), .74));

		new Main(rmos);
	}
}
