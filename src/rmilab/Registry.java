package rmilab;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import rmilab.utilities.RemoteObjRef;

/**
 * Registry is responsible keeping track of all the remote object references The
 * registry is represented as a ConcurrentHashMap and has keys as the
 * ServiceName and value as the ROR.
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 */
public class Registry {
	private static ConcurrentHashMap<String, RemoteObjRef> remoteObjects = new ConcurrentHashMap<String, RemoteObjRef>();
	public static final int PORTNUM = 1234;

	/**
	 * Adds a ror with given service name to the registry
	 * 
	 * @param serviceName
	 *            - key
	 * @param obj
	 *            - value, remote object reference
	 */
	public static void bind(String serviceName, RemoteObjRef obj)

	{
		if (!remoteObjects.containsKey(serviceName)) {
			remoteObjects.put(serviceName, obj);
			System.out.println("Object:" + serviceName + " has been binded");
		}

		else
			System.out
					.println("There is already an object with the key in the registry");
	}

	/**
	 * Removes a ror given the objectKey from the registry
	 * 
	 * @param objKey
	 *            - key into the HashMap
	 */
	public static void unbind(String objKey) {
		if (remoteObjects.containsKey(objKey)) {
			remoteObjects.remove(objKey);
			System.out.println("Object:" + objKey
					+ " has been successfully unbound");
		}

		else
			System.out
					.println("There isn't an object registered with that key to unbind");
	}

	/**
	 * Looks up a partciular ROR given its key aka its serviceName
	 * 
	 * @param serviceName
	 *            - key into registry
	 * @return - ror corresponding to given key
	 */
	public static RemoteObjRef lookup(String serviceName)

	{
		if (remoteObjects.containsKey(serviceName)) {
			System.out.println("Found Object");
			return remoteObjects.get(serviceName);
		}

		else {
			System.out.println("Can't find Object:" + serviceName
					+ " in lookup");
			return null;
		}

	}

	/**
	 * Gives all the rors keys
	 * 
	 * @return - Set of All ror keys in registry
	 */
	public Set<String> list() {
		return remoteObjects.keySet();
	}

}
