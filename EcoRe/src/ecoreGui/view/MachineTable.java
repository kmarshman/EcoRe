package ecoreGui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ecore.RCM;
import ecore.RMOS;

/**
 * Table view for RCM group
 * @author Kelsey
 *
 */
public class MachineTable extends DisplayTable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public MachineTable(){
		new MachineTable(new RMOS());
	}
	
	/**
	 * Creates new table for RCM group
	 * @param rmos
	 */
	public MachineTable(RMOS rmos){
		super.setRmos(rmos);
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		setBackground(new Color(148, 255, 123));
		
		setPreferredSize(new Dimension(700, 300));
		setMinimumSize(new Dimension(700, 300));
		setMaximumSize(new Dimension(700, 300));
		
		display();
	}
	
	@Override
	public void display(){
		this.removeAll();
		this.updateUI();
		
		JLabel tableTitle = new JLabel("Complete Machine List");
		setFont(new Font("Sans Serif", Font.BOLD, 14));
		tableTitle.setFont(new Font("Sans Serif", Font.BOLD, 14));
		
		String[] columnNames = {"", "ID", "Location", "Status", "Capacity", "Weight","Cash", "Paper", "Notes"};
		
		Object[][] rcms = getMachines();
		
	    DefaultTableModel model = new DefaultTableModel(rcms, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
		super.setTable(new JTable(model));
		super.getTable().getColumnModel().getColumn(0).setPreferredWidth(15);
		super.getTable().getColumnModel().getColumn(1).setPreferredWidth(50);
		super.getTable().getColumnModel().getColumn(2).setPreferredWidth(200);
		super.getTable().getColumnModel().getColumn(5).setPreferredWidth(50);
		super.getTable().getColumnModel().getColumn(6).setPreferredWidth(50);
		super.getTable().getColumnModel().getColumn(7).setPreferredWidth(50);
		super.getTable().getColumnModel().getColumn(8).setPreferredWidth(100);
		
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
	 * Creates 2D Object array for table data
	 * @return 2D Object array filled with RCM fields
	 */
	private Object[][] getMachines(){
		int size = super.getRmos().getRCMGroup().size();
		Object[][] items = new Object[size][9];
		
		int count = 0;
		for (RCM machine: super.getRmos().getRCMGroup()){
			items[count][0] = false;
			items[count][1] = machine.getID();
			items[count][2] = machine.getLocation();
			items[count][3] = machine.getStatus();
			items[count][4] = machine.getCapacity();
			items[count][5] = machine.getWeight();
			items[count][6] = machine.getCash();
			items[count][7] = machine.getCouponPaper();			
			items[count][8] = machine.getState();
			
			count++;
		}
		return items;
	}
}
