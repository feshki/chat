package Client.Forms;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Client.Listeners.LoginBtnLoginListener;



public class LoginForm extends JFrame{

	//this frame component
	JLabel lblIp;
	JLabel lblPort;
	JLabel lblUsername;
	JLabel lblPassword;
	public JLabel lblMessage;
	public JTextField txtIp;
	public JTextField txtPort;
	public JTextField txtUsername;
	public JTextField txtPassword;
	JButton btnLogin;
	public LoginForm()
	{
		//initialize component
		lblIp = new JLabel("IP:");
		lblPort = new JLabel("PORT:");
		lblUsername = new JLabel("USERNAME:");
		lblPassword = new JLabel("PASSWORD:");
		lblMessage = new JLabel("");
		lblMessage.setForeground(Color.red);
		txtIp = new JTextField();
		txtPort = new JTextField();
		txtUsername = new JTextField();
		txtPassword = new JTextField();
		btnLogin = new JButton("login");
		
		this.setTitle("login");
		this.setLayout(null);
		this.setSize(280, 300);
		
		//set component size and possition
		lblIp.setBounds(20,20,100,40);
		lblPort.setBounds(20,60,100,40);
		lblUsername.setBounds(20,100,100,40);
		lblPassword.setBounds(20,140,100,40);
		lblMessage.setBounds(20,170,220,40);
		txtIp.setBounds(140,20,100,30);
		txtPort.setBounds(140,60,100,30);
		txtUsername.setBounds(140,100,100,30);
		txtPassword.setBounds(140,140,100,30);
		btnLogin.setBounds(100,210,100,30);
		
		//add listener to the login button
		btnLogin.addActionListener(new LoginBtnLoginListener(this));
		
		//add component to main frame
		this.getContentPane().add(lblIp);
		this.getContentPane().add(lblPort);
		this.getContentPane().add(lblUsername);
		this.getContentPane().add(lblPassword);
		this.getContentPane().add(lblMessage);
		this.getContentPane().add(txtIp);
		this.getContentPane().add(txtPort);
		this.getContentPane().add(txtUsername);
		this.getContentPane().add(txtPassword);
		this.getContentPane().add(btnLogin);
		
		this.setVisible(true);
	}

}
