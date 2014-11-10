package phase2.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import phase2.messaging.Message;

public class NewConnectionHandler implements Runnable {
    
    ServerSocket serverSocket;
    HashMap<String, CommunicationTunnel> serverTunnels;
    BlockingQueue<Message> serverInQ;
    
    public NewConnectionHandler(ServerSocket serverSocket, HashMap<String, CommunicationTunnel> tunnels, Queue<Message> inQ) {
        this.serverSocket = serverSocket;
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
