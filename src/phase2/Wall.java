package phase2;

import java.util.ArrayList;
import java.util.List;

import phase2.physicsComponents.PhysicsComponent;
import phase2.physicsComponents.StaticLine;
import physics.LineSegment;
import physics.Vect;

public class Wall extends Gadget {
    
    private List<PhysicsComponent> physicsComponentList = new ArrayList<>();
    
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
     */
    private Wall(int x, int y, String name, int width, int height, Orientation orientation) {
    	super(new GridPoint(x,y), name, width, height, 1);
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
     * Create the walls necessary for a board, in the form of a list.
     * @return four walls, each of length 22, covering each of the sides.
     * They are named "leftWall", "topWall", "rightWall", and "bottomWall"
     */
    public List<Wall> makeWalls(){
    	List<Wall> wallList = new ArrayList<>();
    	wallList.add(new Wall(-1, -1, "leftWall", 1, 22, Orientation.ZERO)); // add left wall
    	wallList.add(new Wall(-1, -1, "topWall", 22, 1, Orientation.NINETY)); // add top wall
    	wallList.add(new Wall(20, -1, "rightWall", 1, 22, Orientation.ONE_HUNDRED_EIGHTY)); // add right wall
    	wallList.add(new Wall(-1, 20, "bottomWall", 22, 1, Orientation.TWO_HUNDRED_SEVENTY)); // add left wall
    	return wallList;
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


    /**
     * Does nothing as walls have no action
     */
    public void action() {
        return;
    }    
    
    public char charRep() {
        return '.';
    }
    
    public void updateGadgetPosition(double timeDelta) {
    	return;
    }
}
