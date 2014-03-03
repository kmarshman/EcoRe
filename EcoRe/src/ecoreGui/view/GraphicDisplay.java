package ecoreGui.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import ecore.RMOS;

public abstract class GraphicDisplay extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private RMOS rmos;
	
	@Override
	public void update(Observable arg0, Object arg1) {
		setRmos((RMOS)arg1);
		repaint();

	}
	
	public RMOS getRmos() {
		return rmos;
	}
	
	public void setRmos(RMOS rmos) {
		this.rmos = rmos;
	}

}
