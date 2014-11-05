package Pingball;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import Pingball.Gadget.Orientation;
import physics.Vect;
import physics.Geometry.DoublePair;

public class TriangleBumperTest {

    /*
     * Testing partitions:
     *  - Four different orientations
     *  - getTimeUntilCollision/Collision:
     *      - Not going to collide
     *      - About to collide
     *      - Colliding
     *  - Collision
     *      - Hitting different sides of bumper
     *      - Corner case
     */
    
    public static TriangleBumper triangleBumper1;
    public static TriangleBumper triangleBumper2;
    public static TriangleBumper triangleBumper3;
    public static TriangleBumper triangleBumper4;

    @BeforeClass
    public static void setUpBeforeClass() {
        List<Gadget> emptyList = new ArrayList<Gadget>();
        
        triangleBumper1 = new TriangleBumper(new DoublePair(5, 5), Orientation.ZERO, emptyList, false);
        triangleBumper2 = new TriangleBumper(new DoublePair(5, 5), Orientation.NINETY, emptyList, false);
        triangleBumper3 = new TriangleBumper(new DoublePair(5, 5), Orientation.ONE_HUNDRED_EIGHTY, emptyList, false);
        triangleBumper4 = new TriangleBumper(new DoublePair(5, 5), Orientation.TWO_HUNDRED_SEVENTY, emptyList, false);        
    }
    
    @Test
    public void testTriangleBumper0CollisionSide1() {
        Ball testBall = new Ball(new Vect(0, 5), new DoublePair(5.5, 4.75));
        
        double collisiontime = triangleBumper1.getTimeUntilCollision(testBall);
        assertEquals(0, collisiontime, 0.0001);
        
        triangleBumper1.collision(testBall);
        assertEquals(new Vect(0, -5), testBall.getVelocity());
    }
    
    @Test
    public void testTriangleBumper0NoCollisionSide2() {
        Ball testBall = new Ball(new Vect(-5 , 0), new DoublePair(4.75, 5));

        double collisionTime = triangleBumper1.getTimeUntilCollision(testBall);
        assertEquals(Double.POSITIVE_INFINITY, collisionTime, 0.0001);
    }

    @Test
    public void testTriangleBumper0GoingToCollideHypotenuse() {
        Ball testBall = new Ball(new Vect(-5, -5), new DoublePair(6, 6));
        
        double collisionTime = triangleBumper1.getTimeUntilCollision(testBall);
        assertEquals(0.064645, collisionTime, 0.0001);
    }
    
    @Test
    public void testTriangleBumper0CornerCase() {
        Ball testBall = new Ball(new Vect(-5, 0), new DoublePair(6.25, 5));
        
        double collisionTime = triangleBumper1.getTimeUntilCollision(testBall);
        assertEquals(0, collisionTime, 0.0001);
        
        triangleBumper1.collision(testBall);
        assertEquals(new Vect(5, 0), testBall.getVelocity());

    }
    
    @Test
    public void testTriangleBumper90CollisionSide2() {
        Ball testBall = new Ball(new Vect(-5, 0), new DoublePair(6.25, 5.5));
        
        double collisiontime = triangleBumper2.getTimeUntilCollision(testBall);
        assertEquals(0, collisiontime, 0.0001);
        
        triangleBumper2.collision(testBall);
        assertEquals(new Vect(5, 0), testBall.getVelocity());
    }
    
    @Test
    public void testTriangleBumper90GoingToCollideSide1() {
        Ball testBall = new Ball(new Vect(0, 5), new DoublePair(5.5, 4));
        
        double collisiontime = triangleBumper2.getTimeUntilCollision(testBall);
        assertEquals(0.15, collisiontime, 0.0001);
    }

    @Test
    public void testTriangleBumper90NoCollisioneHypotenuse() {
        Ball testBall = new Ball(new Vect(-5, 5), new DoublePair(5, 6));
        
        double collisionTime = triangleBumper2.getTimeUntilCollision(testBall);
        assertEquals(Double.POSITIVE_INFINITY, collisionTime, 0.0001);
    }

    @Test
    public void testTriangleBumper180GoingToCollideSide1() {
        Ball testBall = new Ball(new Vect(0, -5), new DoublePair(5.5, 7));
        
        double collisiontime = triangleBumper3.getTimeUntilCollision(testBall);
        assertEquals(0.15, collisiontime, 0.0001);        
    }
    
    @Test
    public void testTriangleBumper180NoCollisionSide2() {
        Ball testBall = new Ball(new Vect(5, 0), new DoublePair(7, 7));

        double collisionTime = triangleBumper3.getTimeUntilCollision(testBall);
        assertEquals(Double.POSITIVE_INFINITY, collisionTime, 0.0001);
    }

    @Test
    public void testTriangleBumper180CollisionHypotenuse() {
        Ball testBall = new Ball(new Vect(5, 6), new DoublePair(5.8, 5.45));
        
        double collisionTime = triangleBumper3.getTimeUntilCollision(testBall);
        assertEquals(0, collisionTime, 0.0001);
        
        triangleBumper3.collision(testBall);
        assertEquals(new Vect(-5.0, 6), testBall.getVelocity());
    }

    @Test
    public void testTriangleBumper270CollisionSide1() {
        Ball testBall = new Ball(new Vect(6, 1), new DoublePair(4.75, 5.5));
        
        double collisiontime = triangleBumper4.getTimeUntilCollision(testBall);
        assertEquals(0, collisiontime, 0.0001);
        
        triangleBumper4.collision(testBall);
        assertEquals(new Vect(-6, 1), testBall.getVelocity());
    }
    
    @Test
    public void testTriangleBumper270NoCollisionSide2() {
        Ball testBall = new Ball(new Vect(5, 5), new DoublePair(7, 7));

        double collisionTime = triangleBumper4.getTimeUntilCollision(testBall);
        assertEquals(Double.POSITIVE_INFINITY, collisionTime, 0.0001);
    }

    @Test
    public void testTriangleBumper270GoingToCollideHypotenuse() {
        Ball testBall = new Ball(new Vect(-5, 5), new DoublePair(7, 3));
        
        double collisionTime = triangleBumper4.getTimeUntilCollision(testBall);
        assertEquals(0.364645, collisionTime, 0.0001);
    }

}
