package phase1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import phase1.GameObject.UnsupportedOperationException;
import phase1.Util.InvalidInvariantException;
import physics.Geometry;
import physics.Geometry.DoublePair;
import physics.Vect;

public class Board {

    protected static final double timeStep = .05;
    protected static int height = 20;
    protected static int width = 20;
    private final List<Ball> balls;
    private final List<Boundary> boundaries;
    private final List<TriangleBumper> triangleBumpers;
    private final List<Absorber> absorbers;
    private final List<Flipper> flippers;
    private final List<SquareBumper> squareBumpers;
    private final List<CircleBumper> circleBumpers;
    protected HashMap<DoublePair, GameObject> pointToObject;
    private double timestamp;
    protected static Vect gravity = new Vect(0, 25.0);
    protected static double mu = 0.025;
    
    /**
     * Create a game board object with these parameters:
     * @param height - height of board
     * @param width - width of board
     * @param gameObjects - Size 7 List of List of GameObject's of characteristic:
     *      List<Ball>, List<Boundary>, List<SquareBumper>, List<TriangleBumper>,
     *      List<CircleBumper>, List<Flipper>, List<Absorber>
     * @throws InvalidInvariantException 
     */
    public Board(int height, int width, List<List<GameObject>> gameObjects) throws InvalidInvariantException {

        Board.height = height;
        Board.width = width;
        this.balls = Util.convertListToBall(gameObjects.get(0));
        this.boundaries = Util.convertListToBoundary(gameObjects.get(1));
        this.squareBumpers = Util.convertListToSquareBumper(gameObjects.get(2));
        this.triangleBumpers = Util.convertListToTriangleBumper(gameObjects.get(3));
        this.circleBumpers = Util.convertListToCircleBumper(gameObjects.get(4));
        this.flippers = Util.convertListToFlipper(gameObjects.get(5));
        this.absorbers = Util.convertListToAbsorber(gameObjects.get(6));

        this.pointToObject = populatePointToObject();
        this.timestamp = System.currentTimeMillis();
        
        if(!checkRep()) throw new Util.InvalidInvariantException();
    }
    
    /**
     * Changes the gravitational acceleration vector
     * @param newGravity - new gravity y coordinate
     */
    protected void setGravity(double newGravity) {
        Board.gravity = new Vect(0, newGravity);
    }
    
    /**
     * Changes the friction coefficient of the board
     * @param newMu - new friction coefficient
     */
    protected void setMu(double newMu) {
        Board.mu = newMu;
    }
    
    /**
     * Returns the list of balls on this board
     * @return balls - list of balls on this board
     */
    protected List<Ball> getBalls() {
        return this.balls;
    }
    

    /**
     * Function that maps a pair of coordinates to the object that exists there, if any
     * @return the HashMap of the such described mapping
     */
    public HashMap<DoublePair, GameObject> populatePointToObject() {
        pointToObject = new HashMap<DoublePair, GameObject>();
        List<GameObject> objects = Util.combineLists(circleBumpers, absorbers, flippers, triangleBumpers, squareBumpers, balls);
        
        for (GameObject gadget : objects)
            gadget.putPoint(pointToObject);
        
        return pointToObject;
    }

    /**
     * Function that updates the state of the board constantly 
     * @throws UnsupportedOperationException
     * @throws InvalidInvariantException 
     */
    public void oneFrameActions() throws UnsupportedOperationException, InvalidInvariantException {
        double currentTime = System.currentTimeMillis();
        final int TO_SECONDS = 1000;
        double timeDifference = (currentTime - timestamp)/TO_SECONDS;
        while (timeDifference > 0) {
            this.updateState();

            timeDifference -= Board.timeStep;
        }
        timestamp = System.currentTimeMillis();
    }
    
    /**
     * Function the updates the state of all objects on the board after a very small time
     * @throws InvalidInvariantException 
     */
    public void updateState() throws UnsupportedOperationException, InvalidInvariantException {
        
        List<GameObject> nonBallGameObjects = Util.combineLists(boundaries, squareBumpers, circleBumpers, triangleBumpers, flippers, absorbers);

        for (Ball ball : balls) {
            if(ball.inAbsorber()) continue;

            List<GameObject> gameObjects = new ArrayList<GameObject>(nonBallGameObjects);
            List<GameObject> currBalls = new ArrayList<GameObject>(balls);
            currBalls.remove(ball);
            gameObjects.addAll(currBalls);

            double deltaT = Board.timeStep;            
            
            while (deltaT > 0) {
                GameObject bestGadget = gameObjects.get(0);
                double bestTimeCollision = Float.POSITIVE_INFINITY;
                for (GameObject gadget:gameObjects) {
                    double collisionTime = gadget.timeUntilCollision(ball);
                    if(collisionTime < bestTimeCollision) {
                        bestGadget = gadget;
                        bestTimeCollision = collisionTime;
                    }
                }
                
          
                
                // Ball is so far from object, that doesn't need to calculate
                // position after reflection
                if (bestTimeCollision > deltaT) {
                    ball.updateCenterX(ball.getCenterX() + ball.getVelocity().x()
                            * deltaT);
                    ball.updateCenterY(ball.getCenterY() + ball.getVelocity().y()
                            * deltaT);
                    ball.updateVelocityWithGravityAndFriction(
                            ball.getVelocity(), deltaT);
                    deltaT = 0;

                    // Ball is near object, so need to calculate position after
                    // reflection
                } else {
                    bestGadget.reactWhenHit(ball, bestTimeCollision);
                    deltaT -= bestTimeCollision;
                }
            }
        }
        
    }

    /**
     * @return string representation of the board
     */
    @Override
    public String toString() {
        populatePointToObject();
        StringBuilder outString = new StringBuilder();

        String horizontalBoundary = "";
        for(int i = 0; i <= Board.width + 1; i++) {horizontalBoundary += '.';}
        outString.append(horizontalBoundary + '\n');
        for (double y = 0; y < Board.height; y++) {
            outString.append('.');
            for (double x = 0; x < Board.width; x++) {
                Geometry.DoublePair entry = new Geometry.DoublePair(x, y);
                if (pointToObject.containsKey(entry)) {
                    outString.append(pointToObject.get(entry).toString());
                } else {
                    outString.append(" ");
                }
            }
            outString.append(".\n");
        }

        outString.append(horizontalBoundary);

        return outString.toString();
    }
    
    /**
     * checks representation invariant for this method
     *  - has boundaries
     *  - has non-negative height and width
     * @return whether the representation invariant for Board type is met 
     */
    protected boolean checkRep() {
        if(boundaries.isEmpty()) return false;
        if(height < 0 || width < 0) return false;
        return true;
    }
    
}
