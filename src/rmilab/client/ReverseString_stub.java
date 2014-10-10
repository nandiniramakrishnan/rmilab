package rmilab.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import rmilab.utilities.FibonacciInterface;
import rmilab.utilities.RMIMessage;
import rmilab.utilities.RMIMessage.MessageType;
import rmilab.utilities.RMIMessageDelivery;
import rmilab.utilities.ReverseInterface;

/**
 * The ReverseString stub is on the client side and is responsible for making
 * connections with the ReverseString_skeleton which is on the Server side. The
 * client receives, processes, and sends the client the info for the request
 * 
 * @author Nandini Ramakrishnan and Shri Karthikeyan
 * 
 */
public class ReverseString_stub implements ReverseInterface {
	static final int PORT = 9999;
	String hostname;

	/**
	 * Constructor that creates an instance of the stub. It takes in the host
	 * name of the skeleton so it can send communications.
	 * 
	 * @param host
	 */
	public ReverseString_stub(String host) {
		this.hostname = host;
	}

	/*
	 * @see rmilab.utilities.FibonacciInterface#getFibonacciSeries(int)
	 */
	public String reverseString(String str) throws IOException, ClassNotFoundException  {

		/* Create a socket connection with the Skeleton */
		Socket s = new Socket(hostname, PORT);

		/* Wrap the parameters */
		Object[] params = new Object[1];
		params[0] = str;

		RMIMessage methodInvocation = new RMIMessage(MessageType.METHOD, "rev",
				"rmilab.server.ReverseString", "reverseString", params);

		/* Send Method Request to skeleton */
		RMIMessageDelivery rmd = new RMIMessageDelivery(s);
		rmd.sendMessage(methodInvocation);

		/* Get response from skeleton */
		RMIMessage response = rmd.getMessage();

		/* Process response from skeleton */
		if (response.getType() == MessageType.RETURN)
			return (String) response.getReturnValue();
		else
			return null;
	}


}


