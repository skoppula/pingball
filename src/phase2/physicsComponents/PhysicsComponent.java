package phase2.physicsComponents;
import physics.*;

/**
 * Provides a wrapper for parts in the physics library
 * so that we can treat them all with the same set of methods.
 * Classes that extend this physics component can be mutable
 */
public interface PhysicsComponent {
    
    /**
     * 
     * @return true if the object is able to rotate, false otherwise
     */
    public boolean canRotate();
    
    /**
     * If the object has an angular velocity, can set that angular velocity.
     * @param angularVelocity - the velocity to be set, in radians per second
     */
    public void setAngularVelocity(double angularVelocity);
    
    /**
     * Rotates the object, at the angular velocity specified earlier, by timestep*angularVelocity degrees
     * Can never be used by an object that cannot rotate!
     * @param timestep - the time the object should rotate, in seconds
     */
    public void rotateForTime(double timestep);
    
    /**
     * Rotates the rotating object to an angle newAngle
     * Can never be used by an object that cannot rotate!
     * @param newAngle - the new angle that this component will be rotated to.
     */
    public void rotateToAngle(Angle newAngle);
    

    
    /**
     * Can never be used by an object that cannot rotate!
     * @return the angle, between with respect to north, going clockwise.
     */
    public Angle getAngle();
    
    /**
     * Provides the time until the ball will collide with the PhysicsComponent.
     * 
     * @param ballCircle - the Circle describing the physical location of the ball
     * @param ballVelocity - the velocity of the ball currently, in distance units per second.
     * @return the time in seconds until the ball will collide with PhysicsComponent
     */
    public double timeUntilCollision(Circle ballCircle, Vect ballVelocity);
    
    /** Calculates the new velocity of a ball after colliding with this PhysicsComponent
     * 
     * @param ballCircle - the Circle describing the physical location of the ball
     * @param ballVelocity - the velocity of the ball before collision, in distance units per second
     * @param reflectionCoefficient - the reflection coefficient of the collision
     * @return the velocity vector (in units distance per second) that the ball will be moving at
     * after the collision.
     */
    public Vect reflect(Circle ballCircle, Vect ballVelocity, double reflectionCoefficient);
}
