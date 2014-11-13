package phase2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

import phase2.Messaging.Message;
import phase2.Messaging.ServerWallConnectMessage;

/**
 * Handles the input from the console on the server side,
 * and converts it into Messages, which are given to QueueProcessor
 *
 */
public class ConsoleInputManager implements Runnable {

	private final BlockingQueue<Message> inQ;
    
    public ConsoleInputManager(BlockingQueue<Message> inQ) {
        this.inQ = inQ;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {
            while((input=br.readLine())!=null){

                String[] parts = input.split(" ");
                if (parts.length != 3) throw new IllegalArgumentException();

                boolean isVerticalConnection = false;
                if(parts[0].equals("v") || parts[0].equals("h")) {
                    isVerticalConnection = parts[0].equals("v");
                } else throw new IllegalArgumentException();

                System.out.println("isV" + isVerticalConnection);
                ServerWallConnectMessage message = new ServerWallConnectMessage(parts[1], parts[2], isVerticalConnection ? ServerWallConnectMessage.ConnectionType.VERTICAL : ServerWallConnectMessage.ConnectionType.HORIZONTAL);
                inQ.add(message);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
