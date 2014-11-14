package phase2.BoardGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import phase2.Board.*;
import phase2.Board.Flipper.BumperSide;
import phase2.Board.Gadget.Orientation;
import phase2.Board.Util.InvalidInvariantException;
import physics.Vect;

public class PingBoardListenerBoardCreator extends PingBoardBaseListener {
    
    private String BOARD_NAME;
    private double GRAVITY = 25;
    private double FRICTION1 = 0.025;
    private double FRICTION2 = 0.025;
    
    private Map<String, Gadget> gadgetsMap = new HashMap<String, Gadget>(); // maps names to gadgets
    private Map<String, Ball> ballsMap = new HashMap<String, Ball>(); // maps names to ball
    private Map<String, ArrayList<String>> triggerToAction = new HashMap<String, ArrayList<String>>(); // key = name of gadget whose trigger it is, value = name of gadget whose action performed
    private ArrayList<Object> boardIngredientsList = new ArrayList<Object>(); 
    
    // temporary variables
    Stack<List<String>> stack = new Stack<List<String>>();
    
    // create the board
    public void exitBoard(PingBoardParser.BoardContext ctx) {
        List<Gadget> gadgets = new ArrayList<Gadget>(gadgetsMap.values());
        for (Gadget gadget: gadgets) {
            if (triggerToAction.containsKey(gadget.getName())) {
                ArrayList<Gadget> gadgetsToTrigger = new ArrayList<Gadget>();
                for (String nameOfActionGadget : triggerToAction.get(gadget.getName())) {
                    Gadget nextActionGadget = gadgetsMap.get(nameOfActionGadget);
                    gadgetsToTrigger.add(nextActionGadget);
                }
                gadget.setGadgetsToTrigger(gadgetsToTrigger);
            }
        }
        boardIngredientsList.addAll(Arrays.asList(gadgets, BOARD_NAME, GRAVITY, FRICTION1, FRICTION2, ballsMap.values()));
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
                System.out.println("friction1" + FRICTION1);
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
    
    // link gadgets that trigger each other
    public void exitFire(PingBoardParser.FireContext ctx) {
        int numParameters = ctx.getChildCount()-1;
        String triggerName = "";
        String actionName = "";
        for (int i=0; i<numParameters; i++) {
            List<String> paraTypeAndValue = stack.pop();
            String type = paraTypeAndValue.get(0);
            String value = paraTypeAndValue.get(1);
            if (type.equals("trigger")) {
                triggerName = value;
            }
            else if (type.equals("action")) {
                actionName = value;
            }
        }
        if (triggerToAction.containsKey(triggerName)) {
            triggerToAction.get(triggerName).add(actionName);
        }
        else {
            ArrayList<String> namesToTrigger = new ArrayList<String>();
            namesToTrigger.add(actionName);
            triggerToAction.put(triggerName, namesToTrigger);
        }
        System.out.println(triggerToAction);
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
            e.printStackTrace();
        }
    }
    
    // construct a flipper when leaving flipper node
    public void exitFlipper(PingBoardParser.FlipperContext ctx) {
        // left or right flipper?
        BumperSide flipperType;
        String flipperTypeStr = ctx.getChild(0).getText();
        if (flipperTypeStr.equals("leftFlipper")) {
            flipperType = BumperSide.LEFT;
        }
        else {
            flipperType = BumperSide.RIGHT;
        }
        
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
        Flipper flipper = new Flipper(x, y, name, flipperType, orientation);
        gadgetsMap.put(name, flipper);
    }
    
    // construct an absorber when leaving absorber node
    public void exitAbsorber(PingBoardParser.AbsorberContext ctx) {
        int numParameters = ctx.getChildCount()-1;
        String name = "";
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
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
            else if (type.equals("width")) {
                width = Integer.parseInt(value);
            }
            else if (type.equals("height")) {
                height = Integer.parseInt(value);
            }
        }
        Absorber absorber;
        try {
            absorber = new Absorber(x, y, name, width, height);
            gadgetsMap.put(name, absorber);
        } catch (InvalidInvariantException e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
        }
    }

    // push trigger gadget name onto stack
    public void enterTrigger(PingBoardParser.TriggerContext ctx) {
        String trigger = ctx.getChild(3).getText();
        stack.push(Arrays.asList("trigger", trigger));
    }
    
    // push action gadget name onto stack
    public void enterAction(PingBoardParser.ActionContext ctx) {
        String action = ctx.getChild(3).getText();
        stack.push(Arrays.asList("action", action));
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
    
    // push gadget width onto the stack
    public void enterWidth(PingBoardParser.WidthContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("width", valueString));
    }
    
    // push gadget height onto the stack
    public void enterHeight(PingBoardParser.HeightContext ctx) {
        String valueString = ctx.getChild(3).getText();
        stack.push(Arrays.asList("height", valueString));
    }

    
    /**
     * @return Board parsed from the file
     */
    public ArrayList<Object> getBoardIngredients() {
        return boardIngredientsList;
    }
}
