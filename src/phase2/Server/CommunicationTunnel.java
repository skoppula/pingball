package phase2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import phase2.Messaging.BoardInitMessage;
import phase2.Messaging.Message;
import phase2.Messaging.Message.MessageType;
import phase2.Messaging.TerminateMessage;

public class CommunicationTunnel implements Runnable {
    
    String name;
    Socket socket;
    BufferedReader in;
    PrintWriter out = null;
    BlockingQueue<Message> serverInQ;
    BlockingQueue<Message> tunnelOutQ;

    /*
     * Maintains a connection with a client
     */
    public CommunicationTunnel(Socket socket, BlockingQueue<Message> serverInQ) {
        this.socket = socket;
        this.serverInQ = serverInQ;
        this.tunnelOutQ = new LinkedBlockingQueue<Message>();
    }

    // when recieve client initialize message, add to tunnels hashmap
    // all other messages add to serverInQ
    @Override
    public void run() {

        //TODO create two new threads, one for managing input
        //TODO user disconnect 
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String line = null; // sorry, have to use nulls because readLine is evil!
            while(line == null){
            	line = in.readLine();
            }
            // THE FIRST MESSAGE SHOULD BE THE BOARD INIT MESSAGE, BECAUSE IT'S THE FIRST MESSAGE
            Message inMessage = Message.decode(line);
            assert(inMessage.getType() == MessageType.BOARDINIT);
            // handle the board init messages
            this.name = ((BoardInitMessage)inMessage).getBoardName();
            QueueProcessor.nameToBoardTunnel.put(this.name, this);
            
            
            while(true){
            	try{
		            for (line = in.readLine(); line != null; line = in.readLine()) {
		                inMessage = Message.decode(line);
		                serverInQ.put(inMessage);
		            }
		
		            if(!tunnelOutQ.isEmpty()) {
		                String messageJSON = tunnelOutQ.take().toString();
		                out.println(messageJSON);
		            }
            	} catch(InterruptedException e){
            		e.printStackTrace();
            	}
            }
        } catch (IOException e) {
            try {
				serverInQ.put(new TerminateMessage(this.name));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} // Make sure the server knows that this client has disconnected
        } 
    }
    
    public void addToTunnelOutQ(Message message) {
        tunnelOutQ.add(message);
    }

}
