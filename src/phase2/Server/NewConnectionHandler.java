package phase2.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import phase2.Messaging.Message;

/**
 * Listens for new connections on the port,
 * and creates CommunicationTunnels to handle the communication with these clients.
 *
 */
public class NewConnectionHandler implements Runnable {
	/*
	 * Rep invariants:
	 * CommunicationTunnels are responsible for closing their own sockets.
	 * Should not access the output of serverInQ.
	 */
    
    ServerSocket serverSocket;
    BlockingQueue<Message> serverInQ;
    
    public NewConnectionHandler(ServerSocket serverSocket, BlockingQueue<Message> inQ) {
        this.serverSocket = serverSocket;
        this.serverInQ = inQ;
    }

    @Override
    public void run() {
        while (true) {
        	@SuppressWarnings("resource")
			Socket socket = new Socket(); // we need a non-null socket
            try {
                //Blocks until new connection
                socket = serverSocket.accept();

                Thread tunnel = new Thread(new CommunicationTunnel(socket, serverInQ));
                System.out.println("New client: " + socket);
                tunnel.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
