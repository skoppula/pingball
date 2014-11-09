package phase2.Messaging;

/**
 * An abstract class for messages to be exchanged between different parts
 * of the program.
 *
 */
public abstract class Message{
	
	MessageType messageType;
	
	public enum MessageType{
		BALL,
		SERVERWALLCONNECT,
		CLIENTWALLCHANGE,
		BOARDINIT,
		TERMINATE
	}

	/**
	 * 
	 * @return the type of message
	 */
	public MessageType getType(){
		return messageType;
	}
	
}
