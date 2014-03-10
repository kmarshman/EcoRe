package ecoreGui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ecore.RMOS;
import ecoreGui.view.ItemTypeTable;

/**
 * Control panel for item types
 * @author Kelsey
 *
 */
public class ItemTypeControl extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor
	 */
	public ItemTypeControl(){
		new ItemTypeControl(new RMOS(), new ItemTypeTable());
	}
	
	/**
	 * Create new panel for changing the price of recyclable types
	 * @param rmos
	 * @param table
	 */
	public ItemTypeControl(final RMOS rmos, final ItemTypeTable table){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 200, 75) );
		setBackground(new Color(148, 255, 123));
		
		JButton changePrice = new JButton("Change Price");
		changePrice.setBackground(Color.WHITE);
		changePrice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ArrayList<String> selectedRows = new ArrayList<String>();
				for(int i = 0; i < table.getTable().getRowCount(); i++) {
				     if((Boolean) table.getTable().getValueAt(i, 0)) {
				         selectedRows.add((String)table.getTable().getValueAt(i, 1));
				     }
				}
				changePrice(selectedRows, rmos);
			}
		});
		
		add(changePrice, BorderLayout.EAST);
	}
	
	/**
	 * Given the selected item types, prompts the user for new prices and updates the RMOS
	 * @param selectedRows
	 * @param rmos
	 */
	private void changePrice(ArrayList<String> selectedRows, RMOS rmos){
		for (String type: selectedRows){
			JTextField price = new JTextField();
			final JComponent[] inputs = new JComponent[] {
					new JLabel("New price for " + type + " items:"), price
			};
			JOptionPane.showMessageDialog(null, inputs, ("Change Price of " + type), JOptionPane.PLAIN_MESSAGE);
			try{
				double newPrice= Double.parseDouble(price.getText().trim());
				switch(type){
				case "Glass":
					rmos.setGlassPrice(newPrice);
					break;
				case "Aluminum":
					rmos.setAluminumPrice(newPrice);
					break;
				}
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null,"Please enter numbers only.", "Change Price Failed", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
