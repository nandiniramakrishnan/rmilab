package rmilab;

import java.util.ArrayList;
import rmilab.utilities.FibonacciInterface;

/**
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan This class computes the all
 *         the fibonacci numbers up to n and returns an array list of them
 * 
 */
public class Fibonacci implements FibonacciInterface {

	public ArrayList<Integer> getFibonacciSeries(int num) {
		ArrayList<Integer> al = new ArrayList<Integer>(num);
		al.add(0);
		al.add(1);
		for (int i = 2; i < num; i++) {
			al.add(al.get(i - 1) + al.get(i - 2));
		}
		return al;
	}
}
