package phase2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import phase2.Util.InvalidInvariantException;
import phase2.physicsComponents.PhysicsComponent;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Geometry.DoublePair;

public class Absorber extends Gadget {

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
    public Absorber(int x, int y, String name, int width, int height) throws InvalidInvariantException {
        
        super(new GridPoint(x, y), name, width, height, 1); // the reflection coefficient doesn't matter in this case;
        setTriggers(new ArrayList<Gadget>());
        this.loaded = false;
        
        this.top = new LineSegment(this.getX(), this.getY(), this.getX() + this.width, this.getY());
        this.bottom = new LineSegment(this.getX(), this.getY() + this.height, this.getX() + this.width, this.getY() + this.height);
        this.left = new LineSegment(this.getX(), this.getY(), this.getX(), this.getY() + this.height);
        this.right = new LineSegment(this.getX() + this.width, this.getY(), this.getX() + this.width, this.getY() + this.height);
        this.edges = new ArrayList<LineSegment>(Arrays.asList(top, bottom, right, left));
      
        this.upLeftCircle = new Circle(this.getX(), this.getY(), 0.01);
        this.upRightCircle = new Circle(this.getX() + this.width, this.getY(), 0.01);
        this.downLeftCircle = new Circle(this.getX(), this.getY() + this.height, 0.01);
        this.downRightCircle = new Circle(this.getX() + this.width, this.getY() + this.height, 0.01);
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
    @Override
    public double getTimeUntilCollision(Ball ball) {
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
    
    @Override
    public void collision(Ball ball) {
        if(!this.loaded) {
            this.loadedBall = ball;
            this.loadedBall.updateCenterX(this.getX() + width - this.loadedBall.getBallCircle().getRadius());
            this.loadedBall.updateCenterY(this.getY() + height - this.loadedBall.getBallCircle().getRadius());
            this.loadedBall.inAbsorber = true;
            this.loadedBall.setVelocity(new Vect(0.0, 0.0));
            setLoaded(true);
        } //NEIGHTER BALL NOR ABSORBER IS AFFECTED IF ABSORBER ALREADY LOADED (PASS THROUGH FUNCTIONALITY) 
    }

    /**
     * This function triggers the reactions given by the collisions of the ball with this absorber
     * @param ball is the ball that collides with this absorber
     * @param the time that the ball takes to collide with this absorber
     * @throws InvalidInvariantException 
     */
    public void updateGadgetPosition(double time) {
        return;
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
            loadedBall.updateCenterX(this.getX() + width - BALL_RADIUS); 
            loadedBall.updateCenterY(this.getY() - BALL_RADIUS);
            
            this.loadedBall.inAbsorber = false;
        }
    }

    /**
     * 
     * @return a list of line segments representing the edges of the absorber
     */
    public List<LineSegment> getEdges() {
        return this.edges;
    }

    /**
     * @return a list of circles representing the corners of the bumper
     */
    public List<Circle> getCircles() {
        return this.circles;
    }

    @Override
    public char charRep() {
        return '=';
    }
}
