package phase2.Messaging;

import org.json.simple.JSONObject;

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
	 * @param boardWall the boardWall that this ball came from
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
	
	/**
	 * 
	 * @return the boardWallPair that the ball came from
	 */
	public BoardWallPair getBoardWall(){
		return boardWall;
	}
	
	
	@SuppressWarnings("unchecked")
	protected JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("messageType", "BALL");
		
		JSONObject contents = new JSONObject();
		contents.put("ball", ball.toJSONObject());
		contents.put("boardWall", boardWall.toJSONObject());
		obj.put("messageContents",contents);
		return obj;
	}
	
	/**
	 * RI: checks 
	 * @return whether RI is met
	 */
	private boolean checkRep(){
		return this.equals(BallMessage.fromJSON(this.toJSONObject()));
	}
	
	/**
	 * Converts the JSONObject given into a message of this type.
	 * @param jsonObject the object containing the relevant information for this message
	 * @return a message of this type, with parameters as defined by the jsonObject
	 * @throws IllegalArgumentException if the jsonObject does not have the correct parameters,
	 * throw an exception
	 */
	protected static Message fromJSON(JSONObject messageContents) throws IllegalArgumentException{
	    System.out.println(messageContents);
		Ball ball = new Ball((JSONObject)messageContents.get("ball"));
		BoardWallPair boardWall = new BoardWallPair((JSONObject)messageContents.get("boardWall"));
		return new BallMessage(ball, boardWall);
	}

}
