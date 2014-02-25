package rmos;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Table view for item types
 * @author Kelsey
 *
 */
public class ItemTypeTable extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private RMOS rmos;
	private JTable types;
	
	/**
	 * Default Constructor
	 */
	public ItemTypeTable(){
		new ItemTypeTable(new RMOS());
	}
	
	/**
	 * Creates new table view for given RMOS' accepted item types
	 * @param rmos
	 */
	public ItemTypeTable(RMOS rmos){
		this.rmos = rmos;
		
		setLayout(new BorderLayout());
		
		String[] columnNames = {"", "Type", "Price"};
		Object[][] items = getItems();
		
	    DefaultTableModel model = new DefaultTableModel(items, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override 
			public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
	      
		types = new JTable(model);
		types.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JScrollPane scrollPane = new JScrollPane(types, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	/**
	 * Creates 2D Object array from item type fields
	 * @return 2D Object array with item name and price
	 */
	private Object[][] getItems(){
		Object[][] items = new Object[2][3];
		
		int count = 0;
		for (ItemType t: rmos.getItemTypes()){
			items[count][0] = false;
			items[count][1] = t.getName();
			items[count][2] = "$" + t.getPrice();
			count++;
		}
		return items;
	}

	public JTable getTable(){
		return types;
	}

}
