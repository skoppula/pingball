package Pingball;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Pingball.Gadget.Orientation;
import physics.Vect;
import physics.Geometry.DoublePair;

public class BoardTest {
    
    /*
     * Partitions:
     * - gravity working
     * - friction working
     * - valid boards: overlapping gadgets, gadgets out of bounds
     *          gadgets initialized in the same place
     */

    /*
     * ApplyGravityandFriction: check if 'hits ceiling lightly' check different
     * delta Times
     */

    // Testing gravity and friction
    @Test
    public void testHitCeilingLightly() {
        Ball ball = new Ball(new Vect(0, -50), new DoublePair(19.5, 19.5));

        Board board = new Board(new ArrayList<Gadget>());

        board.addBall(ball);
        board.updateBoard(2.2);

        assertTrue(ball.getVelocity().length() < 5);
        assertEquals(0, ball.getVelocity().x(), 0.0001);

    }

    @Test
    public void testMovingUnderGravityAndFrictionTimeIntervals() {
        Ball ball = new Ball(new Vect(-5, -4), new DoublePair(19.5, 19.5));

        Board board = new Board(new ArrayList<Gadget>());

        board.addBall(ball);
        board.updateBoard(2.2);

        assertEquals(.9367, ball.getVelocity().y(), .0001);
        assertEquals(-3.5861, ball.getVelocity().x(), 0.0001);
       
        assertEquals(10.19782215, ball.getPosition().d1, .001);
        assertEquals(19.3177, ball.getPosition().d2, .001);
        
    }
    
    @Test
    public void testMovingUnderGravityAndFrictionTimeIntervals2() {
        Ball ball = new Ball(new Vect(35, -2), new DoublePair(10.5, 9.5));

        Board board = new Board(new ArrayList<Gadget>());

        board.addBall(ball);
        board.updateBoard(.2);

        assertEquals(2.92249, ball.getVelocity().y(), .0001);
        assertEquals(29.64355, ball.getVelocity().x(), 0.0001);
       
        assertEquals(16.934747, ball.getPosition().d1, .001);
        assertEquals(9.60628, ball.getPosition().d2, .001);
        
    }

    // have multiple gadgets initialized in the same place
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOverlappingGadgets1() {
        DoublePair squarePos = new DoublePair(1);
        DoublePair circlePos = new DoublePair(1);
        List<Gadget> empty = new ArrayList<Gadget>();
        SquareBumper square = new SquareBumper(squarePos, empty, false);
        CircleBumper circle = new CircleBumper(circlePos, empty, false);
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.add(square);
        gadgetList.add(circle);
        Board board = new Board(gadgetList);
        // board's constructor should assert something false!
    }
    
    // have a flipper run into another gadget
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOverlappingGadgets2() {
        DoublePair flipperPos = new DoublePair(1);
        DoublePair circlePos = new DoublePair(2, 1);
        List<Gadget> empty = new ArrayList<Gadget>();
        LeftFlipper flipper = new LeftFlipper(flipperPos, Orientation.ZERO, empty, false);
        CircleBumper circle = new CircleBumper(circlePos, empty, false);
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.add(flipper);
        gadgetList.add(circle);
        Board board = new Board(gadgetList);
        // board's constructor should assert something false!
    }
    
    // have another gadget be initialized in an absorber
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOverlappingGadgets3() {
        DoublePair flipperPos = new DoublePair(17);
        DoublePair absorberPos = new DoublePair(0, 15);
        List<Gadget> empty = new ArrayList<Gadget>();
        LeftFlipper flipper = new LeftFlipper(flipperPos, Orientation.ZERO, empty, false);
        Absorber absorber = new Absorber(absorberPos, 18, 3, empty, false);
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.add(flipper);
        gadgetList.add(absorber);
        Board board = new Board(gadgetList);
        // board's constructor should assert something false!
    }
    
    // test for a gadget declared out of bounds
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOutOfBoundsGadgets1() {
        DoublePair trianglePos = new DoublePair(20, 1);
        List<Gadget> empty = new ArrayList<Gadget>();
        TriangleBumper triangle = new TriangleBumper(trianglePos, Orientation.NINETY, empty, false);
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.add(triangle);
        Board board = new Board(gadgetList);
        // board's constructor should assert something false!
    }
    
    // test for an absorber that goes out of bounds
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOutOfBoundsGadgets2() {
        DoublePair absorberPos = new DoublePair(0, 15);
        List<Gadget> empty = new ArrayList<Gadget>();
        Absorber absorber = new Absorber(absorberPos, 18, 3, empty, false);
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.add(absorber);
        Board board = new Board(gadgetList);
        // board's constructor should assert something false!
    }
}
