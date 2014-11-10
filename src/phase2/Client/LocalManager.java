package phase2.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import phase2.Board.Board;
import phase2.Messaging.*;

public class LocalManager {

    //TODO make these types threadsafe, especially lim, lom
    private Board board;
    private LocalInputManager lim;
    private LocalOutputManager lom;
    protected Queue<Message> inQ;
    protected Queue<Message> outQ;
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

    public LocalManager(Board board, InetAddress address, int port) throws IOException {
        this.inQ = new LinkedList<Message>();
        this.outQ = new LinkedList<Message>();
        this.board = board;
        this.networkedGame = true;
        
        Socket socket = new Socket(address, port);

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
    
    public void runGame() {
        while(true) {
            while(!inQ.isEmpty())
                //TODO update the state of the board according to an incoming message
                board.syncChange(inQ.remove());

            //TODO change updateBoard method to return List of Messages to update Board about
            List<Message> out = board.updateBoard(0.01);
            board.printBoard();

            for(Message message:out)
                outQ.add(message);
        }
    }
}
