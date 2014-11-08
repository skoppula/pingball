package phase2;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import phase2.Flipper.BumperSide;
import phase2.Gadget.Orientation;
import phase2.Util.InvalidInvariantException;
import physics.Vect;
import physics.Geometry.DoublePair;

public class Pingball {
    
    private static final long TIME_DELTA_MILLISECONDS = 1000/60;
    private static final int DEFAULT_PORT = 4444;
    private static final int MAXIMUM_PORT = 65535;

    /**
     * Updates the board every timeDelta Prints out the board every timeDelta
     * 
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        double timeInSeconds = TIME_DELTA_MILLISECONDS * .001;
        Board board;
        int port = DEFAULT_PORT;
        Optional<InetAddress> host = Optional.empty();
        Optional<File> file = Optional.empty();
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));

        if (args.length == 0) board = defaultBoard();
        else {
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > MAXIMUM_PORT)
                            throw new IllegalArgumentException("port " + port + " out of range");
                    
                    } else if (flag.equals("--host")) {
                        String hostStr = arguments.remove();
                        if(hostStr.matches("\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b")) {
                            byte[] ip = new byte[4];
                            String[] ipTokens = hostStr.split("\\.");
                            for(int i = 0; i < ipTokens.length; ++i) {
                                ip[i] = Byte.parseByte(ipTokens[i]);
                            }
                            try {
                                host = Optional.of(InetAddress.getByAddress(ip));
                            } catch (UnknownHostException|SecurityException e2) {
                                throw new IllegalArgumentException("Unrecognized host IP");
                            }
                        } else {
                            try {
                                host = Optional.of(InetAddress.getByName(hostStr));
                            } catch (UnknownHostException|SecurityException e2) {
                                throw new IllegalArgumentException("Unrecognized hostname");
                            }
                        }

                    } else {
                        file = Optional.of(new File(arguments.remove()));
                        if (!file.get().isFile()) 
                            throw new IllegalArgumentException("file not found: \"" + file + "\"");
                        if (!arguments.isEmpty()) throw new IllegalArgumentException("No arguments after file");
                        break;
                    } 

                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse port: " + flag);
                }
            }
            
            if(!file.isPresent()) throw new IllegalArgumentException("No file provided");
            else {
                board = parseBoardFile(file.get());
            }
        }

        new Player(board, host, port);

        while(true) {
            Thread.sleep(TIME_DELTA_MILLISECONDS);
            board.updateBoard(timeInSeconds);
            board.printBoard();
        }
    }
    
    /**
     * @return benchmark board "default"
     */
    public static Board defaultBoard() {
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        try{
	        // make  gadgets
	        CircleBumper circle1 = new CircleBumper(1, 10, "circle1");
	        TriangleBumper triangle = new TriangleBumper(12, 15, "triangle", Orientation.ONE_HUNDRED_EIGHTY);
	        SquareBumper square1 = new SquareBumper(0, 17, "square1");
	        SquareBumper square2 = new SquareBumper(1, 17, "square2");
	        SquareBumper square3 = new SquareBumper(2, 17, "square3");
	        CircleBumper circle2 = new CircleBumper(7, 18, "circle2");
	        CircleBumper circle3 = new CircleBumper(8, 18, "circle3");
	        CircleBumper circle4 = new CircleBumper(9, 18, "circle4");
	        
	        gadgetList.addAll(Arrays.asList(circle1, triangle, square1, square2, square3, circle2, circle3, circle4));

        } catch(InvalidInvariantException e){
    		e.printStackTrace();
        }
        Board board = new Board(gadgetList, "default");
        Ball ball = new Ball(1.25, 1.25, Vect.ZERO);
        board.addBall(ball);
        return board;
  
    }
    
    /**
     * Return board from file
     * @param file 
     * @return a board
     */
    public static Board parseBoardFile(File file) {
        return null;
    }

}
