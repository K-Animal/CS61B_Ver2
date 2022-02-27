package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
                // TODO: Modify this.board (and perhaps this.score) to account
        //for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        int top_most_null_row = -1;
        int top_most_row_of_second_tile = -1;
        boolean has_not_moved = false;
        boolean tiles_match = true;
        boolean changed = false;

        board.setViewingPerspective(side);
        for(int column = 0; column < board.size(); column ++) {
            for (int row = board.size() - 1; row >= 0; row--) {
                if (board.tile(column, row) != null) {
                    System.out.println("------------");
                    System.out.println("Column: " + column + " " + "Row: " + row);
                    top_most_null_row = topMostNullPoint(column);
                    top_most_row_of_second_tile = secondTileLocation(column, row);
                    //If a second tile exists does it match Tile t?
                    tiles_match = false;
                    if (top_most_row_of_second_tile > -1 && (tile(column, row).value() == tile(column, top_most_row_of_second_tile).value())) {
                        tiles_match = true;
                        System.out.println("Tile match is true");
                    }
                    //If there is a null spot above then move tiles to said null spot
                    if (top_most_null_row > row) {
                        //If the tile below also matches the tile above then move it to the same spot as well
                        if (tiles_match) {
                            System.out.println("Tile match and there is a null spot above Null spot position -> " + top_most_null_row);
                            board.move(column, top_most_null_row, tile(column, top_most_row_of_second_tile));
                            board.move(column, top_most_null_row, tile(column, row));
                            score = score + tile(column, row).value()*2;
                        }
                    } else if (tiles_match) {
                        //If Tile t is already as high as it can go then leave it and check for a match
                        //with the second tile
                        System.out.println("No null spot above but tiles match");
                        board.move(column, row, tile(column, top_most_row_of_second_tile));
                        score = score + tile(column, row).value()*2;
                    }
                    if (top_most_null_row > row && top_most_row_of_second_tile == -1) {
                        System.out.println("Null row exists but no second tile does");
                        board.move(column, top_most_null_row, tile(column, row));
                    }
                    System.out.println("------------");
                }
            }
        }
        changed = true;
        board.setViewingPerspective(Side.NORTH);
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        checkGameOver();

        if (changed) {
            setChanged();
        }
        return changed;
    }
    // If it exists, finds the topmost Null row and returns it
    public int topMostNullPoint(int column) {
        int top_most_null_row = -1;
        for (int row = board.size() - 1; row >= 0; row--) {
            if(tile(column, row) == null) {
                top_most_null_row = row;
                break;
            }
        }
        System.out.println("topMostNullSpot was called: " + top_most_null_row);
        System.out.println("topMostNullPoint: " + column + " Row: " + top_most_null_row);
        return top_most_null_row;
    }
    // If a second tile exists, returns the second Tile row
    public int secondTileLocation(int column, int row) {
        int top_most_row_of_second_tile = -1;
        if (row - 1 < 0) {
            return top_most_row_of_second_tile;
        }
        for (row = row - 1; row >= 0; row--) {
            if (tile(column, row) != null) {
                top_most_row_of_second_tile = row;
                break;
            }
        }
        System.out.println("secondTileLocation was called: " + top_most_row_of_second_tile);
        System.out.println("Position of secondTileLocation Column: " + column + " Row: " + row);
        return top_most_row_of_second_tile;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j ++) {
                if (b.tile(i,j) == null) {
                    k = 1;
                }
            }
        }
        if (k == 1) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        int k = 0;
        int tileValue;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j ++) {
                if (b.tile(i,j) == null) {
                    tileValue = 0;
                } else {
                    tileValue = b.tile(i,j).value();
                }
                if (tileValue == MAX_PIECE) {
                    k = 1;
                }
            }
        }
        if (k == 1) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        int tileValue;
        int sideTileValue;
        int k = 0;
        if (emptySpaceExists(b)) {
            return true;
        }
        /** Checks whether a move exists to the top or bottom and
         * returns true if it does. tile(int col, int row) */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tileValue = b.tile(i,j).value();
                if (j + 1 >= 4) {
                    continue;
                } else {
                    sideTileValue = b.tile(i,(j + 1)).value();
                    if (tileValue == sideTileValue) {
                        k = 1;
                    }
                }
            }
        }
        /** Checks whether a move exists to the left or right and
         * returns true if it does. tile(int col, int row) */
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                tileValue = b.tile(i,j).value();
                if (i + 1 >= 4) {
                    continue;
                } else {
                    sideTileValue = b.tile((i + 1), j).value();
                    if (tileValue == sideTileValue) {
                        k = 1;
                    }
                }
            }
        }
        if (k == 1) {
            return true;
        }
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
