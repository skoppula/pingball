package warmup;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import physics.Vect;

/**
 * Test Methodology: 
 * 
 * Test how ball displays on Board without any movement 
 * 
 * Test setVelocity: 
 * -test for when velocity is negative
 * -for when velocity is positive
 * -Positive large 
 * -zero 
 * 
 * 
 * Test updateBallPosition: 
 * -ball has no velocity - should remain zero 
 * -zero time given as input - position should remain the same. 
 * 
 * 
 * Test collideWithBall: 
 * -make sure that a ball that collides with another ball has a different velocity after the collision
 * 
 */
public class BallTest {
    /*
     * setVelocity
     * velocity: 0, .000001, 5, 300
     */
    
    @Test
    public void ballSetVelocityNegative(){
        Ball ball = new Ball(5, 5, new Vect(4, 4));
        Vect correctVector = new Vect(-3, -1);
        ball.setVelocity(correctVector);
        assertEquals(correctVector, ball.getVelocity());
    }
    
    @Test
    public void ballSetVelocityPositiveNormal(){
        Ball ball = new Ball(5, 5, new Vect(4, 4));
        Vect correctVector = new Vect(3, 1);
        ball.setVelocity(correctVector);
        assertEquals(correctVector, ball.getVelocity());
    }
    
    @Test
    public void ballSetVelocityPositiveSmall(){
        Ball ball = new Ball(5, 5, new Vect(4, 4));
        Vect correctVector = new Vect(0.0, 0.0);
        ball.setVelocity(new Vect(0.0000001, 0.0001));
        assertEquals(correctVector, ball.getVelocity());
    }

    @Test
    public void ballSetVelocityPositiveHuge(){
        Ball ball = new Ball(5, 5, new Vect(4, 4));
        Vect correctVector = new Vect(200, 0);
        ball.setVelocity(new Vect(300, 0));
        assertEquals(correctVector, ball.getVelocity());
    }
    
    @Test
    public void ballSetVelocityZero(){
        Ball ball = new Ball(5, 5, new Vect(0, 0));
        Vect correctVector = new Vect(0, 0);
        ball.setVelocity(new Vect(0, 0));
        assertEquals(correctVector, ball.getVelocity());
    }
    
    
    /*
     * updateBallPosition
     * velocity: 0, 5
     * timestep: 0, 1, 50
     * move ball out of bounds
     */
    
    @Test
    public void ballUpdateBallPositionNoVelocity(){
        Ball ball = new Ball(1, 1, new Vect(0, 0));
        double timestep = 1;
        Vect correctFinalBallCenter = new Vect(1, 1);
        ball.updateBallPosition(timestep);
        assertEquals(correctFinalBallCenter, ball.getBallCircle().getCenter());
    }
    
    @Test
    public void ballUpdateBallPositionRegular(){
        Ball ball = new Ball(1, 1, new Vect(5, 5));
        double timestep = 1;
        Vect correctFinalBallCenter = new Vect(6, 6);
        ball.updateBallPosition(timestep);
        assertEquals(correctFinalBallCenter, ball.getBallCircle().getCenter());
    }
    
    @Test
    public void ballUpdateBallPositionZeroTime(){
        Ball ball = new Ball(1, 1, new Vect(5, 5));
        double timestep = 0;
        Vect correctFinalBallCenter = new Vect(1, 1);
        ball.updateBallPosition(timestep);
        assertEquals(correctFinalBallCenter, ball.getBallCircle().getCenter());
    }
    
    @Test
    public void ballUpdateBallPositionHugeTime(){
        Ball ball = new Ball(1, 1, new Vect(5, 5));
        double timestep = 50;
        Vect correctFinalBallCenter = new Vect(19.75, 19.75);
        ball.updateBallPosition(timestep);
        assertEquals(correctFinalBallCenter, ball.getBallCircle().getCenter());
    }
    
    
    /*
     * getGridSymbolRep
     * location: (0,0), (4, 5), (19,19)
     * 
     */
    
    @Test
    public void ballGetGridSymbolRepTopLeft(){
        Ball ball = new Ball(0, 0, new Vect(0, 0));
        GridSymbol correctSymbol = new GridSymbol(0, 0, '*');
        List<GridSymbol> symbolList = new ArrayList<>();
        symbolList.add(correctSymbol);
        List<GridSymbol> returnedList = ball.getGridSymbolRep();
        assertEquals(symbolList.size(), returnedList.size());
        for(int i=0; i<symbolList.size(); i++){
            assertEquals(symbolList.get(i).getX(),returnedList.get(i).getX());
            assertEquals(symbolList.get(i).getY(),returnedList.get(i).getY());
            assertEquals(symbolList.get(i).getChar(),returnedList.get(i).getChar());
        }
    }
    
    @Test
    public void ballGetGridSymbolRepBottomRight(){
        Ball ball = new Ball(19, 19, new Vect(0, 0));
        GridSymbol correctSymbol = new GridSymbol(19, 19, '*');
        List<GridSymbol> symbolList = new ArrayList<>();
        symbolList.add(correctSymbol);
        List<GridSymbol> returnedList = ball.getGridSymbolRep();
        assertEquals(symbolList.size(), returnedList.size());
        for(int i=0; i<symbolList.size(); i++){
            assertEquals(symbolList.get(i).getX(),returnedList.get(i).getX());
            assertEquals(symbolList.get(i).getY(),returnedList.get(i).getY());
            assertEquals(symbolList.get(i).getChar(),returnedList.get(i).getChar());
        }
    }
    
    @Test
    public void ballGetGridSymbolRepNormal(){
        Ball ball = new Ball(4, 5, new Vect(0, 0));
        GridSymbol correctSymbol = new GridSymbol(4, 5, '*');
        List<GridSymbol> symbolList = new ArrayList<>();
        symbolList.add(correctSymbol);
        List<GridSymbol> returnedList = ball.getGridSymbolRep();
        assertEquals(symbolList.size(), returnedList.size());
        for(int i=0; i<symbolList.size(); i++){
            assertEquals(symbolList.get(i).getX(),returnedList.get(i).getX());
            assertEquals(symbolList.get(i).getY(),returnedList.get(i).getY());
            assertEquals(symbolList.get(i).getChar(),returnedList.get(i).getChar());
        }
    }
    
    @Test
    public void ballCollideWithBall(){
        Ball ball1 = new Ball(4, 5, new Vect(1, 0));
        Ball ball2 = new Ball(8, 5, new Vect(-.5, 0));
        ball1.collideWithBall(ball2);
        assertEquals(new Vect(-.5, 0), ball1.getVelocity());
        assertEquals(new Vect(1, 0), ball2.getVelocity());
    }

}
