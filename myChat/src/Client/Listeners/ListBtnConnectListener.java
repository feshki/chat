package Client.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import Client.Forms.ChatForm;
import Client.Forms.ListForm;

public class ListBtnConnectListener implements ActionListener {
	
	ListForm listForm;
	DataInputStream dis;
	DataOutputStream dos;

	public ListBtnConnectListener(ListForm listForm) {
		// TODO Auto-generated constructor stub
		this.listForm = listForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(this.listForm.list.getSelectedIndex()>=0)
		{
			String username = this.listForm.list.getSelectedValue().toString();
			try {
//				dis = new DataInputStream(this.listForm.socket.getInputStream());
				dos = new DataOutputStream(this.listForm.socket.getOutputStream());
				dos.writeUTF("CONNECT " + username);
//				if(dis.readUTF().equals("ok"))
//				{
//					ChatForm chatForm = new ChatForm(this.listForm.socket, this.listForm.getUsername());
//					chatForm.setVisible(true);
//				}
//				else
//				{
//					System.out.println("cant connect");
//				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("cant create dis and dos");
			}
			
		}

	}

}
