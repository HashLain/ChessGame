package position;

import java.lang.Character;

public class Position 
{
    public int row;
    public int col;

    /**
     * @return the row
     */
    public int getRow() { return row; }
    /**
     * @return the column
     */
    public int getCol() { return col; }

    /**
     * Create a new Position from integer coordinates
     * 
     * @param row the initial row
     * @param col the initial column
     */
    public Position(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    /**
     * Create a new Position from a string representation
     * 
     * @param coord a position on a chess board, represented as a VALID string in the form A1 to H8
     */
    public Position(String coord)
    {
        char col = coord.charAt(0);
        char row = coord.charAt(1);
        
        // A -> 0 ... H -> 7
        if (Character.isLowerCase(col))
        {
            col = Character.toUpperCase(col);
        } 
        this.col = (int)col - 65;

        // 8 -> 0 ... 1 -> 7
        this.row = -1 * (int)row + 56;
    }

    /**
     * Create a new Position from an offset from an existing Position
     * 
     * @param origin an existing Position
     * @param offsetX the distance the new Position is from origin vertically on the board
     * @param offsetY the distance the new Position is from origin horizontally on the board
     */
    public Position(Position origin, int offsetRow, int offsetCol)
    {
        this.col = origin.col + offsetCol;
        this.row = origin.row + offsetRow;
    }

    /**
     * Changes the row and column
     * 
     * @param row the new row
     * @param col the new column
     */
    public void changePosition(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    /**
     * Determines if this is a valid Position on a chess board
     * 
     * @return true if both row and col are between -1 and 8
     */
    public boolean isOnBoard()
    {
        return (-1 < row && row < 8) && (-1 < col && col < 8);
    }

    /**
     * Compares this to another Position
     * 
     * @param other the Position to compare to
     * @return true if other is a Position and this and other have the same row and col
     */
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Position)
        {
            return (this.row == ((Position)other).row) && (this.col == ((Position)other).col);
        }
        else
        {
            return false;
        }
    }

    /**
     * Create a new Position from a string representation
     * 
     * @param coord a position on a chess board, represented as a VALID string in the form A1 to H8
     */
    public Position stringToIndex(String pos)
    {
        return new Position(pos);
    }

    /**
     * Helper method to display the row and column of a Position
     * 
     * @return the the ordered pair (row, col) as a string
     */
    @Override
    public String toString()
    {
        return "(" + row + ", " + col + ")";
    }
}
