import java.util.List;
import java.util.Objects;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Mock of the pyramid solitaire model in order to test interactions.
 */
public class PyramidSolitaireMock implements PyramidSolitaireModel {
  private final StringBuilder log;

  /**
   * Constructs an instance of the mock class in order to test interactions.
   * @param log log of given inputs
   */
  public PyramidSolitaireMock(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public List getDeck() {
    return null;
  }

  @Override
  public void startGame(List deck, boolean shouldShuffle, int numRows, int numDraw) {
    log.append(String.format("should shuffle = %b, number rows = %d, " +
            "number draw = %d\n", shouldShuffle, numRows, numDraw));
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    log.append(String.format("card 1 row = %d, card 1 col = %d, card 2 row = %d, " +
            "card 2 col = %d\n", row1, card1, row2, card2));
  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {
    log.append(String.format("card row = %d, card col = %d\n", row, card));
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    log.append(String.format("draw index = %d, card row = %d, card col = %d\n",
            drawIndex, row, card));
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    log.append(String.format("draw index = %d\n", drawIndex));
  }

  @Override
  public int getNumRows() {
    return 0;
  }

  @Override
  public int getNumDraw() {
    return 0;
  }

  @Override
  public int getRowWidth(int row) {
    return 0;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    return false;
  }

  @Override
  public int getScore() throws IllegalStateException {
    return 0;
  }

  @Override
  public Object getCardAt(int row, int card) throws IllegalStateException {
    return null;
  }

  @Override
  public List getDrawCards() throws IllegalStateException {
    return null;
  }
}