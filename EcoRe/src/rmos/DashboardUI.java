package rmos;

import javax.swing.*;

public class DashboardUI extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public DashboardUI(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		GraphGenerator graph = new GraphGenerator();
		add(graph);
		
		ActiveMachineTable activeMachines = new ActiveMachineTable();
		add(activeMachines);
	}
	
}
