package Client.Forms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import Client.ListRecieveThread;
import Client.Listeners.ListBtnCloseListener;
import Client.Listeners.ListBtnConnectListener;
import Client.Listeners.ListBtnListListener;

public class ListForm extends JFrame{
	public Socket socket;
	public String chatWith;
	public JList list;
	private String username;
	private ListRecieveThread ct;
	DataInputStream dis ;
	JButton btnConnect;
	JButton btnList;
	JButton btnClose;
	JLabel lblList;
	public ListForm(Socket socket, String username)
	{
		//initialize
		this.socket = socket;
		this.setUsername(username);
		list = new JList<String>();
		btnConnect = new JButton("Connect to the selected client");
		btnList = new JButton("Idle connected client list");
		btnClose = new JButton("Close");
		lblList = new JLabel("Idle Clients:");
		
		//set component size and position
		this.setSize(460,350);
		list.setBounds(40,50,150,250);
		btnConnect.setBounds(210,80,220,30);
		btnList.setBounds(210,120,220,30);
		btnClose.setBounds(210,270,220,30);
		lblList.setBounds(40,20,100,30);
		
		this.getContentPane().setLayout(null);
		this.setTitle("list of clients");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//add listener for buttons
		btnConnect.addActionListener(new ListBtnConnectListener(this));
		btnList.addActionListener(new ListBtnListListener(this));
		btnClose.addActionListener(new ListBtnCloseListener(this));
		
		//add component to the form
		this.getContentPane().add(list);
		this.getContentPane().add(btnList);
		this.getContentPane().add(btnConnect);
		this.getContentPane().add(btnClose);
		this.getContentPane().add(lblList);
		ct = new ListRecieveThread(this);
		ct.start();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ListRecieveThread getCt() {
		return ct;
	}
	public void setCt(ListRecieveThread ct) {
		this.ct = ct;
	}
	

	
}
