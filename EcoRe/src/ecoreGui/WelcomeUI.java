//The main screen of the RCM part ...
package ecoreGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ecore.RCM;
import ecore.RMOS;

public class WelcomeUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private MaintenanceUI maintain ;
	private RCM rcm;

	public WelcomeUI(RCM rcm, final CardLayout cards, final JPanel cardPanel)
	{
		this.rcm = rcm;
		JPanel headingPanel = new JPanel(new BorderLayout());
		JLabel WelcomeLabel = new JLabel("<html>Welcome to EcoRe <br> Recycle your aluminium , glass and plastic here </html>");
		WelcomeLabel.setFont(new Font("Serif", Font.BOLD, 14));
		headingPanel.add(WelcomeLabel,BorderLayout.NORTH);
		add(headingPanel);

		
		//Main panel
		JPanel mainpanel = new JPanel(new GridLayout(1,2));
		mainpanel.setPreferredSize(new Dimension(500,500));
		JPanel fortabelpanel = new JPanel(new BorderLayout());
		//fortabelpanel.setPreferredSize(new Dimension(580,570)); 
		JLabel tablelabel = new JLabel("Get Cash for your Trash");

		DefaultTableModel model = new DefaultTableModel(); 
		JTable table = new JTable(model); 
		table.setPreferredSize(new Dimension(50,10));
		// Create a couple of columns 
		model.addColumn("Recyclable"); 
		model.addColumn("Price per lb"); 

		// Append a row 
		model.addRow(new Object[]{"Aluminium", "$1.25"});
		model.addRow(new Object[]{"Glass","$1.90"});
		model.addRow(new Object[]{"Plastic","$2.12"});

		fortabelpanel.add(tablelabel,BorderLayout.NORTH);
		fortabelpanel.add(table,BorderLayout.CENTER);

		mainpanel.add(fortabelpanel);

		JPanel rightPanel = new JPanel(new BorderLayout());
		JButton start = new JButton("Start");

		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cards.show(cardPanel, "Recycle");
			}


		});

		JButton maintanence = new JButton("Maintanence");
		maintanence.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//cards.show(cardPanel,maintain);
				cards.show(cardPanel, "Maintenance");
			}


		});

		rightPanel.add(start,BorderLayout.CENTER);
		rightPanel.add(maintanence, BorderLayout.PAGE_END);
		mainpanel.add(rightPanel);
		add(mainpanel);



	}
	
	public void setRCM(RCM rcm){
		this.rcm = rcm;
	}

}
