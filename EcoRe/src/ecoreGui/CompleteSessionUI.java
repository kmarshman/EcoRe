//CompleteSessionUI
package ecoreGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ecore.RCM;
import ecore.RMOS;

public class CompleteSessionUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static RCM rcm;
	protected CardLayout cards;
	protected JPanel cardPanel;

	public CompleteSessionUI(RCM rcm,final CardLayout cards, final JPanel cardPanel){
		this.rcm = rcm;
		this.cards = cards;
		this.cardPanel = cardPanel;
	}
	
	private void display(){
		//setBackground(Color.cyan);
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(590,620));
		JLabel headinglabel = new JLabel("Recycle with EcoRe !");
		mainPanel.add(headinglabel,BorderLayout.NORTH);
		mainPanel.setBackground(Color.cyan);

		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel centrePanel = new JPanel(new BorderLayout());
		centrePanel.setBackground(Color.cyan);
		//Hardcoded values
		JLabel messagelabel = new JLabel("<html>You Deposited : <br>Glass : 2.3lb <br> Aluminium : 1.43lb <br> Plastic : 0.56lb </html>");
		centrePanel.add(messagelabel,BorderLayout.CENTER);
		mainPanel.add(centrePanel,BorderLayout.CENTER);




		JPanel bottomPanel = new JPanel();
		JButton cashButton = new JButton("Get Cash");
		cashButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		JButton couponButton = new JButton("Get Coupon");
		couponButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		JButton exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(cardPanel, "RCM Selector");

			}

		});


		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.setBorder((new TitledBorder(new EtchedBorder(), "Print Details")));

		JPanel leftPanel = new JPanel();
		JTextArea cashText = new JTextArea(11,22);

		leftPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		leftPanel.add(cashButton);
		leftPanel.add(cashText);


		JPanel rightPanel = new JPanel();
		JTextArea couponText = new JTextArea(9,22);

		rightPanel.add(couponButton);
		rightPanel.add(couponText);
		bottomPanel.add(leftPanel);
		bottomPanel.add(rightPanel);
		bottomPanel.add(exitButton,BorderLayout.PAGE_END);
		mainPanel.add(bottomPanel,BorderLayout.SOUTH);
		add(mainPanel);
	}
	
	public void setRCM(RCM rcm){
		this.rcm = rcm;
		removeAll();
		display();
	}
}