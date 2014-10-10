package rmilab.server;

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

/**
 * The RMITransactons class will be called by RMI class and started in a new
 * thread. The RMITransactions class is responsible for recieving,processing and
 * sending responses to client requests.
 * 
 * @author Nandini Ramakrishnan and Shri Karthikeyan
 * 
 */
public class RMITranscations implements Runnable {
	Socket clientSocket;
	Registry tbl;

	/**
	 * Constructor that takes in the client socket connection as well as the
	 * Regsitry in order to create RMITransactions.
	 * 
	 * @param socket
	 *            - client socket
	 * @param reg
	 *            - registry of the server
	 */
	public RMITranscations(Socket socket, Registry reg) {
		this.clientSocket = socket;
		this.tbl = reg;
	}

	/**
	 * Method is responsible for handling/responding to client requests
	 */
	@Override
	public void run() {
		RMIMessageDelivery rmd = new RMIMessageDelivery(clientSocket);
		RemoteObjRef remoteObject;
		RMIMessage inputMsg;
		try {
			/* Read in message from client */
			inputMsg = rmd.getMessage();

			/* Process different types of messages from Client */

			/* Lookup ROR in registry */
			if (inputMsg.getType() == MessageType.LOOKUP) {
				/* Parse message for info */
				String key = inputMsg.getServiceName();
				String methodName = inputMsg.getMethodName();
				Object[] arglist = inputMsg.getParams();

				/* Lookup using serviceName as key to find ROR */
				remoteObject = tbl.lookup(key);

				/*
				 * Reply to Client of found object. Note: if not found table
				 * handles that case
				 */
				RMIMessage outputMsg = new RMIMessage(MessageType.FOUND,
						remoteObject);
				rmd.sendMessage(outputMsg);

				/* Start and invoke the skeleton */
				String interfaceName = remoteObject.getInterfaceName();
				Class c = Class
						.forName("rmilab.server." + interfaceName + "_skeleton");
				Object skeleton = c.newInstance();
				Method method = c.getMethod(methodName);
				method.invoke(skeleton);

			}
			/* Unbind request from Client */
			else if (inputMsg.getType() == MessageType.UNBIND) {
				Registry.unbind(inputMsg.getServiceName());
			}
			/* Rebind request from Client */
			else if (inputMsg.getType() == MessageType.REBIND) {
				Registry.bind(inputMsg.getServiceName(),
						inputMsg.getRemoteObject());
			}
			/* Exception given from Client */
			else if (inputMsg.getType() == MessageType.EXCEPTION) {
				try {
				} catch (Exception e) {

				}
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
