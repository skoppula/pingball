package phase2.messaging;

import phase2.Gadget.Orientation;

/**
 * A message responsible
 * for informing a board about which of its walls
 * is now involved or not involved in an inter-board connection.
 *
 */
public class ClientWallChangeMessage extends Message {
	
	private final BoardWallPair otherBoardWall;
	private final boolean connectOrDisconnect;

	/**
	 * Create a message informing a board of which wall it needs to connect
	 * to another board.
	 * @param otherBoardWall the name of the other board, and the wall orientation from
	 * the other board. We can determine which wall to dis/connect on our board based off of
	 * the orientation of the dis/connected wall on the other board.
	 * @param connectOrDisconnect whether our board should be connecting or disconnecting our wall
	 * to the other board
	 */
	public ClientWallChangeMessage(BoardWallPair otherBoardWall, boolean connectOrDisconnect){
		this.otherBoardWall = otherBoardWall;
		this.connectOrDisconnect = connectOrDisconnect;
		this.messageType = MessageType.CLIENTWALLCHANGE;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	 * 
	 * @return whether our board should be connecting or disconnecting our wall
	 * to the other board
	 */
	public boolean isConnectOrDisconnect() {
		return connectOrDisconnect;
	}

}
