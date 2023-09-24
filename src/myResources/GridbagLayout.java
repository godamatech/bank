package myResources;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.*;

public class GridbagLayout extends JFrame {
//	JPanel panel;
//
//	JLabel lblUsername;
//	JTextField txtUsername;
//
//	JLabel lblPassword;
//	JPasswordField txtPass;
//	JButton btnLogin;
	
	public GridbagLayout() {
//		panel = new JPanel();
//		panel.setLayout(new GridBagLayout());
//
//		lblUsername = new JLabel("User Name: ");
//		txtUsername = new JTextField(15);
//
//		lblPassword = new JLabel("Password: ");
//		txtPass = new JPasswordField(15);
//
//		btnLogin = new JButton("Login");

		
//
//		addComp(panel, lblUsername, 0, 0, 1, 1, GridBagConstraints.EAST);
//		addComp(panel, txtUsername, 1, 0, 1, 1, GridBagConstraints.WEST);
//		
//		addComp(panel, lblPassword, 0, 1, 1, 1, GridBagConstraints.EAST);
//		addComp(panel, txtPass, 1, 1, 1, 1, GridBagConstraints.WEST);
//		addComp(panel, btnLogin, 1, 2, 1, 1, GridBagConstraints.WEST);
//		
//		this.add(panel);
//		panel.setBackground(Color.cyan);
//		btnLogin.setBackground(Color.green);
//		this.setVisible(true);
//		this.setSize(300, 150);
//		this.setLocationRelativeTo(null);
		
		
	}
	
	public void addComp(JPanel p, Component c, int x, int y, int width, int height, int align ) {
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.gridx = x;
		gbCon.gridy = y;
		gbCon.gridwidth = width;
		gbCon.gridheight = height;
		gbCon.weightx = 100;
		gbCon.weighty = 100;
		gbCon.insets = new Insets(5, 5, 5, 5);
		gbCon.anchor = align;
		gbCon.fill = GridBagConstraints.NONE;
		p.add(c, gbCon);
		
		
	}//end of addComp()
	
	
}
