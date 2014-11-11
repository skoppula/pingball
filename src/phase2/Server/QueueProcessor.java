package phase2.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import phase2.Board.Gadget.Orientation;
import phase2.Messaging.*;

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
	static final ConcurrentMap<String, CommunicationTunnel> nameToBoardTunnelMap = new ConcurrentHashMap<>();
	
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
		    		handleBallMessage((BallMessage)message);
		    		break;
				case SERVERWALLCONNECT:
					handleServerWallConnectMessage((ServerWallConnectMessage)message);
					break;
				case TERMINATE:
					handleTerminateMessage((TerminateMessage)message);
					break;
				default:
					throw new IllegalStateException("Should never be able to get here. Server can only get"
							+ "BALL, SERVERWALLCONNECT, and TERMINATE messages.");
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
    private void handleBallMessage(BallMessage message){
    	// TODO implement
    }
    
    /**
     * Handles the connection of two walls according to a server
     * @param message
     */
    private void handleServerWallConnectMessage(ServerWallConnectMessage message){
    	//TODO implement
    	BoardWallPair boardWall1;
    	BoardWallPair boardWall2;
    	switch(message.getConnectionType()){
		case HORIZONTAL:
			boardWall1 = new BoardWallPair(message.getBoardName1(), Orientation.ONE_HUNDRED_EIGHTY);
			boardWall2 = new BoardWallPair(message.getBoardName2(), Orientation.ZERO);
			break;
		case VERTICAL:
			boardWall1 = new BoardWallPair(message.getBoardName1(), Orientation.TWO_HUNDRED_SEVENTY);
			boardWall2 = new BoardWallPair(message.getBoardName2(), Orientation.NINETY);
			break;
		default:
			throw new IllegalStateException("DIS IS IMPOSIRBR");
    	}
    	// If the map already contains a mapping for boardWall1, make sure to remove it, and its reverse mapping
    	if(wallConnectionMap.containsKey(boardWall1)){
    		wallConnectionMap.remove(wallConnectionMap.get(boardWall1));
    		wallConnectionMap.remove(boardWall1);
    		//TODO send connection break message to the correct boards
    	}
    	// same with boardWall2
    	if(wallConnectionMap.containsKey(boardWall2)){
    		wallConnectionMap.remove(wallConnectionMap.get(boardWall2));
    		wallConnectionMap.remove(boardWall2);
    		//TODO send connection break message to the correct boards
    	}
    	wallConnectionMap.
    }
    
    /**
     * Handles the removal of a board from all components of the server
     * @param message
     */
    private void handleTerminateMessage(TerminateMessage message){
    	//TODO implement
    }

}
