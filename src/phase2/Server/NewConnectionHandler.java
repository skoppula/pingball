package phase2.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import phase2.CommunicationTunnel;

public class NewConnectionHandler implements Runnable {
    
    ServerSocket serverSocket;
    
    public NewConnectionHandler(ServerSocket socket) {
        this.serverSocket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {

                //Blocks until new connection
                Socket socket = serverSocket.accept();

                CommunicationTunnel tunnel = new CommunicationTunnel(socket);
                System.out.println("New client: " + socket);
                tunnel.run();
                //TODO decide if need to close the socket after the call to run()

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
