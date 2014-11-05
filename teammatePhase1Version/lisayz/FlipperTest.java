package Pingball;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import Pingball.Gadget.Orientation;
import physics.Angle;
import physics.Geometry;
import physics.Vect;
import physics.Geometry.DoublePair;

public class FlipperTest {

    /*
     * Test partitions:
     *      - LeftFlipper: each of 4 orientations
     *      - RightFlipper: each of 4 orientations
     *      - triggered while: in default position, swinging from default -> other, swinging from other -> default, in fully swung position
     *      - Swinging for long enough to potentially go out of bounds (it shouldn't)
     *      - check that it does trigger itself & that it can trigger other gadgets
     *      - action called when in default position, swinging from default -> other, swinging from other -> default, in fully swung position
     *      - collision with ball: hit COR, hit middle of flipper, hit end of flipper
     */
    private DoublePair position = new DoublePair(5,5);
    
    // initialize Flippers for testing, one LeftFlipper and one RightFlipper for each orientation
    private LeftFlipper leftFlipper0 = new LeftFlipper(position, Orientation.ZERO, new ArrayList<Gadget>(), false);
    private LeftFlipper leftFlipper90 = new LeftFlipper(position, Orientation.NINETY, new ArrayList<Gadget>(), false);
    private LeftFlipper leftFlipper180 = new LeftFlipper(position, Orientation.ONE_HUNDRED_EIGHTY, new ArrayList<Gadget>(), false);
    private LeftFlipper leftFlipper270 = new LeftFlipper(position, Orientation.TWO_HUNDRED_SEVENTY, new ArrayList<Gadget>(), false);
    private RightFlipper rightFlipper0 = new RightFlipper(position, Orientation.ZERO, new ArrayList<Gadget>(), false);
    private RightFlipper rightFlipper90 = new RightFlipper(position, Orientation.NINETY, new ArrayList<Gadget>(), false);
    private RightFlipper rightFlipper180 = new RightFlipper(position, Orientation.ONE_HUNDRED_EIGHTY, new ArrayList<Gadget>(), false);
    private RightFlipper rightFlipper270 = new RightFlipper(position, Orientation.TWO_HUNDRED_SEVENTY, new ArrayList<Gadget>(), false);
        
    // time constants to help with testing
    private final static double SECONDS_TO_MID_SWING = 0.041666666666667;
    private final static double SECONDS_TO_FULL_SWING = 0.08333333333333;
    private final static Angle NINETY_DEGREES = new Angle(Math.PI/2);
    private final static Angle ONE_HUNDRED_EIGHTY_DEGREES = new Angle(Math.PI);
    private final static Angle TWO_HUNDRED_SEVENTY_DEGREES = new Angle(Math.PI*3/2);
    private final static double LENIENCY = 0.0001;
    
    // Left Flipper in Default position has its action called for SECONDS_TO_FULL_SWING
    @Test
    public void testleftFlipper0FullSwing() {
        assertEquals(0, leftFlipper0.currentAngle.radians(), LENIENCY);
        leftFlipper0.action();
        leftFlipper0.updateGadgetPosition(SECONDS_TO_FULL_SWING);
        assertEquals(-Math.PI/2, leftFlipper0.currentAngle.radians(), LENIENCY);
        assertTrue(leftFlipper0.inFullySwungPosition());
    }
    
    // Left Flipper in Default position has its action called for SECONDS_TO_MID_SWING
    @Test
    public void testleftFlipper0HalfSwing() {
        assertEquals(0, leftFlipper0.currentAngle.radians(), LENIENCY);
        leftFlipper0.action();
        leftFlipper0.updateGadgetPosition(SECONDS_TO_MID_SWING);
        assertEquals(-Math.PI/4, leftFlipper0.currentAngle.radians(), LENIENCY);
        assertFalse(leftFlipper0.inDefaultPosition());
    }
    
    // Left Flipper in Default position has its action called for SECONDS_TO_MID_SWING,
    // then action called again for SECONDS_TO_MID_SWING
    // the Flipper should return to angle being zero
    @Test
    public void testleftFlipper0HalfSwingHalfSwing() {
        assertEquals(0, leftFlipper0.currentAngle.radians(), LENIENCY);
        leftFlipper0.action();
        leftFlipper0.updateGadgetPosition(SECONDS_TO_MID_SWING);
        leftFlipper0.action();
        leftFlipper0.updateGadgetPosition(SECONDS_TO_MID_SWING);
        assertEquals(0, leftFlipper0.currentAngle.radians(), LENIENCY);
        assertTrue(leftFlipper0.inDefaultPosition());
    }
    
    // Left Flipper in Default position has its action called and is allowed to move
    // for 2*SECONDS_TO_FULL_SWING. Current angle should not go beyond 90˚
    @Test
    public void testleftFlipper0TwoFullSwings() {
        assertEquals(0, leftFlipper0.currentAngle.radians(), LENIENCY);
        leftFlipper0.action();
        leftFlipper0.updateGadgetPosition(SECONDS_TO_FULL_SWING);
        leftFlipper0.updateGadgetPosition(SECONDS_TO_FULL_SWING);
        assertEquals(-Math.PI/2, leftFlipper0.currentAngle.radians(), LENIENCY);
        assertTrue(leftFlipper0.inFullySwungPosition());
    }
    
    // Left Flipper with 90˚ orientation has its action called for SECONDS_TO_FULL_SWING
    @Test
    public void testleftFlipper90FullSwing() {
        assertEquals(0, leftFlipper90.currentAngle.radians(), LENIENCY);
        leftFlipper90.action();
        leftFlipper90.updateGadgetPosition(SECONDS_TO_FULL_SWING);
        assertEquals(-Math.PI/2, leftFlipper90.currentAngle.radians(), LENIENCY);
        assertTrue(leftFlipper90.inFullySwungPosition());
    }
    
    // Left Flipper in 90˚ orientation has its action called for SECONDS_TO_MID_SWING
    @Test
    public void testleftFlipper90HalfSwing() {
        assertEquals(0, leftFlipper90.currentAngle.radians(), LENIENCY);
        leftFlipper90.action();
        leftFlipper90.updateGadgetPosition(SECONDS_TO_MID_SWING);
        assertEquals(-Math.PI/4, leftFlipper90.currentAngle.radians(), LENIENCY);
        assertFalse(leftFlipper90.inDefaultPosition());
    }
    
    // check that Left Flippers are located correctly in different orientations
    @Test
    public void testLeftFlipperOrientations() {
        Vect flipperCenter = new Vect(position.d1+1, position.d2+1);
        Vect leftFlipper90CorrectCOR = Geometry.rotateAround(leftFlipper0.getCenterOfRotation(), flipperCenter, NINETY_DEGREES);
        Vect leftFlipper180CorrectCOR = Geometry.rotateAround(leftFlipper0.getCenterOfRotation(), flipperCenter, ONE_HUNDRED_EIGHTY_DEGREES);
        Vect leftFlipper270CorrectCOR = Geometry.rotateAround(leftFlipper0.getCenterOfRotation(), flipperCenter, TWO_HUNDRED_SEVENTY_DEGREES);
        Vect positionVect = new Vect(position.d1, position.d2);
        assertTrue(leftFlipper0.getCenterOfRotation().equals(positionVect));
        assertTrue(leftFlipper90.getCenterOfRotation().equals(leftFlipper90CorrectCOR));
        assertTrue(leftFlipper180.getCenterOfRotation().equals(leftFlipper180CorrectCOR));
        assertTrue(leftFlipper270.getCenterOfRotation().equals(leftFlipper270CorrectCOR));
    }
    
    // check that Right Flippers are located correctly in different orientations
    @Test
    public void testRightFlipperOrientations() {
        Vect flipperCenter = new Vect(position.d1+1, position.d2+1);
        Vect rightFlipper90CorrectCOR = Geometry.rotateAround(rightFlipper0.getCenterOfRotation(), flipperCenter, NINETY_DEGREES);
        Vect rightFlipper180CorrectCOR = Geometry.rotateAround(rightFlipper0.getCenterOfRotation(), flipperCenter, ONE_HUNDRED_EIGHTY_DEGREES);
        Vect rightFlipper270CorrectCOR = Geometry.rotateAround(rightFlipper0.getCenterOfRotation(), flipperCenter, TWO_HUNDRED_SEVENTY_DEGREES);
        Vect rightFlipperCORDefault = new Vect(position.d1+2, position.d2);
        assertTrue(rightFlipper0.getCenterOfRotation().equals(rightFlipperCORDefault));
        assertTrue(rightFlipper90.getCenterOfRotation().equals(rightFlipper90CorrectCOR));
        assertTrue(rightFlipper180.getCenterOfRotation().equals(rightFlipper180CorrectCOR));
        assertTrue(rightFlipper270.getCenterOfRotation().equals(rightFlipper270CorrectCOR));
    }
    
    // check that ball collides with COR Circle when it falls on the COR
    // and imparts the correct velocity to the ball
    @Test
    public void testFlipperCORCollision() {
        Ball ball = new Ball(new Vect(0,1), new DoublePair(7,4.75));
        assertEquals(rightFlipper0.getTimeUntilCollision(ball), 0.0, LENIENCY);
        rightFlipper0.collision(ball);
        assertEquals(new Vect(0,-0.95), ball.getVelocity());
    }
    
    // check that ball collides with End Flipper Circle when it falls on the COR
    // and imparts the correct velocity to the ball
    @Test
    public void testFlipperEndOfFlipperCollision() {
        Ball ball = new Ball(new Vect(0,1), new DoublePair(7,4.75));
        assertEquals(leftFlipper180.getTimeUntilCollision(ball), 0.0, LENIENCY);
        leftFlipper180.collision(ball);
        assertEquals(new Vect(0,-0.95), ball.getVelocity());
    }
    
    // check that ball collides with Line Segment when it falls on the COR
    // and imparts the correct velocity to the ball
    @Test
    public void testFlipperLineSegmentCollision() {
        Ball ball = new Ball(new Vect(0,1), new DoublePair(6,4.75));
        assertEquals(leftFlipper90.getTimeUntilCollision(ball), 0.0, LENIENCY);
        leftFlipper90.collision(ball);
        assertEquals(new Vect(0,-0.95), ball.getVelocity());
    }
    
    
    
}
