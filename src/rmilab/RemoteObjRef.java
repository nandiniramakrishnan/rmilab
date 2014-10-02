package rmilab;

public class RemoteObjRef {
	
	private String ipAddress;
	private int portNum;
	private int objKey;
	private String interfaceName;
	public RemoteObjRef(String ipAddress, int portNum, int objKey, String interfaceName)
	{
		this.ipAddress = ipAddress;
		this.portNum = portNum;
		this.objKey = objKey;
		this.interfaceName = interfaceName;
	}
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	
	public int getPortNum()
	{
		return portNum;
	}
	
	public int getObjKey()
	{
		return objKey;
	}
	
	public String getInterfaceName()
	{
		return interfaceName;
	}
	
	Object localise() throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		/* Returns the class object associated with the class or interface */
		Class c = Class.forName(interfaceName + "_stub");
		Object stub = c.newInstance();
		return stub;
	}

}
