package ecoreGui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.border.EmptyBorder;

import ecore.RMOS;

public class BarGraph extends GraphicDisplay {

	private static final long serialVersionUID = 1L;
	private Color[] colors = {new Color(43, 127, 23), new Color(148, 255, 123), new Color(85, 255, 47), new Color(74, 127, 62), new Color(68, 204, 37)};
	private ArrayList<String> ids;
	private ArrayList<Double> values;
	private String title;

	public BarGraph(RMOS rmos){
		setRmos(rmos);
		setBorder(new EmptyBorder(10, 10, 10, 10) );
	}
	
	private void setData(){
		title = getRmos().getChartTitle();
		ids = new ArrayList<String>();
		values = new ArrayList<Double>();
		HashMap<String, Double> data = getRmos().getValues();
		Object[] temp = data.keySet().toArray();
		for(Object t: temp){
			ids.add((String)t);
		}
		temp = data.values().toArray();
		for(Object t: temp){
			values.add((Double)t);
		}
	}
	@Override
	public void paintComponent(Graphics g){
		setData();
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		if (values == null || values.size() == 0) return;
		double minValue = 0;
		double maxValue = 0;
		for (Double v: values) {
			if (minValue > v) minValue = v;
		    if (maxValue < v) maxValue = v;
		}

		Dimension d = getSize();
		int clientWidth = d.width;
		int clientHeight = d.height;
		int barWidth = clientWidth / values.size();

		Font titleFont = new Font("SansSerif", Font.BOLD, 14);
		FontMetrics titleFontMetrics = g2.getFontMetrics(titleFont);
		Font labelFont = new Font("SansSerif", Font.BOLD, 10);
		FontMetrics labelFontMetrics = g2.getFontMetrics(labelFont);

		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (clientWidth - titleWidth) / 2;
		g2.setFont(titleFont);
		g2.drawString(title, x, y);

		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue) return;
		
		double scale = (clientHeight - top - bottom) / (maxValue - minValue);
		y = clientHeight - labelFontMetrics.getDescent();
		g2.setFont(labelFont);

		int colorCount = 0;
		for (int i = 0; i < values.size(); i++) {
			int valueX = i * barWidth + 1;
		    int valueY = top;
		    int height = (int) (values.get(i) * scale);
		    if (values.get(i) >= 0){
		    	valueY += (int) ((maxValue - values.get(i)) * scale);
		    }else {
		    	valueY += (int) (maxValue * scale);
		    	height = -height;
		    }

		    g2.setColor(colors[colorCount]);
		    colorCount++;
		    if (colorCount == colors.length) colorCount = 0;
		    g2.fillRect(valueX, valueY, barWidth - 2, height);
		    
		    g2.setColor(Color.black);
		    g2.drawRect(valueX, valueY, barWidth - 2, height);
		    int labelWidth = labelFontMetrics.stringWidth(ids.get(i));
		    x = i * barWidth + (barWidth - labelWidth) / 2;
		    g2.drawString(ids.get(i), x, y);
		}
	}
}
