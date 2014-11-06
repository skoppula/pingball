package warmup;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CircleBumperTest {
    /* Testing Strategy
    *      - update()
    *          - run update on circle bumper for full period of time 
    *          - for (for isAction = true and false)
    *      - getRepChar()
    *          - run on circle Bumper and check that returned character is correct (0)
    *      - activate()
    *          - call activate on triangle bumper when isAction is true and false
    *      - setGadgetsToActivate()
    *          - set 0, 1, >1 gadgets in gadgetsToActivate.
    *      - getGadgetsToActivate()
    *          - get gadgets to activate where there are 0, 1, >1 gadgets in list
    *      - collide()
    *          - 0,45,90 degrees into the center of the ball
    *          - ball hits bumper at 0 degrees with point of contact at: 
    *               - top of bumper
    *               - middle of bumper
    *               - bottom of bumper
    *      - getPhysicalRep()
    *          - run on circle bumper in two different locations
    *      - getGridSymbolRep()
    *          - run on circle bumper in two different locations
    *      - getCoefficientOfReflection()
    *          - run on circle bumper once
    */

    @Test 
    public void testGetRepCharSquareBumper(){
        CircleBumper cb = new CircleBumper(1,1);
        char c = cb.getRepChar();
        assertTrue('0' == c);
    }
    
    @Test
    public void testActivateSquareBumper(){
        CircleBumper cb = new CircleBumper(1,1);
        assertTrue(cb.isAction == false);
        cb.activate();
        assertTrue(cb.isAction == true);
        cb.activate();
        assertTrue(cb.isAction == true);
    }
    
    @Test
    public void testGetCoefficientOfReflection(){
        CircleBumper cb = new CircleBumper(1,1);
        double test = cb.getCoefficientOfReflection();
        assertTrue(test == 1.);
    }

}
