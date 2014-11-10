package phase2.Server;

import java.util.HashMap;
import java.util.Queue;

import phase2.Messaging.Message;

public class Mailman implements Runnable {
    
    HashMap<String, CommunicationTunnel> tunnels;
    Queue<Message> outQ;

    public Mailman(HashMap<String, CommunicationTunnel> tunnels, Queue<Message> outQ) {
        this.tunnels = tunnels;
        this.outQ = outQ;
    }

    @Override
    public void run() {
        //TODO FINISH HIMMMMMMMM!!!!!!
    }

}
