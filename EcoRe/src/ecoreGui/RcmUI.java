package ecoreGui;

import java.awt.*;

import javax.swing.*;

public class RcmUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public RcmUI(){
		CardLayout cards = new CardLayout();
		this.setLayout(cards);
		
		WelcomeUI welcome = new WelcomeUI();
		RecycleSessionUI recycle = new RecycleSessionUI();
		CompleteSessionUI finish = new CompleteSessionUI();
		MaintenanceUI maintenance = new MaintenanceUI();
		
		add(welcome, "Welcome");
		add(recycle, "Recycle");
		add(finish, "Finish");
		add(maintenance, "Maintenance");
	}

}
