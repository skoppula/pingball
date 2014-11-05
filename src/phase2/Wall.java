package phase2;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Geometry.DoublePair;

public class Wall extends Gadget {
    
    private final LineSegment wall;
    private final DoublePair position;
    private final DoublePair size;
    
    public Wall(Orientation orientation) {
        switch (orientation) {
            case NINETY:    
                wall = new LineSegment(0, 0, 20, 0);
                position = new DoublePair(-1, -1);
                size = new DoublePair(22, 1);
                break;
            case ONE_HUNDRED_EIGHTY:
                wall = new LineSegment(20, 0, 20, 20);
                position = new DoublePair(20, -1);
                size = new DoublePair(1, 22);
                break;
            case TWO_HUNDRED_SEVENTY:
                wall = new LineSegment(0, 20, 20, 20);
                position = new DoublePair(-1, 20);
                size = new DoublePair(22, 1);
                break;
            default: //case ZERO
                wall = new LineSegment(0, 0, 0, 20);
                position = new DoublePair(-1, -1);
                size = new DoublePair(1, 22);
                break;
        }
    }
    
    /**
     * Returns time until ball collides with Wall, infinity if never 
     */
    @Override
    public double getTimeUntilCollision(Ball ball) {
        return Geometry.timeUntilWallCollision(wall, ball.getGirth(), ball.getVelocity());
    }

    /**
     * Mutates the ball object as necessary to represent the effect of the collision with the Wall
     * Only mutates the ball's position if the interaction with the Wall is not a standard reflection
     * @param ball that the collision will happen with
     */
    @Override
    public void collision(Ball ball) {        
        Vect velocity = ball.getVelocity();

        ball.setVelocity(Geometry.reflectWall(wall, velocity));
        return;        
    }

    /**
     * Returns the size of the Wall (width, height)
     */
    @Override
    public DoublePair getBoardRepSize() {
        return size;
    }
    
    /**
     * Returns the size of the Wall (width, height)
     */
    @Override
    public DoublePair getSize() {
        return size;
    }

    /**
     * Returns the position of the Wall on the board
     */
    @Override
    public DoublePair getBoardRepPosition() {
        return position;
    }

    /**
     * Triggers any other associated Gadgets
     */
    @Override
    public void trigger() {
        return;        
    }

    /**
     * Does nothing as walls have no action
     */
    @Override
    public void action() {
        return;
    }    
    
    @Override
    public char charRep() {
        return '.';
    }
    
    @Override
    public void updateGadgetPosition(double timeDelta) {}
    
    @Override
    public String toString(){
        return "Wall";
    }
}
