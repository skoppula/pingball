package phase2.PhysicsComponents;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.Vect;

/**
 * A wrapper class for a circle that is rotating 
 */
/* 
 * A RotatingCircle is valid if it has: 
 *  a valid circle for its location- a circle is valid if it is inside of the grid and if it is not at (0,0)
 */
public class RotatingCircle implements PhysicsComponent {
    private Circle circle;
    final private Vect pivotPoint;
    private double angularVelocity;
    private final double reflectionCoefficient;
    private Angle angle;
    
    
    /**
     * Constructs a new rotatingCircle
     * 
     * @param circle a circle representing the location and size of the RotatingCircle to be created. Cannot be created at (0,0) or have size 0 
     * @param newPivotPoint A vector that represents where the rotating circle is going to pivot around 
     * @param angularVelocity The angular velocity of the circle around its pivot point clockwise in rad/second
     * @param reflectionCoefficient The reflectionCoefficient of the RotatingCircle to be created 
     */
    public RotatingCircle(Circle circle, Vect newPivotPoint, double angularVelocity, double reflectionCoefficient){
        this.circle = circle;
        this.pivotPoint = newPivotPoint;
        this.angularVelocity = angularVelocity;
        this.reflectionCoefficient = reflectionCoefficient;
        double angleFromXAxis = Math.atan2(circle.getCenter().y() - pivotPoint.y(), circle.getCenter().x() - pivotPoint.x());
        this.angle = new Angle(.5*Math.PI + angleFromXAxis);
    }

    @Override
    public double timeUntilCollision(Circle ballCircle, Vect ballVelocity) {
        return Geometry.timeUntilRotatingCircleCollision(circle, pivotPoint, angularVelocity, ballCircle, ballVelocity);
    }

    @Override
    public Vect reflect(Circle ballCircle, Vect ballVelocity,
            double reflectionCoefficient) {
        return Geometry.reflectRotatingCircle(circle, pivotPoint, angularVelocity, ballCircle, ballVelocity, this.reflectionCoefficient*reflectionCoefficient);
    }

    @Override
    public boolean canRotate() {
        return true;
    }
    
    /**
     * Specify the clockwise angular velocity in radians per second that the circle will rotate
     */
    public void setAngularVelocity(double angularVelocity){
        this.angularVelocity = angularVelocity;
    }
    
    /**
     * Rotates the rotating circle clockwise, at the angular velocity specified earlier, by timestep*angularVelocity degrees
     * @param timestep - the time the line has been rotating, in seconds
     */
    public void rotateForTime(double timestep){
        this.rotateToAngle(angle.plus(new Angle(timestep*this.angularVelocity)));
    }
    
    /**
     * Rotates the rotating circle clockwise to an angle newAngle
     * @param newAngle - the new angle that this component will be rotated to, in radians.
     */
    public void rotateToAngle(Angle newAngle){
        Angle angleToRotate = newAngle.minus(this.angle);
        this.circle = Geometry.rotateAround(circle, pivotPoint, angleToRotate);
        angle = newAngle;
    }
    
    /**
     * 
     * @return the angle, between with respect to north, going clockwise.
     */
    public Angle getAngle(){
        return angle;
    }
    
    public Circle getCircle() {
        return circle;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }
    
    public Vect getPivotPoint() {
        return pivotPoint;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof RotatingCircle) {
            boolean sameCircle = this.circle.equals(((RotatingCircle) other).getCircle());
            boolean samePivot = this.pivotPoint.equals(((RotatingCircle) other).getPivotPoint());
            boolean sameAngularVelocity = this.angularVelocity == ((RotatingCircle) other).getAngularVelocity();
            boolean sameAngle = this.angle.equals(((RotatingCircle) other).getAngle());
            return sameCircle && sameAngularVelocity && samePivot && sameAngle;
        }
        else {
            return false;
        }
    }

}
