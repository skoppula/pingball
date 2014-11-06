package warmup;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * An absorber is a gadget that absorbs any balls that collide with it, and launches
 * them out when triggered.
 *
 */
public class Absorber extends Gadget {
    //Rep Invariant: An absorber is a valid absorber if: 
    //it has a coefficient of Reflection of .95
    //is located inside the BoardMatrix 
    
    private List<Ball> containedBalls = new ArrayList<>();
    // Balls currently stored in the absorber.
    private Ball ballCurrentlyBeingLaunched;
    // The ball most recently launched through the absorber.
    private boolean isLaunching = false;
    
    /**
     * Creates a new absorber gadget.
     * Note that the following invariants must be maintained:
     * 0 <= x < x + width <= 19
     * 0 <= y < y + width <= 19
     * @param x - the x location of the top left corner
     * @param y - the y location of the top left corner
     * @param width - the number of tiles in the x direction that our absorber extends
     * @param length - the number of tiles in the y direction that our absorber extends
     */
    public Absorber(int x, int y, int width, int length){
        this.coefficientOfReflection = 0.95; // doesn't matter
        this.orientation = new Angle(0);
        this.location = new GridPoint(x, y);
        this.width = width;
        this.height = length;
        this.isAction = false;
        
        ballCurrentlyBeingLaunched = new Ball(location.getX() + width - .25, location.getY() + height - .25, new Vect(0, 0));
        // The ball most recently launched by the absorber is initially an arbitrary ball not present in our simulation.
        
        this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x + width, y), coefficientOfReflection));
        this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x, y + height), coefficientOfReflection));
        this.physicsComponentList.add(new StaticLine(new LineSegment(x + width, y, x + width, y + height), coefficientOfReflection));
        this.physicsComponentList.add(new StaticLine(new LineSegment(x, y + height, x + width, y + height), coefficientOfReflection));
        this.physicsComponentList.add(new StaticCircle(new Circle(x, y, 0.01), coefficientOfReflection));
        this.physicsComponentList.add(new StaticCircle(new Circle(x + width, y, 0.01), coefficientOfReflection));
        this.physicsComponentList.add(new StaticCircle(new Circle(x, y + height, 0.01), coefficientOfReflection));
        this.physicsComponentList.add(new StaticCircle(new Circle(x + width, y + height, 0.01), coefficientOfReflection));
    }

    /**
     * Stores a new ball in the absorber.
     * @param ball
     */
    private void insertBall(Ball ball){
        containedBalls.add(ball);
    }
    
    /**
     * Remove the first ball from the absorber.
     * Note that the number of balls in the absorber MUST BE GREATER THAN 0.
     * @return the ball removed from the absorber.
     */
    private Ball popBall(){
        return containedBalls.remove(0);
    }
    
    /**
     * If the absorber is triggered, eject a ball from the absorber 
     * (unless there are no balls, or a ball is currently being launched).
     * @param timestep - the amount of time to elapse, in seconds
     */
    @Override
    public void update(double timestep) {
        final double EJECTION_VELOCITY = -47.2;
        if(isAction){
            if(this.containedBalls.size() != 0 && ! isLaunching){ 
                // if we have at least one ball to push out, and the most recently launched ball is already out
                // (can collide with the environment)
                isLaunching = true;
                Ball ball = popBall(); // pick our ball
                ball.allowUpdates(); // let it be moved in Board
                ballCurrentlyBeingLaunched = ball; // remember which ball is currently being launched
                ball.setVelocity(new Vect(0, EJECTION_VELOCITY)); // set it free!
            }
            isAction = false; // and release our trigger
        }
    }
    
    @Override
    /**
     * Stores the colliding ball in the absorber, unless the ball is currently being launched, in
     * which case it should pass through uninhibited.
     * @param ball - ball to be absorbed
     */
    public void collide(Ball ball){
        if(ball == ballCurrentlyBeingLaunched && isLaunching){ // if this is the ball that was most recently launched, and thus must be colliding from the underside
            ball.setWhetherTeleporting(true);
            ball.setGadgetBeingTeleportedThrough(this);
            // also push the ball a bit higher, because otherwise it won't advance forward
            isLaunching = false;
        }
        else{
            insertBall(ball); // absorb the ball
            ball.setBallCircle(new Circle(location.getX() + width - .25, location.getY() + height - .25, .25));
            //place it in the corner
            ball.disallowUpdates();
        }
    }
    
    @Override
    public char getRepChar() {
        return '=';
    }


}
