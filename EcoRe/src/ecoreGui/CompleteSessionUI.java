//CompleteSessionUI
package ecoreGui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import ecore.RCM;
import ecore.RMOS;

public class CompleteSessionUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private RCM rcm;
	private RMOS rmos;
	private CardLayout cards;
	private JPanel cardPanel;
	private String weight, value;
	private JLabel print, error, valueLabel;
	private JButton coupon, cash;


	public CompleteSessionUI(RMOS rmos, RCM rcm,final CardLayout cards, final JPanel cardPanel)
	{
		this.rcm = rcm;
		this.cards = cards;
		this.cardPanel = cardPanel;
		this.rmos = rmos;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		valueLabel = new JLabel("");
	}
	
	private void display()
	{
		rmos.rcmUpdate();
		setBackground(new Color(255,255,255));
		updateSession();
		
		if(rcm.getCash() < rcm.getSessionValue()){
			cash.setEnabled(false);
			error.setText("Out of cash");
		}
		if(rcm.getCouponPaper() < 1){
			coupon.setEnabled(false);
			error.setText("Out of coupons");
		}
		
		JLabel title = new JLabel("<html><font face=\"Garamond\" color=\"228b22\"><center>Woud you like to receive cash or coupon?</center></font></html>");
		title.setFont(new Font("Sans Serif", Font.BOLD, 14));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setHorizontalTextPosition(SwingConstants.CENTER);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		valueLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
		valueLabel.setForeground(new Color(34,139, 34));
		valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel cashWrapper = new JPanel();
		cashWrapper.setBackground(new Color(255,255,255));
		cashWrapper.setLayout(new BoxLayout(cashWrapper, BoxLayout.Y_AXIS));
		cash = new JButton("Cash");
		cash.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				print.setText(rcm.printCash());
				cash.setEnabled(false);
				coupon.setEnabled(false);
				rmos.rcmUpdate();
			}
		});
		cashWrapper.add(cash);
		
		JPanel couponWrapper = new JPanel();
		couponWrapper.setLayout(new BoxLayout(couponWrapper, BoxLayout.Y_AXIS));
		coupon = new JButton("Coupon");
		coupon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				print.setText(rcm.printCoupon());
				cash.setEnabled(false);
				coupon.setEnabled(false);
				rmos.rcmUpdate();
			}
		});
		couponWrapper.add(coupon);
		
		print = new JLabel("");
		print.setPreferredSize(new Dimension(500, 200));
		print.setMaximumSize(new Dimension(500, 200));
		print.setMinimumSize(new Dimension(500, 200));
		print.setBackground(Color.WHITE);
		print.setOpaque(true);
		print.setBorder(new LineBorder(Color.BLACK));
		print.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel options = new JPanel();
		options.setBackground(new Color(255,255,255));
		options.add(cashWrapper);
		options.add(Box.createRigidArea(new Dimension(30, 0)));
		options.add(Box.createHorizontalGlue());
		options.add(couponWrapper);
		
		JPanel printPanel = new JPanel();
		printPanel.setBackground(new Color(255,255,255));
		printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.Y_AXIS));
		printPanel.add(options);
		printPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		printPanel.add(print);
		printPanel.add(Box.createVerticalGlue());
		
		error = new JLabel("");
		error.setFont(new Font("Sans Serif", Font.BOLD, 14));
		error.setForeground(Color.RED);
		error.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel exitWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		exitWrapper.setBackground(new Color(255,255,255));
		JButton done = new JButton("Exit");
		done.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				rcm.finishSession();
				rmos.rcmUpdate();
				cards.show(cardPanel, "RCM Selector");
				
				//cards.next(cardPanel);
				//cards.next(cardPanel);
			}
		});
		exitWrapper.add(done);
		
		add(Box.createRigidArea(new Dimension(0,20)));
		add(valueLabel);
		add(Box.createVerticalGlue());
		add(title);
		add(Box.createVerticalGlue());
		add(error);
		add(Box.createVerticalGlue());
		add(printPanel);
		add(Box.createVerticalGlue());
		add(exitWrapper);
	}
	
	public void setRCM(RCM rcm){
		this.rcm =  rcm;
		removeAll();
		display();
	}
	
	public void updateSession(){
		weight = String.valueOf((rcm.getSessionAluminumWeight() + rcm.getSessionGlassWeight()));
		value = String.valueOf((rcm.getSessionValue()));
		
		valueLabel.setText("<html>You deposited " + weight + " lb for a total of $" + value);
	}
}