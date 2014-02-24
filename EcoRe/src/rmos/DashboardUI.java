package rmos;

import javax.swing.*;

public class DashboardUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public DashboardUI(RMOS rmos){		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		GraphGenerator graph = new GraphGenerator();
		add(graph);
		
		ActiveMachineTable activeMachines = new ActiveMachineTable(rmos);
		add(activeMachines);
	}
	
}
