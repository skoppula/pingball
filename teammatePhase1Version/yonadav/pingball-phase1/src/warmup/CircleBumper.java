package warmup;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;

/*
 *  RepInvariant: A CircleBumper is valid if: 
 *  it has a valid gridpoint 
 *
 */
/**
 * A class that represents an immutable CircleBumper
 */
public class CircleBumper extends Gadget {

    /**
     * Creates a new CircleBumper
     * @param x valid x to place the Circle Bumper. Must be inside the boardMatrix
     * @param y valid y to place the Circle Bumper. Must be inside the boardMatrix
     */
    public CircleBumper(int x, int y){
        this.coefficientOfReflection = 1.0;
        this.orientation = new Angle(0);
        this.location = new GridPoint(x, y);
        this.width = 1;
        this.height = 1;
        this.isAction = false;
        this.physicsComponentList.add(new StaticCircle(new Circle((double)x + .5, (double)y + .5, .5), coefficientOfReflection));
    }

    @Override
    public void update(double timestep) {
    }


    /**
     * CircleBumper is represented by a '0'
     */
    @Override
    public char getRepChar() {
        return '0';
    }
}
