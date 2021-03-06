package ecoreGui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ecore.Item;
import ecore.ItemType;
import ecore.RMOS;
import ecoreGui.view.RecyclablesTable;

/**
 * Control panel for recyclable items
 * @author Kelsey
 *
 */
public class RecyclablesControl extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public RecyclablesControl(){
		new RecyclablesControl(new RMOS(), new RecyclablesTable());
	}
	
	/**
	 * Creates control panel for adding/removing recyclables
	 * @param rmos
	 * @param table
	 */
	public RecyclablesControl(final RMOS rmos, final RecyclablesTable table){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 200, 75) );
		setBackground(new Color(148, 255, 123));
		
		JButton removeRecyclable = new JButton("Remove");
		removeRecyclable.setBackground(Color.WHITE);
		removeRecyclable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ArrayList<String> selectedRows = new ArrayList<String>();
				for(int i = 0; i < table.getTable().getRowCount(); i++) {
				     if((Boolean) table.getTable().getValueAt(i, 0)) {
				         selectedRows.add((String)table.getTable().getValueAt(i, 1));
				     }
				}
				removeItems(selectedRows, rmos);
			}
		});
		
		JButton addNewRecyclable = new JButton("Add");
		addNewRecyclable.setBackground(Color.WHITE);
		addNewRecyclable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField name = new JTextField();
				String[] options = {"Glass", "Aluminum"};
				JComboBox<String> type = new JComboBox<String>(options);
				JTextField weight = new JTextField();
				final JComponent[] inputs = new JComponent[] {
						new JLabel("Name:"),name,
						new JLabel("Type:"),type,
						new JLabel("Weight in oz:"), weight
				};
				JOptionPane.showMessageDialog(null, inputs, "Add New Recyclable", JOptionPane.PLAIN_MESSAGE);
				ItemType newType = null;
				switch(type.getSelectedIndex()){
				case 0: 
					newType = rmos.getGlass();
					break;
				case 1: 
					newType = rmos.getAluminum();
					break;
				}
				try{
					double newWeight = Double.parseDouble(weight.getText().trim());
					String newName = name.getText().trim();
					if(newName.length() > 0){
						rmos.addItem(new Item(newName, newType, newWeight));
					}else{
						JOptionPane.showMessageDialog(null,"Please enter a name for the item.", "Add New Item Failed", JOptionPane.ERROR_MESSAGE);
					}
				}catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Please enter numbers only for the item's weight.", "Add New Item Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		add(removeRecyclable, BorderLayout.WEST);
		add(addNewRecyclable, BorderLayout.EAST);
	}
	
	/**
	 * Removes selected items from the list of accepted recyclables
	 * @param selectedRows
	 * @param rmos
	 */
	private void removeItems(ArrayList<String> selectedRows, RMOS rmos){
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these items?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirmation == JOptionPane.YES_OPTION){
			for (String name: selectedRows){
				rmos.removeItem(name);
			}
		}
	}

}
