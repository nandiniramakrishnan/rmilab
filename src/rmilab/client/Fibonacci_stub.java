package rmilab.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import rmilab.utilities.FibonacciInterface;


public class Fibonacci_stub implements FibonacciInterface{
	
	
	public ArrayList<Integer> getFibonacciSeries() throws IOException, ClassNotFoundException {
		/*
		 * marshal an array containing object name, method name, arguments
		 */
		ArrayList<Integer> al;
		InetAddress ip = InetAddress.getLocalHost();
		int port = 9999;
		/* create socket? */
		Socket s = new Socket(ip, 9999);
		String[] methodInvocation = {"Fibonacci","getFibonacciSeries",Integer.toString(10)};
		ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream()); /* ask ta */
        objectOutput.writeObject(methodInvocation);
        
        /*
         * unmarshal the return value
         */
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());	/* ask ta */
        al = (ArrayList<Integer>)objectInput.readObject();
        
		return al;
	}
	
}
