package phase2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import phase2.Util.InvalidInvariantException;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Geometry.DoublePair;
import phase2.physicsComponents.*;

public class TriangleBumper extends Gadget {

    private List<PhysicsComponent> physicsComponentList = new ArrayList<>();
    private Orientation orientation;

    /**
     * Create a triangle-shaped bumper with these parameters:
     * @param xCoord = the x coordinate of the bumper's origin
     * @param yCoord = the y coordinate of the bumper's origin
     * @param orientation = the number of degrees the triangle is rotated clockwise,
     * with respect to its "zero-degree orientation" with the point in the top-left corner and the hypoteneuse going
     * northeast to southwest.
     * @throws InvalidInvariantException 
     */
    public TriangleBumper(int x, int y, String name, Orientation orientation) throws InvalidInvariantException {
        super(new GridPoint(x, y), name, 1, 1, 1);
        this.orientation = orientation;
        
        double dx = (double) x;
        double dy = (double) y;
        if(orientation.equals(Orientation.ZERO)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy + 1, 0.01), coefficientOfReflection));
        }
        else if(orientation.equals(Orientation.NINETY)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy + 1, 0.01), coefficientOfReflection));
        }
        else if(orientation.equals(Orientation.ONE_HUNDRED_EIGHTY)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy + 1, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy + 1, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy + 1, 0.01), coefficientOfReflection));
        }
        else if(orientation.equals(Orientation.TWO_HUNDRED_SEVENTY)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy + 1, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy + 1, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy + 1, 0.01), coefficientOfReflection));
        }
        else{
            throw new IllegalStateException("TriangleBumper's orientation must be 0, 90, 180, or 270. Was "
                    + orientation.toString());
        }
        
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
     * @return string representation of the triangular bumper
     */
    @Override
    public String toString() {
        return Character.toString(charRep());
    }

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
	public void action() {
		return;
	}

	@Override
	public void updateGadgetPosition(double timeDelta) {
		return;
	}

	@Override
	public char charRep() {
		if(orientation.equals(Orientation.ZERO) || orientation.equals(Orientation.ONE_HUNDRED_EIGHTY)){
            return '/';
        }
        else if(orientation.equals(Orientation.NINETY) || orientation.equals(Orientation.TWO_HUNDRED_SEVENTY)){
            return '\\';
        }
        else{
            throw new IllegalStateException("TriangleBumper's orientation must be 0, 90, 180, or 270. Was:"
                    + orientation.toString());
        }
	}
    
}
