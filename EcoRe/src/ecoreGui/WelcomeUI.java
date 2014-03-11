//The main screen of the RCM part ...
package ecoreGui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ecore.RCM;
import ecore.RCM.State;
import ecore.RMOS;
import ecoreGui.view.RcmItemTable;

public class WelcomeUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private CardLayout cards;
	private JPanel cardPanel;
	private RMOS rmos;
	private RCM rcm;
	private GridBagConstraints cons;

	public WelcomeUI(RMOS rmos, RCM rcm, final CardLayout cards, final JPanel cardPanel)
	{
		this.rmos = rmos;
		this.rcm = rcm;
		this.cards = cards;
		this.cardPanel = cardPanel;
		
		setLayout(new GridBagLayout());
		setBackground(new Color(255,255,255));
		setFont(new Font("Sans Serif", Font.BOLD, 14));
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
	}
	
	private void display()
	{
		cons.gridx = 2;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.PAGE_START;
		cons.gridwidth = 3;
		cons.insets = new Insets(0,0,100,0);
		JLabel welcomeLabel = new JLabel("<html><font face=\"Garamond\" color=\"228b22\"><center>Welcome to EcoRe <br> Recycle your aluminium , glass and plastic here</center></font></html>");;
		welcomeLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
		welcomeLabel.setAlignmentX(SwingConstants.CENTER);
		add(welcomeLabel, cons);

		cons.gridx = 1;
		cons.gridy = 3;
		cons.anchor = GridBagConstraints.CENTER;
		cons.gridwidth = 3;
		cons.insets = new Insets(0,50,0,0);
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setBackground(Color.WHITE);
		JLabel tableLabel = new JLabel("Get Cash for your Trash");
		tableLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
		RcmItemTable table = new RcmItemTable(rmos);
		rmos.addObserver(table);
		tablePanel.add(tableLabel);
		tablePanel.add(table);
		add(tablePanel, cons);
		

		cons.gridx = 3;
		cons.gridy = 3;
		cons.anchor = GridBagConstraints.CENTER;
		cons.ipady = 40;
		cons.ipadx = 40;
		cons.insets = new Insets(0,250,0,10);
		JButton start = new JButton("Start");
		if(rcm.getState() == State.NONOPERATIONAL){
			start.setText("Out of Order");
			start.setForeground(Color.RED);
			start.setEnabled(false);
		}
		start.setSize(new Dimension(100, 100));
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cards.next(cardPanel);
			}
		});
		add(start, cons);

		cons.gridx = 4;
		cons.gridy = 5;
		cons.anchor = GridBagConstraints.LAST_LINE_END;
		cons.ipady = 0;
		cons.ipadx = 0;
		cons.insets = new Insets(400,0,0,0);
		JButton maintenance = new JButton("Maintanence");
		maintenance.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField key = new JTextField();
				final JComponent[] inputs = new JComponent[] {
						new JLabel("Enter 4 Digit Access Key:"), key
				};
				
				JOptionPane.showMessageDialog(null, inputs, "Enter Access Key", JOptionPane.PLAIN_MESSAGE);
				String entered = key.getText().trim();
				if(rmos.getRCMGroup().get(0).aunthenticateWorker(entered)){
					cards.next(cardPanel);
					cards.next(cardPanel);
					cards.next(cardPanel);
				}else{
					JOptionPane.showMessageDialog(null,"Incorrect Key", "Add New Machine Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(maintenance, cons);
	}
	
	public void setRCM(RCM rcm){
		this.rcm = rcm;
		removeAll();
		display();
	}
}
