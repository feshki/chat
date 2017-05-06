package Client.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Client.Forms.ChatForm;

public class ChatBtnCloseListener implements ActionListener {
	ChatForm chatForm;

	public ChatBtnCloseListener(ChatForm chatForm) {
		// TODO Auto-generated constructor stub
		this.chatForm = chatForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// TODO Auto-generated method stub

		this.chatForm.getCrt().stop();

		this.chatForm.dispose();

	}

}
