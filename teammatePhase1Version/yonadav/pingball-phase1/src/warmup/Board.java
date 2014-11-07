package warmup;

import java.util.ArrayList;
import java.util.List;

import physics.*;
import warmup.Ball; 
import warmup.BoardMatrix; 

/*
 * Rep Invariant: A board is a valid board iff: 
 * Every gadget inside its list of Gadgets is valid 
 * Every ball inside its list of balls is valid
 */
/**
 * A mutable class that represents a board 
 */
public class Board {
   
    /*
     * List of all gadgets that exist on the board.
     */
    private List<Gadget> listOfGadgets;

    /*
     * List of all balls that exist on the board 
     */
    private List<Ball> listOfBalls;

    /*
     * Number of timesteps occurred since instance of Board was created
     */
    int time;

    /*
     *  Number of seconds that each timestep takes.
     */
    double timeStepDuration;

    /*
     *  amt. of friction per second
     */
    double MU; 

    /*
     * amt. of friction per unit distance
     */
    double MU2; 

    /*
     * acceleration of the ball downwards due to gravity, in units distance per second squares
     */
    Vect GRAVITY_VECTOR = new Vect(0, 25);
        
    /*
     * list of gadgets that have been collided with on current time step
     */
    private List<Gadget> collisionGadgets;
    
    /*
     * @return the list of Gadgets that are in this board
     */
    public List<Gadget> getListOfGadgets(){
        return this.listOfGadgets; 
    }

    /*
     * @return the list of balls that are in this board
     */
    public List<Ball> getListOfBalls()
    {
        return this.listOfBalls; 
    }

    /**
     * Creates an empty Board 
     * - timeStepDuration is set to 1
     * - time is initialized as 0
     * - Geometry tuning parameters are: 
        gadgetList.addAll(outerWalls);
     */
    public Board(){
        this.listOfGadgets = new ArrayList<Gadget>();
        this.listOfGadgets.addAll(OuterWall.makeWalls());
        this.listOfBalls = new ArrayList<Ball>();
        this.timeStepDuration = 1./20; 
        time = 0;
        this.MU = .025;
        this.MU2 = .025;
        this.collisionGadgets = new ArrayList<Gadget>();
    }

    /**
     * Creates a new Board
     * @param listOfGadgets List of Gadget instances that exist in the Board
     * @param listOfBalls List of Ball instances that exist in the Board
     * @param framesPerSecond number of times board display updates itself to the user per second. Must be greater than 5.
     * 
     * Walls are added by default, so they should not be included in the listOfGadgets. 
     */
    public Board(List<Gadget> listOfGadgets, List<Ball> listOfBalls, double boardUpdatesPerSecond, double MU, double MU2){
        this.listOfGadgets = new ArrayList<>(listOfGadgets);
        this.listOfGadgets.addAll(OuterWall.makeWalls());
        this.listOfBalls = new ArrayList<>(listOfBalls);
        this.timeStepDuration = 1/boardUpdatesPerSecond;
        time = 0;
        this.MU = MU;
        this.MU2 = MU2;
        this.collisionGadgets = new ArrayList<Gadget>();
    }

    /**
     * Makes a new BoardMatrix out of the current board 
     * Board properties:
     *      - length, width = 20
     *      - size = 400
     * @return String representation of Board including newlines 
     */
    public String makeBoardMatrix(){
        BoardMatrix boardMatrix = new BoardMatrix();

        for (Gadget gadget: listOfGadgets){
            //retrieve gadget representation
            List<GridSymbol> symbols = gadget.getGridSymbolRep();
            //add this gadget's representation to boardMatrix
            boardMatrix.addTiles(symbols);
        }

        for(Ball ball: listOfBalls){
            //retrieve ball representation
            List<GridSymbol> symbols = ball.getGridSymbolRep();
            //add this ball's representation to boardMatrix
            boardMatrix.addTiles(symbols);
        }
        //return string representation of boardMatrix
        String boardMatrixString = boardMatrix.printString();
        return boardMatrixString;
    }
    
    /**
     * @return a java string representation of a board that can be used to do string equality 
     */
    public String makeDebugBoardMatrix(){
        BoardMatrix boardMatrix = new BoardMatrix();

        for (Gadget gadget: listOfGadgets){
            //retrieve gadget representation
            List<GridSymbol> symbols = gadget.getGridSymbolRep();
            //add this gadget's representation to boardMatrix
            boardMatrix.addTiles(symbols);
            } 

        for(Ball ball: listOfBalls){
            //System.out.println(ball.getGridSymbolRep().get(0));
            //retrieve ball representation
            List<GridSymbol> symbols = ball.getGridSymbolRep();
            //add this ball's representation to boardMatrix
            boardMatrix.addTiles(symbols);
        }
        //return string representation of boardMatrix
        String boardMatrixString = boardMatrix.printDebugString();
        return boardMatrixString;
    }
    
    /**
     * This method causes the board to move forward one timestep. It will cause all collisions that will
     * happen this timestep to happen as well as trigger all gadgets that were triggered this timestep 
     */
    public void update(){
        updateAll();
        try {
            Thread.sleep((long)(1000*timeStepDuration));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.collisionGadgets = new ArrayList<Gadget>();
    }
    
    /**
     * Activates the gadgets that are triggered by a gadget that has been collided with on the current timestep
     */
    public void activateGadgets(Gadget collisionGadget){
        for (Gadget gadget: collisionGadget.getGadgetsToActivate()){
            gadget.activate();
        }
    }
    
    /**
     * Updates properties of each gadget
     */
    public void updateGadgets(double time){
        for (Gadget gadget: listOfGadgets){
            gadget.update(time);
        }
    }
    /**
     * Moves all balls in board given a timestep
     * @param time length of timestep
     */
    public void moveBalls(double time){
        for (Ball ball: listOfBalls){
            if(ball.canUpdate()){
                ball.updateBallPosition(time);
            }
        }
    }
    
    /**
     * Move all gadgets and balls in a given timestep
     * @param time length of timestep
     */
    public void moveAll(double time){
        moveBalls(time);
        updateGadgets(time);
    }
    
    /**
     * Check whether any of the balls in the board were stationary
     * @return true all of the balls are moving
     */
    public boolean ballsMoving(){
        boolean ballsMoving = false; 
        for (Ball ball: listOfBalls){
            if (!ball.getVelocity().equals(new Vect(0,0))){
                ballsMoving = true;
            };
        }
        return ballsMoving;
    }
    
    /**
     * updating all gadgets and balls in board in a timeStepDuration
     */
    public void updateAll(){
        double timeLeftInTimeStep = this.timeStepDuration; 
        while(timeLeftInTimeStep > 0){
            double minTimeUntilCollision = this.timeStepDuration + 1; //DID +1
            Gadget gadgetToCollideWith = this.getListOfGadgets().get(0);
            Ball collidingBall = this.getListOfBalls().get(0);
            Ball ballToCollideWith = this.getListOfBalls().get(0);
            boolean collideWithBall = false;
            boolean collideWithGadget = false;
                        
            //find collision that happens at earliest time
            for (Ball currentBall: listOfBalls){
                if(currentBall.canUpdate()){ // if the ball is allowed to be updated (not true if in absorber)
                    //Account for Friction and Gravity
                    Vect velocityAfterFriction = currentBall.getVelocity().times(1 - MU*this.timeStepDuration 
                            - MU2*this.timeStepDuration*currentBall.getVelocity().length());
                    currentBall.setVelocity(velocityAfterFriction);
                    Vect velocityAfterGravity = currentBall.getVelocity().plus(GRAVITY_VECTOR.times(this.timeStepDuration));
                    currentBall.setVelocity(velocityAfterGravity);

                    for( Gadget currentGadget: this.getListOfGadgets()){
                        if( !(currentBall.isTeleporting() && currentBall.getGadgetBeingTeleportedThrough() == currentGadget)){
                            List<PhysicsComponent> gadgetParts = currentGadget.getPhysicalRep(); // this gives us all the physical components that make up a gadget
                            for(PhysicsComponent gadgetPart: gadgetParts){
                                double timeUntilCollision = gadgetPart.timeUntilCollision(currentBall.getBallCircle(), currentBall.getVelocity());
                                if (timeUntilCollision < minTimeUntilCollision){ //find the collision that is going to happen the soonest
    
                                    minTimeUntilCollision = timeUntilCollision;
                                    collidingBall = currentBall;
                                    gadgetToCollideWith = currentGadget;
                                    collideWithBall = false;
                                    collideWithGadget = true;
                                }
                            }
                        }
                    }
                    for(Ball ballToCheckCollision: this.getListOfBalls()){
                        if (!ballToCheckCollision.equals(currentBall)){ //.equals is equals instance not observational equality
                            if(ballToCheckCollision.canUpdate()){ //CAN COLLIDE WITH OTHER BALLS
                                double timeUntilCollision = Geometry.timeUntilBallBallCollision(currentBall.getBallCircle(), currentBall.getVelocity(),
                                        ballToCheckCollision.getBallCircle(), ballToCheckCollision.getVelocity());
                            
                                if (timeUntilCollision < minTimeUntilCollision){ //find the collision that is going to happen the soonest
                                    minTimeUntilCollision = timeUntilCollision; 
                                    collidingBall = currentBall;
                                    ballToCollideWith = ballToCheckCollision;
                                    collideWithBall = true;
                                    collideWithGadget = false;
                                }
                            }
                        }
                    }
                }
            }
            //update the board due to time step
            if (minTimeUntilCollision<timeLeftInTimeStep){
                moveAll(minTimeUntilCollision);
                if(collideWithGadget){
                    gadgetToCollideWith.collide(collidingBall);
                    activateGadgets(gadgetToCollideWith);
                    timeLeftInTimeStep -= minTimeUntilCollision;
                }
                else if (collideWithBall){
                    collidingBall.collideWithBall(ballToCollideWith);
                    timeLeftInTimeStep -= minTimeUntilCollision;
                }
            }
            else{
                moveAll(timeLeftInTimeStep);
                break;
                }
            collideWithBall = false;
            collideWithGadget = false;
            }
        for(Ball ball: listOfBalls){
            ball.setWhetherTeleporting(false);
        }
    }
    
    /**
     * Sets environment variables, such as gravity and friction.
     * @param GRAVITY_VECTOR - in units distance per second
     * @param MU
     * @param MU2
     */
    public void setGravityAndFriction(Vect GRAVITY_VECTOR, double MU, double MU2){
        this.GRAVITY_VECTOR = GRAVITY_VECTOR;
        this.MU = MU;
        this.MU2 = MU2;
    }
        
}

