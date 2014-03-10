package ecoreGui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ecore.RCM;
import ecore.RMOS;
import ecore.RCM.Status;
import ecoreGui.view.MachineTable;

/**
 * RCM group control panel
 * @author Kelsey
 *
 */
public class MachineControl extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> actions;
	private Random random = new Random();

	/**
	 * Default Constructor
	 */
	public MachineControl(){
		new MachineControl(new RMOS(), new MachineTable());
	}
	
	/**
	 * Creates new control panel for RCM group
	 * @param rmos
	 * @param table table with user selections
	 */
	public MachineControl(final RMOS rmos, final MachineTable table){
		setLayout(new BorderLayout());
		setBackground(new Color(148, 255, 123));
		
		String[] options = {"I want to...", "Activate", "Deactivate", "Remove"};
		actions = new JComboBox<String>(options);
		actions.setBackground(Color.WHITE);
		actions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ArrayList<String> selectedRows = new ArrayList<String>();
				for(int i = 0; i < table.getTable().getRowCount(); i++) {
				     if((Boolean) table.getTable().getValueAt(i, 0)) {
				         selectedRows.add((String)table.getTable().getValueAt(i, 1));
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
				actions.setSelectedIndex(0);
			}
		});
		
		JButton addNewMachine = new JButton("Add");
		addNewMachine.setBackground(Color.WHITE);
		addNewMachine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField location = new JTextField();
				final JComponent[] inputs = new JComponent[] {
						new JLabel("Enter the location of the new machine."),location
				};
				
				JOptionPane.showMessageDialog(null, inputs, "Add New Machine", JOptionPane.PLAIN_MESSAGE);
				String newLocation = location.getText().trim();
				if(newLocation.length() > 0){
					String id = null;
					while(id == null){
						String temp = String.valueOf(random.nextInt(9999-1000) + 1000);
						boolean taken = false;
						for(RCM rcm : rmos.getRCMGroup()){
							if (rcm.getID().equals(temp)){
								taken = true;
								break;
							}
						}
						if(!taken) id = temp;
					}
					rmos.addRCM(new RCM(newLocation, id));
				}else{
					JOptionPane.showMessageDialog(null,"Please enter a location for the machine.", "Add New Machine Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		add(actions, BorderLayout.WEST);
		add(addNewMachine, BorderLayout.EAST);
		setBorder(new EmptyBorder(10, 10, 200, 10) );
	}
	
	/**
	 * Activates the selected machines
	 * @param rmos
	 * @param selectedRows
	 */
	private void activateMachines(RMOS rmos, ArrayList<String> selectedRows){
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to activate these machines?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirmation == JOptionPane.YES_OPTION){
			for (String id: selectedRows){
				rmos.changeRCMstatus(id, Status.ACTIVE);
			}
		}
	}
	
	/**
	 * Deactivates the selected machines
	 * @param rmos
	 * @param selectedRows
	 */
	private void deactivateMachines(RMOS rmos, ArrayList<String> selectedRows){
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to deactivate these machines?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirmation == JOptionPane.YES_OPTION){
			for (String id: selectedRows){
				rmos.changeRCMstatus(id, Status.INACTIVE);
			}
		}
	}
	
	/**
	 * Removes selected machines from the RCM group
	 * @param rmos
	 * @param selectedRows
	 */
	private void removeMachines(RMOS rmos, ArrayList<String> selectedRows){
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these machines?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirmation == JOptionPane.YES_OPTION){
			for (String id: selectedRows){
				rmos.removeRCM(id);
			}
		}
	}

}
