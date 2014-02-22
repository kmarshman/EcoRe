package rmos;

import java.awt.*;
import javax.swing.*;

public class DashboardUI extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public DashboardUI(){
		setLayout(new GridLayout(2,1));
		
		GraphGenerator graph = new GraphGenerator();
		add(graph);
		
		ActiveMachineTable activeMachines = new ActiveMachineTable();
		add(activeMachines);
	}
	
}
