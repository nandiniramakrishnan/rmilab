package rmilab.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import rmilab.utilities.FibonacciInterface;
import rmilab.utilities.RMIMessage;
import rmilab.utilities.RMIMessageDelivery;
import rmilab.utilities.RemoteObjRef;

public class Client implements Serializable{
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		Scanner in = new Scanner(System.in);
		String request, obj = null, registryHost = null, registryPort = null, 
				serviceName = null;

		System.out.println("Enter your request");
		System.out.println("<object-name> <registry-host> <registry-port> <service-name>");
		request = in.nextLine();
		String[] parts = request.split(" ");
		obj = parts[0]; 
		registryHost = parts[1];
		registryPort = parts[2];
		serviceName = parts[3];
		System.out.println(obj);
		System.out.println(registryHost);
		System.out.println(registryPort);
		System.out.println(serviceName);
		System.out.println("registryPort="+registryPort);
		RemoteObjRef ror = (RemoteObjRef)performLookup(registryHost, Integer.parseInt(registryPort), 
				serviceName);
		System.out.println("Client: Here's you reference! "+ror);
	    FibonacciInterface stub = (FibonacciInterface)ror.localise();
	    ArrayList<Integer> result = stub.getFibonacciSeries();
	    System.out.println(result);
	}	

	public static Object performLookup(String registryHost, int registryPort, 
			String serviceName) throws UnknownHostException, IOException, 
			ClassNotFoundException {
		RemoteObjRef ror;
		RMIMessage msg = new RMIMessage();
		msg.type="IDENTIFIER";
		msg.serviceName = serviceName;
		RMIMessageDelivery rmd = new RMIMessageDelivery(registryHost, registryPort);
		rmd.sendMessage(msg);
		RMIMessage refmsg = rmd.getMessage();
		ror = refmsg.getRemoteObject();
		/*
		Socket s = new Socket(registryHost, registryPort);
		ObjectOutputStream registryStream = new ObjectOutputStream(s.getOutputStream());
        registryStream.writeObject(serviceName);
        
		*/
        
        //registryStream.close();
        return ror;
	}
	/*
	public static Object getReference(Socket s) throws IOException,
	ClassNotFoundException {
		ObjectInputStream referenceStream = new ObjectInputStream(s.getInputStream());	
        Object ror = referenceStream.readObject();
        return ror;
	}*/
}
