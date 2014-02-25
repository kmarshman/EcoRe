package rmos;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Main tab for RMOS - contains usage statistic visualization and overview of active machines
 * @author Kelsey
 *
 */
public class DashboardUI extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates new dashboard display for given RMOS
	 * @param rmos
	 */
	public DashboardUI(final RMOS rmos){		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		display(rmos);
	}
	
	/**
	 * Default Constructor
	 */
	public DashboardUI(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		display(new RMOS());
	}
	
	/**
	 * Splits panel into visualization panel and active machine table
	 * @param rmos
	 */
	private void display(RMOS rmos){
		removeAll();
		
		GraphGenerator graph = new GraphGenerator();
		add(graph);
		
		ActiveMachineTable activeMachines = new ActiveMachineTable(rmos);
		add(activeMachines);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		display((RMOS)arg);
		
	}
	
}
