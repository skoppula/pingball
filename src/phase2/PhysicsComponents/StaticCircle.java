package phase2.PhysicsComponents;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.Vect;

/**
 * A physical circle, which does not rotate or move in other ways.
 */
public class StaticCircle implements PhysicsComponent {
    // Rep. Invariant: circle radius must be non-zero.
    
    
    /*
     * The geometry component representing our StaticCircle
     */
    private final Circle circle;
    
    /*
     * The reflection coefficient for collisions with our StaticCircle;
     */
    private final double reflectionCoefficient;
    
    /**
     * Creates a new static circle.
     * @param circle - must be a circle of nonzero radius.
     * @param reflectionCoefficient - the reflection coefficient for collisions with our StaticCircle.
     */
    public StaticCircle(Circle circle, double reflectionCoefficient){
        if (circle.getRadius() == 0){
            throw new IllegalArgumentException("Circles cannot have radius exactly 0. Just make it small :P");
        }
        this.circle = circle;
        this.reflectionCoefficient = reflectionCoefficient;
    }

    
    @Override
    public double timeUntilCollision(Circle ballCircle, Vect ballVelocity) {
        return Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
    }

    @Override
    public Vect reflect(Circle ballCircle, Vect ballVelocity,
            double reflectionCoefficient) {
        return Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, this.reflectionCoefficient*reflectionCoefficient);
    }

    @Override
    public boolean canRotate() {
        return false;
    }

    @Override
    public void setAngularVelocity(double angularVelocity) {
        throw new UnsupportedOperationException("This element cannot rotate, and so you shouldn't be calling"
                + "this method! If you want to find out whether it can rotate, use object.canRotate()");
    }

    @Override
    public void rotateForTime(double timestep) {
        throw new UnsupportedOperationException("This element cannot rotate, and so you shouldn't be calling"
                + "this method! If you want to find out whether it can rotate, use object.canRotate()");
    }

    @Override
    public void rotateToAngle(Angle newAngle) {
        throw new UnsupportedOperationException("This element cannot rotate, and so you shouldn't be calling"
                + "this method! If you want to find out whether it can rotate, use object.canRotate()");
    }

    @Override
    public Angle getAngle() {
        throw new UnsupportedOperationException("This element cannot rotate, and so you shouldn't be calling"
                + "this method! If you want to find out whether it can rotate, use object.canRotate()");
    }
    
    public Circle getCircle() {
        return circle;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof StaticCircle) {
            boolean sameCircle = this.circle.equals(((StaticCircle) other).getCircle());
            return sameCircle;
        }
        else {
            return false;
        }
    }

}
