package visual;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import position.Position;
import player.Player;
import pieces.*;

/**
 * This class repesents the chess board Interface 
 * provides the fucntionality of the board and set pieces 
 */
public class Interface {
  private int rows = 8;
  private int cols = 8;
  private char currentPlayer;
  public Position click = null;
  public final Piece[][] gameBoard = new Piece[rows][cols];
  public JFrame frame;
  public JPanel board;

  /**
   * Constructor for the Inteface class 
   */
  public Interface() {
    this.currentPlayer = 'w';
    setGameBoard();
    display();
  }

/**
 * Sets all the pieces
 * at the start of the game 
 * update later for other pieces
 */
  public void setGameBoard() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        Position set = new Position(i,j);
        //Black Pawn setter 
        if (i == 1) {
          Pawn blackPawn = new Pawn('b', set, gameBoard);
          gameBoard[i][j] = blackPawn;
        }
        //Black pieces setter 
        else if (i == 0) {
          switch (j) {
            case 0:
            case 7:
              Rook blackRook = new Rook('b', set, gameBoard);
              gameBoard[i][j] = blackRook;
              break;
            case 1:
            case 6:
              Knight blackKnight = new Knight('b', set, gameBoard);
              gameBoard[i][j] = blackKnight;
              break;
            case 2:
            case 5:
              Bishop blackBishop = new Bishop('b', set, gameBoard);
              gameBoard[i][j] = blackBishop;
              break;
            case 3:
              Queen blackQueen = new Queen('b', set, gameBoard);
              gameBoard[i][j] = blackQueen;
              break;
            case 4:
              King blackKing = new King('b', set, gameBoard);
              gameBoard[i][j] = blackKing;
              break;
          }
        }
        else if (i == 6) {
          //White pawn setter
          Pawn whitePawn = new Pawn('w', set, gameBoard);
          gameBoard[i][j] = whitePawn;
        } 
        //White Piece setter 
        else if (i == 7) {
          switch (j) {
            case 0:
            case 7:
              Rook whiteRook = new Rook('w', set, gameBoard);
              gameBoard[i][j] = whiteRook;
              break;
            case 1:
            case 6:
              Knight whiteKnight = new Knight('w', set, gameBoard);
              gameBoard[i][j] = whiteKnight;
              break;
            case 2:
            case 5:
              Bishop whiteBishop = new Bishop('w', set, gameBoard);
              gameBoard[i][j] = whiteBishop;
              break;
            case 3:
              Queen whiteQueen = new Queen('w', set, gameBoard);
              gameBoard[i][j] = whiteQueen;
              break;
            case 4: 
              King whiteKing = new King('w', set, gameBoard);
              gameBoard[i][j] = whiteKing;
              break;
          }
        }
      }
    }
  }

  /**
   * Changes the current player 
   */
  public void updatePlayer() {
    if (this.currentPlayer == 'w') {
      this.currentPlayer = 'b';
    } else {
      this.currentPlayer = 'w';
    }
  }

/**
 * Mouse listner for user to interact with gui 
 * First position is based on click of player 
 * Will keep listing till a piece is clicked 
 * Next click is the final position to move piece 
 *
 * @param panel The Panel that will be listing for click 
 * @param i Used to get the row value of Panel clicked on 
 * @param j Used to get Column value of Panel clicked on 
 */
  public void addMouseListenerToPanel(JPanel panel, int i, int j) {
    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        //Will keep listing to piece is clicked 
        //This is start value of piece 
        if (click == null) {
          if (gameBoard[i][j] != null) {
            click = new Position(i, j);
          }
        } else {
          //Once piece is clicked; gets next click 
          //for the end position of the piece 
            Position end = new Position(i, j);
            //Calls movePiece function to see if valid move 
            if (movePiece(click, end)) {
              click = null;
              display();
            } else {
              //Error messages when invalid move is clicked 
              //Resets entire method and makes player try a differnt move 
              JOptionPane.showMessageDialog(null, "Invalid Move!", "ERROR", JOptionPane.ERROR_MESSAGE);
              click = null;
            }
          }
      }
    });
  }
/**
 * Displays the current boards status as a GUI 
 */
  public void display() {
    if (frame == null) {
      frame = new JFrame("Chessboard");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
      //Sets the entire size of frame. For now should be half size of screen size 
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int frameWidth = screenSize.width / 2;
      int frameHeight = screenSize.height / 2;
      frame.setLayout(new BorderLayout());
    
      //The boards frame 
      board = new JPanel();
      board.setLayout(new GridLayout(rows, cols));
      int panelSize = frameWidth / rows;
    
      //Sets the color and array of the board
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          JPanel panel = new JPanel();
          panel.setPreferredSize(new Dimension(panelSize, panelSize));
          panel.setBackground((i+j) % 2 == 0 ? Color.decode("#8B4513") : Color.decode("#D2B48C"));
          panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

          //Gets pieces icon based on current values of gameBoard array
          Piece piece = gameBoard[i][j];
          if (piece != null) {
            JLabel label = new JLabel(piece.getName(), SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.PLAIN, panelSize/2));
            panel.add(label);
          }

          addMouseListenerToPanel(panel, i, j);

          board.add(panel);
        }
      }

      //Column Label of the game board A - H characters
      //Set up as  GridBagLayout to keep aligned
      JPanel colLabel = new JPanel(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.BOTH;
      for (char c = 'A'; c <= 'H'; c++) {
        JLabel label = new JLabel(String.valueOf(c), SwingConstants.CENTER);
        label.setFont(new Font(label.getName(), Font.PLAIN, 24));
        gbc.weightx = 1.0 / cols;
        colLabel.add(label, gbc);
      }

      //Row labels for the game board 
      //Also set as GridBagLayout to keep align
      JPanel rowLabel = new JPanel(new GridBagLayout());
      gbc.gridy = 0;
      gbc.weighty = 1.0 / rows;
      for (int i = 8; i >= 1; i--) {
        JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
        label.setFont(new Font(label.getName(), Font.PLAIN, 24));
        rowLabel.add(label, gbc);
        gbc.gridy++;
      }

      //Adds all JPanels to the frame to make full GUI 
      frame.add(board, BorderLayout.CENTER);
      frame.add(rowLabel, BorderLayout.WEST);
      frame.add(colLabel, BorderLayout.NORTH);

      //Sets visable and size for frame 
      frame.setSize(frameWidth, frameHeight);
      frame.pack();
      frame.setVisible(true);
    }
  }
 
  /**
   * Reterives the piece at the given position on the board 
   *
   * @param pos The position to reterive the piece from
   * @return The piece at the specificed position or null if no piece found
   */
  public Piece getPiece(Position pos) {
    int row = pos.getRow();
    int col = pos.getCol();
    return gameBoard[row][col];
  }

  /**
   * Reterives the gameBoard array 
   * Used for player class to check values 
   * @return The game board array's current value
   */
  public Piece[][] getGameBoard() {
    return gameBoard;
  }

/**
 *
 * Calls the pieces move function to move the piecce on the 
 * JPanel board 
 * @param start finds the start position on the current piece 
 * @param end finds the end position of the players choice 
 * @return Will return true of false if movement succesfull
 */
  public boolean movePiece(Position start, Position end) {
    int startRow = start.getRow();
    int startCol = start.getCol();
    int endRow = end.getRow();
    int endCol = end.getCol();

    
    Piece toMove = gameBoard[startRow][startCol];
    
    //Checks if current player matches color piece 
    if (toMove.getColor() != this.currentPlayer) {
      return false;
    }

    //Calls piece move function to check if able to move
    if(toMove.move(end)) {
      gameBoard[endRow][endCol] = toMove;
      gameBoard[startRow][startCol] = null;
      updateBoard(start, end);
      updatePlayer();
      return true;
    }

    return false;
  }

/**
 * Updates the visual dipslay of the JPanel of the chess game 
 * Removes the value of the current icon, repaints the JPanel 
 * Repains the new current value
 * @param start The starting position of the piece 
 * @param end The end position of where the piece will move to 
 */
  public void updateBoard(Position start, Position end) {

    JPanel startPanel = (JPanel) board.getComponent(start.getRow() * cols + start.getCol());
    JPanel endPanel = (JPanel) board.getComponent(end.getRow() * cols + end.getCol());
    // Remove the piece icon from the start panel
    startPanel.removeAll();
    startPanel.setBackground((start.getRow() + start.getCol()) % 2 == 0 ? Color.decode("#8B4513") : Color.decode("#D2B48C"));
    endPanel.removeAll();
    endPanel.setBackground((end.getRow() + end.getCol()) % 2 == 0 ? Color.decode("#8B4513") : Color.decode("#D2B48C"));

    // Update the end panel with the new piece icon
    Piece piece = gameBoard[end.getRow()][end.getCol()];
    if (piece != null) {
        JLabel label = new JLabel(piece.getName(), SwingConstants.CENTER);
        int panelSize = endPanel.getPreferredSize().width;
        label.setFont(new Font("SansSerif", Font.PLAIN, panelSize/2));
        endPanel.add(label);
    }

    // Repaint the updated panels
    startPanel.revalidate();
    startPanel.repaint();
    endPanel.revalidate();
    endPanel.repaint();
  }

}
