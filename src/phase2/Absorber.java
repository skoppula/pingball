package phase2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import phase2.Util.InvalidInvariantException;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Geometry.DoublePair;

public class Absorber extends Gadget {

    private final int m;
    private final int k;
    private boolean loaded;
    private Ball loadedBall;

    private final LineSegment top;
    private final LineSegment bottom;
    private final LineSegment left;
    private final LineSegment right;
    private final List<LineSegment> edges;
    
    private final Circle upLeftCircle;
    private final Circle upRightCircle;
    private final Circle downLeftCircle;
    private final Circle downRightCircle;
    private final List<Circle> circles;
    
    /**
     * A game object that represents an absorber as described in the notes
     * It has top, bottom, right, and left edges as LineSegments
     * 
     * @param xCoord - x coordinate
     * @param yCoord - y coordinate
     * @param kMultiplier - the size of the length
     * @param mMultiplier - the size of the height
     * @throws InvalidInvariantException 
     */
    public Absorber(int xCoord, int yCoord, int kMultiplier, int mMultiplier) throws InvalidInvariantException {

        setPosition(xCoord, yCoord);
        
        setTriggers(new ArrayList<GameObject>());
        setCoefficient(1);

        this.m = mMultiplier;
        this.k = kMultiplier;
        this.loaded = false;
        
        this.top = new LineSegment(this.getX(), this.getY(), this.getX() + this.getK(), this.getY());
        this.bottom = new LineSegment(this.getX(), this.getY() + this.getM(), this.getX() + this.getK(), this.getY() + this.getM());
        this.left = new LineSegment(this.getX(), this.getY(), this.getX(), this.getY() + this.getM());
        this.right = new LineSegment(this.getX() + this.getK(), this.getY(), this.getX() + this.getK(), this.getY() + this.getM());
        this.edges = new ArrayList<LineSegment>(Arrays.asList(top, bottom, right, left));
      
        this.upLeftCircle = new Circle(this.getX(), this.getY(), 0.01);
        this.upRightCircle = new Circle(this.getX() + this.getK(), this.getY(), 0.01);
        this.downLeftCircle = new Circle(this.getX(), this.getY() + this.getM(), 0.01);
        this.downRightCircle = new Circle(this.getX() + this.getK(), this.getY() + this.getM(), 0.01);
        this.circles = new ArrayList<Circle>(Arrays.asList(upRightCircle, upLeftCircle, downRightCircle, downLeftCircle));
        
        if (!this.checkRep()) {
            throw new Util.InvalidInvariantException();
        }
    }

    /**
     * Function that checks the representation invariant of absorber
     * The absorber's origin must be within the board
     * @return a boolean that specifies whether or not the rep invariant is preserved
     */
    protected boolean checkRep() {
        if ( this.getX() < 0 || this.getY() < 0 || this.getX() >= Board.width || this.getY() >= Board.height ) {
            return false;
        }
        return true;
    }
    
    /**
     * Function that sets if the absorber contains a ball or not
     * It changes the state of toggle whenever the absorber contains or not a ball
     */
    public void setLoaded(boolean b) {
        this.loaded = b;
    }
    
    /**
     * @return string representation of absorber
     */
    @Override
    public String toString() {
        return "=";
    }

    /**
     * @param ball is the ball that collides with this absorber
     * @return the time that the ball takes to collide with this absorber
     */
    public double timeUntilCollision(Ball ball) {
        double minCollisionTime = Double.POSITIVE_INFINITY;

        for (LineSegment edge : this.getEdges()) {
            minCollisionTime = Math.min(minCollisionTime, Geometry
                    .timeUntilWallCollision(edge, ball.getCircle(),
                            ball.getVelocity()));
        }
        
        for (Circle circle : this.getCircles()) {
            minCollisionTime = Math.min(minCollisionTime, Geometry
                    .timeUntilCircleCollision(circle, ball.getCircle(),
                            ball.getVelocity()));
        }
        return minCollisionTime;
    }

    /**
     * This function triggers the reactions given by the collisions of the ball with this absorber
     * @param ball is the ball that collides with this absorber
     * @param the time that the ball takes to collide with this absorber
     * @throws InvalidInvariantException 
     */
    @Override
    public void reactWhenHit(Ball ball, double time) throws UnsupportedOperationException, InvalidInvariantException {
        final double BALL_RADIUS = 0.25;
        if(!this.loaded) {
            ball.updateCenterX(this.getX() + this.getK() - BALL_RADIUS);
            ball.updateCenterY(this.getY() + this.getM() - BALL_RADIUS);
            ball.updateVelocity(new Vect(0.0, 0.0));
            this.loadedBall = ball;
            ball.inAbsorber = true;
            setLoaded(true);
            
        } else {
            
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
        }

        for(GameObject obj:this.getTriggers()) {
            obj.doTriggerAction();
        }

    }

    /**
     * Function that maps every absorber cell to a (x,y) coordinate
     * @param pointToObject is the hashMap of every (x,y) point to the object that exists there, if any
     */
    public void putPoint(HashMap<DoublePair, GameObject> pointToObject) {
        for (int k = 0; k < this.getK(); k++) {
            for (int m = 0; m < this.getM(); m++) {
            pointToObject.put(new DoublePair((int) Math.floor(this.getX() + k),
                    (int) Math.floor(this.getY() + m)), this);
            }
        }
    }
    
    /**
     * This function triggers the trigger action specific to this absorber
     * @throws InvalidInvariantException 
     */
    @Override
    public void doTriggerAction() throws UnsupportedOperationException, InvalidInvariantException {
        final double BALL_RADIUS = 0.25;
        if (this.loaded == true) {
            setLoaded(false);
            this.loadedBall.updateVelocityWithGravityAndFriction(new Vect(this.loadedBall.getVelocityX(), this.loadedBall.getVelocityY() - 50), Board.timeStep);
            loadedBall.updateCenterX(this.getX() + k - BALL_RADIUS);
            loadedBall.updateCenterY(this.getY() - BALL_RADIUS);
            
            this.loadedBall.inAbsorber = false;
        }
    }

    /** 
     * @return a line segment representing the upper edge of the absorber
     */
    public LineSegment getUpperEdge() {
        return this.top;
    }

    /**
     * @return a line segment representing the lower edge of the absorber
     */
    public LineSegment getLowerEdge() {
        return this.bottom;
    }

    /**
     * 
     * @return a line segment representing the right edge of the absorber
     */
    public LineSegment getRightEdge() {
        return this.right;
    }

    /**
     * 
     * @return a line segment representing the left edge of the absorber
     */
    public LineSegment getLeftEdge() {
        return this.left;
    }

    /**
     * 
     * @return a list of line segments representing the edges of the absorber
     */
    public List<LineSegment> getEdges() {
        return this.edges;
    }
    
    /**
     * @return an int m representing the height of the absorber
     */
    public int getM() {
        return this.m;
    }
    
    /**
     * @return an int m representing the height of the absorber
     */
    public int getK() {
        return this.k;
    }

    /**
     * @return a list of circles representing the corners of the bumper
     */
    public List<Circle> getCircles() {
        return this.circles;
    }
}
