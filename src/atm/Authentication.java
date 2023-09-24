//##################### ATHENTICATION

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

public class Authentication extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle;

	JLabel lblPin;
	JLabel lblCardId;
	JTextField txtPin;
	private JTextField txtCardId;

	JButton btnSubmit;
	JButton btnExit;

	DbConnection db = new DbConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private static String curCardNum;

	public static String getCurCardNum() {
		return curCardNum;
	}

	public void setCurCardNum(String curCardNum) {
		this.curCardNum = curCardNum;
	}

	public Authentication() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		lblTitle = new JLabel("User Authentication");

		lblPin = new JLabel("PIN (4 digits only* ): ");
		lblCardId = new JLabel("Card Number*: ");
		txtPin = new JPasswordField(4);
		txtCardId = new JTextField(10);

		btnSubmit = new JButton(" Submit ");
		btnExit = new JButton(" Exit ");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, lblPin, 0, 1, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, txtPin, 1, 1, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, lblCardId, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, txtCardId, 1, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnSubmit, 0, 3, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnExit, 1, 3, 1, 1, GridBagConstraints.WEST);

		lblTitle.setForeground(Color.blue);
		btnSubmit.setForeground(Color.BLUE);
		btnExit.setForeground(Color.BLUE);
		btnSubmit.setBackground(Color.gray);
		btnExit.setBackground(Color.gray);

		this.setVisible(true);
		this.setSize(500, 350);
		this.setResizable(false);
		this.setTitle("Authentication Page");
		this.add(panel);
		this.setLocationRelativeTo(null);

		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 20);
		
		btnExit.setFont(bigFont);
		btnSubmit.setFont(bigFont);
		lblCardId.setFont(bigFont);
		lblPin.setFont(bigFont);
		lblTitle.setFont(bigFont);
		txtCardId.setFont(bigFont);
		txtPin.setFont(bigFont);
		
		btnSubmit.addActionListener(this);
		btnExit.addActionListener(this);

	}// end of constructor

	public static void main(String[] args) {
		new Authentication();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String atmCardId = txtCardId.getText().toString();
		String pin = txtPin.getText().toString();

		if (e.getSource() == btnExit) {
			int option = JOptionPane.showConfirmDialog(this, "Are you sure? ", "Warning", JOptionPane.OK_CANCEL_OPTION);
			if (option == 0) {
				System.exit(0);
			}

		} else if (e.getSource() == btnSubmit) {
			db.connectToDb();			
			if (txtCardId.getText().equalsIgnoreCase("") || txtPin.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "All fields are required!", "Authentication Input Message",
						JOptionPane.WARNING_MESSAGE);
			} else {				
//				try {
//					int cardNum = Integer.parseInt(atmCardId);
//				} catch (NumberFormatException nfe) {
//					JOptionPane.showMessageDialog(null,  "I'm Sorry, only figures are required!", "Authentication Page", JOptionPane.WARNING_MESSAGE);
//				}
				
				int cardNum = Integer.parseInt(atmCardId);
				try {
					ps = db.getCon().prepareStatement("select * from atmcard where atmCardId=? and pin=?");
					ps.setInt(1, cardNum);
					ps.setString(2, pin);

					rs = ps.executeQuery();
					if (rs.next()) {
						this.setCurCardNum(atmCardId);
						System.err.println("Testing from Auth: " + this.getCurCardNum());
						new Menu(atmCardId);//class instance
						this.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Credentials!", "Authentication Input Message",
								JOptionPane.WARNING_MESSAGE);
					}
					
				} catch (SQLException e1) {
					System.err.println("Error during query" + e1);
				}finally {
					try {
						db.getCon().close();
					} catch (SQLException e1) {
						System.out.println("Failed to close Connection");
					}
				}

			}
			
		}

	}// end of actionPerformed()

}
