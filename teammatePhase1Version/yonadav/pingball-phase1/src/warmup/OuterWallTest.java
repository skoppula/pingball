package warmup;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class OuterWallTest {

    /*Testing Methodology
     *      - update()
     *          - run update on outerWall for full period of time (make sure no actions happen)
     *          - for (for isAction = true and false)
     *      - getRepChar()
     *          - run outer Wall and check that returned character is .
     *      - activate()
     *          - call activate on all gadgets when isAction is true and false
     *      - setGadgetsToActivate()
     *          - set 0, 1, >1 gadgets in gadgetsToActivate.
     *      - getGadgetsToActivate()
     *          - get gadgets to activate where there are 0, 1, >1 gadgets in list
     *      - collide()
     *          - collide with walls at <45, 45, 90, 135, >135 degrees
     *      - getPhysicalRep()
     *          - run on OuterWall with 1x1 board (length 3 wall), 20x20 board
     *      - getGridSymbolRep()
     *          - run on OuterWall with 1x1 board (length 3 wall), 20x20 board
     *      - getCoefficientOfReflection()
     *          - run on wall once 
     *      - makeWalls()
     *          - run on different sizes of wall
     *         
     */
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
