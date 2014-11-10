package phase2.messaging;

import org.json.simple.JSONObject;

/**
 * A message to inform the server that a new board
 * has joined the simulation. This should be the
 * first message sent by any board.
 *
 */
public class BoardInitMessage extends Message {
	
	private final String boardName;

	/**
	 * Create a message to inform the server that a new board has joined.
	 * @param boardName the name of the board being initialized
	 */
	public BoardInitMessage(String boardName){
		this.boardName = boardName;
		this.messageType = MessageType.BOARDINIT;
	}


	public String getBoardName() {
		return boardName;
	}

	
	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("messageType", "BOARDINIT");
		
		JSONObject contents = new JSONObject();
		contents.put("boardName", boardName);
		obj.put("messageContents", contents);
		return obj;
	}
	
	/**
	 * Converts the JSONObject given into a message of this type.
	 * @param jsonObject the object containing the relevant information for this message
	 * @return a message of this type, with parameters as defined by the jsonObject
	 * @throws IllegalArgumentException if the jsonObject does not have the correct parameters,
	 * throw an exception
	 */
	static Message fromJSON(JSONObject messageContents) throws IllegalArgumentException{
		return new BoardInitMessage((String)messageContents.get("boardName"));
	}
}
