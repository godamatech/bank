package atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import myResources.GridbagLayout;

public class AirtimeTopUp extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle;

	JComboBox comboNetwork;

	JTextField txtPhone;
	JTextField txtAmount;

	JButton btnBuy;
	JButton btnClose;

	public AirtimeTopUp() {

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		comboNetwork = new JComboBox();

		comboNetwork.addItem("Please Select Network");
		comboNetwork.addItem("MTN");
		comboNetwork.addItem("Airtel");
		comboNetwork.addItem("GLO");
		comboNetwork.addItem("9 Mobile (Etisalat)");
		comboNetwork.addItem("SMILE");

		lblTitle = new JLabel("Please Select your Network");
		txtPhone = new JTextField(8);
		txtAmount = new JTextField(8);

		btnBuy = new JButton("Buy");
		btnClose = new JButton("Close");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, comboNetwork, 0, 1, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, txtPhone, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, txtAmount, 1, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnClose, 0, 3, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnBuy, 1, 3, 1, 1, GridBagConstraints.WEST);

		lblTitle.setForeground(Color.blue);
		btnBuy.setForeground(Color.blue);

		btnClose.setBackground(Color.gray);
		panel.setBackground(Color.green);

		this.setVisible(true);
		this.setSize(500, 300);
		this.setTitle("Airtime-Top-up");
		this.add(panel);
		this.setLocationRelativeTo(null);

		Font bigFont = new Font("Sans-Serif", Font.BOLD, 24);
		Font normalFont = new Font("Times New Roman", Font.BOLD, 24);
		lblTitle.setFont(bigFont);
		txtAmount.setFont(bigFont);
		txtPhone.setFont(bigFont);
		comboNetwork.setFont(bigFont);
		btnBuy.setFont(bigFont);
		btnClose.setFont(bigFont);
		
		
		
		txtPhone.setText("Phone-No");
		txtAmount.setText("Amount");

		btnClose.addActionListener(this);

	}// end of constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose) {
			this.dispose();
		} else if (e.getSource() == btnBuy) {
			try {

			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public static void main(String[] args) {
		new AirtimeTopUp();
	}
}
