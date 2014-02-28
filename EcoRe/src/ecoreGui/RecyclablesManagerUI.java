package ecoreGui;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import control.ItemTypeControl;
import control.RecyclablesControl;
import view.ItemTypeTable;
import view.RecyclablesTable;
import ecore.RMOS;

/**
 * Recyclables Tab
 * @author Kelsey
 *
 */
public class RecyclablesManagerUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private static RMOS rmos;

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
		RecyclablesManagerUI.rmos = rmos;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		RecyclablesTable tableView = new RecyclablesTable(rmos);
		tableView.setBorder(new EmptyBorder(10, 10, 10, 10));
		RecyclablesManagerUI.rmos.addObserver(tableView);
		RecyclablesControl control = new RecyclablesControl(rmos, tableView.getTable());
		control.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		add(tableView);
		add(control);
		
		add(Box.createRigidArea(new Dimension(10, 30)));
		
		ItemTypeTable itemTableView = new ItemTypeTable(rmos);
		itemTableView.setBorder(new EmptyBorder(10, 10, 10, 10));
		RecyclablesManagerUI.rmos.addObserver(itemTableView);
		ItemTypeControl itemControl = new ItemTypeControl(rmos, itemTableView.getTable());
		itemControl.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		add(itemTableView);
		add(itemControl);
		
		add(Box.createVerticalGlue());
	}
}
