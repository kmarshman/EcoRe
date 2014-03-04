package ecoreGui.control;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ecore.RMOS;
import ecoreGui.view.BarGraph;
import ecoreGui.view.GraphicDisplay;

/**
 * Usage statistics visualization panel
 * @author Kelsey
 *
 */
public class GraphGenerator extends GraphicDisplay{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 */
	public GraphGenerator(){
		new GraphGenerator(new RMOS());
	}
	
	/**
	 * Creates new statistics graph panel
	 */
	public GraphGenerator(RMOS rmos){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(500, 1000));
		
		JPanel graphAxis = new JPanel(new GridBagLayout());
		graphAxis.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;

		cons.gridx = 1;
		cons.gridy = 1;
		String[] metricOptions = {"Value", "Weight"};
		JComboBox<String> metrics = new JComboBox<String>(metricOptions);
		graphAxis.add(metrics, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		JLabel blank = new JLabel(" ");
		graphAxis.add(blank, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		String[] timeframeOptions = {"Day", "Week", "Month"};
		JComboBox<String> timeframes = new JComboBox<String>(timeframeOptions);
		graphAxis.add(timeframes, cons);
		
		add(graphAxis, BorderLayout.EAST);
		
		BarGraph graph = new BarGraph(rmos, "Chart Title");	
		add(graph, BorderLayout.CENTER);
		
	}
}
