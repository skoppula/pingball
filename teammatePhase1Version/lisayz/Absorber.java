package Pingball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Geometry.DoublePair;

public class Absorber implements Gadget {

    private final LineSegment leftBumper;
    private final LineSegment rightBumper;
    private final LineSegment topBumper;
    private final LineSegment bottomBumper;

    private final Circle topLeftCorner;
    private final Circle topRightCorner;
    private final Circle bottomLeftCorner;
    private final Circle bottomRightCorner;
    
    private final DoublePair position;
    private final DoublePair ballPosition;
    private final DoublePair size;
    
    private final List<Gadget> gadgetsToTrigger;

    private List<Ball> balls = new ArrayList<Ball>();
    
    private final boolean triggersSelf;
    /**
     * Creates an Absorber with the given k & m such that the absorber is a 
     * rectangle with size kL x mL
     * @param k positive integer <=20
     * @param m positive integer <=20
     * @param list of Gadgets that the Absorber should trigger when hit
     */
    public Absorber(DoublePair position, int k, int m, List<Gadget> gadgetsToTrigger, boolean triggersSelf) {
        this.gadgetsToTrigger = Collections.unmodifiableList(gadgetsToTrigger);
        this.triggersSelf = triggersSelf;
        
        this.position = position;
        this.size = new DoublePair(k, m);
                
        double x = position.d1;
        double y = position.d2;
        
        leftBumper = new LineSegment(x, y, x, y+m);
        rightBumper = new LineSegment(x+k, y, x+k, y+m);
        topBumper = new LineSegment(x, y, x+k, y);
        bottomBumper = new LineSegment(x, y+m, x+k, y+m);

        topLeftCorner = new Circle(new Vect(x, y), 0);
        topRightCorner = new Circle(new Vect(x+k, y), 0);
        bottomLeftCorner = new Circle(new Vect(x, y+m), 0);
        bottomRightCorner = new Circle(new Vect(x+k, y+m), 0);

        ballPosition = new DoublePair(x+k - 0.25, y+m - 0.25);
    }
    
    /**
     * Returns (width, height)
     */
    @Override
    public DoublePair getBoardRepSize() {
        return size;
    }

    @Override
    public double getTimeUntilCollision(Ball ball) {
        double topLeftCollisionTime = Geometry.timeUntilCircleCollision(topLeftCorner, ball.getGirth(),
                ball.getVelocity());
        double topRightCollisionTime = Geometry.timeUntilCircleCollision(topRightCorner, ball.getGirth(),
                ball.getVelocity());
        double bottomLeftCollisionTime = Geometry.timeUntilCircleCollision(bottomLeftCorner, ball.getGirth(),
                ball.getVelocity());
        double bottomRightCollisionTime = Geometry.timeUntilCircleCollision(bottomRightCorner, ball.getGirth(),
                ball.getVelocity());

        double minCornerCollision = Math.min(topLeftCollisionTime, Math.min(topRightCollisionTime, Math.min(bottomLeftCollisionTime, bottomRightCollisionTime)));
        
        double leftCollisionTime = Geometry.timeUntilWallCollision(leftBumper, ball.getGirth(),
                ball.getVelocity());
        double rightCollisionTime = Geometry.timeUntilWallCollision(rightBumper, ball.getGirth(),
                ball.getVelocity());
        double topCollisionTime = Geometry.timeUntilWallCollision(topBumper, ball.getGirth(),
                ball.getVelocity());
        double bottomCollisionTime = Geometry.timeUntilWallCollision(bottomBumper, ball.getGirth(),
                ball.getVelocity());
        
        double minWallCollision = Math.min(leftCollisionTime, Math.min(rightCollisionTime, Math.min(topCollisionTime, bottomCollisionTime)));
        
        return Math.min(minCornerCollision, minWallCollision);
    }

    /**
     * Returns position of top left corner of the absorber as (x,y)
     */
    @Override
    public DoublePair getBoardRepPosition() {
        return position;
    }
    
    

    /**
     * Mutates ball to express effects of collision
     * Specifically, will change the position of the ball to be .25L from the bottom and .25L from the right side of the absorber and give it 0 velocity
     * @param ball that the collision will happen with
     */
    @Override
    public void collision(Ball ball) {
        if(!balls.contains(ball)) {
            ball.setPosition(ballPosition);
            ball.setVelocity(new Vect(0, 0));
            
            balls.add(ball);
            trigger();
        }
    }

    /**
     * Triggers any associated gadgets (including itself)
     */
    @Override
    public void trigger() {
        if(this.triggersSelf) {
            this.action();
        }
        for(Gadget gadget: gadgetsToTrigger)
            gadget.action();
    }

    /**
     * If the absorber is holding a ball, it should mutate that ball to have an initial velocity of 50L/sec
     * If not, it should do nothing
     */
    @Override
    public void action() {
        if (!balls.isEmpty()) {
            Ball nextBall = balls.remove(0);
            nextBall.setPosition(new DoublePair(nextBall.getPosition().d1, position.d2));
            nextBall.setVelocity(new Vect(0, -50));
        }        
    }
    
    public char charRep() {
        return '=';
    }

    @Override
    public void updateGadgetPosition(double timeDelta) {}
    
    @Override
    public String toString(){
        return "Absorber";
    }

    @Override
    public DoublePair getSize() {
        return this.size;
    }
}
