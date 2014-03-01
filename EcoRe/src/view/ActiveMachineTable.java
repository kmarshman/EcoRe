package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		display();
	}
	
	@Override
	public void display(){
		this.removeAll();
		this.updateUI();
		
		JPanel machineTable = new JPanel(new BorderLayout());
		
		JLabel tableTitle = new JLabel("Active Machines");
		setFont(new Font("SansSerif", Font.BOLD, 14));
		tableTitle.setFont(new Font("Serif", Font.BOLD, 14));
		
		String[] columnNames = {"ID", "Location", "Status", "Notes"};
		
		Object[][] rcms = getMachines();
		
	    DefaultTableModel model = new DefaultTableModel(rcms, columnNames) {

			private static final long serialVersionUID = 1L;

			@Override
	        public Class<?> getColumnClass(int column) {
	          return getValueAt(0, column).getClass();
	        }
	      };
		super.setTable(new JTable(model));
		JScrollPane scrollPane = new JScrollPane(super.getTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
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
		Object[][] items = new Object[size][4];
		
		int count = 0;
		for (RCM machine: super.getRmos().getRCMGroup()){
			if (machine.getStatus() == Status.ACTIVE){
				items[count][0] = machine.getID();
				items[count][1] = machine.getLocation();
				items[count][2] = machine.getStatus();
				items[count][3] = machine.getState();
				count++;
			}
		}
		return items;
	}		
}
