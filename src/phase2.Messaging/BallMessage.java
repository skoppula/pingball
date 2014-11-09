package phase2.Messaging;

import phase2.Board.Ball;

/**
 * A class used to transmit
 * information about a ball, and the board wall that
 * is relevant to it. For example, if a board is sending a ball
 * which should be teleporting to a server, the boardwall is the wall
 * it originated from.
 *
 */
public class BallMessage extends Message {
	

	private final Ball ball;
	private final BoardWallPair boardWall;
	
	/**
	 * Creates a message intended to transmit a ball and
	 * a board's wall relevant to its action.
	 * @param ballName the name of the ball being sent
	 * @param boardWall the 
	 */
	public BallMessage(Ball ball, BoardWallPair boardWall){
		this.ball = ball;
		this.boardWall = boardWall;
		this.messageType = MessageType.BALL;
	}
	
	/**
	 * @return the ball passed in this message
	 */
	public Ball getBall(){
		return ball;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
