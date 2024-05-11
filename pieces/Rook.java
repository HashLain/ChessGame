package pieces;
import position.Position;
import java.util.ArrayList;

/**
 * The Rook class repesents a Rook piece in a chess game 
 * Can mover verticially or horozontial
 * It extneds the abstract Piece class 
 */
public class Rook extends Piece {
  private String name;
  public Piece[][] board;

/**
 * Constructor for the Rook class 
 *
 * @param color The color of the Rook, is either 'w' or 'b' for white or black
 * @param pos The inital position of the Rook piece on the board 
 * @param board The board on which the Rook is placed. 
 */
  public Rook (char color, Position pos, Piece[][] board) {
    super(pos, color);
    this.board = board;
    this.name = (getColor() == 'w') ? "\u2656" : "\u265C";
  }

/**
 * Getter for the name of the Rook
 *
 * @return The String of the Rook's name 
 */
  @Override
  public String getName() {
    return this.name;
  }

/** 
 * Getter for the String repesnation of the Rook 
 *
 * @return The String repesnation of the Rook.
 */
  @Override
  public String toString() {
    return this.name;
  }

/**
 * Checks if the given position is a valid move for the Rook piece 
 * and moves it if it is 
 *
 * @param pos The end position of where the Rook is to move to 
 * @return True if the move is valid, returns false ottherwise
 */
  @Override
  public boolean move(Position pos) {
    int newRow = pos.getRow();
    int newCol = pos.getCol();
    int currRow = this.pos.getRow();
    int currCol = this.pos.getCol();
    Piece check = board[newRow][newCol];
    Position temp = new Position(currRow, currCol);
    
    //Checks for the same color first of the end position 
    if (check != null && check.getColor() == this.getColor()) {
      return false;
    }

    //Handles the movement upwards through the board 
    if (currRow > newRow && currCol == newCol) {
      while (currRow > newRow+1) {
        currRow -= 1;
        temp.changePosition(currRow, currCol);
        check = board[currRow][currCol];
        if (check != null) {
          return false;
        }
      }
      this.pos.changePosition(newRow, newCol);
      return true;
    }

    //Handles the downards movement till end position is reached 
    if (currRow < newRow && currCol == newCol) {
      while (currRow < newRow-1) {
        currRow += 1;
        temp.changePosition(currRow, currCol);
        check = board[currRow][currCol];
        if (check != null) {
          return false;
        }
      }
      this.pos.changePosition(newRow, newCol);
      return true;
    }

    //Handles movement to the left till end position is reached 
    if (currRow == newRow && currCol > newCol) {
      while (currCol > newCol+1) {
        currCol -= 1;
        temp.changePosition(currRow, currCol);
        check = board[currRow][currCol];
        if (check != null) {
          return false;
        }
      }
      this.pos.changePosition(newRow, newCol);
      return true;
    }

    //Handles movmenet to the right till the end position is reached 
    if (currRow == newRow && currCol < newCol) {
      while (currCol < newCol-1) {
        currCol += 1;
        temp.changePosition(currRow, currCol);
        check = board[currRow][currCol];
        if (check != null) {
          return false;
        }
      }
      this.pos.changePosition(newRow, newCol);
      return true;
    }

    return false;
  }

/**
 * Gets list of all possible positions based on the current position 
 *
 * @return An ArrayList of all possible moves 
 */
  public ArrayList<Position> possibleMoves() {
    ArrayList<Position> possibleMoves = new ArrayList<>();
    Piece check = null;
    int tempRow = this.pos.getRow();
    int tempCol = this.pos.getCol();
    Position temp = new Position(tempRow, tempCol);
   
    //Checks all possible moves upwards
    while (tempRow > 0 && check == null) {
      tempRow -= 1;
      temp.changePosition(tempRow, tempCol);
      check = board[tempRow][tempCol];
      if (check == null) {
        possibleMoves.add(temp);
      }
    }

    //Resets temp position to original 
    //Checks all possible moves downwards 
    tempRow = this.pos.getRow();
    while (tempRow < 8 && check == null) {
      tempRow += 1;
      temp.changePosition(tempRow, tempCol);
      check = board[tempRow][tempCol];
      if (check == null) {
        possibleMoves.add(temp);
      }
    }

    //Resets temp position to original 
    //Checks all possible positions to the left
    tempRow = this.pos.getRow();
    while (tempCol > 0 && check == null) {
      tempCol -= 1;
      temp.changePosition(tempRow, tempCol);
      check = board[tempRow][tempCol];
      if (check == null) {
        possibleMoves.add(temp);
      }
    }


    //Resets temp postions to orginal 
    //Checks for all possible positions to the right
    tempCol = this.pos.getCol();
    while (tempCol < 8 && check == null) {
      tempCol += 1;
      temp.changePosition(tempRow, tempCol);
      check = board[tempRow][tempCol];
      if (check == null) {
        possibleMoves.add(temp);
      }
    }

    return possibleMoves;
  }
}
