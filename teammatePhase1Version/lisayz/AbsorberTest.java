package Pingball;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import physics.Geometry.DoublePair;

public class AbsorberTest {
    
    /*
     * Testing partitions:
     * - Absorber Size
     *      - Absorber of size 1
     *      - Absorber of size > 1
     * - getTimeUntilCollision:
     *      - Always positive
     *      - 0 when about to collide
     *      - Correct time away from wall that it's going to collide with
     * - collision
     *      - changes position of ball appropriately
     *      - calls all triggers
     *      - balls contains one more ball
     *      - collisions from multiple sides
     *      - multiple balls
     * - action
     *      - multiple balls
     *      - Should do nothing if ball list empty
     *      - Should set ball's velocity to -50
     * - trigger
     *      - test triggering self (other trigger tests in TriggersTest)
     */
    
    public static Ball ball1;
    public static Ball ball2;
    public static Ball ball3;
    public static Ball ball4;
    public static Ball ball5;
        
    @BeforeClass
    public static void setUpBeforeClass() {
        ball1 = new Ball(new Vect(0, 6), new DoublePair(5.5, 4.75));
        ball2 = new Ball(new Vect(10, -5), new DoublePair(5.5, 8.25));
        ball3 = new Ball(new Vect(10, 10), new DoublePair(4.75, 4.75));
        ball4 = new Ball(new Vect(-10, -10), new DoublePair(4.75, 4.75));
        ball5 = new Ball(new Vect(10, 10), new DoublePair(5.5, 4));
    }

    public static Absorber makeSmallAbsorber() {
        return new Absorber(new DoublePair(5, 5), 1, 1, new ArrayList<Gadget>(), false);
    }

    public static Absorber makeSelfTriggerAbsorber() {
        return new Absorber(new DoublePair(5, 5), 1, 1, new ArrayList<Gadget>(), true);
    }

    public static Absorber makeLargeAbsorber() {
        ArrayList<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.add(new SquareBumper(new DoublePair(10, 10), new ArrayList<Gadget>(), false));
        gadgetList.add(new CircleBumper(new DoublePair(15, 15), new ArrayList<Gadget>(), false));

        return new Absorber(new DoublePair(5, 5), 3, 3, gadgetList, false);
    }
    
    @Test
    public void testSize1AbsorberCollisionCollidingOnTopAndAction() {
        Ball testBall = new Ball(new Vect(0, 6), new DoublePair(5.5, 4.75));
        Absorber smallAbsorber = makeSmallAbsorber();
        
        double collisiontime = smallAbsorber.getTimeUntilCollision(testBall);
        assertEquals(0, collisiontime, 0.0001);
        
        smallAbsorber.collision(testBall);
        assertEquals(new DoublePair(5.75, 5.75), testBall.getPosition());
        assertEquals(new Vect(0, 0), testBall.getVelocity());
        
        smallAbsorber.action();
        assertEquals(new DoublePair(5.75, 5.0), testBall.getPosition());
        assertEquals(new Vect(0, -50), testBall.getVelocity());

    }
    
    @Test
    public void testLargeAbsorberCollisionCollidingOnBottomAndAction() {
        Ball testBall = new Ball(new Vect(10, -5), new DoublePair(5.5, 8.25));
        Absorber largeAbsorber = makeLargeAbsorber();
        
        double collisiontime = largeAbsorber.getTimeUntilCollision(testBall);
        assertEquals(0, collisiontime, 0.0001);
        
        largeAbsorber.collision(testBall);
        assertEquals(new DoublePair(7.75, 7.75), testBall.getPosition());
        assertEquals(new Vect(0, 0), testBall.getVelocity());

        largeAbsorber.action();
        assertEquals(new DoublePair(7.75, 5.0), testBall.getPosition());
        assertEquals(new Vect(0, -50), testBall.getVelocity());
    }

    @Test
    public void testLargeAbsorberCollisionCornerCase() {
        Absorber largeAbsorber = makeLargeAbsorber();

        double collisiontime = largeAbsorber.getTimeUntilCollision(ball3);
        assertEquals(0, collisiontime, 0.01);
    }

    @Test
    public void testLargeAbsorberCollisionNegativeInfinity() {
        Absorber largeAbsorber = makeLargeAbsorber();

        double collisiontime = largeAbsorber.getTimeUntilCollision(ball4);
        assertEquals(Double.POSITIVE_INFINITY, collisiontime, 0.0001);
    }

    @Test
    public void testlargeAbsorberPositiveTimeCollision() {
        Absorber largeAbsorber = makeLargeAbsorber();

        double collisiontime = largeAbsorber.getTimeUntilCollision(ball5);
        assertEquals(0.075, collisiontime, 0.0001);
    }
    
    @Test
    public void testMultipleBallCollisionsAndAction() {
        Absorber largeAbsorber = makeLargeAbsorber();

        Ball testBall1 = new Ball(new Vect(10, -5), new DoublePair(5.5, 8.25));
        Ball testBall2 = new Ball(new Vect(10, -5), new DoublePair(6.5, 8.25));
        
        double collisiontime1 = largeAbsorber.getTimeUntilCollision(testBall1);
        assertEquals(0, collisiontime1, 0.0001);

        double collisiontime2 = largeAbsorber.getTimeUntilCollision(testBall2);
        assertEquals(0, collisiontime2, 0.0001);

        largeAbsorber.collision(testBall1);
        largeAbsorber.collision(testBall2);

        assertEquals(new DoublePair(7.75, 7.75), testBall1.getPosition());
        assertEquals(new Vect(0, 0), testBall1.getVelocity());

        assertEquals(new DoublePair(7.75, 7.75), testBall2.getPosition());
        assertEquals(new Vect(0, 0), testBall2.getVelocity());
        
        largeAbsorber.action();

        assertEquals(new DoublePair(7.75, 5.0), testBall1.getPosition());
        assertEquals(new Vect(0, -50), testBall1.getVelocity());        
        
        assertEquals(new DoublePair(7.75, 7.75), testBall2.getPosition());
        assertEquals(new Vect(0, 0), testBall2.getVelocity());

        largeAbsorber.action();
        
        assertEquals(new DoublePair(7.75, 5.0), testBall2.getPosition());
        assertEquals(new Vect(0, -50), testBall2.getVelocity());
    }
    
    @Test
    public void testTriggersSelf() {
        Ball testBall = new Ball(new Vect(0, 6), new DoublePair(5.5, 4.75));        
        Absorber selfTriggerAbsorber = makeSelfTriggerAbsorber();
        
        double collisiontime = selfTriggerAbsorber.getTimeUntilCollision(testBall);
        assertEquals(0, collisiontime, 0.0001);
        
        selfTriggerAbsorber.collision(testBall);
        assertEquals(new DoublePair(5.75, 5.0), testBall.getPosition());
        assertEquals(new Vect(0, -50), testBall.getVelocity());
    }
    
}
