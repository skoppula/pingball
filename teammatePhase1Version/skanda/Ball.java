package phase1;

import java.util.HashMap;

import phase1.Util.InvalidInvariantException;
import physics.Circle;
import physics.Geometry;
import physics.Vect;
import physics.Geometry.DoublePair;

public class Ball extends GameObject {

    private Circle circle;
    private Vect velocity;
    protected double mu2 = 0.025;
    protected boolean inAbsorber = false;
    
    /**
     * Creates a ball object with these parameters:
     * @param xCoord - the x coordinate of the ball
     * @param yCoord - the y coordinate of the ball
     * @param radius - the radius of the ball
     * @param velocity - the velocity of the ball
     * @throws InvalidInvariantException 
     */
    public Ball(double xCoord, double yCoord, double radius, Vect velocity) throws InvalidInvariantException {
        this.circle = new Circle(xCoord, yCoord, radius);
        this.velocity = velocity;
        if (!this.checkRep()) {
            throw new Util.InvalidInvariantException();
        }
    }
    
    /**
     * Function that checks the representation invariant of ball
     * The ball's origin must be within the board
     * @return a boolean that specifies whether or not the rep invariant is preserved
     */
    protected boolean checkRep() {
        if ( this.getX() < 0 || this.getY() < 0 || this.getX() >= Board.width || this.getY() >= Board.height ) {
            return false;
        }
        return true;
    }
    
    /**
     * Check if the ball is held static in the absorber
     * @return boolean with whether or not the ball is captured
     */
    public boolean inAbsorber() {
        return this.inAbsorber;
    }
    
    /**
     * Function that switches the state of the ball:
     *  - if the ball is in the absorber, then toggle it to not be in the absorber anymore
     *  - if the ball is not in the absorber, then toggle it to be there
     */
    public void toggleInAbsorber() {
        this.inAbsorber = !this.inAbsorber;
    }

    /**
     * Changes the friction coefficient of ball
     * @param newMu2 - new mu2 coefficient as described in the handout
     */
    public void setMu2(double newMu2) {
       this.mu2 = newMu2;
    }
    
    /**
     * Update x coordinate of center
     * @param newX - new x coordinate of center
     * @throws InvalidInvariantException 
     */
    public void updateCenterX(double newX) throws InvalidInvariantException {
        if (newX < 0)
            newX = 0;
        if (newX > Board.width)
            newX = 19;
        this.circle = new Circle(newX, this.circle.getCenter().y(),
                this.circle.getRadius());
        if (!this.checkRep()) {
            throw new Util.InvalidInvariantException();
        }
    }

    /**
     * Update y coordinate of center
     * @param newY - new y coordinate of center
     * @throws InvalidInvariantException 
     */
    public void updateCenterY(double newY) throws InvalidInvariantException {
        if (newY < 0)
            newY = 0;
        if (newY > Board.height)
            newY = 19;
        this.circle = new Circle(this.circle.getCenter().x(), newY,
                this.circle.getRadius());
        if (!this.checkRep()) {
            throw new Util.InvalidInvariantException();
        }
    }

    /**
     * Get x coordinate of center
     * @return x coordinate of center
     */
    public double getCenterX() {
        return circle.getCenter().x();
    }

    /**
     * Get y coordinate of center
     * @return y coordinate of center
     */
    public double getCenterY() {
        return circle.getCenter().y();
    }

    /**
     * Get x coordinate of velocity
     * @return x coordinate of velocity
     */
    public double getVelocityX() {
        return this.velocity.x();
    }

    /**
     * Get y coordinate of velocity
     * @return y coordinate of velocity
     */
    public double getVelocityY() {
        return this.velocity.y();
    }

    /**
     * Update velocity of ball, applying gravity and friction
     * @param velocity - new velocity of ball (unmodified by forces yet)
     * @param time - time spanned in the course of this motion
     */
    public void updateVelocityWithGravityAndFriction(Vect velocity, double time) {
        this.velocity = velocity.plus(Board.gravity.times(time));
        this.velocity = this.velocity.times(1 - Board.mu * time
                - this.mu2 * this.velocity.length() * time);
    }  
    
    /**
     * Update velocity of ball without gravity or friction
     * @param velocity - new velocity of ball
     */
    public void updateVelocity(Vect velocity) {
        this.velocity = velocity;
    }  

    /**
     * Get velocity of ball
     * @return velocity of ball
     */
    public Vect getVelocity() {
        return new Vect(this.velocity.angle(), this.velocity.length());
    }

    /**
     * @return circle representation of ball
     */
    public Circle getCircle() {
        return new Circle(this.circle.getCenter().x(), this.circle.getCenter()
                .y(), this.circle.getRadius());
    }
    
    /**
     * @return string representation of ball
     */
    @Override
    public String toString() {
        return "*";
    }
    
    /**
     * @param other
     * @return true if other represents the same ball as this ball
     */
    @Override
    public boolean equals(Object other) {
    	if (!(other instanceof Ball)) return false;
    	Ball otherBall = (Ball) other;
    	return this.circle.equals(otherBall.circle) && this.velocity.equals(otherBall.velocity)
    			&& this.mu2==otherBall.mu2 && this.inAbsorber==otherBall.inAbsorber;
    }

    /**
     * @param ball is the ball that collides with this ball
     * @return the time that the ball takes to collide with this ball
     */
    @Override
    public double timeUntilCollision(Ball ball) {
        double time = Geometry.timeUntilBallBallCollision(this.getCircle(),
                this.getVelocity(), ball.getCircle(), ball.getVelocity());
        return time;
    }

    /**
     * This function triggers the reactions given by the collisions of the ball with this ball
     * @param ball is the ball that collides with this ball
     * @param the time that the ball takes to collide with this ball
     * @throws InvalidInvariantException 
     */
    @Override
    public void reactWhenHit(Ball ball, double time) throws UnsupportedOperationException, InvalidInvariantException {      
        
        double thisBallCenterX = this.getCenterX() + this.getVelocityX() * time;
        double thisBallCenterY = this.getCenterY() + this.getVelocityY() * time;
        
        double otherBallCenterX = ball.getCenterX() + ball.getVelocityX() * time;
        double otherBallCenterY = ball.getCenterY() + ball.getVelocityY() * time;
        
        this.updateCenterX(thisBallCenterX);
        this.updateCenterY(thisBallCenterY);
        
        ball.updateCenterX(otherBallCenterX);
        ball.updateCenterY(otherBallCenterY);
        
        Geometry.VectPair velocities = Geometry.reflectBalls(this.getCircle().getCenter(),
                1, this.getVelocity(), ball.getCircle().getCenter(), 1,
                ball.getVelocity());
        
        this.updateVelocityWithGravityAndFriction(velocities.v1, time);
        ball.updateVelocityWithGravityAndFriction(velocities.v2, time); 
        
        for (GameObject obj : this.getTriggers()) {
            obj.doTriggerAction();
        }
    }
    
    /**
     * Function that maps the ball to a (x,y) coordinate
     * @param pointToObject is the hashMap of every (x,y) point to the object that exists there, if any
     */
    public void putPoint(HashMap<DoublePair, GameObject> pointToObject) {
        int finalX, finalY;
        if (this.getCenterY() < 0.5) finalY = 0;
        else finalY = (int) Math.ceil(this.getCenterY());
        if (this.getCenterX() < 0.5) finalX = 0;
        else finalX = (int) Math.ceil(this.getCenterX());

        if (finalX > 19) {
            finalX = 19;
        }
        if (finalY > 19) {
            finalY = 19;
        }
        
        pointToObject.put(new DoublePair(finalX, finalY), this);
    }

    /**
     * This function triggers the trigger action specific to this ball
     */
    @Override
    public void doTriggerAction() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }


    
}
