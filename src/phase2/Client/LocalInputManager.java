package phase2.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Queue;
import phase2.Messaging.Message;

/**
 * Takes information from the socket, converts it to messages,
 * and passes those messages back through a queue.
 */
public class LocalInputManager implements Runnable {

    Socket socket;
    BufferedReader in;
    Queue<Message> inQ;
    
    /**
     * Manages the inputs of the local client
     * @param inQ the queue to place read messages on
     * @param socket the socket to read information from
     * @throws IOException if the socket communication fails
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

                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    Message inMessage = Message.decode(line);
                    inQ.add(inMessage);
                }
                in.close();
            }

        } catch (IOException e) {
        	try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            e.printStackTrace();
        } 

    }
}
