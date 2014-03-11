package ecoreGui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import ecore.RMOS;

/**
 * 
 * @author Kelsey
 *
 */
public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates main window split into 2 panels: left for RMOS and right for RCMs
	 * @param rmos instance of model represented by UI
	 */
	public Main(final RMOS rmos, final File rmosFile){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("EcoRe");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension((screenSize.width/8)*7, screenSize.height/7*6));
		setLocationRelativeTo(null);
		
		
		setLayout(new GridLayout(1,2));
		
		RcmUI rcmUI = new RcmUI(rmos);
		RmosUI rmosUI = new RmosUI(rmos);
		
    	getContentPane().add(rmosUI);
		getContentPane().add(rcmUI);
    	
    	setVisible(true);
    	
    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) {
    			try{
    				FileOutputStream fileOut = new FileOutputStream(rmosFile, false); 
			        ObjectOutputStream out = new ObjectOutputStream(fileOut);
			        out.writeObject(rmos);
			        out.close();
			        fileOut.close();
			   }catch(IOException i){
			        i.printStackTrace();
			   }
    		}
    	});
	}
	
	public static void main(String[] args) {
		RMOS rmos = null;
	    try{
	    	FileInputStream fileIn = new FileInputStream("rmos.ser");
	    	ObjectInputStream in = new ObjectInputStream(fileIn);
	    	rmos = (RMOS) in.readObject();
	    	rmos.setChart("Value Collected Today", "Value", "Day");
	    	rmos.setIO();
	    	in.close();
	    	fileIn.close();
	    }catch(IOException i){
	    	rmos = new RMOS();
	    }catch(ClassNotFoundException c){
	    	System.out.println("RMOS class not found");
	    	c.printStackTrace();
	    	return;
	    }
		
	   	File file = new File("rmos.ser");
	   	if(!file.exists()) {
	   	    try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	   	}
   	 
		new Main(rmos, file);
	}
}
