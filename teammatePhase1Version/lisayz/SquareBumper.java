package Pingball;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Geometry.DoublePair;
import physics.Vect;

public class SquareBumper implements Gadget {

    private final double EPSILON = 0.01;
    
    private final LineSegment leftBumper;
    private final LineSegment rightBumper;
    private final LineSegment topBumper;
    private final LineSegment bottomBumper;
    
    private final Circle topLeftCorner;
    private final Circle topRightCorner;
    private final Circle bottomLeftCorner;
    private final Circle bottomRightCorner;

    private final DoublePair position;

    private final boolean triggerSelf;

    private final List<Gadget> gadgetsToTrigger;

    /**
     * Constructs a SquareBumper that could trigger other Gadgets when hit
     * 
     * @param position
     *            of SquareBumper on board9.7
     * @param triggers
     *            other Gadgets that hitting this SquareBumper should trigger
     */
    public SquareBumper(DoublePair position, List<Gadget> gadgetsToTrigger,
            boolean triggerSelf) {
        this.gadgetsToTrigger = Collections.unmodifiableList(gadgetsToTrigger);
        this.position = position;

        double x = position.d1;
        double y = position.d2;

        leftBumper = new LineSegment(x, y, x, y + 1);
        rightBumper = new LineSegment(x + 1, y, x + 1, y + 1);
        topBumper = new LineSegment(x, y, x + 1, y);
        bottomBumper = new LineSegment(x, y + 1, x + 1, y + 1);

        topLeftCorner = new Circle(new Vect(x, y), 0);
        topRightCorner = new Circle(new Vect(x+1, y), 0);
        bottomLeftCorner = new Circle(new Vect(x, y+1), 0);
        bottomRightCorner = new Circle(new Vect(x+1, y+1), 0);
        
        this.triggerSelf = triggerSelf;
    }

    /**
     * Returns the size of every Square Bumper, (1,1)
     */
    @Override
    public DoublePair getBoardRepSize() {
        return new DoublePair(1, 1);
    }

    /**
     * Returns the size of every Square Bumper, (1,1)
     */
    @Override
    public DoublePair getSize() {
        return new DoublePair(1, 1);
    }

    /**
     * Returns time until ball collides with SquareBumper, infinity if never
     */
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
     * Returns position of SquareBumper on the Board
     */
    @Override
    public DoublePair getBoardRepPosition() {
        return position;
    }

    /**
     * Mutates the ball object as necessary to represent the effect of the
     * collision with the SquareBumper Only mutates the ball's position if the
     * interaction with the SquareBumper is not a standard reflection
     * 
     * @param ball
     *            that the collision will happen with
     */
    @Override
    public void collision(Ball ball) {
        trigger();
        List<LineSegment> possibleWallCollisions = Arrays.asList(leftBumper,
                rightBumper, topBumper, bottomBumper);

        List<Circle> possibleCornerCollisions = Arrays.asList(topLeftCorner,topRightCorner,bottomLeftCorner,bottomRightCorner);

        Vect velocity = ball.getVelocity();

        for (LineSegment wall : possibleWallCollisions) {
            double timeUntilCollision = Geometry.timeUntilWallCollision(wall,
                    ball.getGirth(), ball.getVelocity());
            if (timeUntilCollision == 0) {
                ball.setVelocity(Geometry.reflectWall(wall, velocity));
                return;
            }
        }
        for (Circle corner : possibleCornerCollisions) {
            double timeUntilCollision = Geometry.timeUntilCircleCollision(corner,
                    ball.getGirth(), ball.getVelocity());
            if (timeUntilCollision < EPSILON) {
                ball.setVelocity(Geometry.reflectCircle(corner.getCenter(), ball.getGirth().getCenter(), velocity));
                return;
            }
        }

    }

    /**
     * Triggers any other associated Gadgets
     */
    @Override
    public void trigger() {

        this.action();
        for (Gadget gadget : gadgetsToTrigger) {
            gadget.action();
        }
        if (this.triggerSelf) this.action();
    }

    /**
     * Does nothing as SquareBumper has no action
     */
    @Override
    public void action() {
    }

    @Override
    public char charRep() {
        return '#';
    }

    @Override
    public String toString() {
        return "SquareBumper";
    }

    @Override
    public void updateGadgetPosition(double timeDelta) {
    }

}
