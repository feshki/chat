package Client.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import Client.Forms.ChatForm;

public class ChatBtnSendListener implements ActionListener {
	
	ChatForm chatForm;
	DataOutputStream dos;

	public ChatBtnSendListener(ChatForm chatForm) {
		// TODO Auto-generated constructor stub
		this.chatForm = chatForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if(!this.chatForm.getTxtSendMsg().getText().trim().equals(""))
		{
			try {
				dos = new DataOutputStream(this.chatForm.getSocket().getOutputStream());
				dos.writeUTF(this.chatForm.getTxtSendMsg().getText());
				this.chatForm.getTxtMyMsg().setText(this.chatForm.getTxtMyMsg().getText() + "\n" +this.chatForm.getTxtSendMsg().getText());
				this.chatForm.getTxtHisMsg().setText(this.chatForm.getTxtHisMsg().getText()+ "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
