package phase2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.antlr.v4.codegen.model.chunk.ThisRulePropertyRef_ctx;

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
            System.out.println(inMessage);
            this.name = ((BoardInitMessage)inMessage).getBoardName();
            System.out.println(name);
            QueueProcessor.nameToBoardTunnelMap.put(this.name, this);
            
            Thread ih = new Thread(new InputHandler(in, serverInQ, name));
            ih.start();
            
            Thread oh = new Thread(new OutputHandler(out, tunnelOutQ));
            oh.start();
            
            
        } catch (IOException e) {
        	e.printStackTrace();
        } 
    }
    
    public void addToOutQ(Message message) {
        tunnelOutQ.add(message);
    }
    
    private class InputHandler implements Runnable {

        private BufferedReader in;
        private BlockingQueue<Message> serverInQ;
        private String name;

        public InputHandler(BufferedReader in, BlockingQueue<Message> serverInQ, String name) {
            this.serverInQ = serverInQ;
            this.name = name;
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
            } catch(IOException e){
            	try{
					serverInQ.put(new TerminateMessage(this.name));
            		socket.close();
            	}catch(IOException | InterruptedException e2){
            		e.printStackTrace();
            	}
            } catch(InterruptedException e){
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
            try {
                if(!tunnelOutQ.isEmpty()) {
                    String messageJSON = tunnelOutQ.take().toString();
                    out.println(messageJSON);
		        }
            } catch(InterruptedException e) {
                e.printStackTrace();
            } 
        }
        
    }
}
