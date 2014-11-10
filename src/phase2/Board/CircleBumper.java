package phase2.Board;

import java.util.ArrayList;
import java.util.List;

import phase2.Board.Util.InvalidInvariantException;
import phase2.PhysicsComponents.PhysicsComponent;
import phase2.PhysicsComponents.StaticCircle;
import physics.Circle;
import physics.Vect;

public class CircleBumper extends Gadget {
	

    /**
     * Create a square-shaped bumper with these parameters:
     * @param xCoord = the x coordinate of the bumper's origin
     * @param yCoord = the y coordinate of the bumper's origin
     * @throws InvalidInvariantException 
     */
    public CircleBumper(int x, int y, String name) throws InvalidInvariantException {
    	super(new GridPoint(x, y), name, 1, 1, 1);
    	final double CIRCLE_RADIUS = .5;
    	physicsComponentList.add(new StaticCircle(new Circle(new Vect(this.getX() + CIRCLE_RADIUS, this.getY() + CIRCLE_RADIUS), CIRCLE_RADIUS),
    			this.reflectionCoef));
        if (!this.checkRep()) {
            throw new Util.InvalidInvariantException();
        }
    }

    /**
     * Function that checks the representation invariant of bumper
     * The bumper's origin must be within the board
     * @return a boolean that specifies whether or not the rep invariant is preserved
     */
    protected boolean checkRep() {
        if ( this.getX() < 0 || this.getY() < 0 || this.getX() >= 20 || this.getY() >= 20 ) {
            return false;
        }
        return true;
    }

    /**
     * @return string representation of the circle bumper
     */
    @Override
    public String toString() {
        return "0";
    }

    /**
     * @param ball is the ball that collides with this bumper
     * @return the time that it takes until the ball will collide with this bumper
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
     * This function triggers the trigger action specific to this bumper
     * @throws UnsupportedOperationException 
     */
    @Override
    public void action(){
        return;
    }

    /**
     * Redirects (by changing the velocity) the ball which is going to collide with CircleBumper.
     * Also sends out triggers, following the collision.
     * @param ball the ball colliding with circleBumper
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

	@Override
	public void updateGadgetPosition(double timeDelta) {
		return;
	}

	@Override
	public char charRep() {
		return '0';
	}
}
