package Client.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import Client.Forms.ListForm;

public class ListBtnListListener implements ActionListener {
	ListForm listForm;
	DataInputStream dis;
	DataOutputStream dos;

	public ListBtnListListener(ListForm listForm) {
		// TODO Auto-generated constructor stub
		this.listForm = listForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
//			dis = new DataInputStream(listForm.socket.getInputStream());
			dos = new DataOutputStream(listForm.socket.getOutputStream());
			dos.writeUTF("LIST");
			System.out.println(" before recieve list");
//			String[] input = dis.readUTF().split(" ");
//			this.listForm.list.setListData(input);
//			System.out.println("added list to jlist");
//			this.listForm.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("can't create dis");
		}
	}

}
