package rmos;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class DashboardUI extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	public DashboardUI(final RMOS rmos){		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		display(rmos);
	}
	
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
