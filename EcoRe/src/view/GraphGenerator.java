package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Usage statistics visualization panel
 * @author Kelsey
 *
 */
public class GraphGenerator extends GraphicDisplay{

	private static final long serialVersionUID = 1L;

	/**
	 * Creates new graph panel
	 * TODO: this class is still in process
	 */
	public GraphGenerator(){
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
		
		BarGraph graph = new BarGraph();	
		add(graph, BorderLayout.CENTER);
		
	}
}
