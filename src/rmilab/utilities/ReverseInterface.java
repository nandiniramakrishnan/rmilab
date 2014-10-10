package rmilab.utilities;

import java.io.IOException;

/**
 * This is the interface the defines ReverseString. ReverseString takes in a string str and
 * returns the reversed form of the string
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 */
public interface ReverseInterface extends Remote440 {
	public String reverseString(String str) throws IOException, ClassNotFoundException;
}
