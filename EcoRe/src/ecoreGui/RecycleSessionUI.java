package ecoreGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ecore.Item;
import ecore.ItemType;
import ecore.RCM;
import ecore.RMOS;

/**
 * Represents recyclable item
 * @author Shweta
 *
 */

public class RecycleSessionUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private RCM rcm;
	private RMOS rmos;
	private CardLayout cards;
	private JPanel cardPanel;
	private double value, aluminumWeight, glassWeight;
	private ButtonGroup glassGroup, aluminumGroup;

	public RecycleSessionUI() 
	{
		new RecycleSessionUI(new RMOS(), new RCM(), null, null);
	}

	public RecycleSessionUI(RMOS rmos, RCM rcm,final CardLayout cards, final JPanel cardPanel) //throws TooManyListenersException 
	{
		this.rmos = rmos;
		this.rcm = rcm;
		this.cards = cards;
		this.cardPanel = cardPanel;
		
		value = 0;
		aluminumWeight = 0;
		glassWeight = 0;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	private void display()
	{	
		JLabel title = new JLabel("<html><center>EcoRe<br>Select an Item to Recycle</center></html>");
		title.setFont(new Font("Sans Serif", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setHorizontalTextPosition(SwingConstants.CENTER);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel valueLabel = new JLabel("<html><center>You have deposited " + (aluminumWeight + glassWeight) + " lbs for a total of $" + value + "</center></html>");
		valueLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
		valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel aluminumLabel = new JLabel("<html><center>You have deposited " + aluminumWeight + " lbs of aluminum" + "</center></html>");
		aluminumLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
		aluminumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel glassLabel = new JLabel("<html><center>You have deposited " + glassWeight + " lbs of glass" + "</center></html>");
		glassLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
		glassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setAlignmentX(CENTER_ALIGNMENT);
		statusPanel.setAlignmentY(TOP_ALIGNMENT);
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
		statusPanel.add(valueLabel);
		statusPanel.add(aluminumLabel);
		statusPanel.add(glassLabel);
		statusPanel.add(Box.createVerticalGlue());	
		
		JPanel glassItems = new JPanel();
		glassItems.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Glass"));
		glassItems.setPreferredSize(new Dimension(450, 150));
		glassItems.setMaximumSize(new Dimension(450, 150));
		glassItems.setMinimumSize(new Dimension(450, 150));
		
		glassGroup = new ButtonGroup();
		for(Item i: rmos.getAcceptedItems()){
			if(i.getType().getName().equals("Glass")){
				JRadioButton button = new JRadioButton(i.getName());
				glassGroup.add(button);
				glassItems.add(button);
			}
		}
		
		JPanel aluminumItems = new JPanel();
		aluminumItems.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Aluminum"));
		aluminumItems.setPreferredSize(new Dimension(450, 150));
		aluminumItems.setMaximumSize(new Dimension(450, 150));
		aluminumItems.setMinimumSize(new Dimension(450, 150));
		
		aluminumGroup = new ButtonGroup();
		for(Item i: rmos.getAcceptedItems()){
			if(i.getType().getName().equals("Aluminum")){
				JRadioButton button = new JRadioButton(i.getName());
				button.setActionCommand(i.getName());
				aluminumGroup.add(button);
				aluminumItems.add(button);
			}
		}
		
		
		JPanel items = new JPanel();
		items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
		items.add(glassItems);
		items.add(aluminumItems);
		items.add(Box.createVerticalGlue());
		
		JButton recycle = new JButton("Recycle");
		recycle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String glass = null;
				String aluminum = null;
				try{
					glass = glassGroup.getSelection().getActionCommand();
				}catch(Exception e1){
					
				}
				try{
					aluminum = aluminumGroup.getSelection().getActionCommand();
				}catch(Exception e2){
					
				}
				Item entered = null;
				for(Item i : rmos.getAcceptedItems()){
					if(glass != null && i.getName().equals(glass)){
						entered = i;
						glassWeight += i.getWeight();
					}else if (aluminum != null && i.getName().equals(aluminum)){
						entered = i;
						aluminumWeight += i.getWeight();
					}
				}
				if(entered == null){
					JOptionPane.showMessageDialog(null,"Item Not Accepted", "Recycle Failed", JOptionPane.ERROR_MESSAGE);
				}else{
					rcm.recycleItem(entered);
					value += entered.getValue();
					removeAll();
					display();
				}
			}
		});
		
		JButton finish = new JButton("Finish");
		finish.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				rcm.finishSession();
				rmos.rcmUpdate();
				cards.next(cardPanel);
			}
		});
		JPanel finishWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		finishWrapper.add(finish);
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(title);
		add(Box.createVerticalGlue());
		add(statusPanel);
		add(Box.createVerticalGlue());
		add(items);
		add(recycle);
		add(Box.createVerticalGlue());
		add(finishWrapper);
	}
	
	public void setRCM(RCM rcm) 
	{
		this.rcm = rcm;
		removeAll();
		display();
	}

}
















