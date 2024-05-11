package pieces;

import position.Position;
import java.util.ArrayList;

public class King extends Piece {
    private String name;
    public Piece[][] board;
/**
 * King Piece Constructor
 */
    public King(char color, Position pos, Piece[][] board) {
        super(pos, color);
        this.name = (color == 'w') ? "\u2654" : "\u265A";
        this.board = board;
    }

/**
 * Used to get the Kings Icon 
 *
 *@return Icon for the king piece 
 */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Handles the movement for the king piece 
     *
     * @param pos end position of the king piece 
     * @return True or False depending if movement is good or not
     */
    @Override
    public boolean move(Position pos) {
        int newRow = pos.getRow();
        int newCol = pos.getCol();
        int currRow = this.pos.getRow();
        int currCol = this.pos.getCol();

        // Check if the move is a valid king move
        if ((Math.abs(newRow - currRow) <= 1 && Math.abs(newCol - currCol) <= 1)) {
            Piece check = board[newRow][newCol];
            // Final check if the final position is null or enemy piece
            if (check == null || check.getColor() != this.getColor()) {
                this.pos.changePosition(newRow, newCol);
                return true;
            }
        }

        return false;
    }

    /**
     * Arraylist for the avaible places of the kings movement 
     *
     * @return The arraylist of the kings avaible movements
     */

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        //Uses Offset values to get the next places 
        int[] rowOffsets = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] colOffsets = { -1, 0, 1, -1, 1, -1, 0, 1 };
        
        //Checks for all offset slots 
        for (int i = 0; i < 8; i++) {
            int newRow = this.pos.getRow() + rowOffsets[i];
            int newCol = this.pos.getCol() + colOffsets[i];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Position newPos = new Position(newRow, newCol);
                if (move(newPos)) {
                    possibleMoves.add(newPos);
                }
            }
        }

        return possibleMoves;
    }
}
