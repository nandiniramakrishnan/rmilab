package rmilab;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Fibonacci_stub {
	ArrayList<Integer> al;
	
	public Fibonacci_stub(ArrayList<Integer> al) {
		this.al = al;
	}
	
	public ArrayList<Integer> getFibonacciSeries(int number) {
		/*
		 * marshal an array containing object name, method name, arguments
		 */
		
		/* create socket? */
		String[] methodInvocation = {"Fibonacci","getFibonacciSeries",Integer.toString(number)};
		ObjectOutputStream objectOutput = remotecall.getOutputStream(); /* ask ta */
        objectOutput.writeObject(s);
        
        /*
         * unmarshal the return value
         */
        ObjectInputStream objectInput = remotecall.getInputStream();	/* ask ta */
        al = (ArrayList<Integer>)objectInput.readObject();
        
		return al;
	}
	
}
