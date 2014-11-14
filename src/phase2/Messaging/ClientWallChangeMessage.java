package phase2.Messaging;

import org.json.simple.JSONObject;

/**
 * A message responsible
 * for informing a board about which of its walls
 * is now involved or not involved in an inter-board connection.
 *
 */
public class ClientWallChangeMessage extends Message {
	/*
	 * abstractionFunction: represents a message to (connectOrDisconnect? connect : disconnect)
	 * a wall from a board, which is the opposite wall from otherBoardWall.
	 * Rep Invariant: see Message
	 * 
	 */
	
	private final BoardWallPair otherBoardWall;
	private final boolean connectOrDisconnect;

	/**
	 * Create a message informing a board of which wall it needs to connect
	 * to another board.
	 * @param otherBoardWall the name of the other board, and the wall orientation from
	 * the other board. We can determine which wall to dis/connect on our board based off of the orientation of the dis/connected wall on the other board. Connecting --> true, disconnecting --> false
	 * @param connectOrDisconnect whether our board should be connecting or disconnecting our wall
	 * to the other board
	 */
	public ClientWallChangeMessage(BoardWallPair otherBoardWall, boolean connectOrDisconnect){
		this.otherBoardWall = otherBoardWall;
		this.connectOrDisconnect = connectOrDisconnect;
		this.messageType = MessageType.CLIENTWALLCHANGE;
	}

	private boolean checkRep(){
		return this.equals(ClientWallChangeMessage.fromJSON(this.toJSONObject()));
	}
	
	/**
	 * @return the name of the other board, and the wall orientation from
	 * the other board. We can determine which wall to dis/connect on our board based off of
	 * the orientation of the dis/connected wall on the other board.
	 */
	public BoardWallPair getOtherBoardWall() {
		return otherBoardWall;
	}

	/**
	 * @return whether our board should be connecting or disconnecting our wall
	 * to the other board. Connecting --> true, disconnecting --> false
	 */
	public boolean isConnectOrDisconnect() {
		return connectOrDisconnect;
	}

	@SuppressWarnings("unchecked")
	protected JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("messageType", "CLIENTWALLCHANGE");
		
		JSONObject contents = new JSONObject();
		contents.put("otherBoardWall", otherBoardWall.toJSONObject());
		contents.put("connectOrDisconnect", connectOrDisconnect);
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
		BoardWallPair otherBoardWall = new BoardWallPair((JSONObject)messageContents.get("otherBoardWall"));
		return new ClientWallChangeMessage(otherBoardWall, (boolean)messageContents.get("connectOrDisconnect"));
	}
}
