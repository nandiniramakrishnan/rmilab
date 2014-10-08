package rmilab.utilities;

import java.io.Serializable;
import java.net.InetAddress;

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
		this.serviceName = identifier;
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
	
	public Object localise() throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		System.out.println("interfaceName="+interfaceName);
		/* Returns the class object associated with the class or interface */
		Class c = Class.forName("rmilab.client."+interfaceName + "_stub");
		Object stub = c.newInstance();
		if (stub != null) {
			System.out.println("stub is not null! stub="+stub);
		}
		return stub;
	}

}
