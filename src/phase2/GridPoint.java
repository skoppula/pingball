package warmup;

/**
 * An immutable class to represent a point on a board grid.
 *
 */
/*
 * RepInvariant: must be inside the boardMatrix in order to be a valid gridpoint 
 */
public class GridPoint {
    
    private final int x;
    private final int y;
    
    /**
     * Creates a new Gridpoint at coordinate (x,y) 
     * @param x a valid X coordinate that is inside the boardMatrix
     * @param y a valid y coordinate that is inside the boardMatrix
     */
    public GridPoint(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return the X coordinate 
     */
    public int getX(){
        return x;
    }
    
    /**
     * @return the Y coordinate of this GridPoint
     */
    public int getY(){
        return y;
    }

}
