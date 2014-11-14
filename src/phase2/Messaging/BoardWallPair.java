package phase2.Messaging;

import org.antlr.v4.parse.ANTLRParser.ruleReturns_return;
import org.json.simple.JSONObject;

import phase2.Board.Gadget.Orientation;

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
	
	/**
	 * Creates a BoardWallPair from a JSON representation of a BoardWallPair
	 * @param obj a JSON rep of BoardWallPair, see BoardWallPair implementation for details.
	 */
	protected BoardWallPair(JSONObject obj){
	    System.out.println("json object: " + obj);
		this.boardName = (String)obj.get("boardName");
		Orientation orientation;
		switch((String)obj.get("orientation")){
		case "0":
			orientation = Orientation.ZERO;
			break;
		case "90":
			orientation = Orientation.NINETY;
			break;
		case "180":
			orientation = Orientation.ONE_HUNDRED_EIGHTY;
			break;
		case "270":
			orientation = Orientation.TWO_HUNDRED_SEVENTY;
			break;
		default:
			throw new IllegalStateException("Should never be able to get here!");
		}
		this.wallOrientation = orientation;
	}
	
	public String board(){
		return boardName;
	}
	
	/**
	 * Converts this into a representation of itself as a JSON object
	 * @return a JSON object describing this board wall pair
	 */
	@SuppressWarnings("unchecked")
	protected JSONObject toJSONObject(){
		JSONObject obj = new JSONObject();
		obj.put("boardName", boardName);
		String s;
		switch(wallOrientation){
		case ZERO:
			s = "0";
			break;
		case NINETY:
			s = "90";
			break;
		case ONE_HUNDRED_EIGHTY:
			s = "180";
			break;
		case TWO_HUNDRED_SEVENTY:
			s = "270";
			break;
		default:
			throw new IllegalStateException("Should never be able to get here!");
		}
		obj.put("orientation", s);
		return obj;
	}
	
	public Orientation wallOrientation(){
		return wallOrientation;
	}
	
	/**
	 * Implements observational equality.
	 */
	@Override
	public boolean equals(Object other){
		if(!this.getClass().equals(other.getClass())){
			return false;
		}
		BoardWallPair otherM = (BoardWallPair)other;
		return(this.boardName.equals(otherM.boardName) && this.wallOrientation == otherM.wallOrientation());
	}
	
	@Override
	public int hashCode(){
		return this.board().hashCode();
	}
	
    @Override
    public String toString() {
        return boardName + wallOrientation;
    }

}
