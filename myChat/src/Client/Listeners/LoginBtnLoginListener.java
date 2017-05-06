package Client.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Client.Forms.ListForm;
import Client.Forms.LoginForm;

public class LoginBtnLoginListener implements ActionListener {
	LoginForm login;
	Socket socket;
	String ip;
	int port;
	String username;
	String password;
	DataInputStream dis;
	DataOutputStream dos;

	public LoginBtnLoginListener(LoginForm login) {
		// TODO Auto-generated constructor stub
		this.login = login;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (socket != null) {
			socket = null;
		}
		if (checkTextField()) {
			try {
				socket = new Socket(ip, port);
				this.login.lblMessage.setText("socket created");

				try {
					dos = new DataOutputStream(socket.getOutputStream());
					dos.writeUTF("LOGIN" + " " +  username + " " + password);
					try {
						dis = new DataInputStream(socket.getInputStream());
						if (dis.readUTF().equals("ok")) {
							this.login.lblMessage.setText("you are loggined");
							ListForm listForm = new ListForm(socket,username);
							listForm.setVisible(true);
						} else {
							try {
								this.socket.close();
							} catch (IOException e) {
								this.login.lblMessage
										.setText("trying to close socket failed");
							}

							this.login.lblMessage
									.setText("username or password is uncorrect");
						}
					} catch (IOException e) {
						
						this.login.lblMessage
								.setText("cant creat data input connection");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
					this.login.lblMessage
							.setText("cant create data output connection");
				}

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				this.login.lblMessage.setText("host error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				this.login.lblMessage.setText("io exeption");
			}

		}
	}

	private boolean checkTextField() {
		if (!(ip = this.login.txtIp.getText()).trim().equals("")) {

			if (!this.login.txtPort.getText().trim().equals("")) {
				try {
					port = Integer
							.parseInt(this.login.txtPort.getText().trim());
				} catch (NumberFormatException e) {
					this.login.lblMessage.setText("only number in PORT field");
					return false;
				}
				if (!(username = this.login.txtUsername.getText()).trim()
						.equals("")) {
					if (!(password = this.login.txtPassword.getText()).trim()
							.equals("")) {
						this.login.lblMessage.setText("");
						return true;
					} else {
						this.login.lblMessage.setText("please insert PASSWORD");
						return false;
					}
				} else {
					this.login.lblMessage.setText("please insert USERNAME");
					return false;
				}
			} else {
				this.login.lblMessage.setText("please insert PORT");
				return false;
			}
		} else {
			this.login.lblMessage.setText("please insert IP");
			return false;
		}

	}

}
