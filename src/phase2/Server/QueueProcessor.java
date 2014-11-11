package phase2.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import phase2.messaging.*;

/**
 * A class which does the meat of the server's tasks.
 * It handles the the messages sent to the server by the board,
 * and replies with outputted messages to the board.
 * 
 * Thread safety: only one instance of this class
 * should ever be running at the same time.
 */
public class QueueProcessor implements Runnable {
	//rep invariants: wallConnectionMap.get(a) == b iff wallConnectionMap.get(b) == a

	/**
	 * The input queue to our server.
	 */
	private final BlockingQueue<Message> inQ;
	/**
	 * A map that maps names of boards to their communication tunnels.
	 */
	static final ConcurrentMap<String, CommunicationTunnel> nameToBoardTunnel = new ConcurrentHashMap<>();
	
	/**
	 * A map that maps walls on boards to the name of the board they are connected to
	 */
	private final Map<BoardWallPair, BoardWallPair> wallConnectionMap;
	
    public QueueProcessor(BlockingQueue<Message> inQ) {
        //TODO finish initializing fields
        // My shit is hard! I'm the greatest
        // Look around, that's why everybody's congregated
    	
    	this.inQ = inQ;
    	wallConnectionMap = new HashMap<>();
    }

    @Override
    public void run() {
        // TODO convert inQ messages to outQ messages
    	// let's say I get a Message message
    	while(true){
	    	Message message;
			try {
				message = inQ.take();
		    	switch(message.getType()){
		    	case BALL:
		    		handleBallMessage(message);
		    		break;
				case BOARDINIT:
					handleBoardInitMessage(message);
					break;
				case SERVERWALLCONNECT:
					handleServerWallConnectMessage(message);
					break;
				case TERMINATE:
					handleTerminateMessage(message);
					break;
				default:
					throw new IllegalStateException("Should never be able to get here. Server can only get"
							+ "BALL, BOARDINIT, SERVERWALLCONNECT, and TERMINATE messages.");
		    	}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
    	}
        
    }
    
    /**
     * Handles incoming ball messages, and sends the appropriate notifications
     * to other boards.
     * @param message
     */
    private void handleBallMessage(Message message){
    	// TODO implement
    }
    
    /**
     * Handles the initialization of new boards
     * @param message
     */
    private void handleBoardInitMessage(Message message){
    	//TODO implement
    }
    
    /**
     * Handles the connection of two walls according to a server
     * @param message
     */
    private void handleServerWallConnectMessage(Message message){
    	//TODO implement
    }
    
    /**
     * Handles the removal of a board from all components of the server
     * @param message
     */
    private void handleTerminateMessage(Message message){
    	//TODO implement
    }

}
