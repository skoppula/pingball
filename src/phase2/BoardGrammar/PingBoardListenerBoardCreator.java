package phase2.BoardGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import jdk.nashorn.internal.parser.TokenStream;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import phase2.Board.*;
import phase2.Board.Gadget.Orientation;
import phase2.Board.Util.InvalidInvariantException;
import physics.Vect;

public class PingBoardListenerBoardCreator extends PingBoardBaseListener {
    
    private static String BOARD_NAME;
    private static double GRAVITY = 25;
    private static double FRICTION1 = 0.025;
    private static double FRICTION2 = 0.025;
    
    private static Board board;
    private Map<String, Gadget> gadgetsMap = new HashMap<String, Gadget>(); // maps names to gadgets
    private Map<String, Ball> ballsMap = new HashMap<String, Ball>(); // maps names to ball
    // private static List<Gadget> gadgets = new ArrayList<Gadget>();
    
    // temporary variables
    Stack<List<String>> stack = new Stack<List<String>>();
    static Stack<Board> boardStack = new Stack<Board>();
    
    // create the board
    public void exitBoard(PingBoardParser.BoardContext ctx) {
        List<Gadget> gadgets = new ArrayList(gadgetsMap.values());
        board = new Board(gadgets, BOARD_NAME, GRAVITY, FRICTION1, FRICTION2);
        for (Ball ball: ballsMap.values()) {
            board.addBall(ball);
        }
        boardStack.push(board);
    }
    
    // initialize the board's fields
    public void exitBoardInit(PingBoardParser.BoardInitContext ctx) {
        int numParameters = ctx.getChildCount()-1; // extra child on left says 'board'
        for (int i=0; i<numParameters; i++) {
            List<String> paraTypeAndValue = stack.pop();
            String type = paraTypeAndValue.get(0);
            String value = paraTypeAndValue.get(1);
            if (type.equals("name")) {
                BOARD_NAME =  value;
            }
            else if (type.equals("gravity")) {
                GRAVITY = Double.parseDouble(value);
            }
            else if (type.equals("friction1")) {
                FRICTION1 = Double.parseDouble(value);
            }
            else if (type.equalsIgnoreCase("friction2")) {
                FRICTION2 = Double.parseDouble(value);
            }
        }
    }
    
    // create a ball on exiting ball node, put it in gadgetsMap
    public void exitBall(PingBoardParser.BallContext ctx) {
        int numParameters = ctx.getChildCount()-1;
        String ballName = "";
        double xVelocity = 0;
        double yVelocity = 0;
        double x = 0;
        double y = 0;
        for (int i=0; i<numParameters; i++) {
            List<String> paraTypeAndValue = stack.pop();
            String type = paraTypeAndValue.get(0);
            String value = paraTypeAndValue.get(1);
            if (type.equals("name")) {
                ballName =  value;
            }
            else if (type.equals("floatx")) {
                x = Double.parseDouble(value);
            }
            else if (type.equals("floaty")) {
                y = Double.parseDouble(value);
            }
            else if (type.equalsIgnoreCase("xVelocity")) {
                xVelocity = Double.parseDouble(value);
            }
            else if (type.equalsIgnoreCase("yVelocity")) {
                yVelocity = Double.parseDouble(value);
            }
        }
        Vect ballVelocity = new Vect(xVelocity, yVelocity);
        Ball ball = new Ball(x, y, ballVelocity, ballName);
        ballsMap.put(ballName, ball);
    }
    
    
    // construct a squareBumper when leaving squareBumper node
    public void exitSquareBumper(PingBoardParser.SquareBumperContext ctx) {
        int numParameters = ctx.getChildCount()-1;
        String name = "";
        int x = 0;
        int y = 0;
        for (int i=0; i<numParameters; i++) {
            List<String> paraTypeAndValue = stack.pop();
            String type = paraTypeAndValue.get(0);
            String value = paraTypeAndValue.get(1);
            if (type.equals("name")) {
                name =  value;
            }
            else if (type.equals("x")) {
                x = Integer.parseInt(value);
            }
            else if (type.equals("y")) {
                y = Integer.parseInt(value);
            }
        }
        SquareBumper squareBumper;
        try {
            squareBumper = new SquareBumper(x, y, name);
            gadgetsMap.put(name, squareBumper);
        } catch (InvalidInvariantException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    // construct a flipper when leaving flipper node
    public void exitFlipper(PingBoardParser.FlipperContext ctx) {
        
    }
    
    // construct a circleBumper when leaving circleBumper node
    public void exitCircleBumper(PingBoardParser.CircleBumperContext ctx) {
        int numParameters = ctx.getChildCount()-1;
        String name = "";
        int x = 0;
        int y = 0;
        for (int i=0; i<numParameters; i++) {
            List<String> paraTypeAndValue = stack.pop();
            String type = paraTypeAndValue.get(0);
            String value = paraTypeAndValue.get(1);
            if (type.equals("name")) {
                name =  value;
            }
            else if (type.equals("x")) {
                x = Integer.parseInt(value);
            }
            else if (type.equals("y")) {
                y = Integer.parseInt(value);
            }
        }
        CircleBumper circleBumper;
        try {
            circleBumper = new CircleBumper(x, y, name);
            gadgetsMap.put(name, circleBumper);
        } catch (InvalidInvariantException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    // construct a triangleBumper when leaving triangleBumper node
    public void exitTriangleBumper(PingBoardParser.TriangleBumperContext ctx) {
        int numParameters = ctx.getChildCount()-1;
        String name = "";
        int x = 0;
        int y = 0;
        Orientation orientation = Orientation.ZERO;
        for (int i=0; i<numParameters; i++) {
            List<String> paraTypeAndValue = stack.pop();
            String type = paraTypeAndValue.get(0);
            String value = paraTypeAndValue.get(1);
            if (type.equals("name")) {
                name =  value;
            }
            else if (type.equals("x")) {
                x = Integer.parseInt(value);
            }
            else if (type.equals("y")) {
                y = Integer.parseInt(value);
            }
            else if (type.equals("orientation")) {
                int intValue = Integer.parseInt(value);
                if (intValue==0) {
                    orientation = Orientation.ZERO;
                }
                else if (intValue==90) {
                    orientation = Orientation.NINETY;
                }
                else if (intValue==180) {
                    orientation = Orientation.ONE_HUNDRED_EIGHTY;
                }
                else if (intValue==270) {
                    orientation = Orientation.TWO_HUNDRED_SEVENTY;
                }
            }
        }
        TriangleBumper triangleBumper;
        try {
            triangleBumper = new TriangleBumper(x, y, name, orientation);
            gadgetsMap.put(name, triangleBumper);
        } catch (InvalidInvariantException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    // construct a flipper when leaving flipper node
    public void exitFlipper(PingBoardParser.FlipperContext ctx) {
        int numParameters = ctx.getChildCount()-1;
        String name = "";
        int x = 0;
        int y = 0;
        Orientation orientation = Orientation.ZERO;
        for (int i=0; i<numParameters; i++) {
            List<String> paraTypeAndValue = stack.pop();
            String type = paraTypeAndValue.get(0);
            String value = paraTypeAndValue.get(1);
            if (type.equals("name")) {
                name =  value;
            }
            else if (type.equals("x")) {
                x = Integer.parseInt(value);
            }
            else if (type.equals("y")) {
                y = Integer.parseInt(value);
            }
            else if (type.equals("orientation")) {
                int intValue = Integer.parseInt(value);
                if (intValue==0) {
                    orientation = Orientation.ZERO;
                }
                else if (intValue==90) {
                    orientation = Orientation.NINETY;
                }
                else if (intValue==180) {
                    orientation = Orientation.ONE_HUNDRED_EIGHTY;
                }
                else if (intValue==270) {
                    orientation = Orientation.TWO_HUNDRED_SEVENTY;
                }
            }
        }
        TriangleBumper triangleBumper;
        try {
            triangleBumper = new TriangleBumper(x, y, name, orientation);
            gadgetsMap.put(name, triangleBumper);
        } catch (InvalidInvariantException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    // push orientation onto stack
    public void enterOrientation(PingBoardParser.OrientationContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("orientation", valueString));
    }
    
    // push the token with the name String on the stack
    public void enterYVelocity(PingBoardParser.YVelocityContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("yvelocity", valueString));
    }
    
    // push the token with the name String on the stack
    public void enterXVelocity(PingBoardParser.XVelocityContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("xvelocity", valueString));
    }
    
    // push the token with the name String on the stack
    public void enterFloatY(PingBoardParser.FloatYContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("floaty", valueString));
    }
    
    // push the token with the name String on the stack
    public void enterFloatX(PingBoardParser.FloatXContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("floatx", valueString));
    }
    
    // push the token with the gravity constant on the stack
    public void enterGravity(PingBoardParser.GravityContext ctx) {
        String gravityValueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("gravity", gravityValueString));
    }
    
    // push the token with the friction1 constant on the stack
    public void enterFriction1(PingBoardParser.Friction1Context ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("friction1", valueString));
    }
    
    // push the token with the friction2 constant on the stack
    public void enterFriction2(PingBoardParser.Friction2Context ctx) {
        String friction2ValueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("friction2", friction2ValueString));
    }

    // push the token with the name on the stack
    public void enterName(PingBoardParser.NameContext ctx) {
        String nameString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("name", nameString));
    }
    
    // push gadget x-position onto the stack
    public void enterIntX(PingBoardParser.IntXContext ctx) {
        String xString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("x", xString));
    }
    
    // push gadget x-position onto the stack
    public void enterIntY(PingBoardParser.IntYContext ctx) {
        String yString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("y", yString));
    }

    
    /**
     * @return Board parsed from the file
     */
    public static Board getBoard() {
        Board board = boardStack.pop();
        return board;
    }
}
