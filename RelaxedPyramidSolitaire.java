package cs3500.pyramidsolitaire.model.hw04;

import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.Card;

/**
 * Class that models the relaxed pyramid solitaire gameboard.
 */
public class RelaxedPyramidSolitaire extends AbstractPyramidSolitaire {

  /**
   * Creates a playable instance of the pyramid solitaire game.
   */
  public RelaxedPyramidSolitaire() {
    super();
  }

  @Override
  public List<Card> getDeck() {
    return super.getDeck();
  }

  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw) {
    super.startGame(deck, shouldShuffle, numRows, numDraw);
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
    return super.isValidParams(numRows, numDraw);
  }

  /**
   * Determines if a given deck is valid. The criteria for validity is that it is not null and does
   * not contain greater or less than 52 cards.
   *
   * @param deck The deck to determine validity
   * @return True if deck is valid, false otherwise
   */
  protected boolean isValid(List<Card> deck) {
    return super.isValid(deck);
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

    //if the values equal 13
    if ((val1 + val2) == 13) {
      //if both can be removed
      if (canRemove(row1, card1) && canRemove(row2, card2)) {
        //Decrease the score, set both positions to null
        gameScore -= val1 + val2;
        gameBoard[row1][card1] = null;
        gameBoard[row2][card2] = null;
      }

      //if one can be removed and it is covering the other one -- card 1
      else if (canRemove(row1, card1) && isCovered(row1, card1, row2, card2)
              && otherIsNull(row2, card2)) {
        gameScore -= val1 + val2;
        gameBoard[row1][card1] = null;
        gameBoard[row2][card2] = null;
      }

      //if one can be removed and it is covering the other one -- card 2
      else if (canRemove(row2, card2) && isCovered(row2, card2, row1, card1)
              && otherIsNull(row1, card1)) {
        gameScore -= val1 + val2;
        gameBoard[row1][card1] = null;
        gameBoard[row2][card2] = null;
      }

      //otherwise throw exception
      else {
        throw new IllegalArgumentException();
      }
    } else {
      throw new IllegalArgumentException();
    }


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
    return super.getRowWidth(row);
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    return super.isGameOver();
  }

  /**
   * Determines if one of the cards below it is null.
   * @param row the row of the card
   * @param card the column of the card
   * @return true if it has at least one null card below, false otherwise
   */
  private boolean otherIsNull(int row, int card) {
    //if one of the below cards is null, return true
    //because we already checked that the other one is covered, we just need to check
    //that the other is null, doesn't matter which
    return (getCardAt(row + 1, card) == null)
            || getCardAt(row + 1, card + 1) == null;
  }

  /**
   * Determines if the second card is covered by the first card.
   *
   * @param row1  the row of the first card
   * @param card1 the column of the first card
   * @param row2  the row of the second card
   * @param card2 the column of the second car
   * @return true if the card is covered by it, false otherwise
   */
  private boolean isCovered(int row1, int card1, int row2, int card2) {
    //if the row is correct
    if (row2 + 1 == row1) {
      //and the column is correct
      if (card2 + 1 == card1 || card2 == card1) {
        return true;
      }
    }

    //otherwise
    return false;
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
