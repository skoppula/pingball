package phase2.Messaging;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * An abstract class for immutable messages to be exchanged between different parts
 * of the program.
 *
 */
public abstract class Message{
	/* Abstraction Function: Message = MessageType + Objects
	* Rep invariant: message = MessageType.fromJSONObject(message.toJSONObject()) 
	* (these checkReps are stored in subclasses)
	* if a.toString().equals(b.toString()), a must be observationally equal to b
	* (meaning toString() is 1-to-1 with a given object observationally)
	*/
	
	MessageType messageType;
	
	public enum MessageType{
		BALL,
		SERVERWALLCONNECT,
		CLIENTWALLCHANGE,
		BOARDINIT,
		TERMINATE;
	}

	/**
	 * Converts the message into a string, with the following property:
	 * if a.toString().equals(b.toString()), a == b (assuming that == uses observational equality). 
	 * @return a message obeying the above criteria
	 */
	@Override
	public String toString(){
		return this.toJSONObject().toString();
	}
	
	/**
	 * Creates a JSONObject representation of the message with the following property:
	 * if a.toJSONObject() == b.toJSONObject(), a == b (assuming observational equality). In this case, we mean
	 * observational equality.
	 */
	abstract JSONObject toJSONObject();
	/*
	 * Note that occasionally, we do not use the full definition of observational
	 * equality. For example, here, technically Ball has elements that compose its state other than the elements
	 * necessary to initialize it. However, BallMessage only transmits the elements necessary to initialize it.
	 */
	
	/**
	 * 
	 * @return the type of message
	 */
	public MessageType getType(){
		return messageType;
	}
	
	
	/**
	 * Implements observational equality.
	 */
	@Override
	public boolean equals(Object other){
		if(!this.getClass().equals(other.getClass())){
			return false;
		}
		return(this.toString().equals(other.toString()));
	}
	
	@Override
	public int hashCode(){
		return this.toString().hashCode();
	}
	

	
	/**
	 * Converts the string representation of a message back into a message.
	 * Specifically, a = fromString(a.toString())
	 * @param input the string from which to generate a message
	 * @return the message detailed by the input string
	 * @throws IllegalArgumentException if the inputted string is not a valid JSON data structure,
	 * or is not a valid message type, 
	 */
	public static Message decode(String input) throws IllegalArgumentException{
		/*
		 * NOTE: If you create new message types, you must update a clause for them in this method!
		 * Additionally, they must have a static method "fromString". I can't require a static method
		 * for each subclass as part of the interface, but you should put it in there!
		 * 
		 * Explanation of JSON syntax:
		 * The JSON messages that are created and parsed by each message class are of the following form:
		 * Each message contains two attributes: 
		 * 1) messageType, which is the type of message being sent
		 * 2) messageConents, the contents of the message which depends on what type of message it is.
		 * 
		 * Each message handles its own contents internally. It creates its own JSON representation
		 * with toString, which outputs a JSON object in string form.
		 * It also converts from a JSONObject to a message of that type, using fromJSON(JSONObject).
		 * 
		 */
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject)parser.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid JSON string.");
		}
		
		switch((String)obj.get("messageType")){
		case "BALL":
			return BallMessage.fromJSON((JSONObject)obj.get("messageContents"));
		case "SERVERWALLCONNECT":
			return ServerWallConnectMessage.fromJSON((JSONObject)obj.get("messageContents"));
		case "CLIENTWALLCHANGE":
			return ClientWallChangeMessage.fromJSON((JSONObject)obj.get("messageContents"));
		case "BOARDINIT":
			return BoardInitMessage.fromJSON((JSONObject)obj.get("messageContents"));
		case "TERMINATE":
			return 	TerminateMessage.fromJSON((JSONObject)obj.get("messageContents"));
		default:
			throw new IllegalArgumentException("inputted JSON object must have an attribute messageType"
					+ "which must correspond to a valid message type!");
		}
		
		
	}

}











