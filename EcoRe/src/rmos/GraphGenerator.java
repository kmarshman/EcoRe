package rmos;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class GraphGenerator extends JPanel{
	public GraphGenerator(){
		setLayout(new BorderLayout());
		
		JPanel control = new JPanel(new BorderLayout());
		control.setBorder(new EmptyBorder(20, 10, 20, 10) );
		
		String[] metricOptions = {"Value", "Weight"};
		JComboBox metrics = new JComboBox(metricOptions);
		
		String[] timeframeOptions = {"Day", "Week", "Month"};
		JComboBox timeframes = new JComboBox(timeframeOptions);
		
		control.add(metrics, BorderLayout.NORTH);
		control.add(timeframes, BorderLayout.SOUTH);
		
		add(control, BorderLayout.EAST);
		
		JPanel graph = new JPanel();
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		graph.setBackground(new Color(0x00FF00));
		
		add(graph, BorderLayout.CENTER);
		
	}
}
