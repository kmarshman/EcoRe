package rmos;

import java.awt.*;

import javax.swing.*;

import rcm.*;

public class Main extends JFrame {
	
	public Main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("EcoRe");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension((screenSize.width/8)*7, screenSize.height/5*4));
		setLocationRelativeTo(null);
		
		setLayout(new GridLayout(1,2));
		
		RecycleUI rcm = new RecycleUI();
		
    	JTabbedPane tabbedPane = new JTabbedPane();
    	tabbedPane.addTab("Home", new DashboardUI());
    	tabbedPane.addTab("Manage Machines", new MachineManagerUI());
    	tabbedPane.addTab("Manage Recyclables", new RecyclablesManagerUI());
    	
    	getContentPane().add(tabbedPane);
    	getContentPane().add(rcm);
    	
    	setVisible(true);
	}

	public static void main(String[] args) {
		Main window = new Main();
	}

}
