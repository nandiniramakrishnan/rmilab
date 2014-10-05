package rmilab;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class RMI {
	static String host;
	static int port;

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String InitialClassName = args[0];	/* Fibonacci */
		String registryHost = args[1];
		int registryPort = Integer.parseInt(args[2]);	
		String serviceName = args[3];		/* What is the service name? the method name? */

		// it should have its own port. assume you hardwire it.
		host = (InetAddress.getLocalHost()).getHostName();
		port = 12345;

		Class initialclass = Class.forName(InitialClassName);
		Class initialskeleton = Class.forName(InitialClassName+"_skel");

		// you should also create a remote object table here.
		// it is a table of a ROR and a skeleton.
		// as a hint, I give such a table's interface as RORtbl.java. 
		RORTable tbl = new RORTable();

		// after that, you create one remote object of fibonacci.
		Object o = initialclass.newInstance(); /* new Fibonacci() */

		// then register it into the table.
		//tbl.addObj(host, port, o);
		// IS THIS THE SAME AS BINDING?

		tbl.bind(1, (RemoteObjRef) o);

		// create a socket.
		ServerSocket serverSoc = new ServerSocket(port);
	}
}
