package phase2.messaging;

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

}
