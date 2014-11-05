package phase2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pingball.Gadget.Orientation;
import physics.Geometry;
import physics.Geometry.DoublePair;
import physics.Vect;

public class Board {

    /*
     * 
     */
    private final int width = 20;
    private final int height = 20;

    // make gravity and friction fields

    private final double gravity = 25;
    private final double mu = .025;
    private final double mu2 = .025;

    private final double discreteTime = 0.00025;

    private List<Ball> balls = new ArrayList<Ball>();
    private List<Gadget> gadgets = new ArrayList<Gadget>();
    private List<Gadget> gadgetsWithoutWalls = new ArrayList<Gadget>();

    private Map<Ball, List<Collidable>> ballToCollidables = new HashMap<Ball, List<Collidable>>();
    

    public Board(List<Gadget> gadgets) {
        this.gadgets = gadgets;
        this.gadgetsWithoutWalls = gadgets;
        // set up walls
        Wall leftWall = new Wall(Orientation.ZERO);
        Wall topWall = new Wall(Orientation.NINETY);
        Wall rightWall = new Wall(Orientation.ONE_HUNDRED_EIGHTY);
        Wall bottomWall = new Wall(Orientation.TWO_HUNDRED_SEVENTY);
        gadgets.add(leftWall);
        gadgets.add(topWall);
        gadgets.add(rightWall);
        gadgets.add(bottomWall);
        checkRep();
    }

    public void addBall(Ball ball) {
        balls.add(ball);
        ballToCollidables.put(ball, new ArrayList<Collidable>());
        checkRep();
    }
    
    /**
     * Mutates board to represent the board after timeDelta seconds based on
     * ball changing position
     */
    public void updateBallPositions(double timeDelta) {
        if (timeDelta > 0) {
            double timeToMove = timeDelta;
            boolDoubleTuple collisionDoubleTuple = willCollide(timeDelta);
            if (collisionDoubleTuple.bool) {
                timeToMove = collisionDoubleTuple.time;
                updateCollisions(timeToMove);
                
                for (Ball ball : ballToCollidables.keySet()) {
                    ball.move(timeToMove);
                }
                collideBalls();

                for (Ball ball : balls) {
                    ball.updatePrevVelocity();
                }
                
                if (timeDelta - timeToMove > Math.pow(10, -10)) {
                    updateBallPositions(timeDelta - timeToMove);
                } 
            }
            else {
                for (Ball ball : ballToCollidables.keySet()) {
                    ball.move(timeToMove);
                }
            }
            for (Gadget gadget : gadgets) {
                gadget.updateGadgetPosition(timeToMove);
            }
        }
        return;

    }

    /**
     * Mutates board to represent changes in gadgets after timeDelta seconds
     */
    public void updateBoard(double timeDelta) {

        int timeSteps = (int) (timeDelta / discreteTime);
        for (int i = 0; i < timeSteps; i++) {
            applyGravityandFriction(discreteTime);
            updateBallPositions(discreteTime);
        }

        applyGravityandFriction(timeDelta - timeSteps * discreteTime);
        updateBallPositions(timeDelta - timeSteps * discreteTime);
        
    }

    /**
     * 
     * @param timeDelta
     *            time in which gravity and friction are applied, should not be
     *            larger than discrete time Applies the effects of gravity and
     *            friction to all balls on the board
     */
    private void applyGravityandFriction(double timeDelta) {
        for (Ball ball : balls) {
            Vect ballVelocity = ball.getVelocity();
            double Gforce =  gravity * timeDelta;
            ballVelocity = ballVelocity.plus(new Vect(0, Gforce));
            ballVelocity = ballVelocity.times(1 - mu * timeDelta - mu2
                    * ballVelocity.length() * timeDelta);

            ball.setVelocity(ballVelocity);
        }
    }
    
    /**
     * 
     * @param timeDelta
     *            time in which we wish to determine whether there will be a
     *            collision
     * @return whether or not there is a collision in time delta
     */
    private boolDoubleTuple willCollide(double timeDelta) {

        Double time = timeDelta;

        Geometry.setForesight(timeDelta);
        for (Ball ball : balls) {
            for (Gadget gadget : gadgets) {
                double time2 = gadget.getTimeUntilCollision(ball);

                if (time2 < time) {
                    time = time2;
                }
            }
            for (Ball ball2 : balls) {
                double time2 = ball2.getTimeUntilCollision(ball);

                if (time2 < time) {
                    time = time2;
                }
            }
        }
        return new boolDoubleTuple(!time.equals(timeDelta), time);
    }

    /**
     * Updates the velocities for all balls after colliding
     */
    private void collideBalls() {
        for (Ball ball : balls) {
            List<Collidable> collidingObjects = ballToCollidables.get(ball);
            for (Collidable object : collidingObjects) {
                object.collision(ball);
            }
            ballToCollidables.put(ball, new ArrayList<Collidable>());
        }
    }
    
    private double updateCollisions(double timeDelta) {

        Double time = timeDelta;
        Geometry.setForesight(timeDelta);
        for (Ball ball : balls) {
            for (Gadget gadget : gadgets) {
                double time2 = gadget.getTimeUntilCollision(ball);

                if (time2 == time) {
                    ballToCollidables.get(ball).add(gadget);
                }
            }
            for (Ball ball2 : balls) {
                double time2 = ball2.getTimeUntilCollision(ball);

                if (time2 == time) {
                    ballToCollidables.get(ball).add(ball2);
                }
            }
        }

        return time;
    }


    /**
     * Prints string representation of board
     */
    public void printBoard() {

        String boardString = "";

        char[][] boardRep = generateBoardRep();
        for (char[] row : boardRep) {
            boardString += new String(row) + "\n";
        }

        System.out.println(boardString);
    }

    /**
     * Generates the char[][] representation of all the gadgets and ball
     * currently on the board The char[][] is a list of
     * 
     * @return String representation of the board
     */
    public char[][] generateBoardRep() {

        char[][] boardRep = new char[22][22];
        for(int i = 0; i < boardRep.length; i++) {
            for(int j = 0; j < boardRep[0].length; j++) {
                boardRep[i][j] = ' '; 
            }
        }

        for (Gadget gadget : gadgets) {
            char gadgetChar = gadget.charRep();

            int x = (int) gadget.getBoardRepPosition().d1;
            int y = (int) gadget.getBoardRepPosition().d2;            

            for (int j = y; j < y + gadget.getBoardRepSize().d2; j++) {
                for (int i = x; i < x + gadget.getBoardRepSize().d1; i++) {
                    boardRep[j + 1][i + 1] = gadgetChar; 
                }
            }
        }
        for (Ball ball : balls) {

            // System.out.println("x " + ball.getPosition().d1 + " y " + ball.getPosition().d2);

            int x = (int) ball.getPosition().d1;
            int y = (int) ball.getPosition().d2;

            boardRep[y + 1][x + 1] = ball.charRep();
        }
        return boardRep;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /*
     * Rep invariants: - all of the board's gadgets fit within the board - the
     * board's gadgets do not overlap
     */
    private void checkRep() {
        // assert gadgets fit on board (works except for flippers & absorbers)
        for (int i=0; i<gadgetsWithoutWalls.size(); i++) {
            Gadget gadget = gadgetsWithoutWalls.get(i);
            assert (gadget.getBoardRepPosition().d1 <= 20);
            assert (gadget.getBoardRepPosition().d2 <= 20);
            assert (gadget.getBoardRepPosition().d1 >= -1);
            assert (gadget.getBoardRepPosition().d2 >= -1);
        }

        // make sure no gadgets are in the same position
        Map<DoublePair, Integer> gadgetPositions = new HashMap<DoublePair, Integer>();
        for (Gadget gadget : gadgetsWithoutWalls) {
            DoublePair position = gadget.getBoardRepPosition();
            if (gadgetPositions.containsKey(position)) {
                int currentCount = gadgetPositions.get(position);
                gadgetPositions.put(position, currentCount + 1);
            } else {
                gadgetPositions.put(position, 1);
            }
        }
        for (int gadgetsPerPosition : gadgetPositions.values()) {
            System.out.println(gadgetsPerPosition);
            assert (gadgetsPerPosition == 1 || gadgetsPerPosition == 2);
        }
    }

    private class boolDoubleTuple {
        public final boolean bool;
        public final double time;
                
        public boolDoubleTuple(boolean bool, double time){
            this.bool = bool;
            this.time = time;
        }
    }
        
    
}
