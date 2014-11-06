package warmup;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BoardMatrixTest {

    /*
     * addTile testing strategy
     * character added: ' ', '#'
     * location of character added:
     * (given a board with size 1)
     * x = 0, y = 0
     * (given a board size of 20)
     * x: 0, 5, 19, 
     * y: 0, 5, 19
     * character located at tile before conversion: ' ', '%'
     */
    
    
    /*
     * addTiles testing strategy
     * number of locations: 0, 1, 2
     * overlapping locations
     */
    
    /*
     * printString
     * blank matrix
     * matrix with one character in it
     * matrix with two characters in it
     * matrix with size 1
     */
    
    @Test
    public void printStringBlankMatrix(){
        BoardMatrix boardMatrix = new BoardMatrix();
        String correctString = ""
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "                      \n"
                + "\n";
        assertEquals(correctString, boardMatrix.printString());
    }
    
    @Test
    public void printStringOneCharMatrix(){
        BoardMatrix boardMatrix = new BoardMatrix();
        List<Gadget> outerWalls = OuterWall.makeWalls();
        for(Gadget wall: outerWalls){
            boardMatrix.addTiles(wall.getGridSymbolRep());
        }
        
        String correctString = "......................\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +".                    .\n"
                +"......................\n"
                +"\n";

        assertEquals(correctString, boardMatrix.printString());
    }
    
    @Test
    public void printStringTwoCharMatrix(){
        BoardMatrix boardMatrix = new BoardMatrix();
        GridSymbol first = new GridSymbol(3, 5, 'F'); 
        GridSymbol second = new GridSymbol(4, 5, 'F'); 
        List<GridSymbol> listOfSymbols = new ArrayList<>(Arrays.asList(first, second)); 
        boardMatrix.addTiles(listOfSymbols);
        
        String correctString = "                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"    FF                \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"                      \n"
                +"\n";

        assertEquals(correctString, boardMatrix.printString());
    }
    
    

}
