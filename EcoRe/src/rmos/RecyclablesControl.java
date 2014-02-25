package rmos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RecyclablesControl extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public RecyclablesControl(final RMOS rmos, final JTable table){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 200, 75) );
		
		JButton removeRecyclable = new JButton("Remove");
		removeRecyclable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ArrayList<String> selectedRows = new ArrayList<String>();
				for(int i = 0; i < table.getRowCount(); i++) {
				     if((Boolean) table.getValueAt(i, 0)) {
				         selectedRows.add((String)table.getValueAt(i, 1));
				     }
				}
				removeItems(selectedRows, rmos);
			}
		});
		
		JButton addNewRecyclable = new JButton("Add");
		addNewRecyclable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField name = new JTextField();
				String[] options = {"Glass", "Aluminum"};
				JComboBox<String> type = new JComboBox<String>(options);
				JTextField weight = new JTextField();
				final JComponent[] inputs = new JComponent[] {
						new JLabel("Name:"),name,
						new JLabel("Type:"),type,
						new JLabel("Weight:"), weight
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
				rmos.addItem(new Item(name.getText().trim(), newType, Double.parseDouble(weight.getText().trim())));
			}
		});
		
		add(removeRecyclable, BorderLayout.WEST);
		add(addNewRecyclable, BorderLayout.EAST);
	}
	
	private void removeItems(ArrayList<String> selectedRows, RMOS rmos){
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these items?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirmation == JOptionPane.YES_OPTION){
			for (String name: selectedRows){
				rmos.removeItem(name);
			}
		}
	}

}
