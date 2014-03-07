//File to handle the activities of a maintanence worker
package ecoreGui.control;

import java.awt.CardLayout;

import javax.swing.JPanel;

import ecore.RCM;
import ecore.RMOS;

public class MaintenanceSession extends JPanel {

	private static final long serialVersionUID = 1L;
	private static RCM rcm;
	
	public MaintenanceSession(){
		new MaintenanceSession(new RCM(), null, null);
	}
	
	
	public MaintenanceSession(RCM rcm,final CardLayout cards, final JPanel cardPanel)
	{
		MaintenanceSession.rcm = rcm;
	}

}
