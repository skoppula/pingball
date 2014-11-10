package phase2.Server;

import java.util.HashMap;
<<<<<<< HEAD
import java.util.Map;
import java.util.concurrent.BlockingQueue;
=======
import java.util.HashSet;
import java.util.Queue;
>>>>>>> 9c42e1c6dfc8a0e087423874d8328d1b6f3f58c7

import phase2.messaging.BoardWallPair;
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

<<<<<<< HEAD
	/**
	 * The input queue to our server.
	 */
	private final BlockingQueue<Message> inQ;
	/**
	 * The output queue for our server, to the clients.
	 */
	private final BlockingQueue<Message> outQ;
	/**
	 * A map that maps names of boards to their communication tunnels.
	 */
	private final Map<String, CommunicationTunnel> nameToBoardTunnel;
	/**
	 * A map that maps walls on boards to the name of the board they are connected to
	 */
	private final Map<BoardWallPair, String> wallConnectionMap;
	
    public QueueProcessor(BlockingQueue<Message> inQ, BlockingQueue<Message> outQ) {
        //TODO finish initializing fields
        // My shit is hard! I'm the greatest
        // Look around, that's why everybody's congregated
    	
    	this.inQ = inQ;
    	this.outQ = outQ;
    	nameToBoardTunnel = new HashMap<>();
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
    
    private void handleBallMessage(Message message){
    	// TODO implement
    }
    
    private void handleBoardInitMessage(Message message){
    	// TODO implement
    }
    
    private void handleServerWallConnectMessage(Message message){
    	//TODO implement
    }
    private void handleTerminateMessage(Message message){
    	//TODO implement
    }

}
