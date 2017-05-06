package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Models.DB;
import Server.Models.SocketThread;
import Server.Models.User;

public class Server {
	ServerSocket serverSocket;
	public Server()
	{
		DB.users.add(new User("ali", "1234"));
		DB.users.add(new User("karim", "1234"));
		try {
			serverSocket = new ServerSocket(8090);
			System.out.println("Starting server...");
			while(true)
			{
				try{
				Socket socket = serverSocket.accept();
				System.out.println("Connected to socket");
				SocketThread st = new SocketThread(socket);
				st.start();
				}
				catch(IOException e){
					System.out.println("cant create socket");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("cant start server");
		}
	}

}
