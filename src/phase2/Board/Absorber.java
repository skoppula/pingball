package phase2.Board;

import java.util.ArrayList;
import phase2.Board.Board.InvalidInvariantException;
import phase2.PhysicsComponents.PhysicsComponent;
import phase2.PhysicsComponents.StaticCircle;
import phase2.PhysicsComponents.StaticLine;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * A gadget that represents an absorber in a PingBall game.
 * It is mutable, but only because it can be mutated to hold or not hold a ball.
 */
public class Absorber extends Gadget {
	/*
	 * rep invariant: if this.loaded == true, this.loadedBall must be the ball currently in the absorber.
	 */

    private boolean loaded;
    private Ball loadedBall;

    
    /**
     * A game object that represents an absorber as described in the notes
     * It has top, bottom, right, and left edges as LineSegments
     * 
     * @param xCoord - x coordinate
     * @param yCoord - y coordinate
     * @param kMultiplier - the size of the length
     * @param mMultiplier - the size of the height
     * @throws InvalidInvariantException 
     */
    public Absorber(int x, int y, String name, int width, int height) throws InvalidInvariantException {
        
        super(new GridPoint(x, y), name, width, height, 1); // the reflection coefficient doesn't matter in this case;
        setTriggers(new ArrayList<Gadget>());
        this.loaded = false;
        
        this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x + width, y), reflectionCoef));
        this.physicsComponentList.add(new StaticLine(new LineSegment(x, y, x, y + height), reflectionCoef));
        this.physicsComponentList.add(new StaticLine(new LineSegment(x + width, y, x + width, y + height), reflectionCoef));
        this.physicsComponentList.add(new StaticLine(new LineSegment(x, y + height, x + width, y + height), reflectionCoef));
        this.physicsComponentList.add(new StaticCircle(new Circle(x, y, 0.01), reflectionCoef));
        this.physicsComponentList.add(new StaticCircle(new Circle(x + width, y, 0.01), reflectionCoef));
        this.physicsComponentList.add(new StaticCircle(new Circle(x, y + height, 0.01), reflectionCoef));
        this.physicsComponentList.add(new StaticCircle(new Circle(x + width, y + height, 0.01), reflectionCoef));
        
    }

    
    
    /**
     * @return string representation of absorber
     */
    @Override
    public String toString() {
        return "=";
    }

    /**
     * @param ball is the ball that collides with this absorber
     * @return the time that the ball takes to collide with this absorber
     */
    @Override
    public double getTimeUntilCollision(Ball ball) {
    	double minCollisionTime = Double.POSITIVE_INFINITY;

        for (PhysicsComponent physicsComponent: physicsComponentList) {
            double currCollisionTime = physicsComponent.timeUntilCollision(ball.getBallCircle(), ball.getVelocity());
            minCollisionTime = Math.min(minCollisionTime, currCollisionTime);
        }
        
        if (minCollisionTime < Math.pow(10, -10)) {
            minCollisionTime = Math.pow(10, -10);
        }
        return minCollisionTime;
    }
    
    @Override
    public void collision(Ball ball) {
        if(!this.loaded) {
            this.loadedBall = ball;
            this.loadedBall.updateCenterX(this.getX() + width - this.loadedBall.getBallCircle().getRadius());
            this.loadedBall.updateCenterY(this.getY() + height - this.loadedBall.getBallCircle().getRadius());
            this.loadedBall.inAbsorber = true;
            this.loadedBall.setVelocity(new Vect(0.0, 0.0));
            this.loaded = true;
        }
        else{
        	PhysicsComponent gadgetPartToCollideWith = this.physicsComponentList.get(0);
            double minTimeUntilCollision = Double.MAX_VALUE;
            for(PhysicsComponent gadgetPart: physicsComponentList){
                double timeUntilCollisionPart = gadgetPart.timeUntilCollision(ball.getBallCircle(), ball.getVelocity());
                if (timeUntilCollisionPart < minTimeUntilCollision){ 
                    minTimeUntilCollision = timeUntilCollisionPart;
                    gadgetPartToCollideWith = gadgetPart;
                }
            }
            Vect newVelocity = gadgetPartToCollideWith.reflect(ball.getBallCircle(), ball.getVelocity(), ball.getCoefficentOfReflection()); 
            ball.setVelocity(newVelocity);
        }
        trigger();
    }

    /**
     * This function triggers the reactions given by the collisions of the ball with this absorber
     * @param ball is the ball that collides with this absorber
     * @param the time that the ball takes to collide with this absorber
     * @throws InvalidInvariantException 
     */
    public void updateGadgetPosition(double time) {
        return;
    }
    
    /**
     * This function triggers the trigger action specific to this absorber
     * @throws InvalidInvariantException 
     */
    @Override
    public void action() {
        final double BALL_RADIUS = 0.25;
        if (this.loaded) {
            this.loaded = false;
            this.loadedBall.setVelocity(new Vect(0, - 50));
            loadedBall.updateCenterX(this.getX() + width - BALL_RADIUS); 
            loadedBall.updateCenterY(this.getY() - BALL_RADIUS);
            
            this.loadedBall.inAbsorber = false;
        }
    }

    @Override
    public char charRep() {
        return '=';
    }
}
