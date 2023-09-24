import java.awt.Color;

import javax.swing.*;

public class Main extends JFrame {
	JPanel panel;
	
	JLabel lblUsername;
	JTextField txtUsername;
	
	JLabel lblPassword;
	JPasswordField txtPass;
	JButton btnLogin;

	public Main() {
		panel = new JPanel();
		
		lblUsername = new JLabel("User Name: ");
		txtUsername = new JTextField(15);
		
		lblPassword = new JLabel("Password: ");
		txtPass = new JPasswordField(15);
		
		btnLogin = new JButton("Login");
		
		panel.add(lblUsername);
		panel.add(txtUsername);
		
		panel.add(lblPassword);
		panel.add(txtPass);
		panel.add(btnLogin);
		
		this.add(panel);
		panel.setBackground(Color.cyan);
		btnLogin.setBackground(Color.green);
		this.setVisible(true);
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		
		
	}

	public static void main(String[] args) {
		new Main();
		System.out.println((5000 % 1000)!=0);
	}

}
