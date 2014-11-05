package Pingball;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Geometry.DoublePair;
import physics.Vect;

public class BallTest {

    /*
     * Partitions:
     *      
     *      Move:
     *          ZeroVelocity in x, y, and both
     *          Positive Velocity in x, y, and both
     *          Negative Velocity in x, y, and both
     *          
     *      Collision:
     *          
     * 
     */
    
    
    // Testing Move
    @Test
    public void testMoveZeroVelocity() {
        Ball ball = new Ball(new Vect(0, 0), new DoublePair(2));
        ball.move(10);
        assertEquals(new DoublePair(2), ball.getPosition());
    }

    @Test
    public void testMovePositiveXVelocity() {
        Ball ball = new Ball(new Vect(1, 0), new DoublePair(2));
        ball.move(10);
        assertEquals(new DoublePair(12, 2), ball.getPosition());
    }

    @Test
    public void testMovePositiveYVelocity() {
        Ball ball = new Ball(new Vect(0, 1), new DoublePair(2));
        ball.move(10);
        assertEquals(new DoublePair(2, 12), ball.getPosition());
    }
    
    @Test
    public void testMoveNegativeYVelocity() {
        Ball ball = new Ball(new Vect(0, -1), new DoublePair(19));
        ball.move(10);
        assertEquals(new DoublePair(19, 9), ball.getPosition());
    }

    @Test
    public void testMoveNegativeXYVelocity() {
        Ball ball = new Ball(new Vect(-.5, -1.5), new DoublePair(19));
        ball.move(10);
        assertEquals(new DoublePair(14, 4), ball.getPosition());
    }
    
    @Test
    public void testMoveNegativeXVelocity() {
        Ball ball = new Ball(new Vect(-1, 0), new DoublePair(19));
        ball.move(10);
        assertEquals(new DoublePair(9, 19), ball.getPosition());
    }


    @Test
    public void testMovePositiveXYVelocity() {
        Ball ball = new Ball(new Vect(.5, 1.5), new DoublePair(2));
        ball.move(10);
        assertEquals(new DoublePair(7, 17), ball.getPosition());
    }

    // Test Collision
    @Test
    public void testCollisionEqualandOppositeX() {
        Ball ball1 = new Ball(new Vect(-.5, 0), new DoublePair(2));
        Ball ball2 = new Ball(new Vect(.5, 0), new DoublePair(2.5, 2));

        ball2.collision(ball1);
        ball1.collision(ball2);
        assertEquals(new Vect(.5, 0), ball1.getVelocity());
        assertEquals(new Vect(-.5, 0), ball2.getVelocity());
    }

    @Test
    public void testCollisionEqualandOppositeY() {
        Ball ball1 = new Ball(new Vect(0, .5), new DoublePair(2));
        Ball ball2 = new Ball(new Vect(0, -.5), new DoublePair(2, 2.5));

        ball2.collision(ball1);
        ball1.collision(ball2);
        assertEquals(new Vect(0, -.5), ball1.getVelocity());
        assertEquals(new Vect(0, .5), ball2.getVelocity());
    }
    
    @Test
    public void testCollisionNonEqualVelocities() {
        Ball ball1 = new Ball(new Vect(5, 5), new DoublePair(2));
        Ball ball2 = new Ball(new Vect(-3, 12), new DoublePair(2, 2.5));

        ball2.collision(ball1);
        ball1.collision(ball2);
        assertEquals(new Vect(5, 12), ball1.getVelocity());
        assertEquals(new Vect(-3, 5), ball2.getVelocity());
    }
    
    @Test
    public void testCollisionMultipleBalls() {
        Ball ball1 = new Ball(new Vect(0, 0), new DoublePair(5));
        Ball ball2 = new Ball(new Vect(9, 0), new DoublePair(4.5, 5));
        Ball ball3 = new Ball(new Vect(0, 12), new DoublePair(5, 4.5));
        
        ball2.collision(ball1);
        ball1.collision(ball2);
        ball3.collision(ball1);
        ball1.collision(ball3);
        
        assertEquals(new Vect(9, 12), ball1.getVelocity());
        assertEquals(new Vect(0, 0), ball2.getVelocity());
        assertEquals(new Vect(0, 0), ball3.getVelocity());
        
    }

}
