package warmup;
import java.util.ArrayList;
import java.util.List;

import physics.*;


/**
 * OuterWall is an immutable class that represents the outerwalls of the board. 
 * 
 */
/*
 * An OuterWall is valid if all of its components are valid and: 
 *  it has four linesegments that are outside the board 
 */
public class OuterWall extends Gadget{
    
    public enum WallDirection{ 
        LEFT, RIGHT, TOP, BOTTOM
    }
    
    /**
     * Creates a new OuterWall, at the face specified by direction.
     * the top and bottom sides include the corners adjacent to them.
     * left and right do not.
     * @param direction - one of LEFT, RIGHT, TOP, or BOTTOM
     */ 
    private OuterWall(WallDirection direction){
        double INNER_BOARD_LENGTH = BOARD_LENGTH - 2;
        coefficientOfReflection = 1.0;
        orientation = new Angle(0);
        
        this.isAction = false;
        
        switch(direction){
            case TOP:
            {
                location = new GridPoint(-1, -1); // each side includes the corner that
                // is counterclockwise with respect to it. For example, TOP would include the top left corner,
                // but not the top right.
                width = BOARD_LENGTH;
                height = 1;
                physicsComponentList.add(new StaticLine(new LineSegment(0, 0, INNER_BOARD_LENGTH, 0), coefficientOfReflection));
                physicsComponentList.add(new StaticCircle(new Circle(0, 0, 0.01), coefficientOfReflection));
                physicsComponentList.add(new StaticCircle(new Circle(INNER_BOARD_LENGTH, 0, 0.01), coefficientOfReflection));
            }
            break;
            case BOTTOM:
            {
                location = new GridPoint(-1, BOARD_LENGTH - 2);
                width = BOARD_LENGTH;
                height = 1;
                physicsComponentList.add(new StaticLine(new LineSegment(0, INNER_BOARD_LENGTH, INNER_BOARD_LENGTH, INNER_BOARD_LENGTH),
                        coefficientOfReflection));
                physicsComponentList.add(new StaticCircle(new Circle(0, INNER_BOARD_LENGTH, 0.01), coefficientOfReflection));
                physicsComponentList.add(new StaticCircle(new Circle(INNER_BOARD_LENGTH, INNER_BOARD_LENGTH, 0.01), coefficientOfReflection));
            }
            break;
            case LEFT:
            {
                location = new GridPoint(-1, 0);
                width = 1;
                height = BOARD_LENGTH - 2;
                physicsComponentList.add(new StaticLine(new LineSegment(0, 0, 0, INNER_BOARD_LENGTH), coefficientOfReflection));
            }
            break;
            case RIGHT:
            {
                location = new GridPoint(BOARD_LENGTH - 2, 0);
                width = 1;
                height = BOARD_LENGTH - 2;
                physicsComponentList.add(new StaticLine(new LineSegment(INNER_BOARD_LENGTH, 0, 
                        INNER_BOARD_LENGTH, INNER_BOARD_LENGTH), coefficientOfReflection));
            }
            break;
            default:
                throw new IllegalStateException("THIS SHOULDN'T BE POSSIBLE. CHECK OuterWall!");
        }
    }
    
    /**
     * Creates the four walls in a given table.
     * @return four walls, each in a different direction.
     */
    public static List<Gadget> makeWalls(){
        List<Gadget> returnList = new ArrayList<>();
        returnList.add(new OuterWall(WallDirection.TOP));
        returnList.add(new OuterWall(WallDirection.BOTTOM));
        returnList.add(new OuterWall(WallDirection.LEFT));
        returnList.add(new OuterWall(WallDirection.RIGHT));
        return returnList;
    }
    
    /**
     * OuterWall is represented by dots.
     */
    @Override
    public char getRepChar(){
        return '.';
    }

    @Override
    public void update(double timestep){
    }
    
    
}
