package phase2.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Queue;

import phase2.Util;
import phase2.Messaging.Message;

public class LocalInputManager implements Runnable {

    Socket socket;
    BufferedReader in;
    Queue<Message> inQ;
    
    protected LocalInputManager(Queue<Message> inQ, Socket socket) throws IOException {
        this.socket = socket;
        this.inQ = inQ;
    }
    
    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                Message inMessage = Util.JSONtoMessage(line);
                inQ.add(inMessage);
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();

        } 

    }
}
