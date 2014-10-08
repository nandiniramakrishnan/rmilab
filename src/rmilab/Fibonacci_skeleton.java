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
public class Fibonacci_skeleton {
	//static int port = 9999;
	static ServerSocket s;
	public Fibonacci_skeleton(ServerSocket s) {
		
		this.s = ServerSocket(9999);
	}
	private ServerSocket ServerSocket(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	public static ArrayList<Integer> getFibonacciSeries() throws IOException, ClassNotFoundException, 
	NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, 
	IllegalArgumentException, InvocationTargetException {
		/* Unmarshal the array */
		System.out.println("omg WE'RE IN THE SKELETON");
		System.out.println("skeleton's hostname = "+(InetAddress.getLocalHost()).getHostName());
		System.out.println("Serversocket is listening on port 9999 in the skeleton");
		Socket clientSocket;
		Method method;
		while (true) {
			clientSocket = s.accept();
			System.out.println("connection has been made between stub and skeleton");
		
		/* DO WE NEED SOCKETS HERE? WHERE IS THE INPUT STREAM COMING FROM */
        ObjectInputStream objectInput = new ObjectInputStream(clientSocket.getInputStream());	/* ask ta */
        String[] methodInvocation= (String[])objectInput.readObject();
		Class myObjectClass = Class.forName("rmilab.Fibonacci");
		Constructor constructor = myObjectClass.getConstructor(new Class[] {});
		Fibonacci f = (Fibonacci) constructor
				.newInstance();
        method = f.getClass().getMethod(methodInvocation[1]);
        Object value = method.invoke(f);
        
        /* marshal the return value */
		ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream()); /* ask ta */
        objectOutput.writeObject(value);
		}
	}
}
