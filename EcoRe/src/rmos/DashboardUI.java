package rmos;

import rcm.*;

import java.awt.*;

import javax.swing.*;

public class DashboardUI extends JPanel {
	
	public DashboardUI(){
		setLayout(new GridLayout(2,1));
		
		GraphGenerator graph = new GraphGenerator();
		add(graph);
		
		ActiveMachineTable activeMachines = new ActiveMachineTable();
		add(activeMachines);
	}
	
}
