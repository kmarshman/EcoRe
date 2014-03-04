package ecoreGui;

import java.awt.*;

import javax.swing.*;

import ecore.RMOS;

public class RcmUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public RcmUI(final RMOS rmos){
		CardLayout cards = new CardLayout();
		this.setLayout(cards);

		WelcomeUI welcome = new WelcomeUI(rmos, cards, this);
		RecycleSessionUI recycle = new RecycleSessionUI(rmos,cards, this);
		CompleteSessionUI finish = new CompleteSessionUI(rmos,cards, this);
		Thankyou thanks = new Thankyou(rmos, cards, this);

		add(welcome, "Welcome");
		add(recycle, "Recycle");
		add(finish, "Finish");
		add(thanks, "Thankyou Message"); //new card
	}
	
	

}
