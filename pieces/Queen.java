package pieces;

import position.Position;
import java.util.ArrayList;

public class Queen extends Piece {
    private String name;
    public Piece[][] board;

    /**
     * Constructor for Queen 
     */
    public Queen(char color, Position pos, Piece[][] board) {
        super(pos, color);
        this.name = (getColor() == 'w') ? "\u2655" : "\u265B";
        this.board = board;
    }

    /**
     * @return Returns icon for the Queen Piece 
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Handles movement for the queen piece 
     *
     * @param pos end position of the Queen 
     * @Return True or False if movement good or not
     */
    @Override
    public boolean move(Position pos) {
        int newRow = pos.getRow();
        int newCol = pos.getCol();
        int currRow = this.pos.getRow();
        int currCol = this.pos.getCol();

        Piece check = board[newRow][newCol];

        // Check if the move is either vertical, horizontal, or diagonal
        if (newRow == currRow || newCol == currCol || Math.abs(newRow - currRow) == Math.abs(newCol - currCol)) {
            int moveRow = Integer.compare(newRow, currRow);
            int moveCol = Integer.compare(newCol, currCol);

            int tempRow = currRow + moveRow;
            int tempCol = currCol + moveCol;

            // Check all spaces between current position and target position
            while (tempRow != newRow || tempCol != newCol) {
                if (board[tempRow][tempCol] != null) {
                    return false;
                }

                tempRow += moveRow;
                tempCol += moveCol;
            }

            // Final check if the final position is null or enemy piece
            if (check == null || check.getColor() != this.getColor()) {
                this.pos.changePosition(newRow, newCol);
                return true;
            }
        }

        return false;
    }

    /**
     * Uses Rook and Bishop values to get the avaible positions
     * @return The array list of the queen piece movements
     */
    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        Rook rook = new Rook(this.getColor(), this.pos, this.board);
        Bishop bishop = new Bishop(this.getColor(), this.pos, this.board);

        possibleMoves.addAll(rook.possibleMoves());
        possibleMoves.addAll(bishop.possibleMoves());

        return possibleMoves;
    }
}
