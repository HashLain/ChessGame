package player;
import pieces.*;
import position.Position;
import visual.Interface;
import java.util.ArrayList;
import java.util.List;

public class Player {
  private char color;
  private List<Piece> availablePieces;
  private Interface board;

/**
 * Constructs a Player object with theiir 
 * specified color
 *
 * @param color set to either "w" for white or "b" for black
 * @param chessboard is the board players play on
 */
  public Player(char color, Interface board) {
    this.color = color;
    this.board = board;
    this.availablePieces = new ArrayList<>();
  }
/**
 * Getter to get the player's color
 * @return will return either "w" for white or "b" for black
 */
  public char getPlayerColor() {
    return this.color;
  }

  /**
   * Getter to get the current list of the 
   * players available pieces 
   *
   * @return Avaible Piece list of the current available pieces 
   */
  public List<Piece> getAvailablePieces() {
    return availablePieces;
  }

  /**
   * Updates the array list of the players 
   * avaible pieces in the game board 
   */
  public void updateAvailablePieces() {
    //Clears current values 
    availablePieces.clear();
    //Loops through board 
    Piece[][]board = this.board.getGameBoard();
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        Piece piece = board[row][col];
        //Puts only pieces matching color bacl into the array list 
        if (piece != null && piece.getColor() == this.getPlayerColor()) {
          availablePieces.add(piece);
        }
      }
    }
  }

}
