package phase2;

import java.util.HashMap;

import phase1.Util.InvalidInvariantException;
import physics.Circle;
import physics.Geometry;
import physics.Geometry.DoublePair;

public class CircleBumper extends GameObject {

    /**
     * Create a square-shaped bumper with these parameters:
     * @param xCoord = the x coordinate of the bumper's origin
     * @param yCoord = the y coordinate of the bumper's origin
     * @throws InvalidInvariantException 
     */
    public CircleBumper(int xCoord, int yCoord) throws InvalidInvariantException {
        setPosition(xCoord, yCoord);
        setCoefficient(1.0);
        if (!this.checkRep()) {
            throw new Util.InvalidInvariantException();
        }
    }

    /**
     * Function that checks the representation invariant of bumper
     * The bumper's origin must be within the board
     * @return a boolean that specifies whether or not the rep invariant is preserved
     */
    protected boolean checkRep() {
        if ( this.getX() < 0 || this.getY() < 0 || this.getX() >= Board.width || this.getY() >= Board.height ) {
            return false;
        }
        return true;
    }
    
    /**
     * @return the circle representation of the bumper
     */
    public Circle getCircle() {
        final double CIRCLE_RADIUS = 0.5;
        return new Circle(this.getX() + CIRCLE_RADIUS, this.getY() + CIRCLE_RADIUS, CIRCLE_RADIUS);
    }

    /**
     * @return string representation of the circle bumper
     */
    @Override
    public String toString() {
        return "o";
    }

    /**
     * @param ball is the ball that collides with this bumper
     * @return the time that the ball takes to collide with this bumper
     */
    public double timeUntilCollision(Ball ball) {
        double collisionTime = Geometry.timeUntilCircleCollision(
                this.getCircle(), ball.getCircle(), ball.getVelocity());
        return collisionTime;
    }

    /**
     * This function triggers the reactions given by the collisions of the ball with this bumper
     * @param ball is the ball that collides with this bumper
     * @param the time that the ball takes to collide with this bumper
     * @throws InvalidInvariantException 
     */
    @Override
    public void reactWhenHit(Ball ball, double time) throws UnsupportedOperationException, InvalidInvariantException {
        
        double CenterX = ball.getCenterX() + ball.getVelocity().x() * time;
        double CenterY = ball.getCenterY() + ball.getVelocity().y() * time;
        
        ball.updateCenterX(CenterX);
        ball.updateCenterY(CenterY);
        
        ball.updateVelocityWithGravityAndFriction(Geometry.reflectCircle(
                this.getCircle().getCenter(), ball.getCircle().getCenter(),
                ball.getVelocity(), this.getCoef()), time);
        
        for (GameObject obj : this.getTriggers()) {
            obj.doTriggerAction();
        }
    }

    /**
     * Function that maps the circleBumper to a (x,y) coordinate
     * @param pointToObject is the hashMap of every (x,y) point to the object that exists there, if any
     */
    public void putPoint(HashMap<DoublePair, GameObject> pointToObject) {
        pointToObject.put(new DoublePair((int) Math.floor(this.getX()),
                (int) Math.floor(this.getY())), this);
    }
    
    /**
     * This function triggers the trigger action specific to this bumper
     * @throws UnsupportedOperationException 
     */
    @Override
    public void doTriggerAction() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
