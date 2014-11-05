package Pingball;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import physics.Geometry.DoublePair;

public class SquareBumperTest {

    /*
     * Testing partitions:
     *  - getTimeUntilCollision/Collision:
     *      - Not going to collide
     *      - About to collide
     *      - Colliding
     *  - Collision
     *      - Hitting different sides of bumper
     *      - Corner case
     */
    
    public static SquareBumper squareBumper;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        List<Gadget> emptyList = new ArrayList<Gadget>();
        squareBumper = new SquareBumper(new DoublePair(10, 10), emptyList, false);
    }
    
    @Test
    public void testSquareBumperCollisionCollidingOnTop() {
        Ball testBall = new Ball(new Vect(1, 6), new DoublePair(10.5, 9.75));
        
        double collisiontime = squareBumper.getTimeUntilCollision(testBall);
        assertEquals(0, collisiontime, 0.0001);
        
        squareBumper.collision(testBall);
        assertEquals(new Vect(1, -6), testBall.getVelocity());
    }
    
    @Test
    public void testRightSquareBumperGoingToCollide() {
        Ball testBall = new Ball(new Vect(-5, 1), new DoublePair(10.5, 10.5));

        double collisionTime = squareBumper.getTimeUntilCollision(testBall);
        assertEquals(0.05, collisionTime, 0.0001);
    }

    @Test
    public void testBottomSquareBumperNeverColliding() {
        Ball testBall = new Ball(new Vect(1, -5), new DoublePair(11, 0.25));
        
        double collisionTime = squareBumper.getTimeUntilCollision(testBall);
        assertEquals(Double.POSITIVE_INFINITY, collisionTime, 0.0001);
    }

    @Test
    public void testLeftWallColliding() {
        Ball testBall = new Ball(new Vect(5, 1), new DoublePair(9.75, 10.5));
        
        double collisionTime = squareBumper.getTimeUntilCollision(testBall);
        assertEquals(0, collisionTime, 0.0001);
        
        squareBumper.collision(testBall);
        assertEquals(new Vect(-5.0, 1), testBall.getVelocity());
    }

    @Test
    public void testSquareBumperCollisionCornerCase() {
        Ball testBall = new Ball(new Vect(10, 10), new DoublePair(9.75, 9.75));

        double collisiontime = squareBumper.getTimeUntilCollision(testBall);
        assertEquals(0, collisiontime, 0.01);
        
        squareBumper.collision(testBall);
        assertEquals(-10, testBall.getVelocity().x(), 0.0001);
        assertEquals(-10, testBall.getVelocity().y(), 0.0001);
    }
}
