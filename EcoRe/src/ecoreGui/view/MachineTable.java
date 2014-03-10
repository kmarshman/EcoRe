package ecoreGui.view;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ecore.RCM;
import ecore.RMOS;

/**
 * Table view for RCM group
 * @author Kelsey
 *
 */
public class MachineTable extends DisplayTable {
	
	private static final long serialVersionUID = 1L;
	private DateFormat dateFormat = new SimpleDateFormat("mm/dd/yy hh:mm:ss a");

	/**
	 * Default Constructor
	 */
	public MachineTable(){
		new MachineTable(new RMOS());
	}
	
	/**
	 * Creates new table for RCM group
	 * @param rmos
	 */
	public MachineTable(RMOS rmos){
		super.setRmos(rmos);
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		display();
	}
	
	@Override
	public void display(){
		this.removeAll();
		this.updateUI();
		
		String[] columnNames = {"", "ID", "Location", "Status", "Capacity", "Weight", "Last Emptied", "Cash", "Paper", "Notes"};
		
		Object[][] rcms = getMachines();
		
	    DefaultTableModel model = new DefaultTableModel(rcms, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
		super.setTable(new JTable(model));
		super.getTable().getColumnModel().getColumn(0).setPreferredWidth(10);
		super.getTable().getColumnModel().getColumn(2).setPreferredWidth(100);
		super.getTable().getColumnModel().getColumn(6).setPreferredWidth(150);
		super.getTable().getColumnModel().getColumn(9).setPreferredWidth(100);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(super.getTable().getModel());
		super.getTable().setRowSorter(sorter);
		
		JScrollPane scrollPane = new JScrollPane(super.getTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		super.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	/**
	 * Creates 2D Object array for table data
	 * @return 2D Object array filled with RCM fields
	 */
	private Object[][] getMachines(){
		int size = super.getRmos().getRCMGroup().size();
		Object[][] items = new Object[size][10];
		
		int count = 0;
		for (RCM machine: super.getRmos().getRCMGroup()){
			items[count][0] = false;
			items[count][1] = machine.getID();
			items[count][2] = machine.getLocation();
			items[count][3] = machine.getStatus();
			items[count][4] = machine.getCapacity();
			items[count][5] = machine.getWeight();
			items[count][6] = dateFormat.format(machine.getTimeLastEmptied().getTime());
			items[count][7] = machine.getCash();
			items[count][8] = machine.getCouponPaper();			
			items[count][9] = machine.getState();
			
			count++;
		}
		return items;
	}
}
