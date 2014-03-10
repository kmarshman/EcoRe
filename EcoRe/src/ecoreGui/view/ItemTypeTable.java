package ecoreGui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ecore.ItemType;
import ecore.RMOS;

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
		setPreferredSize(new Dimension(200, 93));
		setBackground(new Color(148, 255, 123));
		
		display();
	}
	
	@Override
	public void display(){
		this.removeAll();
		this.updateUI();
		
		JLabel tableTitle = new JLabel("Recyclable Types");
		setFont(new Font("Sans Serif", Font.BOLD, 14));
		tableTitle.setFont(new Font("Sans Serif", Font.BOLD, 14));
		
		String[] columnNames = {"", "Type", "Price per lb"};
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
		
		super.getTable().setGridColor(Color.BLACK);
		
		JTableHeader header = super.getTable().getTableHeader();
		header.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane(super.getTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		
		add(tableTitle, BorderLayout.NORTH);
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
