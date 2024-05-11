package pieces;
import position.Position;
import java.util.ArrayList;
/**
 * The Pawn class repesents a pawn chess piece 
 * It extends the abstract class Piece defines 
 * specfic movement and behavior of a pawn
 */ 
public class Pawn extends Piece {
  private String name;
  private boolean moved;
  public Piece[][] board;

  /**
   * Constructor to initialize the Pawn object
   *
   * @param color The color of the pawn; "w" for white and "b" for black
   * @param pos The initial position of the pawn 
   * @param board The chessboard which the pawn is placed 
   */   
  public Pawn(char color, Position pos, Piece[][] board) {
    super(pos, color);
    this.name = (getColor() == 'w') ? "\u2659" : "\u265F"; 
    this.moved = false;
    this.board = board;
  }

/**
  * Getter for pawn icon 
  * Uses the color to determine which. 
  *
  * @return The icon value. Either white or black pawn  
  */
  @Override
  public String getName() {
    return this.name; 
  }

/**
 * Handles the movements of the pawn piece and check if a move is valid
 *
 * @param pos The position of which the pawn is to move to 
 * @return True if the move is valid; False otherwise
 */
  @Override
  public boolean move(Position pos) {
    int newRow = pos.getRow();
    int newCol = pos.getCol();
    int currRow = this.pos.getRow();
    int currCol = this.pos.getCol();
    Piece check = board[newRow][newCol];
    //Checks if can move up two spaces for inital movement
    if (Math.abs(newRow - currRow) == 2 && currCol == newCol && !moved) {
      Position space;
      switch (this.getColor()) {
        case 'w':
          space = new Position(currRow-1, currCol);
          if (board[space.getRow()][space.getCol()] == null & check == null) {
            this.pos.changePosition(newRow, newCol);
            moved = true;
            return true;
          }
          break;
        case 'b':
          space = new Position(currRow+1, currCol);
          if (board[space.getRow()][space.getCol()] == null & check == null) {
            this.pos.changePosition(newRow, newCol);
            moved = true;
            return true;
          }
          break;
        default:
          return false;
      }
    }
    
    //Checks for movement for one space above or below the current
    if (Math.abs(newRow-currRow) == 1 && currCol == newCol) {
      if (check == null) {
        this.pos.changePosition(newRow, newCol);    
        moved = true;
        return true;
      }
    }
    
    //Handles the diagonal movement for both spaces.
    //Only succesful if there is a opposite color piece in slot and moving forward
    if (Math.abs(newRow-currRow) == 1 && Math.abs(newCol-currCol) == 1) {
      if (check != null && this.getColor() != check.getColor()) {
        switch (this.getColor()) {
          case 'w':
            if (newRow-currRow == -1) {
              this.pos.changePosition(newRow, newCol);
              moved = true;
              return true;
            }
            break;
          case 'b':
            if (newRow-currRow == 1) {
              this.pos.changePosition(newRow, newCol);
              moved = true;
              return true;
            }
            break;
          default:
            return false;
        }
      }
    }

    return false;
  }


/**
 * Generates a list of all possible moves for the pawn pieces 
 * at it's current position 
 *
 * @return An ArrayList of possible moves for the pawn 
 */
  public ArrayList<Position> possibleMoves() {
    ArrayList<Position> possibleMoves = new ArrayList<>();
    int tempRow = this.pos.getRow();
    int tempCol = this.pos.getCol();
    Position temp = new Position(tempRow, tempCol);
    Piece check;

    switch (this.getColor()) {
        case 'w':
            // Check for moves forward
            if (tempRow > 0) {
                temp.changePosition(tempRow - 1, tempCol);
                check = board[temp.getRow()][temp.getCol()];
                if (check == null) {
                    possibleMoves.add(new Position(tempRow - 1, tempCol));
                    if (!moved) {
                        temp.changePosition(tempRow - 2, tempCol);
                        check = board[temp.getRow()][temp.getCol()];
                        if (check == null) {
                            possibleMoves.add(new Position(tempRow - 2, tempCol));
                        }
                    }
                }
            }

            //Check for move backwards
            if (tempRow < 7) {
                temp.changePosition(tempRow + 1, tempCol);
                check = board[temp.getRow()][temp.getCol()];
                if (check == null) {
                    possibleMoves.add(new Position(tempRow + 1, tempCol));
                }
            }

            // Check for diagonal captures
            if (tempRow > 0 && tempCol > 0) {
                temp.changePosition(tempRow - 1, tempCol - 1);
                check = board[temp.getRow()][temp.getCol()];
                if (check != null && check.getColor() != this.getColor()) {
                    possibleMoves.add(new Position(tempRow - 1, tempCol - 1));
                }
            }

            if (tempRow > 0 && tempCol < 7) {
                temp.changePosition(tempRow - 1, tempCol + 1);
                check = board[temp.getRow()][temp.getCol()];
                if (check != null && check.getColor() != this.getColor()) {
                    possibleMoves.add(new Position(tempRow - 1, tempCol + 1));
                }
            }

            break;

        case 'b':
            // Check for moves forward
            if (tempRow < 7) {
                temp.changePosition(tempRow + 1, tempCol);
                check = board[temp.getRow()][temp.getCol()];
                if (check == null) {
                    possibleMoves.add(new Position(tempRow + 1, tempCol));
                    if (!moved) {
                        temp.changePosition(tempRow + 2, tempCol);
                        check = board[temp.getRow()][temp.getCol()];
                        if (check == null) {
                            possibleMoves.add(new Position(tempRow + 2, tempCol));
                        }
                    }
                }
            }

            //Check for move backwards
            if (tempRow > 0) {
                temp.changePosition(tempRow - 1, tempCol);
                check = board[temp.getRow()][temp.getCol()];
                if (check == null) {
                    possibleMoves.add(new Position(tempRow - 1, tempCol));
                }
            }

            // Check for diagonal captures
            if (tempRow < 7 && tempCol > 0) {
                temp.changePosition(tempRow + 1, tempCol - 1);
                check = board[temp.getRow()][temp.getCol()];
                if (check != null && check.getColor() != this.getColor()) {
                    possibleMoves.add(new Position(tempRow + 1, tempCol - 1));
                }
            }

            if (tempRow < 7 && tempCol < 7) {
                temp.changePosition(tempRow + 1, tempCol + 1);
                check = board[temp.getRow()][temp.getCol()];
                if (check != null && check.getColor() != this.getColor()) {
                    possibleMoves.add(new Position(tempRow + 1, tempCol + 1));
                }
            }

            break;

        default:
            break;
    }

    return possibleMoves;
  }

}
