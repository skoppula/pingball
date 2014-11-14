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

/**
 * The class which handles communication with a given client.
 * Has a serverInQ for messages to the server, and a tunnelOutQ for messages to the client over a socket.
 */
public class CommunicationTunnel implements Runnable {
    
    String name;
    Socket socket;
    BufferedReader in;
    PrintWriter out = null;
    BlockingQueue<Message> serverInQ;
    BlockingQueue<Message> tunnelOutQ;

    /**
     * Creates a new communicationTunnel
     * @param socket the socket with which to communicate with the client
     * @param serverInQ the queue to place messages to the queueProcessor
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


            String line = in.readLine();
            in.readLine();

            // THE FIRST MESSAGE SHOULD BE THE BOARD INIT MESSAGE, BECAUSE IT'S THE FIRST MESSAGE
            Message inMessage = Message.decode(line);
            System.out.println("inMessage1: " + inMessage);
            assert(inMessage.getType() == MessageType.BOARDINIT);

            // handle the board init messages
            this.name = ((BoardInitMessage)inMessage).getBoardName();
            QueueProcessor.nameToBoardTunnelMap.put(this.name, this);
            System.out.println("map: " + QueueProcessor.nameToBoardTunnelMap);
            System.out.println("map get name: " + QueueProcessor.nameToBoardTunnelMap.get(name));
            System.out.println("name: " + name);
            
            Thread ih = new Thread(new InputHandler(in, serverInQ, name));
            ih.start();
            
            Thread oh = new Thread(new OutputHandler(out, tunnelOutQ));
            oh.start();
            
            
        } catch (IOException e) {
        	e.printStackTrace();
        } 
    }
    
    /**
     * Used to send messages to the client
     * @param message the message we want to send to the client
     */
    public void addToOutQ(Message message) {
        tunnelOutQ.add(message);
    }
    
    /**
     * Handles the messages coming from the client to the server, by reading
     * the socket and placing it on the serverInQ. When a disconnect occurs,
     * sends a terminateMessage.
     *
     */
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
                System.out.println(line);
                while(line != null){
                    Message inMessage = Message.decode(line);
                    System.out.println("inMessage: " + inMessage);
                    serverInQ.put(inMessage);
                    line = in.readLine();
                }
                serverInQ.put(new TerminateMessage(this.name));
                System.out.println("queue" + serverInQ);
                socket.close();
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

    /**
     * Takes messages given to the CommunicationTunnel, converts
     * them to strings, and sends them over the socket
     *
     */
    private class OutputHandler implements Runnable {

        private PrintWriter out;
        private BlockingQueue<Message> tunnelOutQ;

        public OutputHandler(PrintWriter out, BlockingQueue<Message> tunnelOutQ) {
            this.out = out;
            this.tunnelOutQ = tunnelOutQ;
        }

        public void run() {
            try {
                while(true) {
                    if(!tunnelOutQ.isEmpty()) {
                        String messageJSON = tunnelOutQ.take().toString();
                        out.println(messageJSON);
                    }
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            } 
        }
    }
}
