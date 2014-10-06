package rmilab;

import java.util.ArrayList;

public class Fibonacci implements FibonacciInterface {
	int num;
	
	public Fibonacci(int number) {
		this.num = number;
		// bind to the registry
	}

	public ArrayList<Integer> getFibonacciSeries() {
		ArrayList<Integer> al = new ArrayList<Integer>(num);
		if (num == 0) {
			return al;
		}
		al.add(0);
		al.add(1);
		for (int i = 2; i < num; i++) {
			al.add(al.get(i-1) + al.get(i-2));
		}
		return al;
	}
}
