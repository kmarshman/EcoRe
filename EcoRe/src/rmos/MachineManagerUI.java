package rmos;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MachineManagerUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public MachineManagerUI(){
		setLayout(new BorderLayout());
		
		MachineTable tableView = new MachineTable();
		
		JPanel control = new JPanel(new BorderLayout());
		
		String[] options = {"I want to...", "Activate", "Deactivate", "Remove"};
		JComboBox<String> actions = new JComboBox<String>(options);
		
		JButton addNewRecyclable = new JButton("Add Item");
		
		control.add(actions, BorderLayout.WEST);
		control.add(addNewRecyclable, BorderLayout.EAST);
		control.setBorder(new EmptyBorder(10, 10, 200, 10) );
		
		add(tableView, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);	
	}

}
