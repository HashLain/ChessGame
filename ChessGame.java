import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import position.Position;
import pieces.*;
import player.Player;
import visual.Interface;
import java.util.List;

public class ChessGame {

  /**
   * Checks if the king is capatured or not for player 
   *
   * @param player The player that checks for their peices 
   * @return False or True depending if the players king is gone
   */
  private static boolean checkKing(Player player) {
     //Gets the avaible pieces of the player 
     List<Piece> playerPieces = player.getAvailablePieces();
     //Checks all the piece 
     //if king still there returns false otherwise returns true 
     for (Piece piece : playerPieces) {
       if (piece instanceof King) {
         return false;
       }
     }
     return true;
  }

  /**
   * Winning Message for the player that captures the other players king
   * Displays pop up of the message 
   *
   * @param player The player that has lost their king 
   */
  private static void displayWinner(Player player) {
    String color = (player.getPlayerColor() == 'w') ? "White" : "Black";
    String message = "Player " + color + " has won!";
    JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
  }


  public static void main(String[] args) {
    Interface game = new Interface();
    Player white = new Player('w', game);
    Player black = new Player('b', game);

    //Will run true till a player captures a king 
    while(true) {
      white.updateAvailablePieces();
      black.updateAvailablePieces();
      if (checkKing(white)) {
        displayWinner(black);
        game.frame.dispose();
        break;
      } else if (checkKing(black)) {
        displayWinner(white);
        game.frame.dispose(); 
        break;
      }
    }
  }
}
