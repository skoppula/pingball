package Pingball;

import physics.Circle;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.Vect;
import physics.Geometry.DoublePair;

public class Ball implements Collidable {

    private Vect velocity;
    private Vect prevVelocity;
    private Circle girth;
    private DoublePair position;
    private final double radius = .25;
    private final double mass = 1;

    public Ball(Vect velocity, DoublePair position) {
        this.velocity = velocity;
        this.prevVelocity = velocity;
        this.position = position;
        this.girth = new Circle(new Vect(position.d1, position.d2), radius);
        checkRep();
    }

    /**
     * Sets the velocity
     */
    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    public void updatePrevVelocity() {
        this.prevVelocity = this.velocity;
    }

    /**
     * Gets the velocity
     */
    public Vect getVelocity() {
        return velocity;
    }

    public void setGirth(Circle newGirth) {
        this.girth = newGirth;
        checkRep();
    }

    public Circle getGirth() {
        return this.girth;
    }

    public void setPosition(DoublePair newPosition) {
        this.position = newPosition;
        this.girth = new Circle(new Vect(newPosition.d1, newPosition.d2),
                radius);
    }

    public DoublePair getPosition() {
        return this.position;
    }

    /**
     * @return time until collision between this and that
     */
    public double getTimeUntilCollision(Ball that) {
        if (that.equals(this)) return Double.POSITIVE_INFINITY;
        else return Geometry.timeUntilBallBallCollision(this.girth, this.velocity,
                that.girth, that.getVelocity());
    }

    /**
     * Updates the other ball's velocity after the collision
     * 
     * @param that
     *            the ball with which we collide
     */
    public void collision(Ball that) {
        VectPair vectors = Geometry.reflectBalls(that.girth.getCenter(),
                that.mass, that.prevVelocity, this.girth.getCenter(),
                this.mass, this.prevVelocity);

        that.velocity = that.velocity.plus(vectors.v1.minus(that.prevVelocity));
    }

    /**
     * moves ball at constant velocity for given time delta
     * 
     * @param timeDelta
     *            time to move for, assumes no collisions during this time;
     */
    public void move(double timeDelta) {
        DoublePair deltas = getDeltaPosition(timeDelta);

        this.setPosition(new DoublePair(this.position.d1 + deltas.d1,
                this.position.d2 + deltas.d2));
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

    /*
     * 
     * Rep invariants: - ball is inside the board
     */
    private void checkRep() {
        Double ballRadius = girth.getRadius();

        assert (ballRadius.equals(radius));
    }
    
    public char charRep() {
        return '*';
    }
}
