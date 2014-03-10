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
		setBackground(new Color(148, 255, 123));
		
		JPanel graphAxis = new JPanel(new GridBagLayout());
		graphAxis.setBackground(new Color(148, 255, 123));
		graphAxis.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;

		cons.gridx = 1;
		cons.gridy = 1;
		String[] metricOptions = {"Value", "Weight"};
		metrics = new JComboBox<String>(metricOptions);
		metrics.setBackground(Color.WHITE);
		graphAxis.add(metrics, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		JLabel blank = new JLabel(" ");
		graphAxis.add(blank, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		String[] timeframeOptions = {"Day", "Week", "Month"};
		timeframes = new JComboBox<String>(timeframeOptions);
		timeframes.setBackground(Color.WHITE);
		graphAxis.add(timeframes, cons);
		
		cons.gridx = 1;
		cons.gridy = 4;
		graphAxis.add(blank, cons);
		
		cons.gridx = 1;
		cons.gridy = 5;
		JButton create = new JButton("Go");
		create.setBackground(Color.WHITE);
		create.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String metric = metrics.getItemAt(metrics.getSelectedIndex());
				String time = timeframes.getItemAt(timeframes.getSelectedIndex());
				if(metric.equals("Day")){
					rmos.setChart((metric + " Collected Today"), metric, time);
				}else{
					rmos.setChart((metric + " Collected This " + time), metric, time);
				}
			}
		});
		graphAxis.add(create, cons);
		
		
		add(graphAxis, BorderLayout.EAST);
		
		BarGraph graph = new BarGraph(rmos);
		rmos.addObserver(graph);
		add(graph, BorderLayout.CENTER);
		
	}
}
