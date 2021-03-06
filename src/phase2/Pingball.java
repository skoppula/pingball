package phase2;

import phase2.Board.Ball;
import phase2.Board.Board;
import phase2.Board.CircleBumper;
import phase2.Board.Gadget;
import phase2.Board.SquareBumper;
import phase2.Board.TriangleBumper;
import phase2.Board.Gadget.Orientation;
import phase2.Board.Board.InvalidInvariantException;
import phase2.Client.LocalManager;
import phase2.Messaging.Message;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import physics.Vect;

/**
 * AF: Runs the client-side control of a multi-client pingball game (either a local game or a networked game)
 */
public class Pingball {
    
    private static final int DEFAULT_PORT = 4444;
    private static final int MAXIMUM_PORT = 65535;
    
    private static final long TIME_DELTA_MILLISECONDS = 1000/60;
    static double timeInSeconds = TIME_DELTA_MILLISECONDS * .0001;
    static BlockingQueue<Message> outQ;

    /**
     * Updates the board every timeDelta Prints out the board every timeDelta
     * 
     * @throws InterruptedException
     * @throws IOException 
     */
    public static void main(String[] args) throws InterruptedException, IOException {

        outQ = new LinkedBlockingDeque<Message>();
        
        Optional<Integer> port = Optional.of(DEFAULT_PORT);
        Optional<InetAddress> host = Optional.empty();
        Optional<File> file = Optional.empty();
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        
        if (args.length != 0) {
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Optional.of(Integer.parseInt(arguments.remove()));

                        if (port.get() < 0 || port.get() > MAXIMUM_PORT)
                            throw new IllegalArgumentException("port " + port + " out of range");

                    } else if (flag.equals("--host")) {
                        String hostStr = arguments.remove();
                        if(hostStr.matches("\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b")) {
                            byte[] ip = new byte[4];
                            String[] ipTokens = hostStr.split("\\.");
                                System.out.println(Arrays.toString(ipTokens));
                            for(int i = 0; i < ipTokens.length; ++i) {
                                ip[i] = (byte) Integer.parseInt(ipTokens[i]);
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
                        file = Optional.of(new File(flag));
                        if (!file.get().isFile())
                            throw new IllegalArgumentException("file not found: \"" + file.get() + "\"");
                        if (!arguments.isEmpty()) throw new IllegalArgumentException("No arguments after file");
                        break;
                    }

                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse port: " + flag);
                }
            }

            if(!file.isPresent()) throw new IllegalArgumentException("No file provided");
        }
        
        LocalManager lm = null;
        if(host.isPresent()) lm = new LocalManager(file.get(), host.get(), port.get(), timeInSeconds);
        else if (file.isPresent()) lm = new LocalManager(file.get(), timeInSeconds);
        else lm = new LocalManager(defaultBoard(outQ), timeInSeconds);
        lm.runGame();
    }
    
    /**
     * @param outQ2 
     * @return benchmark board "default"
     */
    public static Board defaultBoard(BlockingQueue<Message> outQ2) {
        List<Gadget> gadgetList = new ArrayList<Gadget>();
        try{
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
        Ball ball = new Ball(1.25, 1.25, Vect.ZERO, "defaultball");
        Board board = new Board(gadgetList, "default", outQ2);
        board.addBall(ball);
        return board;
    }
}
