package phase2.PhysicsComponents;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * A class that wraps a LineSegment from the Physics Package
 */
/* A staticLine is valid if: 
 *      its lineSegment is inside of the boardMatrix 
 *      its lineSegment has size greater than 0
 */
public class StaticLine implements PhysicsComponent {
    LineSegment lineSegment;
    double reflectionCoefficient;
    
    /**
     * Creates a new StaticLine 
     * @param lineSegment the lineSegment that represents this staticLine. Must be inside the boardMatrix. 
     * @param reflectionCoefficient 
     */
    public StaticLine(LineSegment lineSegment, double reflectionCoefficient){
        this.lineSegment = lineSegment;
        this.reflectionCoefficient = reflectionCoefficient;
    }

    @Override
    public double timeUntilCollision(Circle ballCircle, Vect ballVelocity) {
        return Geometry.timeUntilWallCollision(lineSegment, ballCircle, ballVelocity);
    }

    @Override
    public Vect reflect(Circle ballCircle, Vect ballVelocity,
            double reflectionCoefficient) {
        return Geometry.reflectWall(lineSegment, ballVelocity, reflectionCoefficient*this.reflectionCoefficient);
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

}
