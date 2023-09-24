package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import myResources.GridbagLayout;

public class TransferAccountNum extends JFrame implements ActionListener {
	JPanel panel;
	JLabel lblTitle, lblButtomInstruction;	
	JTextField txtAcctNum;
	JButton btnProceed, btnClose;
	
	private String acctNo;
	private String curCardNo;
	
	public String getAcctNo() {
		return this.acctNo;
	}
	
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	public TransferAccountNum(String cardNo) {
		curCardNo = cardNo;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		lblTitle = new JLabel("Enter Beneficiary's Account Number");
		lblButtomInstruction = new JLabel("Click 'Cancel' to Terminate");
		
		txtAcctNum = new JTextField(7);
		
		btnProceed = new JButton("< Proceed ");
		btnClose = new JButton(" Cancel >");
		
		GridbagLayout g = new GridbagLayout();
		
		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		
		g.addComp(panel, txtAcctNum, 0, 1, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, btnProceed, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 1, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, lblButtomInstruction, 0, 3, 2, 1, GridBagConstraints.CENTER);
		
		lblTitle.setForeground(Color.blue);
		lblButtomInstruction.setForeground(Color.blue);
		txtAcctNum.setForeground(Color.blue);
		btnProceed.setForeground(Color.blue);
		btnClose.setForeground(Color.blue);
		
		btnClose.setBackground(Color.GRAY);
		
		panel.setBackground(Color.green);
		
		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		txtAcctNum.setFont(bigFont);
		btnProceed.setFont(bigFont);
		btnClose.setFont(bigFont);
		lblTitle.setFont(normalFont);
		lblButtomInstruction.setFont(normalFont);
				
		txtAcctNum.setText("0123456789");
		txtAcctNum.requestFocusInWindow();
		
		this.setVisible(true);
		this.setSize(550,300);
		this.setTitle("Beneficiary Account Number");
		this.add(panel);
		this.setLocationRelativeTo(null);
		
		
		btnProceed.addActionListener(this);
		btnClose.addActionListener(this);
		
		
	}//end of constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			this.dispose();
		}else if(e.getSource()==btnProceed) {
			String a = txtAcctNum.getText();
			try {
				Double.parseDouble(a);
				
				new TransferAmount(curCardNo, getAcctNo());
				this.dispose();
				
			}catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Account Number must be in Figures(e.g 1234567890)", "Account Number warning message", JOptionPane.WARNING_MESSAGE);
			}
			
			this.setAcctNo(txtAcctNum.getText());
			
//			this.amount = Double.parseDouble(a);			
//			if(this.amount == 0) {
//				JOptionPane.showMessageDialog(null, "Amount cannot be Zero!", "Amount warning message", JOptionPane.WARNING_MESSAGE);
//			}else if(this.amount % 1000 != 0) {
//				JOptionPane.showMessageDialog(null, "Amount must be in multiples of 1000 (e.g 2000, 5000) Figures(e.g 1000)", "Amount warning message", JOptionPane.WARNING_MESSAGE);
//			} 
//			else if(this.amount >= balance) {
//				balance = this.amount ;
//			}
			
			this.dispose();
		}
	}
	
}
