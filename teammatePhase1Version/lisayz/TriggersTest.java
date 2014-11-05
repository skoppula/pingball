package Pingball;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import Pingball.Gadget.Orientation;
import physics.Angle;
import physics.Vect;
import physics.Geometry.DoublePair;

public class TriggersTest {
    
    private static final double SECONDS_TO_FULL_SWING = 0.08333333333;
    
    // test flipper triggering itself
    @Test
    public void testSelfTriggeringFlipper() {
        DoublePair flipperPos = new DoublePair(5, 10);
        Ball ball = new Ball(new Vect(5,3), flipperPos);
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        LeftFlipper flipper = new LeftFlipper(flipperPos, Orientation.ZERO, gadgetList, true);
        assertEquals(new Angle(0).radians(), flipper.currentAngle.radians(), 0.001);
        flipper.collision(ball);
        flipper.updateGadgetPosition(SECONDS_TO_FULL_SWING);
        assertEquals(-Math.PI/2, flipper.currentAngle.radians(), 0.001);
    }
    
    // test circle bumper triggering flipper
    @Test
    public void testFlipperTriggeringCircle() {
        DoublePair flipperPos = new DoublePair(5, 10);
        DoublePair circlePos = new DoublePair(3,3);
        Ball ball = new Ball(new Vect(5,3), flipperPos);
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        LeftFlipper flipper = new LeftFlipper(flipperPos, Orientation.ZERO, gadgetList, false);
        gadgetList.add(flipper);
        CircleBumper circle = new CircleBumper(circlePos, gadgetList, false);
        assertEquals(new Angle(0).radians(), flipper.currentAngle.radians(), 0.001);
        circle.collision(ball);
        flipper.updateGadgetPosition(SECONDS_TO_FULL_SWING);
        assertEquals(-Math.PI/2, flipper.currentAngle.radians(), 0.001);
    }
}
