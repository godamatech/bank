package atm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import myResources.GridbagLayout;

public class Deposit extends JFrame implements ActionListener {
	JPanel panel;

	JLabel lblTitle;
	
	JTextField txtInput;

	JButton btnDeposit;
	JButton btnClose;

	public Deposit() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		lblTitle = new JLabel("Please Select Transaction");
		
		txtInput = new JTextField(10);

		btnDeposit = new JButton("Deposit");
		btnClose = new JButton("Close");

		GridbagLayout g = new GridbagLayout();

		g.addComp(panel, lblTitle, 0, 0, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, txtInput, 0, 1, 2, 1, GridBagConstraints.CENTER);
		g.addComp(panel, btnDeposit, 0, 2, 1, 1, GridBagConstraints.EAST);
		g.addComp(panel, btnClose, 1, 2, 1, 1, GridBagConstraints.WEST);

		lblTitle.setForeground(Color.blue);

		btnDeposit.setForeground(Color.blue);

		btnClose.setBackground(Color.gray);

		panel.setBackground(Color.green);

		this.setVisible(true);
		this.setSize(350, 150);
		this.setTitle("Deposit Transaction");
		this.add(panel);
		this.setLocationRelativeTo(null);
		
		btnClose.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnClose) {
			this.dispose();
		}
		
	}

}
