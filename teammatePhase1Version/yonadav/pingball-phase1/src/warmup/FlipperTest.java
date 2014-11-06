package warmup;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Angle;
import physics.Circle;
import physics.Vect;
import warmup.Flipper.BumperSide;

//TODO: write a test methodology
/**
 * Test methodology:
 * 
 * Stationary- "Empty" base cases
 * Flipper that is never triggered- make sure it displays correctly 
 * 
 * Tests on Flippers being triggered: 
 * Flipper that is triggered by a square bumpers- put a ball above it and see the triggers go for a while 
 * Flipper that is self triggered- put a ball above it and make sure it flips 
 * 
 * Tests on collisions with flippers: 
 * Flipper that isn't moving or self triggered collides with a ball- should act like a stationary wall 
 * Flipper that is moving hits a ball- should send it to the correct direction
 */
public class FlipperTest {

    private static Vect GRAVITY = new Vect(0,25); 

    @BeforeClass
    public static void setUpBeforeClass(){
    }

    /**
     * Takes in a board and prints out to the console forever while updating 
     * @param boardToRun the board to make run 
     */
    public static void run(Board boardToRun){
        while (true){
            System.out.println(boardToRun.makeBoardMatrix()); 
            boardToRun.update();
        }
    }

    @Test
    public void testOneBallTriggersAFlipper() {
        final int updatesPerSecond = 10; 

        Vect ballVelocity = new Vect(0,1);

        Ball ball = new Ball(1,1, ballVelocity); 
        List<Ball> ballsList = new ArrayList<Ball>(Arrays.asList(ball));

        List<Gadget>  gadgetList = new ArrayList<Gadget>();

        Flipper leftFlipper1 = new Flipper(5, 5, BumperSide.LEFT, Angle.DEG_90); 
        gadgetList.add(leftFlipper1); 

        SquareBumper squareBumper1 = new SquareBumper(1,10); 
        squareBumper1.setGadgetsToActivate(new HashSet<Gadget>(Arrays.asList(leftFlipper1)));
        gadgetList.add(squareBumper1); 


        double oldY = ball.getBallCircle().getCenter().y();
        Board board = new Board(gadgetList, ballsList, updatesPerSecond, 0, 0);
        String result = board.makeBoardMatrix();

        //first lets make sure that we have our board just like we want it 
        String myExpectedBefore = 
                "......................\n" 
                        + ".                    .\n"
                        + ". *                  .\n" 
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".     __             .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ". #                  .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + ".                    .\n"
                        + "......................\n"
                        + "\n"; 

        assertEquals(myExpectedBefore, result); 

        //now after the ball hits the bumper I expect the flipper to change orientation 
        final int numOfStepsUntilFlipperGoesUp = 10; 
        for (int i = 0; i < numOfStepsUntilFlipperGoesUp; i++)
        {
            board.update(); 
        }
        result = board.makeBoardMatrix(); 


        String expectedResultAfter =        "......................\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +". *    |             .\n"
                +".      |             .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +". #                  .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +"......................\n"
                +"\n";


        assertEquals(expectedResultAfter, result); 

        //after the ball has hit the square bumper in 10? updates then we should see the flipper change orientation 

        //run(board); 
    }
}
