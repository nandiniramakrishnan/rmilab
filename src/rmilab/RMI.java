package rmilab;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import rmilab.utilities.RemoteObjRef;


/* This is the server in our RMI Application */
public class RMI implements Serializable {
	static String host;
	static int port = 2694;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, 
	InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		System.out.println("your hostname = "+(InetAddress.getLocalHost()).getHostName());
		Registry tbl = new Registry();
		System.out.println("Registry has been created");
		
		InetAddress ipAddress = InetAddress.getLocalHost();	/* Your IP Address. This is the server */
		RemoteObjRef ror = new RemoteObjRef(ipAddress, 2694, "fib","Fibonacci");	/* Port number of the remote host */
		System.out.println("Remote obj ref for fib="+ror);
		Registry.bind("fib",ror);
		
		ServerSocket serverSoc = new ServerSocket(port);	/* ServerSocket is listening on this port */
		System.out.println("Server has started listening on the serversocket port"); /* can't use 2694 for anything else */
		RemoteObjRef abc;
		while (true)
		{
			Socket clientSocket = null;
			clientSocket = serverSoc.accept();
			if (clientSocket != null) {
				System.out.println("connection has been made");
			}
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			String key = (String)in.readObject();
			System.out.println("Client requested this servicename:"+key);
			abc = tbl.lookup(key);
			System.out.println("Got it!   "+abc);
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			out.writeObject(abc);
			Fibonacci_skeleton.continueGettingFibonacciSeries();
		}
	}
}
