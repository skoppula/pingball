package phase2.messaging;

import phase2.Gadget.Orientation;

/**
 * A class to store information
 * about a wall and its associated board.
 *
 */
public class BoardWallPair {
	
	private final String boardName;
	private final Orientation wallOrientation;
	
	/**
	 * Creates an object that denotes a wall and its respective board.
	 * @param boardName
	 * @param wallOrientation
	 */
	public BoardWallPair(String boardName, Orientation wallOrientation){
		this.boardName = boardName;
		this.wallOrientation = wallOrientation;
	}
	
	public String board(){
		return boardName;
	}
	
	public Orientation wallOrientation(){
		return wallOrientation;
	}

}
