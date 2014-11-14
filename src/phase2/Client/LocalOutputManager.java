package phase2.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;

import phase2.Messaging.Message;

/**
 * Takes messages from the outQ, converts them into strings, and passes them over the socket.
 *
 */
public class LocalOutputManager implements Runnable {

    Queue<Message> outQ;
    PrintWriter out;
    Socket socket;
    
    /**
     * Manages output for the local instance manager
     * @param outQ the queue for outgoing messages
     * @param socket the socket over which to pass information
     * @throws IOException if the connection is interrupted
     */
    public LocalOutputManager(Queue<Message> outQ, Socket socket) throws IOException {
        this.outQ = outQ;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
    }


    @Override
    public void run() {
            while(true) {
                if(!outQ.isEmpty()) {
                    String messageJSON = outQ.remove().toString();
                    out.println(messageJSON);
                }
            }
    }

}
