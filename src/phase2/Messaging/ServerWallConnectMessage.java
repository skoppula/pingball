package phase2.Messaging;

/**
 * A class used to transmit information to a server
 * about connecting two walls
 *
 */
public class ServerWallConnectMessage extends Message {
	
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
		this.connectionType = connectionType;
		this.messageType = MessageType.SERVERWALLCONNECT;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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

}
