package rmilab;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import rmilab.utilities.*;
import rmilab.utilities.RMIMessage.MessageType;

/**
 * 
 * @author Nandini Ramakrishnan and Shri Karthikeyan This class defines the
 *         server side skeleton for fibonacci that is responsible for making the
 *         fibonacci computations and sending them to the fibonacci stub.
 * 
 */
public class ReverseString_skeleton {
	static ServerSocket s;

	/*
	 * Constructor to create an instance of the fibonacci skeleton instance
	 * Creates a new Socket connection
	 */
	public ReverseString_skeleton() {

		try {
			ReverseString_skeleton.s = new ServerSocket(9999);
		} catch (IOException e) {

		}
	}

	public static String reverseString() throws IOException,
			ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

        /* Notice that we are in skeleton */
		System.out.println("Serversocket is listening on port 9999 in the skeleton");
		Socket clientSocket;
		Method method;
		
		/* Continually listen for any client requests via stub */
		while (true) {
			clientSocket = s.accept();
			System.out.println("Connection has been made between stub and skeleton");
			System.out.println("---------------------------------------------------");
			
			/* Read in message request from stub */
			RMIMessageDelivery rmd = new RMIMessageDelivery(clientSocket);
			RMIMessage inputMessage = rmd.getMessage();
			RMIMessage outputMessage ;

			/* Process method request and perform computation */
			if (inputMessage.getType() == MessageType.METHOD) {
				Class myObjectClass = Class.forName("rmilab.ReverseString");
				Constructor constructor = myObjectClass
						.getConstructor(new Class[] {});
				ReverseString f = (ReverseString) constructor.newInstance();
				method = f.getClass().getMethod(inputMessage.getMethodName(),
						String.class);
				Object value = method.invoke(f, inputMessage.getParams()[0]);
			    outputMessage = new RMIMessage(MessageType.RETURN,
						value);
                /* Return output of method */
				rmd.sendMessage(outputMessage);
			}
			
			/* Unexpected request */
			else
			{
				outputMessage = new RMIMessage(MessageType.EXCEPTION, "Unable to make request");
				rmd.sendMessage(outputMessage);
			}
		}
	}
}
