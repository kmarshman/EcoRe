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
		final CardLayout cards = new CardLayout();
		setLayout(cards);
		setBackground(new Color(148, 255, 123));
		
		final RmosWelcomeUI loginContainer = new RmosWelcomeUI(rmos, cards, this);
		
		DashboardUI dashboard = new DashboardUI(rmos);
		MachineManagerUI machineManager = new MachineManagerUI(rmos);
		RecyclablesManagerUI recyclablesManager = new RecyclablesManagerUI(rmos);
		
		UIManager.put("TabbedPane.selected", new javax.swing.plaf.ColorUIResource(new Color(148, 255, 123)));
		UIManager.put("TabbedPane.borderHightlightColor", java.awt.Color.BLACK);
		UIManager.put("TabbedPane.selectHighlight", java.awt.Color.BLACK);
    	UIManager.put("TabbedPane.tabAreaBackground", Color.BLACK);
    	
    	tabbedPane = new JTabbedPane();
    	tabbedPane.setBackground(Color.WHITE);
    	tabbedPane.setForeground(Color.BLACK);
    	tabbedPane.setUI(new BasicTabbedPaneUI() {
    		   @Override
    		   protected void installDefaults() {
    		       super.installDefaults();
    		       highlight = Color.WHITE;
    		       lightHighlight = Color.WHITE;
    		       shadow = new Color(148, 255, 123);
    		       darkShadow = new Color(148, 255, 123);
    		       focus = Color.WHITE;
    		   }
    		});
    	
    	tabbedPane.addTab("Home", dashboard);
    	tabbedPane.addTab("Manage Machines", machineManager);
    	tabbedPane.addTab("Manage Recyclables", recyclablesManager);
    	tabbedPane.addTab("Logout", null);
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	if(tabbedPane.getSelectedIndex() == 0){
            		rmos.setTotalWeights();
            	}
            	if(tabbedPane.getSelectedIndex() == 3){
            		loginContainer.clear();
            		tabbedPane.setSelectedIndex(0);
            		cards.previous(RmosUI.this);
            	}
            }
        });
    	
    	add(loginContainer, "Login");
    	add(tabbedPane, "Tabs");
	}

}
