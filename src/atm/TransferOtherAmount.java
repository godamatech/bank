package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import myResources.GridbagLayout;
import myResources.DbConnection;

public class TransferOtherAmount extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle, lblButtomInstruction;
	
	JTextField txtAmount;
	JButton btnTransfer, btnClose;
	
	double amount;
	String curCardNo;
	PreparedStatement ps;
	ResultSet rs;
	DbConnection db = new DbConnection();
	
	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public TransferOtherAmount(String cardNo) {
		curCardNo = cardNo;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		lblTitle = new JLabel("Enter Amount in multiples of 1000 (e.g 2000, 5000)");
		lblButtomInstruction = new JLabel("Click 'Cancel' to Terminate");
		
		txtAmount = new JTextField(5);
		
		btnTransfer = new JButton("< Transfer ");
		btnClose = new JButton(" Cancel >");
		
		GridbagLayout g=  new GridbagLayout();
		
		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		
		g.addComp(panel, txtAmount, 0, 1, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, btnTransfer, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 1, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, lblButtomInstruction, 0, 3, 2, 1, GridBagConstraints.CENTER);
		
		lblTitle.setForeground(Color.blue);
		lblButtomInstruction.setForeground(Color.blue);
		txtAmount.setForeground(Color.blue);
		btnTransfer.setForeground(Color.blue);
		btnClose.setForeground(Color.blue);
		
		btnClose.setBackground(Color.GRAY);
		
		panel.setBackground(Color.green);
		
		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		txtAmount.setFont(bigFont);
		btnTransfer.setFont(bigFont);
		btnClose.setFont(bigFont);
		lblTitle.setFont(normalFont);
		lblButtomInstruction.setFont(normalFont);
				
		txtAmount.setText("0.00");
		txtAmount.requestFocusInWindow();
		
		this.setVisible(true);
		this.setSize(550,300);
		this.setTitle("Type in the Amount you want to Transfer");
		this.add(panel);
		this.setLocationRelativeTo(null);
		
		
		btnTransfer.addActionListener(this);
		btnClose.addActionListener(this);
		
		
	}//end of constructor
	
	public void transfer(double amount) {// getAmount()
		db.connectToDb();
		int convertedCurCardNo = Integer.parseInt(curCardNo);
		try {
			ps = db.getCon().prepareStatement("SELECT balance FROM account WHERE accountID=?");
			ps.setInt(1, convertedCurCardNo);
			rs = ps.executeQuery();

			if (rs.next()) {
				double curBal = rs.getDouble("balance");

				if (curBal >= amount) {
					curBal -= amount; // Payment Gateway
					
					ps = db.getCon().prepareStatement("update account set balance=? where accountId=?");
					ps.setDouble(1, curBal);
					ps.setInt(2, convertedCurCardNo);
					ps.executeUpdate();
										
					JOptionPane.showMessageDialog(null, "Your have Successfully Transfered N " + amount +". Please Take your cash!", "Transfer Success Message",
							JOptionPane.INFORMATION_MESSAGE);
					
					int option = JOptionPane.showConfirmDialog(this, "Do you want to perform another Transaction? ", "Warning", JOptionPane.OK_CANCEL_OPTION);
					if (option == 0) {
						new Authentication();
					}
					
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Insufficient Fund!", "Transfer validation Message",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Yello! This User is not our Registered Customer. ",
						"Transfer validation Message", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e1) {
			System.out.println("Error occured while fetching Balance" + e1);
		}
	}//end of the method transfer()
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			this.dispose();
		}else if(e.getSource()==btnTransfer) {
			String a = txtAmount.getText();
			try {
				Double.parseDouble(a);
			}catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Amount must be in Figures(e.g 1000)", "Amount warning message", JOptionPane.WARNING_MESSAGE);
			}
//			this.amount = Double.parseDouble(a);
//			if(this.amount == 0) {
//				JOptionPane.showMessageDialog(null, "Amount cannot be Zero!", "Amount warning message", JOptionPane.WARNING_MESSAGE);
//			}else if(this.amount % 1000 != 0) {
//				JOptionPane.showMessageDialog(null, "Amount must be in multiples of 1000 (e.g 2000, 5000) Figures(e.g 1000)", "Amount warning message", JOptionPane.WARNING_MESSAGE);
//			} 
//			else if(this.amount >= balance) {
//				balance = this.amount ;
//			}
//			new TransferAccountNum();
			this.dispose();
		}
		System.out.println(this.getAmount()+ " Transfered");
	}
	
}
