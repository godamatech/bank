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

public class Balance extends JFrame implements ActionListener {
	JPanel panel;
	JLabel lblTitle;
	JTextField txtBalance;
	JButton btnClose;

	String curCardNo;
	PreparedStatement ps;
	ResultSet rs;

	DbConnection db = new DbConnection();

	public Balance(String cardNo) {
		curCardNo = cardNo;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		lblTitle = new JLabel("Your Current Balance is: ");
		txtBalance = new JTextField(8);

		btnClose = new JButton("Close");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, txtBalance, 0, 1, 1, 1, GridBagConstraints.CENTER);
		g.addComp(panel, btnClose, 0, 2, 1, 1, GridBagConstraints.CENTER);

		lblTitle.setForeground(Color.blue);

		btnClose.setBackground(Color.gray);
		panel.setBackground(Color.green);

		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 20);

		lblTitle.setFont(bigFont);
		txtBalance.setFont(bigFont);
		btnClose.setFont(bigFont);

		txtBalance.setText(" 0.00");
		txtBalance.setEditable(false);

		this.setVisible(true);
		this.setSize(350, 240);
		this.setTitle("Account Balance");
		this.add(panel);
		this.setLocationRelativeTo(null);

		btnClose.addActionListener(this);
		
		displayBal();

	}// end of constructor
//*

	
	@Override
	public void actionPerformed(ActionEvent e) {
		db.connectToDb();
		if (e.getSource() == btnClose) {
			this.dispose();
		}

	}// end of action
	
	public void displayBal() {
		try {
			// Integer.parseInt(txtCardNo.getText());
			int convertedCurCardNo = Integer.parseInt(curCardNo);
			db.connectToDb();
			try {
				ps = db.getCon().prepareStatement("select balance from account where accountId=?");
				ps.setInt(1, convertedCurCardNo);

				rs = ps.executeQuery();
//				System.err.println(rs);
				if (rs.next()) {
					String resultantBal =Integer.toString(rs.getInt("balance"));
					txtBalance.setText(resultantBal);
				}

			} catch (Exception e) {
				System.out.println("Error: " + e);
			}

		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Card Number is empty!", "Balance Message",
					JOptionPane.WARNING_MESSAGE);
		}
		
	}//end of displayBalance() method
	
}
