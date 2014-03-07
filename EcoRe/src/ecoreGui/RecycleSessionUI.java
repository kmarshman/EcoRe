//RecycleUI session that calculates the value of the dropped objects
package ecoreGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

import javax.swing.GrayFilter;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ecore.ItemType;
import ecore.RCM;

public class RecycleSessionUI extends JPanel implements DropTargetListener   {

	private static final long serialVersionUID = 1L;
	private RCM rcmObj;
	private ItemType itemTypeObj = null;
	private double FinalValue = 0;
	JLabel totalvalue = new JLabel("Total Value:");

	JTextArea receptacleArea = new JTextArea(5,10);

	public RecycleSessionUI() throws TooManyListenersException
	{
		new RecycleSessionUI(new RCM(), null, null);
	}
	public RecycleSessionUI(RCM rcm,final CardLayout cards, final JPanel cardPanel) //throws TooManyListenersException 
	{
		this.rcmObj = rcm;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


		JPanel panOuter = new JPanel(new BorderLayout());
		JPanel leftPanel = new JPanel(new BorderLayout());
		JPanel rightPanel = new JPanel(new BorderLayout());

		panOuter.setPreferredSize(new Dimension(500,500));

		leftPanel.setPreferredSize(new Dimension(200,400));
		leftPanel.setLayout(new GridLayout(2,1));

		rightPanel.setPreferredSize(new Dimension(400,350));
		rightPanel.setLayout(new GridLayout(4,1));
		JLabel messagelabel = new JLabel("<html> Recycle with EcoRe <br> Drag your items into the receptacle</html>",JLabel.CENTER);

		rightPanel.add(messagelabel);


		ImageIcon icon = new ImageIcon("C:/Users/Shweta/git/EcoRe/EcoRe/src/Images/coke.jpg");
		JLabel imagelabel = new JLabel();
		imagelabel.setIcon(icon);
		JTextField Aluminium = new JTextField("Aluminum");
		Aluminium.setBackground(Color.black);
		Aluminium.setSize(new Dimension(30,40));

		Aluminium.setEditable(false);
		Aluminium.setDragEnabled(true);
		JPanel aluminumPanel = new JPanel(new BorderLayout());
		aluminumPanel.add(imagelabel,BorderLayout.EAST);
		aluminumPanel.add(Aluminium, BorderLayout.WEST);


		rightPanel.add(aluminumPanel);
		aluminumPanel.setBorder((new TitledBorder(new EtchedBorder(), "Aluminium")));



		JPanel plasticPanel = new JPanel();
		plasticPanel.setBorder((new TitledBorder(new EtchedBorder(), "Plastic")));
		rightPanel.add(plasticPanel);

		JPanel glassPanel = new JPanel();
		glassPanel.setBorder((new TitledBorder(new EtchedBorder(), "Glass")));
		rightPanel.add(glassPanel);
		panOuter.add(rightPanel,BorderLayout.CENTER);


		JPanel topLeftPanel = new JPanel(new BorderLayout());
		JLabel customerInfo = new JLabel("You Deposited");



		topLeftPanel.add(customerInfo,BorderLayout.CENTER);
		topLeftPanel.add(totalvalue,BorderLayout.PAGE_END);
		leftPanel.add(topLeftPanel,BorderLayout.NORTH);
		JPanel bottomLeftPanel = new JPanel(new BorderLayout());

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

		// This part recieves the dropped objects
		final ImageIcon receptacleImage = new  ImageIcon("C:/Users/Shweta/git/EcoRe/EcoRe/src/Images/receptacle.jpg");
		JTextArea receptacleArea = new JTextArea(){
			private static final long serialVersionUID = 1L;
		Image image = receptacleImage.getImage();
		//setOpaque(false);
		public void paint(Graphics g) 
		{
			g.drawImage(image, 0, 0, this);
			super.paint(g);
		}};

		

		setVisible(true);
		receptacleArea.setDragEnabled(true);
		bottomLeftPanel.add(receptacleArea,BorderLayout.CENTER);
		DropTarget dt = new DropTarget(receptacleArea, this);
		bottomLeftPanel.add(finishButton,BorderLayout.PAGE_END);
		leftPanel.add(bottomLeftPanel);
		panOuter.add(leftPanel,BorderLayout.EAST);
		add(panOuter,BorderLayout.CENTER);



	}
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}
	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub

	}
	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}
	@Override
	public void drop(DropTargetDropEvent dtde) 
	{

		try {
			// Ok, get the dropped object and try to figure out what it is
			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) 
			{
				System.out.println("Possible flavor: "+ i+ flavors[i].getMimeType());
				dtde.dropComplete(true);
				totalvalue.setText((Double.toString(FinalValue)));
				// FinalValue = itemObj.getValue();

				return;
			}
		}
		catch (Exception e) {
			e.printStackTrace();

		}


	}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	public double calculateDetails()
	{
		rcmObj.getWeight();
		itemTypeObj.setPrice(1.25);
		double price =  rcmObj.getWeight() * itemTypeObj.getPrice();
		return price;
	} 
	
	public void setRCM(RCM rcm){
		rcmObj = rcm;
	}
}

