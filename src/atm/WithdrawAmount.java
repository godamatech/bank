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

import myResources.DbConnection;
import myResources.GridbagLayout;

public class WithdrawAmount extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle, lblButtomInstruction;

	JButton btn1000, btn2000, btn5000, btn10_000, btn20_000, btn100_000;
	JButton btnOthers, btnClose;

	DbConnection db = new DbConnection();
	PreparedStatement ps;
	ResultSet rs;
	private String curCardNo;
	private double amount;

	public WithdrawAmount(String cardNo) {
		curCardNo = cardNo;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		lblTitle = new JLabel("Please Select Amount");
		lblButtomInstruction = new JLabel("Click 'Cancel' to Terminate");

		btn1000 = new JButton("< 1000 ");
		btn2000 = new JButton("< 2000 ");
		btn5000 = new JButton("< 5000 ");
		btnOthers = new JButton("< Others");

		btn10_000 = new JButton("10,000 >");
		btn20_000 = new JButton("20,000 >");
		btn100_000 = new JButton("100,000 >");
		btnClose = new JButton(" Cancel >");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, btn1000, 0, 1, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btn2000, 0, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btn5000, 0, 3, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnOthers, 0, 4, 1, 1, GridBagConstraints.WEST);

		g.addComp(panel, btn10_000, 1, 1, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btn20_000, 1, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btn100_000, 1, 3, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 1, 4, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, lblButtomInstruction, 0, 5, 2, 1, GridBagConstraints.CENTER);

		lblTitle.setForeground(Color.blue);
		lblButtomInstruction.setForeground(Color.blue);
		btn1000.setForeground(Color.blue);
		btn2000.setForeground(Color.blue);
		btn5000.setForeground(Color.blue);
		btn10_000.setForeground(Color.blue);
		btn20_000.setForeground(Color.blue);
		btn100_000.setForeground(Color.blue);
		btnOthers.setForeground(Color.blue);
		btnClose.setForeground(Color.blue);

		btnClose.setBackground(Color.GRAY);

		panel.setBackground(Color.green);

		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		btn1000.setFont(bigFont);
		btn2000.setFont(bigFont);
		btn5000.setFont(bigFont);
		btn10_000.setFont(bigFont);
		btn20_000.setFont(bigFont);
		btn100_000.setFont(bigFont);
		btnOthers.setFont(bigFont);
		btnClose.setFont(bigFont);
		lblTitle.setFont(normalFont);
		lblButtomInstruction.setFont(normalFont);

		this.setVisible(true);
		this.setSize(550, 400);
		this.setTitle("Amount to Withdraw");
		this.add(panel);
		this.setLocationRelativeTo(null);

		btn1000.addActionListener(this);
		btn2000.addActionListener(this);
		btn5000.addActionListener(this);
		btnOthers.addActionListener(this);
		btn10_000.addActionListener(this);
		btn20_000.addActionListener(this);
		btn100_000.addActionListener(this);
		btnClose.addActionListener(this);

	}// end of constructor

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose) {
			this.dispose();
		} else if (e.getSource() == btn1000) {
			this.setAmount(1000.00);
			withdraw(getAmount());
			this.dispose();
		} else if (e.getSource() == btn2000) {
			this.setAmount(2000.00);
			withdraw(getAmount());
			this.dispose();
		} else if (e.getSource() == btn5000) {
			this.setAmount(5000.00);
			withdraw(getAmount());
			this.dispose();
		} else if (e.getSource() == btn10_000) {
			this.setAmount(10000.00);
			withdraw(getAmount());
			this.dispose();
		} else if (e.getSource() == btn20_000) {
			this.setAmount(20000.00);
			withdraw(getAmount());
			this.dispose();
		} else if (e.getSource() == btn100_000) {
			this.setAmount(100000.00);
			withdraw(getAmount());
			this.dispose();
		} else if (e.getSource() == btnOthers) {
			new WithdrawOtherAmount(curCardNo);
			this.dispose();
		}
	}


	public void withdraw(double amount) {// getAmount()
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
										
					JOptionPane.showMessageDialog(null, "Your have Successfully withdrawn N " + amount +". Please Take your cash!", "Withdral Success Message",
							JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Insufficient Fund!", "Withdral validation Message",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Yello! You are not our Registered Customer. ",
						"Withdral validation Message", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e1) {
			System.out.println("Error occured while fetching Balance" + e1);
		}
	}
}
