package warmup;

import java.util.List;

/**
 * @author Yo Shavit
 * This is a mutable class used in the graphical representation of a Board.
 */

/* 
 * Rep. Invariant: A BoardMatrix is valid if: 
 *   it contains no newlines as tile Symbols 
 *   It has width 20L and height 20L
 */
class BoardMatrix {

    private int BOARD_SIZE;
    // The number of tiles in the board.
    
    private Character[][] matrix;
    // The array that contains the representation of items placed on the board.
    // Boards are always square.
    // Does not include outer walls. 
    // Initialized to all spaces, and should never have two different elements
    // try to appear on same location.
    
    /**
     * Create a new blank BoardMatrix of board size 20.
     */
    public BoardMatrix(){
        int INNER_BOARD_SIZE = 20;
        BOARD_SIZE = INNER_BOARD_SIZE + 2;
        matrix = new Character[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE; j++){
                matrix[i][j] = ' ';
            }
        }
    }
    
    /**
     * Adds a new character to the board with location and character specified
     * by symbol. Note that x and y are measured with respect to the top left
     * corner wall, which is (-1,-1).
     * 
     * @param symbol
     *            - must have x, y location between -1 and 20
     * @throws UnsupportedOperationException
     *             - thrown when the location that we're writing to already
     *             contains a gadget
     *             - does not apply when we are trying to write in balls
     */
    void addTile(GridSymbol symbol) {
        matrix[symbol.getY() + 1][symbol.getX() + 1] = symbol.getChar();
    }
    
    
    /** Adds new character c multiple times in locations (x, y) s.t. 
     * x is in xList, y is in yList. Note that the top left corner
     * of the outer wall is in (-1,-1), and the bottom right corner
     * of the outer wall is at (20,20).
     * 
     * @param symbolList - must contain x and y locations which do not overlap, and which
     * are between (-1, -1) and (20, 20). Character must not be a newline or space.
     * @throws UnsupportedOperationException  - thrown when at least one of the locations
     * that we're writing to already contains a different non-space character. This could
     * also happen if the same location appears twice in (xList, yList).
     */
    public void addTiles(List<GridSymbol> symbolList){
        for(int i = 0; i<symbolList.size(); i++){
            addTile(symbolList.get(i));
        }
    }
    
    /**
     * Generates a string representation of the board, which we can then print
     * to the console to show the state of the board at a given instant.
     * @return a 2D ASCII-art representation of our matrix. There is exactly one line of white space
     * below the board.
     */
    public String printString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<BOARD_SIZE; i++){
            for(int j = 0; j<BOARD_SIZE; j++){
                stringBuilder.append(matrix[i][j]);
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    
    
    /**
     * @return a correctly formatted Java string with correct newlines and escaped quotation characters. 
     */
    public String printDebugString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<BOARD_SIZE; i++){
            stringBuilder.append("+\""); 
            for(int j = 0; j<BOARD_SIZE; j++){
                stringBuilder.append(matrix[i][j]);
            }
            stringBuilder.append("\\n\"");
            stringBuilder.append("\n");
        }
        stringBuilder.append("+\"\\n\";");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}