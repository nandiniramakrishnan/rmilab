package rmilab.utilities;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;

public class RemoteObjRef implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2221935140598730230L;
	private InetAddress ipAddress;
	private int portNum;
	private String interfaceName;
	private String serviceName;
	public RemoteObjRef(InetAddress ipAddress, int portNum, String identifier, String interfaceName)
	{
		this.ipAddress = ipAddress;
		this.portNum = portNum;
		this.setServiceName(identifier);
		this.interfaceName = interfaceName;
	}
	
	public InetAddress getIpAddress()
	{
		return ipAddress;
	}
	
	public int getPortNum()
	{
		return portNum;
	}
	
	
	public String getInterfaceName()
	{
		return interfaceName;
	}
	
	public Object localise(String hostname) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
/*
		Object skeleton = constructor.newInstance(new ServerSocket());
		System.out.println("here's your skeleton! "+skeleton);
		//Fibonacci_skeleton.continueGettingFibonacciSeries();
        Method method = c.getMethod(methodName);
        method.invoke(skeleton);
	*/	
		
		System.out.println("interfaceName="+interfaceName);
		/* Returns the class object associated with the class or interface */
		Class c = Class.forName("rmilab.client."+interfaceName + "_stub");
		Constructor constructor = c.getDeclaredConstructor(String.class);
		Object stub = constructor.newInstance(hostname);
		//Object stub = c.newInstance();
		if (stub != null) {
			System.out.println("stub is not null! stub="+stub);
		}
		return stub;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
