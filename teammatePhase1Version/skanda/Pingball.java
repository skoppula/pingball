package phase1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import phase1.Flipper.FlipperRotation;
import phase1.Flipper.FlipperTypeExternal;
import phase1.GameObject.UnsupportedOperationException;
import phase1.TriangleBumper.TriangleBumperType;
import phase1.Util.InvalidInvariantException;
import physics.LineSegment;
import physics.Vect;

/**
 * Creates a PingBall game simulation
 */

public class Pingball {
    
    /**
     * @return the dimensions of our playing board
     */
    
    public static int[] getBoardDimensions() {
        final int WIDTH = 20;
        final int HEIGHT = 20;
        return new int[]{HEIGHT, WIDTH};
    }

    /**
     * @return the board objects in the Default board
     * @throws InvalidInvariantException 
     */
    public static List<List<GameObject>> getDefaultBoardObjects() throws InvalidInvariantException {

        int width = getBoardDimensions()[0];
        int height = getBoardDimensions()[1];

        LineSegment top = new LineSegment(0, 0, width, 0);
        LineSegment left = new LineSegment(0, 0, 0, height);
        LineSegment right = new LineSegment(width, 0, height, width);
        LineSegment bottom = new LineSegment(0, height, width, height);

        List<Boundary> boundaries = new ArrayList<Boundary>();

        for (int x = 0; x <= width; x++) {
            boundaries.add(new Boundary(x, 0, top));
            boundaries.add(new Boundary(x, height, bottom));
        }

        for (int y = 0; y <= height; y++) {
            boundaries.add(new Boundary(0, y, left));
            boundaries.add(new Boundary(width , y, right));
        }

        List<Ball> balls = new ArrayList<Ball>(Arrays.asList(new Ball(1.25, 1.25, .25, new Vect(0, 0))));
        List<Flipper> flippers = new ArrayList<Flipper>();
        List<Absorber> absorbers = new ArrayList<Absorber>();
        List<SquareBumper> squares = new ArrayList<SquareBumper>(Arrays.asList(new SquareBumper(0, 17), new SquareBumper(1, 17), new SquareBumper(2, 17)));
        List<CircleBumper> circles = new ArrayList<CircleBumper>(Arrays.asList(new CircleBumper(1, 10), new CircleBumper(7, 18), new CircleBumper(8, 18), new CircleBumper(9, 18)));
        List<TriangleBumper> triangles = new ArrayList<TriangleBumper>(Arrays.asList(new TriangleBumper(12, 15, TriangleBumperType.DOWNRIGHT)));
        
        return Util.shallowCombineLists(balls, boundaries, squares, triangles, circles, flippers, absorbers);
    }
    
    /**
     * @return the board objects in the Absorber board
     * @throws InvalidInvariantException 
     */
    public static List<List<GameObject>> getAbsorberBoardObjects() throws InvalidInvariantException {

        int width = getBoardDimensions()[0];
        int height = getBoardDimensions()[1];

        LineSegment top = new LineSegment(0, 0, width, 0);
        LineSegment left = new LineSegment(0, 0, 0, height);
        LineSegment right = new LineSegment(width, 0, height, width);
        LineSegment bottom = new LineSegment(0, height, width, height);

        List<Boundary> boundaries = new ArrayList<Boundary>();

        for (int x = 0; x <= width; x++) {
            boundaries.add(new Boundary(x, 0, top));
            boundaries.add(new Boundary(x, height, bottom));
        }

        for (int y = 0; y <= height; y++) {
            boundaries.add(new Boundary(0, y, left));
            boundaries.add(new Boundary(width , y, right));
        }

        List<Ball> balls = new ArrayList<Ball>(Arrays.asList(new Ball(10.25, 15.25, .25, new Vect(0, 0)), new Ball(19.25, 3.25, .25, new Vect(0, 0)), new Ball(1.25, 5.25, .25, new Vect(0, 0))));
        List<Flipper> flippers = new ArrayList<Flipper>();
        List<Absorber> absorbers = new ArrayList<Absorber>(Arrays.asList(new Absorber(0, 18, 20, 2)));
        List<SquareBumper> squares = new ArrayList<SquareBumper>();
        List<CircleBumper> circles = new ArrayList<CircleBumper>(Arrays.asList(new CircleBumper(1, 10), new CircleBumper(2, 10), new CircleBumper(3, 10), new CircleBumper(4, 10), new CircleBumper(5, 10)));
        List<GameObject> circleTriggers = Arrays.asList(absorbers.get(0));
        circles.get(0).setTriggers(circleTriggers);
        circles.get(1).setTriggers(circleTriggers);
        circles.get(2).setTriggers(circleTriggers);
        circles.get(3).setTriggers(circleTriggers);
        circles.get(4).setTriggers(circleTriggers);
        List<TriangleBumper> triangles = new ArrayList<TriangleBumper>(Arrays.asList(new TriangleBumper(19, 0, TriangleBumperType.UPRIGHT)));
        
        return Util.shallowCombineLists(balls, boundaries, squares, triangles, circles, flippers, absorbers);
    }
    
    /**
     * @return the board objects in the Flippers board
     * @throws InvalidInvariantException 
     */
    public static List<List<GameObject>> getFlipperBoardObjects() throws InvalidInvariantException {

        int width = getBoardDimensions()[0];
        int height = getBoardDimensions()[1];

        LineSegment top = new LineSegment(0, 0, width, 0);
        LineSegment left = new LineSegment(0, 0, 0, height);
        LineSegment right = new LineSegment(width, 0, height, width);
        LineSegment bottom = new LineSegment(0, height, width, height);

        List<Boundary> boundaries = new ArrayList<Boundary>();

        for (int x = 0; x <= width; x++) {
            boundaries.add(new Boundary(x, 0, top));
            boundaries.add(new Boundary(x, height, bottom));
        }

        for (int y = 0; y <= height; y++) {
            boundaries.add(new Boundary(0, y, left));
            boundaries.add(new Boundary(width , y, right));
        }

        List<Ball> balls = new ArrayList<Ball>(Arrays.asList(new Ball(0.25,
                3.25, .25, new Vect(0, 0)), new Ball(5.25, 3.25, .25, new Vect(
                0, 0)), new Ball(10.25, 3.25, .25, new Vect(0, 0)), new Ball(
                15.25, 3.25, .25, new Vect(0, 0)), new Ball(19.25, 3.25, .25,
                new Vect(0, 0))));

        List<Flipper> flippers = new ArrayList<Flipper>(Arrays.asList(
                new Flipper(0, 8, FlipperTypeExternal.LEFT, FlipperRotation.NINETY, balls),
                new Flipper(4, 10, FlipperTypeExternal.LEFT, FlipperRotation.NINETY, balls),
                new Flipper(9, 8, FlipperTypeExternal.LEFT, FlipperRotation.NINETY, balls),
                new Flipper(15, 8, FlipperTypeExternal.LEFT, FlipperRotation.NINETY, balls),
                new Flipper(2, 15, FlipperTypeExternal.RIGHT, FlipperRotation.ZERO, balls),
                new Flipper(17, 15, FlipperTypeExternal.RIGHT, FlipperRotation.ZERO, balls)));

        List<Absorber> absorbers = new ArrayList<Absorber>(Arrays.asList(new Absorber(0, 19, 20, 1)));
        absorbers.get(0).setTriggers(Arrays.asList(flippers.get(4), flippers.get(5), absorbers.get(0)));
        
        List<SquareBumper> squares = new ArrayList<SquareBumper>();
        List<GameObject> circleTriggerOne = Arrays.asList(flippers.get(0));
        List<GameObject> circleTriggerTwo = Arrays.asList(flippers.get(2));
        List<GameObject> circleTriggerThree = Arrays.asList(flippers.get(3));
        List<CircleBumper> circles = new ArrayList<CircleBumper>(Arrays.asList(
                new CircleBumper(5, 18),
                new CircleBumper(7, 13),
                new CircleBumper(0, 5),
                new CircleBumper(5, 5),
                new CircleBumper(10, 5),
                new CircleBumper(15, 5)));
        circles.get(2).setTriggers(circleTriggerOne);
        circles.get(4).setTriggers(circleTriggerTwo);
        circles.get(5).setTriggers(circleTriggerThree);

        List<TriangleBumper> triangles = new ArrayList<TriangleBumper>(Arrays.asList(
                new TriangleBumper(19, 0, TriangleBumperType.UPRIGHT),
                new TriangleBumper(10, 18, TriangleBumperType.DOWNRIGHT)));

        return Util.shallowCombineLists(balls, boundaries, squares, triangles, circles, flippers, absorbers);
    }
    
    /**
     * The main function runs a pingball simulation on a board of input
     * @param args - the command line arguments; the first argument represents the board name
     * @throws InterruptedException
     * @throws UnsupportedOperationException
     * @throws InvalidBoardNameException
     * @throws InvalidInvariantException 
     */
    public static void main(String[] args) throws InterruptedException, UnsupportedOperationException, InvalidBoardNameException, InvalidInvariantException {
        
        int height = getBoardDimensions()[0];
        int width = getBoardDimensions()[1];
        final int ITERATIONS = 1000;
        final int SLEEP_TIME = 50;
        List<List<GameObject>> gameObjects;

        String boardType = args.length == 0 ? "default" : args[0];

        switch (boardType) {
            case "default": gameObjects = getDefaultBoardObjects(); break;
            case "absorber": gameObjects = getAbsorberBoardObjects(); break;
            case "flippers": gameObjects = getFlipperBoardObjects(); break;
            default: throw new InvalidBoardNameException();
        }

        Board board = new Board(height, width, gameObjects);
        System.out.println(board);

        for(int iterations = 0; iterations < ITERATIONS; iterations++) {
            board.oneFrameActions();
            System.out.println(board);
            Thread.sleep(SLEEP_TIME);
        }
        
    }

    /**
     * Class for Invalid Board Exception
     */
    public static class InvalidBoardNameException extends Exception {
        private static final long serialVersionUID = 1L;
        
    }
}
