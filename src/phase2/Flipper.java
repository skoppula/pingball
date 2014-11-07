package phase2;

import phase2.physicsComponents.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/*
 * From Yo's phase 1
 */

/**
 * A mutable flipper gadget. When activated, it swings either upward or downward.
 * Flippers come in two different types: right flippers and left flippers.
 * Right flippers pivot around the top right corner, left flippers around
 * the top left. Flippers take up 2L * 2L space, and are happy.
 */
public class Flipper extends Gadget {
    // Rep. Invariant: 

    private Angle angle;
    // The angle that the flipper is at, clockwise from north.

    final private double DEFAULT_ANGULAR_VELOCITY = Math.toRadians(1080);
    // The angular velocity that the flipper moves at when triggered, in radians per second.

    final private BumperSide side;
    // Whether the trigger is a right or left flipper.

    final private Vect pivot;
    // The pivot point around which the flipper rotates.

    private boolean willMoveClockwise; 
    // true if the flipper's current swing will be clockwise, false otherwise

    final private Corner pivotCorner; 
    // the corner that the flipper pivots around. Like pivot, but as an enum.
    
    final private Orientation orientation;
    // orientation of the flipper

    private boolean isActive;
    // whether the flipper is currently rotating
    
    private final List<PhysicsComponent> physicsComponentList = new ArrayList<>();
    // a list representing the physical components of this flipper
    

    /**
     * Determines which type the bumper is.
     *
     */
    public enum BumperSide{
        LEFT,
        RIGHT
    }

    /**
     * Determines where the flipper's origin is, with respect to the box it is confined to.
     * For example, TOPLEFT implies that the flipper's pivot point is at x, y
     */
    private enum Corner{
        TOPRIGHT, TOPLEFT, BOTTOMRIGHT, BOTTOMLEFT
    }

    /**
     * Creates a new flipper with its origin at x, y, of type side
     * @param x - must be between 0 and 18
     * @param y - must be between 0 and 18
     * @param side - either LEFT or RIGHT bumper
     * @param orientation - offset angle measured clockwise from north
     */
    public Flipper(int x, int y, String name, BumperSide side, Orientation orientation){
        super(new GridPoint(x, y), name, 2, 2, .95);
        this.side = side;
        this.orientation = orientation;
        this.isActive = false;
        this.angle = Angle.ZERO; // yolo

        // Will our bumper start out by moving clockwise or counterclockwise?
        switch(side){
        case LEFT:
            this.willMoveClockwise = false;
            break;
        case RIGHT:
            this.willMoveClockwise = true;
            break;
        default:
            throw new IllegalArgumentException("Must be either Flipper.LEFT or Flipper.RIGHT");
        }


        // We will now determine which corner our flipper's origin is at.
        // It will be one of the corners, denoted by the enum Corner, and
        // stored in pivotCorner. This is
        // calculated from the orientation and the type of flipper.

        if((side == BumperSide.LEFT) && orientation.equals(Orientation.ZERO) ||
                side == BumperSide.RIGHT && orientation.equals(Orientation.TWO_HUNDRED_SEVENTY)){
            this.pivotCorner = Corner.TOPLEFT;
            this.pivot = new Vect(this.getX(), this.getY());
        }
        else if((side == BumperSide.LEFT) && orientation.equals(Orientation.NINETY) ||
                side == BumperSide.RIGHT && orientation.equals(Orientation.ZERO)){
            this.pivotCorner = Corner.TOPRIGHT;
            this.pivot = new Vect(this.getX() + 2, this.getY());
        }
        else if((side == BumperSide.LEFT) && orientation.equals(Orientation.ONE_HUNDRED_EIGHTY) ||
                side == BumperSide.RIGHT && orientation.equals(Orientation.NINETY)){
            this.pivotCorner = Corner.BOTTOMRIGHT;
            this.pivot = new  Vect(this.getX()+2, this.getY()+2);
        }
        else if((side == BumperSide.LEFT) && orientation.equals(Orientation.TWO_HUNDRED_SEVENTY) ||
                side == BumperSide.RIGHT && orientation.equals(Orientation.ONE_HUNDRED_EIGHTY)){
            this.pivotCorner = Corner.BOTTOMLEFT;
            this.pivot = new Vect(this.getX(), this.getY() + 2);
        }
        else{
            throw new IllegalStateException("Should not be able to reach this spot. Orientation must be"
                    + "either 0, 90, 180, or 270");
        }

        
        // This is the pivot corner.
        Vect rotatingTip;
        // This is the rotating tip of the flipper.
        switch(pivotCorner){
        case BOTTOMLEFT:
            if(willMoveClockwise){
                rotatingTip = new Vect(location.getX(), location.getY());
            }
            else{
                rotatingTip = new Vect(location.getX() + 2, location.getY() + 2);
            }
            break;
        case BOTTOMRIGHT:
            if(willMoveClockwise){
                rotatingTip = new Vect(location.getX(), location.getY() + 2);
            }
            else{
                rotatingTip = new Vect(location.getX() + 2, location.getY());
            }
            break;
        case TOPLEFT:
            if(willMoveClockwise){
                rotatingTip = new Vect(location.getX() + 2, location.getY());
            }
            else{
                rotatingTip = new Vect(location.getX(), location.getY() + 2);
            }
            break;
        case TOPRIGHT:
            if(willMoveClockwise){
                rotatingTip = new Vect(location.getX(), location.getY() + 2);
            }
            else{
                rotatingTip = new Vect(location.getX(), location.getY());
            }
            break;
        default:
            throw new IllegalStateException("Should not have been able to reach this spot. Orientation should"
                    + "have already been checked, and shown to be "
                    + "either 0, 90, 180, or 270");
        }
        // Now that we know where our other end of the flipper is, we can make the rest of the physical rep.
        this.physicsComponentList.add(new RotatingCircle(new Circle(rotatingTip, 0.01), pivot, 
                0, reflectionCoef));
        this.physicsComponentList.add(new RotatingLine(new LineSegment(pivot, rotatingTip), pivot,
                0, reflectionCoef));
    }

    /**
     * @return a representation of the flipper as a series of grid symbols. 
     * The occupied symbols are the pivot corner, and the corner from which the next swing will originate.
     * For example, a left flipper at orientation 0 would have a symbol at x, y and a symbol at x, y + 1
     * The character used relies on charRep()
     */
    @Override
    public List<GridSymbol> getSymbolRep(){
        List<GridSymbol> symbolList = new ArrayList<>();

        switch(pivotCorner){
        case BOTTOMLEFT:
            symbolList.add(new GridSymbol(location.getX(), location.getY() + 1, this.charRep()));
            if(willMoveClockwise){
                symbolList.add(new GridSymbol(location.getX(), location.getY(), this.charRep()));
            }
            else{
                symbolList.add(new GridSymbol(location.getX() + 1, location.getY() + 1, this.charRep()));
            }
            break;
        case BOTTOMRIGHT:
            symbolList.add(new GridSymbol(location.getX() + 1, location.getY() + 1, this.charRep()));
            if(willMoveClockwise){
                symbolList.add(new GridSymbol(location.getX(), location.getY() + 1, this.charRep()));
            }
            else{
                symbolList.add(new GridSymbol(location.getX() + 1, location.getY(), this.charRep()));
            }
            break;
        case TOPLEFT:
            symbolList.add(new GridSymbol(location.getX(), location.getY(), this.charRep()));
            if(willMoveClockwise){
                symbolList.add(new GridSymbol(location.getX() + 1, location.getY(), this.charRep()));
            }
            else{
                symbolList.add(new GridSymbol(location.getX(), location.getY() + 1, this.charRep()));
            }
            break;
        case TOPRIGHT:
            symbolList.add(new GridSymbol(location.getX() + 1, location.getY(), this.charRep()));
            if(willMoveClockwise){
                symbolList.add(new GridSymbol(location.getX() + 1, location.getY() + 1, this.charRep()));
            }
            else{
                symbolList.add(new GridSymbol(location.getX(), location.getY(), this.charRep()));
            }
            break;
        default:
            throw new IllegalStateException("Should not be able to reach this spot. Orientation should"
                    + "have already been checked, must have been"
                    + "either 0, 90, 180, or 270");
        }
        return symbolList;
    }


    /**
     * Steps the flipper forward in time. If the flipper has been triggered, causes it to begin swinging.
     * If the swing is continuing, keep moving it forward, until it reaches the other side, and then stop it,
     * and prepare it to swing back.
     */
    @Override
    public void updateGadgetPosition(double timestep) {
        if (isActive){
            switch(pivotCorner){
            case BOTTOMLEFT:
                if(willMoveClockwise){
                    if(this.angle.compareTo(Angle.DEG_90) >= 0){ //this angle is greater than 90 degrees- set it back to 90 and set the rotational velocity to 0 
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){
                                physicsComponent.rotateToAngle(Angle.DEG_90);
                                physicsComponent.setAngularVelocity(0);
                            }
                        }
                        this.willMoveClockwise = false;
                        isActive = false;

                    }

                    else{ //this physics component needs to keep rotating until it is at 90 degrees 
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){                               
                                physicsComponent.setAngularVelocity(DEFAULT_ANGULAR_VELOCITY); // rotate clockwise
                                physicsComponent.rotateForTime(timestep);
                            }
                        }
                    }
                }
                else{
                    // Note that this relies on the fact that a timestep does not swing through 180 degrees,
                    if(this.angle.compareTo(Angle.DEG_180) > 0){
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){
                                physicsComponent.rotateToAngle(Angle.ZERO);
                                physicsComponent.setAngularVelocity(0);
                            }
                        }
                        this.willMoveClockwise = true;
                        isActive = false;

                    }
                    else{
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){                               
                                physicsComponent.setAngularVelocity(-1*DEFAULT_ANGULAR_VELOCITY); // rotate clockwise
                                physicsComponent.rotateForTime(timestep);
                            }
                        }
                    }
                }
                break;
            case BOTTOMRIGHT:
                if(willMoveClockwise){
                    // Note that this relies on the fact that a timestep does not swing throug 180 degrees,
                    // i.e. timestep must be greater than .2 seconds TODO ensure this is true!
                    if(this.angle.compareTo(Angle.DEG_180) <= 0){
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){
                                physicsComponent.rotateToAngle(Angle.ZERO);
                                physicsComponent.setAngularVelocity(0);
                            }
                        }
                        this.willMoveClockwise = false;
                        isActive = false;

                    }
                    else{
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){                               
                                physicsComponent.setAngularVelocity(DEFAULT_ANGULAR_VELOCITY); // rotate clockwise
                                physicsComponent.rotateForTime(timestep);
                            }
                        }
                    }
                }
                else{
                    if(this.angle.compareTo(Angle.DEG_270) <= 0){
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){
                                physicsComponent.rotateToAngle(Angle.DEG_270);
                                physicsComponent.setAngularVelocity(0);
                            }
                        }
                        this.willMoveClockwise = true;
                        isActive = false;

                    }
                    else{
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){                               
                                physicsComponent.setAngularVelocity(-1*DEFAULT_ANGULAR_VELOCITY); // rotate clockwise
                                physicsComponent.rotateForTime(timestep);
                            }
                        }
                    }
                }
                break;
            case TOPLEFT:
                if(willMoveClockwise){
                    if(this.angle.compareTo(Angle.DEG_180) >= 0){
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){
                                physicsComponent.rotateToAngle(Angle.DEG_180);
                                physicsComponent.setAngularVelocity(0);
                            }
                        }
                        this.willMoveClockwise = false;
                        isActive = false;

                    }
                    else{
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){                               
                                physicsComponent.setAngularVelocity(DEFAULT_ANGULAR_VELOCITY); // rotate clockwise
                                physicsComponent.rotateForTime(timestep);
                            }
                        }
                    }
                }
                else{
                    if(this.angle.compareTo(Angle.DEG_90) <= 0){
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){
                                physicsComponent.rotateToAngle(Angle.DEG_90);
                                physicsComponent.setAngularVelocity(0);
                            }
                        }
                        this.willMoveClockwise = true;
                        isActive = false;

                    }
                    else{
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){                               
                                physicsComponent.setAngularVelocity(-1*DEFAULT_ANGULAR_VELOCITY); // rotate clockwise
                                physicsComponent.rotateForTime(timestep);
                            }
                        }
                    }
                }
                break;
            case TOPRIGHT:
                if(willMoveClockwise){
                    if(this.angle.compareTo(Angle.DEG_270) >= 0){
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){
                                physicsComponent.rotateToAngle(Angle.DEG_270);
                                physicsComponent.setAngularVelocity(0);
                            }
                        }
                        this.willMoveClockwise = false;
                        isActive = false;

                    }
                    else{
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){                               
                                physicsComponent.setAngularVelocity(DEFAULT_ANGULAR_VELOCITY); // rotate clockwise
                                physicsComponent.rotateForTime(timestep);
                            }
                        }
                    }
                }
                else{
                    if(this.angle.compareTo(Angle.DEG_180) <= 0){
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){
                                physicsComponent.rotateToAngle(Angle.DEG_180);
                                physicsComponent.setAngularVelocity(0);
                            }
                        }
                        this.willMoveClockwise = true;
                        isActive = false;

                    }
                    else{
                        for(PhysicsComponent physicsComponent: physicsComponentList){
                            if(physicsComponent.canRotate()){                               
                                physicsComponent.setAngularVelocity(-1*DEFAULT_ANGULAR_VELOCITY); // rotate clockwise
                                physicsComponent.rotateForTime(timestep);
                            }
                        }
                    }
                }
                break;
            default:
                throw new IllegalStateException("Should not have been able to reach this spot. Orientation must"
                        + "have already been checked, and verified as"
                        + "either 0, 90, 180, or 270");
            }

            //Now, make sure that your orientation is consistent with the angle of your physics components.
            for(PhysicsComponent physicsComponent: physicsComponentList){
                if(physicsComponent.canRotate()){
                    angle = physicsComponent.getAngle();
                }
            }
        }

    }


    /**
     * Return which character represents flipper when displaying it on a BoardMatrix grid.
     */
    @Override
    public char charRep() {
        switch(pivotCorner){
        case BOTTOMLEFT:
            if(willMoveClockwise){
                return '|';
            }
            else{
                return '_';
            }
        case BOTTOMRIGHT:
            if(willMoveClockwise){
                return '_';
            }
            else{
                return '|';
            }
        case TOPLEFT:
            if(willMoveClockwise){
                return '_';
            }
            else{
                return '|';
            }
        case TOPRIGHT:
            if(willMoveClockwise){
                return '|';
            }
            else{
                return '_';
            }
        default:
            throw new IllegalStateException("Should not be able to reach this spot. Orientation should"
                    + "have already been checked, must have been"
                    + "either 0, 90, 180, or 270");
        }
    }

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void action() {
		isActive = true;
	}

	@Override
	public double getTimeUntilCollision(Ball ball) {
		double minTime = Double.POSITIVE_INFINITY;
		for(PhysicsComponent physicsComponent: physicsComponentList){
			double newTime = physicsComponent.timeUntilCollision(ball.getBallCircle(), ball.getVelocity());
			if(newTime > minTime){
				newTime = minTime;
			}
		}
		
        if (minTime < Math.pow(10, -10)) {
            return Util.EPSILON;
        }
        else {
            return minTime;
        }  
	}

	@Override
	public void collision(Ball ball) {
		// TODO Auto-generated method stub
		
	}
}
