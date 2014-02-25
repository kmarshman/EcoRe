package rmos;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rcm.RCM;

public class MachineTable extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private RMOS rmos;
	private JTable machines;

	public MachineTable(RMOS rmos){
		this.rmos = rmos;
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		String[] columnNames = {"", "ID", "Location", "Status", "Capacity", "Weight", "Last Emptied", "Cash", "Paper", "Notes"};
		
		Object[][] rcms = getMachines();
		
	    DefaultTableModel model = new DefaultTableModel(rcms, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
		machines = new JTable(model);
		machines.getColumnModel().getColumn(0).setPreferredWidth(5);
		machines.getColumnModel().getColumn(2).setPreferredWidth(100);
		machines.getColumnModel().getColumn(6).setPreferredWidth(100);
		machines.getColumnModel().getColumn(9).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(machines, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		machines.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	private Object[][] getMachines(){
		int size = rmos.getRCMGroup().size();
		Object[][] items = new Object[size][10];
		
		int count = 0;
		for (RCM machine: rmos.getRCMGroup()){
			items[count][0] = false;
			items[count][1] = machine.getID();
			items[count][2] = machine.getLocation();
			items[count][3] = machine.getStatus();
			items[count][4] = machine.getCapacity();
			items[count][5] = machine.getWeight();
			items[count][6] = machine.getTimeLastEmptied();
			items[count][7] = machine.getCash();
			items[count][8] = machine.getCouponPaper();			
			items[count][9] = machine.getState();
			
			count++;
		}
		return items;
	}
	
	public JTable getTable(){
		return machines;
	}
}
