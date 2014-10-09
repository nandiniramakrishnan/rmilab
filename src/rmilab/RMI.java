package rmilab;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import rmilab.utilities.RMIMessage;
import rmilab.utilities.RMIMessage.MessageType;
import rmilab.utilities.RMIMessageDelivery;
import rmilab.utilities.RemoteObjRef;


/* This is the server in our RMI Application */
public class RMI implements Serializable {
	static int port = 1234;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, 
	InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		System.out.println("your hostname = "+(InetAddress.getLocalHost()).getHostName());
		Registry tbl = new Registry();
		
		InetAddress ipAddress = InetAddress.getLocalHost();	/* Your IP Address. This is the server */
		RemoteObjRef ror = new RemoteObjRef(ipAddress, port, "fib","Fibonacci");	/* Port number of the remote host */
		Registry.bind("fib",ror);
		System.out.println("Your reference is binded. ror="+ror);
		ServerSocket serverSoc = new ServerSocket(port);	/* ServerSocket is listening on this port */
		System.out.println("Server has started listening on the port 1234"); /* can't use 1234 for anything else */
		RemoteObjRef remoteObject;
		while (true)
		{
			Socket clientSocket = null;
			
			// thread begin
			clientSocket = serverSoc.accept();
			RMIMessageDelivery rmd = new RMIMessageDelivery(clientSocket);
			RMIMessage inputMsg = rmd.getMessage();
			/*ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			String key = (String)in.readObject();*/

            if(inputMsg.getType() == MessageType.LOOKUP )
            {
             String key = inputMsg.getServiceName();
			  String methodName = inputMsg.getMethodName();
			  Object[] arglist = inputMsg.getParams();
			  remoteObject = tbl.lookup(key); 
			  String interfaceName = remoteObject.getInterfaceName();
			  RMIMessage outputMsg = new RMIMessage(MessageType.FOUND, remoteObject);
			 rmd.sendMessage(outputMsg);
			/*
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			out.writeObject(abc);*/
			Class c = Class.forName("rmilab."+interfaceName + "_skeleton");
			System.out.println("rmilab."+interfaceName + "_skeleton");
			Constructor constructor = c.getDeclaredConstructor(ServerSocket.class);
			Object skeleton = constructor.newInstance(new ServerSocket());
			System.out.println("here's your skeleton! "+skeleton);
			//Fibonacci_skeleton.continueGettingFibonacciSeries();
	        Method method = c.getMethod(methodName);
	        method.invoke(skeleton);
			
			// THREAD END
		}
		}
	
	}
}
