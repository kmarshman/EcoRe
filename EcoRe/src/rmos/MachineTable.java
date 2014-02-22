package rmos;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MachineTable extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public MachineTable(){
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
		JTable machines = new JTable(model);
		machines.getColumnModel().getColumn(0).setPreferredWidth(5);
		machines.getColumnModel().getColumn(2).setPreferredWidth(100);
		machines.getColumnModel().getColumn(6).setPreferredWidth(100);
		machines.getColumnModel().getColumn(9).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(machines, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		machines.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public Object[][] getMachines(){
		Object[] item = {false, "2132432", "23 Main St.", "Active", "256 lb", "123 lb", "Jul 23 02:16:57", "$564", "89", "Near Capacity"};
		Object[][]items = {item, item, item, item};
		return items;
	}
}
