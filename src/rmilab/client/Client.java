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
import rmilab.utilities.RMIMessage.MessageType;
import rmilab.utilities.RMIMessageDelivery;
import rmilab.utilities.RemoteObjRef;

public class Client implements Serializable{
	//public static String registryHost;
	//public static int argNo;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		Scanner in = new Scanner(System.in);
		String request, obj = null, registryPort = null, 
				serviceName = null, methodName = null, registryHost = null;
		int argNo = 0;

		System.out.println("Enter your request");
		System.out.println("<object-name> <registry-host> <registry-port> <service-name> <method-name> <args...>");
		request = in.nextLine();
		String[] parts = request.split(" ");
		obj = parts[0]; 
		registryHost = parts[1];
		registryPort = parts[2];
		serviceName = parts[3];
		methodName = parts[4];
		argNo = Integer.parseInt(parts[5]);
		Object[] params = new Object[1];
		params[0] = argNo;
		System.out.println(obj);
		System.out.println(registryHost);
		System.out.println(registryPort);
		System.out.println(serviceName);
		System.out.println(methodName+" args:"+argNo);
		System.out.println("registryPort="+registryPort);
		RemoteObjRef ror = (RemoteObjRef)performLookup(registryHost, Integer.parseInt(registryPort), 
				serviceName, methodName, argNo);
		
		System.out.println("Client: Here's you reference! "+ror);
	    FibonacciInterface stub = (FibonacciInterface)ror.localise(registryHost);
	    ArrayList<Integer> result = stub.getFibonacciSeries((Integer)params[0]);
	    System.out.println(result);
	}	

	public static Object performLookup(String registryHost, int registryPort, 
			String serviceName,String methodName, int argNo) throws UnknownHostException, IOException, 
			ClassNotFoundException {
		RemoteObjRef ror;
		//RMIMessage msg = new RMIMessage();
		//msg.setServiceName(serviceName);
		//msg.setMethodName(methodName);
		//msg.setParams(new Object[]{new Integer(argNo)}); 
		Object [] params = new Object[1];
		params[0] = argNo;
		RMIMessage msg = new RMIMessage(MessageType.LOOKUP,serviceName, methodName,params);
		RMIMessageDelivery rmd = new RMIMessageDelivery(registryHost, registryPort);
		rmd.sendMessage(msg);
		RMIMessage refmsg = rmd.getMessage();
		System.out.println("REFMSG!!!!: "+refmsg);
		if(refmsg.getType() == MessageType.FOUND)
		{
		  ror = refmsg.getRemoteObject();
		  System.out.println("ROOOOR!!!! : "+ror);
		  return ror;
		}
		/*
		Socket s = new Socket(registryHost, registryPort);
		ObjectOutputStream registryStream = new ObjectOutputStream(s.getOutputStream());
        registryStream.writeObject(serviceName);
        
		*/
        return null;
        //registryStream.close();
	}
	/*
	public static Object getReference(Socket s) throws IOException,
	ClassNotFoundException {
		ObjectInputStream referenceStream = new ObjectInputStream(s.getInputStream());	
        Object ror = referenceStream.readObject();
        return ror;
	}*/
}
