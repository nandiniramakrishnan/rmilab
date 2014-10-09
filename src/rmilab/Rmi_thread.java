package rmilab;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import rmilab.utilities.RMIMessage;
import rmilab.utilities.RMIMessageDelivery;
import rmilab.utilities.RMIMessage.MessageType;
import rmilab.utilities.RemoteObjRef;

public class Rmi_thread implements Runnable{
	Socket clientSocket;
	Registry tbl;
	public Rmi_thread(Socket socket, Registry reg) {
		this.clientSocket = socket;
		this.tbl = reg;
	}

	@Override
	public void run() {
		RMIMessageDelivery rmd = new RMIMessageDelivery(clientSocket);
		RemoteObjRef remoteObject;
		RMIMessage inputMsg;
		try {
			inputMsg = rmd.getMessage();
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
			else if (inputMsg.getType() == MessageType.UNBIND ) {
				Registry.unbind(inputMsg.getServiceName());
			}
			else if(inputMsg.getType() == MessageType.REBIND) {
				Registry.bind(inputMsg.getServiceName(), inputMsg.getRemoteObject());
			}
			else if(inputMsg.getType() == MessageType.EXCEPTION){
				try {
					throw inputMsg.getException();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


