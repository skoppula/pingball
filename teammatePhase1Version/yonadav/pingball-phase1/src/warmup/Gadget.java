package warmup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import physics.Angle;
import physics.Vect;

/**
 * Gadgets represent objects in the playing area that interact with the game.
 * They can be Bumpers, Flippers, Walls or Absorbers
 * Interactions between specific gadgets with other gadgets and with the ball are 
 * described in more detail in the individual classes of gadgets
 *
 */
public abstract class Gadget {
    
    // All gadgets should be immutable. Location, though Vect contains doubles, should always be in integer form.
    /*
     * All gadgets are mutable
     * Orientation: Default set to 0
     * Location: GridPoints representing location must be within the bounds:
     *         - 0 <= x < x + width <= 20
     *         - 0 <= y < y + height <= 20
     */
    
    /*
     * Multiplier applied to the magnitude of the ball's velocity after it bounces off the gadget
     * CoefficientOfReflection 1.0: means two colliding objects leave the collision with the same velocity
     *                              in a different direction
     * Coefficient of Reflection < 1.0 damp ball's velocity
     * Coefficient of Reflection > 1.0 increase ball's velocity                             
     */
    protected double coefficientOfReflection;
    
    /*
     * Determines clockwise rotation from it's default orientation
     * Default orientation is represented as 0 degrees
     * Rotation occurs around the center of the bounding box of the Gadget
     */
    protected Angle orientation;
    
    /*
     * (x,y) GridPoint coordinates representing the location of the Gadget
     * The coordinates represent to the top left position of the bounding box 
     */
    protected GridPoint location;
    
    /*
     * Width of bounding box (in units distance) 
     */
    protected int width;
    
    /*
     * Height of bounding box (in units distance)
     */
    protected int height;
    
    /*
     * List of physics components that make the gadget
     */
    protected List<PhysicsComponent> physicsComponentList = new ArrayList<>();
    
    /*
     * Length of board, including outer wall (in units distance)
     */
    protected int BOARD_LENGTH = 22;
    
    /*
     * List of Gadgets that are hooked up to this gadget's trigger
     */
    protected Set<Gadget> gadgetsToActivate = new HashSet<>(); 
    
    /*
     * boolean representing whether the gadget is performing its action
     */
    protected boolean isAction = false;
    
    /**
     * Run the updates that this Gadget goes through in a given time step.
     */
    public abstract void update(double timestep);
    
    /**
     * @return the character that this Gadget is represented by.
     */
    public abstract char getRepChar();
    
    /**
     * Activate a gadget 
     */
    public void activate(){
        isAction = true;
    }
    
    /**
     * return a list of the Gadgets that its trigger is hooked up to
     */
    public Set<Gadget> getGadgetsToActivate(){
        return new HashSet<>(this.gadgetsToActivate);
    }
    
    /**
     * sets list of gadgets that its trigger is hooked up to
     */
    public void setGadgetsToActivate(Set<Gadget> setOfGadgets){
        this.gadgetsToActivate = new HashSet<>(setOfGadgets);
    }
    
    /**
     * Modifies ball position and velocity after a collision
     * @param ball - an instance of Ball that is colliding with this gadget
     * @param timeUntilCollision - time until the ball is going to collide with the this gadget.
     */
    public void collide(Ball ball){
        List<PhysicsComponent> gadgetParts = this.getPhysicalRep();
        PhysicsComponent gadgetPartToCollideWith = gadgetParts.get(0);
        double minTimeUntilCollision = Double.MAX_VALUE;
        for(PhysicsComponent gadgetPart: gadgetParts){
            double timeUntilCollisionPart = gadgetPart.timeUntilCollision(ball.getBallCircle(), ball.getVelocity());
            if (timeUntilCollisionPart < minTimeUntilCollision){ 
                minTimeUntilCollision = timeUntilCollisionPart;
                gadgetPartToCollideWith = gadgetPart;
            }
        }
        Vect newVelocity = gadgetPartToCollideWith.reflect(ball.getBallCircle(), ball.getVelocity(), ball.getCoefficentOfReflection()); 
        ball.setVelocity(newVelocity);
    }
    
    /**
     * Provides a representation of the gadget's physical structure, using components
     * from the physics library (including LineSegment and Circle).
     * @return a list of components that compose the gadget, in no particular order.
     */
    public List<PhysicsComponent> getPhysicalRep(){
        List<PhysicsComponent> returnList = new ArrayList<>();
        for(PhysicsComponent element: this.physicsComponentList){
            returnList.add(element);
        }
        return returnList;
    }
    
    /**
     * The visual representation of a gadget.
     * @return the symbols to be placed on a BoardMatrix.
     */
    public List<GridSymbol> getGridSymbolRep(){
        List<GridSymbol> symbolList = new ArrayList<>();
        for(int i=0; i<this.width; i++){
            for(int j=0; j<this.height; j++){
                symbolList.add(new GridSymbol(location.getX() + i, location.getY() + j, getRepChar()));
            }
        }
        return symbolList;
    }
    
    /**
     * @return coefficientOfReflection of the gadget
     */
    public double getCoefficientOfReflection(){
        return coefficientOfReflection;
    }
}
