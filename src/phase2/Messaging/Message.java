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

    public static Message JSONtoMessage(String line) {
        // TODO write this
        return null;
    }

    public String convertMessageToJSON() {
        // TODO write this
        return null;
    }
	
}
