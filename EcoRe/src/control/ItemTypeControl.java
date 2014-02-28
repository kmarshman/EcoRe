package control;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ecore.RMOS;

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
		new ItemTypeControl(new RMOS(), new JTable());
	}
	
	/**
	 * Create new panel for changing the price of recyclable types
	 * @param rmos
	 * @param table
	 */
	public ItemTypeControl(final RMOS rmos, final JTable table){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 200, 75) );
		
		JButton changePrice = new JButton("Change Price");
		changePrice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ArrayList<String> selectedRows = new ArrayList<String>();
				for(int i = 0; i < table.getRowCount(); i++) {
				     if((Boolean) table.getValueAt(i, 0)) {
				         selectedRows.add((String)table.getValueAt(i, 1));
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
					new JLabel("New price:"), price
			};
			JOptionPane.showMessageDialog(null, inputs, ("Change Price of " + type), JOptionPane.PLAIN_MESSAGE);
			switch(type){
			case "Glass":
				rmos.setGlassPrice(Double.parseDouble(price.getText().trim()));
				break;
			case "Aluminum":
				rmos.setAluminumPrice(Double.parseDouble(price.getText().trim()));
				break;
			}
		}
	}

}