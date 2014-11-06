package warmup;

import java.util.ArrayList;

import physics.Angle;
import physics.LineSegment;
import physics.Circle;


/*
 *   Rep Invariant: a square bumper is valid if it has 
 *   # as its character 
 *   And its location is inside of the grid (contained in a bounding box: (0,0) to (19,19)) 
 *   And it is made up of four StaticLines and four StaticCircles at the ends of those lines
 *
 */
/**
 * An immutable class that represents an immutable squareBumper  
 */
public class SquareBumper extends Gadget {
  
    /**
     * creates a new Square bumper 
     * @param x must be a valid x that is inside the boardMatrix
     * @param y must be a valid y that is inside the boardMatrix
     */
    public SquareBumper(int x, int y){
        this.coefficientOfReflection = 1.0;
        this.orientation = new Angle(0);
        this.location = new GridPoint(x, y);
        this.width = 1;
        this.height = 1;
        
        this.isAction = false;
        
        double dx = (double)x;
        double dy = (double)y;
        this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy), coefficientOfReflection));
        this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx, dy + 1), coefficientOfReflection));
        this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx + 1, dy + 1), coefficientOfReflection));
        this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy + 1, dx + 1, dy + 1), coefficientOfReflection));
        this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy, .01), coefficientOfReflection));
        this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy, 0.01), coefficientOfReflection));
        this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy + 1, 0.01), coefficientOfReflection));
        this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy + 1, 0.01), coefficientOfReflection));
    }

    /**
     * SquareBumper does nothing when updating.
     */
    @Override
    public void update(double timestep) {
    }

    /**
     * SquareBumper is represented by a poundSign.
     */
    @Override
    public char getRepChar() {
        return '#';
    }

}
