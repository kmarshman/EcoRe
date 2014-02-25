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

import rcm.RCM;
import rcm.RCM.Status;

public class MachineControl extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> actions;

	public MachineControl(final RMOS rmos, final JTable table){
		setLayout(new BorderLayout());
		
		String[] options = {"I want to...", "Activate", "Deactivate", "Remove"};
		actions = new JComboBox<String>(options);
		actions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ArrayList<String> selectedRows = new ArrayList<String>();
				for(int i = 0; i < table.getRowCount(); i++) {
				     if((Boolean) table.getValueAt(i, 0)) {
				         selectedRows.add((String)table.getValueAt(i, 1));
				     }
				}
				switch(actions.getSelectedIndex()){
				case 0:
					break;
				case 1:
					activateMachines(rmos, selectedRows);
					break;
				case 2:
					deactivateMachines(rmos, selectedRows);
					break;
				case 3:
					removeMachines(rmos, selectedRows);
					break;
				}
			}
		});
		
		JButton addNewMachine = new JButton("Add");
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
		
		add(actions, BorderLayout.WEST);
		add(addNewMachine, BorderLayout.EAST);
		setBorder(new EmptyBorder(10, 10, 200, 10) );
	}
	
	private void activateMachines(RMOS rmos, ArrayList<String> selectedRows){
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to activate these machines?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirmation == JOptionPane.YES_OPTION){
			for (String id: selectedRows){
				rmos.changeRCMstatus(id, Status.ACTIVE);
			}
		}
	}
	
	private void deactivateMachines(RMOS rmos, ArrayList<String> selectedRows){
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to deactivate these machines?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirmation == JOptionPane.YES_OPTION){
			for (String id: selectedRows){
				rmos.changeRCMstatus(id, Status.INACTIVE);
			}
		}
	}
	
	private void removeMachines(RMOS rmos, ArrayList<String> selectedRows){
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these machines?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirmation == JOptionPane.YES_OPTION){
			for (String id: selectedRows){
				rmos.removeRCM(id);
			}
		}
	}

}
