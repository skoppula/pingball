package phase2.messaging;

import org.json.simple.JSONObject;

/**
 * Informs the server that a board has exited
 * the simulation. 
 *
 */
public class TerminateMessage extends Message {
	
	private final String boardName;

	/**
	 * Create a message to inform the server that
	 * a board has exitted the simulation.
	 * @param boardName the name of the board to be removed from the server
	 */
	public TerminateMessage(String boardName){
		this.boardName = boardName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBoardName() {
		return boardName;
	}
	
	protected JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("messageType", "TERMINATE");
		
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
	protected static Message fromJSON(JSONObject messageContents) throws IllegalArgumentException{
		return new TerminateMessage((String)messageContents.get("boardName"));
	}

}
