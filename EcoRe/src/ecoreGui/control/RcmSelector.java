package ecoreGui.control;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ecore.RCM;
import ecore.RMOS;
import ecoreGui.CompleteSessionUI;
import ecoreGui.MaintenanceUI;
import ecoreGui.RecycleSessionUI;
import ecoreGui.WelcomeUI;

public class RcmSelector extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> rcmChoice;
	private GridBagConstraints cons;
	private RMOS rmos;
	private CardLayout cards;
	private JPanel cardPanel;
	private WelcomeUI welcome;
	private RecycleSessionUI recycle;
	private CompleteSessionUI complete;
	private MaintenanceUI maintenance;
	private ArrayList<RCM> activeMachines;
	private Image imgBackground = null;
	private final String imgPath = "src/ecore/images/recycle.jpg";

	public RcmSelector(RMOS rmos, CardLayout cards, JPanel cardPanel, WelcomeUI welcome, RecycleSessionUI recycle, CompleteSessionUI complete, MaintenanceUI maintenance){
		setLayout(new GridBagLayout());
		imgBackground = new ImageIcon(imgPath).getImage();

		this.rmos = rmos;
		this.cards = cards;
		this.cardPanel = cardPanel;
		this.welcome = welcome;
		this.recycle = recycle;
		this.complete = complete;
		this.maintenance = maintenance;
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
		
		display();
	}
	
	public void display (){
		cons.gridx = 0;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.PAGE_START;
		JLabel welcomeLabel = new JLabel("<html><font face=\"Garamond\" color=\"228b22\"><left>Welcome to EcoRe!<br>Select a machine to get started.<left></font><html>");
		welcomeLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
		add(welcomeLabel, cons);
		
		cons.gridx = 1;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.LINE_END;
		JPanel selection = new JPanel();
		selection.setBorder(new EmptyBorder(10, 10, 0, 10));
		add(selection, cons);
		
		activeMachines = new ArrayList<RCM>();
		for(RCM m: rmos.getRCMGroup()){
			activeMachines.add(m);
		}
		
		int size = activeMachines.size();
		String[] rcmList = new String[size + 1];
		rcmList[0] = "Select...";
		for(int i = 0; i < size; i++){
			rcmList[i+1] = activeMachines.get(i).getLocation() + ": "+ activeMachines.get(i).getID();
		}
		rcmChoice = new JComboBox<String>(rcmList);
		rcmChoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rcmChoice.getSelectedIndex() != 0){
					RCM rcm = activeMachines.get(rcmChoice.getSelectedIndex()-1);
					welcome.setRCM(rcm);
					recycle.setRCM(rcm);
					complete.setRCM(rcm);
					maintenance.setRCM(rcm);
					rcmChoice.setSelectedIndex(0);
					cards.next(cardPanel);
				}
			}
		});
		selection.add(rcmChoice);
		selection.setBackground(Color.white);
		rcmChoice.setBackground(Color.white);
		rcmChoice.setForeground(new Color(34, 139, 34));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.rmos = (RMOS)arg1;
		removeAll();
		//TBD Shweta -- This is not right. Cause display() may disrupt the current work-flow (e.g. checkout).
		display();
	}
	@Override
    protected void paintComponent(Graphics g) {
		int imgX = imgBackground.getWidth(null)/3;
        int imgY = imgBackground.getHeight(null)/3;
		Graphics2D graphicsObj = (Graphics2D) g.create();

		Paint selectorPaint = new GradientPaint(0, 0, Color.white, 0, 0, Color.white);
		graphicsObj.setPaint(selectorPaint);
		graphicsObj.fillRect(0, 0, getWidth(), getHeight());
		graphicsObj.drawImage(imgBackground, (getWidth() - imgX), (getHeight() - imgY), imgX, imgY, null);

	}
}
