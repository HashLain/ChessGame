package pieces;
import position.Position;
import java.util.ArrayList;
/**
 * The Bishop class repesents a bishop chess piece 
 * It extneds the abstract class and defines the specfic 
 * movement and behavior of a bishop on a chessboard.
 */
public class Bishop extends Piece {
  private String name;
  public Piece[][] board;
/**
 * Constructor to initialzie a Bishop object.
 *
 * @param color The color of the Bishop; either "W" for white or "b" for Black
 * @param pos The initial position for the Bishop 
 * @param board The chessboard for which the Bishop is placed in 
 */
  public Bishop(char color, Position pos, Piece[][] board) {
    super(pos, color);
    this.name = (getColor() == 'w') ? "\u2657" : "\u265D";
    this.board = board;
  }

/**
 * Getter for the name of the Bishop 
 *
 * @return The name of the Bishop 
 */
  @Override
  public String getName() {
    return this.name;
  }

/**
 * Handles movement and validitiy of the requested move for the Bishop
 * When complete will change the position of the Bishop or return false
 *
 * @param pos The end position to which the Bishop will be moved to 
 * @return True if the move is valid, false otherwise
 */
  @Override
  public boolean move(Position pos) {
    int newRow = pos.getRow();
    int newCol = pos.getCol();
    int currRow = this.pos.getRow();
    int currCol = this.pos.getCol();
    Piece check = board[newRow][newCol];

    //Determines if the Bishop will be moving upwards or downwards
    //As well as left or right across the board
    if (Math.abs(newRow - currRow) == Math.abs(newCol - currCol)) {
      int moveRow = (newRow > currRow) ? 1 : -1;
      int moveCol = (newCol > currCol) ? 1 : -1;
      int tempRow = currRow + moveRow;
      int tempCol = currCol + moveCol;
      
      //Well check every space the Bishop needs till it reaches position pos 
      //if a piece is blocking one spot on it's path; returns false 
      while (tempRow != newRow && tempCol != newCol) {
        Position temp = new Position(tempRow, tempCol);
        if (board[tempRow][tempCol] != null) {
          return false;
        }

        tempRow += moveRow;
        tempCol += moveCol;
      }
      //Final check if the final position is null or enemy piece 
      //Returns true only if null or enemy
      if (check == null || check.getColor() != this.getColor()) {
        this.pos.changePosition(newRow, newCol);
        return true;
      }
    }

    return false;
  }

/**
 * Generates a list of possible moves for the bishop to go to based on current position
 *
 * @return An ArrayList of the possible positions 
 */
  public ArrayList<Position> possibleMoves() {
    ArrayList<Position> possibleMoves = new ArrayList<>();
    switch (this.getColor()) {
      case 'w': 
        int tempRow = this.pos.getRow();
        int tempCol = this.pos.getCol();
        //For white, 
        //handles all possible moves till end of the 
        //upper left side of the board 
        while (tempRow > 0 && tempCol > 0) {
          tempRow -= 1;
          tempCol -= 1;
          Position temp = new Position(tempRow, tempCol);
          Piece check = board[tempRow][tempCol];
          if (check == null) {
            possibleMoves.add(temp);
          }
        }
        
        //For White
        //Resets temp back to orginal position and 
        //Checks all positions till the end of the upper right side of the board
        tempRow = this.pos.getRow();
        tempCol = this.pos.getCol();
        while (tempRow > 0 && tempCol < 8) {
          tempRow -= 1;
          tempCol += 1;
          Position temp = new Position(tempRow, tempCol);
          Piece check = board[tempRow][tempCol];
          if (check == null) {
            possibleMoves.add(temp);
          }
        }
        
        //For White 
        //Resets temp position to original position 
        //Checks for all possible positions till end of lower left side of the board
        tempRow = this.pos.getRow();
        tempCol = this.pos.getCol();
        while (tempRow < 8 && tempCol > 0) {
          tempRow += 1;
          tempCol -= 1;
          Position temp = new Position(tempRow, tempCol);
          Piece check = board[tempRow][tempCol];
          if (check == null) {
            possibleMoves.add(temp);
          }
        }
        
        //For White 
        //Resests temp back to orginal position 
        //Checks for all possible positions till end of the lower right side of the board
        tempRow = this.pos.getRow();
        tempCol = this.pos.getCol();
        while (tempRow < 8 && tempCol < 8) {
          tempRow += 1;
          tempCol += 1;
          Position temp = new Position(tempRow, tempCol);
          Piece check = board[tempRow][tempCol];
          if (check == null) {
            possibleMoves.add(temp);
          }
        }
        break;

      case 'b': 
        tempRow = this.pos.getRow();
        tempCol = this.pos.getCol();

        //For Black 
        //Check for all possible positions till end of the upper left side of the board
        while (tempRow < 8 && tempCol > 0) {
          tempRow += 1;
          tempCol -= 1;
          Position temp = new Position(tempRow, tempCol);
          Piece check = board[tempRow][tempCol];
          if (check == null) {
            possibleMoves.add(temp);
          }
        }
        
        //For Black
        //Resets back to temp position back orginal position
        //Checks all positions till end of the upper right side of the board 
        tempRow = this.pos.getRow();
        tempCol = this.pos.getCol();
        while (tempRow < 8 && tempCol < 8) {
          tempRow += 1;
          tempCol += 1;
          Position temp = new Position(tempRow, tempCol);
          Piece check = board[tempRow][tempCol];
          if (check == null) {
            possibleMoves.add(temp);
          }
        }
        
        //For Black 
        //Resets temp back to orginal postion 
        //Checks for all possible postions till end of lower left side of the board 
        tempRow = this.pos.getRow();
        tempCol = this.pos.getCol();
        while (tempRow > 0 && tempCol > 0) {
          tempRow -= 1;
          tempCol -= 1;
          Position temp = new Position(tempRow, tempCol);
          Piece check = board[tempRow][tempCol];
          if (check == null) {
            possibleMoves.add(temp);
          }
        }

        
        //For Black 
        //Resets temp position back to orginal postion 
        //Checks for all possible postions till end of the lower right side of the board  
        tempRow = this.pos.getRow();
        tempCol = this.pos.getCol();
        while (tempRow > 0 && tempCol < 8) {
          tempRow -= 1;
          tempCol += 1;
          Position temp = new Position(tempRow, tempCol);
          Piece check = board[tempRow][tempCol];
          if (check == null) {
            possibleMoves.add(temp);
          }
        }
        break;
    }
    return possibleMoves;
  }
}
