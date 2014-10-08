package rmilab.utilities;

import java.io.Serializable;

public class RMIMessage implements Serializable{
   
	public enum MessageType{
		FOUND, EXCEPTION, RETURN, LOOKUP, REBIND, UNBIND;
		
	}
	private String key;

	public MessageType type;
	private RemoteObjRef obj;
	private Exception exception;
	private Object returnValue;
	public String serviceName;
	public String methodName;
	private Object[] params;
	private String interfaceName;
	
	/* Method invocation request */	
	
    /* Response messages */
	public RMIMessage() {
		
	}
	
	/* Found RemoteObject Message, and stub to skeleton method invocation  */
	public RMIMessage(MessageType type, RemoteObjRef obj)
	{
		this.type = type;
		this.obj = obj;
	}
	
	/* Exception Message*/
	public RMIMessage(MessageType type, Exception e)
	{
		this.type = type;
		exception = e;
	}
	
	/* Return Value Message */
	public RMIMessage(MessageType type, Object returnValue)
	{
	  this.type = type;
	  this.returnValue = returnValue;
	}
	
	/** Request messages **/
	
	/*Lookup Service message*/
	public RMIMessage(MessageType type,String serviceName, String methodName, Object [] params)
	{ 
		this.type = type;
		this.serviceName = serviceName;
		this.methodName = methodName;
		this.params = params;
	}
	
	/* Rebind */
	public RMIMessage(MessageType type, String serviceName, RemoteObjRef obj)
	{
		this.type = type;
		this.setServiceName(serviceName);
		this.obj = obj;
	}
	
	/* Unbind */
	public RMIMessage(MessageType type, String serviceName)
	{
		this.type = type;
		this.setServiceName(serviceName);
	}
	
	/** Getters */
	
	public String getKey() {
		return key;
	}
	
	public MessageType getType()
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
	

	public String getMethodName()
	{
		return methodName;
	}
	public String getInterfaceName() {
		return interfaceName;
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
