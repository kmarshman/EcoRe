package ecoreGui;

import javax.swing.*;

import view.ActiveMachineTable;
import ecore.RMOS;

/**
 * Main tab for RMOS - contains usage statistic visualization and overview of active machines
 * @author Kelsey
 *
 */
public class DashboardUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static RMOS rmos;
	
	/**
	 * Default Constructor
	 */
	public DashboardUI(){
		new DashboardUI(new RMOS());
	}
	
	/**
	 * Creates new dashboard display for given RMOS
	 * @param rmos
	 */
	public DashboardUI(RMOS rmos){
		DashboardUI.rmos = rmos;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		GraphGenerator graph = new GraphGenerator();
		add(graph);
		
		ActiveMachineTable activeMachines = new ActiveMachineTable(rmos);
		add(activeMachines);
		DashboardUI.rmos.addObserver(activeMachines);
	}	
}
