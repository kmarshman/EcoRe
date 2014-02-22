package rmos;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RecyclablesManagerUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public RecyclablesManagerUI(){
		setLayout(new BorderLayout());
		
		RecyclablesTable tableView = new RecyclablesTable();
		
		JPanel control = new JPanel(new BorderLayout());
		
		String[] options = {"I want to...", "Change Price", "Remove"};
		JComboBox<String> actions = new JComboBox<String>(options);
		
		JButton addNewRecyclable = new JButton("Add Item");
		
		control.add(actions, BorderLayout.WEST);
		control.add(addNewRecyclable, BorderLayout.EAST);
		control.setBorder(new EmptyBorder(10, 75, 200, 75) );
		
		add(tableView, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);		
	}
}
