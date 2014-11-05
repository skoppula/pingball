package phase2;


import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry.DoublePair;

public abstract class Gadget {
    
    private double reflectionCoef;
    private GridPoint location; // the (x,y) coordinates of the top left bounding box of the gadget
    
    public Gadget(GridPoint location) {
        this.location = location;
    }

    /**
     * @return DoublePair representing the (width,height) of the Gadget on the Board
     */
    public DoublePair getBoardRepSize;
    
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
     * @return list of characters in the form of a String
     * The characters represent the string representation of
     * each 1L x 1L space that the gadget is in
     * from top to bottom, left to right
     */
    @Override
    public abstract String toString();


    
    /**
     * Triggers any actions on other Gadgets that it may need to
     */
    public abstract void trigger();

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
}