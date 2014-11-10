package phase2.BoardGrammar;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import phase2.Pingball;
import phase2.Board.Ball;
import phase2.Board.Board;
import phase2.Board.Gadget;
import phase2.Board.SquareBumper;
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
        Board board = Pingball.parseBoardFile(new File("src/phase2/BoardGrammar/boardFiles/simplestBoard.pb"));
        List<Gadget> gadgets = new ArrayList<Gadget>();
        Board correctBoard = new Board(gadgets, "sampleBoard1", 20.0, 0.020, 0.020);
        assertTrue(correctBoard.hasEqualAttributes(board));
    }
    
    // make a board with a ball defined
    @Test
    public void makeBoardTest2() {
        Board board = Pingball.parseBoardFile(new File("src/phase2/BoardGrammar/boardFiles/ball.pb"));
        List<Gadget> gadgets = new ArrayList<Gadget>();
        Board correctBoard = new Board(gadgets, "sampleBoard1", 20.0, 0.020, 0.020);
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(board));
    }
    
    // make a board with square bumpers
    @Test
    public void makeBoardTest3() throws InvalidInvariantException {
        Board board = Pingball.parseBoardFile(new File("src/phase2/BoardGrammar/boardFiles/squareBumper.pb"));
        
        // add bumpers to correct board
        List<Gadget> gadgets = new ArrayList<Gadget>();
        gadgets.add(new SquareBumper(0, 2, "Square0"));
        gadgets.add(new SquareBumper(1, 2, "Square1"));
        gadgets.add(new SquareBumper(2, 2, "Square2"));
        
        Board correctBoard = new Board(gadgets, "sampleBoard1", 20.0, 0.020, 0.020);
        Vect ballVelocity = new Vect(2.5, 2.5);
        Ball ball = new Ball(0.5, 0.5, ballVelocity, "Ball");
        correctBoard.addBall(ball);
        assertTrue(correctBoard.hasEqualAttributes(board));
    }
    
}
