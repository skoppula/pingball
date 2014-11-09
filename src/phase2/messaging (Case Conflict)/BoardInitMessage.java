package phase2.Messaging;

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBoardName() {
		return boardName;
	}

}
