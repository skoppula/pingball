package warmup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.LineSegment;

/**
 * Triangle Bumper is a right-triangular shaped bumper.
 * Default orientation 
 *      - places one corner in NE, one in NW, and one in SW
 *      - diagonal goes from SW corner to NE corner
 */
public class TriangleBumper extends Gadget {
    /*
     * Rep Invariant
     *     - sidelength is 1L, diagonal length is Sqrt(2)L
     *     - width, height = 1
     *     - location:
     *         - 0 <= x < x + width <= 19
     *         - 0 <= y < y + height <= 19
     */
    
    double DEGREES_TO_RADIANS = Math.PI/180.;
    
    /**
     * Creates a TriangleBumper.
     * @param x - x location of top-left corner
     * @param y - y location of top-left corner
     * @param orientation - determines which way 90 degree corner will point.
     * Default orientation (0 degrees) means that corner is in top left.
     * Any rotation is clockwise. For now, orientation must be 0, 90, 180, or 270.
     */
    public TriangleBumper(int x, int y, Angle orientation){
        this.coefficientOfReflection = 1.0;
        this.orientation = orientation;
        this.location = new GridPoint(x, y);
        this.width = 1;
        this.height = 1;
        
        this.isAction = false;
        this.gadgetsToActivate = new HashSet<Gadget>();
        
        double dx = (double) x;
        double dy = (double) y;
        if(orientation.equals(Angle.ZERO)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy + 1, 0.01), coefficientOfReflection));
        }
        else if(orientation.equals(Angle.DEG_90)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy + 1, 0.01), coefficientOfReflection));
        }
        else if(orientation.equals(Angle.DEG_180)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy + 1, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx + 1, dy, dx, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy + 1, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy + 1, 0.01), coefficientOfReflection));
        }
        else if(orientation.equals(Angle.DEG_270)){
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy + 1, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticLine(new LineSegment(dx, dy, dx + 1, dy + 1), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx, dy + 1, 0.01), coefficientOfReflection));
            this.physicsComponentList.add(new StaticCircle(new Circle(dx + 1, dy + 1, 0.01), coefficientOfReflection));
        }
        else{
            throw new IllegalStateException("TriangleBumper's orientation must be 0, 90, 180, or 270. Was (in radians): "
                    + orientation.toString());
        }
    }

    /**
     * TriangleBumper has no update functionality.
     */
    @Override
    public void update(double timestep) {
    }

    /**
     * TriangleBumper's representative character depends on its orientation.
     * If orientation = 0 or 180, the representation is '/'
     * If orientation = 90 or 270, the representation is '\'
     * Other orientations are not supported.
     */
    @Override
    public char getRepChar() {
        if(orientation.equals(Angle.ZERO) || orientation.equals(Angle.DEG_180)){
            return '/';
        }
        else if(orientation.equals(Angle.DEG_90) || orientation.equals(Angle.DEG_270)){
            return '\\';
        }
        else{
            throw new IllegalStateException("TriangleBumper's orientation must be 0, 90, 180, or 270. Was (in radians): "
                    + orientation.toString());
        }
    }

        

}
