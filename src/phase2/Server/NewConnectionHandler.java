package phase2.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import phase2.Messaging.Message;

public class NewConnectionHandler implements Runnable {
    
    ServerSocket serverSocket;
    HashMap<String, CommunicationTunnel> serverTunnels;
    BlockingQueue<Message> serverInQ;
    
    public NewConnectionHandler(ServerSocket serverSocket, HashMap<String, CommunicationTunnel> tunnels, BlockingQueue<Message> inQ) {
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

                Thread tunnel = new Thread(new CommunicationTunnel(socket, serverInQ));
                System.out.println("New client: " + socket);
                tunnel.start();
                
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
