package phase2.Board;

import java.util.ArrayList;
import java.util.List;

import phase2.Board.Util.InvalidInvariantException;
import phase2.PhysicsComponents.PhysicsComponent;
import phase2.PhysicsComponents.StaticCircle;
import phase2.PhysicsComponents.StaticLine;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class SquareBumper extends Gadget {

    
    List<PhysicsComponent> physicsComponentList = new ArrayList<>();

    /**
     * Create a square-shaped bumper with these parameters:
     * 
     * @param xCoord - the x coordinate of the bumper's origin, must be between 0 and 19
     * @param yCoord - the y coordinate of the bumper's origin, must be between 0 and 19
     * @throws InvalidInvariantException thrown when the inputs are invalid
     */
    public SquareBumper(int x, int y, String name) throws InvalidInvariantException{
    	super(new GridPoint(x, y), name, 1, 1, 1);
        physicsComponentList.add(new StaticLine(new LineSegment(this.getX(), this.getY(), this.getX() + 1,
                this.getY()), this.reflectionCoef));
        physicsComponentList.add(new StaticLine(new LineSegment(this.getX(), this.getY() + 1,
                this.getX() + 1, this.getY() + 1), this.reflectionCoef));
        physicsComponentList.add(new StaticLine(new LineSegment(this.getX() + 1, this.getY(),
                this.getX() + 1, this.getY() + 1), this.reflectionCoef));
        physicsComponentList.add(new StaticLine(new LineSegment(this.getX(), this.getY(), this.getX(),
                this.getY() + 1), this.reflectionCoef));

        physicsComponentList.add(new StaticCircle(new Circle(this.getX(), this.getY(), 0.01), this.reflectionCoef));
        physicsComponentList.add(new StaticCircle(new Circle(this.getX() + 1, this.getY(), 0.01), this.reflectionCoef));
        physicsComponentList.add(new StaticCircle(new Circle(this.getX(), this.getY() + 1, 0.01), this.reflectionCoef));
        physicsComponentList.add(new StaticCircle(new Circle(this.getX() + 1, this.getY() + 1, 0.01), this.reflectionCoef));

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
     * @return string representation of the square bumper
     */
    @Override
    public String toString() {
        return "#";
    }

    /**
     * @param ball - the ball that collides with this bumper
     * @return the time that the ball takes to collide with this bumper
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
     * Redirects (by changing the velocity) the ball which is going to collide with SquareBumper.
     * Also sends out triggers, following the collision.
     * @param ball the ball colliding with squareBumper
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
	public void action() {
		return;
	}

	@Override
	public void updateGadgetPosition(double timeDelta) {
		return;
	}

	@Override
	public char charRep() {
		return '#';
	}
}
