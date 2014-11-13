package phase2.Client;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import phase2.Board.Board;
import phase2.Messaging.BoardInitMessage;
import phase2.Messaging.Message;

public class LocalManager {

    //TODO make these types threadsafe, especially lim, lom
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
     * like i love lisa nd yo
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
     * Manages the game of the local manager
     * @param board
     * @param address
     * @param port
     * @throws IOException
     */
    public LocalManager(File file, InetAddress address, int port, double deltaTime) throws IOException {
        this.inQ = new LinkedBlockingQueue<Message>();
        this.outQ = new LinkedBlockingQueue<Message>();
        this.board = new Board(file, outQ);
        this.networkedGame = true;
        this.deltaTime = deltaTime;
        
        Socket socket = new Socket(address, port); 
        System.out.println("HERE");
        outQ.add(new BoardInitMessage(board.getName()));
        System.out.println("HERE2");
        lim = new Thread(new LocalInputManager(inQ, socket));
        lom = new Thread(new LocalOutputManager(outQ, socket));
        lim.start();
        lom.start();
        System.out.println("HERE3");

        System.out.println("Adding you to server " + address + " " + port);
    }
    

    public LocalManager(File file, double deltaTime) {
    	this.deltaTime = deltaTime;
    	this.outQ = new LinkedBlockingQueue<>(); // note that we have to give the board some sort of queue
    	// however this queue should never have any items other than the startup message
    	this.inQ = new LinkedBlockingQueue<>(); // this queue should also never be given anything
    	try {
			this.board = new Board(file, outQ);
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.networkedGame = false;
        System.out.println("Starting local game..");
    }
    
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
     * Runs the game on the local machine
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
