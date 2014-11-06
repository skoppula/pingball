package phase2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import phase2.Gadget.Orientation;
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
        
/*
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

        for (;;) {
            Thread.sleep(TIME_DELTA_MILLISECONDS);
            board.updateBoard(timeInSeconds);
            board.printBoard();
        }
        */
    }
    
    
 
    
    /**
     * @return benchmark board "default"
     */
    public static Board defaultBoard() {
        
    	//TODO figure out how to add names to a map in a board
        List<Gadget> emptyList = new ArrayList<Gadget>();
        
        // setup initial positions & orientations
        DoublePair initialBallPosition = new DoublePair(1.25, 1.25);
        
        // make ball & gadgets
        Ball ball = new Ball(Vect.ZERO, initialBallPosition);
        CircleBumper circle1 = new CircleBumper(1, 10, "circle1");
        TriangleBumper triangle = new TriangleBumper(12, 15, "triangle", Orientation.ONE_HUNDRED_EIGHTY);
        SquareBumper square1 = new SquareBumper(0, 17, "square1");
        SquareBumper square2 = new SquareBumper(1, 17, "square2");
        SquareBumper square3 = new SquareBumper(2, 17, "square3");
        CircleBumper circle2 = new CircleBumper(7, 18, "circle2");
        CircleBumper circle3 = new CircleBumper(8, 18, "circle3");
        CircleBumper circle4 = new CircleBumper(9, 18, "circle4");
        
        List<Gadget> gadgetList = new ArrayList<Gadget>();


        gadgetList.addAll(Arrays.asList(circle1, triangle, square1, square2, square3, circle2, circle3, circle4));

        Board board = new Board(gadgetList);
        board.addBall(ball);
        return board;
  
    }
    
    /**
     * @return benchmark board "absorber"
     */
    public static Board absorberBoard() {
        
        // setup initial positions & orientations
        DoublePair ballPosition1 = new DoublePair(10.25, 15.25);
        DoublePair ballPosition2 = new DoublePair(19.25, 3.25);
        DoublePair ballPosition3 = new DoublePair(1.25, 5.25);
        
        // make ball & gadgets
        Ball ball1 = new Ball(Vect.ZERO, ballPosition1);
        Ball ball2 = new Ball(Vect.ZERO, ballPosition2);
        Ball ball3 = new Ball(Vect.ZERO, ballPosition3);
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
        
        
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.addAll(Arrays.asList(absorber, triangle, circle1, circle2, circle3, circle4, circle5));
        Board board = new Board(gadgetList);
        board.addBall(ball1);
        board.addBall(ball2);
        board.addBall(ball3);
        return board;
  
    }
    
    /**
     * @return benchmark board "flippers"
     */
    public static Board flippersBoard() {
        
        // setup initial positions & orientations
        DoublePair ballPosition1 = new DoublePair(0.25, 3.25);
        DoublePair ballPosition2 = new DoublePair(5.25, 3.25);
        DoublePair ballPosition3 = new DoublePair(10.25, 3.25);
        DoublePair ballPosition4 = new DoublePair(15.25, 3.25);
        DoublePair ballPosition5 = new DoublePair(19.25, 3.25);
        DoublePair leftFlipperPosition1 = new DoublePair(0,8);
        DoublePair leftFlipperPosition2 = new DoublePair(4,10);
        DoublePair leftFlipperPosition3 = new DoublePair(9,8);
        DoublePair leftFlipperPosition4 = new DoublePair(15,8);
        Orientation leftFlipperOrientation = Orientation.NINETY;
        DoublePair circlePosition1 = new DoublePair(5,18);
        DoublePair circlePosition2 = new DoublePair(7,13);
        DoublePair circlePosition3 = new DoublePair(0,5); // trigger LeftFlipper1
        DoublePair circlePosition4 = new DoublePair(5,5);
        DoublePair circlePosition5 = new DoublePair(10,5); // trigger LeftFlipper3
        DoublePair circlePosition6 = new DoublePair(15,5); // trigger LeftFlipper4
        DoublePair trianglePosition1 = new DoublePair(19,0);
        DoublePair trianglePosition2 = new DoublePair(10,18);
        Orientation triangleOrientation1 = Orientation.NINETY;
        Orientation triangleOrientation2 = Orientation.ONE_HUNDRED_EIGHTY;
        DoublePair rightFlipperPosition1 = new DoublePair(2,15);
        DoublePair rightFlipperPosition2= new DoublePair(17,15);
        Orientation rightFlipperOrientation = Orientation.ZERO;
        List<Gadget> emptyList = new ArrayList<Gadget>();
        emptyList = Collections.unmodifiableList(emptyList);
        DoublePair absorberPosition = new DoublePair(0,19);
        int absorberK = 20;
        int absorberM = 1;
        
        // make ball & gadgets
        Ball ball1 = new Ball(Vect.ZERO, ballPosition1);
        Ball ball2 = new Ball(Vect.ZERO, ballPosition2);
        Ball ball3 = new Ball(Vect.ZERO, ballPosition3);
        Ball ball4 = new Ball(Vect.ZERO, ballPosition4);
        Ball ball5 = new Ball(Vect.ZERO, ballPosition5);
        CircleBumper circle1 = new CircleBumper(circlePosition1, emptyList, false); 
        CircleBumper circle2 = new CircleBumper(circlePosition2, emptyList, false); 
        LeftFlipper leftFlipper1 = new LeftFlipper(leftFlipperPosition1, leftFlipperOrientation, emptyList, false);
        LeftFlipper leftFlipper2 = new LeftFlipper(leftFlipperPosition2, leftFlipperOrientation, emptyList, false);
        LeftFlipper leftFlipper3 = new LeftFlipper(leftFlipperPosition3, leftFlipperOrientation, emptyList, false);
        LeftFlipper leftFlipper4 = new LeftFlipper(leftFlipperPosition4, leftFlipperOrientation, emptyList, false);
        List<Gadget> trigLF1 = new ArrayList<Gadget>();
        trigLF1.add(leftFlipper1);
        CircleBumper circle3 = new CircleBumper(circlePosition3, trigLF1, false); // triggers LeftFlipper1
        CircleBumper circle4 = new CircleBumper(circlePosition4, emptyList, false);
        List<Gadget> trigLF3 = new ArrayList<Gadget>();
        trigLF3.add(leftFlipper3);
        CircleBumper circle5 = new CircleBumper(circlePosition5, trigLF3, false); // triggers LeftFlipper3
        List<Gadget> trigLF4 = new ArrayList<Gadget>();
        trigLF4.add(leftFlipper4);
        CircleBumper circle6 = new CircleBumper(circlePosition6, trigLF4, false); // triggers LeftFlipper4

        TriangleBumper triangle1 = new TriangleBumper(trianglePosition1, triangleOrientation1, emptyList, false);
        TriangleBumper triangle2 = new TriangleBumper(trianglePosition2, triangleOrientation2, emptyList, false);
        RightFlipper rightFlipper1 = new RightFlipper(rightFlipperPosition1, rightFlipperOrientation, emptyList, false);
        RightFlipper rightFlipper2 = new RightFlipper(rightFlipperPosition2, rightFlipperOrientation, emptyList, false);

        List<Gadget> trigForAbsorber = new ArrayList<Gadget>();
        trigForAbsorber.addAll(Arrays.asList(rightFlipper1, rightFlipper2));
        
        Absorber absorber = new Absorber(absorberPosition, absorberK, absorberM, trigForAbsorber, true); // triggers RightFlipper1, RightFlipper2, Absorber
        
        
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        gadgetList.addAll(Arrays.asList(leftFlipper1, leftFlipper2, leftFlipper3, leftFlipper4,
                circle1, circle2, circle3, circle4, circle5, circle6,
                triangle1, triangle2, rightFlipper1, rightFlipper2, absorber));
        Board board = new Board(gadgetList);
        
        board.addBall(ball1);
        board.addBall(ball2);
        board.addBall(ball3);
        board.addBall(ball4);
        board.addBall(ball5);
        return board;
  
    }


}
