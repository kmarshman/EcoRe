package rmos;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Recyclables Tab
 * @author Kelsey
 *
 */
public class RecyclablesManagerUI extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public RecyclablesManagerUI(){
		new RecyclablesManagerUI(new RMOS());
	}
	
	/**
	 * Creates new recyclables tab
	 * @param rmos
	 */
	public RecyclablesManagerUI(RMOS rmos){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		display(rmos);
	}
	
	/**
	 * Adds item and item type tables and controls to panel
	 * @param rmos
	 */
	private void display(RMOS rmos){
		removeAll();
		
		RecyclablesTable tableView = new RecyclablesTable(rmos);
		tableView.setBorder(new EmptyBorder(10, 10, 10, 10));
		RecyclablesControl control = new RecyclablesControl(rmos, tableView.getTable());
		control.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		add(tableView);
		add(control);
		
		add(Box.createRigidArea(new Dimension(10, 30)));
		
		ItemTypeTable itemTableView = new ItemTypeTable(rmos);
		itemTableView.setBorder(new EmptyBorder(10, 10, 10, 10));
		ItemTypeControl itemControl = new ItemTypeControl(rmos, itemTableView.getTable());
		itemControl.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		add(itemTableView);
		add(itemControl);
		
		add(Box.createVerticalGlue());
	}

	@Override
	public void update(Observable o, Object arg) {
		display((RMOS)arg);
		
	}
}
