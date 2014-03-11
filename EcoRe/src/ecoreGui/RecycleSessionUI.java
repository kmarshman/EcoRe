//RecycleUI session that calculates the value of the dropped objects
package ecoreGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
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

public class RecycleSessionUI extends JPanel
{

	private static final long serialVersionUID = 1L;
	private RCM rcmObj;
	private Item itemObj;
	private ItemType itemTypeAluminumObj = null;
	private ItemType itemTypeGlassObj;
	private Item itemGlassObj;
	private CardLayout cards;
	private JPanel cardPanel;
	private JLabel totalvalue = new JLabel();
	private JLabel customerInfo = new JLabel();
	private JTextArea receptacleArea = new JTextArea();

	JPanel aluminumPanel = new JPanel(new BorderLayout());
	JPanel rightPanel = new JPanel(new BorderLayout());
	JPanel leftPanel = new JPanel(new BorderLayout());
	JPanel panOuter = new JPanel(new BorderLayout());
	JPanel topLeftPanel = new JPanel(new BorderLayout());
	JPanel bottomLeftPanel = new JPanel(new BorderLayout());
	JPanel plasticPanel = new JPanel();
	JPanel glassPanel = new JPanel();
	JLabel imagelabel = new JLabel(); 
	JLabel imagelabel2 = new JLabel();
	JLabel imagelabel3 = new JLabel();


	ImageIcon icon = new ImageIcon("C:/Users/Shweta/git/EcoRe/EcoRe/src/Images/CokeEdited.jpg");
	ImageIcon glassIcon = new ImageIcon("C:/Users/Shweta/git/EcoRe/EcoRe/src/Images/editedglasstiny.jpg");
	ImageIcon recycleIcon = new ImageIcon ("C:/Users/Shweta/git/EcoRe/EcoRe/src/Images/recylce.jpg");
	JTextField Aluminium;
	DragSource ds;
	private RecycleSessionUIDragDrop dragDropObj = new RecycleSessionUIDragDrop();

	private enum itemKeys {
		itemName,
		itemWeight,
		itemPrice,
		itemPriceUnit,
	}

	public RecycleSessionUI(RMOS rmos,RCM rcm,final CardLayout cards, final JPanel cardPanel)  
	{
		this.rcmObj = rcm;
		this.cards = cards;
		this.cardPanel = cardPanel;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		display();
	}

	private void display()
	{
		panOuter.setPreferredSize(new Dimension(500,500));
		//Left Panel Initial setup
		leftPanel.setPreferredSize(new Dimension(200,400));
		leftPanel.setBackground(new Color(133,255,133));
		leftPanel.setLayout(new GridLayout(2,1));

		//Right Panel code
		rightPanel.setPreferredSize(new Dimension(400,350));
		rightPanel.setBackground(new Color(255,255,255));
		rightPanel.setLayout(new GridLayout(4,1));
		JLabel messagelabel = new JLabel("<html><font face=\"Garamond\" color=\"228b22\"> Recycle with EcoRe <br> Drag your items into the receptacle</html>",JLabel.CENTER);
		rightPanel.add(messagelabel);

		//Aluminium Instance
		itemTypeAluminumObj = new ItemType("Aluminium", 1.25);
		itemObj = new Item("Coke Can", itemTypeAluminumObj, 2);
		//Glass Instance 
		itemTypeGlassObj = new ItemType("Glass", 1.50);
		itemGlassObj = new Item("Soda Bottles", itemTypeGlassObj, 4);

		//Draggable objects
		Aluminium = new JTextField(
				itemKeys.itemName.toString()   		+ ": " + itemObj.getName()   			+ ", " +
						itemKeys.itemWeight.toString() 		+ ": " + itemObj.getWeight() 			+ ", " +
						itemKeys.itemPrice.toString() 		+ ": " + itemObj.getType().getPrice() 	+ ", " +
						itemKeys.itemPriceUnit.toString()   + ": " + "$/lbs");

		Aluminium.setPreferredSize(new Dimension(180,25));
		Aluminium.setEditable(false);
		Aluminium.setDragEnabled(true);

		//Aluminium Panel
		aluminumPanel.add(Aluminium, BorderLayout.WEST);
		aluminumPanel.setBackground(Color.white);
		imagelabel.setIcon(icon);
		aluminumPanel.add(imagelabel,BorderLayout.EAST); 
		aluminumPanel.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GREEN, null), "ALUMINUM")));
		

		//Adding to the right panel
		rightPanel.add(aluminumPanel);
		panOuter.add(rightPanel,BorderLayout.CENTER);

		JTextField Glass = new JTextField(
				itemKeys.itemName.toString()   		+ ": " + itemGlassObj.getName()   			+ ", " +
						itemKeys.itemWeight.toString() 		+ ": " + itemGlassObj.getWeight() 			+ ", " +
						itemKeys.itemPrice.toString() 		+ ": " + itemGlassObj.getType().getPrice() 	+ ", " +
						itemKeys.itemPriceUnit.toString()   + ": " + "$/lbs");
		Glass.setPreferredSize(new Dimension(190,118));
		Glass.setDragEnabled(true);
		Glass.setEditable(false);

		//Glass Panel
		imagelabel2.setIcon(glassIcon);
		//glassPanel.add(imagelabel2,BorderLayout.EAST);
		glassPanel.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GREEN, null), "Glass")));
		glassPanel.setBackground(Color.white);
		rightPanel.add(glassPanel);
		glassPanel.add(Glass,BorderLayout.WEST);

		//Plastic Panel
		plasticPanel.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GREEN, null), "Plastic")));
		plasticPanel.setBackground(Color.white);
		rightPanel.add(plasticPanel);

		//Left Panel
		imagelabel3.setIcon(recycleIcon);
		topLeftPanel.setBackground(new Color(255,255,255));
		topLeftPanel.add(imagelabel3,BorderLayout.NORTH);
		JLabel customerInfoLabel = new JLabel("Items Deposited :");
		topLeftPanel.add(customerInfoLabel,BorderLayout.WEST);
		topLeftPanel.add(customerInfo,BorderLayout.CENTER);
		topLeftPanel.add(totalvalue,BorderLayout.PAGE_END);
		leftPanel.add(topLeftPanel,BorderLayout.NORTH);

		//Bottom Panel
		JButton backButton = new JButton("BACK");	
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cards.previous(cardPanel);
			}

		});
		JButton finishButton = new JButton("FINISH");
		finishButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cards.show(cardPanel, "Finish");
			}

		});	

		//	Drop Target Area
		receptacleArea.setDragEnabled(true);
		receptacleArea.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GREEN, null), "Dump Here !")));
		bottomLeftPanel.add(receptacleArea,BorderLayout.CENTER);
		bottomLeftPanel.add(finishButton,BorderLayout.PAGE_END);
		leftPanel.add(bottomLeftPanel);

		panOuter.add(leftPanel,BorderLayout.EAST);
		add(panOuter, BorderLayout.CENTER);
	}

	public void setRCM(RCM _rcm) 
	{
		this.rcmObj = _rcm;
		repaint();
	}

	//Inner class that implements the drag drop features.
	class RecycleSessionUIDragDrop implements DropTargetListener, DragGestureListener
	{
		DropTarget target = new DropTarget(receptacleArea, this);
		double itemCount = 0;
		double itemTotalValue = 0;
		double valueGlass = 0;
		int machineCapacity = 0;
		final int maxCapacity = 4;

		public String stackTraceToString(Throwable e) {
			StringBuilder sb = new StringBuilder();
			for (StackTraceElement element : e.getStackTrace()) {
				sb.append(element.toString());
				sb.append("\n");
			}
			return sb.toString();
		}

		@Override
		public void dragGestureRecognized(DragGestureEvent arg0) {

		}

		@Override
		public void dragEnter(DropTargetDragEvent arg0) {
		}

		@Override
		public void dragExit(DropTargetEvent arg0) {
		}

		@Override
		public void dragOver(DropTargetDragEvent arg0) {
		}

		@Override
		public void drop(DropTargetDropEvent dtde) {

			dtde.acceptDrop(DnDConstants.ACTION_COPY);
			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();

			for (DataFlavor flavor : flavors) 
			{
				try{
					if (flavor.isFlavorTextType()) 
					{
						String sItem = tr.getTransferData(flavor).toString();
						if (sItem.contains(itemKeys.itemName.toString())) {
							System.out.println(sItem);

							String[] arrValuePairs = sItem.split(", ");
							Map<String, String> valueMap = new HashMap<String, String>();
							for (String valuePair : arrValuePairs)
							{
								String[] items = valuePair.split(": ");
								valueMap.put(items[0], items[1]);
							}

							++ itemCount;
							customerInfo.setText(Double.toString(itemCount));
							itemTotalValue += Double.parseDouble(valueMap.get(itemKeys.itemPrice.toString()));
							totalvalue.setText("Total Value =  $" + itemTotalValue);
							machineCapacity += Double.parseDouble(valueMap.get(itemKeys.itemWeight.toString()));
							//Not working
							//rcmObj.recycleItem(itemObj);
						}
						System.out.println("Capacity =" +machineCapacity);
						break;
					}
				} catch (Exception e) {
					// Print out the error stack
					e.printStackTrace();
				}
			}
			dtde.dropComplete(true);
			//if( machineCapacity >= maxCapacity)
			if(machineCapacity >  rcmObj.getCapacity())
			{
				target.setActive(false);
				JFrame frame = null;
				JOptionPane.showMessageDialog(frame,"Please use any other nearest machine", " Machine Full !", JOptionPane.WARNING_MESSAGE);
			}
			return;
		}

		@Override
		public void dropActionChanged(DropTargetDragEvent arg0) {
		}
	}
}
















