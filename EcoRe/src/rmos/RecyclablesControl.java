package rmos;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RecyclablesControl extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public RecyclablesControl(RMOS rmos){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 200, 75) );
		
		JButton removeRecyclable = new JButton("Remove Item");
		JButton addNewRecyclable = new JButton("Add Item");
		
		add(removeRecyclable, BorderLayout.WEST);
		add(addNewRecyclable, BorderLayout.EAST);
	}

}
