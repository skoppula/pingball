package phase2.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    	
    	this.inQ = inQ;
    	wallConnectionMap = new HashMap<>();
    }

    @Override
    public void run() {
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
			} catch(InterruptedException e) {
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
    	if(wallConnectionMap.containsKey(message.getBoardWall())){
    		BoardWallPair destination = wallConnectionMap.get(message.getBoardWall());
    		CommunicationTunnel destTunnel = nameToBoardTunnelMap.get(destination);
    		destTunnel.addToOutQ(message); // reroute the message to the correct board, and leave the message untouched
    	} else {
    		System.out.println("This is odd. The ball we got doesn't know where to go. Oh well.");
    	}
    	
    }
    
    /**
     * Handles the connection of two walls according to a server
     * @param message
     */
    private void handleServerWallConnectMessage(ServerWallConnectMessage message){
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
    	
    	CommunicationTunnel tunnel1 = nameToBoardTunnelMap.get(boardWall1);
    	CommunicationTunnel tunnel2 = nameToBoardTunnelMap.get(boardWall2);
    	// If the map already contains a mapping for boardWall1, make sure to remove it, and its reverse mapping
    	if(wallConnectionMap.containsKey(boardWall1)){
    		// break the connection with boardWall1's old wall connection
    		BoardWallPair oldPair1 = wallConnectionMap.get(boardWall1);
    		CommunicationTunnel oldPairTunnel1 = nameToBoardTunnelMap.get(oldPair1);
    		oldPairTunnel1.addToOutQ(new ClientWallChangeMessage(boardWall1, false));
    		
    		//wallConnectionMap.remove(oldPair1);
    		// The above line is not necessary because balls which manage to sneak through before the wall becomes
    		// impermeable should be allowed to leave through the old connection
    		
    		//tunnel1.addToOutQ(new ClientWallChangeMessage(oldPair1, false));
    		// The above line is not necessary, because if we are changing boardWall1's connection later anyway
    		wallConnectionMap.remove(boardWall1);
    	}
    	// same with boardWall2
    	if(wallConnectionMap.containsKey(boardWall2)){
    		BoardWallPair oldPair2 = wallConnectionMap.get(boardWall2);
    		CommunicationTunnel oldPairTunnel2 = nameToBoardTunnelMap.get(oldPair2);
    		oldPairTunnel2.addToOutQ(new ClientWallChangeMessage(boardWall2, false));
    		//wallConnectionMap.remove(oldPair2);
    		// The above line is not necessary because balls which manage to sneak through before the wall becomes
    		// impermeable should be allowed to leave through the old connection
    		
    		// tunnel1.addToOutQ(new ClientWallChangeMessage(oldPair2, false));
    		// The above line is not necessary, because if we are changing boardWall1's connection later anyway
    		wallConnectionMap.remove(boardWall1);
    	}
    	
    	wallConnectionMap.put(boardWall1, boardWall2);
    	wallConnectionMap.put(boardWall1, boardWall2);
    	tunnel1.addToOutQ(new ClientWallChangeMessage(boardWall2, true));
    	tunnel2.addToOutQ(new ClientWallChangeMessage(boardWall1, true));
    }
    
    /**
     * Handles the removal of a board from all components of the server
     * @param message
     */
    private void handleTerminateMessage(TerminateMessage message){
        System.out.println("NOOOOOOO I'm terminatingggggg D:");
    	String boardName = message.getBoardName();
    	List<Orientation> oriList = new ArrayList<>();
    	oriList.add(Orientation.NINETY);
    	oriList.add(Orientation.ONE_HUNDRED_EIGHTY);
    	oriList.add(Orientation.ZERO);
    	oriList.add(Orientation.TWO_HUNDRED_SEVENTY);
    	// remove every wall connection this board may have had from the map
    	for(Orientation ori: oriList){
    		BoardWallPair bwp = new BoardWallPair(boardName, ori);
    		if(wallConnectionMap.containsKey(bwp)){
    			wallConnectionMap.remove(bwp);
    		}
    	}
    	// remove the board from the nameToBoardTunnelMap
    	nameToBoardTunnelMap.remove(boardName);

    }

}
