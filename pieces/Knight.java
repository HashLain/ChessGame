package pieces;

import position.Position;
import java.util.ArrayList;

public class Knight extends Piece {
    private String name;
    public Piece[][] board;

    /**
     * Constructor for the Kinght Piece 
     */
    public Knight(char color, Position pos, Piece[][] board) {
        super(pos, color);
        this.name = (color == 'w') ? "\u2658" : "\u265E";
        this.board = board;
    }

    /**
     * Gets the icon value for the kinght piece 
     *
     * @return icon value for the piece 
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Handles the movement for the knight piece 
     *
     * @param pos the end position for the Knight piece 
     * @return True or False depending if movement good or not 
     */
    @Override
    public boolean move(Position pos) {
        int newRow = pos.getRow();
        int newCol = pos.getCol();
        int currRow = this.pos.getRow();
        int currCol = this.pos.getCol();

        // Check if the move is a valid knight move
        if ((Math.abs(newRow - currRow) == 2 && Math.abs(newCol - currCol) == 1) ||
            (Math.abs(newRow - currRow) == 1 && Math.abs(newCol - currCol) == 2)) {
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
     * Gets arraylist for the possible moves of the piece 
     *
     * @return arraylist for the possible moves 
     */
    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        //Offset values to check postiions 
        int[] rowOffsets = { -2, -1, 1, 2, 2, 1, -1, -2 };
        int[] colOffsets = { 1, 2, 2, 1, -1, -2, -2, -1 };

        //Loops through all values to get all possible movements 
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
