package rmilab.utilities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class RMIMessageDelivery implements Serializable  {
    Socket socket;
	public RMIMessageDelivery(Socket s) {
		this.socket = s;
	}
	
	public RMIMessageDelivery(String host, int port) {
		try {
			this.socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = -1808777860083306760L;

	public void sendMessage(RMIMessage message) throws IOException
     {
    	 Object packagedMessage = (Object) message;
    	 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    	 out.writeObject(packagedMessage);
    	 //socket.close();
     }
     
     public RMIMessage getMessage() throws IOException, ClassNotFoundException
     {
    	 RMIMessage message = null;
    	 ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    	 message = (RMIMessage) in.readObject();
    	 return message;
     }
     

}
