package ecoreGui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ecore.RCM;
import ecore.RMOS;

public class EmptiedTable extends DisplayTable{
	
	private static final long serialVersionUID = 1L;
	private DateFormat dateFormat = new SimpleDateFormat("mm/dd/yy hh:mm:ss a");

	/**
	 * Default Constructor
	 */
	public EmptiedTable(){
		new EmptiedTable(new RMOS());
	}
	
	/**
	 * Creates a new view for the provided RMOS
	 * @param rmos
	 */
	public EmptiedTable(RMOS rmos){
		super.setRmos(rmos);
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		setBackground(new Color(148, 255, 123));
		
		display();
	}
	
	@Override
	public void display(){
		this.removeAll();
		this.updateUI();
		
		JPanel machineTable = new JPanel(new BorderLayout());
		machineTable.setBackground(new Color(148, 255, 123));
		machineTable.setPreferredSize(new Dimension(500, 200));
		machineTable.setMinimumSize(new Dimension(500, 200));
		machineTable.setMaximumSize(new Dimension(500, 200));
		
		JLabel tableTitle = new JLabel("Maintenance");
		setFont(new Font("Sans Serif", Font.BOLD, 14));
		tableTitle.setFont(new Font("Sans Serif", Font.BOLD, 14));
		tableTitle.setBackground(new Color(148, 255, 123));
		
		String[] columnNames = {"ID", "Time Last Emptied", "Emptied This Week", "Emptied This Month", "Emptied This Year"};
		
		Object[][] rcms = getData();
		
	    DefaultTableModel model = new DefaultTableModel(rcms, columnNames);
		super.setTable(new JTable(model));
		super.getTable().getColumnModel().getColumn(0).setPreferredWidth(30);
		super.getTable().getColumnModel().getColumn(1).setPreferredWidth(150);
		super.getTable().getColumnModel().getColumn(2).setPreferredWidth(120);
		super.getTable().getColumnModel().getColumn(3).setPreferredWidth(120);
		super.getTable().getColumnModel().getColumn(4).setPreferredWidth(120);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(super.getTable().getModel());
		super.getTable().setRowSorter(sorter);
		
		super.getTable().setGridColor(Color.BLACK);
		
		JTableHeader header = super.getTable().getTableHeader();
		header.setBackground(Color.WHITE);
		header.setFont(new Font("Sans Serif", Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane(super.getTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
	private Object[][] getData(){
		HashMap<String, Integer> lastWeek = getRmos().getWeekEmptyData();
		HashMap<String, Integer> lastMonth = getRmos().getMonthEmptyData();
		HashMap<String, Integer> lastYear = getRmos().getYearEmptyData();
		
		int size = super.getRmos().getNumActiveRCMs();
		Object[][] items = new Object[size][5];
		
		int count = 0;
		for (RCM machine: super.getRmos().getRCMGroup()){
			if (lastWeek.containsKey(machine.getID()) || lastMonth.containsKey(machine.getID()) || lastYear.containsKey(machine.getID())){
				items[count][0] = machine.getID();
				items[count][1] = dateFormat.format(machine.getTimeLastEmptied().getTime());
				items[count][2] = lastWeek.get(machine.getID());
				items[count][3] = lastMonth.get(machine.getID());
				items[count][4] = lastYear.get(machine.getID());
				count++;
			}
		}
		return items;
	}		
}
