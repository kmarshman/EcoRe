package ecoreGui;

import java.awt.BorderLayout;

import javax.swing.*;

import ecoreGui.control.MachineControl;
import ecoreGui.view.EmptiedTable;
import ecoreGui.view.MachineTable;
import ecore.RMOS;

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
		
		MachineTable tableView = new MachineTable(rmos);
		MachineManagerUI.rmos.addObserver(tableView);
		MachineControl control = new MachineControl(rmos, tableView);
		
		JPanel tableWrapper = new JPanel();
		tableWrapper.setLayout(new BoxLayout(tableWrapper, BoxLayout.Y_AXIS));
		tableWrapper.add(tableView);
		tableWrapper.add(control);
		
		add(tableWrapper, BorderLayout.CENTER);
		
		EmptiedTable empty = new EmptiedTable(rmos);
		MachineManagerUI.rmos.addObserver(empty);
		add(empty, BorderLayout.SOUTH);
		
		
	}

}