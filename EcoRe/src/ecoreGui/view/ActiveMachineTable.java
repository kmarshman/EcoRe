package ecoreGui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ecore.RCM;
import ecore.RMOS;
import ecore.RCM.Status;

/**
 * Table view for active machines
 * @author Kelsey
 *
 */
public class ActiveMachineTable extends DisplayTable{

	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public ActiveMachineTable(){
		new ActiveMachineTable(new RMOS());
	}
	
	/**
	 * Creates a new view for the provided RMOS
	 * @param rmos
	 */
	public ActiveMachineTable(RMOS rmos){
		super.setRmos(rmos);
		
		setLayout(new BorderLayout());
		setBackground(new Color(148, 255, 123));
		
		display();
	}
	
	@Override
	public void display(){
		this.removeAll();
		this.updateUI();
		
		JPanel machineTable = new JPanel(new BorderLayout());
		machineTable.setBackground(new Color(148, 255, 123));
		
		JLabel tableTitle = new JLabel("Active Machines");
		setFont(new Font("Sans Serif", Font.BOLD, 14));
		tableTitle.setFont(new Font("Sans Serif", Font.BOLD, 14));
		tableTitle.setBackground(new Color(148, 255, 123));
		
		String[] columnNames = {"ID", "Location", "State"};
		
		Object[][] rcms = getMachines();
		
	    DefaultTableModel model = new DefaultTableModel(rcms, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override
	        public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
		super.setTable(new JTable(model));
		super.getTable().getColumnModel().getColumn(0).setPreferredWidth(5);
		super.getTable().getColumnModel().getColumn(1).setPreferredWidth(200);
		super.getTable().getColumnModel().getColumn(0).setPreferredWidth(30);
		super.getTable().getColumnModel().getColumn(0).setPreferredWidth(30);
		
		super.getTable().setGridColor(Color.BLACK);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(super.getTable().getModel());
		super.getTable().setRowSorter(sorter);
		
		JTableHeader header = super.getTable().getTableHeader();
		header.setBackground(Color.WHITE);
		header.setFont(new Font("Sans Serif", Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane(super.getTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(new Color(148, 255, 123));
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		
		machineTable.add(tableTitle, BorderLayout.NORTH);
		machineTable.add(scrollPane, BorderLayout.CENTER);
		
		add(machineTable, BorderLayout.CENTER);
		
	}
	
	/**
	 * Creates 2D Object array with RCM data
	 * @param rmos
	 * @return 2D Object array
	 */
	private Object[][] getMachines(){
		int size = super.getRmos().getNumActiveRCMs();
		Object[][] items = new Object[size][3];
		
		int count = 0;
		for (RCM machine: super.getRmos().getRCMGroup()){
			if (machine.getStatus() == Status.ACTIVE){
				items[count][0] = machine.getID();
				items[count][1] = machine.getLocation();
				items[count][2] = machine.getState();
				count++;
			}
		}
		return items;
	}		
}
