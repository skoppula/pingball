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
    public static List<Gadget> combineLists(List ... lists){

        List<Gadget> outList = new ArrayList<Gadget>();

        for(List<Gadget> list:lists)
            outList.addAll(list);

        return outList;
    }

    
    /**
     * Function that takes all the input lists and outputs them in a single list of game objects
     * @param lists are the lists of separate game objects
     * @return a list of all game objects
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<List<Gadget>> shallowCombineLists(List ... lists){

        List<List<Gadget>> outList = new ArrayList<List<Gadget>>();

        for(List<Gadget> list:lists)
            outList.add(list);

        return outList;
    }
    

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to boundaries
     */
    public static List<Wall> convertListToBoundary(List<Gadget> gameObjects) {
        List<Wall> out = new ArrayList<Wall>();
        for(Gadget o:gameObjects) {
            out.add((Wall) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to triangle bumpers
     */
    public static List<TriangleBumper> convertListToTriangleBumper(List<Gadget> gameObjects) {
        List<TriangleBumper> out = new ArrayList<TriangleBumper>();
        for(Gadget o:gameObjects) {
            out.add((TriangleBumper) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to circle bumpers
     */
    public static List<CircleBumper> convertListToCircleBumper(List<Gadget> gameObjects) {
        List<CircleBumper> out = new ArrayList<CircleBumper>();
        for(Gadget o:gameObjects) {
            out.add((CircleBumper) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to square bumpers
     */
    public static List<SquareBumper> convertListToSquareBumper(List<Gadget> gameObjects) {
        List<SquareBumper> out = new ArrayList<SquareBumper>();
        for(Gadget o:gameObjects) {
            out.add((SquareBumper) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to absorbers
     */
    public static List<Absorber> convertListToAbsorber(List<Gadget> gameObjects) {
        List<Absorber> out = new ArrayList<Absorber>();
        for(Gadget o:gameObjects) {
            out.add((Absorber) o);
        }
        return out;
    }

    /**
     * Function that takes a list of game objects and converts them to the specific type of object they represent
     * @param gameObjects - a list of gadgets 
     * @return a list of same gadgets converted to flippers
     */
    public static List<Flipper> convertListToFlipper(List<Gadget> gameObjects) {
        List<Flipper> out = new ArrayList<Flipper>();
        for(Gadget o:gameObjects) {
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
