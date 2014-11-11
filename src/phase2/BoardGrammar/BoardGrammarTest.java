package phase2.BoardGrammar;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import phase2.Pingball;
import phase2.Board.Ball;
import phase2.Board.Board;
import phase2.Board.CircleBumper;
import phase2.Board.Flipper;
import phase2.Board.Flipper.BumperSide;
import phase2.Board.Gadget;
import phase2.Board.SquareBumper;
import phase2.Board.Gadget.Orientation;
import phase2.Board.TriangleBumper;
import phase2.Board.Util.InvalidInvariantException;
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
     */

    // make a board with friction1, friction2, and gravity defined
    @Test
    public void makeBoardTest1() {
        Board board1 = Pingball.parseBoardFile(new File("src/phase2/BoardGrammar/boardFiles/simplestBoard.pb"));
        List<Gadget> gadgets1 = new ArrayList<Gadget>();
        Board correctBoard = new Board(gadgets1, "sampleBoard1", 20.0, 0.020, 0.020);
        assertTrue(correctBoard.hasEqualAttributes(board1));
    }
    
    // make a board with a ball defined
    @Test
    public void makeBoardTest2() {
        Board board2 = Pingball.parseBoardFile(new File("src/phase2/BoardGrammar/boardFiles/ball.pb"));
        List<Gadget> gadgets2 = new ArrayList<Gadget>();
        Board correctBoard = new Board(gadgets2, "sampleBoard1", 20.0, 0.020, 0.020);
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(board2));
    }
    
    // make a board with square bumpers
    @Test
    public void makeBoardTest3() throws InvalidInvariantException {
        Board board3 = Pingball.parseBoardFile(new File("src/phase2/BoardGrammar/boardFiles/squareBumper.pb"));
        
        // add bumpers to correct board
        List<Gadget> gadgets3 = new ArrayList<Gadget>();
        gadgets3.add(new SquareBumper(0, 2, "Square0"));
        gadgets3.add(new SquareBumper(1, 2, "Square1"));
        gadgets3.add(new SquareBumper(2, 2, "Square2"));
        
        Board correctBoard = new Board(gadgets3, "sampleBoard1", 20.0, 0.020, 0.020);
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(board3));
    }
    
    // make a board with triangle bumpers
    @Test
    public void makeBoardTest4() throws InvalidInvariantException {
        Board boardtemp = Pingball.parseBoardFile(new File("src/phase2/BoardGrammar/boardFiles/bumpers.bp"));
        
        // add bumpers to correct board
        List<Gadget> gadgets = new ArrayList<Gadget>();
        gadgets.add(new SquareBumper(0, 2, "Square0"));
        gadgets.add(new SquareBumper(1, 2, "Square1"));
        gadgets.add(new CircleBumper(4, 3, "Circle4"));
        gadgets.add(new CircleBumper(5, 4, "Circle5"));
        gadgets.add(new TriangleBumper(8, 9, "Tri1", Orientation.TWO_HUNDRED_SEVENTY));
        gadgets.add(new TriangleBumper(11, 9, "Tri2", Orientation.ONE_HUNDRED_EIGHTY));
        
        Board correctBoard = new Board(gadgets, "sampleBoard1", 20.0, 0.020, 0.020);
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(boardtemp));
    }
    
    // make a board with bumpers and flippers
    @Test
    public void makeBoardTest5() throws InvalidInvariantException {
        Board board = Pingball.parseBoardFile(new File("src/phase2/BoardGrammar/boardFiles/flippers.pb"));
        
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
        
        Board correctBoard = new Board(gadgets, "sampleBoard1", 20.0, 0.020, 0.020);
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(board));
    }
    

    
}
