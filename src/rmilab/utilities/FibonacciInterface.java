package rmilab.utilities;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public interface FibonacciInterface extends Remote440 {
	public ArrayList<Integer> getFibonacciSeries() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
