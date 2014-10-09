package rmilab.client;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import rmilab.utilities.FibonacciInterface;
import rmilab.utilities.RMIMessage;
import rmilab.utilities.RMIMessage.MessageType;
import rmilab.utilities.RMIMessageDelivery;
import rmilab.utilities.RemoteObjRef;
import rmilab.utilities.ReverseInterface;

/**
 * This Client gets the appropriate information from standard-in to make
 * appropriate method calls for the example classes, Fibonacci and ReverseString
 * that is provided.
 * 
 * @author Nandini Ramakrishnan and Shri Karthikeyan
 * 
 */
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {
		boolean userSaysYes = true;
		while (userSaysYes) {
			Scanner in = new Scanner(System.in);
			String request, obj = null, registryPort = null, serviceName = null, methodName = null, registryHost = null;

			/* Get client request from standard in */
			Object arg;
			System.out.println("Enter your request");
			System.out
					.println("<object-name> <registry-host> <registry-port> <service-name> <method-name> <args...>");
			request = in.nextLine();

			/* Parse and save info */
			String[] parts = request.split(" ");
			obj = parts[0];
			registryHost = parts[1];
			registryPort = parts[2];
			serviceName = parts[3];
			methodName = parts[4];
			
			
			arg = parts[5];




			/* Lookup the remote object reference from the registry */
			RemoteObjRef ror = (RemoteObjRef) performLookup(registryHost,
					Integer.parseInt(registryPort), serviceName, methodName,
					arg);

			/* Print confirmation of found reference */
			System.out.println("Client: Here's you reference! " + ror);

			/* This is for Example 1: Fib */
			if (serviceName.equals("fib")) {
				/* Create and localize stub */
				FibonacciInterface stub = (FibonacciInterface) ror
						.localise(registryHost);
				
				/* Properly pass in as many arguments as needed */
				Object[] params = new Object[1];
				params[0] = arg;
				
				/* Call getFibonacciSeries method on the stub */
				ArrayList<Integer> result = stub
						.getFibonacciSeries(Integer.parseInt((String)params[0]));
				System.out.println(result);
			}
			/* This is for Example 2: Rev */
			else if (serviceName.equals("rev")) {
				ReverseInterface stub = (ReverseInterface) ror
						.localise(registryHost);
				
				/* Properly pass in as many arguments as needed */
				Object[] params = new Object[1];
				params[0] = arg;
				
				String result = stub.reverseString((String) params[0]);
				System.out.println(result);
			}

			else {
				System.out.println("Invalid Service Name");
			}

			/* See if this client wants to make any more requests */
			System.out
					.println("Do you want to make another request? Say Y or N");
			String response = in.nextLine();
			if (response.equals("N")) {
				userSaysYes = false;
				System.out.println("Goodbye!");
			}
		}
	}

	/**
	 * Makes a connection with the registry to look up and return remote object
	 * reference if found, else returns a null value.
	 * 
	 * @param registryHost
	 *            - the registry's host
	 * @param registryPort
	 *            - the registry's port #
	 * @param serviceName
	 *            - is the key into registry
	 * @param methodName
	 *            - method that we are looking for
	 * @param argNo
	 *            - argument we are passing in
	 * @return - Remote Object reference if found else return null
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object performLookup(String registryHost, int registryPort,
			String serviceName, String methodName, Object argNo)
			throws UnknownHostException, IOException, ClassNotFoundException {
		RemoteObjRef ror;
		/* Formatting arguments into array to fit RMIMessage requirements */
		Object[] params = new Object[1];
		params[0] = argNo;

		/* Create a lookup request for ror */
		RMIMessage msg = new RMIMessage(MessageType.LOOKUP, serviceName,
				methodName, params);

		/* Send request to registry */
		RMIMessageDelivery rmd = new RMIMessageDelivery(registryHost,
				registryPort);
		rmd.sendMessage(msg);

		/* Get reply from server and process */
		RMIMessage refmsg = rmd.getMessage();
		System.out.println("Server responded with: " + refmsg);

		/* Different possible replies */
		if (refmsg.getType() == MessageType.FOUND) {
			ror = refmsg.getRemoteObject();
			System.out.println("Reference was found : " + ror);
			return ror;
		} else if (refmsg.getType() == MessageType.EXCEPTION) {
			System.out
					.println("There was an exception returned from the server");
			return null;
		} else {
			System.out.println("Reference was NOT found");
			return null;
		}

	}

}
