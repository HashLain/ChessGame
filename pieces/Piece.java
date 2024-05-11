package pieces;

import java.util.ArrayList;
import position.*;

public abstract class Piece 
{
    public Position pos;
    public char color;

    /**
     * @return the color
     */
    public char getColor() { return color; }

    /**
     * Creates a new Piece from a Position. Does not initialize board
     * 
     * @param startPosition the initial position of the Piece on the board
     * @param color the color of the Piece represented as a character: 'w' or 'b'
     */
    public Piece(Position startPosition, char color)
    {
        this.pos = startPosition;
        this.color = color;
    }

    /**
     * @return an ArrayList of valid positions the piece can move to
     */
    public abstract ArrayList<Position> possibleMoves();

    /**
     * Moves this Piece to a new position on the board if that position is listed in
     * possibleMoves()
     * 
     * @param newPosition the Position this is attempting to move to
     * @return true if newPosition is a valid move and this Piece's Position was changed
     */
    public abstract boolean move(Position newPosition);
    /**
     * @return a string representation of this Piece, in form 'wp', 'bQ', etc. The first 
     * character represents the color and the second character represents the Piece type
     */ 
    public abstract String getName();
}
