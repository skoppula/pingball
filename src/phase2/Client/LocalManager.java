package phase2.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import phase2.Board.Board;
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
    public LocalManager(Board board, InetAddress address, int port) throws IOException {
        this.inQ = new LinkedBlockingQueue<Message>();
        this.outQ = new LinkedBlockingQueue<Message>();
        this.board = board;
        this.networkedGame = true;
        
        Socket socket = new Socket(address, port); 
        //TODO how are we getting a socket? shouldn't we only have a port, then listen on that port for sockets?

        lim = new LocalInputManager(inQ, socket);
        lom = new LocalOutputManager(outQ, socket);
        lim.run();
        lom.run();
        //TODO send board initialization message

        socket = new Socket(address, port);
        System.out.println("Adding you to server " + address + " " + port);
    }
    
    public LocalManager(Board board) {
        this.networkedGame = false;
        System.out.println("Starting local game..");
    }
    
    /**
     * Runs the game on the local machine
     */
    public void runGame() {
        while(true) {
            while(!inQ.isEmpty())
                //TODO update the state of the board according to an incoming message
                board.syncChange(inQ.remove());

            //TODO change updateBoard method to return List of Messages to update Board about
            //TODO why not just allow board to dump messages on our inQ whenever? I thought that's what we proposed
            List<Message> out = board.updateBoard(0.01);
            board.printBoard();

            for(Message message:out)
                outQ.add(message);
        }
    }
}
