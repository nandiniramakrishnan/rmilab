package rmilab.server;

import rmilab.utilities.ReverseInterface;

public class ReverseString implements ReverseInterface {
	public String reverseString(String str) {
		String reverse = "";
		int length = str.length();

		for ( int i = length - 1 ; i >= 0 ; i-- ) {
			reverse = reverse + str.charAt(i);
		}
		
		return reverse;
	}
}
