package Server.Models;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DB {
	public static List<User> users = new ArrayList<User>();
	public static List<SocketThread> sockets = new ArrayList<SocketThread>();
	public static Socket userSocket(String user)
	{
		for(SocketThread st: sockets)
		{
			if(st.getUsername().equals(user) && !st.isConnect())
			{
				return st.getSocket();
			}
		}
		return null;
	}
	public static SocketThread userThread(String user)
	{
		for(SocketThread st : sockets)
		{
			if(st.getUsername().equals(user))
			{
				return st;
			}
		}
		return null;
	}

	public static String getNotConnectedUsers()
	{
		String tmp = "";
		int count = 0;
		for(SocketThread st: sockets)
		{
			if(!st.isConnect())
			{
				tmp+=" " + st.getUsername();
				count++;
			}
		}
		return count + tmp;
	}

	public static void removeThread(String username)
	{
		Iterator it = sockets.iterator();
		while(it.hasNext()){
			if(((SocketThread)it.next()).getUsername().equals(username))
			{
				System.out.println("removing " + username);
				it.remove();
			}
		}
	
				
	}
	
	public static boolean idleUserExist(String username)
	{
		for(SocketThread st : sockets)
		{
			if(st.getUsername().equals(username) && !st.isConnect())
				return true;
		}
		return false;
	}
}
