package rmilab;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import edu.cmu.cs.cs440.hw1.MigratableProcess;


/* This is the server in our RMI Application */
public class RMI {
	static String host;
	static int port;

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String InitialClassName = args[0];	/* Fibonacci */
		String registryHost = args[1];
		int registryPort = Integer.parseInt(args[2]);	
		String serviceName = args[3];		/* What is the service name? the method name? */

		host = (InetAddress.getLocalHost()).getHostName();	/* Host name of server */
		port = 12345;	/* Port of the server. Hard-coded */

		Class initialclass = Class.forName(InitialClassName);
		Class initialskeleton = Class.forName(InitialClassName+"_skel");

		// you should also create a remote object table here.
		// it is a table of a ROR and a skeleton.
		// as a hint, I give such a table's interface as RORtbl.java. 
		RORTable tbl = new RORTable();

		// DO A LOOK UP HERE? RETURN THE LOOKUP TO THE SERVER

		// after that, you create one remote object of fibonacci.
		Object o = initialclass.newInstance(); /* new Fibonacci() */

		// then register it into the table.
		//tbl.addObj(host, port, o);
		// IS THIS THE SAME AS BINDING?

		//tbl.bind(1, (RemoteObjRef) o);

		// create a socket.
		ServerSocket serverSoc = new ServerSocket(port);
		while (true)
		{
			Socket clientSocket = null;
			try {
				clientSocket = serverSoc.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectInputStream in;
			in = new ObjectInputStream(clientSocket.getInputStream());
			int objKey = (Integer) in.readObject();
			RemoteObjRef ror = tbl.lookup(objKey);

			// (1) receives an invocation request.
			// (2) creates a socket and input/output streams.
			// (3) gets the invocation, in martiallled form.
			// (4) gets the real object reference from tbl.
			// (5) Either:
			//      -- using the interface name, asks the skeleton,
			//         together with the object reference, to unmartial
			//         and invoke the real object.
			//      -- or do unmarshalling directly and involkes that
			//         object directly.
			// (6) receives the return value, which (if not marshalled
			//     you should marshal it here) and send it out to the 
			//     the source of the invoker.
			// (7) closes the socket.
			clientSocket.close();
		}
	}
}
