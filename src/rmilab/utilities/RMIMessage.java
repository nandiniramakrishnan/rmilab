package rmilab.utilities;

import java.io.Serializable;

/**
 * 
 * @author Nandini Ramakrishnan and Shri Karthikeyan
 * 
 *         The RMIMessage class is a wrapper for all messages that need to be
 *         send via sockets. It provides different constructors to create
 *         customizable messages for a particular scenario.For all RMIMessage
 *         classes there is a MessageType associated with it that allows one to
 *         see the type of message so that one can see which constructor was
 *         used/what values should be parsed.
 */
public class RMIMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Defines the different types of messages we can have */
	public enum MessageType {
		FOUND, EXCEPTION, RETURN, LOOKUP, REBIND, UNBIND, METHOD;

	}

	private MessageType type;
	private RemoteObjRef obj;
	private Exception exception;
	private Object returnValue;
	private String serviceName;
	private String methodName;
	private String interfaceName;
	private Object[] params;

	/* Method invocation request */

	/* Response messages */

	public RMIMessage() {

	}

	/* Found RemoteObject Message, and stub to skeleton method invocation */
	public RMIMessage(MessageType type, RemoteObjRef obj) {
		this.type = type;
		this.obj = obj;
	}

	/* Exception Message */
	public RMIMessage(MessageType type, Exception e) {
		this.type = type;
		exception = e;
	}

	/* Return Value Message */
	public RMIMessage(MessageType type, Object returnValue) {
		this.type = type;
		this.returnValue = returnValue;
	}

	/** Request messages **/

	/* Method Invocation message */
	public RMIMessage(MessageType type, String serviceName,
			String interfaceName, String methodName, Object[] params) {
		this.type = type;
		this.serviceName = serviceName;
		this.methodName = methodName;
		this.params = params;
	}

	/* Lookup */
	public RMIMessage(MessageType type, String serviceName, String methodName,
			Object[] params) {
		this.type = type;
		this.serviceName = serviceName;
		this.methodName = methodName;
		this.params = params;
	}

	/* Rebind */
	public RMIMessage(MessageType type, String serviceName, RemoteObjRef obj) {
		this.type = type;
		this.serviceName = serviceName;
		this.obj = obj;
	}

	/* Unbind */
	public RMIMessage(MessageType type, String serviceName) {
		this.type = type;
		this.serviceName = serviceName;
	}

	/** Getters */

	public MessageType getType() {
		return type;
	}

	public RemoteObjRef getRemoteObject() {
		return obj;
	}

	public Exception getException() {
		return exception;
	}

	public Object getReturnValue() {
		return returnValue;
	}

	public String getServiceName() {
		return serviceName;
	}

	public Object[] getParams() {
		return params;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

}
