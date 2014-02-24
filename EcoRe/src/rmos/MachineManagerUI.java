package rmos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import rcm.RCM;

public class MachineManagerUI extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	public MachineManagerUI(final RMOS rmos){	
		setLayout(new BorderLayout());
		
		display(rmos);
	}
	
	private void display(final RMOS rmos){
		removeAll();
		
		MachineTable tableView = new MachineTable(rmos);
		
		JPanel control = new JPanel(new BorderLayout());
		
		String[] options = {"I want to...", "Activate", "Deactivate", "Remove"};
		JComboBox<String> actions = new JComboBox<String>(options);
		
		JButton addNewMachine = new JButton("Add Item");
		addNewMachine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField location = new JTextField();
				final JComponent[] inputs = new JComponent[] {
						new JLabel("Enter the location of the new machine."),location
				};
				JOptionPane.showMessageDialog(null, inputs, "Add New Machine", JOptionPane.PLAIN_MESSAGE);
				rmos.addRCM(new RCM(location.getText().trim()));
			}
		});
		
		control.add(actions, BorderLayout.WEST);
		control.add(addNewMachine, BorderLayout.EAST);
		control.setBorder(new EmptyBorder(10, 10, 200, 10) );
		
		add(tableView, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);	
	}

	@Override
	public void update(Observable o, Object arg) {
		display((RMOS)arg);
		
	}

}
