package rmos;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Table view for item types
 * @author Kelsey
 *
 */
public class ItemTypeTable extends DisplayTable {
	
	private static final long serialVersionUID = 1L;
	
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
		super.setRmos(rmos);
		
		setLayout(new BorderLayout());
		
		display();
	}
	
	@Override
	public void display(){
		this.removeAll();
		this.updateUI();
		
		String[] columnNames = {"", "Type", "Price"};
		Object[][] items = getTypes();
		
	    DefaultTableModel model = new DefaultTableModel(items, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override 
			public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
	      
		super.setTable(new JTable(model));
		super.getTable().getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JScrollPane scrollPane = new JScrollPane(super.getTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	/**
	 * Creates 2D Object array from item type fields
	 * @return 2D Object array with item name and price
	 */
	private Object[][] getTypes(){
		Object[][] items = new Object[2][3];
		
		int count = 0;
		for (ItemType t: super.getRmos().getItemTypes()){
			items[count][0] = false;
			items[count][1] = t.getName();
			items[count][2] = "$" + t.getPrice();
			count++;
		}
		return items;
	}
}
