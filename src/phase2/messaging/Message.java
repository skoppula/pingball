package phase2.messaging;

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
	 * Converts the message into a string, with the following property:
	 * if a.toString() == b.toString(), a == b. In this case, we mean
	 * observational equality. One use of this method is to transmit message
	 * data over Sockets.
	 * @return a message obeying the above criteria
	 */
	@Override
	public abstract String toString();
	
	/**
	 * 
	 * @return the type of message
	 */
	public MessageType getType(){
		return messageType;
	}
	
	
	/**
	 * Converts the string representation of a message back into a message.
	 * Specifically, a = fromString(a.toString())
	 * @param input the string from which to generate a message
	 * @return the message detailed by the input string
	 */
	public static Message fromString(String input) throws IllegalArgumentException{
		/*
		 * NOTE: If you create new message types, you must update a clause for them in this method!
		 */
		//TODO implement
		
	}

}
