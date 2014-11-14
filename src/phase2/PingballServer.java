package phase2;

/**
 * PingballServer is a server for a multi-user pingball game.
 * It accepts requests of the form:
 *      v board1 board2 (to connect the boards board1 and board2 vertically)
 *      h board1 board2 (to connect the boards board1 and board2 horizontally)

 * Overall, the purpose of the server is to coordinate the communication and transmission
 * of balls and information between the clients on the network.
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

/**
 * AF: Runs the server-side control of a multi-client pingball game.
 */
public class PingballServer {
	
	/*
	 * Thread safety argument:
	 * PingballServer achieves thread-safety by concentrating all of the shared-memory
	 * accesses in the same thread, to be executed sequentially.
	 * The program is composed of two parts, client and server. We will first discuss
	 * their internal thread-safety, and then talk about how they interact and the impossibility of deadlock.
	 * 
	 * The client is structured as follows:
	 * There are three parallel threads, connected by queues (shown as -q->)
	 * Socket input --> LocalInputHandler -q-> LocalManager -q-> LocalOutputHandler --> Socket output
	 * 
	 * LocalInputManager is responsible for taking data from the input stream, converting it to
	 * messages, and then passing the messages into a queue, which leads to LocalManager.
	 * 
	 * LocalOutputManager is responsible for taking any messages passed into it by a queue, and
	 * running them out the socket as strings.
	 * 
	 * LocalManager takes the messages from a queue feeding into it, passes them into board 
	 * (which updates its state), and then steps board forward. If board needs to, it places
	 * messages on a queue to LocalOutputManager, to go to the server.
	 * 
	 * None of these threads are mutually dependent on each others' actions, and they're separated
	 * by queues which take care of the synchronization (and are the only form of shared memory),
	 * so the client is threadsafe!
	 * 
	 * The server is structured as follows:
	 * There are four major thread regions, each of which contribute to the operation of the server:
	 * 
	 * NewConnectionHandler					ConsoleInputManager
	 * 					||							||
	 * 					\/							||
	 * socket in --> CommunicationTunnel -q-> 		\/		 -q-> CommunicationTunnel --> socket out
	 * socket in --> CommunicationTunnel -q-> QueueProcessor -q-> CommunicationTunnel --> socket out
	 * socket in --> CommunicationTunnel -q-> 				 -q-> CommunicationTunnel --> socket out
	 * 
	 * As is evident in the diagram, every message in the program is passed through QueueProcessor.
	 * With the exception of CommunicationTunnel (which handles BoardInitMessages in its constructor
	 * and can add keyvalue pairs to boardToCommunicationTunnelMap) there is no shared memory anywhere
	 * in the program other than in the queues. Each queue only has one thread ever able to take things
	 * off of them, so there's no chance of a race condition in terms of message acquisition. Also,
	 * we don't care about the order of messages placed on the queue (so long as if they are sent from
	 * the same source, they are processed sequentially, which is true everywhere in our program).
	 * 
	 * NewConnectionHandler listens to a port, and every time it finds a new socket, it creates a
	 * communicationTunnel in a new thread.
	 * 
	 * CommunicationTunnel handles the input and output from the server. It contains two asynchronous
	 * threads, which, much like LocalInputHandler and LocalOutputHandler, take new messages from sockets
	 * and place them on the QueueProcessor's input queue, and also receive messages and push them
	 * out of the socket. When the socket closes, it sends a terminateMessage to QueueProcessor.
	 * The one exception is BoardInitMessage, which is caught by CommunicationTunnel
	 * as the first message sent, and allows CommunicationTunnel to add itself to the 
	 * boardToCommunicationTunnelMap. However, this action is on a threadsafe type, and any calls to
	 * boardToCommunicationTunnelMap.get(boardName) will necessarily occur after this action, because
	 * no other messages in the program should gain access to the name "boardName."
	 * 
	 * ConsoleInputManager listens to the program's standard input, and if the input is a 
	 * correct wall connect message, it converts it to a message and passes that message to
	 * QueueProcessor's queue. Nothing about this can possible be un-threadsafe.
	 * 
	 * Our final thread is QueueProcessor, which is in charge of handling the messages in the program
	 * and sending correct outgoing messages. It receives the messages that various sources give it
	 * sequentially on its input queue, handles whatever message based off the sort of message, and
	 * places any outgoing messages on the queues leading to the client's respective CommunicationTunnel,
	 * as specified in boardToCommunicationTunnelMap.
	 * 
	 * As is visible, there are no cyclic dependencies in terms of memory to be accessed. Only
	 * a single thread ever takes anything off of any given queue, and the only other shared memory
	 * is only accessed by one party (QueueProcessor) though it is added to by another (CommunicationTunnel).
	 * We surmise that our Server is threadsafe!
	 * 
	 * The only question left to ask is whether the relationship between client and server is threadsafe.
	 * We have designed the system such that clients can send and receive all of their messages
	 * asynchronously, so ordering doesn't matter (and the program doesn't block). Server only ever
	 * acts based on individual messages (other than the init message, but it is hard-coded to be first).
	 * Therefore, the server will never wait for another message or anything else, and so will not block.
	 * 
	 * We surmise that our system is threadsafe!
	 * Mad props for reading all that.
	 * TL;DR Qz r c00l
	 * 
	 */

    private static final int DEFAULT_PORT = 4444;
    private static final int MAXIMUM_PORT = 65535;

    private final ServerSocket serverSocket;
    
    protected HashSet<String> waitlist;
    
    BlockingQueue<Message> inQ;
    
    //When they ask me how I code so quick, I say poon-lickin
    //Don't doubt my shit, you'll have lisa and yo bitchin
    
    private Thread nch;
    private Thread cim;
    private Thread qp;
    
    private PingballServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.inQ = new LinkedBlockingDeque<Message>();
    }
    
    private void serve() {
        nch = new Thread(new NewConnectionHandler(serverSocket, inQ));
        nch.start();
        cim = new Thread(new ConsoleInputManager(inQ));
        cim.start();
        qp = new Thread(new QueueProcessor(inQ));
        qp.start();
    }

    public static void main(String[] args) throws IOException {

        int port = DEFAULT_PORT;
        if (args.length==0) {
            System.out.println("Using default port " + port);
        } else if (args[0].equals("--port") && args.length==2) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + port);
            }

            if (port < 0 || port > MAXIMUM_PORT) {
                throw new IllegalArgumentException("port " + port + " out of range");
            }

        } else throw new IllegalArgumentException("Too many arguments");
        
        runPingballServer(port);
    }

    public static void runPingballServer(int port) throws IOException {
        PingballServer server = null;
        server = new PingballServer(port);
        server.serve();
    }
    
}
