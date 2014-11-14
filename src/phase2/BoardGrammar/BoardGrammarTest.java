package phase2.BoardGrammar;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.collections.WeakListChangeListener;

import org.junit.Test;

import phase2.Pingball;
import phase2.Board.Absorber;
import phase2.Board.Ball;
import phase2.Board.Board;
import phase2.Board.CircleBumper;
import phase2.Board.Flipper;
import phase2.Board.Wall;
import phase2.Board.Flipper.BumperSide;
import phase2.Board.Gadget;
import phase2.Board.SquareBumper;
import phase2.Board.Gadget.Orientation;
import phase2.Board.TriangleBumper;
import phase2.Board.Util.InvalidInvariantException;
import phase2.Messaging.Message;
import physics.Vect;

public class BoardGrammarTest {
    /*
     * Testing strategy
     *      - creates a board with the correct friction & gravity given any number of those parameters
     *      - with/without ball
     *      - with/without gadgets
     *          *square bumpers
     *          *circle bumpers
     *          *triangle bumpers
     *          *flippers
     *          *absorbers
     *      - comments: start file, in middle of file, end file
     */

    LinkedBlockingQueue<Message> q = new LinkedBlockingQueue<>(); // just for initializing Board, not actually used
    
    /*// equals test - testing gadget equality
    @Test
    public void equalsTest1() throws InvalidInvariantException {
        List<Gadget> gadgets1 = new ArrayList<Gadget>();
        gadgets1.add(new CircleBumper(1, 1, "circle"));
        
        List<Gadget> gadgets2 = new ArrayList<Gadget>();
        gadgets2.add(new CircleBumper(1, 2, "circle"));
        
        Board board1 = new Board(gadgets1, "board", new LinkedBlockingQueue<>());
        Board board2 = new Board(gadgets2, "board", new LinkedBlockingQueue<>());
        
        assertFalse(board1.hasEqualAttributes(board2));
    }
    
    // equals test - testing gadget equality when one gadgets list is a
    // subset of the other
    @Test
    public void equalsTest2() throws InvalidInvariantException {
        List<Gadget> gadgets1 = new ArrayList<Gadget>();
        gadgets1.add(new CircleBumper(1, 1, "circle"));
        
        List<Gadget> gadgets2 = new ArrayList<Gadget>();
        gadgets2.add(new CircleBumper(1, 1, "circle"));
        gadgets2.add(new CircleBumper(1, 2, "circle1"));
        
        Board board1 = new Board(gadgets1, "board", new LinkedBlockingQueue<>());
        Board board2 = new Board(gadgets2, "board", new LinkedBlockingQueue<>());
        
        assertFalse(board1.hasEqualAttributes(board2));
    }
    
    // make a board with friction1, friction2, and gravity defined
    @Test
    public void makeBoardTest1() throws IOException {
        Board board1 = new Board(new File("src/phase2/BoardGrammar/boardFiles/simplestBoard.pb"), q);
        List<Gadget> gadgets1 = new ArrayList<Gadget>();
        Board correctBoard = new Board(gadgets1, "sampleBoard1", 20.0, 0.020, 0.020, new LinkedBlockingQueue<>());
        assertTrue(correctBoard.hasEqualAttributes(board1));
    }
    
    // make a board with a ball defined
    @Test
    public void makeBoardTest2() throws IOException {
        Board board2 = new Board(new File("src/phase2/BoardGrammar/boardFiles/ball.pb"), q);
        List<Gadget> gadgets2 = new ArrayList<Gadget>();
        Board correctBoard = new Board(gadgets2, "sampleBoard1", 20.0, 0.020, 0.020, new LinkedBlockingQueue<>());
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(board2));
    }
    
    // make a board with square bumpers
    @Test
    public void makeBoardTest3() throws InvalidInvariantException, IOException {
        Board board3 = new Board(new File("src/phase2/BoardGrammar/boardFiles/squareBumper.pb"), q);
        
        // add bumpers to correct board
        List<Gadget> gadgets3 = new ArrayList<Gadget>();
        gadgets3.add(new SquareBumper(0, 2, "Square0"));
        gadgets3.add(new SquareBumper(1, 2, "Square1"));
        gadgets3.add(new SquareBumper(2, 2, "Square2"));
        
        Board correctBoard = new Board(gadgets3, "sampleBoard1", 20.0, 0.020, 0.020, new LinkedBlockingQueue<>());
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(board3));
    }
    
    // make a board with triangle bumpers
    @Test
    public void makeBoardTest4() throws InvalidInvariantException, IOException {
        Board boardtemp = new Board(new File("src/phase2/BoardGrammar/boardFiles/bumpers.bp"), q);
        
        // add bumpers to correct board
        List<Gadget> gadgets = new ArrayList<Gadget>();
        gadgets.add(new SquareBumper(0, 2, "Square0"));
        gadgets.add(new SquareBumper(1, 2, "Square1"));
        gadgets.add(new CircleBumper(4, 3, "Circle4"));
        gadgets.add(new CircleBumper(5, 4, "Circle5"));
        gadgets.add(new TriangleBumper(8, 9, "Tri1", Orientation.TWO_HUNDRED_SEVENTY));
        gadgets.add(new TriangleBumper(11, 9, "Tri2", Orientation.ONE_HUNDRED_EIGHTY));
        
        Board correctBoard = new Board(gadgets, "sampleBoard1", 20.0, 0.020, 0.020, new LinkedBlockingQueue<>());
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(boardtemp));
    }
    
    // make a board with bumpers and flippers
    @Test
    public void makeBoardTest5() throws InvalidInvariantException, IOException {
        Board board = new Board(new File("src/phase2/BoardGrammar/boardFiles/flipperTest.pb"), q);
        
        // add bumpers to correct board
        List<Gadget> gadgets = new ArrayList<Gadget>();
        gadgets.add(new SquareBumper(0, 2, "Square0"));
        gadgets.add(new SquareBumper(1, 2, "Square1"));
        gadgets.add(new CircleBumper(4, 3, "Circle4"));
        gadgets.add(new CircleBumper(5, 4, "Circle5"));
        gadgets.add(new TriangleBumper(8, 9, "Tri1", Orientation.TWO_HUNDRED_SEVENTY));
        gadgets.add(new TriangleBumper(11, 9, "Tri2", Orientation.ONE_HUNDRED_EIGHTY));
        gadgets.add(new Flipper(8, 2, "FlipL1", BumperSide.LEFT, Orientation.ZERO));
        gadgets.add(new Flipper(10, 2, "FlipR1", BumperSide.RIGHT, Orientation.ZERO));
        gadgets.add(new Flipper(8, 7, "FlipL2", BumperSide.LEFT, Orientation.ZERO));
        gadgets.add(new Flipper(10, 7, "FlipR2", BumperSide.RIGHT, Orientation.ZERO));
        
        Board correctBoard = new Board(gadgets, "sampleBoard1", 20.0, 0.020, 0.020, new LinkedBlockingQueue<>());
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(board));
    }
    
    // make a board with bumpers, flippers, and absorbers.
    // tests not having all board constants specified
    @Test
    public void makeBoardTest6() throws InvalidInvariantException, IOException {
        Board board = new Board(new File("src/phase2/BoardGrammar/boardFiles/absorberTest.pb"), q);
        
        // add bumpers to correct board
        List<Gadget> gadgets = new ArrayList<Gadget>();
        gadgets.add(new CircleBumper(1, 10, "CircleA"));
        gadgets.add(new CircleBumper(2, 10, "CircleB"));
        gadgets.add(new TriangleBumper(19, 0, "Tri", Orientation.ONE_HUNDRED_EIGHTY));
        gadgets.add(new Absorber(0, 18, "Abs", 20, 2));
        
        Board correctBoard = new Board(gadgets, "Absorber", 25.0, 0.025, 0.025, new LinkedBlockingQueue<>());
        Vect ballVelocity = new Vect(0.1, 0.1);
        Ball ballB = new Ball(19.25, 3.25, ballVelocity, "BallB");
        Ball ballC = new Ball(1.25, 5.25, ballVelocity, "BallC");
        correctBoard.addBall(ballB);
        correctBoard.addBall(ballC);
        assertTrue(correctBoard.hasEqualAttributes(board));
    }
    */
    /*// tests trigger functionality
    @Test
    public void makeBoardTest7() throws InvalidInvariantException, IOException {
        Board board = new Board(new File("src/phase2/BoardGrammar/boardFiles/simpeTrigger.pb"), q);
        
        // add bumpers to correct board
        List<Gadget> gadgets = new ArrayList<Gadget>();
        Flipper flipL = new Flipper(10, 7, "FlipL", BumperSide.LEFT, Orientation.ZERO);
        SquareBumper square = new SquareBumper(0, 2, "Square");
        Absorber abs = new Absorber(0, 19, "Abs", 20, 1);
        gadgets.add(square);
        gadgets.add(new CircleBumper(4, 3, "Circle"));
        gadgets.add(new TriangleBumper(1, 1, "Tri", Orientation.TWO_HUNDRED_SEVENTY));
        gadgets.add(abs);
        gadgets.add(flipL);
        gadgets.add(new Flipper(12, 7, "FlipR", BumperSide.RIGHT, Orientation.ZERO));
        
        // set up triggers
        ArrayList<Gadget> squareTriggers = new ArrayList<Gadget>();
        squareTriggers.add(flipL);
        square.setGadgetsToTrigger(squareTriggers);
        ArrayList<Gadget> absorberTriggers = new ArrayList<Gadget>();
        absorberTriggers.add(abs);
        abs.setGadgetsToTrigger(absorberTriggers);

        Board correctBoard = new Board(gadgets, "Example", 25.0, 0.025, 0.025, new LinkedBlockingQueue<>());
        Vect ballVelocity = new Vect(-3.4, -2.3);
        Ball ball = new Ball(1.8, 4.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);

        assertTrue(correctBoard.hasEqualAttributes(board));
    }*/
    /*
    // tests sample board 4
    @Test
    public void sampleBoard4Test() throws InvalidInvariantException, IOException {
        Board board = new Board(new File("src/phase2/BoardGrammar/boardFiles/sampleBoard4.pb"), q);
      
    }
    
    // tests default board
    @Test
    public void defaultBoardTest() throws InvalidInvariantException, IOException {
        Board board = new Board(new File("src/phase2/BoardGrammar/boardFiles/default.pb"), q);
      
    }*/
    
    // tests triggering 
    @Test
    public void flippersBoardTest() throws InvalidInvariantException, IOException {
        Board board = new Board(new File("src/phase2/BoardGrammar/boardFiles/flippers.pb"), q);

        List<Gadget> gadgets = new ArrayList<Gadget>();
        gadgets.add(new Flipper(0,8,"FlipA",BumperSide.LEFT,Orientation.NINETY));
        gadgets.add(new Flipper(4,10,"FlipB",BumperSide.LEFT,Orientation.NINETY));
        gadgets.add(new Flipper(9,8,"FlipC",BumperSide.LEFT,Orientation.NINETY));
        gadgets.add(new Flipper(15,8,"FlipD",BumperSide.LEFT,Orientation.NINETY));
        
        gadgets.add(new Flipper(2,15,"FlipE",BumperSide.RIGHT,Orientation.ZERO));
        gadgets.add(new Flipper(17,15,"FlipF",BumperSide.RIGHT,Orientation.ZERO));
        
        gadgets.add(new CircleBumper(5, 18, "CircleA"));
        gadgets.add(new CircleBumper(7, 13, "CircleB"));
        gadgets.add(new CircleBumper(0, 5, "CircleC"));
        gadgets.add(new CircleBumper(5, 5, "CircleD"));
        gadgets.add(new CircleBumper(10, 5, "CircleE"));
        gadgets.add(new CircleBumper(15, 5, "CircleF"));
        
        gadgets.add(new TriangleBumper(19, 0, "TriA", Orientation.NINETY));
        gadgets.add(new TriangleBumper(10, 18, "TriB", Orientation.ONE_HUNDRED_EIGHTY));
        
        gadgets.add(new Absorber(0, 19, "Abs", 20, 1));
        
        Board correctBoard = new Board(gadgets, "Flippers", q);
        
        assertTrue(correctBoard.getGadgets().containsAll(board.getGadgets()));
        assertTrue(correctBoard.getGadgets().size()==board.getGadgets().size());
    }
    
}
