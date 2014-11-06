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
    public Absorber(int xCoord, int yCoord, int kMultiplier, int mMultiplier, String name) throws InvalidInvariantException {
        
        super(new GridPoint(xCoord, yCoord), name);
        setTriggers(new ArrayList<Gadget>());
        this.reflectionCoef = 1;

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
                    .timeUntilWallCollision(edge, ball.getBallCircle(),
                            ball.getVelocity()));
        }
        
        for (Circle circle : this.getCircles()) {
            minCollisionTime = Math.min(minCollisionTime, Geometry
                    .timeUntilCircleCollision(circle, ball.getBallCircle(),
                            ball.getVelocity()));
        }
        return minCollisionTime;
    }
    
    public void collision(Ball ball) {
        if(!this.loaded) {
            this.loadedBall = ball;
            this.loadedBall.updateCenterX(this.getX() + this.getK() - this.loadedBall.getBallCircle().getRadius());
            this.loadedBall.updateCenterY(this.getY() + this.getM() - this.loadedBall.getBallCircle().getRadius());
            this.loadedBall.inAbsorber = true;
            this.loadedBall.setVelocity(new Vect(0.0, 0.0));
            setLoaded(true);
        }
    }

    /**
     * This function triggers the reactions given by the collisions of the ball with this absorber
     * @param ball is the ball that collides with this absorber
     * @param the time that the ball takes to collide with this absorber
     * @throws InvalidInvariantException 
     */
    @Override
    public void updateGadgetPosition(double time) {
        final double BALL_RADIUS = 0.25;

        for(Gadget obj:this.gadgetsToTrigger) {
            obj.action();
        }
    }

    /**
     * Function that maps every absorber cell to a (x,y) coordinate
     * @param pointToObject is the hashMap of every (x,y) point to the object that exists there, if any
     */
    public void putPoint(HashMap<DoublePair, Gadget> pointToObject) {
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
    public void action() {
        final double BALL_RADIUS = 0.25;
        if (this.loaded == true) {
            setLoaded(false);
            this.loadedBall.setVelocity(new Vect(0, this.loadedBall.getVelocity().y() - 50));
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

    @Override
    public char charRep() {
        // TODO Auto-generated method stub
        return 0;
    }
}
