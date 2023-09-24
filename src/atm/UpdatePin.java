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

public class UpdatePin extends JFrame implements ActionListener {
	JPanel panel;
	JLabel lblTitle;
	JTextField txtCurrentPin, txtCardNo, txtNewPin1, txtNewPin2;
	JButton btnUpdate, btnClose;
	
	PreparedStatement ps;
	ResultSet rs;
		
	DbConnection db = new DbConnection();
	
	public UpdatePin() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		lblTitle = new JLabel("Note: Only Digits are required e.g 1234");
		txtCurrentPin = new JTextField(8);
		txtCardNo = new JTextField(8);
		txtNewPin1 = new JTextField(8);
		txtNewPin2 = new JTextField(8);

		btnUpdate = new JButton("Update");
		btnClose = new JButton("Close");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, txtCurrentPin, 0, 1, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, txtCardNo, 1, 1, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, txtNewPin1, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, txtNewPin2, 1, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnClose, 0, 3, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnUpdate, 1, 3, 1, 1, GridBagConstraints.WEST);

		lblTitle.setForeground(Color.blue);
		btnUpdate.setForeground(Color.blue);

		btnClose.setBackground(Color.gray);
		panel.setBackground(Color.green);
		
		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 20);
		
		lblTitle.setFont(bigFont);
		txtCardNo.setFont(bigFont);
		txtCurrentPin.setFont(bigFont);
		txtNewPin1.setFont(bigFont);
		txtNewPin2.setFont(bigFont);
		
		btnClose.setFont(bigFont);
		btnUpdate.setFont(bigFont);
		

		this.setVisible(true);
		this.setSize(500,350);
		this.setTitle("Change Password");
		this.add(panel);
		this.setLocationRelativeTo(null);

		txtCardNo.setText("Card-Number");
		txtCurrentPin.setText("Current-PIN");
		txtNewPin1.setText("New-PIN");
		txtNewPin2.setText("Confirm-PIN");

		btnClose.addActionListener(this);
		btnUpdate.addActionListener(this);

	}// end of constructor

	
	@Override
	public void actionPerformed(ActionEvent e) {
		db.connectToDb();
		if(e.getSource()==btnClose) {
			this.dispose();
		}else if(e.getSource()==btnUpdate) {
			
			try {
				Integer.parseInt(txtCurrentPin.getText());
				Integer.parseInt(txtCardNo.getText());
				Integer.parseInt(txtNewPin1.getText());
				
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null,  "*All fields must be in figures!", "PIN Update Message", JOptionPane.WARNING_MESSAGE);		
			}
			
			int curCardNo = Integer.parseInt(txtCardNo.getText());
			
			String curPin = txtCurrentPin.getText().toString();
			String newPass1 = txtNewPin1.getText().toString();
			String newPass2 = txtNewPin2.getText().toString();
			
			if(newPass1.equals(newPass2)) {

				try {
					ps = db.getCon().prepareStatement("select * from atmcard where atmCardId=? and pin=?");
					ps.setInt(1, curCardNo);
					ps.setString(2, curPin);
					
					rs = ps.executeQuery();
					if (rs.next()) {									
						PreparedStatement newPs = db.getCon().prepareStatement("update atmcard set pin=? where atmCardId=?");
						newPs.setString(1, newPass1);
						newPs.setInt(2, curCardNo);
						
						newPs.executeUpdate();
						
						JOptionPane.showMessageDialog(this, "Password Updated Successfully!", "Password Success Message", JOptionPane.INFORMATION_MESSAGE);
						
						this.dispose();
//						txtCardNo.setText("");
//						txtCurrentPin.setText("");
//						txtNewPin1.setText("");
//						txtNewPin2.setText("");
						
					}else {
						JOptionPane.showMessageDialog(this, "Invalid Credentials!", "Password Validation Message", JOptionPane.ERROR_MESSAGE);
					}
					
					
				} catch (SQLException e1) {
					System.out.println("Error occured during athentication: "+e1);
				}
					
			}else {
				JOptionPane.showMessageDialog(this, "Passwords does not match!", "Change Password Warning", JOptionPane.WARNING_MESSAGE);
			}
		
		}
		
	}//end of update action
	
}
