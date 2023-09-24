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

public class WithdrawOtherAmount extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle, lblButtomInstruction;

	JTextField txtAmount;
	JButton btnWithdraw, btnClose;

	private String curCardNo;
	private double amount;
	private PreparedStatement ps;
	private ResultSet rs;

	DbConnection db = new DbConnection();

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public WithdrawOtherAmount(String cardNo) {
		curCardNo = cardNo;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		lblTitle = new JLabel("Enter Amount in multiples of 1000 (e.g 2000, 5000)");
		lblButtomInstruction = new JLabel("Click 'Cancel' to Terminate");

		txtAmount = new JTextField(5);

		btnWithdraw = new JButton("< Withdraw ");
		btnClose = new JButton(" Cancel >");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);

		g.addComp(panel, txtAmount, 0, 1, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, btnWithdraw, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 1, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, lblButtomInstruction, 0, 3, 2, 1, GridBagConstraints.CENTER);

		lblTitle.setForeground(Color.blue);
		lblButtomInstruction.setForeground(Color.blue);
		txtAmount.setForeground(Color.blue);
		btnWithdraw.setForeground(Color.blue);
		btnClose.setForeground(Color.blue);

		btnClose.setBackground(Color.GRAY);

		panel.setBackground(Color.green);

		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		txtAmount.setFont(bigFont);
		btnWithdraw.setFont(bigFont);
		btnClose.setFont(bigFont);
		lblTitle.setFont(normalFont);
		lblButtomInstruction.setFont(normalFont);

		txtAmount.setText("0.00");
		txtAmount.requestFocusInWindow();

		this.setVisible(true);
		this.setSize(550, 300);
		this.setTitle("Type in your Withdrawal Amount");
		this.add(panel);
		this.setLocationRelativeTo(null);

		btnWithdraw.addActionListener(this);
		btnClose.addActionListener(this);

	}// end of constructor

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnClose) {
			this.dispose();
		} else if (e.getSource() == btnWithdraw) {
			db.connectToDb();
			String a = txtAmount.getText();
			try {
				this.amount = Double.parseDouble(a);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Amount must be in Figures(e.g 1000)", "Amount warning message",
						JOptionPane.WARNING_MESSAGE);
			}
			if (this.amount == 0) {
				JOptionPane.showMessageDialog(null, "Amount cannot be Zero!", "Amount warning message",
						JOptionPane.WARNING_MESSAGE);
			} else if (this.amount % 1000 != 0) {
				JOptionPane.showMessageDialog(null,
						"Amount must be in multiples of 1000 (e.g 2000, 5000) Figures(e.g 1000)",
						"Amount warning message", JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					ps = db.getCon().prepareStatement("SELECT balance FROM account WHERE accountID=?");
					ps.setInt(1, Integer.parseInt(curCardNo));
					rs = ps.executeQuery();

					if (rs.next()) {
						double curBal = rs.getDouble("balance");

						if (curBal >= amount) {
							curBal -= amount; // Payment Gateway
							
							
							ps = db.getCon().prepareStatement("update account set balance=? where accountId=?");
							ps.setDouble(1, curBal);
							ps.setInt(2, Integer.parseInt(curCardNo));
							ps.executeUpdate();
												
							JOptionPane.showMessageDialog(null, "Your have Successfully withdrawn N" + amount +". Please Take your cash!", "Withdral Success Message",
									JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							
						} else {
							JOptionPane.showMessageDialog(null, "Insufficient Fund!", "Withdral validation Message",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Yello! You are not our Registered Customers. ",
								"Withdral validation Message", JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e1) {
					System.out.println("Error occured while fetching Balance" + e1);
				}finally {
					try {
						db.getCon().close();
					} catch (SQLException e1) {
						System.out.println("Failed to close connection");
					}
				}
			}//end of else
		} // end of btnWithdrawal

	}// end of actionPerformed()

}
