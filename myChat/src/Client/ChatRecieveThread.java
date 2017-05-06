package Client;

import java.io.DataInputStream;
import java.io.IOException;

import Client.Forms.ChatForm;

public class ChatRecieveThread extends Thread {
	ChatForm chatForm;
	DataInputStream dis;

	public ChatRecieveThread(ChatForm chatForm) {
		this.chatForm = chatForm;
	}

	@Override
	public void run() {
		while (true) {
			try {
				dis = new DataInputStream(this.chatForm.getSocket()
						.getInputStream());
				String in = dis.readUTF();
				this.chatForm.getTxtHisMsg().setText(this.chatForm.getTxtHisMsg().getText()+"\n"+in);
				this.chatForm.getTxtMyMsg().setText(this.chatForm.getTxtMyMsg().getText()+ "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				this.stop();
			}
		}
	}

}
