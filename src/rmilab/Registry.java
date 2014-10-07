package rmilab;

import java.util.HashMap;
import java.util.Set;

import rmilab.utilities.RemoteObjRef;

public class Registry {
  private static HashMap<String, RemoteObjRef> remoteObjects = new HashMap<String, RemoteObjRef>();
  public static final int PORTNUM = 1234;
  
  public static void bind(String serviceName, RemoteObjRef obj)
  {
	  if(!remoteObjects.containsKey(serviceName))
	  {
	    remoteObjects.put(serviceName,obj);
	    System.out.println("Object:" + serviceName + " has been binded");
	  }
	  
	  else
		  System.out.println("There is already an object with the key in the registry");
  }
  
  public  void unbind(int objKey)
  {
	  if(remoteObjects.containsKey(objKey))
	  {
		  remoteObjects.remove(objKey);
		  System.out.println("Object:" + objKey + " has been successfully unbound");
	  }
	  
	  else
		  System.out.println("There isn't an object registered with that key to unbind");
  }
  
  public RemoteObjRef lookup(String serviceName)
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

