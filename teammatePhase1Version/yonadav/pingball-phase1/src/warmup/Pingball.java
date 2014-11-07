package warmup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import physics.Angle;
import physics.Vect;
import warmup.Flipper.BumperSide;


/**
 * TODO: put documentation for your class here
 */
public class Pingball {

    private static int FRAMES_PER_SECOND; 

    /**
     * TODO: describe your main function's command line arguments here
     */
    public static void main(String[] args) {
        //use the standard input to select the board function which returns a board all set up and ready to go 
        FRAMES_PER_SECOND = 20; 
        Board boardToPlay; 
        String argument = ""; 
        if (args.length <1  ){
        	argument = "default"; 
        }
        else{
        	argument = args[0]; 
        }
        switch(argument)
        {
        case "flippers":
            boardToPlay = makeFlippers(); 
            break; 

        case "absorber":
            boardToPlay = makeAbsorber(); 
            break;

            //make it respond both to the string default and to no arguments
        case "default": 
            boardToPlay = makeDefault(); 
            break; 

        default: 
            boardToPlay = makeDefault(); 
            break;
        }

        while(true){
            System.out.println(boardToPlay.makeBoardMatrix());
            boardToPlay.update();
        }
    }

    public static Board makeDefault(){
        List<Ball> listOfBalls = new ArrayList<>(); 
        Ball ball = new Ball(1.25, 1.25, new Vect(0,0)); 
        listOfBalls.add(ball); 

        List<Gadget> listOfGadgets = new ArrayList<>(); 

        CircleBumper firstCircleBumper = new CircleBumper(1, 10); 
        listOfGadgets.add(firstCircleBumper); 

        TriangleBumper firstTriangleBumper = new TriangleBumper(12, 15, Angle.DEG_180 ); 
        listOfGadgets.add(firstTriangleBumper); 

        SquareBumper firstSquareBumper = new SquareBumper(0,17); 
        listOfGadgets.add(firstSquareBumper); 

        SquareBumper secondSquareBumper = new SquareBumper(1,17); 
        listOfGadgets.add(secondSquareBumper); 

        SquareBumper thirdSquareBumper = new SquareBumper(2,17); 
        listOfGadgets.add(thirdSquareBumper); 

        CircleBumper secondCircleBumper = new CircleBumper(7, 18); 
        listOfGadgets.add(secondCircleBumper); 

        CircleBumper thirdCircleBumper = new CircleBumper(8, 18); 
        listOfGadgets.add(thirdCircleBumper); 

        CircleBumper fourthCircleBumper = new CircleBumper(9, 18); 
        listOfGadgets.add(fourthCircleBumper); 

        return new Board(listOfGadgets, listOfBalls, FRAMES_PER_SECOND, 0.0, 0.0); 
    }

    public static Board makeAbsorber(){
        List<Ball> listOfBalls = new ArrayList<>(); 
        Ball ball = new Ball(10.25, 15.25, new Vect(0,0)); 
        listOfBalls.add(ball); 

        Ball ball1 = new Ball(19.25, 3.25, new Vect(0,0)); 
        listOfBalls.add(ball1); 

        Ball ball2 = new Ball(1.25, 5.25, new Vect(0,0)); 
        listOfBalls.add(ball2); 

        List<Gadget> listOfGadgets = new ArrayList<>(); 


        Absorber absorber = new Absorber(0, 18, 20, 2); 

        Set<Gadget> setOfGadgetsToActivate = new HashSet<Gadget>(); 
        setOfGadgetsToActivate.add(absorber); 


        listOfGadgets.add(absorber); 

        TriangleBumper firstTriangleBumper = new TriangleBumper(19, 0, Angle.DEG_90); 
        listOfGadgets.add(firstTriangleBumper); 

        CircleBumper firstCircleBumper = new CircleBumper(1, 10); 
        firstCircleBumper.setGadgetsToActivate(setOfGadgetsToActivate);
        listOfGadgets.add(firstCircleBumper); 

        CircleBumper secondCircleBumper = new CircleBumper(2, 10); 
        secondCircleBumper.setGadgetsToActivate(setOfGadgetsToActivate);
        listOfGadgets.add(secondCircleBumper); 

        CircleBumper thirdCircleBumper = new CircleBumper(3, 10); 
        thirdCircleBumper.setGadgetsToActivate(setOfGadgetsToActivate);
        listOfGadgets.add(thirdCircleBumper); 

        CircleBumper fourthCircleBumper = new CircleBumper(4, 10); 
        fourthCircleBumper.setGadgetsToActivate(setOfGadgetsToActivate);
        listOfGadgets.add(fourthCircleBumper); 

        CircleBumper fiveCircleBumper = new CircleBumper(5, 10); 
        fiveCircleBumper.setGadgetsToActivate(setOfGadgetsToActivate);
        listOfGadgets.add(fiveCircleBumper); 

        return new Board(listOfGadgets, listOfBalls, FRAMES_PER_SECOND, 0.0, 0.0); 
    }

    public static Board makeFlippers(){
        List<Ball> listOfBalls = new ArrayList<>(); 
        Ball ball = new Ball(0.25, 3.25, new Vect(0,0)); 
        listOfBalls.add(ball); 

        Ball ball1 = new Ball(5.25, 3.25, new Vect(0,0)); 
        listOfBalls.add(ball1); 

        Ball ball2 = new Ball(10.25, 3.25, new Vect(0,0)); 
        listOfBalls.add(ball2); 

        Ball ball3 = new Ball(15.25, 3.25, new Vect(0,0)); 
        listOfBalls.add(ball3); 

        Ball ball4 = new Ball(19.25, 3.25, new Vect(0,0)); 
        listOfBalls.add(ball4); 


        List<Gadget> listOfGadgets = new ArrayList<>(); 

        Flipper leftFlipper1 = new Flipper(0, 8, BumperSide.LEFT,  Angle.DEG_90); 
        listOfGadgets.add(leftFlipper1); 

        Flipper leftFlipper2 = new Flipper(4, 10, BumperSide.LEFT,  Angle.DEG_90); 
        listOfGadgets.add(leftFlipper2); 

        Flipper leftFlipper3 = new Flipper(9, 8, BumperSide.LEFT,  Angle.DEG_90); 
        listOfGadgets.add(leftFlipper3); 

        Flipper leftFlipper4 = new Flipper(15, 8, BumperSide.LEFT,  Angle.DEG_90); 
        listOfGadgets.add(leftFlipper4); 

        //All of the CircleBumpers
        CircleBumper firstCircleBumper = new CircleBumper(5, 18); 
        listOfGadgets.add(firstCircleBumper); 

        CircleBumper secondCircleBumper = new CircleBumper(7, 13); 
        listOfGadgets.add(secondCircleBumper); 

        //Triggered Gadgets: leftFlipper1
        CircleBumper thirdCircleBumper = new CircleBumper(0, 5); 
        thirdCircleBumper.setGadgetsToActivate(new HashSet<Gadget>(Arrays.asList(leftFlipper1))); 
        listOfGadgets.add(thirdCircleBumper); 

        CircleBumper fourthCircleBumper = new CircleBumper(5, 5); 
        listOfGadgets.add(fourthCircleBumper); 

        CircleBumper fiveCircleBumper = new CircleBumper(10, 5); 
        fiveCircleBumper.setGadgetsToActivate(new HashSet<Gadget>(Arrays.asList(leftFlipper3)));
        listOfGadgets.add(fiveCircleBumper); 
        
        CircleBumper sixCircleBumper = new CircleBumper(15, 5); 
        sixCircleBumper.setGadgetsToActivate(new HashSet<Gadget>(Arrays.asList(leftFlipper4)));
        listOfGadgets.add(sixCircleBumper); 
        
 
        TriangleBumper firstTriangleBumper = new TriangleBumper(19, 0, Angle.DEG_90); 
        listOfGadgets.add(firstTriangleBumper);        
  
        TriangleBumper secondTriangleBumper = new TriangleBumper(10, 18, Angle.DEG_180); 
        listOfGadgets.add(secondTriangleBumper);        
        
        Flipper rightFlipper1 = new Flipper(2, 15, BumperSide.RIGHT, Angle.ZERO); 
        listOfGadgets.add(rightFlipper1);        
         
        Flipper rightFlipper2 = new Flipper(17, 15, BumperSide.RIGHT, Angle.ZERO); 
        listOfGadgets.add(rightFlipper2);        
        
        Absorber absorber = new Absorber(0, 19, 20, 1); 
        listOfGadgets.add(absorber); 
        absorber.setGadgetsToActivate(new HashSet<Gadget>(Arrays.asList(rightFlipper1, rightFlipper2, absorber)));

        return new Board(listOfGadgets, listOfBalls, FRAMES_PER_SECOND, 0.0, 0.0); 


    }
}