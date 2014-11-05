package phase2;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry.DoublePair;
import warmup.GridSymbol;

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
    @Override
    public abstract String toString();


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
        
    /*
     * Enum representing the four orientations for gadgets 
     */
    public enum Orientation {ZERO, NINETY, ONE_HUNDRED_EIGHTY, TWO_HUNDRED_SEVENTY};
    
    protected int getX() {
        return this.x;
    }

    protected int getY() {
        return this.y;
    }
}
