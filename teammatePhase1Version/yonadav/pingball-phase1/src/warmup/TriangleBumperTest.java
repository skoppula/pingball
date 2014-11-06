package warmup;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Angle;

public class TriangleBumperTest {
    /* Testing Strategy
    *      - update()
    *          - run update on triangle bumper for full period of time 
    *          - for (for isAction = true and false)
    *      - getRepChar()
    *          - run on triangle Bumper and check that returned character is correct
    *      - activate()
    *          - call activate on triangle bumper when isAction is true and false
    *      - setGadgetsToActivate()
    *          - set 0, 1, >1 gadgets in gadgetsToActivate.
    *      - getGadgetsToActivate()
    *          - get gadgets to activate where there are 0, 1, >1 gadgets in list
    *      - collide()
    *          - vertical, horizontal and diagonal sides of bumper
    *          - corners of the bumper in 0, 90, 0<angle<90 degrees
    *      - getPhysicalRep()
    *          - run on triangle bumper on all orientations
    *      - getGridSymbolRep()
    *          - run on triangle bumper on all orientations
    *      - getCoefficientOfReflection()
    *          - run on triangle bumper once
    *         
    */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test 
    public void testGetRepCharTriangleBumperZeroAngle(){
        TriangleBumper tb = new TriangleBumper(1,1,Angle.ZERO);
        char c = tb.getRepChar();
        assertTrue('/' == c);
    }
    
    @Test
    public void testActivateTriangleBumper(){
        TriangleBumper tb = new TriangleBumper(1,1,Angle.ZERO);
        assertTrue(tb.isAction == false);
        tb.activate();
        assertTrue(tb.isAction == true);
        tb.activate();
        assertTrue(tb.isAction == true);
    }
    
    @Test
    public void testGetCoefficientOfReflection(){
        TriangleBumper tb = new TriangleBumper(1,1, Angle.ZERO);
        double test = tb.getCoefficientOfReflection();
        assertTrue(test == 1.);
    }

}
