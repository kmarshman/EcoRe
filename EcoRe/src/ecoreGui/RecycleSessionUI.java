package ecoreGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ecore.RMOS;

public class RecycleSessionUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static RMOS rmos;

	public RecycleSessionUI()
	{
		new RecycleSessionUI(new RMOS(), null, null);
	}
	public RecycleSessionUI(RMOS rmos,final CardLayout cards, final JPanel cardPanel) 
	{
		this.rmos = rmos;
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

		String aluminiumIcsPath[] =  {
			"EcoRe/EcoRe/src/Images/coca-cola.jpg",
		};
		int aluminiumIcsCount = aluminiumIcsPath.length;
		ImageIcon icsObj[] = new ImageIcon[aluminiumIcsCount];
		JLabel aluminiumIcsLabels[] = new JLabel[aluminiumIcsCount];
		JPanel aluminiumIcsPanel = new JPanel();

		for (int idx = 0; idx < aluminiumIcsCount; ++ idx) {
			icsObj[idx] = new ImageIcon(aluminiumIcsPath[idx]);
			aluminiumIcsLabels[idx] = new JLabel(icsObj[idx]);
			aluminiumIcsPanel.add(aluminiumIcsLabels[idx]);
		}
		aluminiumIcsPanel.setBorder((new TitledBorder(new EtchedBorder(), "Aluminium")));
		
		rightPanel.add(aluminiumIcsPanel);
		
		JPanel plasticPanel = new JPanel();
		plasticPanel.setBorder((new TitledBorder(new EtchedBorder(), "Plastic")));
		rightPanel.add(plasticPanel);

		JPanel glassPanel = new JPanel();
		glassPanel.setBorder((new TitledBorder(new EtchedBorder(), "Glass")));
		rightPanel.add(glassPanel);
		panOuter.add(rightPanel,BorderLayout.CENTER);


		JPanel topLeftPanel = new JPanel(new BorderLayout());
		JLabel customerInfo = new JLabel("You Deposited");
		JLabel totalvalue = new JLabel("Total Value:");

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
				cards.next(cardPanel);
			}


		});
		JTextArea receptacleArea = new JTextArea(5,10);
		setVisible(true);
		bottomLeftPanel.add(receptacleArea,BorderLayout.CENTER);
		//bottomLeftPanel.add(backButton,BorderLayout.AFTER_LINE_ENDS);
		bottomLeftPanel.add(finishButton,BorderLayout.PAGE_END);
		leftPanel.add(bottomLeftPanel);

		panOuter.add(leftPanel,BorderLayout.EAST);
		add(panOuter,BorderLayout.CENTER);



	}
}