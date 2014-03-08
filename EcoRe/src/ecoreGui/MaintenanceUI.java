package ecoreGui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ecore.RCM;

public class MaintenanceUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private RCM rcm;
	private CardLayout cards;
	private JPanel cardPanel;
	private JTextField cash, coupon;
	private String state, weight, capacity, currentCash, currentCoupons;
	private JLabel statusLabel;
	
	public MaintenanceUI(RCM rcm, final CardLayout cards, final JPanel cardPanel)
	{
		this.rcm = rcm;
		this.cards = cards;
		this.cardPanel = cardPanel;
		statusLabel = new JLabel("");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setFont(new Font("Sans Serif", Font.BOLD, 14));
	}
	
	private void display(){		
		updateStatus();
		
		JLabel title = new JLabel("Machine: " + rcm.getID());
		title.setFont(new Font("Sans Serif", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		statusLabel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Status"));
		statusLabel.setPreferredSize(new Dimension(150, 150));
		JPanel statusWrapper = new JPanel();
		statusWrapper.add(Box.createHorizontalGlue());
		statusWrapper.add(statusLabel);
		statusWrapper.add(Box.createHorizontalGlue());
		
		JPanel emptyWrapper = new JPanel();
		emptyWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel emptyLabel = new JLabel("Remove Recyclables:");
		JButton empty = new JButton("Empty");
		empty.setMaximumSize(new Dimension(70, 30));
		empty.setAlignmentX(SwingConstants.RIGHT);
		empty.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				rcm.empty();
				updateStatus();
			}
		});
		emptyWrapper.add(emptyLabel);
		emptyWrapper.add(empty);
		
		JPanel cashWrapper = new JPanel();
		cashWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
		JPanel cashPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel cashLabel = new JLabel("Add Cash");
		cash = new JTextField(10);
		JButton addCash = new JButton("Add");
		addCash.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					double add = Double.parseDouble(cash.getText().trim());
					rcm.addCash(add);
					updateStatus();
				}catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Please enter numbers only.", "Add Cash Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cashPanel.add(cashLabel);
		cashPanel.add(cash);
		cashPanel.add(addCash);
		cashWrapper.add(cashPanel);
		
		JPanel couponWrapper = new JPanel();
		couponWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
		JPanel couponPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel couponLabel = new JLabel("Add Coupon Paper");
		coupon = new JTextField(10);
		JButton addCoupon = new JButton("Add");
		addCoupon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					int add = Integer.parseInt(coupon.getText().trim());
					rcm.addCoupon(add);
					updateStatus();
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Please a postive integer.", "Add Coupon Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		couponPanel.add(couponLabel);
		couponPanel.add(coupon);
		couponPanel.add(addCoupon);
		couponWrapper.add(couponPanel);
		
		JPanel wrapper = new JPanel(new BorderLayout());
		JPanel options = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 0;
		options.add(cashWrapper, cons);
		cons.gridy = 1;
		options.add(couponWrapper, cons);
		cons.gridy = 2;
		options.add(emptyWrapper, cons); 
		wrapper.add(options, BorderLayout.CENTER);
		
		JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
		JButton done = new JButton("Exit");
		done.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.next(cardPanel);
				
			}
		});
		buttonWrapper.add(done);

		add(title);
		add(Box.createRigidArea(new Dimension(0,50)));
		add(statusWrapper);
		add(Box.createVerticalGlue());
		add(wrapper);
		add(Box.createVerticalGlue());
		add(buttonWrapper);
	}
	
	public void setRCM(RCM rcm){
			this.rcm = rcm;
			removeAll();
			display();
	}
	
	private void updateStatus(){
		state = rcm.getState().toString();
		weight = String.valueOf(rcm.getWeight()) + " lb";
		capacity = String.valueOf(rcm.getCapacity()) + " lb";
		currentCash = "$" + String.valueOf(rcm.getCash());
		currentCoupons = String.valueOf(rcm.getCouponPaper());
		
		statusLabel.setText("<html>State: " + state + "<br>Weight: " + weight + "<br>Capacity: " + capacity + "<br>Cash: " + currentCash + "<br>Coupons: " + currentCoupons + "</html>");
	}
		
}