package phase2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

import phase2.Messaging.ServerWallConnectMessage;

public class ConsoleInputManager implements Runnable {

    HashMap<String, String> wallConnections;
    HashMap<String, String> tunnels;
    HashSet<String[]> waitlist;
    
    public ConsoleInputManager(HashMap<String, String> wallConnections, HashMap<String, String> tunnels, HashSet<String[]> waitlist) {
        this.wallConnections = wallConnections;
        this.waitlist = waitlist;
        this.tunnels = tunnels;
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
                if(parts[0].equals("v") || parts[1].equals("h")) {
                    isVerticalConnection = parts[0].equals("v");
                } else throw new IllegalArgumentException();

                ServerWallConnectMessage message = new ServerWallConnectMessage(parts[1], parts[2], isVerticalConnection ? ServerWallConnectMessage.ConnectionType.VERTICAL : ServerWallConnectMessage.ConnectionType.HORIZONTAL);

                System.out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO read in from stdin and update wall connections appropriately
        //FUCK THIS SHIT
        //SHIT IS AN ANAGRAM OF THIS
    }
}
