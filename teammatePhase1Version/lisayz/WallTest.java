package Pingball;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Pingball.Gadget.Orientation;
import physics.Vect;
import physics.Geometry.DoublePair;

public class WallTest {
    
    /*
     * Testing partitions:
     * - Wall orientations (all four)
     * - Time until collision
     *      - Colliding with wall
     *      - Going to collide with wall
     *      - Will never collide with wall
     * - Collision
     *      - Correctly reflects ball/changes its velocity
     */

    public static Wall topWall;
    public static Wall bottomWall;
    public static Wall leftWall;
    public static Wall rightWall;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        topWall = new Wall(Orientation.NINETY);
        bottomWall = new Wall(Orientation.TWO_HUNDRED_SEVENTY);
        leftWall = new Wall(Orientation.ZERO);
        rightWall = new Wall(Orientation.ONE_HUNDRED_EIGHTY);
    }

    @Test
    public void testTopWallColliding() {
        Ball testBall = new Ball(new Vect(1, -5), new DoublePair(11, 0.25));
        
        double collisionTime = topWall.getTimeUntilCollision(testBall);
        assertEquals(0, collisionTime, 0.0001);
        
        topWall.collision(testBall);
        assertEquals(new Vect(1.0, 5.0), testBall.getVelocity());
    }
    
    @Test
    public void testLeftWallGoingToCollide() {
        Ball testBall = new Ball(new Vect(-5, 1), new DoublePair(11, 12));

        double collisionTime = leftWall.getTimeUntilCollision(testBall);
        assertEquals(2.15, collisionTime, 0.0001);
    }

    @Test
    public void testBottomWallNeverColliding() {
        Ball testBall = new Ball(new Vect(1, -5), new DoublePair(11, 0.25));
        
        double collisionTime = bottomWall.getTimeUntilCollision(testBall);
        assertEquals(Double.POSITIVE_INFINITY, collisionTime, 0.0001);
    }

    @Test
    public void testRightWallColliding() {
        Ball testBall = new Ball(new Vect(5, 1), new DoublePair(19.75, 5));
        
        double collisionTime = rightWall.getTimeUntilCollision(testBall);
        assertEquals(0, collisionTime, 0.0001);
        
        rightWall.collision(testBall);
        assertEquals(new Vect(-5.0, 1.0), testBall.getVelocity());
    }

}
