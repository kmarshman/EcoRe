package rmos;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

/**
 * Table view for recyclables
 * @author Kelsey
 *
 */
public class RecyclablesTable extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private RMOS rmos;
	private JTable recyclables;

	/**
	 * Default Constructor
	 */
	public RecyclablesTable(){
		new RecyclablesTable(new RMOS());
	}
	
	/**
	 * Creates new table view of recyclables
	 * @param rmos
	 */
	public RecyclablesTable(RMOS rmos){
		this.rmos = rmos;
		
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
	      
		recyclables = new JTable(model);
		recyclables.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JScrollPane scrollPane = new JScrollPane(recyclables, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	/**
	 * Creates 2D array for table data
	 * @return 2D Object array of recyclable item fields
	 */
	private Object[][] getItems(){
		int size = rmos.getAcceptedItems().size();
		Object[][] items = new Object[size][5];
		
		int count = 0;
		for (Item i: rmos.getAcceptedItems()){
			items[count][0] = false;
			items[count][1] = i.getName();
			items[count][2] = i.getType().getName();
			items[count][3] = i.getWeight() + "oz";
			items[count][4] = "$" + i.getValue();
			count++;
		}
		return items;
	}
	
	public JTable getTable(){
		return recyclables;
	}
	
}

