package ecoreGui;

import java.awt.*;

import javax.swing.*;

public class WelcomeUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public WelcomeUI(){
		setBackground(Color.pink);
		
		JButton start = new JButton("Start");
		JButton maintenance = new JButton("Maintenace");
		
		add(start);
		add(maintenance);
	}

}
