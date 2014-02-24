package rmos;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ActiveMachineTable extends JPanel {

	private static final long serialVersionUID = 1L;

	public ActiveMachineTable(){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		JPanel machineTable = new JPanel(new BorderLayout());
		
		JLabel tableTitle = new JLabel("Active Machines");
		tableTitle.setFont(new Font("Serif", Font.BOLD, 14));
		
		String[] columnNames = {"ID", "Location", "Status", "Notes"};
		
		Object[][] rcms = getMachines();
		
	    DefaultTableModel model = new DefaultTableModel(rcms, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override
	        public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
		JTable machines = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(machines, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		machineTable.add(tableTitle, BorderLayout.NORTH);
		machineTable.add(scrollPane, BorderLayout.CENTER);
		
		add(machineTable, BorderLayout.CENTER);
		
	}
	
	public Object[][] getMachines(){
		Object[] item = {"2132432", "23 Main St.", "Active", "Near Capacity"};
		Object[][]items = {item, item, item, item};
		return items;
	}
		
}
