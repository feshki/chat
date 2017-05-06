package Client.Forms;

import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.ChatRecieveThread;
import Client.Listeners.ChatBtnCloseListener;
import Client.Listeners.ChatBtnSendListener;

public class ChatForm extends JFrame{
	private Socket socket;
	private String username;
	private JTextArea txtMyMsg;
	private JTextArea txtHisMsg;
	private JTextField txtSendMsg;
	private ChatRecieveThread crt;
	JLabel lblMyMsg;
	JLabel lblHisMsg;
	JButton btnSend;
	JButton btnClose;
	public ChatForm(Socket socket,String username)
	{
		//init components
		this.setSocket(socket);
		this.setUsername(username);
		setTxtMyMsg(new JTextArea());
		setTxtHisMsg(new JTextArea());
		setTxtSendMsg(new JTextField());
		lblHisMsg = new JLabel("His/Her Messages:");
		lblMyMsg = new JLabel("My Messages:");
		btnSend = new JButton("Send");
		btnClose = new JButton("Close");
		
		this.setLayout(null);
		this.setTitle("chat with " + this.getUsername());
	
		//set component size and position
		this.setSize(700,600);
		lblMyMsg.setBounds(30,30,100,30);
		lblHisMsg.setBounds(350,30,100,30);
		getTxtMyMsg().setBounds(30,60,300,400);
		getTxtHisMsg().setBounds(350,60,300,400);
		getTxtSendMsg().setBounds(30,480,400,30);
		btnSend.setBounds(440,480,100,30);
		btnClose.setBounds(550,480,100,30);
		
		
		//configure messages boxes
		getTxtMyMsg().setEditable(false);
		getTxtHisMsg().setEditable(false);
		getTxtMyMsg().setAutoscrolls(true);
		getTxtHisMsg().setAutoscrolls(true);
		
		//add listener to buttons
		btnSend.addActionListener(new ChatBtnSendListener(this));
		btnClose.addActionListener(new ChatBtnCloseListener(this));
		
		
		//add component to form
		this.getContentPane().add(lblMyMsg);
		this.getContentPane().add(lblHisMsg);
		this.getContentPane().add(getTxtHisMsg());
		this.getContentPane().add(getTxtMyMsg());
		this.getContentPane().add(getTxtSendMsg());
		this.getContentPane().add(btnSend);
		this.getContentPane().add(btnClose);
	
		crt = new ChatRecieveThread(this);
		crt.start();
		
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public JTextArea getTxtMyMsg() {
		return txtMyMsg;
	}
	public void setTxtMyMsg(JTextArea txtMyMsg) {
		this.txtMyMsg = txtMyMsg;
	}
	public JTextArea getTxtHisMsg() {
		return txtHisMsg;
	}
	public void setTxtHisMsg(JTextArea txtHisMsg) {
		this.txtHisMsg = txtHisMsg;
	}
	public JTextField getTxtSendMsg() {
		return txtSendMsg;
	}
	public void setTxtSendMsg(JTextField txtSendMsg) {
		this.txtSendMsg = txtSendMsg;
	}
	public ChatRecieveThread getCrt() {
		return crt;
	}
	public void setCrt(ChatRecieveThread crt) {
		this.crt = crt;
	}

}
