package phase2.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import phase2.Messaging.BallMessage;
import phase2.Messaging.BoardWallPair;
import phase2.PhysicsComponents.PhysicsComponent;
import phase2.PhysicsComponents.StaticLine;
import physics.LineSegment;
import physics.Vect;

/**
 * A mutable gadget that represents a wall.
 *
 */
public class Wall extends Gadget {
	/* Rep Invariant: isTeleporter can be true only if board.isOnline == true
	* the wall must be within the confines of the board, i.e. -1<= x, y <=20
	* text must be of length less than 20
	* 
	*/
    
	private final Board board;
	protected boolean isTeleporter = false;
	protected final Orientation orientation;
	private String text;
    /**
     * Creates a new Wall.
     * @param x the x-location of this wall's origin, must be -1<= x <=20
     * @param y the y-location of this wall's origin, must be -1<= y <=20
     * @param name the name of the wall
     * @param width the width of the wall. Must correspond to the wall's orientation, according to the following table:
     * if orientation = ZERO: width = 1, height = 22
     * if orientation = NINETY: width = 22, height = 1
     * if orientation = ONE_HUNDRED_EIGHTY: width = 1, height = 22
     * if orientation = TWO_HUNDRED_SEVENTY: width = 22, height = 1
     * @param height the height of the wall. Must correspond to the wall's orientation, as detailed for width
     * @param orientation the orientation of the wall. 0 degrees is the left wall, 90 is the top wall, 180 is the left wall, 270 is the bottom wall
     * @param board the board that this wall is placed in
     */
    private Wall(int x, int y, String name, int width, int height, Orientation orientation, Board board) {
    	super(new GridPoint(x,y), name, width, height, 1);
    	this.board = board;
    	this.text = "";
    	this.orientation = orientation;
        switch (orientation) {
            case NINETY:    
                physicsComponentList.add(new StaticLine(new LineSegment(0, 0, 20, 0), this.reflectionCoef));
                break;
            case ONE_HUNDRED_EIGHTY:
            	physicsComponentList.add(new StaticLine(new LineSegment(20, 0, 20, 20), this.reflectionCoef));
                break;
            case TWO_HUNDRED_SEVENTY:
            	physicsComponentList.add(new StaticLine(new LineSegment(0, 20, 20, 20), this.reflectionCoef));
                break;
            case ZERO:
            	physicsComponentList.add(new StaticLine(new LineSegment(0, 0, 0, 20), this.reflectionCoef));
            	break;
            default:
            	throw new IllegalStateException("Should never be able to get here! Must be a valid Orientation.");
        }
    }
    
    
    /**
     * Create the walls necessary for a board, in the form of a map from orientations to walls.
     * @param the board that these walls are being created for
     * @return a dictionary mapping the four orientations to four walls, each of length 22, covering each of the sides.
     * They are named "leftWall", "topWall", "rightWall", and "bottomWall"
     */
    public static Map<Orientation, Wall> makeWalls(Board board){
    	Map<Orientation, Wall> wallMap = new HashMap<>();
    	wallMap.put(Orientation.ZERO, new Wall(-1, -1, "leftWall", 1, 22, Orientation.ZERO, board)); // add left wall
    	wallMap.put(Orientation.NINETY, new Wall(-1, -1, "topWall", 22, 1, Orientation.NINETY, board)); // add top wall
    	wallMap.put(Orientation.ONE_HUNDRED_EIGHTY, new Wall(20, -1, "rightWall", 1, 22, Orientation.ONE_HUNDRED_EIGHTY, board)); // add right wall
    	wallMap.put(Orientation.TWO_HUNDRED_SEVENTY, new Wall(-1, 20, "bottomWall", 22, 1, Orientation.TWO_HUNDRED_SEVENTY, board)); // add left wall
    	return wallMap;
    }
    
    /**
     * Returns time until ball collides with Wall, infinity if never 
     */
    @Override
    public double getTimeUntilCollision(Ball ball) {
        double minCollisionTime = Double.POSITIVE_INFINITY;

        for (PhysicsComponent physicsComponent: physicsComponentList) {
            double currCollisionTime = physicsComponent.timeUntilCollision(ball.getBallCircle(), ball.getVelocity());
            minCollisionTime = Math.min(minCollisionTime, currCollisionTime);
        }
        return minCollisionTime;
    }

    /**
     * Mutates the ball object as necessary to represent the effect of the collision with the Wall
     * Only mutates the ball's position if the interaction with the Wall is not a standard reflection
     * @param ball that the collision will happen with
     */
    @Override
    public void collision(Ball ball) {
    	if(!isTeleporter){
	    	PhysicsComponent gadgetPartToCollideWith = this.physicsComponentList.get(0);
	        double minTimeUntilCollision = Double.MAX_VALUE;
	        for(PhysicsComponent gadgetPart: physicsComponentList){
	            double timeUntilCollisionPart = gadgetPart.timeUntilCollision(ball.getBallCircle(), ball.getVelocity());
	            if (timeUntilCollisionPart < minTimeUntilCollision){ 
	                minTimeUntilCollision = timeUntilCollisionPart;
	                gadgetPartToCollideWith = gadgetPart;
	            }
	        }
	        Vect newVelocity = gadgetPartToCollideWith.reflect(ball.getBallCircle(), ball.getVelocity(), ball.getCoefficentOfReflection()); 
	        ball.setVelocity(newVelocity);
	        trigger();
    	}
    	else{
    		board.flagForRemoval(ball);
    		try {
				board.outQ.put(new BallMessage(ball, new BoardWallPair(this.name, this.orientation)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    	}
    }

    /**
     * Does nothing as walls have no action
     */
    public void action() {
        return;
    }    
    
    public char charRep() {
        return '.';
    }
    
    /**
     * @return a list of gridsymbols detailing the visualization of the wall.
     * Note that this contains the wall's text
     */
    @Override
    public List<GridSymbol> getSymbolRep(){
    	List<GridSymbol> symbolList = new ArrayList<>();
    	
    	char[] textChars = this.text.toCharArray();
    	int counter = 0;
    	switch(this.orientation){
		case NINETY:
			symbolList.add(new GridSymbol(-1, -1, '.'));
			while(counter<textChars.length){
				symbolList.add(new GridSymbol(counter, -1, textChars[counter]));
				counter++;
			}
			while(counter<width - 1){ // we want the maximum to be 20
				symbolList.add(new GridSymbol(counter, -1, '.'));
				counter++;
			}
			break;
		case ONE_HUNDRED_EIGHTY:
			symbolList.add(new GridSymbol(20, -1, '.'));
			while(counter<textChars.length){
				symbolList.add(new GridSymbol(20, counter, textChars[counter]));
				counter++;
			}
			while(counter<height - 1){
				symbolList.add(new GridSymbol(20, counter, '.'));
				counter++;
			}
			break;
		case TWO_HUNDRED_SEVENTY:
			symbolList.add(new GridSymbol(-1, 20, '.'));
			while(counter<textChars.length){
				symbolList.add(new GridSymbol(counter, 20, textChars[counter]));
				counter++;
			}
			while(counter<width - 1){
				symbolList.add(new GridSymbol(counter, 20, '.'));
				counter++;
			}
			break;
		case ZERO:
			symbolList.add(new GridSymbol(-1, -1, '.'));
			while(counter<textChars.length){
				symbolList.add(new GridSymbol(-1, counter, textChars[counter]));
				counter++;
			}
			while(counter<height - 1){
				symbolList.add(new GridSymbol(-1, counter, '.'));
				counter++;
			}
			break;
		default:
			throw new IllegalStateException("Should never be able to get here.");
    	}
    	return symbolList;
    }
    
    /**
     * Sets the wall to be permeable (teleports balls that collide with it)
     * also sets the wall to display a given text
     * @param text must be less than or equal to 20 characters in length, all ASCII (non-newline)
     */
    public void connect(String text) {
    	this.text = text;
    	this.isTeleporter = true;
    }
    
    /**
     * Makes the wall impermeable (no longer teleports balls)
     * and removes all text from the wall.
     */
    public void disconnect(){
    	this.text = "";
    	this.isTeleporter = false;
    }
    
    
    
    /**
     * Wall's position is not updated.
     */
    public void updateGadgetPosition(double timeDelta) {
    	return;
    }
}
