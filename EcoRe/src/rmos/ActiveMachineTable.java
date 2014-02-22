package rmos;

import java.awt.BorderLayout;

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
		machines.getColumnModel().getColumn(1).setPreferredWidth(100);
		machines.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(machines, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		machines.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public Object[][] getMachines(){
		Object[] item = {"2132432", "23 Main St.", "Active", "Near Capacity"};
		Object[][]items = {item, item, item, item};
		return items;
	}
		
}
