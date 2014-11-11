package phase2.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;

import phase2.Messaging.Message;

public class LocalOutputManager implements Runnable {

    Queue<Message> outQ;
    PrintWriter out = null;
    Socket socket;
    
    public LocalOutputManager(Queue<phase2.messaging.Message> outQ, Socket socket) throws IOException {
        this.outQ = outQ;
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);

            if(!outQ.isEmpty()) {
                String messageJSON = outQ.remove().toString();
                out.println(messageJSON);
                //TODO how does it differentiate between lines? Do you need to pass in a newline?
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            out.close();
        }
    }

}
