package rmilab;

import java.util.ArrayList;
import rmilab.utilities.FibonacciInterface;

public class Fibonacci implements FibonacciInterface {

	public ArrayList<Integer> getFibonacciSeries(int num) {
		ArrayList<Integer> al = new ArrayList<Integer>(num);
		al.add(0);
		al.add(1);
		for (int i = 2; i < num; i++) {
			al.add(al.get(i-1) + al.get(i-2));
		}
		return al;
	}
}
