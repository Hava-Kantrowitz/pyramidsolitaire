package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.Card;

/**
 * Class that models the tripeaks pyramid solitaire gameboard.
 */
public class TripeaksPyramidSolitaire extends AbstractPyramidSolitaire {

  /**
   * Creates a playable instance of the pyramid solitaire game.
   */
  public TripeaksPyramidSolitaire() {
    super();
  }

  @Override
  public List<Card> getDeck() {
    //Loops through each suit and number value, creating the card and
    //adding it to the list of cards deck
    myDeck2.clear();

    //loops through twice in order to create the deck of 104
    for (int k = 0; k < 2; k++) {
      for (int i = 0; i < 4; i++) {
        for (int j = 1; j <= 13; j++) {
          Card nextCard = new Card(suit[i], j);
          myDeck2.add(nextCard);
        }
      }
    }

    return myDeck2;
  }

  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw) {
    drawDeck.clear();


    //if the deck itself is valid and the parameters are valid, keep going

    if (isValid(deck) && isValidParams(numRows, numDraw)) {
      //figure out the length of first row based on number of rows
      int firstRowLen = (numRows + 1) - (numRows % 2);

      //initialize game board
      gameBoard = new Card[numRows][firstRowLen + numRows - 1];
      myDeck = new ArrayList<>(deck);

      //if shuffle is requested, shuffle it
      if (shouldShuffle) {
        Collections.shuffle(myDeck);
      }

      //figure out where the half height occurs
      int halfHeight = (int) Math.ceil(numRows / 2);

      //figure out the number of nulls in each section of the first row
      int firstRowNulls = ((numRows - 2) - (numRows % 2)) / 2;

      //the number of numbers in each section of the first row
      int firstRowNums = 1;

      //for the first half the pyramid, where they don't overlap
      for (int i = 0; i < halfHeight; i++) {

        //do the first group of numbers
        for (int j = 0; j < firstRowNums; j++) {
          //put the card into the game board
          gameBoard[i][j] = myDeck.get(0);
          //increment the game score
          gameScore += gameBoard[i][j].getValue();
          //remove the card from the main deck
          myDeck.remove(0);
        }

        int combined = firstRowNums + firstRowNulls;

        //then the first group of nulls
        for (int j = firstRowNums + 1; j < combined; j++) {
          gameBoard[i][j] = null;
        }

        //then the second group of numbers
        for (int j = combined; j < combined + firstRowNums; j++) {
          //put the card into the game board
          gameBoard[i][j] = myDeck.get(0);
          //increment the game score
          gameScore += gameBoard[i][j].getValue();
          //remove the card from the main deck
          myDeck.remove(0);
        }

        int combined2 = combined + firstRowNums;

        //then the second group of nulls
        for (int j = combined2; j < combined2 + firstRowNulls; j++) {
          gameBoard[i][j] = null;
        }

        //then the last group of numbers
        for (int j = combined2 + firstRowNulls; j < combined2 + firstRowNulls + firstRowNums; j++) {
          //put the card into the game board
          gameBoard[i][j] = myDeck.get(0);
          //increment the game score
          gameScore += gameBoard[i][j].getValue();
          //remove the card from the main deck
          myDeck.remove(0);
        }

        //before going to the next row, increase the number of numbers
        //and decrease the number of nulls
        firstRowNums++;
        firstRowNulls--;
      }

      //loop through the combined half the pyramid
      for (int i = halfHeight; i < numRows; i++) {
        for (int j = 0; j < firstRowLen + i; j++) {
          //put the card into the game board
          gameBoard[i][j] = myDeck.get(0);
          //increment the game score
          gameScore += gameBoard[i][j].getValue();
          //remove the card from the main deck
          myDeck.remove(0);
        }
      }

      //Loops through the length of the draw
      for (int k = 0; k < numDraw; k++) {
        //adds it to the draw deck
        drawDeck.add(myDeck.get(0));
        //removes it from the main deck
        myDeck.remove(0);
      }

      //Start the gameplay
      this.gameStarted = true;
    } else {
      if (!isValid(myDeck)) {
        throw new IllegalArgumentException("Deck not valid");
      } else if (!isValidParams(numRows, numDraw)) {
        throw new IllegalArgumentException("Parameters not valid");
      }
      throw new IllegalArgumentException("Starting params illegal");
      //otherwise throw illegal argument exception
    }

  }

  /**
   * Determines if the starting parameters are valid. The number of rows is considered valid only if
   * it is one or greater and 9 or less. The number of draws is considered valid only if positive.
   *
   * @param numRows the starting parameter of rows
   * @param numDraw the starting parameter of draw cards
   * @return True if the parameters are valid, false otherwise
   */
  protected boolean isValidParams(int numRows, int numDraw) {
    //Assume it is true
    boolean isValid = true;
    //If any params are invalid, return false
    if (numRows < 1 || numDraw < 0 || numRows > 8) {
      isValid = false;
    }

    int numPosDraw = 104;
    for (int i = numRows; i > 0; i--) {
      numPosDraw = numPosDraw - i;
    }
    if (numDraw > numPosDraw) {
      isValid = false;
    }
    return isValid;
  }

  /**
   * Determines if a given deck is valid. The criteria for validity is that it is not null and does
   * not contain greater or less than 104 cards.
   *
   * @param deck The deck to determine validity
   * @return True if deck is valid, false otherwise
   */
  protected boolean isValid(List<Card> deck) {
    //Assume true
    boolean isValid = true;

    if (deck == null) {
      return false;
    }

    for (int i = 0; i < deck.size(); i++) {
      for (int j = i + 1; j < deck.size(); j++) {
        for (int k = j + 1; k < deck.size(); k++) {
          if (deck.get(i).equals(deck.get(j)) && deck.get(i).equals(deck.get(k))) {
            return false;
          }
        }
      }
    }

    //if deck is valid, return true
    if (deck.size() != 104) {
      return false;
    }
    return isValid;
  }

  /**
   * Determines if the given card can be removed from the pyramid by determining if it is covered by
   * any cards below it.
   *
   * @param row  the row of the card under determination
   * @param card the column of the card under consideration
   * @return true if the card can be removed, false otherwise
   */
  protected boolean canRemove(int row, int card) {
    return super.canRemove(row, card);
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    super.remove(row1, card1, row2, card2);
  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {
    super.remove(row, card);
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    super.removeUsingDraw(drawIndex, row, card);
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    super.discardDraw(drawIndex);
  }

  @Override
  public int getNumRows() {
    return super.getNumRows();
  }

  @Override
  public int getNumDraw() {
    return super.getNumDraw();
  }

  @Override
  public int getRowWidth(int row) {
    int numCols = 0;
    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }

    if (row < 0 || row > gameBoard.length) {
      System.out.println("Row is " + row);
      System.out.println("Gameboard length is " + gameBoard.length);
      throw new IllegalArgumentException();
    }

    int firstRowLen = (gameBoard.length + 1) - (gameBoard.length % 2);

    return firstRowLen + row;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    return super.isGameOver();
  }

  /**
   * Determines whether there are any moves left on the board.
   *
   * @return true if moves are still possible, false otherwise
   */
  protected boolean anyMovesLeft() {
    return super.anyMovesLeft();
  }

  @Override
  public int getScore() throws IllegalStateException {
    return super.getScore();
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    return super.getCardAt(row, card);
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    return super.getDrawCards();
  }

  /**
   * Returns whether or not the game has started.
   *
   * @return true if game has started, false otherwise
   */
  protected boolean hasGameStarted() {
    return super.hasGameStarted();
  }
}

