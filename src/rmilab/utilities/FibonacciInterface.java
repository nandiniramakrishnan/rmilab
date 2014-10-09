package rmilab.utilities;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * This is the interface the defines Fibonacci Fibonacci takes in a num and
 * returns all the fibonacci numbers up to n
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 */
public interface FibonacciInterface extends Remote440 {
	public ArrayList<Integer> getFibonacciSeries(int num) throws IOException,
			ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException;
}
