package phase2.Client;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import phase2.Board.Board;
import phase2.Messaging.Message;

/**
 * Manages the playing of PingBall from the client side.
 * Does this by handling input and output, and the updating of the gameBoard itself.
 *
 */
public class LocalManager {
	// Rep invariant: only one thread should ever take things off a queue
	// deltaTime must be greater than 0

	
    private Board board;
    private Thread lim;
    private Thread lom;
    protected BlockingQueue<Message> inQ; 
    protected BlockingQueue<Message> outQ;
    private final double deltaTime;
    boolean networkedGame;
    
    /*
     * THE STORY OF LIM AND LOM
     * 
     * lim and lom were twins
     * - they loved each other so much
     * so so much
     * like i love lisa and yo
     * 
     * anyways they decided to go on a walk
     * but then they saw race 
     * they didn't know what to do
     * and they got separated
     * 
     * THEY GOT KIDNAPPED BY A SPARKLEHEAD.
     * 
     * CHECK FOR THREAD SAFETY KIDS
     */
    
    /*
     * OH FUCK OH FUCK
     */

    /**
     * Creates a local manager that will connect to a server.
     * @param file the location of the file (of type .pb) through which we generate boards
     * @param address the address over which to communicate with the server
     * @param port the port over which to communicate with the server
     * @param deltaTime the time between frames when visualizing the board
     * @throws IOException
     */
    public LocalManager(File file, InetAddress address, int port, double deltaTime) throws IOException {
        this.inQ = new LinkedBlockingQueue<Message>();
        this.outQ = new LinkedBlockingQueue<Message>();
        this.board = new Board(file, outQ);
        this.networkedGame = true;
        this.deltaTime = deltaTime;
        
        System.out.println(address);
        System.out.println(port);
        Socket socket = new Socket(address, port); 
        //outQ.add(new BoardInitMessage(board.getName()));
        lim = new Thread(new LocalInputManager(inQ, socket));
        lom = new Thread(new LocalOutputManager(outQ, socket));
        lim.start();
        lom.start();


        System.out.println("Adding you to server " + address + " " + port);
    }
    

    /**
     * Creates a localManager for a local game
     * @param file describes the gadgets and balls on the board, using a file of type .pb
     * @param deltaTime the time in between frames of visualization of the board
     */
    public LocalManager(File file, double deltaTime) {
    	this.deltaTime = deltaTime;
    	this.outQ = new LinkedBlockingQueue<>(); // note that we have to give the board some sort of queue
    	// however this queue should never have any items other than the startup message
    	this.inQ = new LinkedBlockingQueue<>(); // this queue should also never be given anything
    	try {
			this.board = new Board(file, outQ);
		} catch (IOException e){
			e.printStackTrace();
		}
        this.networkedGame = false;
        System.out.println("Starting local game..");
    }
    
    /**
     * Creates a localManager for a local game
     * @param board takes in a predetermined board file
     * @param deltaTime the time in between frames of visualization of the board
     */
    public LocalManager(Board board, double deltaTime) {
    	this.deltaTime = deltaTime;
    	this.outQ = new LinkedBlockingQueue<>(); // note that we have to give the board some sort of queue
    	this.inQ = new LinkedBlockingQueue<>(); // this queue should also never be given anything
    	this.board = board;
    	// however this queue should never have any items other than the startup message
        this.networkedGame = false;
        System.out.println("Starting local game..");
    }

    /**
     * Runs the game (by updating the board) on the local machine.
     */
    public void runGame() {
        while(true) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            while(!inQ.isEmpty())
				try {
					board.syncChange(inQ.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            board.updateBoard(deltaTime);
            board.printBoard();
        }
    }
}
