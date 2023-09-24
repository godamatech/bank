//  #################### MAIN-MENU


package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import myResources.DbConnection;
import myResources.GridbagLayout;

public class Menu extends JFrame implements ActionListener {
	JPanel panel;
	JLabel lblTitle;
	
//	JButton btnDeposit ;
	JButton btnWithdrawal;                        
	JButton btnTransfer;
	JButton btnAirtime;
	JButton btnCheckBalance;
	JButton btnChangePin;
	JButton btnOpenAcct;
	JButton btnClose;
	JTextField txtCardID;
	JTextField txtCardOwner;
	
	PreparedStatement ps;
	ResultSet rs;
	private String curCardNo;
	private String userName;
	
	
	public Menu(String cardNo) {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		curCardNo = cardNo;
		lblTitle = new JLabel("user");
				
//		btnDeposit = new JButton("Deposit");
		btnWithdrawal = new JButton("< Withdraw");
		btnTransfer = new JButton("Transfer >");
		btnAirtime = new JButton("< Buy Airtime");
		btnCheckBalance = new JButton("Balance Enquiry >");
		btnChangePin = new JButton("< Change PIN");
		btnOpenAcct = new JButton("Open Account >");
		btnClose = new JButton("< Cancel >");
	
		Authentication auth = new Authentication();
		
		GridbagLayout g = new GridbagLayout();
		
		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		
//		g.addComp(panel, btnDeposit, 0, 1, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnWithdrawal, 0, 1, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnTransfer, 1, 1, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnAirtime, 0, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnCheckBalance, 1, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnChangePin, 0, 3, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnOpenAcct, 1, 3, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 1, 4, 2, 1, GridBagConstraints.CENTER);
		
		lblTitle.setForeground(Color.blue);
		
//		btnDeposit.setForeground(Color.blue);
		btnWithdrawal.setForeground(Color.blue);
		btnAirtime.setForeground(Color.blue);
		btnCheckBalance.setForeground(Color.blue);
		btnTransfer.setForeground(Color.blue);
		btnChangePin.setForeground(Color.blue);
		btnOpenAcct.setForeground(Color.blue);;
		btnClose.setForeground(Color.blue);

		btnClose.setBackground(Color.GRAY);
		
		panel.setBackground(Color.green);
		
		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		btnWithdrawal.setFont(bigFont);
		btnAirtime.setFont(bigFont);
		btnChangePin.setFont(bigFont);
		btnCheckBalance.setFont(bigFont);
		btnTransfer.setFont(bigFont);
		btnClose.setFont(bigFont);
		btnOpenAcct.setFont(bigFont);
		lblTitle.setFont(normalFont);
				
		
		this.setVisible(true);
		this.setSize(550,400);
		this.setTitle("Transactions Menu");
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.revalidate();
		
		btnAirtime.addActionListener(this);
		btnChangePin.addActionListener(this);
		btnCheckBalance.addActionListener(this);
		btnWithdrawal.addActionListener(this);
//		btnDeposit.addActionListener(this);
		btnTransfer.addActionListener(this);
		btnOpenAcct.addActionListener(this);
		btnClose.addActionListener(this);
		
		displayUserName();
				
	}//end of constructor
	
	DbConnection db = new DbConnection();
	public void displayUserName() {
		
		try {
			int convertedCurCardNo = Integer.parseInt(curCardNo);
			db.connectToDb();
			
			try {
				ps = db.getCon().prepareStatement("select name from customer where customerId=?");
				ps.setInt(1, convertedCurCardNo);

				rs = ps.executeQuery();
				
				if (rs.next()) {
					lblTitle.setText("Hi " +rs.getString("name"));
				}else {
					lblTitle.setText("Unknown User");
				}
				
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}

		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Card Number is empty!", "Main menu Message",
					JOptionPane.WARNING_MESSAGE);
		}
		
	}//end of displayUserName() method

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			this.dispose();
		}else if(e.getSource()==btnWithdrawal) {
			new WithdrawAccountType(curCardNo);
			this.dispose();
		}else if(e.getSource()==btnTransfer) {
			new TransferAccountNum(curCardNo);
//			this.dispose();
		}else if(e.getSource()==btnAirtime) {
//			new AirtimeTopUp();
			new NetworkType();
			this.dispose();
		}else if(e.getSource()==btnCheckBalance) {
			new Balance(curCardNo);
			this.dispose();
		}else if(e.getSource()==btnChangePin) {
			new UpdatePin();
			this.dispose();
		}else if(e.getSource()==btnOpenAcct) {
			new Customer();
			this.dispose();
		}
		
	}
}
