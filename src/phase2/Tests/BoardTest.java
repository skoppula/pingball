package phase2.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import phase2.Board.*;
import phase2.Board.Board.InvalidInvariantException;
import phase2.Board.Flipper.BumperSide;
import phase2.Board.Gadget.Orientation;
import phase2.Messaging.Message;
import physics.Vect;
import physics.Geometry.DoublePair;

public class BoardTest {
    
    /*
     * Partitions:
     * - apply gravity to board components
     * - apply gravity to friction components
     * - validity of boards: check for
     *          overlapping gadgets, 
     *          gadgets out of bounds,
     *          gadgets initialized in the same place
     */
    
    // Set up empty list of gadgets and empty queue for use in tests
    private List<Gadget> gadgets = new ArrayList<Gadget>();
    private BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();

    /*
     * ApplyGravityandFriction: check if 'hits ceiling lightly' check different
     * delta Times
     */
    // Testing gravity and friction
    @Test
    public void testHitCeilingLightly() {
        Board board = new Board(gadgets, "board", queue);
        Ball ball = new Ball(19.5, 19.5, new Vect(0, -50), "ball");
        board.addBall(ball);
        board.updateBoard(2.2);
        assertTrue(ball.getVelocity().length() < 5);
        assertEquals(0, ball.getVelocity().x(), 0.0001);
    }

    @Test
    public void testMovingUnderGravityAndFrictionTimeIntervals() {
        Ball ball = new Ball(19.5, 19.5, new Vect(-5, -4), "ball");
        Board board = new Board(gadgets, "board", queue);
        board.addBall(ball);
        board.updateBoard(2.2);
        assertEquals(.9367, ball.getVelocity().y(), .0001);
        assertEquals(-3.5861, ball.getVelocity().x(), 0.0001);
        assertEquals(10.19782215, ball.getBallCircle().getCenter().x(), .001);
        assertEquals(19.3177, ball.getBallCircle().getCenter().y(), .001);
    }
    
    @Test
    public void testMovingUnderGravityAndFrictionTimeIntervals2() {
        Ball ball = new Ball(10.5, 9.5, new Vect(35, -2), "ball");
        Board board = new Board(gadgets, "board", queue);
        board.addBall(ball);
        board.updateBoard(.2);
        assertEquals(2.92249, ball.getVelocity().y(), .0001);
        assertEquals(29.64355, ball.getVelocity().x(), 0.0001);
        assertEquals(16.934747, ball.getBallCircle().getCenter().x(), .001);
        assertEquals(9.60628, ball.getBallCircle().getCenter().y(), .001);
    }

    // have multiple gadgets initialized in the same place
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOverlappingGadgets1() throws InvalidInvariantException {
        DoublePair squarePos = new DoublePair(1);
        DoublePair circlePos = new DoublePair(1);
        List<Gadget> empty = new ArrayList<Gadget>();
        SquareBumper square = new SquareBumper(1, 1, "square");
        CircleBumper circle = new CircleBumper(1, 1, "circle");
        gadgets.add(square);
        gadgets.add(circle);
        Board board = new Board(gadgets, "board", queue);
        // board's constructor should assert something false!
    }
    
    // have a flipper run into another gadget
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOverlappingGadgets2() throws InvalidInvariantException {
        Flipper flipper = new Flipper(1, 1, "flipper", BumperSide.LEFT, Orientation.ZERO);
        CircleBumper circle = new CircleBumper(2, 1, "circle");
        gadgets.add(flipper);
        gadgets.add(circle);
        Board board = new Board(gadgets, "board", queue);
        // board's constructor should assert something false!
    }
    
    // have another gadget be initialized in an absorber
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOverlappingGadgets3() throws InvalidInvariantException {
        DoublePair flipperPos = new DoublePair(17);
        DoublePair absorberPos = new DoublePair(0, 15);
        Flipper flipper = new Flipper(17, 17, "flipper", BumperSide.LEFT, Orientation.ZERO);
        Absorber absorber = new Absorber(0, 15, "absorber", 18, 3); 
        gadgets.add(flipper);
        gadgets.add(absorber);
        System.out.println(gadgets);
        Board board = new Board(gadgets, "board", queue);
        // board's constructor should assert something false!
    }
    
    // test for a gadget declared out of bounds
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOutOfBoundsGadgets1() throws InvalidInvariantException {
        DoublePair trianglePos = new DoublePair(20, 1);
        List<Gadget> empty = new ArrayList<Gadget>();
        TriangleBumper triangle = new TriangleBumper(20, 1, "triangle", Orientation.NINETY);
        gadgets.add(triangle);
        Board board = new Board(gadgets, "board", queue);
        // board's constructor should assert something false!
    }
    
    // test for an absorber that goes out of bounds
    @Test(expected=AssertionError.class)
    public void testInvalidBoardOutOfBoundsGadgets2() throws InvalidInvariantException {
        Absorber absorber = new Absorber(0, 15, "absorber", 18, 3);
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.add(absorber);
        Board board = new Board(gadgets, "board", queue);
        // board's constructor should assert something false!
    }
}
