package phase2.Messaging;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import phase2.Board.Ball;
import phase2.Board.Gadget.Orientation;
import phase2.messaging.ServerWallConnectMessage.ConnectionType;
import physics.Circle;
import physics.Vect;

public class MessageTest {

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
		swcMessage = new ServerWallConnectMessage("board1", "board2", ConnectionType.HORIZONTAL);
		messageList.add(swcMessage);
		terminateMessage = new TerminateMessage("targetBoard");
		messageList.add(terminateMessage);
	}
	
	
	@Test
	public void JSONReflexivityTest(){
		for(Message message: messageList){
			assertEquals(message, Message.decode(message.toString()));
		}
	}
	

	

}
