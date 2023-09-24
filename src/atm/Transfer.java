package atm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.ha.BalanceStrategy;

import myResources.GridbagLayout;

public class Transfer extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle;
	
	JComboBox comboBenefBank;
	JTextField txtAmount;
	JTextField txtBenefAcctNo;
	JTextField txtBenefAcctName;

	JButton btnTransfer;

	JButton btnClose;

	public Transfer() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		lblTitle = new JLabel("Please Select Transaction");
		txtAmount = new JTextField(10);
		txtBenefAcctNo = new JTextField(10);
		txtBenefAcctName = new JTextField(13);
		
		comboBenefBank = new JComboBox();
		
		String banks [] = {"Access", "Ja'iz", "G.T Bank", "U.B.A", "First Bank", "FCMB", "Zenith"};
		comboBenefBank.addItem(banks);
		btnTransfer = new JButton("Transfer");
		btnClose = new JButton("Close");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, comboBenefBank, 0, 1, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, txtBenefAcctNo, 1, 1, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, txtBenefAcctName, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, txtAmount, 1, 2, 1, 1, GridBagConstraints.WEST);
		g.addComp(panel, btnTransfer, 1, 3, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 2, 3, 1, 1, GridBagConstraints.WEST);

		lblTitle.setForeground(Color.blue);
		btnTransfer.setForeground(Color.blue);

		btnClose.setBackground(Color.gray);
		panel.setBackground(Color.green);
		
		txtBenefAcctName.setEditable(false);
		txtBenefAcctNo.setText("Beneficiary-Account-No");
		txtAmount.setText("Amount-in-Naira");

		this.setVisible(true);
		this.setSize(430, 300);
		this.setTitle("Transfer Transaction");
		this.add(panel);
		this.setLocationRelativeTo(null);

		btnClose.addActionListener(this);
		
	}// end of constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnClose) {
			this.dispose();
		}
		
	}
	
	public static void main(String[] args) {
	new Transfer();
}
}// end of class
