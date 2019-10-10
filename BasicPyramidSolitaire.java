package cs3500.pyramidsolitaire.model.hw02;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import cs3500.pyramidsolitaire.model.hw04.AbstractPyramidSolitaire;

/**
 * Class that models the pyramid solitaire gameboard.
 */
public class BasicPyramidSolitaire extends AbstractPyramidSolitaire implements PyramidSolitaireModel<Card> {

  private String[] suit = {"Spades", "Hearts", "Diamonds", "Clubs"};
  private ArrayList<Card> myDeck = new ArrayList<Card>();
  private ArrayList<Card> myDeck2 = new ArrayList<Card>();
  private ArrayList<Card> drawDeck = new ArrayList<Card>();
  private Card[][] gameBoard;
  private int gameScore;
  private boolean gameStarted = false;

  /**
   * Creates a playable instance of the pyramid solitaire game.
   */
  public BasicPyramidSolitaire() {
    //sets the score to 0
    this.gameScore = 0;
  }

  @Override
  public List<Card> getDeck() {
    //Loops through each suit and number value, creating the card and
    //adding it to the list of cards deck
    myDeck2.clear();

    for (int i = 0; i < 4; i++) {
      for (int j = 1; j <= 13; j++) {
        Card nextCard = new Card(suit[i], j);
        myDeck2.add(nextCard);
      }
    }

    return myDeck2;
  }

  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw) {
    drawDeck.clear();


    //if the deck itself is valid and the parameters are valid, keep going

    if (isValid(deck) && isValidParams(numRows, numDraw)) {
      //initialize the gameboard
      gameBoard = new Card[numRows][numRows];
      myDeck = new ArrayList<>(deck);


      //if shuffle is requested, shuffle it
      if (shouldShuffle) {
        Collections.shuffle(myDeck);
      }

      //Loops through each row and card
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j <= i; j++) {
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
      }
      else if (!isValidParams(numRows, numDraw)) {
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
  private boolean isValidParams(int numRows, int numDraw) {
    //Assume it is true
    boolean isValid = true;
    //If any params are invalid, return false
    if (numRows < 1 || numDraw < 0 || numRows > 9) {
      isValid = false;
    }

    int numPosDraw = 52;
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
   * not contain greater or less than 52 cards.
   *
   * @param deck The deck to determine validity
   * @return True if deck is valid, false otherwise
   */
  private boolean isValid(List<Card> deck) {
    //Assume true
    boolean isValid = true;

    if (deck == null) {
      return false;
    }

    for (int i = 0; i < deck.size(); i++) {
      for (int j = i + 1; j < deck.size(); j++) {
        if (deck.get(i).equals(deck.get(j))) {
          return false;
        }
      }
    }

    //if deck is valid, return true
    if (deck.size() != 52) {
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
  private boolean canRemove(int row, int card) {
    //If the card is in the final row, it is automatically unexposed
    if (row == gameBoard.length - 1) {
      return true;
    }

    //Return whether the card below isn't there
    if ((getCardAt(row + 1, card) != null)) {
      return false;
    }
    else if (getCardAt(row + 1, card + 1) != null) {
      return false;
    }
    return true;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {

    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }

    //If either card doesn't exist, illegal inputs
    Card firstCard = getCardAt(row1, card1);
    Card secondCard = getCardAt(row2, card2);
    if (firstCard == null || secondCard == null) {
      throw new IllegalArgumentException();
    }

    //get the value of each card
    int val1 = firstCard.getValue();
    int val2 = secondCard.getValue();

    //if the values equal 13 and both can be removed
    if ((val1 + val2) == 13 && canRemove(row1, card1) && canRemove(row2, card2)) {
      //Decrease the score, set both positions to null
      gameScore -= val1 + val2;
      gameBoard[row1][card1] = null;
      gameBoard[row2][card2] = null;
    } else {
      throw new IllegalArgumentException();
    }


  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {

    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }

    //if card doesn't exist, illegal argument
    if (getCardAt(row, card) == null) {
      throw new IllegalArgumentException();
    }

    //get the card value
    int cardVal = gameBoard[row][card].getValue();

    //if the value is 13 and it can be removed
    if (cardVal == 13 && canRemove(row, card)) {
      //decrease score, remove from pyramid
      gameBoard[row][card] = null;
      gameScore -= cardVal;
    } else {
      throw new IllegalArgumentException();
    }


  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }

    if (drawIndex > drawDeck.size() - 1 || drawIndex < 0 || getCardAt(row, card) == null) {
      if (drawIndex < 0) {
        throw new IllegalArgumentException("draw index is less than 0");
      }
      if (drawIndex > drawDeck.size() - 1) {
        throw new IllegalArgumentException("draw index is greater than the deck size");
      }
      throw new IllegalArgumentException("given card is null");
    }

    //get both values
    int val1 = drawDeck.get(drawIndex).getValue();
    int val2 = gameBoard[row][card].getValue();

    if ((val1 + val2 == 13) && canRemove(row, card)) {
      //Remove card from pyramid, remove from discard pile, decrease score
      gameBoard[row][card] = null;
      gameScore -= val2;
      discardDraw(drawIndex);
    }
    else {
      throw new IllegalArgumentException("val1: " + val1 + ", val2: " + val2);
    }

  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }

    if (drawIndex > drawDeck.size() - 1 || drawIndex < 0 || drawDeck.get(drawIndex) == null) {
      throw new IllegalArgumentException();
    }

    //if the deck still has cards to draw from
    if (myDeck.size() > 0) {
      //add a card to the draw deck and remove it from the main deck
      drawDeck.set(drawIndex, myDeck.remove(0));
    } else {
      drawDeck.set(drawIndex, null);
    }


  }

  @Override
  public int getNumRows() {
    if (!hasGameStarted()) {
      return -1;
    }
    return gameBoard.length;
  }

  @Override
  public int getNumDraw() {
    if (!hasGameStarted()) {
      return -1;
    }
    return drawDeck.size();
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

    return row + 1;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }

    //determines if the score is 0 or if there aren't any moves left
    return (getScore() == 0 || !anyMovesLeft());
  }

  /**
   * Determines whether there are any moves left on the board.
   *
   * @return true if moves are still possible, false otherwise
   */
  private boolean anyMovesLeft() {

    int nullCounter = 0;
    for (int s = 0; s < getDrawCards().size(); s++) {
      if (getDrawCards().get(s) == null) {
        nullCounter++;
      }
    }

    if (nullCounter != getDrawCards().size()) {
      return true;
    }

    for (int i = 0; i < getNumRows(); i++) {
      for (int j = 0; j < getRowWidth(i); j++) {
        Card testCard = getCardAt(i, j);
        if (testCard != null) {
          if (canRemove(i,j) && testCard.getValue() == 13) {
            return true;
          }

          for (Card s : drawDeck) {
            if (s != null) {
              if (canRemove(i, j) && s.getValue() + testCard.getValue() == 13) {
                return true;
              }
            }
          }
        }

        for (int k = 0; k < getNumRows(); k++) {
          for (int r = 0; r < getRowWidth(k); r++) {
            if (canRemove(i, j) && canRemove(k, r)) {
              Card card1 = getCardAt(i, j);
              Card card2 = getCardAt(k, r);
              if (card1 != null && card2 != null) {
                if (card1.getValue() + card2.getValue() == 13) {
                  return true;
                }
              }

            }
          }
        }
      }
    }

    return false;
  }

  @Override
  public int getScore() throws IllegalStateException {
    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }
    return gameScore;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }

    if (row < 0 || card < 0 || row > getNumRows() - 1 || card > getNumRows() - 1) {
      if (row < 0) {
        throw new IllegalArgumentException("row less than 0");
      }
      if (card < 0) {
        throw new IllegalArgumentException("card less than 0");
      }
      if (row > getNumRows() - 1) {
        throw new IllegalArgumentException("row greater than numRows");
      }
      throw new IllegalArgumentException("something wrong with row" + " " + card);
    }

    return gameBoard[row][card];
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    if (!hasGameStarted()) {
      throw new IllegalStateException();
    }
    ArrayList<Card> drawCopy = new ArrayList<>();
    drawCopy.addAll(drawDeck);
    return drawCopy;
  }

  /**
   * Returns whether or not the game has started.
   *
   * @return true if game has started, false otherwise
   */
  private boolean hasGameStarted() {
    return gameStarted;
  }
}

