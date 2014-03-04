package ecoreGui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ecore.RMOS;

public class Thankyou extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Thankyou(RMOS rmos,final CardLayout cards, final JPanel cardPanel)
	{
		JLabel thanklabel = new JLabel("<html>Thank you for using the recycle machine.</html>");
		add(thanklabel);	

		JButton homeButton = new JButton("HOME");
		homeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				cards.next(cardPanel);

			}

		});
		add(homeButton);
		
			}


}