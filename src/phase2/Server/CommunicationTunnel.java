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
import phase2.Messaging.TerminateMessage;
import phase2.Messaging.Message.MessageType;

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
            QueueProcessor.nameToBoardTunnelMap.put(this.name, this);
            
            InputHandler ih = new InputHandler(in, serverInQ);
            ih.run();
            
            OutputHandler oh = new OutputHandler(out, tunnelOutQ);
            oh.run();
            
        } catch (IOException e) {
            try {
				serverInQ.put(new TerminateMessage(this.name));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} 
        } 
    }
    
    public void addToTunnelOutQ(Message message) {
        tunnelOutQ.add(message);
    }
    
    private class InputHandler implements Runnable {

        private BufferedReader in;
        private BlockingQueue<Message> serverInQ;

        public InputHandler(BufferedReader in, BlockingQueue<Message> serverInQ) {
            this.serverInQ = serverInQ;
            this.in = in;
        }
        
        public void run() {
            try{
                String line = in.readLine();
                while(line != null){
		            Message inMessage = Message.decode(line);
		            serverInQ.put(inMessage);
		            line = in.readLine();
                }
            } catch(InterruptedException | IOException e){
                e.printStackTrace();
            } 
        }
        
    }

    private class OutputHandler implements Runnable {

        private PrintWriter out;
        private BlockingQueue<Message> tunnelOutQ;

        public OutputHandler(PrintWriter out, BlockingQueue<Message> tunnelOutQ) {
            this.out = out;
            this.tunnelOutQ = tunnelOutQ;
        }

        public void run() {
            try{
                if(!tunnelOutQ.isEmpty()) {
                    String messageJSON = tunnelOutQ.take().toString();
                    out.println(messageJSON);
		        }
            } catch(InterruptedException e){
                e.printStackTrace();
            } 
        }
        
    }
}
