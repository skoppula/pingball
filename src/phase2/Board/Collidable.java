package phase2.Board;


public interface Collidable {

    /**
     * @param ball that will collide with the Gadget
     * @return time until ball collides with Gadget in seconds, infinity seconds if the ball never collides
     */
    public double getTimeUntilCollision(Ball ball);
    
    /**
     * Mutates the ball object as necessary to represent the effect of the collision with the Gadget or ball
     * Only mutates the ball's position if the interaction with the Gadget is not a standard reflection
     * @param ball that the collision will happen with
     */
    public void collision(Ball ball);
}
