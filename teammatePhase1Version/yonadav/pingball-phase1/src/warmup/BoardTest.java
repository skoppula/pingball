package warmup;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Circle;
import physics.Vect;
import warmup.Gadget;

/**
 * Testing Strategy
 * No triggers: 
 *    - Empty board 
 *    - no collision:
 *         - moving into white space
 *         - moving next to but not colliding with the walls
 *    - collision with things that don't have triggers:
 *         - collision (angle = 0)
 *         - collision at an angle (+ angle and - angle)
 * 
 * With triggers: 
 *      colliding with one flipper that must act when it is collided with 
 *      colliding with one absorber that must act when it is collided with 
 *      colliding with one bumper that must act to trigger a flipper when the bumper is collided with 
 *         
 */
public class BoardTest {

    private static Vect GRAVITY = new Vect(0,25); 
    @BeforeClass
    public static void setUpBeforeClass(){
    }
    
    @Test
    public void testMoveNoCollision(){
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        Ball ball = new Ball(1,1, new Vect(1,1));
        List<Ball> ballList = new ArrayList<Ball>(Arrays.asList(ball));
        Board board = new Board(gadgetList, ballList, 1, 0,0);
        board.setGravityAndFriction(new Vect(0,0), 0,0);
//        String startBoard = board.makeBoardMatrix();
//        System.out.println(test);
        board.update();
        String test = board.makeBoardMatrix();
        String expected = 
                "......................\n"
              + ".                    .\n"
              + ".                    .\n"
              + ".  *                 .\n"
              + ".                    .\n"
              + ".                    .\n"
              + ".                    .\n"
              + ".                    .\n"
              + ".                    .\n"
              + ".                    .\n"
              + ".                    .\n"
              + ".                    .\n"
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
        System.out.println(ball.getBallCircle());
        assertEquals(expected,test);
        assertTrue(ball.getVelocity().equals(new Vect(1,1)));
        board.update();
        assertEquals(ball.getBallCircle().getCenter(), new Vect(3,3));
        assertTrue(ball.getVelocity().equals(new Vect(1,1)));
        board.update();
        assertEquals(ball.getBallCircle().getCenter(), new Vect(4,4));
        assertTrue(ball.getVelocity().equals(new Vect(1,1)));
    }
    
    @Test
    public void testOneBallCollideCircleBumperZeroDegree(){
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        Ball ball = new Ball(2,2, new Vect(-1,0));
        List<Ball> ballList = new ArrayList<Ball>(Arrays.asList(ball));
        Board board = new Board(gadgetList, ballList, 1, 0,0);
        board.setGravityAndFriction(new Vect(0,0), 0,0);
        board.update();
        System.out.println(ball.getBallCircle());
//        assertTrue(ball.getVelocity().equals(new Vect(-1,0)));
//        assertEquals(ball.getBallCircle().getCenter(), new Vect(1,2));
        board.update();
        System.out.println(ball.getBallCircle());
//        assertEquals(ball.getBallCircle().getCenter(), new Vect(0,1));
//        assertTrue(ball.getVelocity().equals(new Vect(1,0)));
        board.update();
        System.out.println(ball.getBallCircle());
//        assertEquals(ball.getBallCircle().getCenter(), new Vect(0,2));
//        assertTrue(ball.getVelocity().equals(new Vect(1,0)));
    }
    
    

//    @Test
//    public void testMove11NoCollision() {
//        final int updatesPerSecond = 10; 
//
//        List<Gadget>  gadgetList = new ArrayList<Gadget>();
//        Vect ballVelocity = new Vect(0,1);
//
//        Ball ball = new Ball(1,1, ballVelocity); 
//        double oldY = ball.getBallCircle().getCenter().y();
//        List<Ball> ballsList = new ArrayList<Ball>(Arrays.asList(ball));
//        Board board = new Board(gadgetList, ballsList, updatesPerSecond, 0, 0);
//                
//        String test = board.makeBoardMatrix();
//        
//        board.update();
//
//        //make sure that ball has the correct velocity after one second when adjusted for gravity: 
//        // d = 1/2(a*t^2) + vi*t 
//        double newY = oldY + GRAVITY.length()*.5 + ballVelocity.length();  
//
//        Circle expectedCircle = new Circle(1, newY, .5); 
//        assertEquals(expectedCircle, ball.getBallCircle()); 
//        
//        test = board.makeBoardMatrix();
//
//        String expected = 
//                "....................\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".  *               .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + ".                  .\n"
//                        + "....................\n"
//                        + "\n";
//
//        System.out.println("\n_______\n");
//        System.out.println(test);
//        System.out.println("\n_______\n");
//        System.out.println(expected);
//        //assertEquals(expected,test);  
//        //assertEquals(new Vect(1,1), ballsList.get(0).getVelocity());
//    }
}
