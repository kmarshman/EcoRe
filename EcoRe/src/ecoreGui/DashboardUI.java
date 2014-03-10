package ecoreGui;


import java.awt.Color;

import javax.swing.*;

import ecoreGui.control.GraphGenerator;
import ecoreGui.view.ActiveMachineTable;
import ecoreGui.view.ItemChart;
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
		setBackground(new Color(148, 255, 123));
		
		GraphGenerator graph = new GraphGenerator(rmos);
		add(graph);
		
		JPanel wrapper = new JPanel();
		wrapper.setBackground(new Color(148, 255, 123));
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		
		ActiveMachineTable activeMachines = new ActiveMachineTable(rmos);
		wrapper.add(activeMachines);
		DashboardUI.rmos.addObserver(activeMachines);
		
		ItemChart itemChart = new ItemChart(rmos);
		wrapper.add(itemChart);
		DashboardUI.rmos.addObserver(itemChart);
		
		add(wrapper);
	}	
}
