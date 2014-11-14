package phase2.Board;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import phase2.Board.Gadget.Orientation;
import phase2.BoardGrammar.PingBoardLexer;
import phase2.BoardGrammar.PingBoardListenerBoardCreator;
import phase2.BoardGrammar.PingBoardParser;
import phase2.BoardGrammar.PingBoardParser.RootContext;
import phase2.Messaging.Message.MessageType;
import phase2.Messaging.*;
import physics.Geometry;
import physics.Vect;

public class Board {
	//Rep Invariant: triggerMap: if triggerMap[key] = value, then must have triggerMap[value] = key
	// outQ should only ever be accessed if the board is online

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
    
    /**
     * The list of balls being dealt with for removal
     */
    private Set<Ball> ballsToRemove = new HashSet<>();
    
    /*
     * Length of board (in units distance)
     */
    protected static int BOARD_LENGTH = 20;

    public static int boardLength() {
        return BOARD_LENGTH;
    }

    final BlockingQueue<Message> outQ;
    private final double discreteTime = 0.00025;

    private List<Ball> balls = new ArrayList<Ball>();
    private List<Gadget> gadgets = new ArrayList<Gadget>();
    private final Map<Orientation, Wall> wallMap;

    private Map<Ball, List<Collidable>> ballToCollidables = new HashMap<Ball, List<Collidable>>();
    
    /** A map from names of gadgets to the gadgets themselves */
    Map<String, Gadget> nameToGadgetMap = new HashMap<>();
    
    /**
     * Generates an online Board from a .pb file, and sends a greeting to the server.
     * @param file must be a valid .pb pingball board file (see file format in specs)
     * @param outQ the queue that outputs from the board to the server
     * @throws IOException if the connection is disturbed
     */
    @SuppressWarnings("unchecked")
    public Board(File file, BlockingQueue<Message> outQ) throws IOException {
        // initialize fields that are independent of the file
        this.outQ = outQ;
        
        // Read in board files using ANTLR
        // make a stream of characters to feed to the lexer
        FileReader filereader = new FileReader(file);
        CharStream stream = new ANTLRInputStream(filereader);
        // pass the character stream to an instance of the generated lexer class
        PingBoardLexer lexer = new PingBoardLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // feed the stream of tokens we've generated to the parser
        PingBoardParser parser = new PingBoardParser(tokens);
        RootContext tree = parser.root();
        
        // Visit each node in the parse tree in order,
        // top-to-bottom, left-to-right, calling methods that we want
        ParseTreeWalker walker = new ParseTreeWalker();
        PingBoardListenerBoardCreator listener = new PingBoardListenerBoardCreator();
        walker.walk(listener,tree);
        List<Object> boardIngredients = listener.getBoardIngredients();
        // boardIngredients are of the form [gadgets, name, gravity, friction1, friction2, balls]
        this.gadgets = (ArrayList<Gadget>) boardIngredients.get(0);
        this.name = (String) boardIngredients.get(1);
        this.GRAVITY_VECTOR = new Vect(0, (double) boardIngredients.get(2));
        this.MU = (double) boardIngredients.get(3);
        this.MU2 = (double) boardIngredients.get(4);
        this.wallMap = Wall.makeWalls(this);
        for(Orientation key: wallMap.keySet()){
            this.gadgets.add(wallMap.get(key));
        }
        for(Ball ball:(Collection<Ball>) boardIngredients.get(5)){
        	this.addBall(ball);
        }
        
        for(Gadget gadget: gadgets){
            if(nameToGadgetMap.containsKey(gadget.getName())){
                throw new IllegalArgumentException("The provided list of gadgets has at least two gadgets with the same name:" + gadget.getName());
            }
            nameToGadgetMap.put(gadget.getName(), gadget);
        }

        //Send the boardinit message
        try {
            outQ.put(new BoardInitMessage(this.name));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a board with the default values for friction1, friction2, and gravity,
     * and sends a greeting to the server.
     * @param gadgets the list of gadgets to be initialized (not including walls)
     * @param name the name of our board
     * @param outQ the output queue from the board to the server
     */
    public Board(List<Gadget> gadgets, String name, BlockingQueue<Message> outQ) {
    	this.outQ = outQ;
        this.name = name;
        //Send the boardinit message
        try {
			outQ.put(new BoardInitMessage(this.name));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        this.gadgets = new ArrayList<>(gadgets);
        // set up walls
        this.wallMap = Wall.makeWalls(this);
        for(Orientation key: wallMap.keySet()){
        	this.gadgets.add(wallMap.get(key));
        }
        this.GRAVITY_VECTOR = new Vect(0,DEFAULT_GRAVITY_VALUE);
        this.MU = DEFAULT_MU;
        this.MU2 = DEFAULT_MU2;

        for(Gadget gadget: gadgets){
        	if(nameToGadgetMap.containsKey(gadget.getName())){
        		throw new IllegalArgumentException("The provided list of gadgets has at least two gadgets with the same name:" + gadget.getName());
        	}
        	nameToGadgetMap.put(gadget.getName(), gadget);
        }
    }
    
    
    /**
     * Creates a board with the specified values for friction1, friction2, and gravity,
     * and sends a greeting to the server
     * @param gadgets the list of gadgets in the board (not including walls)
     * @param name the name of the board
     * @param outQ the output queue from the board to the server
     */
    public Board(List<Gadget> gadgets, String name, double gravity, double friction1, double friction2,
    		BlockingQueue<Message> outQ) {
    	this.outQ = outQ;
        this.name = name;
        //Send the boardinit message
        try {
			outQ.put(new BoardInitMessage(this.name));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        this.gadgets = new ArrayList<>(gadgets);
        this.GRAVITY_VECTOR = new Vect(0,gravity);
        this.MU = friction1;
        this.MU2 = friction2;

        // set up walls
        this.wallMap = Wall.makeWalls(this);
        for(Orientation key: wallMap.keySet()){
        	this.gadgets.add(wallMap.get(key));
        }
        
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
     * balls changing position.
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
                
                removeFlaggedBalls(); // get rid of any balls that have been flagged (and thus absorbed
                // by the wall). This is done to avoid taking the ball out of a list while still iterating
                // over that list.
                
                if (timeDelta - timeToMove > Math.pow(10, -10) && balls.size() != 0) {
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
     * 
     * Empty and process the Messages in the queue
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
            if (ball.inAbsorber) continue;
            Vect ballVelocity = ball.getVelocity();
            double Gforce =  GRAVITY_VECTOR.y() * timeDelta;
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
            if(ballsToRemove.contains(ball)) continue;
            List<Collidable> collidingObjects = ballToCollidables.get(ball);
            for (Collidable object : collidingObjects) {
                if(object instanceof Wall) {
                    if(((Wall) object).isTeleporter) {
                        ballsToRemove.add(ball);
                        try {
                            outQ.put(new BallMessage(ball, new BoardWallPair(this.name, ((Wall) object).orientation)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    } 
                }
                object.collision(ball);
            }
            ballToCollidables.put(ball, new ArrayList<Collidable>());
        }
    }
    
    /**
     * Collides balls with gadgets and other balls if they will collide in exactly timeDelta
     * @param timeDelta must be positive
     * @return 
     */
    private void updateCollisions(double timeDelta) {

        Geometry.setForesight(timeDelta);
        for (Ball ball : balls) {
            for (Gadget gadget : gadgets) {
                double time2 = gadget.getTimeUntilCollision(ball);

                if (time2 == timeDelta) {
                    ballToCollidables.get(ball).add(gadget);
                }
            }
            for (Ball ball2 : balls) {
                double time2 = ball2.getTimeUntilCollision(ball);

                if (time2 == timeDelta) {
                    ballToCollidables.get(ball).add(ball2);
                }
            }
        }
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



    /**
     * Stores information of a double and a tuple - used to keep track of board update state.
     *
     */
    private class boolDoubleTuple {
        public final boolean bool;
        public final double time;
                
        public boolDoubleTuple(boolean bool, double time){
            this.bool = bool;
            this.time = time;
        }
    }
    
    public Vect getGravityVector() {
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
        boolean gravityEqual = this.getGravityVector().equals(otherBoard.getGravityVector());
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


    /**
     * Processes the message Board receives and changes board's state according
     * @param message must be either a BALL or a CLIENTWALLCHANGE message
     */
    public void syncChange(Message message) {
        // find which wall will either
        // a) receive a new ball or
        // b) connect/disconnect
        Orientation inOrientation;
        switch(message.getType()){
		case BALL:
			inOrientation = ((BallMessage) message).getBoardWall().wallOrientation();
			break;
		case CLIENTWALLCHANGE:
			inOrientation = ((ClientWallChangeMessage)message).getOtherBoardWall().wallOrientation();
			break;
		default:
			throw new IllegalStateException("This shouldn't be possible");
        }
        Orientation outOrientation;
        switch (inOrientation) {
            case ZERO: outOrientation = Orientation.ONE_HUNDRED_EIGHTY; break;
            case NINETY: outOrientation = Orientation.TWO_HUNDRED_SEVENTY; break;
            case ONE_HUNDRED_EIGHTY: outOrientation = Orientation.ZERO; break;
            default: outOrientation = Orientation.NINETY; break; // 270 case
        }
        Wall newWall = wallMap.get(outOrientation);
        
        if (message.getType().equals(MessageType.BALL)) {
            // calculate what the ball should look like when it comes out of the wall
            Ball newBall;
            double newX;
            double newY;
            Ball oldBall = ((BallMessage) message).getBall();
            switch(outOrientation) {
                case ZERO:
                    newX = newWall.getX()+1;
                    newY = oldBall.getBallCircle().getCenter().y();
                case NINETY:
                    newX = oldBall.getBallCircle().getCenter().x();
                    newY = newWall.getY()+1;
                case ONE_HUNDRED_EIGHTY:
                    newX = newWall.getX()-1;
                    newY = oldBall.getBallCircle().getCenter().y();
                default: // case TWO HUNDRED SEVETY
                    newX = oldBall.getBallCircle().getCenter().x();
                    newY = newWall.getY()-1;
            }
            // ball's position changes, but velocity stays the same
            newBall = new Ball(newX, newY, oldBall.getVelocity(), oldBall.getName());
            this.addBall(newBall);
        }
        else if (message.getType().equals(MessageType.CLIENTWALLCHANGE)) {
            if (((ClientWallChangeMessage) message).isConnectOrDisconnect()) { // connecting
                String otherBoardName = ((ClientWallChangeMessage) message).getOtherBoardWall().board();
                newWall.connect(otherBoardName);
            }
            else { // disconnecting
                newWall.disconnect();
            }
        }
        else {
            throw new RuntimeException("Wrong message type for Board");
        }
    }


    /**
     * Flags a ball for removal. The next time removeFlaggedBalls() is called, this ball will be removed.
     * @param ball
     */
    void flagForRemoval(Ball ball) {
		ballsToRemove.add(ball);
	}
	
	/**
	 * Removes all balls that have been flagged for removal.
	 */
	private void removeFlaggedBalls(){
		for(Ball ball: ballsToRemove){
			balls.remove(ball);
			ballToCollidables.remove(ball);
		}
		ballsToRemove = new HashSet<>();
	}
	
	
}
    
