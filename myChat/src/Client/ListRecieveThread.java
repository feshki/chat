package Client;

import java.io.DataInputStream;
import java.io.IOException;

import Client.Forms.ChatForm;
import Client.Forms.ListForm;

public class ListRecieveThread extends Thread {

	private ListForm listForm;
	DataInputStream dis;

	public ListRecieveThread(ListForm listFrom) {
		this.listForm = listFrom;
	}

	@Override
	public void run() {
		while (true) {
			try {
				dis = new DataInputStream(this.listForm.socket.getInputStream());
				String in = dis.readUTF();
				if (in.equals("CONNECT") || in.equals("ok")) {
					ChatForm chatForm = new ChatForm(this.listForm.socket,
							this.listForm.getUsername());
					chatForm.setVisible(true);
					this.stop();
				} else {
					this.listForm.list.setListData(dis.readUTF().split(" "));
					this.listForm.repaint();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
