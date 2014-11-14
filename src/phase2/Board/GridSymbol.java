package phase2.Board;

import physics.Vect;

/**
 * An immutable class to represent a grid symbol
 */
public class GridSymbol {
	/*
	 * Abstraction function: a grid pixel with an associated character
	 */
	
    private final char c;
    // the character representing of this symbol
    private final GridPoint position;
    // the location of this symbol
    
    /**
     * @param x x-coordinate of upper left corner of the bounding box
     * @param y y-coordinate of upper left corner of the bounding box
     * @param c character representation of the symbol
     */
    public GridSymbol(int x, int y, char c){
        position = new GridPoint(x,y);
        this.c = c;
    }
    

    /**
     * 
     * @param v Vector representing the x and y coordinates of the upper 
     *             left corner of the bounding box
     * @param c character representation of the symbol
     */
    public GridSymbol(Vect v, char c){
        position = new GridPoint((int)v.x(), (int)v.y());
        this.c = c;
    }
    
    /**
     * @return x-coordinate of upper left corner of the bounding box
     */
    public int getX(){
        return position.getX();
    }
    
    /**
     * @return x-coordinate of upper left corner of the bounding box
     */
    public int getY(){
        return position.getY();
    }
    
    /**
     * @return return character representation of the symbol
     */
    public char getChar(){
        return c;
    }
    
    /**
     * @return String representation of GridSymbol
     */
    @Override
    public String toString(){
        return "Position x = " + this.getX() + " y = " + this.getY() + " character = " + this.getChar();
    }

}
