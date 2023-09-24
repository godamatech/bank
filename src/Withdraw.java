

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import myResources.DbConnection;
import myResources.GridbagLayout;

public class Withdraw extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle;
	JTextField txtAmount;
	JTextField txtAccountNo;

	JButton btnWithdraw;

	JButton btnClose;
	
	PreparedStatement ps;
	ResultSet rs;
	
	DbConnection db = new DbConnection();
	double curBal = 0.0;

	public Withdraw() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		lblTitle = new JLabel("*Withdrawal Amount should be in Figures*");
		txtAmount = new JTextField(10);
		
		txtAccountNo = new JTextField(10);

		btnWithdraw = new JButton("Withdraw");
		btnClose = new JButton("Close");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, txtAmount, 0, 1, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, txtAccountNo, 1, 1, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnWithdraw, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 1, 2, 1, 1, GridBagConstraints.WEST);

		lblTitle.setForeground(Color.blue);

		btnWithdraw.setForeground(Color.blue);
		btnClose.setBackground(Color.gray);
		panel.setBackground(Color.green);
		
		txtAmount.setText("Amount-in-Naira");
		txtAccountNo.setText("Account-No");

		this.setVisible(true);
		this.setSize(350, 170);
		this.setTitle("Withdrawal Transaction");
		this.add(panel);
		this.setLocationRelativeTo(null);

		btnClose.addActionListener(this);
		btnWithdraw.addActionListener(this);
		
	}// end of constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		db.connectToDb();
		if(e.getSource()==btnClose) {
			this.dispose();
		}else if(e.getSource()==btnWithdraw) {
			String amountToWithdraw = txtAmount.getText().toString();
			String acctNo = txtAccountNo.getText().toString();
			
			if(amountToWithdraw.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Amount is Required in the Input Space Provided!", "Withdrawal Warning Message", JOptionPane.WARNING_MESSAGE);
			}else {
				try {
					Double.parseDouble(amountToWithdraw);
					
					
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,  "Withdrawal Amount must be in figures!", "Withdral validation Message", JOptionPane.WARNING_MESSAGE);
				}
				double amount = Double.parseDouble(amountToWithdraw);
				
				try {
					ps = db.getCon().prepareStatement("SELECT balance FROM account WHERE accountNo=?");
					ps.setString(1, acctNo);
					rs = ps.executeQuery();
					
					if (rs.next()) {
						curBal = rs.getDouble("balance");
						
						if(curBal >= amount) {
							curBal -= amount;	// Payment Gateway
							JOptionPane.showMessageDialog(null,  "Please Take your cash!", "Withdral Success Message", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
						}else {
							JOptionPane.showMessageDialog(null,  "Insufficient Fund!", "Withdral validation Message", JOptionPane.WARNING_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null,  "Yello! You are not our Registered Customers. ", "Withdral validation Message", JOptionPane.ERROR_MESSAGE);
					}
					
					
				} catch (SQLException e1) {
					System.out.println("Error occured while fetching Balance"+e1);
				}
				
				
			}
		}
		
	}
	public static void main(String[] args) {
		new Withdraw();
	}
}// end of class
