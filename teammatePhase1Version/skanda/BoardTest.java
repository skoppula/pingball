package phase1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import phase1.Util.InvalidInvariantException;
import physics.Geometry.DoublePair;
import physics.LineSegment;
import physics.Vect;

public class BoardTest {
	
	/*
	 * Testing Strategy:
	 * 1. toString: a.first test printing a small board with no gadgets 
	 * 				b.Print normal sized board (22X22) with a few gadgets
	 * 2. populatePointToObject: a. test with empty PointToObject
	 * 							 b. test one key mapping to one object
	 * 							 c. test multiple keys mapping to multiple objects
	 */
	private static int width = 20;
    private static int height = 20;

    private static LineSegment top = new LineSegment(0, 0, width - 1, 0);
    private static LineSegment left = new LineSegment(0, 0, 0, height - 1);
    private static LineSegment right = new LineSegment(width - 1, 0, height - 1, width - 1);
    private static LineSegment bottom = new LineSegment(0, height - 1, width - 1,
            height - 1);

    private static List<Boundary> boundaries = new ArrayList<Boundary>();
    private static List<SquareBumper> squares = new ArrayList<SquareBumper>();
    private static List<TriangleBumper> triangles = new ArrayList<TriangleBumper>();
    private static List<CircleBumper> circles = new ArrayList<CircleBumper>();
    private static List<Flipper> flippers = new ArrayList<Flipper>();
    private static List<Absorber> absorbers = new ArrayList<Absorber>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
        for (int x = 0; x < width; x++) {
            boundaries.add(new Boundary(x, 0, top));
            boundaries.add(new Boundary(x, height - 1, bottom));
        }
        for (int y = 0; y < height; y++) {
            boundaries.add(new Boundary(0, y, left));
            boundaries.add(new Boundary(width - 1, y, right));
        }
	}
	
	
	
	@Test
	public void testToStringSmallEmptyBoard() throws InvalidInvariantException {
		List<Ball> balls = new ArrayList<Ball>(Arrays.asList(new Ball(5,5,0.25,new Vect(0,0))));

		List<List<GameObject>> boardInput = Util.shallowCombineLists(balls, boundaries, squares, triangles, circles, flippers, absorbers);
		Board b = new Board(5,5,boardInput);
		String expected = ".......\n.     .\n.     .\n.     .\n.     .\n.     .\n.......";
		assertEquals(expected, b.toString());
	}
	
	@Test
	public void testToStringNormalSizedBoard() throws InvalidInvariantException {
		List<Ball> balls = new ArrayList<Ball>(Arrays.asList(new Ball(5,5,0.25,new Vect(0,0))));
		SquareBumper square = new SquareBumper(3,3);
		squares.add(square);
		List<TriangleBumper> triangles = new ArrayList<TriangleBumper>();
	    List<CircleBumper> circles = new ArrayList<CircleBumper>();
		List<List<GameObject>> boardInput = Util.shallowCombineLists(balls, boundaries, squares, triangles, circles, flippers, absorbers);
		Board b = new Board(22,22,boardInput);
		String expectedBoard = 
				 "........................\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "   #                  .\n."
				+ "                      .\n."
				+ "     *                .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ "                      .\n."
				+ ".......................";
		assertEquals(expectedBoard, b.toString());
	}
	
	@Test
	public void testPopulatePointToObjectEmpty() throws InvalidInvariantException {
		List<Ball> balls = new ArrayList<Ball>();
		List<SquareBumper> squares = new ArrayList<SquareBumper>();
		List<List<GameObject>> boardInput = Util.shallowCombineLists(balls, boundaries, squares, triangles, circles, flippers, absorbers);
		Board b = new Board(22,22,boardInput);
		
		HashMap<DoublePair, GameObject> expected = new HashMap<DoublePair, GameObject>();
		HashMap<DoublePair, GameObject> actual =b.populatePointToObject();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPopulatePointToObjectSingletonKey() throws InvalidInvariantException {
		List<Ball> balls = new ArrayList<Ball>(Arrays.asList(new Ball(10,10,0.25,new Vect(0,1))));
		List<SquareBumper> squares = new ArrayList<SquareBumper>();
	    List<TriangleBumper> triangles = new ArrayList<TriangleBumper>();
	    List<CircleBumper> circles = new ArrayList<CircleBumper>();
		List<List<GameObject>> boardInput = Util.shallowCombineLists(balls, boundaries, squares, triangles, circles, flippers, absorbers);
		Board b = new Board(22,22,boardInput);
		HashMap<DoublePair, GameObject> expected = new HashMap<DoublePair, GameObject>();
		Ball ball =new Ball(10,10,0.25, new Vect(0,1));
		expected.put(new DoublePair(10,10), ball);
		HashMap<DoublePair, GameObject> actual =b.populatePointToObject();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPopulatePointToObjectMultipleKeys() throws InvalidInvariantException {
		List<Ball> balls = new ArrayList<Ball>(Arrays.asList(new Ball(10,10,0.25,new Vect(0,1))));
		List<SquareBumper> squares = new ArrayList<SquareBumper>();
		SquareBumper square = new SquareBumper(3,3);
		squares.add(square);
		List<TriangleBumper> triangles = new ArrayList<TriangleBumper>();
	    List<CircleBumper> circles = new ArrayList<CircleBumper>();
	    CircleBumper circle =new CircleBumper(6,8);
        circles.add(circle);
		List<List<GameObject>> boardInput = Util.shallowCombineLists(balls, boundaries, squares, triangles, circles, flippers, absorbers);
	    Board b = new Board(22,22,boardInput);
		HashMap<DoublePair, GameObject> expected = new HashMap<DoublePair, GameObject>();
		Ball ball =new Ball(10,10,0.25, new Vect(0,1));
		expected.put(new DoublePair(10,10), ball);
		expected.put(new DoublePair(6,8), circle);
		expected.put(new DoublePair(3,3), square);
		HashMap<DoublePair, GameObject> actual =b.populatePointToObject();
		assertEquals(expected, actual);
	}

}
