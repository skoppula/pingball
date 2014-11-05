package Pingball;

import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Geometry.DoublePair;
import physics.Vect;

public class LeftFlipper extends Flipper {
    
    
    // Rep invariants:
    //     0 >= currentAngle.radians() >= -π/2
    
    /**
     * Constructs a new Flipper with the given coordinates, orientation, and gadgets that the Flipper triggers
     * Sets the angle of the LeftFlipper to 0 (meaning that it is at its default start angle)
     * Sets the rotational velocity of the flipper to 0
     * Sets the LineSegment representation to the Left Flipper's default position given its orientation
     * Sets the Circle at the end of the Flipper to the correct location
     * @param position
     * @param orientation
     */
    public LeftFlipper(DoublePair position, Orientation orientation, List<Gadget> gadgets, boolean triggersSelf) {
        super(position, orientation, gadgets, triggersSelf);
        lineSegRep = new LineSegment(this.getCenterOfRotation(), this.getDefaultEndOfFlipper());
    }
    
    // (rotating anti-clockwise)
    // 
    // 0 degrees: | *    (pivot in NW corner)
    //            | *    
    // 
    // 90 degrees: - -   (pivot in NE corner)
    //             * *  
    // 
    // 180 degrees: * |   (pivot in SE corner)
    //              * |
    // 
    // 270 degrees: * *   (pivot in SW corner)
    //              - -
    //  
    /**
     *   Update the Flipper by changing its Line Segment representation, rotational velocity, and current angle
     *   assuming that it has not been triggered
     **/
    @Override
    public void updateGadgetPosition(double deltaTime) {
        /*
         * Update Line Segment representation & currentAngle
         */
        // find the correct angle to rotate the line segment by 
        double rotationAngleRadians = this.getRotationalVelocity() * deltaTime; // positive for clockwise, negative for anti-clockwise
        // if the flipper is trying to go too far in the default direction
        if (currentAngle.radians() + rotationAngleRadians > 0) {
            rotationAngleRadians = -currentAngle.radians();
        }
        // if the flipper is trying to go too far in the opposite direction
        else if (currentAngle.radians() + rotationAngleRadians < -Angle.RAD_PI_OVER_TWO.radians()) {
            rotationAngleRadians = -Angle.RAD_PI_OVER_TWO.radians() - currentAngle.radians();
        }
        
        Angle rotationAngle = new Angle(rotationAngleRadians);
        lineSegRep = Geometry.rotateAround(lineSegRep, this.getCenterOfRotation(), rotationAngle);
        endOfFlipperCircle = Geometry.rotateAround(endOfFlipperCircle, this.getCenterOfRotation(), rotationAngle);
        currentAngle = currentAngle.plus(rotationAngle);
        
        /*
         * Update rotational velocity (change to zero if it's reached either extreme)
         */
        if (rotationalVelocity>0 && this.inDefaultPosition() || rotationalVelocity<0 && this.inFullySwungPosition()) {
            rotationalVelocity = 0;
        }
    }

    /**
     * @return a Vector representing the pivot point of the flipper
     */
    @Override
    public Vect getCenterOfRotation() {
        double centerx = originPosition.d1;
        double centery = originPosition.d2;
        switch(orientation) {
            case NINETY:
                centerx = centerx+2;
                break;
            case ONE_HUNDRED_EIGHTY:
                centerx = centerx+2;
                centery = centery+2;
                break;
            case TWO_HUNDRED_SEVENTY:
                centery = centery+2;
                break;
            default:
                break;
        }
        return new Vect(centerx, centery);
    }

    /**
     * Returns coordinates of the end of the flipper when it's in its default position
     * @return Vect of the end of the flipper in its default position
     */
    @Override
    public Vect getDefaultEndOfFlipper() {
        Orientation orientation = this.orientation;
        double x2, y2; 
        double originx = this.originPosition.d1;
        double originy = this.originPosition.d2;
        switch (orientation) {
            case NINETY:
                x2 = originx;
                y2 = originy;   
                break;
            case ONE_HUNDRED_EIGHTY:
                x2 = originx+2;
                y2 = originy;   
                break;
            case TWO_HUNDRED_SEVENTY:
                x2 = originx+2;
                y2 = originy+2;  
                break;
            default: // case ZERO
                x2 = originx;
                y2 = originy+2;     
                break;
        }
        return new Vect(x2,y2);
    }

    /**
     * Action a LeftFlipper takes when triggered
     *      - if it's in the default position, rotate anti-clockwise
     *      - if it's in the opposite position, rotate clockwise
     *      - if it's anywhere else, rotate in the opposite direction that it's rotating in
     */
    @Override
    public void action() {
        if (this.inDefaultPosition()) {
            rotationalVelocity = -ROTATIONAL_SPEED;
        }
        else if (this.inFullySwungPosition()) {
            rotationalVelocity = ROTATIONAL_SPEED;
        }
        else {
            rotationalVelocity = -rotationalVelocity;
        }
    }
    
    /**
     * @return  boolean whether the Flipper is swung a full -90˚ from its default position (anti-clockwise)
     */
    @Override
    protected boolean inFullySwungPosition() {
        return Math.abs(currentAngle.radians() + Math.PI/2) < 0.0001;
    }

    /**
     * Returns the origin of the flipper (top left coordinate of gadget)
     * @return DoublePair of (x, y)
     */
    @Override
    public DoublePair getBoardRepPosition() {
        DoublePair flipperTop = originPosition;
        DoublePair flipperBottom = new DoublePair(originPosition.d1, originPosition.d2 + 1);
        DoublePair flipperLeft = originPosition;
        DoublePair flipperRight = new DoublePair(originPosition.d1 + 1, originPosition.d2);
        
        switch(orientation) {
            case NINETY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4) 
                    return flipperTop;
                else return flipperRight;
            case ONE_HUNDRED_EIGHTY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4) 
                    return flipperRight;
                else return flipperBottom;
            case TWO_HUNDRED_SEVENTY:
                if(Math.abs(currentAngle.radians()) < Math.PI/4) 
                    return flipperBottom;
                else return flipperLeft;
            default: //case ZERO
                if(Math.abs(currentAngle.radians()) < Math.PI/4)
                    return flipperLeft;
                else return flipperTop;
        }
    }
    
    /**
     * @return boolean whether the Flipper is in its default position
     */
    public boolean inDefaultPosition() {
        return currentAngle.radians() > -0.0001;
    }

    @Override
    public DoublePair getSize() {
        return new DoublePair(2, 2);
    }

}
