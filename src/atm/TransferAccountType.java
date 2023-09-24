package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import myResources.GridbagLayout;

public class TransferAccountType extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle, lblButtomInstruction;
	
	JButton btnSavings;                        
	JButton btnCurrent;
	JButton btnCredit;
	JButton btnClose;
	
	private String acctType;


	public TransferAccountType() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		lblTitle = new JLabel("Please Select Your Account Type");
		lblButtomInstruction = new JLabel("Click 'Cancel' to Terminate");
				
		btnSavings = new JButton("SAVINGS >");
		btnCurrent = new JButton("CURRENT >");
		btnCredit = new JButton("CREDIT >");
		btnClose = new JButton("< Cancel >");
		
		GridbagLayout g = new GridbagLayout();
		
		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		
		g.addComp(panel, btnSavings, 0, 1, 2, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnCurrent, 0, 2, 2, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnCredit, 0, 3, 2, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 1, 4, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, lblButtomInstruction, 1, 5, 2, 1, GridBagConstraints.CENTER);
		
		lblTitle.setForeground(Color.blue);
		lblButtomInstruction.setForeground(Color.blue);
		btnSavings.setForeground(Color.blue);
		btnCurrent.setForeground(Color.blue);
		btnCredit.setForeground(Color.blue);
		btnClose.setForeground(Color.blue);
		
		btnClose.setBackground(Color.GRAY);
		
		panel.setBackground(Color.green);
		
		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		btnSavings.setFont(bigFont);
		btnCurrent.setFont(bigFont);
		btnCredit.setFont(bigFont);
		btnClose.setFont(bigFont);
		lblTitle.setFont(normalFont);
		lblButtomInstruction.setFont(normalFont);
				
		
		this.setVisible(true);
		this.setSize(550,400);
		this.setTitle("Account Type");
		this.add(panel);
		this.setLocationRelativeTo(null);
		
		btnSavings.addActionListener(this);
		btnCurrent.addActionListener(this);
		btnCredit.addActionListener(this);
		btnClose.addActionListener(this);
		
		
	}//end of constructor
	
	public String getAcctType() {
		return acctType;
	}


	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			this.dispose();
		}else if(e.getSource()==btnSavings) {
			this.setAcctType("Savings");
//			new TransferAmount();
			this.dispose();
		}else if(e.getSource()==btnCurrent) {
			this.setAcctType("Current");
//			new TransferAmount();
			this.dispose();
		}else if(e.getSource()==btnCredit) {
			this.setAcctType("Credit");
//			new TransferAmount();
//			this.dispose();
		}
		System.out.println(this.getAcctType());
	}
	
}
