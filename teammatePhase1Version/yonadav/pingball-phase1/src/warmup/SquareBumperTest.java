package warmup;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;

public class SquareBumperTest {

    /*Testing Methodology
     *      - update()
     *          - run update on all Square for full period of time (make sure no actions happen)
     *          - for (for isAction = true and false)
     *      - getRepChar()
     *          - run square bumper and check that returned character is #
     *      - activate()
     *          - call activate on all gadgets when isAction is true and false
     *      - setGadgetsToActivate()
     *          - set 0, 1, >1 gadgets in gadgetsToActivate.
     *      - getGadgetsToActivate()
     *          - get gadgets to activate where there are 0, 1, >1 gadgets in list
     *      - collide()
     *          - collide with 4 faces of bumper
     *          - collide with corner of bumper from angle 0, angle 45, between 0 and 45
     *      - getPhysicalRep()
     *          - run on all square bumper.
     *      - getGridSymbolRep()
     *          - run on square bumper. Make sure bumper is placed in the correct location
     *      - getCoefficientOfReflection()
     *          - run on square bumper. 
     *         
     */

    
    @Test 
    public void testGetRepCharSquareBumper(){
        SquareBumper sb = new SquareBumper(1,1);
        char c = sb.getRepChar();
        assertTrue('#' == c);
    }
    
    @Test
    public void testActivateSquareBumper(){
        SquareBumper sb = new SquareBumper(1,1);
        assertTrue(sb.isAction == false);
        sb.activate();
        assertTrue(sb.isAction == true);
        sb.activate();
        assertTrue(sb.isAction == true);
    }
    
    @Test
    public void testGetCoefficientOfReflection(){
        SquareBumper sb = new SquareBumper(1,1);
        double test = sb.getCoefficientOfReflection();
        assertTrue(test == 1.);
    }

}
