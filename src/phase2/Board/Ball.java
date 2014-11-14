package phase2.Board;


import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import physics.Geometry.DoublePair;
import physics.Geometry.VectPair;

/**
 * A mutable class that represents a single ball. 
 * 
 */ 
public class Ball implements Collidable{
	/*
	 * Abstraction function: a Pingball ball of name this.name, represented by a circle ballCircle,
	 * with velocity newVelocity and coefficient of reflection coefficientOfReflection
	 * Rep invariant:
	 * prevVelocity is velocity 1 timestep ago
	 * velocity must be of magnitude between .01 and 200, or 0
	 * center position must be between .25 and 19.75 for both x and y
	 * required that ball = new Ball(ball.toJSONObject())
	 */

    private Circle ballCircle;
    private Vect velocity;
    private Vect prevVelocity;
    final private double coefficentOfReflection;
    protected boolean inAbsorber = false;
    private static double L = 1;
    private final double mass = 1;
    private final String name;
    

    private boolean doNotUpdate = false;
    // this is true if a ball is in an absorber, for example.

    /**
     * Creates a new ball
     * @param x coordinate between .25 and 19.75 
     * @param y coordinate between .25 and 19.75 
     * @param newVelocity a Vect representing the ball's velocity
     * @param name the name of the ball
     */
    public Ball(double x, double y, Vect newVelocity, String name){
    	this.name = name;
        this.ballCircle = new Circle( x, y, 0.25); 
        this.velocity = newVelocity; 
        this.prevVelocity = velocity;
        this.coefficentOfReflection = 1.0; 
        this.checkRep();
    }
    
    /**
     * Builds a ball object from a JSON map of a ball object.
     * @param JSONObject a map containing the necessary fields to define a ball object.
     */
    public Ball(JSONObject jsonObject){
    	this.ballCircle = new Circle((double)jsonObject.get("x"), (double)jsonObject.get("y"), .25);
    	this.name = (String)jsonObject.get("name");
    	this.velocity = new Vect((double)jsonObject.get("xVel"), (double)jsonObject.get("yVel"));
    	this.prevVelocity = velocity;
    	this.coefficentOfReflection = 1.0;
    }
    
    /**
     * Converts the current ball into a JSON object.
     * @return an object which describes the ball through its location, name, and velocity.
     */
    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject(){
    	JSONObject obj = new JSONObject();
    	obj.put("x",ballCircle.getCenter().x());
    	obj.put("y",ballCircle.getCenter().y());
    	obj.put("xVel", this.velocity.x());
    	obj.put("yVel", this.velocity.y());
    	obj.put("name", this.name);
    	return obj;
    	
    }


    public void updateCenterX(double x) {
        this.ballCircle = new Circle(x, this.ballCircle.getCenter().y(), this.ballCircle.getRadius());
    }
    
    public void updateCenterY(double y) {
        this.ballCircle = new Circle(this.ballCircle.getCenter().x(), y, this.ballCircle.getRadius());
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
    }

    
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
     * Updates the other ball's velocity after the collision
     * 
     * @param that
     *            the ball with which we collide
     */
    public void collision(Ball that) {
        VectPair vectors = Geometry.reflectBalls(that.getBallCircle().getCenter(),
                that.mass, that.prevVelocity, this.getBallCircle().getCenter(),
                this.mass, this.prevVelocity);

        that.velocity = that.velocity.plus(vectors.v1.minus(that.prevVelocity));
    }
    
    /**
     * @return time until collision between this and that
     */
    public double getTimeUntilCollision(Ball that) {
        if (that.equals(this)) return Double.POSITIVE_INFINITY;
        else return Geometry.timeUntilBallBallCollision(this.getBallCircle(), this.velocity,
                that.getBallCircle(), that.getVelocity());
    }

	
    public void updatePrevVelocity() {
        this.prevVelocity = this.velocity;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Checks whether or not this Ball has the same name, position, and 
     * velocity as the other ball
     * Checks if this Board has the same balls as the other board
     * @param otherBoard
     * @return
     */
    @Override
    public boolean equals(Object otherBall) {
        if (otherBall instanceof Ball) {
            boolean circleEqual = this.ballCircle.equals(((Ball) otherBall).getBallCircle());
            boolean velocityEqual = this.getVelocity().equals(((Ball) otherBall).getVelocity());
            boolean nameEqual = this.getName().equals(((Ball) otherBall).getName());
            return circleEqual && velocityEqual && nameEqual;
        }
        else {
            return false;
        }
        
    }

    /**
     * Returns hashcode of the ball name
     * @return 
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
