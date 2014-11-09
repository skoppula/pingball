package phase2.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Queue;

import phase2.Messaging.Message;

public class NewConnectionHandler implements Runnable {
    
    ServerSocket serverSocket;
    HashMap<String, CommunicationTunnel> serverTunnels;
    Queue<Message> serverInQ;
    
    public NewConnectionHandler(ServerSocket socket, HashMap<String, CommunicationTunnel> tunnels, Queue<Message> inQ) {
        this.serverSocket = socket;
        this.serverTunnels = tunnels;
        this.serverInQ = inQ;
    }

    @Override
    public void run() {
        while (true) {
            try {

                //Blocks until new connection
                Socket socket = serverSocket.accept();

                CommunicationTunnel tunnel = new CommunicationTunnel(socket, serverTunnels, serverInQ);
                System.out.println("New client: " + socket);
                tunnel.run();
                //TODO decide if need to close the socket after the call to run()

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
