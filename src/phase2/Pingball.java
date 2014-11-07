package phase2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import phase2.Flipper.BumperSide;
import phase2.Gadget.Orientation;
import phase2.Util.InvalidInvariantException;
//import Pingball.Gadget.Orientation;
import physics.Vect;
import physics.Geometry.DoublePair;
//import Pingball.Ball;
//import Pingball.Board;

public class Pingball {
    
    private static final long TIME_DELTA_MILLISECONDS = 1000/60;

    /**
     * Updates the board every timeDelta Prints out the board every timeDelta
     * 
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        double timeInSeconds = TIME_DELTA_MILLISECONDS * .001;
        Board board;

        if (args.length == 0) {
            board = defaultBoard();
        }
        else {
            String boardName = args[0];
            if (boardName.equals("absorber")) {
                board = absorberBoard();
            }
            // boardName: "flippers"
            else {
                board = flippersBoard();
            }
        }                

        while(true) {
            Thread.sleep(TIME_DELTA_MILLISECONDS);
            board.updateBoard(timeInSeconds);
            board.printBoard();
        }
    }
    
    
 
    
    /**
     * @return benchmark board "default"
     */
    public static Board defaultBoard() {
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        try{
	        // make  gadgets
	        CircleBumper circle1 = new CircleBumper(1, 10, "circle1");
	        TriangleBumper triangle = new TriangleBumper(12, 15, "triangle", Orientation.ONE_HUNDRED_EIGHTY);
	        SquareBumper square1 = new SquareBumper(0, 17, "square1");
	        SquareBumper square2 = new SquareBumper(1, 17, "square2");
	        SquareBumper square3 = new SquareBumper(2, 17, "square3");
	        CircleBumper circle2 = new CircleBumper(7, 18, "circle2");
	        CircleBumper circle3 = new CircleBumper(8, 18, "circle3");
	        CircleBumper circle4 = new CircleBumper(9, 18, "circle4");
	        
	        gadgetList.addAll(Arrays.asList(circle1, triangle, square1, square2, square3, circle2, circle3, circle4));

        } catch(InvalidInvariantException e){
    		e.printStackTrace();
        }
        Board board = new Board(gadgetList);
        Ball ball = new Ball(1.25, 1.25, Vect.ZERO);
        board.addBall(ball);
        return board;
  
    }
    
    /**
     * @return benchmark board "absorber"
     */
    public static Board absorberBoard() {
        
        // make gadgets
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        
        try{
	        Absorber absorber = new Absorber(0, 18, "absorber", 20, 2);
	        List<Gadget> triggersAbsorber = new ArrayList<Gadget>();
	        triggersAbsorber.add(absorber);
	        TriangleBumper triangle = new TriangleBumper(19, 0, "triangle", Orientation.NINETY);
	        CircleBumper circle1 = new CircleBumper(1, 10, "circle1"); // triggers absorber
	        CircleBumper circle2 = new CircleBumper(2, 10, "circle2"); // triggers absorber
	        CircleBumper circle3 = new CircleBumper(3, 10, "circle3"); // triggers absorber
	        CircleBumper circle4 = new CircleBumper(4, 10, "circle4"); // triggers absorber
	        CircleBumper circle5 = new CircleBumper(5, 10, "circle5"); // triggers absorber
	        
	        //set up the triggers
	        circle1.setTriggers(triggersAbsorber);
	        circle2.setTriggers(triggersAbsorber);
	        circle3.setTriggers(triggersAbsorber);
	        circle4.setTriggers(triggersAbsorber);
	        circle5.setTriggers(triggersAbsorber);
	        
	       
	        gadgetList.addAll(Arrays.asList(absorber, triangle, circle1, circle2, circle3, circle4, circle5));
        } catch(InvalidInvariantException e){
    		System.out.println("One of the invariants in these objects was broken!");
    		e.printStackTrace();
        }
        Board board = new Board(gadgetList);
        
        // make balls
        Ball ball1 = new Ball(10.25, 15.25, Vect.ZERO);
        Ball ball2 = new Ball(19.25, 3.25, Vect.ZERO);
        Ball ball3 = new Ball(1.25, 5.25, Vect.ZERO);
        board.addBall(ball1);
        board.addBall(ball2);
        board.addBall(ball3);
        return board;
  
    }
    
    /**
     * @return benchmark board "flippers"
     */
    public static Board flippersBoard() {
    	
    	List<Gadget> gadgetList = new ArrayList<Gadget>();
        
        // make gadgets
    	try{
	        CircleBumper circle1 = new CircleBumper(5, 18, "circle1"); 
	        CircleBumper circle2 = new CircleBumper(7, 13, "circle2"); 
	        Flipper leftFlipper1 = new Flipper(0 , 8, "leftFlipper1", BumperSide.LEFT, Orientation.NINETY);
	        Flipper leftFlipper2 = new Flipper(4, 10, "leftFlipper2", BumperSide.LEFT, Orientation.NINETY);
	        Flipper leftFlipper3 = new Flipper(9, 8, "leftFlipper3", BumperSide.LEFT, Orientation.NINETY);
	        Flipper leftFlipper4 = new Flipper(15, 8, "leftFlipper4", BumperSide.LEFT, Orientation.NINETY);
	        List<Gadget> trigLF1 = new ArrayList<Gadget>();
	        trigLF1.add(leftFlipper1);
	        CircleBumper circle3 = new CircleBumper(0, 5, "circle3"); // triggers LeftFlipper1
	        circle3.setTriggers(trigLF1);
	        CircleBumper circle4 = new CircleBumper(5, 5, "circle4");
	        List<Gadget> trigLF3 = new ArrayList<Gadget>();
	        trigLF3.add(leftFlipper3);
	        CircleBumper circle5 = new CircleBumper(10, 5, "circle5"); // triggers LeftFlipper3
	        circle5.setTriggers(trigLF3);
	        List<Gadget> trigLF4 = new ArrayList<Gadget>();
	        trigLF4.add(leftFlipper4);
	        CircleBumper circle6 = new CircleBumper(15, 5, "circle6"); // triggers LeftFlipper4
	        circle6.setTriggers(trigLF4);
	
	        TriangleBumper triangle1 = new TriangleBumper(19, 0, "triangle1", Orientation.NINETY);
	        TriangleBumper triangle2 = new TriangleBumper(10, 18, "triangle2", Orientation.ONE_HUNDRED_EIGHTY);
	        Flipper rightFlipper1 = new Flipper(2, 15, "rightFlipper1", BumperSide.RIGHT, Orientation.ZERO);
	        Flipper rightFlipper2 = new Flipper(17, 15, "rightFlipper2", BumperSide.RIGHT, Orientation.ZERO);
	
	        
	        Absorber absorber = new Absorber(0, 19, "absorber", 20, 1);
	        List<Gadget> trigForAbsorber = new ArrayList<Gadget>();
	        trigForAbsorber.addAll(Arrays.asList(rightFlipper1, rightFlipper2, absorber));
	        absorber.setTriggers(trigForAbsorber);
	        
	        gadgetList.addAll(Arrays.asList(leftFlipper1, leftFlipper2, leftFlipper3, leftFlipper4,
	                circle1, circle2, circle3, circle4, circle5, circle6,
	                triangle1, triangle2, rightFlipper1, rightFlipper2, absorber));
    	}catch(InvalidInvariantException e){
    		System.out.println("One of the invariants in these objects was broken!");
    		e.printStackTrace();
    	}
        Board board = new Board(gadgetList);
        
        // make balls
        Ball ball1 = new Ball(.25, 3.25, Vect.ZERO);
        Ball ball2 = new Ball(5.25, 3.25, Vect.ZERO);
        Ball ball3 = new Ball(10.25, 3.25, Vect.ZERO);
        Ball ball4 = new Ball(15.25, 3.25, Vect.ZERO);
        Ball ball5 = new Ball(19.25, 3.25, Vect.ZERO);
        board.addBall(ball1);
        board.addBall(ball2);
        board.addBall(ball3);
        board.addBall(ball4);
        board.addBall(ball5);
        return board;
  
    }


}
