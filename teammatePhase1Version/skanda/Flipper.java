package phase1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import phase1.Util.InvalidInvariantException;
import physics.Circle;
import physics.Geometry;
import physics.Geometry.DoublePair;
import physics.LineSegment;

public class Flipper extends GameObject {

    static private final double SPEED = 1080 * Math.PI/180.0;

    public enum FlipperType {
        CLOCKWISE, COUNTERCLOCKWISE
    }

    public enum FlipperTypeExternal {
        LEFT, RIGHT
    }

    public enum FlipperRotation {
        ZERO, NINETY, ONEEIGHTY, TWOSEVENTY
    }

    public enum FlipperOrientation {
        EAST, NORTH, WEST, SOUTH
    }

    private FlipperType type;
    private FlipperOrientation orientation;
    Circle boundaryCircleOne;
    Circle boundaryCircleTwo;
    private List<Ball> balls;

    private LineSegment segment;

    /**
     * Create a flipper with these parameters:
     * @param xCoord - the x coordinate of the flipper's origin
     * @param yCoord - the y coordinate of the flipper's origin
     * @param externalType - flipper type: either left or right
     * @param rotation - flipper's rotation
     * @param balls - the balls on the board
     * @throws InvalidInvariantException 
     */
    public Flipper(int xCoord, int yCoord, FlipperTypeExternal externalType, FlipperRotation rotation, List<Ball> balls) throws InvalidInvariantException {
        
        DoublePair dp = rotateCoords(xCoord, yCoord, externalType, rotation);
        setPosition((int) dp.d1, (int) dp.d2);
        final double DEFAULT_COEF = 0.95;
        setCoefficient(DEFAULT_COEF);

        this.balls = balls;
        this.type = externalType == FlipperTypeExternal.LEFT ? FlipperType.COUNTERCLOCKWISE : FlipperType.CLOCKWISE;
        switch(rotation) {
            case ZERO: this.orientation = FlipperOrientation.SOUTH;
            case NINETY: this.orientation = FlipperOrientation.WEST;
            case ONEEIGHTY: this.orientation = FlipperOrientation.NORTH;
            default: this.orientation = FlipperOrientation.EAST;
        }
        
        this.setSegmentPosition();
        if(!checkRep())
            throw new Util.InvalidInvariantException();
    }
    
    /**
     * Method that rotates a flipper and modifies the coordinates accordingly
     * @param xCoord - the x coordinate of the flipper
     * @param yCoord - the y coordinate of the flipper
     * @param externalType - the type of the flipper
     * @param rotation - the rotation angle of the flipper
     * @return - a pair of coordinates for the newly rotated flipper
     */
    public DoublePair rotateCoords(int xCoord, int yCoord, FlipperTypeExternal externalType, FlipperRotation rotation) {
        if (externalType == FlipperTypeExternal.LEFT) {
            switch(rotation) {
                case ZERO: return new DoublePair(xCoord, yCoord);
                case NINETY: return new DoublePair(xCoord+1, yCoord);
                case ONEEIGHTY: return new DoublePair(xCoord + 1, yCoord + 1);
                default: return new DoublePair(xCoord, yCoord + 1);
            }
        } else {
            switch(rotation) {
                case ZERO: return new DoublePair(xCoord, yCoord);
                case NINETY: return new DoublePair(xCoord, yCoord + 1);
                case ONEEIGHTY: return new DoublePair(xCoord - 1, yCoord + 1);
                default: return new DoublePair(xCoord - 1, yCoord);
            }
        }
    }

    /**
     * 
     * @return the type of the flipper: right or left
     */
    public FlipperType getType() {
        return this.type;
    }
    
    /**
     * Creates the representation of the flipper, according to the orientation it has
     */
    public void setSegmentPosition() {
        this.boundaryCircleOne = new Circle(this.getX(), this.getY(), 0);

        if (this.orientation == FlipperOrientation.NORTH)
            this.boundaryCircleTwo = new Circle(this.getX(), this.getY() - 1, 0);
        else if (this.orientation == FlipperOrientation.SOUTH)
            this.boundaryCircleTwo = new Circle(this.getX(), this.getY() + 1, 0);
        else if (this.orientation == FlipperOrientation.EAST)
            this.boundaryCircleTwo = new Circle(this.getX() + 1, this.getY(), 0);
        else
            this.boundaryCircleTwo = new Circle(this.getX()-1, this.getY(), 0);

        this.segment = new LineSegment(boundaryCircleOne.getCenter(), boundaryCircleTwo.getCenter());
    }
    
    /**
     * Change the type of a flipper
     */
    public void toggleType() {
        this.type = this.type == FlipperType.CLOCKWISE ? FlipperType.COUNTERCLOCKWISE : FlipperType.CLOCKWISE;
    }

    /**
     * @return the orientation of the flipper: up or down
     */
    public FlipperOrientation getOrientation() {
        return this.orientation;
    }

    /**
     * Method that changes the position of the flipper
     * if it's down, it becomes up, and vice-versa
     */
    public void togglePosition() {
        if (this.type == FlipperType.CLOCKWISE) {
            if (this.orientation == FlipperOrientation.NORTH)
                this.orientation = FlipperOrientation.EAST;
            else if (this.orientation == FlipperOrientation.SOUTH)
                this.orientation = FlipperOrientation.WEST;
            else if (this.orientation == FlipperOrientation.EAST)
                this.orientation = FlipperOrientation.SOUTH;
            else
                this.orientation = FlipperOrientation.NORTH;
        } else {
            if (this.orientation == FlipperOrientation.NORTH)
                this.orientation = FlipperOrientation.WEST;
            else if (this.orientation == FlipperOrientation.SOUTH)
                this.orientation = FlipperOrientation.EAST;
            else if (this.orientation == FlipperOrientation.EAST)
                this.orientation = FlipperOrientation.NORTH;
            else
                this.orientation = FlipperOrientation.SOUTH;
        }
        toggleType();
        setSegmentPosition();
    }

    /**
     * 
     * @return a line segment representing the flipper
     */
    public LineSegment getSegment() {
        return new LineSegment(segment.p1(), segment.p2());
    }

    /**
     * @return string representation of the flipper
     */
    @Override
    public String toString() {
        return this.orientation == FlipperOrientation.EAST || this.orientation == FlipperOrientation.WEST ? "-" : "|";
    }

    /**
     * @param ball is the ball that collides with this flipper
     * @return the time that the ball takes to collide with this flipper
     */
    @Override
    public double timeUntilCollision(Ball ball) {
        double collisionTime = Geometry.timeUntilWallCollision(this.segment, ball.getCircle(), ball.getVelocity());
        double endPointCTOne = Geometry.timeUntilCircleCollision(boundaryCircleOne, ball.getCircle(), ball.getVelocity());
        double endPointCTTwo = Geometry.timeUntilCircleCollision(boundaryCircleTwo, ball.getCircle(), ball.getVelocity());
        collisionTime = endPointCTOne < collisionTime ? endPointCTOne : collisionTime;
        collisionTime = endPointCTTwo < collisionTime ? endPointCTTwo : collisionTime;
        return collisionTime;
    }

    /**
     * This function triggers the reactions given by the collisions of the ball with this flipper
     * @param ball is the ball that collides with this flipper
     * @param the time that the ball takes to collide with this flipper
     * @throws InvalidInvariantException 
     */
    @Override
    public void reactWhenHit(Ball ball, double time) throws UnsupportedOperationException, InvalidInvariantException {

        double endPointCTOne = Geometry.timeUntilCircleCollision(boundaryCircleOne, ball.getCircle(), ball.getVelocity());
        double endPointCTTwo = Geometry.timeUntilCircleCollision(boundaryCircleTwo, ball.getCircle(), ball.getVelocity());

        if (time == endPointCTOne) {
            ball.updateVelocityWithGravityAndFriction(Geometry.reflectCircle(this.boundaryCircleOne.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), this.getCoef()), time);
        } else if (time == endPointCTTwo) {
            ball.updateVelocityWithGravityAndFriction(Geometry.reflectCircle(this.boundaryCircleTwo.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), this.getCoef()), time);
        } else {
            ball.updateVelocityWithGravityAndFriction(Geometry.reflectWall(this.getSegment(), ball.getVelocity(), this.getCoef()), time);
        }

        ball.updateCenterX(ball.getCenterX() + ball.getVelocityX() * time);
        ball.updateCenterY(ball.getCenterY() + ball.getVelocityY() * time);

        for (GameObject obj : this.getTriggers()) {
            obj.doTriggerAction();
        }
    }
    
    /**
     * 
     * @return - a list with the coordinates of the squares that are affected by the flipper
     */
    public List<DoublePair> getHitSquares() {
        List<DoublePair> out;
        if (type == FlipperType.CLOCKWISE && orientation == FlipperOrientation.EAST || type == FlipperType.COUNTERCLOCKWISE && orientation == FlipperOrientation.SOUTH) {
            DoublePair d1 = new DoublePair(segment.p1().x()+1,segment.p1().y());
            DoublePair d2 = new DoublePair(segment.p1().x()+1,segment.p1().y()+1);
            DoublePair d3 = new DoublePair(segment.p1().x(),segment.p1().y()+1);
            out = new ArrayList<DoublePair>(Arrays.asList(d1, d2, d3));
            return out;

        } else if (type == FlipperType.CLOCKWISE && orientation == FlipperOrientation.NORTH || type == FlipperType.COUNTERCLOCKWISE && orientation == FlipperOrientation.EAST) {
            DoublePair d1 = new DoublePair(segment.p1().x()+1,segment.p1().y());
            DoublePair d2 = new DoublePair(segment.p1().x()+1,segment.p1().y()-1);
            DoublePair d3 = new DoublePair(segment.p1().x(),segment.p1().y()-1);
            out = new ArrayList<DoublePair>(Arrays.asList(d1, d2, d3));
            return out;
        } else if (type == FlipperType.CLOCKWISE && orientation == FlipperOrientation.WEST || type == FlipperType.COUNTERCLOCKWISE && orientation == FlipperOrientation.NORTH) {
            DoublePair d1 = new DoublePair(segment.p1().x()-1,segment.p1().y());
            DoublePair d2 = new DoublePair(segment.p1().x()-1,segment.p1().y()-1);
            DoublePair d3 = new DoublePair(segment.p1().x(),segment.p1().y()-1);
            out = new ArrayList<DoublePair>(Arrays.asList(d1, d2, d3));
            return out;
        } else {
            DoublePair d1 = new DoublePair(segment.p1().x()-1,segment.p1().y());
            DoublePair d2 = new DoublePair(segment.p1().x()-1,segment.p1().y()+1);
            DoublePair d3 = new DoublePair(segment.p1().x(),segment.p1().y()+1);
            out = new ArrayList<DoublePair>(Arrays.asList(d1, d2, d3));
            return out;
        }
        
    }

    /**
     * This function triggers the trigger action specific to this flipper
     */
    @Override
    public void doTriggerAction() {
        
        List<DoublePair> hitSquares = this.getHitSquares();
        
        for(Ball ball:this.balls) {
            if(hitSquares.contains(new DoublePair(Math.floor(ball.getCenterX()), Math.floor(ball.getCenterY())))) {
                if (this.type == FlipperType.CLOCKWISE) {
                    ball.updateVelocityWithGravityAndFriction(Geometry.reflectRotatingWall(this.segment, this.segment.p1(), Flipper.SPEED, ball.getCircle(), ball.getVelocity(), this.getCoef()), Board.timeStep);
                }
            }
        }

        togglePosition();
    }
    
    /**
     * Function that maps the flipper to (x,y) coordinates
     * @param pointToObject is the hashMap of every (x,y) point to the object that exists there, if any
     */
    public void putPoint(HashMap<DoublePair, GameObject> pointToObject) {
        pointToObject.put(new DoublePair((int) Math.floor(this.getSegment().p1().x()),
                (int) Math.floor(this.getSegment().p1().y())), this);
        pointToObject.put(new DoublePair((int) Math.floor(this.getSegment().p2().x()),
                (int) Math.floor(this.getSegment().p2().y())), this);
    }
    
    /**
     * Checks the representation invariant of Flipper
     *  - if flipper is in the 4x4 box (checked by making sure length of segment = 1)
     *  - on the board
     * @return if the rep invariant is met
     */
    protected boolean checkRep() {
        if (this.segment.length() != 1) return false;
        if (this.segment.p1().x() < 0 || this.segment.p1().x() >= Board.width) return false;
        if (this.segment.p1().y() < 0 || this.segment.p1().y() >= Board.height) return false;
        if (this.segment.p2().x() < 0 || this.segment.p2().x() >= Board.width) return false;
        if (this.segment.p2().y() < 0 || this.segment.p2().y() >= Board.height) return false;
        return true;
    }
}
