package rmilab;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class Fibonacci_skeleton {

	public void stuff(String[] args) {
		/* Unmarshal the array */
		Method method;
		
		/* DO WE NEED SOCKETS HERE? WHERE IS THE INPUT STREAM COMING FROM */
        ObjectInputStream objectInput = remotecall.getInputStream();	/* ask ta */
        String[] methodInvocation= (String[])objectInput.readObject();
		Class myObjectClass = Class.forName(methodInvocation[0]);
		Constructor constructor = myObjectClass.getConstructor(new Class[] {
				Integer.class });
		Fibonacci f = (Fibonacci) constructor
				.newInstance(0, args);
        method = f.getClass().getMethod(methodInvocation[1]);
        Object value = method.invoke(f);
        
        /* marshal the return value */
		ObjectOutputStream objectOutput = remotecall.getOutputStream(); /* ask ta */
        objectOutput.writeObject(value);
	}
}
