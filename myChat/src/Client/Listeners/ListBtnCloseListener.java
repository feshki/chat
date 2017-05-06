package Client.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Client.Forms.ListForm;

public class ListBtnCloseListener implements ActionListener {
	private ListForm listForm;

	public ListBtnCloseListener(ListForm listForm) {
		// TODO Auto-generated constructor stub
		this.listForm = listForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			this.listForm.getCt().stop();
			this.listForm.socket.close();
			this.listForm.dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("can't close socket");
		}

	}

}
