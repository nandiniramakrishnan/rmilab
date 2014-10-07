package rmilab;

import java.util.ArrayList;
import rmilab.utilities.FibonacciInterface;

public class Fibonacci implements FibonacciInterface {

	public ArrayList<Integer> getFibonacciSeries() {
		ArrayList<Integer> al = new ArrayList<Integer>(10);
		/*if (num == 0) {
			return al;
		}*/
		al.add(0);
		al.add(1);
		for (int i = 2; i < 10; i++) {
			al.add(al.get(i-1) + al.get(i-2));
		}
		return al;
	}
}
