package phase2.Board;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.codegen.model.chunk.ThisRulePropertyRef_ctx;
import org.antlr.v4.parse.ANTLRParser.finallyClause_return;
import org.antlr.v4.parse.ANTLRParser.range_return;

import phase2.Messaging.Message;
import physics.Geometry;
import physics.Vect;

public class Board {
	//Rep Invariant: triggerMap: if triggerMap[key] = value, then must have triggerMap[value] = key

    /*
     * 
     */
    private final String name;
    private final int width = 20;
    private final int height = 20;
    private final Vect GRAVITY_VECTOR;
    private final double MU;
    private final double MU2;
    
    // default values
    private final double DEFAULT_GRAVITY_VALUE = 25;
    private final double DEFAULT_MU = .025;
    private final double DEFAULT_MU2 = .025;
    
    /*
     * Length of board (in units distance)
     */
    protected static int BOARD_LENGTH = 20;

    public static int boardLength() {
        return BOARD_LENGTH;
    }

    // make gravity and friction fields
    private final double gravity = 25;


    private final double discreteTime = 0.00025;

    private List<Ball> balls = new ArrayList<Ball>();
    private List<Gadget> gadgets = new ArrayList<Gadget>();
    private List<Gadget> gadgetsWithoutWalls = new ArrayList<Gadget>();

    private Map<Ball, List<Collidable>> ballToCollidables = new HashMap<Ball, List<Collidable>>();
    
    /** A map from names of gadgets to the gadgets themselves */
    Map<String, Gadget> nameToGadgetMap = new HashMap<>();
    
    
    /**
     * Creates a board with the default values for friction1, friction2, and gravity
     * @param gadgets
     * @param name
     */
    public Board(List<Gadget> gadgets, String name) {
        this.name = name;
        this.gadgets = gadgets;
        this.gadgetsWithoutWalls = gadgets;
        this.GRAVITY_VECTOR = new Vect(0,DEFAULT_GRAVITY_VALUE);
        this.MU = DEFAULT_MU;
        this.MU2 = DEFAULT_MU2;

        // set up walls
        gadgets.addAll(Wall.makeWalls());
        for(Gadget gadget: gadgets){
        	if(nameToGadgetMap.containsKey(gadget.getName())){
        		throw new IllegalArgumentException("The provided list of gadgets has at least two gadgets with the same name:" + gadget.getName());
        	}
        	nameToGadgetMap.put(gadget.getName(), gadget);
        }
    }
    
    
    /**
     * Creates a board with the specified values for friction1, friction2, and gravity
     * @param gadgets
     * @param name
     */
    public Board(List<Gadget> gadgets, String name, double gravity, double friction1, double friction2) {
        this.name = name;
        this.gadgets = gadgets;
        this.gadgetsWithoutWalls = gadgets;
        this.GRAVITY_VECTOR = new Vect(0,gravity);
        this.MU = friction1;
        this.MU2 = friction2;

        // set up walls
        gadgets.addAll(Wall.makeWalls());
        for(Gadget gadget: gadgets){
            if(nameToGadgetMap.containsKey(gadget.getName())){
                throw new IllegalArgumentException("The provided list of gadgets has at least two gadgets with the same name:" + gadget.getName());
            }
            nameToGadgetMap.put(gadget.getName(), gadget);
        }
    }
    

    public void addBall(Ball ball) {
        balls.add(ball);
        ballToCollidables.put(ball, new ArrayList<Collidable>());
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
     * @return 
     */
    public List<Message> updateBoard(double timeDelta) {

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
            if (ball.inAbsorber) continue;
            Vect ballVelocity = ball.getVelocity();
            double Gforce =  gravity * timeDelta;
            ballVelocity = ballVelocity.plus(new Vect(0, Gforce));
            ballVelocity = ballVelocity.times(1 - MU * timeDelta - MU2
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
                if (ball2.equals(ball)) continue;
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
        System.out.println(generateBoardRep());
    }

    /**
     * Generates the ASCII character representation of the board
     * @return String representation of the board as ASCII characters
     */
    public String generateBoardRep() {
    	BoardMatrix boardMatrix = new BoardMatrix();

        for (Gadget gadget : gadgets) {
            boardMatrix.addTiles(gadget.getSymbolRep());
        }
        for (Ball ball : balls) {
        	boardMatrix.addTiles(ball.getSymbolRep());
        }
        return boardMatrix.printString();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    private class boolDoubleTuple {
        public final boolean bool;
        public final double time;
                
        public boolDoubleTuple(boolean bool, double time){
            this.bool = bool;
            this.time = time;
        }
    }
    
    public Vect getGRAVITY_VECTOR() {
        return GRAVITY_VECTOR;
    }


    public double getMU() {
        return MU;
    }


    public double getMU2() {
        return MU2;
    }
    
    public String getName() {
        return name;
    }

    
    /**
     * Checks whether or not this Board has the same name, gravity, friction1, friction2
     * as otherBoard
     * Checks if this Board has the same balls as the other board
     * Checks if this Board has the same gadgets as the other board
     * @param otherBoard
     * @return
     */
    public boolean hasEqualAttributes(Board otherBoard) {
        // check that constants are equal
        boolean gravityEqual = this.getGRAVITY_VECTOR().equals(otherBoard.getGRAVITY_VECTOR());
        boolean friction1Equal = this.getMU()==otherBoard.getMU();
        boolean friction2Equal = this.getMU2()==otherBoard.getMU2();
        boolean nameEqual = this.getName().equals(otherBoard.getName());
        boolean constantsEqual = gravityEqual && friction1Equal && friction2Equal && nameEqual;
        
        // check that balls are equal
        boolean equalNumberBalls = this.balls.size() == otherBoard.balls.size();
        boolean ballsEqual = equalNumberBalls && this.balls.containsAll(otherBoard.balls);
         
        // check that gadgets are equal
        boolean sameNumGadgets = this.gadgets.size() == otherBoard.gadgets.size();
        boolean gadgetsEqual = sameNumGadgets && this.gadgets.containsAll(otherBoard.gadgets);

        System.out.println("c" + constantsEqual);
        System.out.println("grav" + gravityEqual);
        System.out.println("fric1" + friction1Equal);
        System.out.println("fric2" + friction2Equal);
        System.out.println("name" + nameEqual);
        System.out.println(this.getMU());
        System.out.println(otherBoard.getMU());

        System.out.println("b" + ballsEqual);
        System.out.println(this.balls);
        System.out.println(otherBoard.balls);
        
        System.out.println("g" + gadgetsEqual);
        System.out.println(this.gadgets);
        System.out.println(otherBoard.gadgets);
        
        return constantsEqual && ballsEqual && gadgetsEqual;
    }


    public void syncChange(Message remove) {
        // TODO Auto-generated method stub
        
    }
    
}
