package phase2.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Queue;
import phase2.Messaging.Message;

public class LocalInputManager implements Runnable {

    Socket socket;
    BufferedReader in;
    Queue<Message> inQ;
    
    /**
     * Manages the inputs of the local client
     * @param inQ
     * @param socket
     * @throws IOException
     */
    protected LocalInputManager(Queue<Message> inQ, Socket socket) throws IOException {
        this.socket = socket;
        this.inQ = inQ;
    }
    
    @Override
    public void run() {

        try {
            while(true) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("here");
                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    Message inMessage = Message.decode(line);
                    inQ.add(inMessage);
                }
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
}
