package phase2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import phase2.Messaging.BoardInitMessage;
import phase2.Messaging.Message;

public class CommunicationTunnel implements Runnable {
    
    String name;
    Socket socket;
    BufferedReader in;
    PrintWriter out = null;
    HashMap<String, CommunicationTunnel> tunnels;
    Queue<Message> serverInQ;
    Queue<Message> tunnelOutQ;

    //code smashing the masses
    //getting lisa and yo addicted the fastest 
    
    public CommunicationTunnel(Socket socket, HashMap<String, CommunicationTunnel> tunnels, Queue<Message> serverInQ) {
        this.socket = socket;
        this.tunnels = tunnels;
        this.serverInQ = serverInQ;
        this.tunnelOutQ = new LinkedList<Message>();
    }

    // when recieve client initialize message, add to tunnels hashmap
    // all other messages add to serverInQ
    @Override
    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                Message inMessage = Message.JSONtoMessage(line);
                if(inMessage.getType().equals(Message.MessageType.BOARDINIT)) {
                    tunnels.put(((BoardInitMessage) inMessage).getBoardName(), this);
                } else serverInQ.add(inMessage);
            }

            in.close();

            out = new PrintWriter(socket.getOutputStream(), true);

            if(!tunnelOutQ.isEmpty()) {
                String messageJSON = tunnelOutQ.remove().convertMessageToJSON();
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
