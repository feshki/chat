package Server.Models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketThread extends Thread {

	private String username;
	private Socket socket;
	private Socket pairSocket;
	private String pairUsername;
	DataInputStream dis;
	DataOutputStream dos;
	private boolean connect = false;

	public SocketThread(Socket socket) {
		this.socket = socket;
	}
	public SocketThread(String username)
	{
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public boolean isConnect() {
		return connect;
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}

	@Override
	public boolean equals(Object o) {
		SocketThread tmp = (SocketThread) o;
		if (this.getUsername().equals(tmp.getUsername())) {
			return true;
		}
		return false;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(DB.sockets.size());
			if (!this.isConnect()) {
				try {
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());
					String[] input = dis.readUTF().split(" ");
					switch(input[0])
					{
					case "LOGIN":
						System.out.println("we are at login case now");
						if(DB.users.contains(new User(input[1],input[2])) && !DB.sockets.contains(new SocketThread(input[1])))
						{
							System.out.println("at if section");
							dos.writeUTF("ok");
							this.setUsername(input[1]);
							DB.sockets.add(this);
							System.out.println("in if section and add socket to db");
						}
						else
						{
							System.out.println("we are at else section");
							dos.writeUTF("not");
							dos.close();
							dis.close();
							socket.close();
							System.out.println("close dis dos and socket and now trying to stop thread");
							this.stop();
						}
						break;
					case "LIST":
						System.out.println("we are at list section");
						String list = DB.getNotConnectedUsers();
						System.out.println("this is list " + list);
						dos.writeUTF(list);
						System.out.println("send list to client");
						break;
					case "CONNECT":
						if(DB.idleUserExist(input[1]))
						{
							dos.writeUTF("ok");
							this.pairSocket = DB.userSocket(input[1]);
							this.setPairUsername(input[1]);
							DB.userThread(input[1]).setPairSocket(this.getSocket());
							DB.userThread(input[1]).setPairUsername(this.getUsername());
							DB.userThread(input[1]).setConnect(true);
							dos = new DataOutputStream(this.pairSocket.getOutputStream());
							dos.writeUTF("CONNECT");
							this.setConnect(true);
						}
						else
						{
							dos.writeUTF("not");
						}
						break;
					case "CLOSE":
						break;
						default:
							break;
					}
				} catch (IOException e) {
					System.out.println("can't create dis or dos");
					try{
					dos.close();
					dis.close();
					socket.close();
					}
					catch(IOException ee)
					{
						System.out.println("can't close dis,dos,socket");
					}
					DB.removeThread(username);
					System.out.println("remove " + username + " from db");
					this.stop();
				}
			} else {
				try {
					dos = new DataOutputStream(this.pairSocket.getOutputStream());
					dos.writeUTF(dis.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					this.setConnect(false);
				}
					

			}
		}
	}
	public String getPairUsername() {
		return pairUsername;
	}
	public void setPairUsername(String pairUsername) {
		this.pairUsername = pairUsername;
	}
	public Socket getPairSocket() {
		return pairSocket;
	}
	public void setPairSocket(Socket pairSocket) {
		this.pairSocket = pairSocket;
	}
}
