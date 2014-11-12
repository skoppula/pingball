package phase2;
import phase2.Board.Ball;
import phase2.Board.Board;
import phase2.Board.CircleBumper;
import phase2.Board.Gadget;
import phase2.Board.SquareBumper;
import phase2.Board.TriangleBumper;
import phase2.Board.Gadget.Orientation;
import phase2.Board.Util.InvalidInvariantException;
import phase2.BoardGrammar.*;
import phase2.BoardGrammar.PingBoardParser.RootContext;
import phase2.Client.LocalManager;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import physics.Vect;

public class Pingball {
    
    private static final int DEFAULT_PORT = 4444;
    private static final int MAXIMUM_PORT = 65535;
    
    private static final long TIME_DELTA_MILLISECONDS = 1000/60;
    static double timeInSeconds = TIME_DELTA_MILLISECONDS * .001;

    /**
     * Updates the board every timeDelta Prints out the board every timeDelta
     * 
     * @throws InterruptedException
     * @throws IOException 
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // Board board = new Board("boardfile.txt");
        /*
        for (;;) {
            Thread.sleep(TIME_DELTA_MILLISECONDS);
            board.updateBoard(timeInSeconds);
            board.printBoard();
        }*/
        
        LocalManager lm = null;
        if(host.isPresent()) lm = new LocalManager(board, host.get(), port.get());
        else lm = new LocalManager(board);
        lm.runGame();
    }
    
    /**
     * @return benchmark board "default"
     */
    public static Board defaultBoard() {
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
        
        // Read in board files using ANTLR
        try {
            // make a stream of characters to feed to the lexer
            FileReader filereader = new FileReader(file);
            CharStream stream = new ANTLRInputStream(filereader);
            // pass the character stream to an instance of the generated lexer class
            PingBoardLexer lexer = new PingBoardLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // feed the stream of tokens we've generated to the parser
            PingBoardParser parser = new PingBoardParser(tokens);
            RootContext tree = parser.root();
            System.err.println(tree.toStringTree());
            tree.inspect(parser);
            
            // Visit each node in the parse tree in order,
            // top-to-bottom, left-to-right, calling methods that we want
            ParseTreeWalker walker = new ParseTreeWalker();
            PingBoardListener listener = new PingBoardListenerBoardCreator();
            walker.walk(listener,tree);
            Board board = PingBoardListenerBoardCreator.getBoard();
            return board;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
