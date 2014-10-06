package rmilab;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;


public class Fibonacci_skeleton {
	String host = "hostname";
	int port = 2345;
	public void stuff(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/* Unmarshal the array */
		Socket s = new Socket(host, port);
		Method method;
		
		/* DO WE NEED SOCKETS HERE? WHERE IS THE INPUT STREAM COMING FROM */
        ObjectInputStream objectInput = (ObjectInputStream) s.getInputStream();	/* ask ta */
        String[] methodInvocation= (String[])objectInput.readObject();
		Class myObjectClass = Class.forName(methodInvocation[0]);
		Constructor constructor = myObjectClass.getConstructor(new Class[] {
				Integer.class });
		Fibonacci f = (Fibonacci) constructor
				.newInstance(0, args);
        method = f.getClass().getMethod(methodInvocation[1]);
        Object value = method.invoke(f);
        
        /* marshal the return value */
		ObjectOutputStream objectOutput = (ObjectOutputStream) s.getOutputStream(); /* ask ta */
        objectOutput.writeObject(value);
	}
}
