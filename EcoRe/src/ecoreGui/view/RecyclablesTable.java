package ecoreGui.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
		setBackground(new Color(148, 255, 123));
		
		display();
	}
	
	@Override
	public void display(){
		JLabel tableTitle = new JLabel("Accepted Items");
		setFont(new Font("Sans Serif", Font.BOLD, 14));
		tableTitle.setFont(new Font("Sans Serif", Font.BOLD, 14));
		
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
		super.getTable().getColumnModel().getColumn(1).setPreferredWidth(220);
		super.getTable().getColumnModel().getColumn(2).setPreferredWidth(130);
		super.getTable().getColumnModel().getColumn(3).setPreferredWidth(130);
		super.getTable().setBackground(Color.WHITE);
		
		super.getTable().setGridColor(Color.BLACK);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(super.getTable().getModel());
		super.getTable().setRowSorter(sorter);
		
		JTableHeader header = super.getTable().getTableHeader();
		header.setBackground(Color.WHITE);
		header.setFont(new Font("Sans Serif", Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane(super.getTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		
		add(tableTitle, BorderLayout.NORTH);
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

