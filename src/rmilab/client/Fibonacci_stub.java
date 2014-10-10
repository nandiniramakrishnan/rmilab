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

/**
 * The Fibonacci stub is on the client side and is responsible for making
 * connections with the Fibonaaci_skeleton which is on the Server side. The
 * client receives, processes, and sends the client the info for the request
 * 
 * @author Nandini Ramakrishnan and Shri Karthikeyan
 * 
 */
public class Fibonacci_stub implements FibonacciInterface {
	static final int PORT = 5678;
	String hostname;

	/**
	 * Constructor that creates an instance of the stub. It takes in the host
	 * name of the skeleton so it can send communications.
	 * 
	 * @param host
	 */
	public Fibonacci_stub(String host) {
		this.hostname = host;
	}

	/*
	 * @see rmilab.utilities.FibonacciInterface#getFibonacciSeries(int)
	 */
	public ArrayList<Integer> getFibonacciSeries(int num) throws IOException,
			ClassNotFoundException {

		/* Create a socket connection with the Skeleton */
		Socket s = new Socket(hostname, PORT);

		/* Wrap the parameters */
		Object[] params = new Object[1];
		params[0] = num;

		RMIMessage methodInvocation = new RMIMessage(MessageType.METHOD, "fib",
				"rmilab.server.Fibonacci", "getFibonacciSeries", params);

		/* Send Method Request to skeleton */
		RMIMessageDelivery rmd = new RMIMessageDelivery(s);
		rmd.sendMessage(methodInvocation);

		/* Get response from skeleton */
		RMIMessage response = rmd.getMessage();

		/* Process response from skeleton */
		if (response.getType() == MessageType.RETURN)
			return (ArrayList<Integer>) response.getReturnValue();
		else
			return null;
	}

}
