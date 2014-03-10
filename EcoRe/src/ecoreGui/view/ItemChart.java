package ecoreGui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		setRmos(rmos);
		setPreferredSize(new Dimension(250, 250));
		setBorder(new EmptyBorder(50, 50, 50, 50));		
	}
	
	private void getSlices(){
		getRmos().setTotalWeights();
		slices[0] = new Slice(getRmos().getTotalAluminumWeight(), new Color(85, 255, 47), "Aluminum");
		slices[1] = new Slice(getRmos().getTotalGlassWeight(), new Color(68, 204, 37), "Glass");;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		getSlices();
		drawPie((Graphics2D)g, getBounds());
	}
	
	private void drawPie(Graphics2D g, Rectangle area){
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
	    	g.fillArc(40, 40, area.width - 40, area.height - 40, startAngle, arcAngle);
		      
	    	curValue += s.value;
	    }
	    
	    int x = 0;
	    int y = 35;
	    for(Slice s: slices){
	    	g.setColor(s.color);
	    	g.fillRect(x, y, 10, 10);
	    	
	    	g.setColor(Color.BLACK);
	    	int percentage = (int) (s.value / total * 100);
	    	g.drawString ((s.name + " " + percentage + "%"), x + 15, y + 10);
	    	y += 15;
	    }
	    
	    g.setColor(Color.BLACK);
	    g.setFont(new Font("SansSerif", Font.BOLD, 14));
		g.drawString("% Contributions by Material", 30, 25);
	}
}
