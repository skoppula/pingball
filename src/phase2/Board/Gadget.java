package phase2.Board;


import java.util.ArrayList;
import java.util.List;

import phase2.PhysicsComponents.PhysicsComponent;

/**
 * The abstract class representing every type of gadget placed on a board.
 * It is theoretically mutable if people call setTriggers to change the gadgets triggered
 * by this gadget. However, as is detailed in that method's spec, it should never be called
 * other than immediately after the gadget is initialized. Otherwise it is immutable.
 */
public abstract class Gadget implements Collidable{
	/*
	 * Rep invariant:
	 * gadgetsToTrigger should not contain duplicates
	 * location should be inside the board (x and y should be from -1 to 20)
	 * width and height must be between 1 and 22
	 */
    
    protected List<PhysicsComponent> physicsComponentList = new ArrayList<>();
    protected String name;
    /**
     * the gadget's coefficient of reflection
     */
    protected final double reflectionCoef;
    protected List<Gadget> gadgetsToTrigger = new ArrayList<>();
    protected final GridPoint location; // the (x,y) coordinates of the top left bounding box of the gadget\
    protected final int width;
    protected final int height;
    
    public Gadget(GridPoint location, String name, int width, int height, double reflectionCoef) {
        this.location = location;
        this.name = name;
        this.width = width;
        this.height = height;
        this.reflectionCoef = reflectionCoef;
    }


    /**
     * Set this gadget to trigger the following objects.
     * Note that this method should only ever be called once, immediately after the gadget constructor,
     * and before the initialization of the gadget's board.
     * @param lst
     */
    public void setTriggers(List<Gadget> lst) {
        this.gadgetsToTrigger = new ArrayList<>(lst);
    }
    

    /**
     * @return a string which identifies this object (usually a name)
     */
    @Override
    public String toString(){
    	return name;
    }


    /**
     * 
     * @return the name of the gadget.
     */
    public String getName(){
    	return name;
    }
    
    /**
     * Triggers any actions on other Gadgets that it may need to
     */
    public void trigger() {
        for (Gadget gadget: gadgetsToTrigger) {
            gadget.action();
        }
    }

    /**
     * Performs the action of the gadget
     */
    public abstract void action();
    
    /**
     * @param deltaTime the time frame over which to update the gadget
     */
    public abstract void updateGadgetPosition(double timeDelta);
    

    
    /**
     * @return character representing one space of that Gadget
     */
    public abstract char charRep();
    
    /**
     * The visual representation of a gadget.
     * @return the symbols representing this gadget, as a function of location, width, and height.
     */
    public List<GridSymbol> getSymbolRep(){
        List<GridSymbol> symbolList = new ArrayList<>();
        for(int i=0; i<this.width; i++){
            for(int j=0; j<this.height; j++){
                symbolList.add(new GridSymbol(location.getX() + i, location.getY() + j, charRep()));
            }
        }
        return symbolList;
    }
        
    /*
     * Enum representing the four orientations for gadgets 
     */
    public enum Orientation {ZERO, NINETY, ONE_HUNDRED_EIGHTY, TWO_HUNDRED_SEVENTY};
    
    protected int getX() {
        return this.location.getX();
    }

    protected int getY() {
        return this.location.getY();
    }
    
    
    /**
     * Returns physics components list
     */
    public List<PhysicsComponent> getPhysicsComponents() {
        return physicsComponentList;
    }
    
    /**
     * Checks whether gadgets are equal by checking whether
     * their physics components are equal
     * @return 
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Gadget) {
            boolean sameSizeList = this.physicsComponentList.size() == ((Gadget) other).getPhysicsComponents().size();
            return sameSizeList && this.physicsComponentList.containsAll(((Gadget) other).getPhysicsComponents());
            
        }
        else {
            return false;
        }
    }
    
    /**
     * Returns hashcode of the gadget name
     * @return 
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
