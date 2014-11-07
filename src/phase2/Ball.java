package phase2;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import physics.Geometry.DoublePair;

/**
 * A mutable class that represents a single ball. 
 * 
 */ 
/* Rep Invariant: at any one step in time, a ball has:
 *  ballCircle that has size and a location inside the 20x20 grid
 *  Velocity that 
 *  immutable coefficient of reflection that is set during construction
 *  
 */
public class Ball implements Collidable{

    private Circle ballCircle;
    private Vect velocity;
    final private double coefficentOfReflection;
    protected boolean inAbsorber = false;
    private static double L = 1;
    

    private boolean doNotUpdate = false;
    // this is true if a ball is in an absorber, for example.

    /**
     * Creates a new ball
     * @param x coordinate between (.5, .5) and ( 19.5, 19.5) 
     * @param y coordinate between (.5, .5) and ( 19.5, 19.5) 
     * @param newVelocity a Vect representing the ball's velocity 
     */
    public Ball(double x, double y, Vect newVelocity){
        this.ballCircle = new Circle( x, y, 0.25); 
        this.velocity = newVelocity; 
        this.coefficentOfReflection = 1.0; 
        this.checkRep();
    }

    /**
     * allows ball to be updated 
     */
    public void allowUpdates() {
        this.doNotUpdate = false;
    }

    /**
     * returns whether or not the ball is allowed to be updated
     * @return true if the ball can be updated
     */
    public boolean canUpdate(){
        return ! this.doNotUpdate;
    }

    public void updateCenterX(double x) {
        this.ballCircle = new Circle(x, this.ballCircle.getCenter().y(), this.ballCircle.getRadius());
    }
    
    public void updateCenterY(double y) {
        this.ballCircle = new Circle(this.ballCircle.getCenter().x(), y, this.ballCircle.getRadius());
    }

    /**
     * disallows ball to be updated
     */
    public void disallowUpdates() {
        this.doNotUpdate = true;
    }
    
        
    /**
     * checks this object to make sure that is a valid rep
     */
    private void checkRep()
    {
        assert(isValidLocation(this.getBallCircle())); 
        assert(isValidVelocity(this.getVelocity())); 
    }

    /**
     * makes sure that a circle location is valid. This means that the ball is within the bounding box created by ( 0.25, 0.25) and ( 19.25, 19.25). 
     * @param location The circle that refers to a ball's location
     * @return true if the circle represents a ball in our valid 
     */
    private static boolean isValidLocation(Circle location)
    {
        final double maxGridCoordinate = 20; 
        final double minGridCoordinate = 0; 

        return (location.getCenter().x()*L >= minGridCoordinate && location.getCenter().x()*L <= maxGridCoordinate ) && (location.getCenter().y()*L >= minGridCoordinate && location.getCenter().y()*L <= maxGridCoordinate ); //ball must be s; //ball must be inside the grid
    }

    /**
     * makes sure that a given velocity is valid. A velocity is valid if it is either 0 or between .05L and 200L; 
     * @param velocity the velocity to test if it is valid
     * @return  true if the velocity is valid. False if not. 
     */
    private static boolean isValidVelocity(Vect velocity)
    {
        Vect zero = new Vect(0,0); 
        return (velocity.equals(zero) || (velocity.length() > .01*L && velocity.length() < 200*L) ); 
    }


    /**
     * @return the ballCircle of this ball
     */
    public Circle getBallCircle(){
        return this.ballCircle; 
    }

    /**
     * sets this ball's circle to ballCircle if ballCircle is a valid circle 
     * @param ballCircle
     * @throws an IllegalArgumentException if the ballCircle is not a valid circle
     */
    public void setBallCircle(Circle ballCircle) {
        if (isValidLocation(ballCircle)){
            this.ballCircle = ballCircle;
        }
        else{
            throw new IllegalArgumentException("The input ballCircle is not located within the board " + ballCircle.getCenter().x() + " " + ballCircle.getCenter().y()); 
        }
    }

    /**
     * @return the velocity vector of this ball
     */
    public Vect getVelocity(){
        return this.velocity; 
    }

    /**
     * Sets the velocity of the ball to the new velocity
     * @param newVelocity
     * @throws an IllegalArguementException if the velocity is not valid 
     */
    public void setVelocity(Vect newVelocity){
        this.velocity =  newVelocity;
        /*
        if (newVelocity.length() < .01*L){
            this.velocity = new Vect(0, 0); //if the velocity is less than .01, then set it to zero 
            System.out.println("here");
        }
        else if (newVelocity.length() > 200*L){ //if the newVelocity is greater than 200*L, then set it to 200*L in the same direction
            Vect unitVect = newVelocity.unitSize(); 
            Vect scaledLargestPosVector = unitVect.times(200); 
            this.velocity = scaledLargestPosVector; //if the velocity is less than .01, then set it to zero 
        }
        else{
            this.velocity =  newVelocity; 
        }*/
    }

        /**
         * updates the position of the ball given the amount of time that has passed.
         * @param time The time to advance the ball by- may not be long enough to make the ball move to a position outside of the board or make the velocity illegal. 
         * @throws IllegalArgumentException if the time is large enough to make the ball travel outside of the bounds of grid. 
         */
        /*public void updateBallPosition(double time){
            //a velocity vector scaled by the amount of time that has passed 
            Vect scaledVector = this.getVelocity().times(time);  // d = (vi)*t 
            Vect newBallCenter = scaledVector.plus(this.getBallCircle().getCenter()); 
    
            if (isValidLocation(new Circle(newBallCenter, .25))){ //the update operation is going to place the ball in a valid circle location
                Circle newCircle = new Circle(newBallCenter, this.getBallCircle().getRadius()); 
                this.setBallCircle(newCircle); 
            }
            else{ //otherwise lets reset whatever parameter is outside the board (either x or y) to be inside the board 
                final double minGridCoordinate = 0.25;
                final double maxGridCoordinate = 19.75;
                double xCoord =  newBallCenter.x(); 
                double yCoord =  newBallCenter.y(); 
                if (xCoord < minGridCoordinate){
                    xCoord = minGridCoordinate; //reset 
                }
                else if (xCoord > maxGridCoordinate){
                    xCoord = maxGridCoordinate; 
                }
    
                if ( yCoord < minGridCoordinate ){
                    yCoord = minGridCoordinate; 
                }
                else if (yCoord > maxGridCoordinate){
                    yCoord = maxGridCoordinate; 
                }
                this.setBallCircle(new Circle(xCoord, yCoord, this.getBallCircle().getRadius()));
            }
        }*/
    
    /**
     * moves ball at constant velocity for given time delta
     * 
     * @param timeDelta
     *            time to move for, assumes no collisions during this time;
     */
    public void move(double timeDelta) {
        DoublePair deltas = getDeltaPosition(timeDelta);

        Vect circleVect = new Vect(this.getBallCircle().getCenter().x() + deltas.d1, this.getBallCircle().getCenter().y() + deltas.d2);
        Circle newCircle = new Circle(circleVect, this.getBallCircle().getRadius());
                
        this.setBallCircle(newCircle);
    }
    
    /**
     * Returns change in position of the ball assuming it won't hit a wall
     */
    private DoublePair getDeltaPosition(double timeDelta) {
        Double xmagnitude = velocity.angle().cos() * velocity.length();
        Double ymagnitude = velocity.angle().sin() * velocity.length();

        Double deltaXPosition = xmagnitude * timeDelta;
        Double deltaYPosition = ymagnitude * timeDelta;

        return new DoublePair(deltaXPosition, deltaYPosition);
    }
    
    
    /** * @return the coefficient of reflection for this ball
     */
    public double getCoefficentOfReflection() {
        return coefficentOfReflection;
    }

    /**
     * @return a list of GridSymbols that give
     */
    public List<GridSymbol> getSymbolRep(){
        List<GridSymbol> symbolList = new ArrayList<>();
        symbolList.add(new GridSymbol(this.ballCircle.getCenter(), '*'));
        return symbolList;
    }
    
    /**
     * Make two balls collide. Ball positions and velocities are updated until
     * immediately after the collision.
     * @param ballCollidedWith - the ball that this is colliding with
     */
    public void collision(Ball ballCollidedWith){
        Geometry.VectPair vectPair = Geometry.reflectBalls(this.ballCircle.getCenter(), 1, this.velocity,
                ballCollidedWith.ballCircle.getCenter(), 1, ballCollidedWith.velocity);
        this.setVelocity(vectPair.v1);
        ballCollidedWith.setVelocity(vectPair.v2);
    }

	@Override
	public double getTimeUntilCollision(Ball other) {
		double timeUntilCollision = Geometry.timeUntilBallBallCollision(this.getBallCircle(), this.getVelocity(),
                other.getBallCircle(), other.getVelocity());
		return timeUntilCollision;
	}

}
