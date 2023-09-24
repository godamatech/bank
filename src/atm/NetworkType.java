package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import myResources.GridbagLayout;

public class NetworkType extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle, lblButtomInstruction;
	
	JButton btnMtn, btnAirtel, btn9mobile, btnGlo, btnVisafone, btnSmile, btnClose;
	
	private String networkType;


	public NetworkType() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		lblTitle = new JLabel("Please Select Your Network");
		lblButtomInstruction = new JLabel("Click 'Cancel' to Terminate");
				
		btnVisafone = new JButton("< VISAFONE ");
		btn9mobile = new JButton("< 9-MOBILE ");
		btnSmile = new JButton("< SMILE ");
		
		btnMtn = new JButton("MTN >");
		btnAirtel = new JButton("AIRTEL >");
		btnGlo = new JButton("GLO >");
		
		btnClose = new JButton("< Cancel >");
		
		GridbagLayout g = new GridbagLayout();
		
		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		
		g.addComp(panel, btnVisafone, 0, 1, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btn9mobile, 0, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnSmile, 0, 3, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnMtn, 1, 1, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnAirtel, 1, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnGlo, 1, 3, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 0, 4, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, lblButtomInstruction, 0, 5, 2, 1, GridBagConstraints.CENTER);
		
		lblTitle.setForeground(Color.blue);
		lblButtomInstruction.setForeground(Color.blue);
		btnVisafone.setForeground(Color.blue);
		btn9mobile.setForeground(Color.blue);
		btnSmile.setForeground(Color.blue);
		btnMtn.setForeground(Color.blue);
		btnAirtel.setForeground(Color.blue);
		btnGlo.setForeground(Color.blue);
		btnClose.setForeground(Color.blue);
		
		btnClose.setBackground(Color.GRAY);
		
		panel.setBackground(Color.green);
		
		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		btnVisafone.setFont(bigFont);
		btn9mobile.setFont(bigFont);
		btnSmile.setFont(bigFont);
		btnMtn.setFont(bigFont);
		btnAirtel.setFont(bigFont);
		btnGlo.setFont(bigFont);
		btnClose.setFont(bigFont);
		lblTitle.setFont(normalFont);
		lblButtomInstruction.setFont(normalFont);
				
		
		this.setVisible(true);
		this.setSize(550,420);
		this.setTitle("Account Type");
		this.add(panel);
		this.setLocationRelativeTo(null);
		
		btnVisafone.addActionListener(this);
		btn9mobile.addActionListener(this);
		btnSmile.addActionListener(this);
		btnMtn.addActionListener(this);
		btnAirtel.addActionListener(this);
		btnGlo.addActionListener(this);
		btnClose.addActionListener(this);
		
		
	}//end of constructor
	
	public String getNetworkType() {
		return this.networkType;
	}


	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			this.dispose();
		}else if(e.getSource()==btnVisafone) {
			this.setNetworkType("Visafone");
			new AirtimeAmount();
			this.dispose();
		}else if(e.getSource()==btn9mobile) {
			this.setNetworkType("9mobile");
			new AirtimeAmount();
			this.dispose();
		}else if(e.getSource()==btnSmile) {
			this.setNetworkType("Smile");
			new AirtimeAmount();
			this.dispose();
		}else if(e.getSource()==btnMtn) {
			this.setNetworkType("Mtn");
			new AirtimeAmount();
			this.dispose();
		}else if(e.getSource()==btnAirtel) {
			this.setNetworkType("Airtel");
			new AirtimeAmount();
			this.dispose();
		}else if(e.getSource()==btnGlo) {
			this.setNetworkType("Glo");
			new AirtimeAmount();
			this.dispose();
		}
		System.out.println(this.getNetworkType());
	}
	
}
