package phase2;

import java.util.ArrayList;
import java.util.List;

public class Util {

    /**
     * Function that takes all the input lists and outputs them in a single list of game objects
     * @param lists are the lists of separate game objects
     * @return a list of all game objects
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<GameObject> combineLists(List ... lists){

        List<GameObject> outList = new ArrayList<GameObject>();

        for(List<GameObject> list:lists)
            outList.addAll(list);

        return outList;
    }

    
    /**
     * Function that takes all the input lists and outputs them in a single list of game objects
     * @param lists are the lists of separate game objects
     * @return a list of all game objects
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<List<GameObject>> shallowCombineLists(List ... lists){

        List<List<GameObject>> outList = new ArrayList<List<GameObject>>();

        for(List<GameObject> list:lists)
            outList.add(list);

        return outList;
    }
    
    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to balls
     */
    public static List<Ball> convertListToBall(List<GameObject> gameObjects) {
        List<Ball> out = new ArrayList<Ball>();
        for(GameObject o:gameObjects) {
            out.add((Ball) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to boundaries
     */
    public static List<Boundary> convertListToBoundary(List<GameObject> gameObjects) {
        List<Boundary> out = new ArrayList<Boundary>();
        for(GameObject o:gameObjects) {
            out.add((Boundary) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to triangle bumpers
     */
    public static List<TriangleBumper> convertListToTriangleBumper(List<GameObject> gameObjects) {
        List<TriangleBumper> out = new ArrayList<TriangleBumper>();
        for(GameObject o:gameObjects) {
            out.add((TriangleBumper) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to circle bumpers
     */
    public static List<CircleBumper> convertListToCircleBumper(List<GameObject> gameObjects) {
        List<CircleBumper> out = new ArrayList<CircleBumper>();
        for(GameObject o:gameObjects) {
            out.add((CircleBumper) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to square bumpers
     */
    public static List<SquareBumper> convertListToSquareBumper(List<GameObject> gameObjects) {
        List<SquareBumper> out = new ArrayList<SquareBumper>();
        for(GameObject o:gameObjects) {
            out.add((SquareBumper) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to absorbers
     */
    public static List<Absorber> convertListToAbsorber(List<GameObject> gameObjects) {
        List<Absorber> out = new ArrayList<Absorber>();
        for(GameObject o:gameObjects) {
            out.add((Absorber) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to flippers
     */
    public static List<Flipper> convertListToFlipper(List<GameObject> gameObjects) {
        List<Flipper> out = new ArrayList<Flipper>();
        for(GameObject o:gameObjects) {
            out.add((Flipper) o);
        }
        return out;
    }
    
    /**
     * Class for Invalid Invariant Exception
     */
    public static class InvalidInvariantException extends Exception {
        private static final long serialVersionUID = 1L;
        
    }
}
