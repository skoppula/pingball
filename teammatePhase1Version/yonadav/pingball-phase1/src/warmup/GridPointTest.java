package warmup;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test creators: 
 * Making a gridpoint at a point (myX, myY) will then return myX and myY
 *
 */
public class GridPointTest {

    
    
    @Test
    public void creatorTest() {
        final int myX = 3; 
        final int myY = 5; 
        GridPoint myGridPoint = new GridPoint(myX, myY); 
        
        assertEquals(myX, myGridPoint.getX()); 
        assertEquals(myY, myGridPoint.getY()); 
    }
    
    @Test
    public void creator2tTest() {
        final int myX = 8; 
        final int myY = 1; 
        GridPoint myGridPoint = new GridPoint(myX, myY); 
        
        assertEquals(myX, myGridPoint.getX()); 
        assertEquals(myY, myGridPoint.getY()); 
    }

}
