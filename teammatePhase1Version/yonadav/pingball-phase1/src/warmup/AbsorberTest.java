package warmup;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * Test Methodology: 
 * 
 * Test Creators: 
 * Absorber() creates a valid absorber according to its rep invariant 
 * 
 * Empty of balls: 
 * Make sure that absorber displays correctly without any balls around it 
 * 
 * One ball: 
 * make sure that the absorber works when you have one ball that goes into it over and over- should be self triggering 
 * 
 * Two balls: 
 * have one absorber that takes in two balls. 
 *
 */
public class AbsorberTest {

    @Test
    public void noBallsCreator() {
        Absorber emptyAbsorber = new Absorber(0,17,16,2); 
        
        Board myBoard = new Board(Arrays.asList(emptyAbsorber), Arrays.asList(), 20, 0, 0); 
        
        System.out.println(myBoard.makeDebugBoardMatrix()); 
        
        
        String result = "......................\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".================    .\n"
                +".================    .\n"
                +".                    .\n"
                +"......................\n"
                +"\n";

        assertEquals(result, myBoard.makeBoardMatrix()); 
    }

}
