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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import myResources.DbConnection;
import myResources.GridbagLayout;

public class Customer extends JFrame implements ActionListener{
	JPanel panel;

	JLabel lblTitle;
	
	JLabel lblName;
	JTextField txtName;

	JLabel lblEmail;
	JTextField txtEmail;

	JLabel lblCountry;
	JTextField txtCountry;

	JLabel lblState;
	JTextField txtState;

	JLabel lblAddress;
	JTextField txtAddress;
	
	JButton btnSubmit, btnClose;
	
	DbConnection db = new DbConnection();
	PreparedStatement ps;
	ResultSet rs;
	
	//this is the object of my own created gridbag layout manager from layoutManager package
	GridbagLayout gb = new GridbagLayout();
	
	public Customer() {
				
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		lblTitle = new JLabel("* CUSTOMER ENROLLMENT *");

		lblName = new JLabel("Name: ");
		txtName = new JTextField(15);
		
		lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField(15);
		
		lblCountry = new JLabel("Country: ");
		txtCountry = new JTextField(15);
		
		lblState = new JLabel("State: ");
		txtState = new JTextField(15);
		
		lblAddress = new JLabel("Address: ");
		txtAddress = new JTextField(15);
		
		btnSubmit = new JButton("Submit");
		btnClose = new JButton("Exit");
		
		gb.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		
		gb.addComp(panel, lblName, 0, 1, 1, 1, GridBagConstraints.EAST);
		gb.addComp(panel, txtName, 1, 1, 1, 1, GridBagConstraints.WEST);
		
		gb.addComp(panel, lblEmail, 0, 2, 1, 1, GridBagConstraints.EAST);
		gb.addComp(panel, txtEmail, 1, 2, 1, 1, GridBagConstraints.WEST);
		
		gb.addComp(panel, lblCountry, 0, 3, 1, 1, GridBagConstraints.EAST);
		gb.addComp(panel, txtCountry, 1, 3, 1, 1, GridBagConstraints.WEST);
		
		gb.addComp(panel, lblState, 0, 4, 1, 1, GridBagConstraints.EAST);
		gb.addComp(panel, txtState, 1, 4, 1, 1, GridBagConstraints.WEST);
		
		gb.addComp(panel, lblAddress, 0, 5, 1, 1, GridBagConstraints.EAST);
		gb.addComp(panel, txtAddress, 1, 5, 1, 1, GridBagConstraints.WEST);
		
		gb.addComp(panel, btnSubmit, 0, 6, 1, 1, GridBagConstraints.EAST);
		gb.addComp(panel, btnClose, 1, 6, 1, 1, GridBagConstraints.WEST);
		
		lblTitle.setForeground(Color.BLUE);
		btnSubmit.setBackground(Color.gray);
		btnSubmit.setForeground(Color.blue);
		btnClose.setBackground(Color.gray);
		btnClose.setForeground(Color.blue);
		
		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		lblAddress.setFont(bigFont);
		lblCountry.setFont(bigFont);
		lblEmail.setFont(bigFont);
		lblState.setFont(bigFont);
		lblTitle.setFont(bigFont);
		lblName.setFont(bigFont);
		
		txtAddress.setFont(bigFont);
		txtCountry.setFont(bigFont);
		txtEmail.setFont(bigFont);
		txtName.setFont(bigFont);
		txtState.setFont(bigFont);
		
		btnSubmit.setFont(bigFont);
		btnClose.setFont(bigFont);
		
		
		this.setVisible(true);
		this.setSize(550, 450);
		this.setTitle(" Customer ");
		this.setLocationRelativeTo(null);
		this.add(panel);
		
		btnClose.addActionListener(this);
		btnSubmit.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = txtName.getText();
		String email = txtEmail.getText();
		String country = txtCountry.getText();
		String state = txtState.getText();
		String address = txtAddress.getText();
		
		if(e.getSource()==btnClose) {
			System.exit(0);
		}else if(e.getSource()==btnSubmit) {
			db.connectToDb();
			if(name.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "All fields are required!", "Input validation", JOptionPane.WARNING_MESSAGE);
			}else {
				try {
					ps = db.getCon().prepareStatement("INSERT INTO customer VALUES(NULL,?,?,?,?,? )");
					ps.setString(1, name);
					ps.setString(2, email);
					ps.setString(3, country);
					ps.setString(4, state);
					ps.setString(5, address);
					
					JOptionPane.showMessageDialog(null, "All fields are required!", "Input validation", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.out.println("Error during Insertion "+ e1);
				}
			}
			System.err.println("submit");
		}
		
	}
	public static void main(String[] args) {
		new Customer();
	}
}
