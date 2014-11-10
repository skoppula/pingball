package phase2.PhysicsComponents;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * A rotating line is valid if: 
 *  its lineSegment is contained inside the boardMatrix and is greater than size 0 
 */
public class RotatingLine implements PhysicsComponent {
    LineSegment lineSegment;
    Vect center;
    double angularVelocity;
    double reflectionCoefficient;
    Angle angle;
    
    /**
     * Creates a new RotatingLine 
     * @param lineSegment 
     * @param center
     * @param angularVelocity
     * @param reflectionCoefficient
     */
    public RotatingLine(LineSegment lineSegment, Vect center, double angularVelocity, double reflectionCoefficient){
        this.lineSegment = lineSegment;
        this.center = center;
        this.angularVelocity = angularVelocity;
        this.reflectionCoefficient = reflectionCoefficient;
        Angle angleFromXAxis = lineSegment.angle();
        this.angle = new Angle(.5*Math.PI + angleFromXAxis.radians());
    }
    
    /**
     * Specify the clockwise angular velocity in radians per second that the line will rotate
     */
    @Override
    public void setAngularVelocity(double angularVelocity){
        this.angularVelocity = angularVelocity;
    }
    
    /**
     * Rotates the rotating line clockwise, at the angular velocity specified earlier, by timestep*angularVelocity degrees
     * @param timestep - the time the line has been rotating, in seconds
     */
    @Override
    public void rotateForTime(double timestep){
        this.rotateToAngle(angle.plus(new Angle(timestep*this.angularVelocity)));
    }
    
    /**
     * Rotates the rotating line clockwise to an angle newAngle
     * @param newAngle - the new angle that this component will be rotated to, in radians.
     */
    public void rotateToAngle(Angle newAngle){
        Angle angleToRotate = newAngle.minus(this.angle);
        this.lineSegment = Geometry.rotateAround(lineSegment, center, angleToRotate);
        angle = newAngle;
    }
    
    /**
     * 
     * @return the angle, between with respect to north, going clockwise.
     */
    public Angle getAngle(){
        return angle;
    }

    @Override
    public double timeUntilCollision(Circle ballCircle, Vect ballVelocity) {
        return Geometry.timeUntilRotatingWallCollision(lineSegment, center, angularVelocity, ballCircle, ballVelocity);
    }

    @Override
    public Vect reflect(Circle ballCircle, Vect ballVelocity,
            double reflectionCoefficient) {
        return Geometry.reflectRotatingWall(lineSegment, center, angularVelocity, ballCircle, ballVelocity, this.reflectionCoefficient*reflectionCoefficient);
    }

    @Override
    public boolean canRotate() {
        return true;
    }

}
