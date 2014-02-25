package rmos;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Machine management tab
 * @author Kelsey
 *
 */
public class MachineManagerUI extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

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
	public MachineManagerUI(final RMOS rmos){	
		setLayout(new BorderLayout());
		
		display(rmos);
	}
	
	/**
	 * Adds machine table view and control to the panel
	 * @param rmos
	 */
	private void display(final RMOS rmos){
		removeAll();
		
		MachineTable tableView = new MachineTable(rmos);
		MachineControl control = new MachineControl(rmos, tableView.getTable());
		
		add(tableView, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);	
	}

	@Override
	public void update(Observable o, Object arg) {
		display((RMOS)arg);
		
	}

}