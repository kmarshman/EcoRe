package ecoreGui.control;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ecore.RCM;
import ecore.RMOS;
import ecoreGui.CompleteSessionUI;
import ecoreGui.RecycleSessionUI;
import ecoreGui.WelcomeUI;

public class RcmSelector extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> rcmChoice;

	public RcmSelector(final RMOS rmos, final CardLayout cards, final JPanel cardPanel, final WelcomeUI welcome, final RecycleSessionUI recycle, final CompleteSessionUI complete){
		setLayout(new BorderLayout());
		
		JPanel selection = new JPanel();
		
		JLabel comboLabel = new JLabel("Select a recycling machine:");
		
		int size = rmos.getRCMGroup().size();
		String[] rcmList = new String[size + 1];
		rcmList[0] = "RCM";
		for(int i = 0; i < size; i++){
			rcmList[i+1] = rmos.getRCMGroup().get(i).getID();
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
		
		selection.add(comboLabel);
		selection.add(rcmChoice);
		
		add(selection, BorderLayout.CENTER);
	}
}
