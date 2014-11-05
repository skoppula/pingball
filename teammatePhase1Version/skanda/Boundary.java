package phase1;

import java.util.HashMap;

import phase1.Util.InvalidInvariantException;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Geometry.DoublePair;

public class Boundary extends GameObject {

    LineSegment boundary;
    Circle boundaryCircleOne;
    Circle boundaryCircleTwo;
    boolean transparent;

    /**
     * Create an opaque boundary object with these parameters:
     * 
     * @param xCoord - the x coordinate of the bumper's origin
     * @param yCoord - the y coordinate of the bumper's origin
     * @param boundary - the line segment that describes a boundary
     * @throws InvalidInvariantException 
     */
    public Boundary(int xCoord, int yCoord, LineSegment boundary) throws InvalidInvariantException {
        setPosition(xCoord, yCoord);
        setCoefficient(1.0);
        this.boundary = boundary;
        this.boundaryCircleOne = new Circle(boundary.p1(), 0);
        this.boundaryCircleTwo = new Circle(boundary.p2(), 0);
        this.transparent = false;
        if (!this.checkRep()) {
            throw new Util.InvalidInvariantException();
        }
    }

    /**
     * Create an opaque or transparent boundary object with these parameters:
     * 
     * @param xCoord - the x coordinate of the bumper's origin
     * @param yCoord - the y coordinate of the bumper's origin
     * @param boundary - the line segment that describes a boundary
     * @param transparent - specify transparency or opacity of boundary
     * @throws InvalidInvariantException 
     */
    public Boundary(int xCoord, int yCoord, LineSegment boundary,
            boolean transparent) throws InvalidInvariantException {
        setPosition(xCoord, yCoord);
        this.boundary = boundary;
        this.boundaryCircleOne = new Circle(boundary.p1(), 0);
        this.boundaryCircleTwo = new Circle(boundary.p2(), 0);
        this.transparent = transparent;
        if (!this.checkRep()) {
            throw new Util.InvalidInvariantException();
        }
    }

    /**
     * Function that checks the representation invariant of boundary
     * The boundary's origin must be within the board
     * @return a boolean that specifies whether or not the rep invariant is preserved
     */
    protected boolean checkRep() {
        if ( this.getX() < 0 || this.getY() < 0 || this.getX() > Board.width || this.getY() > Board.height ) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @return a LineSegment object representing the segment of this boundary
     */
    public LineSegment getLine() {
        return new LineSegment(boundary.p1().x(), boundary.p1().y(), boundary
                .p2().x(), boundary.p2().y());
    }

    /**
     * @return string representation of the boundary
     */
    @Override
    public String toString() {
        return ".";
    }

    /**
     * @param ball - the ball that collides with this boundary
     * @return the time that the ball takes to collide with this boundary
     */
    @Override
    public double timeUntilCollision(Ball ball) {
        double collisionTime = Geometry.timeUntilWallCollision(this.getLine(),
                ball.getCircle(), ball.getVelocity());
        double endPointCTOne = Geometry.timeUntilCircleCollision(
                boundaryCircleOne, ball.getCircle(), ball.getVelocity());
        double endPointCTTwo = Geometry.timeUntilCircleCollision(
                boundaryCircleTwo, ball.getCircle(), ball.getVelocity());
        collisionTime = endPointCTOne < collisionTime ? endPointCTOne
                : collisionTime;
        collisionTime = endPointCTTwo < collisionTime ? endPointCTTwo
                : collisionTime;
        return collisionTime;
    }

    /**
     * This function triggers the reactions given by the collisions of the ball
     * with this boundary
     * 
     * @param ball - the ball that collides with this boundary
     * @param time - time that the ball takes to collide with this boundary
     * @throws InvalidInvariantException 
     */
    @Override
    public void reactWhenHit(Ball ball, double time) throws UnsupportedOperationException, InvalidInvariantException {
      
        Circle oldCircle = ball.getCircle();
        
        double CenterX = ball.getCenterX() + ball.getVelocityX() * time;
        double CenterY = ball.getCenterY() + ball.getVelocityY() * time;
        
        ball.updateCenterX(CenterX);
        ball.updateCenterY(CenterY);
        
        double endPointCTOne = Geometry.timeUntilCircleCollision(boundaryCircleOne, oldCircle, ball.getVelocity());
        double endPointCTTwo = Geometry.timeUntilCircleCollision(boundaryCircleTwo, oldCircle, ball.getVelocity());
        
        if (time == endPointCTOne) {
            ball.updateVelocityWithGravityAndFriction(Geometry.reflectCircle(this.boundaryCircleOne.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), this.getCoef()), time);
        } else if (time == endPointCTTwo) {
            ball.updateVelocityWithGravityAndFriction(Geometry.reflectCircle(this.boundaryCircleTwo.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), this.getCoef()), time);
        } else {
            ball.updateVelocityWithGravityAndFriction(Geometry.reflectWall(boundary, ball.getVelocity(), this.getCoef()), time);
        }
        
        for (GameObject obj : this.getTriggers()) {
            obj.doTriggerAction();
        }
    }

    /**
     * Empty method because boundary objects should not exist in the mapping
     * It does nothing for boundary, because boundary is not part of the board per se
     * @param pointToObject is the hashMap of every (x,y) point to the object that exists there, if any
     */
    public void putPoint(HashMap<DoublePair, GameObject> pointToObject) {
        
    }
    
    /**
     * This function triggers the trigger action specific to this boundary
     */
    @Override
    public void doTriggerAction() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}
