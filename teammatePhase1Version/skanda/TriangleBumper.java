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

public class TriangleBumper extends GameObject {

    public enum TriangleBumperType {
        UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT
    }

    private TriangleBumperType bumperType;

    private final LineSegment upperEdge;
    private final LineSegment lowerEdge;
    private final LineSegment rightEdge;
    private final LineSegment leftEdge;
    private final LineSegment forwardDiagonal;
    private final LineSegment backwardDiagonal;
    private List<LineSegment> edges = new ArrayList<LineSegment>();
    
    private Circle firstCircle;
    private Circle secondCircle;
    private Circle thirdCircle;
    private Circle fourthCircle;
    private List<Circle> circles = new ArrayList<Circle>();

    /**
     * Create a triangle-shaped bumper with these parameters:
     * @param xCoord = the x coordinate of the bumper's origin
     * @param yCoord = the y coordinate of the bumper's origin
     * @param type = the type of the bumper: the right angle of the triangle is:
     *  - the upleft corner
     *  - the upright corner
     *  - the downleft corner
     *  - the downright corner
     * @throws InvalidInvariantException 
     */
    public TriangleBumper(int xCoord, int yCoord, TriangleBumperType type) throws InvalidInvariantException {
        setPosition(xCoord, yCoord);
        
        firstCircle = new Circle(this.getX(), this.getY(), 0);
        secondCircle = new Circle(this.getX() + 1, this.getY(), 0);
        thirdCircle = new Circle(this.getX(), this.getY() + 1, 0);
        fourthCircle = new Circle(this.getX() + 1, this.getY() + 1, 0);
        
        upperEdge = new LineSegment(this.getX(), this.getY(), this.getX() + 1, this.getY());
        lowerEdge = new LineSegment(this.getX(), this.getY() + 1, this.getX() + 1, this.getY() + 1);
        rightEdge = new LineSegment(this.getX() + 1, this.getY(), this.getX() + 1, this.getY() + 1);
        leftEdge = new LineSegment(this.getX(), this.getY(), this.getX(), this.getY() + 1);
        forwardDiagonal = new LineSegment(this.getX(), this.getY() + 1, this.getX() + 1, this.getY());
        backwardDiagonal = new LineSegment(this.getX(), this.getY(), this.getX() + 1, this.getY() + 1);

        setCoefficient(1.0);
        this.bumperType = type;
        switch (type) {
        case UPLEFT:
            edges = new ArrayList<LineSegment>(Arrays.asList(upperEdge,
                    forwardDiagonal, leftEdge));
            circles = new ArrayList<Circle>(Arrays.asList(firstCircle, secondCircle, thirdCircle));
            break;
        case UPRIGHT:
            edges = new ArrayList<LineSegment>(Arrays.asList(upperEdge,
                    backwardDiagonal, rightEdge));
            circles = new ArrayList<Circle>(Arrays.asList(firstCircle, secondCircle, fourthCircle));
            break;
        case DOWNLEFT:
            edges = new ArrayList<LineSegment>(Arrays.asList(lowerEdge,
                    backwardDiagonal, leftEdge));
            circles = new ArrayList<Circle>(Arrays.asList(firstCircle, fourthCircle, thirdCircle));
            break;
        case DOWNRIGHT:
            edges = new ArrayList<LineSegment>(Arrays.asList(lowerEdge,
                    forwardDiagonal, rightEdge));
            circles = new ArrayList<Circle>(Arrays.asList(fourthCircle, secondCircle, thirdCircle));
            break;

        default:
            break;
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
        if ( this.getX() < 0 || this.getY() < 0 || this.getX() >= Board.width || this.getY() >= Board.height ) {
            return false;
        }
        return true;
    }
    
    /**
     * @return the type of the triangle bumper, as defined above
     */
    public TriangleBumperType getType() {
        return this.bumperType;
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
     * @return string representation of the triangular bumper
     */
    @Override
    public String toString() {
        return (bumperType == TriangleBumperType.UPLEFT)
                || (bumperType == TriangleBumperType.DOWNRIGHT) ? "/" : "\\";
    }

    /**
     * @param ball is the ball that collides with this bumper
     * @return the time that the ball takes to collide with this bumper
     */
    @Override
    public double timeUntilCollision(Ball ball) {
        double minCollisionTime = Double.POSITIVE_INFINITY;

        for (LineSegment edge : this.getEdges()) {
            minCollisionTime = Math.min(minCollisionTime, Geometry.timeUntilWallCollision(edge, ball.getCircle(), ball.getVelocity()));
        }
        
        for (Circle circle : this.getCircles()) {
            minCollisionTime = Math.min(minCollisionTime, Geometry.timeUntilCircleCollision(circle, ball.getCircle(), ball.getVelocity()));
        }

        return minCollisionTime;
    }

    /**
     * This function triggers the reactions given by the collisions of the ball with this bumper
     * @param ball is the ball that collides with this bumper
     * @param the time that the ball takes to collide with this bumper
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
     * Function that maps the triangle bumper to a (x,y) coordinate
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
