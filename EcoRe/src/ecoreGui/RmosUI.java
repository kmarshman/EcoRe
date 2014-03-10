package ecoreGui;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import ecore.RMOS;

public class RmosUI extends JPanel {

	private static final long serialVersionUID = 1L;
	JTabbedPane tabbedPane;
	
	public RmosUI(final RMOS rmos){
		CardLayout cards = new CardLayout();
		setLayout(cards);
		setBackground(new Color(148, 255, 123));
		
		RmosWelcomeUI loginContainer = new RmosWelcomeUI(rmos, cards, this);
		
		DashboardUI dashboard = new DashboardUI(rmos);
		MachineManagerUI machineManager = new MachineManagerUI(rmos);
		RecyclablesManagerUI recyclablesManager = new RecyclablesManagerUI(rmos);
		
    	tabbedPane = new JTabbedPane();
    	tabbedPane.setBackground(new Color(148, 255, 123));
    	tabbedPane.setForeground(Color.BLACK);
    	tabbedPane.setUI(new BasicTabbedPaneUI() {
    		   @Override
    		   protected void installDefaults() {
    		       super.installDefaults();
    		       highlight = Color.WHITE;
    		       lightHighlight = Color.WHITE;
    		       shadow = Color.BLACK;
    		       darkShadow = Color.BLACK;
    		       focus = Color.BLACK;
    		   }
    		});
    	UIManager.put("TabbedPane.tabAreaBackground", Color.BLACK);
    	
    	tabbedPane.addTab("Home", dashboard);
    	tabbedPane.addTab("Manage Machines", machineManager);
    	tabbedPane.addTab("Manage Recyclables", recyclablesManager);
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	if(tabbedPane.getSelectedIndex() == 0){
            		rmos.setTotalWeights();
            	}
            }
        });
    	
    	add(loginContainer, "Login");
    	add(tabbedPane, "Tabs");
	}

}
