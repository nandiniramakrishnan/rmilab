package rmilab.utilities;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;

/**
 * The RemoteObjRef class is an object that represents the remote object
 * reference. It has key attributes of a remote object reference such as the
 * ipAddress, portNum, identifier, and interfaceName.
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 */
public class RemoteObjRef implements Serializable {

	private static final long serialVersionUID = -2221935140598730230L;
	private InetAddress ipAddress;
	private int portNum;
	private String interfaceName;
	private String serviceName;

	/**
	 * Constructor for RemoteObjRef class
	 * 
	 * @param ipAddress
	 *            - ipAddress of where the ror resides
	 * @param portNum
	 *            - port Num of where it came from
	 * @param identifier
	 *            - the indentifier/key in the registry table
	 * @param interfaceName
	 *            - name of the interface of ror
	 */
	public RemoteObjRef(InetAddress ipAddress, int portNum, String serviceName,
			String interfaceName) {
		this.ipAddress = ipAddress;
		this.portNum = portNum;
		this.serviceName = serviceName;
		this.interfaceName = interfaceName;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public int getPortNum() {
		return portNum;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Method localizes the remote object on a computer with a given hostname
	 * and returns the stub of the ror for that host.
	 * 
	 * @param hostname
	 *            - Given the hostname of the object where ror should be
	 *            localised.
	 * @return - stub of the ror
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Object localise(String hostname) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		/* Returns the class object associated with the class or interface */
		Class c = Class.forName("rmilab.client." + interfaceName + "_stub");
		Constructor constructor = c.getDeclaredConstructor(String.class);
		Object stub = constructor.newInstance(hostname);
		return stub;
	}

}
