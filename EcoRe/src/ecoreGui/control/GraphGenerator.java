package ecoreGui.control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ecore.RMOS;
import ecoreGui.view.BarGraph;

/**
 * Usage statistics visualization panel
 * @author Kelsey
 *
 */
public class GraphGenerator extends JPanel{

	private static final long serialVersionUID = 1L;
	private JComboBox<String> metrics;
	private JComboBox<String> timeframes;
	
	/**
	 * Default constructor
	 */
	public GraphGenerator(){
		new GraphGenerator(new RMOS());
	}
	
	/**
	 * Creates new statistics graph panel
	 */
	public GraphGenerator(final RMOS rmos){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(500, 1000));
		
		JPanel graphAxis = new JPanel(new GridBagLayout());
		graphAxis.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;

		cons.gridx = 1;
		cons.gridy = 1;
		String[] metricOptions = {"Value", "Weight"};
		metrics = new JComboBox<String>(metricOptions);
		graphAxis.add(metrics, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		JLabel blank = new JLabel(" ");
		graphAxis.add(blank, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		String[] timeframeOptions = {"Day", "Week", "Month"};
		timeframes = new JComboBox<String>(timeframeOptions);
		graphAxis.add(timeframes, cons);
		
		cons.gridx = 1;
		cons.gridy = 4;
		graphAxis.add(blank, cons);
		
		cons.gridx = 1;
		cons.gridy = 5;
		JButton create = new JButton("Go");
		create.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String metric = metrics.getItemAt(metrics.getSelectedIndex());
				String time = timeframes.getItemAt(timeframes.getSelectedIndex());
				rmos.setChart((metric + " by " + time), metric, time);
			}
		});
		graphAxis.add(create, cons);
		
		
		add(graphAxis, BorderLayout.EAST);
		
		BarGraph graph = new BarGraph(rmos);
		rmos.addObserver(graph);
		add(graph, BorderLayout.CENTER);
		
	}
}
