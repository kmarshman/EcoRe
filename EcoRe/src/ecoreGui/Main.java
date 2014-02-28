package ecoreGui;

import java.awt.*;

import javax.swing.*;

import ecore.Item;
import ecore.RCM;
import ecore.RMOS;

/**
 * 
 * @author Kelsey
 *
 */
public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
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
		
		RcmUI rcmUI = new RcmUI();
		RmosUI rmosUI = new RmosUI(rmos);
    	
    	getContentPane().add(rmosUI);
    	getContentPane().add(rcmUI);
    	
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
