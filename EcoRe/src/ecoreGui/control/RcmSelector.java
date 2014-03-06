package ecoreGui.control;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ecore.RCM;
import ecore.RMOS;
import ecoreGui.CompleteSessionUI;
import ecoreGui.RecycleSessionUI;
import ecoreGui.WelcomeUI;

public class RcmSelector extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> rcmChoice;
	private GridBagConstraints cons;
	private RMOS rmos;
	private CardLayout cards;
	private JPanel cardPanel;
	private WelcomeUI welcome;
	private RecycleSessionUI recycle;
	private CompleteSessionUI complete;

	public RcmSelector(RMOS rmos, CardLayout cards, JPanel cardPanel, WelcomeUI welcome, RecycleSessionUI recycle, CompleteSessionUI complete){
		setLayout(new GridBagLayout());
		this.rmos = rmos;
		this.cards = cards;
		this.cardPanel = cardPanel;
		this.welcome = welcome;
		this.recycle = recycle;
		this.complete = complete;
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
		
		display();
	}
	
	public void display(){
		cons.gridx = 2;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.PAGE_START;
		JLabel welcomeLabel = new JLabel("<html><center>Welcome to EcoRe!<br>Select a machine to get started.<center><html>");
		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 14));
		add(welcomeLabel, cons);
		
		cons.gridx = 2;
		cons.gridy = 1;
		cons.anchor = GridBagConstraints.LINE_END;
		JPanel selection = new JPanel();
		selection.setBorder(new EmptyBorder(10, 10, 0, 10));
		add(selection, cons);
		
		int size = rmos.getRCMGroup().size();
		String[] rcmList = new String[size + 1];
		rcmList[0] = "Machine...";
		for(int i = 0; i < size; i++){
			rcmList[i+1] = rmos.getRCMGroup().get(i).getLocation() + ": "+ rmos.getRCMGroup().get(i).getID();
		}
		rcmChoice = new JComboBox<String>(rcmList);
		rcmChoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rcmChoice.getSelectedIndex() != 0){
					RCM rcm = rmos.getRCMGroup().get(rcmChoice.getSelectedIndex()-1);
					welcome.setRCM(rcm);
					recycle.setRCM(rcm);
					complete.setRCM(rcm);
					rcmChoice.setSelectedIndex(0);
					cards.next(cardPanel);
				}
			}
		});
		selection.add(rcmChoice);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.rmos = (RMOS)arg1;
		removeAll();
		display();
		
	}
}
