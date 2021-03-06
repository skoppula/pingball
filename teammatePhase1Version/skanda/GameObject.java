package phase1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import phase1.Util.InvalidInvariantException;
import physics.Geometry.DoublePair;

/**
 * An abstract method that represents a game object
 * e.g. square bumper, flipper, circle bumper, absorber, etc... 
 *
 */

public abstract class GameObject {

    private int positionWidth;
    private int positionHeight;
    private double reflectionCoef;
    private List<GameObject> triggersThis = new ArrayList<GameObject>();

    /**
     * Adds a triggered objects to this's list of triggered objects
     * @param trigger = game objects that can be triggered by this
     */
    public void setTriggers(List<GameObject> triggers) {
        this.triggersThis = triggers;
    }
    
    /**
     * Sets initial position of game object
     * @param xCoord = x coordinate of origin
     * @param yCoord = y coordinate of origin
     */
    public void setPosition(int xCoord, int yCoord) {
        this.positionWidth = xCoord;
        this.positionHeight = yCoord;
    }
    
    /**
     * Set the reflection coefficient of a game object
     * @param reflection = the reflection coefficient
     */
    public void setCoefficient(double reflection) {
        this.reflectionCoef = reflection;
    }
    
    
    /**
     * @return a list of all the game objects that this triggers
     */
    public List<GameObject> getTriggers() {
        return Collections.unmodifiableList(triggersThis);
    }

    /**
     * 
     * @return the X coordinate of this game object
     */
    public int getX() {
        return positionWidth;
    }

    /**
     * 
     * @return the Y coordinate of this game object
     */
    public int getY() {
        return positionHeight;
    }
    
    /**
     * @return the reflection coefficient of this game object
     */
    public double getCoef() {
        return reflectionCoef;
    }
    
    /**
     * 
     * @param newX = the new X coordinate of this game object
     */
    public void setX(int newX) {
        this.positionWidth = newX;
    }

    /**
     * 
     * @param newY =  the Y coordinate of this game object
     */
    public void setY(int newY) {
        this.positionHeight = newY;
    }

    /**
     * @return string representation of game object
     */
    @Override
    public abstract String toString();
    
    /**
     * @param ball is the ball that collides with this game object
     * @return the time that the ball takes to collide with this game object
     */
    public abstract double timeUntilCollision(Ball ball);
    
    /**
     * This function triggers the reactions given by the collisions of the ball with this game object
     * @param ball is the ball that collides with this game object
     * @param the time that the ball takes to collide with this game object
     * @throws UnsupportedOperationException 
     * @throws InvalidInvariantException 
     */
    public abstract void reactWhenHit(Ball ball, double time) throws UnsupportedOperationException, InvalidInvariantException;
    
    /**
     * This function triggers the trigger action specific to this game object
     * @throws InvalidInvariantException 
     */
    public abstract void doTriggerAction() throws UnsupportedOperationException, InvalidInvariantException;
    
    /**
     * Function that maps the game object to a (x,y) coordinate
     * @param pointToObject is the hashMap of every (x,y) point to the object that exists there, if any
     */
    public abstract void putPoint(HashMap<DoublePair, GameObject> pointToObject);
    
    /**
     * Checks if two collision times are same within some threshold
     * @param collision time one
     * @param collision time two
     * @return whether or not the collision times are approximately equal
     */
    public static boolean collisionTimesEqual(double T1, double T2) {
        final double THRESHOLD = 0.02;
        return Math.abs(T1-T2) < THRESHOLD;
    }
    
    
    /**
     * Class for Unsupported Operation Exceptions
     */
    public static class UnsupportedOperationException extends Exception {
        
        // required by Java because this class implements Serializable
        private static final long serialVersionUID = 1L;
        
    }
}
