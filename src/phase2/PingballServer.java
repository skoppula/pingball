package phase2;

/**
 * PingballServer is a server.
 * It accepts requests of the form:
 *      Request ::= Number "\n"
 *      Number ::= [0-9]+
 * and for each request, returns a reply of the form:
 *      Reply ::= (Number | "err") "\n"
 * where a Number is the square of the request number,
 * or "err" is used to indicate a misformatted request.
 * TODO FIXME SquareServer can handle only one client at a time.
 */
    
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import phase2.Messaging.Message;
import phase2.Server.CommunicationTunnel;
import phase2.Server.ConsoleInputManager;
import phase2.Server.NewConnectionHandler;
import phase2.Server.QueueProcessor;

public class PingballServer {

    private static final int DEFAULT_PORT = 4444;
    private static final int MAXIMUM_PORT = 65535;

    private final ServerSocket serverSocket;
    
    //TODO make these types threadsafe
    protected HashMap<String, CommunicationTunnel> tunnels;
    protected HashMap<String, String> wallConnections;
    protected HashSet<String> waitlist;
    
    BlockingQueue<Message> inQ;
    BlockingQueue<Message> outQ;
    
    //When they ask me how I code so quick, I say poon-lickin
    //Don't doubt my shit, you'll have lisa and yo bitchin
    
    private NewConnectionHandler nch;
    private ConsoleInputManager cim;
    private QueueProcessor qp;
    
    private PingballServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.tunnels = new HashMap<String, CommunicationTunnel>();
        this.wallConnections = new HashMap<String, String>();
        this.inQ = new LinkedBlockingDeque<Message>();
        this.outQ = new LinkedBlockingDeque<Message>();
    }
    
    private void serve() {
        nch = new NewConnectionHandler(serverSocket, tunnels, inQ);
        nch.run();
        cim = new ConsoleInputManager(inQ);
        cim.run();
        qp = new QueueProcessor(outQ);
        qp.run();
    }

    public static void main(String[] args) throws IOException {

        int port = DEFAULT_PORT;
        if (args.length==2 && args[0].equals("--port")) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + port);
            }
            if (port < 0 || port > MAXIMUM_PORT) {
                throw new IllegalArgumentException("port " + port + " out of range");
            }
        } else if (args.length == 0) {
            System.out.println("Using default port " + port);
        } else throw new IllegalArgumentException("Too many arguments");
        
        runPingballServer(port);
    }

    public static void runPingballServer(int port) throws IOException {
        PingballServer server = null;
        server = new PingballServer(port);
        server.serve();
    }
    
}
