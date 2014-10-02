package rmilab;

public class RMIMessage {
   
	public enum MessageType{
		FOUND, EXCEPTION, RETURN, LOOKUP, REBIND, UNBIND;
		
	}
	private MessageType type;
	private RemoteObjRef obj;
	private Exception exception;
	private Object returnValue;
	private String serviceName;
	private Object[] params;
	
    /* Response messages */
	
	/* Found RemoteObject Message  */
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
	public RMIMessage(MessageType type,String serviceName, Object [] params)
	{ 
		this.type = type;
		this.serviceName = serviceName;
		this.params = params;
	}
	
	/* Rebind */
	public RMIMessage(MessageType type, String serviceName, RemoteObjRef obj)
	{
		this.type = type;
		this.serviceName = serviceName;
		this.obj = obj;
	}
	
	/* Unbind */
	public RMIMessage(MessageType type, String serviceName)
	{
		this.type = type;
		this.serviceName = serviceName;
	}
	
	/** Getters */
	
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
	
	
	
}
