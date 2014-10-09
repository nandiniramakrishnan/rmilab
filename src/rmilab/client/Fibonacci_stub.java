package rmilab.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import rmilab.utilities.FibonacciInterface;
import rmilab.utilities.RMIMessage;
import rmilab.utilities.RMIMessage.MessageType;


public class Fibonacci_stub implements FibonacciInterface{
	static final int PORT = 5678;
	String hostname;
	int fibNum;
	public Fibonacci_stub(String host, Object[] params) {
		this.hostname = host;
		this.fibNum = (int)params[0];
	}
	
	public ArrayList<Integer> getFibonacciSeries() throws IOException, ClassNotFoundException {
		/*
		 * marshal an array containing object name, method name, arguments
		 */
		
		//InetAddress ip = InetAddress.getLocalHost();
		
		/* create socket? */
		Socket s = new Socket(hostname, PORT);
		// USE RMI MESSAGE FOR THIS INSTEAD
		Object [] params = new Object[1];
		//params[0] = argNo;
		
		RMIMessage methodInvocation = new RMIMessage(MessageType.METHOD,"fib", "rmilab.Fibonacci", "getFibonacciSeries",params);
		//String[] methodInvocation = {"Fibonacci","getFibonacciSeries",Integer.toString(10)};
		ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream()); /* ask ta */
        objectOutput.writeObject(methodInvocation);
        
        /*
         * unmarshal the return value
         */
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());	/* ask ta */
        RMIMessage response = (RMIMessage) objectInput.readObject();
        if(response.getType() == MessageType.RETURN)
        	return (ArrayList<Integer>) response.getReturnValue();
        else 
        	return null;
	}
	
}
