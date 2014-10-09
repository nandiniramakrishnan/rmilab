package rmilab;

import java.io.IOException;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import rmilab.utilities.RemoteObjRef;

/**
 * @author Nandini Ramakrishnan and Shri Karthikeyan The RMI class starts the
 *         registry and server and listens for incoming client requests. When
 *         client requests come in, it starts a new thread in order to process
 *         multiple requests from various clients.
 * 
 */
public class RMI implements Serializable {
	private static final long serialVersionUID = 1L;
	static int port = 1234;

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {
		/* Registry and Server's host name */
		System.out.println("your hostname = "
				+ (InetAddress.getLocalHost()).getHostName());

		/* Start Registry */
		Registry tbl = new Registry();

		/* IP Address of the server */
		InetAddress ipAddress = InetAddress.getLocalHost();

		/* Bind examples to the registry */
		RemoteObjRef ror = new RemoteObjRef(ipAddress, port, "fib", "Fibonacci");
		Registry.bind("fib", ror);
		System.out.println("Your reference is binded. ror=" + ror);

		/* Start Server */
		ServerSocket serverSoc = new ServerSocket(port);
		System.out.println("Server has started listening on the port 1234");

		/* Listen for incoming client requests */
		while (true) {
			Socket clientSocket = null;
			clientSocket = serverSoc.accept();
			/* Start new thread with client info */
			new Thread(new RMITranscations(clientSocket, tbl)).start();
		}

	}

}
