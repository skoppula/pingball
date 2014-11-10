package phase2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import phase2.Messaging.Message;

public class CommunicationTunnel implements Runnable {
    
    String name;
    Socket socket;
    BufferedReader in;
    PrintWriter out = null;
    HashMap<String, CommunicationTunnel> tunnels;
    BlockingQueue<Message> serverInQ;
    BlockingQueue<Message> tunnelOutQ;

    //code smashing the masses
    //getting lisa and yo addicted the fastest 
    
    public CommunicationTunnel(Socket socket, BlockingQueue<Message> serverInQ) {
        this.socket = socket;
        this.serverInQ = serverInQ;
        this.tunnelOutQ = new LinkedBlockingQueue<Message>();
    }

    // when recieve client initialize message, add to tunnels hashmap
    // all other messages add to serverInQ
    @Override
    public void run() {

        //TODO create two new threads, one for managing input
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                Message inMessage = Message.decode(line);
                serverInQ.add(inMessage);
            }

            in.close();

            out = new PrintWriter(socket.getOutputStream(), true);

            if(!tunnelOutQ.isEmpty()) {
                String messageJSON = tunnelOutQ.remove().toString();
                out.println(messageJSON);
            }
            
            out.close();

        } catch (IOException e) {
            e.printStackTrace();

        } 
    }
    
    public void addToTunnelOutQ(Message message) {
        tunnelOutQ.add(message);
    }

}
