package Pingball;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Geometry.DoublePair;
import physics.Vect;

public abstract class Flipper implements Gadget {

    // static final constants
    protected static final int WIDTH = 2; // units = L
    protected static final int HEIGHT = 2; // units = L
    protected static final double COEFF_OF_REFLECTION = 0.95;
    protected static final double ROTATIONAL_SPEED = Math.toRadians(1080);
    protected static final double MAX_ROTATION_ANGLE = 90;
    protected static final double EPSILON = 0.0001;
    
    // final variables
    protected final DoublePair originPosition;
    protected final Orientation orientation;
    protected final List<Gadget> gadgetsToTrigger = new ArrayList<Gadget>();
    
    // changing variables
    protected Angle currentAngle; // magnitude of current angle (measured from default to max. angle)
    protected LineSegment lineSegRep; // line segment representation of the flipper
    protected Circle endOfFlipperCircle; // circle of zero radius at the end of the flipper
    protected Circle centerOfRotationCircle; // circle of zero radius at the end of the flipper
    protected double rotationalVelocity; // positive = clockwise, negative = anti-clockwise
    
    protected boolean triggersSelf;
    
    /**
     * Constructs a new Flipper with the given coordinates and orientation.
     * Sets the LineSegment representation to the Left Flipper's default position given its orientation
     * Sets the LineSegment rotation direction to anti-clockwise
     * Sets the angle of the LeftFlipper to 0 (meaning that it is at its default start angle)
     * @param originPosition
     * @param orientation
     * @param gadgets   the gadgets whose action the Flipper should trigger
     */
    public Flipper(DoublePair originPosition, Orientation orientation, List<Gadget> gadgets, boolean triggersSelf) {
        this.triggersSelf = triggersSelf;
        this.originPosition = originPosition;
        this.orientation = orientation;
        centerOfRotationCircle = new Circle(this.getCenterOfRotation(), 0);
        endOfFlipperCircle = new Circle(this.getDefaultEndOfFlipper(), 0);
        for (Gadget gadget: gadgets) {
            this.gadgetsToTrigger.add(gadget);
        }
        this.currentAngle = new Angle(0);
    }
    
    /**
     * An x & y coordinate representing the center of rotation
     * @return Vector representing center of rotation
     */
    protected abstract Vect getCenterOfRotation();
    
    /**
     * the x & y coordinate of the the end of the flipper that rotates. Changes as the flipper rotates.
     * @return Vect representing the position of the other end of the flipper
     */
    protected Vect getEndOfFlipper() {
        return lineSegRep.p2();
    }
    
    /**
     * @return Vect representing the x & y coordinates of the end of the flipper before it has started rotating
     */
    protected abstract Vect getDefaultEndOfFlipper();
    
    /**
     * Changes the flipper's fields to reflect a change in time, assuming it doesn't collide with anything
     * @param deltaTime   the time frame over which to update the flipper
     */
    public abstract void updateGadgetPosition(double deltaTime); 
    
    /**
     * @param deltaTime  time in seconds
     * @return the new LineSegment representation of the Flipper after time deltaTime
     */
    protected LineSegment getLineSegRep() {
        return lineSegRep;
    }

    /**
     * @return width of the Flipper
     */
    public int getWidth() {
        return WIDTH;
    }
    
    /**
     * @return height of the Flipper
     */
    public int getHeight() {
        return HEIGHT;
    }
        
    /**
     * The velocity that the flipper is moving at in radians/second. 
     * Negative for anti-clockwise, positive for clockwise
     * @return  +ROTATIONAL_SPEED if rotating clockwise, -ROTATIONAL_SPEED if rotating anti-clockwise, 0 otherwise
     */
    protected double getRotationalVelocity() {
        return rotationalVelocity;
    }
    
    
    /**
     * Sets the ball's velocity to its new velocity
     * Changes the rotation direction of the flipper
     */
    @Override
    public void collision(Ball ball) {
        Vect newBallVelocity;
        // if the ball will collide with the end of the flipper circle first
        if (this.getTimeUntilEndOfFlipperCollision(ball)<EPSILON) {
            newBallVelocity = Geometry.reflectRotatingCircle(endOfFlipperCircle, this.getCenterOfRotation(), this.getRotationalVelocity(), ball.getGirth(), ball.getVelocity(), COEFF_OF_REFLECTION);
        }
        // if the ball will collide with the center of rotation first
        else if (this.getTimeUntilCORCollision(ball)<EPSILON) {
            newBallVelocity = Geometry.reflectCircle(centerOfRotationCircle.getCenter(), ball.getGirth().getCenter(), ball.getVelocity(), COEFF_OF_REFLECTION);
        }
        // if the ball will collide with the circle at the end of the flipper first
        else {
            newBallVelocity = Geometry.reflectRotatingWall(lineSegRep, this.getCenterOfRotation(), this.getRotationalVelocity(), ball.getGirth(), ball.getVelocity(), COEFF_OF_REFLECTION);
        }
        ball.setVelocity(newBallVelocity);
        trigger();
    }

    /**
     * @return the character representation of a Flipper
     */
    @Override
    public char charRep() {
        switch(orientation) {
            case NINETY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return '-';
                else return '|';
            case ONE_HUNDRED_EIGHTY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return '|';
                else return '-';
            case TWO_HUNDRED_SEVENTY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return '-';
                else return '|';
            default: //case ZERO
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return '|';
                else return '-';
        }
    }
    
    /**
     * @return double   the time until the Line Segment part of the Flipper collides with a ball in seconds
     */
    public double getTimeUntilLineSegCollision(Ball ball) {
        double timeBeforeLineSegCollision;
        if (this.getRotationalVelocity() == 0) {
            timeBeforeLineSegCollision = Geometry.timeUntilWallCollision(lineSegRep, ball.getGirth(), ball.getVelocity());
        }
        else {
            timeBeforeLineSegCollision = Geometry.timeUntilRotatingWallCollision(lineSegRep, this.getCenterOfRotation(), this.rotationalVelocity, ball.getGirth(), ball.getVelocity());
        }
        return timeBeforeLineSegCollision; 
    }
    
    /**
     * @return double   the time until the COR part of the Flipper collides with a ball in seconds
     */
    public double getTimeUntilCORCollision(Ball ball) {
        double timeBeforeCORCollision = Geometry.timeUntilCircleCollision(centerOfRotationCircle, ball.getGirth(), ball.getVelocity());
        return timeBeforeCORCollision;
    }
    
    /**
     * @return double   the time until the end of the Flipper collides with a ball in seconds
     */
    public double getTimeUntilEndOfFlipperCollision(Ball ball) {
        double timeBeforeEndOfFlipperCollision;
        if (this.getRotationalVelocity() == 0) {
            timeBeforeEndOfFlipperCollision = Geometry.timeUntilCircleCollision(endOfFlipperCircle, ball.getGirth(), ball.getVelocity());
        }
        else {
            timeBeforeEndOfFlipperCollision = Geometry.timeUntilRotatingCircleCollision(endOfFlipperCircle, this.getCenterOfRotation(), this.rotationalVelocity, ball.getGirth(), ball.getVelocity());
        }
        return timeBeforeEndOfFlipperCollision;
    }

    /**
     * @return double   the time until the Flipper collides with a ball in seconds
     */
    @Override
    public double getTimeUntilCollision(Ball ball) {
        double minSoFar =  Math.min(this.getTimeUntilEndOfFlipperCollision(ball), this.getTimeUntilCORCollision(ball));
        double minCollisionTime = Math.min(minSoFar, this.getTimeUntilLineSegCollision(ball));
        if (minCollisionTime < Math.pow(10, -10)) {
            return EPSILON;
        }
        else {
            return minCollisionTime;
        }  
    }
    
    /**
     * Triggers the actions of all gadgets in the Flipper's list of gadgets to trigger
     */
    @Override
    public void trigger() {
        if(triggersSelf)
            this.action();
        for (Gadget gadget: gadgetsToTrigger) {
            gadget.action();
        }
    }
    
    /**
     * Action the Flipper takes when it's triggered
     */
    @Override
    public abstract void action();
    
    /**
     * @return boolean whether the Flipper is swung 90˚ from its default position
     */
    abstract boolean inFullySwungPosition();


    /**
     * @return DoublePair size of the Flipper as taken up in the board's representation
     */
    @Override
    public DoublePair getBoardRepSize() {
        switch(orientation) {
            case NINETY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return new DoublePair(2, 1);
                else return new DoublePair(1, 2);
            case ONE_HUNDRED_EIGHTY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return new DoublePair(1, 2); 
                else return new DoublePair(2, 1);
            case TWO_HUNDRED_SEVENTY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return new DoublePair(2, 1);
                else return new DoublePair(1, 2);
            default: //case ZERO
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return new DoublePair(1, 2); 
                else return new DoublePair(2, 1);
        }
    }

    @Override
    public String toString(){
        return "Flipper";
    }
}
