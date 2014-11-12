package phase2.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;

import phase2.Messaging.BoardInitMessage;
import phase2.Messaging.Message;

public class LocalOutputManager implements Runnable {

    Queue<Message> outQ;
    PrintWriter out = null;
    Socket socket;
    
    /**
     * Manages output for the local instance manager
     * @param outQ
     * @param socket
     * @throws IOException
     */
    public LocalOutputManager(Queue<Message> outQ, Socket socket) throws IOException {
        this.outQ = outQ;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
    }


    @Override
    public void run() {
        try {

            if(!outQ.isEmpty()) {
                String messageJSON = outQ.remove().toString();
                out.println(messageJSON);
            }

        } finally {
            out.close();
        }
    }

}
