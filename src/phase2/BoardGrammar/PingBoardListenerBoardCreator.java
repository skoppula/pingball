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
import physics.Vect;

public class PingBoardListenerBoardCreator extends PingBoardBaseListener {
    
    private static String BOARD_NAME;
    private static double GRAVITY = 25;
    private static double FRICTION1 = 0.025;
    private static double FRICTION2 = 0.025;
    
    private static Board board;
    private static Map<String, Gadget> gadgetsMap = new HashMap<String, Gadget>(); // maps names to gadgets
    private static List<Ball> balls = new ArrayList<Ball>();
    
    // temporary variables
    Stack<List<String>> stack = new Stack<List<String>>();
    
    
    // creates the board
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
        System.out.println(stack);
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
        balls.add(ball);
    }
    
    // push the token with the name String on the stack
    public void enterYVelocity(PingBoardParser.YVelocityContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("yvelocity", valueString));
        System.out.println("yv" + stack);
    }
    
    // push the token with the name String on the stack
    public void enterXVelocity(PingBoardParser.XVelocityContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("xvelocity", valueString));
        System.out.println("xv" + stack);
    }
    
    // push the token with the name String on the stack
    public void enterFloatY(PingBoardParser.FloatYContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("floaty", valueString));
        System.out.println("floaty" + stack);
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
        System.out.println("g" + stack);
    }
    
    // push the token with the friction1 constant on the stack
    public void enterFriction1(PingBoardParser.Friction1Context ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("friction1", valueString));
        System.out.println("f1" + stack);
    }
    
    // push the token with the friction2 constant on the stack
    public void enterFriction2(PingBoardParser.Friction2Context ctx) {
        String friction2ValueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("friction2", friction2ValueString));
        System.out.println("f2" + stack);
    }

    // push the token with the name on the stack
    public void enterName(PingBoardParser.NameContext ctx) {
        String nameString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("name", nameString));
        System.out.println("name" + stack);
    }
    
    
    /**
     * @return Board parsed from the file
     */
    public static Board createBoard() {
        List<Gadget> gadgets = new ArrayList(gadgetsMap.values());
        board = new Board(gadgets, BOARD_NAME, GRAVITY, FRICTION1, FRICTION2);
        for (Ball ball: balls) {
            board.addBall(ball);
        }
        return board;
    }
}
