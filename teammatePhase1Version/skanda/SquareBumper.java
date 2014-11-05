package phase1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import phase1.Util.InvalidInvariantException;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Geometry.DoublePair;

public class SquareBumper extends GameObject {

    private final LineSegment upperEdge;
    private final LineSegment lowerEdge;
    private final LineSegment rightEdge;
    private final LineSegment leftEdge;
    private final List<LineSegment> edges;

    private final Circle upLeftCircle;
    private final Circle upRightCircle;
    private final Circle downLeftCircle;
    private final Circle downRightCircle;
    private final List<Circle> circles;

    /**
     * Create a square-shaped bumper with these parameters:
     * 
     * @param xCoord - the x coordinate of the bumper's origin
     * @param yCoord - the y coordinate of the bumper's origin
     * @throws InvalidInvariantException 
     */
    public SquareBumper(int xCoord, int yCoord) throws InvalidInvariantException {
        setPosition(xCoord, yCoord);

        setCoefficient(1.0);
        upperEdge = new LineSegment(this.getX(), this.getY(), this.getX() + 1,
                this.getY());
        lowerEdge = new LineSegment(this.getX(), this.getY() + 1,
                this.getX() + 1, this.getY() + 1);
        rightEdge = new LineSegment(this.getX() + 1, this.getY(),
                this.getX() + 1, this.getY() + 1);
        leftEdge = new LineSegment(this.getX(), this.getY(), this.getX(),
                this.getY() + 1);
        edges = new ArrayList<LineSegment>(Arrays.asList(upperEdge, lowerEdge,
                rightEdge, leftEdge));

        upLeftCircle = new Circle(this.getX(), this.getY(), 0);
        upRightCircle = new Circle(this.getX() + 1, this.getY(), 0);
        downLeftCircle = new Circle(this.getX(), this.getY() + 1, 0);
        downRightCircle = new Circle(this.getX() + 1, this.getY() + 1, 0);
        circles = new ArrayList<Circle>(Arrays.asList(upRightCircle,
                upLeftCircle, downRightCircle, downLeftCircle));
        
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
        if ( this.getX() < 0 || this.getY() < 0 || this.getX() >= Board.width || this.getY() >= Board.height ) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @return a line segment representing the upper edge of the bumper
     */
    public LineSegment getUpperEdge() {
        return this.upperEdge;
    }

    /**
     * 
     * @return a line segment representing the lower edge of the bumper
     */
    public LineSegment getLowerEdge() {
        return this.lowerEdge;
    }

    /**
     * 
     * @return a line segment representing the right edge of the bumper
     */
    public LineSegment getRightEdge() {
        return this.rightEdge;
    }

    /**
     * 
     * @return a line segment representing the left edge of the bumper
     */
    public LineSegment getLeftEdge() {
        return this.leftEdge;
    }

    /**
     * 
     * @return a list of line segments representing the edges of the bumper
     */
    public List<LineSegment> getEdges() {
        return this.edges;
    }

    /**
     * 
     * @return a list of circles representing the corners of the bumper
     */
    public List<Circle> getCircles() {
        return this.circles;
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
    public double timeUntilCollision(Ball ball) {
        double minCollisionTime = Double.POSITIVE_INFINITY;

        for (LineSegment edge : this.getEdges()) {
            double currCollisionTime = Geometry.timeUntilWallCollision(edge, ball.getCircle(), ball.getVelocity());
            minCollisionTime = Math.min(minCollisionTime, currCollisionTime);
        }

        for (Circle circle : this.getCircles()) {
            double currCollisionTime = Geometry.timeUntilCircleCollision(circle, ball.getCircle(), ball.getVelocity());
            minCollisionTime = Math.min(minCollisionTime, currCollisionTime);
        }
        return minCollisionTime;
    }

    /**
     * This function triggers the reactions given by the collisions of the ball
     * with this bumper
     * 
     * @param ball - the ball that collides with this bumper
     * @param time - time that the ball takes to collide with this bumper
     * @throws InvalidInvariantException 
     */
    @Override
    public void reactWhenHit(Ball ball, double time) throws UnsupportedOperationException, InvalidInvariantException {

        Circle oldCircle = ball.getCircle();
        boolean hitMinEdge = false;
        double CenterX = ball.getCenterX() + ball.getVelocity().x() * time;
        double CenterY = ball.getCenterY() + ball.getVelocity().y() * time;
        
        ball.updateCenterX(CenterX);
        ball.updateCenterY(CenterY);
        
        for (LineSegment edge : this.getEdges()) {
            double currCollisionTime = Geometry.timeUntilWallCollision(edge, oldCircle, ball.getVelocity());
            if (collisionTimesEqual(time, currCollisionTime)) {
                ball.updateVelocityWithGravityAndFriction(Geometry.reflectWall(edge, ball.getVelocity(), this.getCoef()), time);
                hitMinEdge = true;
            }
        }

        if (!hitMinEdge) {
            for (Circle circle : this.getCircles()) {
                double currCollisionTime = Geometry.timeUntilCircleCollision(circle, oldCircle, ball.getVelocity());
                if (collisionTimesEqual(time, currCollisionTime)) {
                    ball.updateVelocityWithGravityAndFriction(Geometry.reflectCircle(circle.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), this.getCoef()), time);
                }
            }
        }

        for (GameObject obj : this.getTriggers()) {
            obj.doTriggerAction();
        }
    }

    /**
     * Function that maps the square bumper to a (x,y) coordinate
     * @param pointToObject is the hashMap of every (x,y) point to the object that exists there, if any
     */
    public void putPoint(HashMap<DoublePair, GameObject> pointToObject) {
        pointToObject.put(new DoublePair((int) Math.floor(this.getX()),
                (int) Math.floor(this.getY())), this);
    }
    
    /**
     * This function triggers the trigger action specific to this bumper
     * @throws UnsupportedOperationException
     */
    @Override
    public void doTriggerAction() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();

    }
}
