package phase2;

import physics.Geometry.DoublePair;

public abstract class Gadget {

    /**
     * @return DoublePair representing the (width,height) of the Gadget on the Board
     */
    public DoublePair getBoardRepSize;
    
    /**
     * @return DoublePair representing the (width,height) of the Gadget
     */
    public DoublePair getSize;

    /**
     * @return list of characters in the form of a String
     * The characters represent the string representation of
     * each 1L x 1L space that the gadget is in
     * from top to bottom, left to right
     */
    public String toString;

    /**
     * @return DoublePair representing (x,y) coordinates of the Gadget on the board
     */
    public DoublePair getBoardRepPosition;
    
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
    public char charRep();
        
    /*
     * Enum representing the four orientations for gadgets 
     */
    public enum Orientation {ZERO, NINETY, ONE_HUNDRED_EIGHTY, TWO_HUNDRED_SEVENTY};
}
