package ecoreGui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WelcomeUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public WelcomeUI(){
		JButton start = new JButton("Start");
		JButton maintenance = new JButton("Maintenace");
		
		add(start);
		add(maintenance);
	}

}
