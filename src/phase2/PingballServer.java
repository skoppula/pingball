package phase2;

import java.io.*;
import java.net.*;
import java.util.*;

import phase2.Board.Board;
import phase2.Messaging.Message;
import phase2.Server.NewConnectionHandler;

public class PingballServer {

    private static final int DEFAULT_PORT = 4444;
    private static final int MAXIMUM_PORT = 65535;

    private final ServerSocket serverSocket;

    //TODO make these types threadsafe
    protected HashMap<String, CommunicationTunnel> tunnels = new HashMap<String, CommunicationTunnel>();
    protected HashMap<String, String> wallConnections = new HashMap<String, String>();
    
    Queue<Message> inQ;
    Queue<Message> outQ;
    
    private NewConnectionHandler nch;
    private ConsoleInputManager cim;
    private QueueProcessor qp;
    private Mailman mm;
    
    private PingballServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.inQ = new LinkedList<Message>();
        this.outQ = new LinkedList<Message>();
    }
    
    private void server() {
        nch = new NewConnectionHandler(serverSocket);
        nch.run();

    }

    public static void main(String[] args) throws IOException {

        int port = DEFAULT_PORT;
        if (args[0].equals("--port") && args.length==2) {
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
