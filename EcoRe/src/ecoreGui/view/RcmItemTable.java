package ecoreGui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ecore.ItemType;
import ecore.RMOS;

public class RcmItemTable extends DisplayTable {
	
	private static final long serialVersionUID = 1L;

	public RcmItemTable(){
		new RcmItemTable(new RMOS());
	}
	
	public RcmItemTable(RMOS rmos){
		super.setRmos(rmos);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 55));
		
		display();
	}
	
	@Override
	public void display() {
		this.removeAll();
		this.updateUI();
		
		String[] columnNames = {"Type", "Price per lb"};
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
		Object[][] items = new Object[2][2];
		
		int count = 0;
		for (ItemType t: super.getRmos().getItemTypes()){
			items[count][0] = t.getName();
			items[count][1] = "$" + t.getPrice();
			count++;
		}
		return items;
	}

}
