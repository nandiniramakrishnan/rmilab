package rmilab;

import java.util.HashMap;
import java.util.Set;

<<<<<<< HEAD:src/rmilab/RORTable.java
public class RORTable {
  private static HashMap<String, RemoteObjRef> remoteObjects = new HashMap<String, RemoteObjRef>();
  public static final int PORTNUM = 1234;
  
  public void bind(String objKey, RemoteObjRef obj)
=======
import rmilab.utilities.RemoteObjRef;

public class Registry {
  private static HashMap<String, RemoteObjRef> remoteObjects = new HashMap<String, RemoteObjRef>();
  public static final int PORTNUM = 1234;
  
  public static void bind(String serviceName, RemoteObjRef obj)
>>>>>>> ac4dcb20674a2ad551b8cbfb219550a2f15180f4:src/rmilab/Registry.java
  {
	  if(!remoteObjects.containsKey(serviceName))
	  {
	    remoteObjects.put(serviceName,obj);
	    System.out.println("Object:" + serviceName + " has been binded");
	  }
	  
	  else
		  System.out.println("There is already an object with the key in the registry");
  }
  
  public  void unbind(String objKey)
  {
	  if(remoteObjects.containsKey(objKey))
	  {
		  remoteObjects.remove(objKey);
		  System.out.println("Object:" + objKey + " has been successfully unbound");
	  }
	  
	  else
		  System.out.println("There isn't an object registered with that key to unbind");
  }
  
<<<<<<< HEAD:src/rmilab/RORTable.java
  public RemoteObjRef lookup(String objKey)
=======
  public RemoteObjRef lookup(String serviceName)
>>>>>>> ac4dcb20674a2ad551b8cbfb219550a2f15180f4:src/rmilab/Registry.java
  {
	  if(remoteObjects.containsKey(serviceName))
	  {
		  System.out.println("Found Object");
		  return remoteObjects.get(serviceName);
	  }
	  
	  else
	  {
		  System.out.println("Can't find Object:" + serviceName + " in lookup");
		  return null;
	  }
	  
  }
  
  public Set<String> list()
  {
	  return remoteObjects.keySet();
  }
  
}

