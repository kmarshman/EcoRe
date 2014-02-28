package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import ecore.Item;
import ecore.RMOS;

/**
 * Table view for recyclables
 * @author Kelsey
 *
 */
public class RecyclablesTable extends DisplayTable {
	
	private static final long serialVersionUID = 1L;

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
		super.setRmos(rmos);
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 10, 75) );
		
		display();
	}
	
	@Override
	public void display(){
		String[] columnNames = {"", "Item", "Type", "Weight", "Price"};
		Object[][] items = getItems();
		
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
	 * Creates 2D array for table data
	 * @return 2D Object array of recyclable item fields
	 */
	private Object[][] getItems(){
		this.removeAll();
		this.updateUI();
		
		int size = super.getRmos().getAcceptedItems().size();
		Object[][] items = new Object[size][5];
		
		int count = 0;
		for (Item i: super.getRmos().getAcceptedItems()){
			items[count][0] = false;
			items[count][1] = i.getName();
			items[count][2] = i.getType().getName();
			items[count][3] = i.getWeight() + "oz";
			items[count][4] = "$" + i.getValue();
			count++;
		}
		return items;
	}
	
}

