package cs3500.pyramidsolitaire.model.hw02;

import java.util.List;

import cs3500.pyramidsolitaire.model.hw04.AbstractPyramidSolitaire;

/**
 * Class that models the pyramid solitaire gameboard.
 */
public class BasicPyramidSolitaire extends AbstractPyramidSolitaire {

  /**
   * Creates a playable instance of the pyramid solitaire game.
   */
  public BasicPyramidSolitaire() {
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
    return super.getRowWidth(row);
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

