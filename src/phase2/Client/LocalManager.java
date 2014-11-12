package phase2.Client;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import phase2.Board.Board;
import phase2.Messaging.BoardInitMessage;
import phase2.Messaging.Message;

public class LocalManager {

    //TODO make these types threadsafe, especially lim, lom
    private Board board;
    private LocalInputManager lim;
    private LocalOutputManager lom;
    protected BlockingQueue<Message> inQ; 
    protected BlockingQueue<Message> outQ;
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
    public LocalManager(File file, InetAddress address, int port) throws IOException {
        this.inQ = new LinkedBlockingQueue<Message>();
        this.outQ = new LinkedBlockingQueue<Message>();
        this.board = new Board(file, outQ);
        this.networkedGame = true;
        
        Socket socket = new Socket(address, port); 

        outQ.add(new BoardInitMessage(board.getName()));
        lim = new LocalInputManager(inQ, socket);
        lom = new LocalOutputManager(outQ, socket);
        lim.run();
        lom.run();

        System.out.println("Adding you to server " + address + " " + port);
    }
    
    public LocalManager(File file) {
    	this.outQ = new LinkedBlockingQueue<>(); // note that we have to give the board some sort of queue
    	// however this queue should never have any items other than the startup message
    	try {
			this.board = new Board(file, outQ);
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.networkedGame = false;
        System.out.println("Starting local game..");
    }
    
    /**
     * Runs the game on the local machine
     */
    public void runGame() {
        while(true) {
            while(!inQ.isEmpty())
				try {
					board.syncChange(inQ.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            board.updateBoard(0.01);
            board.printBoard();
        }
    }
}
