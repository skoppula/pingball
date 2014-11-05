package Pingball;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Geometry.DoublePair;
import physics.Vect;

public class TriangleBumper implements Gadget {

    private final double EPSILON = 0.01;
    
    private final LineSegment leg1Bumper;
    private final LineSegment leg2Bumper;
    private final LineSegment hypotenuseBumper;

    private final Circle corner1;
    private final Circle corner2;
    private final Circle corner3;
    
    private final DoublePair position;
    private final Orientation orientation;

    private final boolean triggerSelf;

    private final List<Gadget> gadgetsToTrigger;

    /**
     * Constructs a TriangleBumper that could trigger other Gadgets when hit
     * 
     * @param position
     *            of TriangleBumper on board
     * @param triggers
     *            other Gadgets that hitting this TriangleBumper should trigger
     */
    public TriangleBumper(DoublePair position, Orientation orientation,
            List<Gadget> gadgetsToTrigger, boolean triggerSelf) {
        this.gadgetsToTrigger = Collections.unmodifiableList(gadgetsToTrigger);
        this.position = position;
        this.orientation = orientation;
        this.triggerSelf = triggerSelf;

        double x = position.d1;
        double y = position.d2;

        Vect topLeft = new Vect(x, y);
        Vect bottomLeft = new Vect(x, y + 1);
        Vect topRight = new Vect(x + 1, y);
        Vect bottomRight = new Vect(x + 1, y + 1);
        
        switch (orientation) {
        case NINETY:
            leg1Bumper = new LineSegment(topLeft, topRight);
            leg2Bumper = new LineSegment(topRight, bottomRight);
            hypotenuseBumper = new LineSegment(topLeft, bottomRight);
            corner1 = new Circle(topLeft, 0);
            corner2 = new Circle(topRight, 0);
            corner3 = new Circle(bottomRight, 0);
            break;
        case ONE_HUNDRED_EIGHTY:
            leg1Bumper = new LineSegment(topRight, bottomRight);
            leg2Bumper = new LineSegment(bottomLeft, bottomRight);
            hypotenuseBumper = new LineSegment(bottomLeft, topRight);
            corner1 = new Circle(bottomLeft, 0);
            corner2 = new Circle(topRight, 0);
            corner3 = new Circle(bottomRight, 0);
            break;
        case TWO_HUNDRED_SEVENTY:
            leg1Bumper = new LineSegment(topLeft, bottomLeft);
            leg2Bumper = new LineSegment(bottomLeft, bottomRight);
            hypotenuseBumper = new LineSegment(topLeft, bottomRight);
            corner1 = new Circle(topLeft, 0);
            corner2 = new Circle(bottomLeft, 0);
            corner3 = new Circle(bottomRight, 0);
            break;
        default: // case ZERO
            leg1Bumper = new LineSegment(topLeft, topRight);
            leg2Bumper = new LineSegment(topLeft, bottomLeft);
            hypotenuseBumper = new LineSegment(bottomLeft, topRight);
            corner1 = new Circle(topLeft, 0);
            corner2 = new Circle(topRight, 0);
            corner3 = new Circle(bottomLeft, 0);
            break;
        }
    }

    /**
     * Returns time until ball collides with TriangleBumper, infinity if never
     */
    @Override
    public double getTimeUntilCollision(Ball ball) {
        double corner1CollisionTime = Geometry.timeUntilCircleCollision(corner1, ball.getGirth(),
                ball.getVelocity());
        double corner2CollisionTime = Geometry.timeUntilCircleCollision(corner2, ball.getGirth(),
                ball.getVelocity());
        double corner3CollisionTime = Geometry.timeUntilCircleCollision(corner3, ball.getGirth(),
                ball.getVelocity());
        
        double cornerMin = Math.min(corner1CollisionTime, Math.min(corner2CollisionTime, corner3CollisionTime));
        
        double leftCollisionTime = Geometry.timeUntilWallCollision(leg1Bumper,
                ball.getGirth(), ball.getVelocity());
        double bottomCollisionTime = Geometry.timeUntilWallCollision(
                leg2Bumper, ball.getGirth(), ball.getVelocity());
        double hypotenuseCollisionTime = Geometry.timeUntilWallCollision(
                hypotenuseBumper, ball.getGirth(), ball.getVelocity());

        double sideMin = Math.min(leftCollisionTime,
                Math.min(bottomCollisionTime, hypotenuseCollisionTime));
        
        return Math.min(sideMin, cornerMin);
    }

    /**
     * Mutates the ball object as necessary to represent the effect of the
     * collision with the TriangleBumper Only mutates the ball's position if the
     * interaction with the TriangleBumper is not a standard reflection
     * 
     * @param ball
     *            that the collision will happen with
     */
    @Override
    public void collision(Ball ball) {
        trigger();
        List<LineSegment> possibleCollisions = Arrays.asList(leg1Bumper,
                leg2Bumper, hypotenuseBumper);

        List<Circle> possibleCornerCollisions = Arrays.asList(corner1, corner2, corner2);

        Vect velocity = ball.getVelocity();

        for (LineSegment wall : possibleCollisions) {
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
     * Returns the size of every TriangleBumper, (1,1)
     */
    @Override
    public DoublePair getBoardRepSize() {
        return new DoublePair(1, 1);
    }

    /**
     * Returns the size of every TriangleBumper, (1,1)
     */
    @Override
    public DoublePair getSize() {
        return new DoublePair(1, 1);
    }

    /**
     * Returns position of TriangleBumper on the Board
     */
    @Override
    public DoublePair getBoardRepPosition() {
        return position;
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
     * Does nothing as TriangleBumper has no action
     */
    @Override
    public void action() {
    }

    @Override
    public char charRep() {
        if (orientation == Orientation.ZERO
                || orientation == Orientation.ONE_HUNDRED_EIGHTY)
            return '/';
        return '\\';
    }

    @Override
    public void updateGadgetPosition(double timeDelta) {
    }

    @Override
    public String toString() {
        return "TriangleBumper";
    }
}
