package phase2.Messaging;

import org.json.simple.JSONObject;

/**
 * A class used to transmit information to a server
 * about connecting two walls. Immutable.
 *
 */
public class ServerWallConnectMessage extends Message {
	/*
	 * Abstraction function: a message sent to the server to connect boardName1 and boardName2
	 * in the direction of connectionType.
	 * Rep Invariant: see Message
	 */
	
	public enum ConnectionType{
		HORIZONTAL,
		VERTICAL
	}

	private final String boardName1;
	private final ConnectionType connectionType;
	private final String boardName2;
	
	/**
	 * Create a message denoting a connection between the board named 
	 * boardName1 and the board named boardName2, between the walls denoted by connectionType.
	 * Equivalent to the terminal message "connectionType boardName1 boardName2".
	 * @param boardName1 must be a board that the server is aware of, which does not already have a connection
	 * of this type
	 * @param boardName2 must be a board that the server is aware of, which does not already have a connection
	 * of this type
	 * @param connectionType
	 */
	public ServerWallConnectMessage(String boardName1, String boardName2, ConnectionType connectionType){
		this.boardName1 = boardName1;
		this.boardName2 = boardName2;
		System.out.println("Connecting " + boardName1 + " " + boardName2);
		this.connectionType = connectionType;
		this.messageType = MessageType.SERVERWALLCONNECT;
		assert(checkRep());
	}

	/**
	 * RI: Connection message has valid boardnames
	 * @return whether RI is satisfied
	 */
	private boolean checkRep(){
	    return this.boardName1 != null && this.boardName2 != null;
	}

	public String getBoardName1() {
		return boardName1;
	}



	public String getBoardName2() {
		return boardName2;
	}



	public ConnectionType getConnectionType() {
		return connectionType;
	}
	
	@SuppressWarnings("unchecked")
	protected JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("messageType", "SERVERWALLCONNECT");
		
		JSONObject contents = new JSONObject();
		contents.put("boardName1", boardName1);
		contents.put("boardName2", boardName2);
		String s;
		if(connectionType == ConnectionType.HORIZONTAL){
			s = "h";
		}
		else{
			s = "v";
		}
		contents.put("connectionType", s);
		obj.put("messageContents",contents);
		return obj;
	}
	
	/**
	 * Converts the JSONObject given into a message of this type.
	 * @param jsonObject the object containing the relevant information for this message
	 * @return a message of this type, with parameters as defined by the jsonObject
	 * @throws IllegalArgumentException if the jsonObject does not have the correct parameters,
	 * throw an exception
	 */
	protected static Message fromJSON(JSONObject messageContents) throws IllegalArgumentException{
		ConnectionType ct;
		if(messageContents.get("connectionType").equals("h")){
			ct = ConnectionType.HORIZONTAL;
		}
		else{
			ct = ConnectionType.VERTICAL;
		}
		return new ServerWallConnectMessage((String)messageContents.get("boardName1"), 
				(String)messageContents.get("boardName2"), ct);
	}

}
