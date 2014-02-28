package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import ecore.RMOS;

public class ItemChart extends GraphicDisplay{

	private static final long serialVersionUID = 1L;
	
	public ItemChart(RMOS rmos){
		setPreferredSize(new Dimension(250, 250));
		setBorder(new EmptyBorder(20, 20, 20, 20));
	}
	

}
