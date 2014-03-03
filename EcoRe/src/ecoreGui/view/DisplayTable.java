package ecoreGui.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTable;

import ecore.RMOS;

public abstract class DisplayTable extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private RMOS rmos;
	private JTable table;
	
	public abstract void display();
	
	public JTable getTable(){
		return table;
	}
	
	public void setTable(JTable table){
		this.table = table;
	}
	
	public RMOS getRmos() {
		return rmos;
	}
	
	public void setRmos(RMOS rmos) {
		this.rmos = rmos;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		rmos = (RMOS)arg;
		this.display();
	}

}
