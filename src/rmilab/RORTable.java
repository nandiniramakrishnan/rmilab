package rmilab;

import java.util.HashMap;
import java.util.Set;

public class RORTable {
  private static HashMap<Integer, RemoteObjRef> remoteObjects = new HashMap<Integer, RemoteObjRef>();
  public static final int PORTNUM = 1234;
  
  public void bind(int objKey, RemoteObjRef obj)
  {
	  if(!remoteObjects.containsKey(objKey))
	  {
	    remoteObjects.put(objKey,obj);
	    System.out.println("Object:" + objKey + " has been binded");
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
  
  public RemoteObjRef lookup(int objKey)
  {
	  if(remoteObjects.containsKey(objKey))
	  {
		  System.out.println("Found Object");
		  return remoteObjects.get(objKey);
	  }
	  
	  else
	  {
		  System.out.println("Can't find Object:" + objKey + " in lookup");
		  return null;
	  }
	  
  }
  
  public Set<Integer> list()
  {
	  return remoteObjects.keySet();
  }
  
}

