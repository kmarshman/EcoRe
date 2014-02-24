package rmos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RecyclablesControl extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public RecyclablesControl(final RMOS rmos){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 200, 75) );
		
		JButton removeRecyclable = new JButton("Remove Item");
		JButton addNewRecyclable = new JButton("Add Item");
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

}
