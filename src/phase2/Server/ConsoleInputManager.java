package phase2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

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

                if(parts[0].equals("v") || parts[1].equals("h")) {
                    boolean isVerticalConnection = parts[0].equals("v");
                } else throw new IllegalArgumentException();


                String[] boardParts1 = parts[1].split("_");
                String[] boardParts2 = parts[2].split("_");
                if (boardParts1.length != 2) throw new IllegalArgumentException();
                if (boardParts2.length != 2) throw new IllegalArgumentException();
                boolean isLeftRight = boardParts1[1].equals("left") && boardParts2[2].equals("right");
                boolean isTopBottom = boardParts1[1].equals("top") && boardParts2[2].equals("bottom");
                if(isLeftRight || isTopBottom) {
                    System.out.println("INVALID INSTRUCTION YO");
                    continue;
                }

                String dir1 = isLeftRight ? "LEFT" : "TOP";
                String dir2 = isLeftRight ? "RIGHT" : "BOTTOM";
                if (!tunnels.values().contains(boardParts1[0]) || !tunnels.values().contains(boardParts2[0])) {
                    String[] connection = {boardParts1[0]+" " + dir1, boardParts2[0] + " " + dir2};
                    waitlist.add(connection);
                } else {
                    wallConnections.put(boardParts1[0] + " " + dir1, boardParts2[0] + " " + dir2);
                }
                    
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
