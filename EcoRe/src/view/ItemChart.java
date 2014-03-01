package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.border.EmptyBorder;

import ecore.RMOS;

public class ItemChart extends GraphicDisplay{

	private static final long serialVersionUID = 1L;
	private Slice[] slices = new Slice[2];
	
	private class Slice{
		double value;
		Color color;
		String name;
		
		public Slice(double value, Color color, String name){
			this.value = value;
			this.color = color;
			this.name = name;
		}
	}
	
	public ItemChart(RMOS rmos){
		setPreferredSize(new Dimension(250, 250));
		setBorder(new EmptyBorder(50, 50, 50, 50));
		
		slices[0] = new Slice(getAluminum(), new Color(85, 255, 47), "Aluminum");
		slices[1] = new Slice(getGlass(), new Color(148, 255, 123), "Glass");;
	}
	
	@Override
	public void paintComponent(Graphics g){
		drawPie((Graphics2D)g, getBounds(), slices);
	}
	
	private void drawPie(Graphics2D g, Rectangle area, Slice[] slices){
	    double total = 0.0D;
	    for (Slice s: slices) {
	    	total += s.value;
	    }

	    int startAngle = 0;
	    int curValue = 0;
	    for (Slice s: slices) {
	    	startAngle = (int) (curValue * 360 / total);
	    	int arcAngle = (int) (s.value * 360 / total);
	      
	    	g.setColor(s.color);
	    	g.fillArc(50, 50, area.width - 50, area.height - 50, startAngle, arcAngle);
		      
	    	curValue += s.value;
	    }
	    
	    int x = 0;
	    int y = 20;
	    for(Slice s: slices){
	    	g.setColor(s.color);
	    	g.fillRect(x, y, 10, 10);
	    	
	    	g.setColor(Color.BLACK);
	    	int percentage = (int) (s.value / total * 100);
	    	g.drawString ((s.name + " " + percentage + "%"), x + 15, y + 10);
	    	y += 15;
	    }
	}
	
	private double getAluminum(){
		return 80;
	}
	
	private double getGlass(){
		return 20;
	}
}
