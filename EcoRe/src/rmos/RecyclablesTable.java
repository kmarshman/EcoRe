package rmos;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class RecyclablesTable extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public RecyclablesTable(){
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 10, 75) );
		
		String[] columnNames = {"", "Item", "Type", "Weight", "Price"};
		Object[][] items = getItems();
		
	    DefaultTableModel model = new DefaultTableModel(items, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override 
			public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
	      
		JTable recyclables = new JTable(model);
		recyclables.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JScrollPane scrollPane = new JScrollPane(recyclables, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		recyclables.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public Object[][] getItems(){
		Object[] item = {false, "20oz Bottle", "Glass", ".5oz", "$0.05"};
		Object[][]items = {item, item, item, item};
		return items;
	}
	
}

