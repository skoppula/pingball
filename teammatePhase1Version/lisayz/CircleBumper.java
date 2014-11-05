package Pingball;

import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Geometry.DoublePair;
import physics.Vect;

public class CircleBumper implements Gadget {

    // Position is the upper right corner
    private DoublePair position;

    private Circle bumperCircle;
    private final double radius = .5;

    private final boolean triggerSelf;

    private final List<Gadget> gadgetsToTrigger;

    public CircleBumper(DoublePair position, List<Gadget> gadgets,
            boolean triggerSelf) {
        this.gadgetsToTrigger = gadgets;
        this.position = position;
        bumperCircle = new Circle(position.d1 + .5, position.d2 + .5, radius);
        this.triggerSelf = triggerSelf;
    }

    public double getTimeUntilCollision(Ball ball) {
        return Geometry.timeUntilCircleCollision(bumperCircle, ball.getGirth(),
                ball.getVelocity());
    }

    @Override
    public char charRep() {
        return '0';
    }

    public DoublePair getBoardRepSize() {
        return new DoublePair(1);
    }

    public DoublePair getBoardRepPosition() {
        return position;
    }

    public void trigger() {
        for (Gadget gadget : gadgetsToTrigger) {
            gadget.action();
        }
        if (this.triggerSelf)
            this.action();
    }

    public void updateGadgetPosition(double timeDelta) {

    }

    public void action() {

    }

    public void collision(Ball ball) {

        Vect newVelocity = Geometry.reflectCircle(bumperCircle.getCenter(),
                ball.getGirth().getCenter(), ball.getVelocity());
        ball.setVelocity(newVelocity);
        trigger();
    }

    @Override
    public String toString() {
        return "CircleBumper";
    }

    @Override
    public DoublePair getSize() {
        return new DoublePair(1);
    }

}
