package ecoreGui;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ecore.RMOS;

public class RmosUI extends JPanel {

	private static final long serialVersionUID = 1L;
	JTabbedPane tabbedPane;
	
	public RmosUI(final RMOS rmos){
		CardLayout cards = new CardLayout();
		setLayout(cards);
		
		RmosWelcomeUI loginContainer = new RmosWelcomeUI(rmos, cards, this);
		
		DashboardUI dashboard = new DashboardUI(rmos);
		MachineManagerUI machineManager = new MachineManagerUI(rmos);
		RecyclablesManagerUI recyclablesManager = new RecyclablesManagerUI(rmos);
		
    	tabbedPane = new JTabbedPane();
    	tabbedPane.addTab("Home", dashboard);
    	tabbedPane.addTab("Manage Machines", machineManager);
    	tabbedPane.addTab("Manage Recyclables", recyclablesManager);
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	if(tabbedPane.getSelectedIndex() == 0){
            		rmos.resetItemStatistics();
            	}
            }
        });
    	
    	add(loginContainer, "Login");
    	add(tabbedPane, "Tabs");
	}

}
