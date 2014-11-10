package phase2.Server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

import phase2.messaging.Message;

public class QueueProcessor implements Runnable {

    public QueueProcessor(Queue<Message> inQ, Queue<Message> outQ, HashMap<String, String> wallConnections, HashMap<String, String> tunnels, HashSet<String[]> waitlist) {
        
        //TODO finish initializing fields
        //My shit is hard! I'm the greatest
        //Look around, that's why everybody's congregated
    }

    @Override
    public void run() {
        // TODO convert inQ messages to outQ messages

                if(inMessage.getType().equals(Message.MessageType.BOARDINIT)) {
                    tunnels.put(((BoardInitMessage) inMessage).getBoardName(), this);
        
    }

}
