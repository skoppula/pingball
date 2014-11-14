package phase2.Messaging;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import phase2.Board.Ball;
import phase2.Board.Gadget.Orientation;
import phase2.Messaging.Message.MessageType;
import physics.Circle;
import physics.Vect;

public class MessageTest {

    //1. Test that messages return themselves when they are converted to JSON and converted back
    //2. Check that the actual JSON string is correct
    //3. Check that information is preserved correctly in the message
    
	BallMessage ballMessage;
	ClientWallChangeMessage cwcMessage;
	BoardInitMessage boardInitMessage;
	ServerWallConnectMessage swcMessage;
	TerminateMessage terminateMessage;
	List<Message> messageList = new ArrayList<Message>();
	Circle circle;
	Ball ball;
	Vect velocity;
	BoardWallPair bwp;
	
	@Before
	public void setUp() throws Exception {
		circle = new Circle(4, 3, .25);
		velocity = new Vect(.1, .2);
		ball = new Ball(4, 3, velocity, "myBall");
		bwp = new BoardWallPair("myBoard", Orientation.NINETY);
		ballMessage = new BallMessage(ball, bwp);
		messageList.add(ballMessage);
		cwcMessage = new ClientWallChangeMessage(bwp, true);
		messageList.add(cwcMessage);
		boardInitMessage = new BoardInitMessage("mahBoard");
		messageList.add(boardInitMessage);
		swcMessage = new ServerWallConnectMessage("board1", "board2", ServerWallConnectMessage.ConnectionType.HORIZONTAL);
		messageList.add(swcMessage);
		terminateMessage = new TerminateMessage("targetBoard");
		messageList.add(terminateMessage);
	}
	
	
	//Test Partition 1
	@Test
	public void JSONReflexivityTest(){
		for(Message message: messageList){
			assertEquals(message, Message.decode(message.toString()));
		}
	}

	//Test Partition 2
	@Test
	public void JSONValidity() {
	    String correctJSONString = "{\"messageContents\":{\"connectOrDisconnect\":true,\"otherBoardWall\":{\"orientation\":\"90\",\"boardName\":\"myBoard\"}},\"messageType\":\"CLIENTWALLCHANGE\"}";
	    assertEquals(messageList.get(1).toJSONObject().toJSONString(),correctJSONString);
	}
	
	//Test Partition 3
	@Test
	public void MessageContentValidity() {
	    assertEquals(messageList.get(1).messageType, MessageType.CLIENTWALLCHANGE);
	}
}
