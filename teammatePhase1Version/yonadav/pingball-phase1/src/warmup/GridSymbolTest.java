package warmup;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Vect;

/**
 * 
 * Test constructors:
 * make sure that if the gridSymbol is given a specific GridPoint and a specific symbol that its 
 * getter methods return the same values; 
 *
 */
public class GridSymbolTest {

    @Test
    public void constructorTest() {
        int myX = 4; 
        int myY = 2; 
        GridSymbol resultGridSymbol = new GridSymbol(new Vect(myX, myY), 'x'); 
        
        assertEquals(resultGridSymbol.getChar(), 'x'); 
        assertEquals(resultGridSymbol.getX(), myX); 
        assertEquals(resultGridSymbol.getY(), myY); 
    }

}
