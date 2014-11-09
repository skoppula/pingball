package phase2.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;

import phase2.Util;
import phase2.Messaging.Message;

public class LocalOutputManager implements Runnable {

    Queue<Message> outQ;
    PrintWriter out = null;
    Socket socket;
    
    public LocalOutputManager(Queue<Message> outQ, Socket socket) throws IOException {
        this.outQ = outQ;
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);

            if(!outQ.isEmpty()) {
                String messageJSON = Util.convertMessageToJSON(outQ.remove());
                out.println(messageJSON);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            out.close();
        }
    }

}
