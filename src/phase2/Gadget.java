package phase2;


import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry.DoublePair;

public abstract class Gadget implements Collidable{
    

    protected String name;
    protected final double reflectionCoef;
    protected ArrayList<Gadget> gadgetsToTrigger;
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

    protected void setTriggers(ArrayList<Gadget> lst) {
        this.gadgetsToTrigger = lst;
    }
    
    /**
     * @return DoublePair representing the (width,height) of the Gadget
     */
    public DoublePair getSize;
    
    /**
     * @return the gadget's coefficient of reflection
     */
    public double coefficientOfReflection;
    
    /**
     * @return the x-coordinate of the gadget's origin
     */
    

    /**
     * @return a string which identifies this object (usually a name)
     */
    @Override
    public String toString(){
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
     * Adds the gadgets that this Gadget should trigger to gadgetsToTrigger
     */
    public void setGadgetsToTrigger(ArrayList<Gadget> gadgetsToTrigger) {
        this.gadgetsToTrigger = gadgetsToTrigger;
    }
    
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
}
