package rmos;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ItemTypeTable extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private RMOS rmos;
	
	public ItemTypeTable(RMOS rmos){
		this.rmos = rmos;
		
		setLayout(new BorderLayout());
		
		String[] columnNames = {"Type", "Price"};
		Object[][] items = getItems();
		
	    DefaultTableModel model = new DefaultTableModel(items, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override 
			public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
	      
		JTable types = new JTable(model);
		types.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JScrollPane scrollPane = new JScrollPane(types, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public Object[][] getItems(){
		Object[][] items = new Object[2][2];
		
		int count = 0;
		for (ItemType t: rmos.getItemTypes()){
			items[count][0] = t.getName();
			items[count][1] = "$" + t.getPrice();
			count++;
		}
		return items;
	}

}