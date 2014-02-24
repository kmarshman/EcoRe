package rmos;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ItemTypeControl extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ItemTypeControl(RMOS rmos){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 75, 200, 75) );
		
		JButton changePrice = new JButton("Change Price");
		
		add(changePrice, BorderLayout.EAST);
	}

}
