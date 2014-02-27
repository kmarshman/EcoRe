package rmos;

import java.awt.BorderLayout;

import javax.swing.*;

/**
 * Machine management tab
 * @author Kelsey
 *
 */
public class MachineManagerUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private static RMOS rmos;

	/**
	 * Default Constructor
	 */
	public MachineManagerUI(){
		new MachineManagerUI(new RMOS());
	}
	
	/**
	 * Creates new machine management tab for given RMOS
	 * @param rmos
	 */
	public MachineManagerUI(RMOS rmos){	
		MachineManagerUI.rmos = rmos;
		setLayout(new BorderLayout());
		display();
	}
	
	/**
	 * Adds machine table view and control to the panel
	 * @param rmos
	 */
	private void display(){
		removeAll();
		
		MachineTable tableView = new MachineTable(rmos);
		MachineManagerUI.rmos.addObserver(tableView);
		MachineControl control = new MachineControl(rmos, tableView.getTable());
		
		add(tableView, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);	
	}

}