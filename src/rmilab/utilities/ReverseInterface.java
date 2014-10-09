package rmilab.utilities;

import java.io.IOException;

public interface ReverseInterface extends Remote440 {
	public String reverseString(String str) throws IOException, ClassNotFoundException;
}
