package rmilab.utilities;

import java.io.Serializable;

public class RMIMessage implements Serializable{
   
	public enum MessageType{
		FOUND, EXCEPTION, RETURN, LOOKUP, REBIND, UNBIND;
		
	}
	private String key;
	private String type;
	private RemoteObjRef obj;
	private Exception exception;
	private Object returnValue;
	private String serviceName;
	private Object[] params;
	private String interfaceName;
	private String methodName;
	
	/* Method invocation request */	
	
    /* Response messages */
	public RMIMessage() {
		
	}
	
	/* Found RemoteObject Message, and stub to skeleton method invocation  */
	public RMIMessage(String type, RemoteObjRef obj)
	{
		this.type = type;
		this.obj = obj;
	}
	
	/* Exception Message*/
	public RMIMessage(String type, Exception e)
	{
		this.type = type;
		exception = e;
	}
	
	/* Return Value Message */
	public RMIMessage(String type, Object returnValue)
	{
	  this.type = type;
	  this.returnValue = returnValue;
	}
	
	/** Request messages **/
	
	/*Lookup Service message*/
	public RMIMessage(String type,String serviceName, Object [] params)
	{ 
		this.type = type;
		this.setServiceName(serviceName);
		this.setParams(params);
	}
	
	/* Rebind */
	public RMIMessage(String type, String serviceName, RemoteObjRef obj)
	{
		this.type = type;
		this.setServiceName(serviceName);
		this.obj = obj;
	}
	
	/* Unbind */
	public RMIMessage(String type, String serviceName)
	{
		this.type = type;
		this.setServiceName(serviceName);
	}
	
	/** Getters */
	
	public String getKey() {
		return key;
	}
	
	public String getType()
	{
		return type;
	}
	
	public RemoteObjRef getRemoteObject()
	{
		return obj;
	}
	
	public Exception getException()
	{
		return exception;
	}
	
	public Object getReturnValue()
	{
		return returnValue;
	}
	
	public String getServiceName()
	{
		return serviceName;
	}
	
	public Object[] getParams()
	{
		return params;
	}
	
	public String getInterfaceName() {
		return interfaceName;
	}
	
	public String getMethodName() 
	{
		return methodName;
	}
/*
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}*/

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
	
	
	
}
