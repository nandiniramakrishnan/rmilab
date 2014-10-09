package rmilab.utilities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class is an abstraction that allows for the easy delivery of RMIMessages
 * It creates socket connections and allows to recieve and send things via a
 * particular socket.
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 */
public class RMIMessageDelivery implements Serializable {
	Socket socket;

	/**
	 * Constructor that creates a connection via a socket that is passed
	 * 
	 * @param s
	 *            - socket where communication will occur
	 */
	public RMIMessageDelivery(Socket s) {
		this.socket = s;
	}

	/**
	 * Constructor that creates a connection via a hostname and port num
	 * 
	 * @param host
	 *            - hostname of connection
	 * @param port
	 *            - port that we need to listen/get info from
	 */
	public RMIMessageDelivery(String host, int port) {
		try {
			this.socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = -1808777860083306760L;

	/**
	 * Allows to write messages to a particular socket
	 * 
	 * @param message
	 *            - message to be written in socket
	 * @throws IOException
	 */
	public void sendMessage(RMIMessage message) throws IOException {
		Object packagedMessage = (Object) message;
		ObjectOutputStream out = new ObjectOutputStream(
				socket.getOutputStream());
		out.writeObject(packagedMessage);
	}

	/**
	 * Allows to read in messages from a particular socket
	 * 
	 * @return - RMIMessage that has been read from socket
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public RMIMessage getMessage() throws IOException, ClassNotFoundException {
		RMIMessage message = null;
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		message = (RMIMessage) in.readObject();
		return message;
	}

}
