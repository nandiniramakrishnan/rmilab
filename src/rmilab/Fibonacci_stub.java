package rmilab;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class Fibonacci_stub {
	ArrayList<Integer> al;
	String host = "hostname";
	int port = 2345;
	public Fibonacci_stub(ArrayList<Integer> al) {
		this.al = al;
	}
	
	public ArrayList<Integer> getFibonacciSeries(int number) throws IOException, ClassNotFoundException {
		/*
		 * marshal an array containing object name, method name, arguments
		 */
		
		/* create socket? */
		Socket s = new Socket(host, port);
		String[] methodInvocation = {"Fibonacci","getFibonacciSeries",Integer.toString(number)};
		ObjectOutputStream objectOutput = (ObjectOutputStream) s.getOutputStream(); /* ask ta */
        objectOutput.writeObject(s);
        
        /*
         * unmarshal the return value
         */
        ObjectInputStream objectInput = (ObjectInputStream) s.getInputStream();	/* ask ta */
        al = (ArrayList<Integer>)objectInput.readObject();
        
		return al;
	}
	
}
