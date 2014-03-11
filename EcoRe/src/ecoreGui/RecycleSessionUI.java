//RecycleUI session that calculates the value of the dropped objects
package ecoreGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
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
import java.io.IOException;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
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
	private RMOS rmosObj;
	private Item itemPlasticObj;
	private Item itemGlassObj;
	private CardLayout cards;
	private JPanel cardPanel;
	private JLabel totalvalue = new JLabel("Total Weight : ");
	private JLabel customerInfo = new JLabel();
	JLabel totalWeight = new JLabel();
	private JTextArea receptacleArea = new JTextArea();
	JFrame frame = null;
	double currentWeight = 0;
	private double itemCount = 0;

	JPanel rightPanel = new JPanel(new BorderLayout());
	JPanel leftPanel = new JPanel(new BorderLayout());
	JPanel panOuter = new JPanel(new BorderLayout());
	JPanel topLeftPanel = new JPanel();
	JPanel bottomLeftPanel = new JPanel(new BorderLayout());
	JPanel plasticPanel = new JPanel();
	JPanel glassPanel = new JPanel();
	JLabel imagelabel = new JLabel(); 
	JLabel imagelabel2 = new JLabel();
	JLabel imagelabel3 = new JLabel();
	JTextArea recycleInfoArea = new JTextArea(5,20);
	JLabel ItemWeightLabel = new JLabel();
	
	JLabel totalValue, itemName, itemWeight, itemValue;

	myTextField aluminumTextField;
	myTextField glassTextField;
	myTextField plasticTextField;

	DragSource ds;
	
	private CompleteSessionUI complete;
	
	private RecycleSessionUIDragDrop dragDropObj;

	private enum itemKeys {
		Type,
		Item,
		Weight,
		Price,
		PriceUnit,
	}

	public RecycleSessionUI(RMOS rmos,RCM rcm,final CardLayout cards, final JPanel cardPanel, CompleteSessionUI complete)  
	{
		dragDropObj = new RecycleSessionUIDragDrop();
		
		this.complete = complete;
		this.rmosObj = rmos;
		this.rcmObj = rcm;
		this.cards = cards;
		this.cardPanel = cardPanel;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		display();
	}

	private class myTextField extends JTextField implements Serializable {

		private static final long serialVersionUID = 1L;
		private Map<itemKeys, String> item = new HashMap<itemKeys, String>();

		public myTextField (String type,String name, double weight, double price, String priceUnit) {
			super(name);
			item.put(itemKeys.Type, type);
			item.put(itemKeys.Item, name);
			item.put(itemKeys.Weight, Double.toString(weight));
			item.put(itemKeys.Price, Double.toString(price));
			item.put(itemKeys.PriceUnit, priceUnit);
		}
	}

	private class myTransferable implements Transferable
	{
		private Map<itemKeys, String> trItem;

		public myTransferable (Map<itemKeys, String> item) {
			super();
			setTrItem(item);
		}

		@Override
		public Object getTransferData(DataFlavor arg0)
				throws UnsupportedFlavorException, IOException {
			return
					trItem.get(itemKeys.Type)   + ", " +
					trItem.get(itemKeys.Item) 	+ ", " +
					trItem.get(itemKeys.Weight) + ", " +
					trItem.get(itemKeys.Price) 	+ ", " + 
					trItem.get(itemKeys.PriceUnit);
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { DataFlavor.stringFlavor };
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor arg0) {
			return arg0.equals(DataFlavor.stringFlavor);
		}

		public void setTrItem(Map<itemKeys, String> trItem) {
			this.trItem = trItem;
		}

	}
	private class myTransferHandler extends TransferHandler {

		private static final long serialVersionUID = 1L;

		@Override
		protected Transferable createTransferable (JComponent c) {
			myTextField srcTextField = (myTextField) c;
			return new myTransferable(srcTextField.item);
		}
		@Override
		protected void exportDone(JComponent c, Transferable data, int action) {
			System.out.println("Export done.");
		}

		@Override
		public int getSourceActions (JComponent c) {
			return COPY;
		}
	}

	private void display()
	{
		myTransferHandler trHndlr = new myTransferHandler();
		panOuter.setPreferredSize(new Dimension(500,500));
		//Left Panel Initial setup
		leftPanel.setPreferredSize(new Dimension(200,400));
		leftPanel.setBackground(new Color(133,255,133));
		leftPanel.setLayout(new GridLayout(2,1));

		//Right Panel code
		rightPanel.setPreferredSize(new Dimension(400,350));
		rightPanel.setBackground(new Color(255,255,255));
		rightPanel.setLayout(new GridLayout(4,1));
		JLabel messagelabel = new JLabel("<html> Recycle with EcoRe <br> Drag your items into the receptacle</html>",JLabel.CENTER);
		messagelabel.setForeground(new Color(34, 139, 34));
		messagelabel.setFont((new Font("Garamond", Font.BOLD, 18)));
		rightPanel.add(messagelabel);

		//Aluminium Instance

		itemObj = new Item("Coke Can",rmosObj.getAluminum(), 5.2);
		//itemTypeAluminumObj = new ItemType("Aluminium", itemObj.getValue());
		//Glass Instance 
		itemGlassObj = new Item("Soda Bottles",rmosObj.getGlass(), 400);
		//itemTypeGlassObj = new ItemType("Glass",itemObj.getValue());

		//Plastic Instance
		ItemType itemTypePlasticObj = new ItemType("Plastic" , 1.80);
		itemPlasticObj = new Item("Plastic Bottle",itemTypePlasticObj,1);




		//Draggable objects
		aluminumTextField = new myTextField(
				itemObj.getType().getName(),
				itemObj.getName(),
				itemObj.getWeight(),
				itemObj.getType().getPrice(),
				"$/lbs");

		aluminumTextField.setBackground(new Color(34, 139, 34));
		aluminumTextField.setEditable(false);
		aluminumTextField.setDragEnabled(true);
		aluminumTextField.setTransferHandler(trHndlr);
		aluminumTextField.setHorizontalAlignment(SwingConstants.CENTER);
		aluminumTextField.setPreferredSize(new Dimension(70,100));
		aluminumTextField.setMaximumSize(new Dimension(70, 100));
		aluminumTextField.setMinimumSize(new Dimension(70, 100));

		//Aluminium Panel
		JPanel aluminumPanel = new JPanel();
		aluminumPanel.setBackground(Color.white);
		rightPanel.add(aluminumPanel);
		aluminumPanel.add(Box.createHorizontalGlue());
		aluminumPanel.add(aluminumTextField);
		aluminumPanel.add(Box.createHorizontalGlue());
		aluminumPanel.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GREEN, null), "Aluminum")));



		glassTextField = new myTextField(
				itemGlassObj.getType().getName(),
				itemGlassObj.getName(),
				itemGlassObj.getWeight(),
				itemGlassObj.getValue(),
				"$/lbs");
		glassTextField.setHorizontalAlignment(SwingConstants.CENTER);
		glassTextField.setPreferredSize(new Dimension(70,100));
		glassTextField.setMaximumSize(new Dimension(70, 100));
		glassTextField.setMinimumSize(new Dimension(70, 100));
		
		glassTextField.setDragEnabled(true);
		glassTextField.setEditable(false);
		glassTextField.setTransferHandler(trHndlr);

		//Glass Panel
		glassPanel.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GREEN, null), "Glass")));
		glassPanel.setBackground(Color.white);
		glassPanel.add(Box.createHorizontalGlue());
		glassPanel.add(glassTextField);
		glassPanel.add(Box.createHorizontalGlue());
		rightPanel.add(glassPanel);


		//Plastic Panel
		
		plasticTextField = new myTextField(
				itemPlasticObj.getType().getName(),
				itemPlasticObj.getName(),
				itemPlasticObj.getWeight(),
				itemPlasticObj.getValue(),
				"$/lbs");
		
		plasticTextField.setDragEnabled(true);
		plasticTextField.setTransferHandler(trHndlr);
		plasticTextField.setHorizontalAlignment(SwingConstants.CENTER);
		plasticTextField.setPreferredSize(new Dimension(70,100));
		plasticTextField.setMaximumSize(new Dimension(70, 100));
		plasticTextField.setMinimumSize(new Dimension(70, 100));
		
		plasticPanel.add(Box.createHorizontalGlue());
		plasticPanel.add(plasticTextField);
		plasticPanel.add(Box.createHorizontalGlue());
		plasticPanel.setBorder((new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GREEN, null), "Plastic")));
		plasticPanel.setBackground(Color.white);
		rightPanel.add(plasticPanel);
		panOuter.add(rightPanel,BorderLayout.CENTER);

		//Left Panel
		topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.Y_AXIS));
		topLeftPanel.setBackground(new Color(255,255,255));
		
		customerInfo = new JLabel("Items: 0");
		customerInfo.setHorizontalAlignment(SwingConstants.CENTER);
		topLeftPanel.add(Box.createVerticalGlue());
		topLeftPanel.add(customerInfo);
		
		totalValue = new JLabel("Value: 0");
		totalValue.setHorizontalAlignment(SwingConstants.CENTER);
		topLeftPanel.add(totalValue);
		
		JLabel depositedItem = new JLabel("You Deposited:");
		depositedItem.setHorizontalAlignment(SwingConstants.CENTER);
		topLeftPanel.add(Box.createRigidArea(new Dimension(10, 30)));
		topLeftPanel.add(depositedItem);		
		
		itemName = new JLabel("");
		itemName.setHorizontalAlignment(SwingConstants.CENTER);
		topLeftPanel.add(itemName);
		
		itemWeight = new JLabel("");
		itemWeight.setHorizontalAlignment(SwingConstants.CENTER);
		topLeftPanel.add(itemWeight);
		
		itemValue = new JLabel("");
		itemValue.setHorizontalAlignment(SwingConstants.CENTER);
		topLeftPanel.add(itemValue);
		
		topLeftPanel.add(Box.createRigidArea(new Dimension(10, 30)));
		
		
		
		leftPanel.add(topLeftPanel,BorderLayout.NORTH);

		JButton finishButton = new JButton("FINISH");
		finishButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				itemCount = 0;
				customerInfo.setText("Items: 0");
				totalValue.setText("Value: 0");
				itemName.setText("");
				itemWeight.setText("");
				itemValue.setText("");
				complete.updateSession();
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
		double itemTotalValue = 0;
		double valueGlass = 0;
		Item selectableItems;


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

		private Map<itemKeys, String> extractItemMap(String s) {
			Map<itemKeys, String> retMap = new HashMap<itemKeys, String>();
			String[] arrStrValues = s.split(", ");
			int idx = 0;
			for (itemKeys currentKey : EnumSet.allOf(itemKeys.class)) {
				retMap.put(currentKey, arrStrValues[idx++]);
			}
			return retMap;
		}
		@Override
		public void drop(DropTargetDropEvent dtde) {

			dtde.acceptDrop(DnDConstants.ACTION_COPY);
			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();

			double weight = 0;
			for (DataFlavor flavor : flavors) 
			{
				try{
					if (flavor.isFlavorTextType()) 
					{
						String sItem = tr.getTransferData(flavor).toString();
						if (sItem.contains("java")) 
						{
							break;
						}
						Map<itemKeys, String> valueMap = extractItemMap(sItem);

						++ itemCount;
						String type = valueMap.get(itemKeys.Type);
						String name = valueMap.get(itemKeys.Item);
						double value = Double.parseDouble(valueMap.get(itemKeys.Price));
						weight = Double.parseDouble(valueMap.get(itemKeys.Weight));
						
						
						boolean good = false;
						for(Item i: rmosObj.getAcceptedItems())
						{
							
							if(name.equals(i.getName()) && type.equals(i.getType().getName()) && (weight == i.getWeight()))
							{
								good = true;
								if(((weight/16) + rcmObj.getWeight()) > rcmObj.getCapacity())
								{
									target.setActive(false);
									JOptionPane.showMessageDialog(frame,"Please use any other nearest machine", " Machine Full !", JOptionPane.WARNING_MESSAGE);
									cards.next(cardPanel );
								}else{
									rcmObj.recycleItem(value,weight,type);
									
									customerInfo.setText("Items: " + Double.toString(itemCount) );
									System.out.println("new value: " + rcmObj.getSessionValue());
									totalValue.setText("Value: " + String.valueOf(rcmObj.getSessionValue()));
									itemName.setText(name);
									itemWeight.setText("Weight: " + String.valueOf(weight) + "oz");
									itemValue.setText("Price: $" + String.valueOf(value));
								}
								break;
							}
						}
						if(!good)
						{
							JOptionPane.showMessageDialog(frame,"The item that you have dropped is not accepted", " Invalid Item !", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				catch (Exception e) {
					// Print out the error stack
					e.printStackTrace();

				}
			}
			dtde.dropComplete(true);
			return;
		}

		@Override
		public void dropActionChanged(DropTargetDragEvent arg0) {
		}
	}
}