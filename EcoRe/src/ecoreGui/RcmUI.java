package ecoreGui;

import java.awt.*;

import javax.swing.*;

import ecore.RMOS;
import ecoreGui.control.RcmSelector;

public class RcmUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public RcmUI(final RMOS rmos){
		CardLayout cards = new CardLayout();
		this.setLayout(cards);

		WelcomeUI welcome = new WelcomeUI(rmos, cards, this);
		RecycleSessionUI recycle = new RecycleSessionUI(null,cards, this);
		CompleteSessionUI finish = new CompleteSessionUI(null,cards, this);
		MaintenanceUI maintenance = new MaintenanceUI(null, cards, this);
		RcmSelector selector = new RcmSelector(rmos, cards, this, welcome, recycle, finish, maintenance);
		rmos.addObserver(selector);

		add(selector, "RCM Selector");
		add(welcome, "Welcome");
		add(recycle, "Recycle");
		add(finish, "Finish");
		add(maintenance, "Maintenance");
	}
	
	

}
