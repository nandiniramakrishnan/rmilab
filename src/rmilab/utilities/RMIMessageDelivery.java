package rmilab.utilities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class RMIMessageDelivery implements Serializable  {


	private static final long serialVersionUID = -1808777860083306760L;

	public void sendMessage(Socket s, RMIMessage message) throws IOException
     {
    	 Object packagedMessage = (Object) message;
    	 ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
    	 out.writeObject(packagedMessage);
     }
     
     public Object getMessage(Socket s) throws IOException, ClassNotFoundException
     {
    	 Object message = null;
    	 ObjectInputStream in = new ObjectInputStream(s.getInputStream());
    	 message = (Object) in.readObject();
    	 return message;
     }
     

}
