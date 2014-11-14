package phase2.Board;


import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import phase2.Board.Util.InvalidInvariantException;
import phase2.PhysicsComponents.*;

public class TriangleBumper extends Gadget {

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
        
        if(orientation.equals(Orientation.ZERO)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x + 1, y), reflectionCoef));
            this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticLine(new LineSegment(x + 1, y, x, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x, y, 0.01), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x + 1, y, 0.01), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x, y + 1, 0.01), reflectionCoef));
        }
        else if(orientation.equals(Orientation.NINETY)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x + 1, y), reflectionCoef));
            this.physicsComponentList.add(new StaticLine(new LineSegment(x + 1, y, x + 1, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x + 1, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x, y, 0.01), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x + 1, y, 0.01), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x + 1, y + 1, 0.01), reflectionCoef));
        }
        else if(orientation.equals(Orientation.ONE_HUNDRED_EIGHTY)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(x, y + 1, x + 1, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticLine(new LineSegment(x + 1, y, x + 1, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticLine(new LineSegment(x + 1, y, x, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x, y + 1, 0.01), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x + 1, y, 0.01), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x + 1, y + 1, 0.01), reflectionCoef));
        }
        else if(orientation.equals(Orientation.TWO_HUNDRED_SEVENTY)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticLine(new LineSegment(x, y + 1, x + 1, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x + 1, y + 1), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x, y, 0.01), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x, y + 1, 0.01), reflectionCoef));
            this.physicsComponentList.add(new StaticCircle(new Circle(x + 1, y + 1, 0.01), reflectionCoef));
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
