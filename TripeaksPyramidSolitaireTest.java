import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the correctness of the TripeaksPyramidSolitaire model.
 */
public class TripeaksPyramidSolitaireTest {
  private PyramidSolitaireModel ex1;

  private void initData() {
    ex1 = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.TRIPEAKS);
  }

  //tests if get deck returns a 104 card deck
  @Test
  public void return104() {
    initData();
    assertEquals(104, ex1.getDeck().size());
  }

  //tests if the deck returns the expected 104 card deck in order
  @Test
  public void expectedOrder1() {
    initData();
    assertEquals("K♠", ex1.getDeck().get(12).toString());
  }

  @Test
  public void expectedOrder2() {
    initData();
    assertEquals("2♥", ex1.getDeck().get(14).toString());
  }

  //Throws exception when deck has less than 104 cards
  @Test(expected = IllegalArgumentException.class)
  public void lessThan104() {
    initData();
    ArrayList<Card> smallDeck = new ArrayList<Card>(52);
    ex1.startGame(smallDeck, false, 2, 1);
  }

  //throws exception when deck has more than 104 cards
  @Test(expected = IllegalArgumentException.class)
  public void moreThan104() {
    initData();
    ArrayList<Card> bigDeck = new ArrayList<Card>(105);
    ex1.startGame(bigDeck, false, 2, 1);
  }

  //throws exception when deck is null
  @Test(expected = IllegalArgumentException.class)
  public void startWithNull() {
    initData();
    ArrayList<Card> badDeck = null;
    ex1.startGame(badDeck, false, 4, 1);
  }

  //throws exception when deck has more than 2 copies
  @Test(expected = IllegalArgumentException.class)
  public void noTwoCopies() {
    initData();
    List<Card> badDeck = ex1.getDeck();
    Card extraCard = new Card("Hearts", 3);
    badDeck.remove(0);
    badDeck.add(extraCard);
    ex1.startGame(badDeck, false, 4, 3);
  }

  //throws exception when numRows is less than 1
  @Test(expected = IllegalArgumentException.class)
  public void startWhenRowsLess1() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 0, 1);
  }

  //throws exception when numDraw is less than 1
  @Test(expected = IllegalArgumentException.class)
  public void startWhenDrawLess1() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, -3);
  }

  //tests if deck is shuffled when shuffled parameter is asked for
  @Test
  public void shuffledWhenAskedFor() {
    initData();
    ex1.startGame(ex1.getDeck(), true, 2, 1);
    Card testCard = (Card) ex1.getDeck().get(0);
    assertFalse(new Card("Spades", 5).equals(testCard));
  }

  //tests that deck is not shuffled when shuffled parameter is not asked for
  @Test
  public void notShuffledWhenNotAskedFor() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    Card testCard = (Card) ex1.getDeck().get(4);
    assertTrue(new Card("Spades", 5).equals(testCard));
  }

  //check that the gameboard has the correct number of rows
  @Test
  public void startCorrectRowsBoard() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    assertEquals(2, ex1.getNumRows());

  }

  //check that the gameboard has the correct number of columns in each row
  @Test
  public void startCorrectColsInRowBoard() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    assertEquals(4, ex1.getRowWidth(1));

  }

  //check that drawDeck has the correct number of cards added to it
  @Test
  public void startDrawDeckAddedCards() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    assertEquals(1, ex1.getNumDraw());

  }

  //check that game score has been calculated correctly
  @Test
  public void startGameScoreCalculation() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    assertEquals(28, ex1.getScore());

  }

  //throws exception when game isn't started
  @Test(expected = IllegalStateException.class)
  public void remove1NotStarted() {
    initData();
    ex1.remove(1, 0, 1, 1);

  }

  //throws exception when card1 can't be removed
  @Test(expected = IllegalArgumentException.class)
  public void remove1Card1NotRemovable() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    ex1.remove(0, 0, 1, 1);

  }

  //throws exception when card2 can't be removed
  @Test(expected = IllegalArgumentException.class)
  public void remove1Card2NotRemovable() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    ex1.remove(1, 1, 0, 0);

  }

  //throws exception when card values don't add to 13
  @Test(expected = IllegalArgumentException.class)
  public void remove1ValuesNot13() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    ex1.remove(1, 1, 1, 0);

  }

  //throws exception when card1 position is null
  @Test(expected = IllegalArgumentException.class)
  public void remove1Card1Null() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    ex1.remove(3, 3, 0, 0);

  }

  //throws exception when card2 position is null
  @Test(expected = IllegalArgumentException.class)
  public void remove1Card2Null() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    ex1.remove(1, 1, 3, 3);

  }

  //tests that game score has decreased by 13
  @Test
  public void remove1ScoreUpdated() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(4, 7, 4, 8);
    assertEquals(197, ex1.getScore());
  }

  //tests that card1's position is now null
  @Test
  public void remove1Card1Updated() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(4, 7, 4, 8);
    assertEquals(null, ex1.getCardAt(4, 7));
  }

  //tests that card2's position is now null
  @Test
  public void remove1Card2Updated() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(4, 7, 4, 8);
    assertEquals(null, ex1.getCardAt(4, 8));
  }

  //throws exception when game isn't started
  @Test(expected = IllegalStateException.class)
  public void remove2GameNotStarted() {
    initData();
    ex1.remove(4, 0);
  }

  //throws exception when card can't be removed
  @Test(expected = IllegalArgumentException.class)
  public void remove2CardUnremovable() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(0, 0);
  }

  //throws exception if card value is not 13
  @Test(expected = IllegalArgumentException.class)
  public void remove2ValueNot13() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(4, 0);
  }

  //throws exception when card position is null
  @Test(expected = IllegalArgumentException.class)
  public void remove2cardNull() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(5, 0);
  }

  //throws exception when already removed card is removed
  @Test(expected = IllegalArgumentException.class)
  public void remove2CardAlreadyRemoved() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(4, 2);
    ex1.remove(4, 2);
  }

  //tests that game score has decreased by 13
  @Test
  public void remove2ScoreUpdated() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(4, 1);
    assertEquals(197, ex1.getScore());
  }

  //test that card's position is null
  @Test
  public void remove2CardUpdated() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.remove(4, 1);
    assertEquals(null, ex1.getCardAt(4, 1));
  }

  //throws exception when game isn't started
  @Test(expected = IllegalStateException.class)
  public void removeDrawNotStarted() {
    initData();
    ex1.removeUsingDraw(1, 3, 3);
  }

  //throws exception when index is less than 0
  @Test(expected = IllegalArgumentException.class)
  public void removeDrawIndexLess0() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.removeUsingDraw(-1, 2, 2);
  }

  //throws exception when index is greater than drawSize
  @Test(expected = IllegalArgumentException.class)
  public void drawRemoveIndexTooBig() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.removeUsingDraw(2, 2, 2);
  }

  //throws exception when card can't be removed
  @Test(expected = IllegalArgumentException.class)
  public void drawRemoveCardUnremovable() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.removeUsingDraw(0, 0, 0);
  }

  //throws exception when card values don't add up to 13
  @Test(expected = IllegalArgumentException.class)
  public void drawRemoveValuesNot13() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.removeUsingDraw(0, 2, 1);
  }

  //throws exception when card position is null
  @Test(expected = IllegalArgumentException.class)
  public void drawRemoveCardNull() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.removeUsingDraw(0, 3, 0);
  }

  //tests that game score has decreased by 13
  @Test
  public void drawRemoveUpdateScore() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.removeUsingDraw(0, 4, 6);
    assertEquals(205, ex1.getScore());
  }

  //tests that card's position is now null
  @Test
  public void drawRemoveUpdateCard() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 1);
    ex1.removeUsingDraw(0, 4, 6);
    assertEquals(null, ex1.getCardAt(4, 6));
  }

  //throws exception when game hasn't started
  @Test(expected = IllegalStateException.class)
  public void discardGameNotStarted() {
    initData();
    ex1.discardDraw(1);
  }

  //throws exception when index position is null
  @Test(expected = IllegalArgumentException.class)
  public void discardIndexNull() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.discardDraw(1);
  }

  //throws exception when index position is less than 0
  @Test(expected = IllegalArgumentException.class)
  public void discardIndexLess0() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.discardDraw(-1);
  }

  //tests that card is removed from drawDeck
  @Test
  public void discardCardRemoved() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 3);
    ex1.discardDraw(0);
    assertEquals("3♥", ex1.getDrawCards().get(0).toString());
  }

  //tests that card is added to drawDeck 45
  @Test
  public void discardCardAdded() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 3);
    ex1.discardDraw(0);
    assertEquals("2♥", ex1.getDrawCards().get(2).toString());
  }

  //returns -1 when game isn't started
  @Test
  public void numRowsNotStarted() {
    initData();
    assertEquals(-1, ex1.getNumRows());
  }

  //tests that the correct number of rows is returned
  @Test
  public void numRowsReturned() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    assertEquals(3, ex1.getNumRows());
  }

  //throws exception when game isn't started
  @Test
  public void numDrawNotStarted() {
    initData();
    assertEquals(-1, ex1.getNumDraw());
  }

  //tests that the correct deck size is returned
  @Test
  public void numDrawCorrect() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    assertEquals(1, ex1.getNumDraw());
  }

  //throws exception when game isn't started
  @Test(expected = IllegalStateException.class)
  public void rowWidthNotStarted() {
    initData();
    ex1.getRowWidth(1);
  }

  //throws exception when row is less than 0
  @Test(expected = IllegalArgumentException.class)
  public void rowWidthLess0() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.getRowWidth(-1);
  }

  //throws exception when row is greater than board length
  @Test(expected = IllegalArgumentException.class)
  public void rowWidthTooBig() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.getRowWidth(4);
  }

  //tests that correct col length is returned
  @Test
  public void rowWidthCorrect() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    assertEquals(4, ex1.getRowWidth(1));
  }

  //throws exception when game isn't started
  @Test(expected = IllegalStateException.class)
  public void gameOverNotStarted() {
    initData();
    ex1.isGameOver();
  }

  //tests that game is not over when no exposed cards add to 13 but card in draw pile adds to 13
  @Test
  public void gameNotOverCardsInDraw() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 3);
    assertFalse(ex1.isGameOver());
  }

  //tests that game is not over when score is not 0 and moves are left
  @Test
  public void gameNotOverMovesLeft() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 3);
    assertFalse(ex1.isGameOver());
  }

  //throws exception when game isn't started
  @Test(expected = IllegalStateException.class)
  public void getScoreNotStarted() {
    initData();
    ex1.getScore();
  }

  //tests that correct game score is returned
  @Test
  public void getScoreCorrect() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    assertEquals(78, ex1.getScore());
  }

  //throws exception when game isn't started
  @Test(expected = IllegalStateException.class)
  public void cardAtNotStarted() {
    initData();
    ex1.getCardAt(0, 0);
  }

  //throws exception when card in given position is null
  @Test(expected = IllegalArgumentException.class)
  public void cardAtNull() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 1);
    ex1.getCardAt(0, 3);
  }

  //tests that correct card is returned
  @Test
  public void cardAtCorrect() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    assertEquals("A♠", ex1.getCardAt(0, 0).toString());
  }

  //throws exception when game isn't started
  @Test(expected = IllegalStateException.class)
  public void getDrawNotStarted() {
    initData();
    ex1.getDrawCards();
  }

  //test get draw cards
  @Test
  public void getDrawCorrect() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 1);
    Card testCard = (Card) ex1.getDrawCards().get(0);
    assertFalse(new Card("Spades", 5).equals(testCard));
  }

  //test duplicates don't pass
  @Test(expected = IllegalArgumentException.class)
  public void duplicatesNotPass() {
    initData();
    List<Card> myList = ex1.getDeck();
    Card card1 = new Card("Hearts", 5);
    myList.set(32, card1);
    ex1.startGame(myList, false, 5, 3);
  }

  //test duplicates don't pass
  @Test
  public void noDuplicatesDoesPass() {
    initData();
    List<Card> myList = ex1.getDeck();
    ex1.startGame(myList, true, 7, 10);
    assertNotNull(ex1);
    List<Card> myList2 = ex1.getDeck();
    ex1.startGame(myList2, true, 7, 10);
    assertNotNull(ex1);
  }

  //test that full triangle with 10's on edge and in middle can be printed correctly
  @Test
  public void testPrintFullTriangle() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 8, 2);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("              A♠              2♠              3♠\n" +
            "            4♠  5♠          6♠  7♠          8♠  9♠\n" +
            "          10♠ J♠  Q♠      K♠  A♥  2♥      3♥  4♥  5♥\n" +
            "        6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥  A♦  2♦  3♦  4♦\n" +
            "      5♦  6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦  A♣  2♣  3♣  4♣\n" +
            "    5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣  K♣  A♠  2♠  3♠  4♠  5♠\n" +
            "  6♠  7♠  8♠  9♠  10♠ J♠  Q♠  K♠  A♥  2♥  3♥  4♥  5♥  6♥  7♥\n" +
            "8♥  9♥  10♥ J♥  Q♥  K♥  A♦  2♦  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
            "Draw: J♦, Q♦", t1.toString());
  }

  //test that triangle after removal prints correctly
  @Test
  public void triangleAfterRemoval() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 7, 2);
    ex1.remove(6, 0);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("            A♠          2♠          3♠\n" +
            "          4♠  5♠      6♠  7♠      8♠  9♠\n" +
            "        10♠ J♠  Q♠  K♠  A♥  2♥  3♥  4♥  5♥\n" +
            "      6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
            "    3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦\n" +
            "  A♣  2♣  3♣  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣\n" +
            "    A♠  2♠  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\n" +
            "Draw: K♠, A♥", t1.toString());
  }

  //test that triangle after removal by draw prints correctly
  @Test
  public void triangleAfterRemovalByDraw() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 7, 2);
    ex1.discardDraw(1);
    ex1.removeUsingDraw(1, 6, 11);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("            A♠          2♠          3♠\n" +
            "          4♠  5♠      6♠  7♠      8♠  9♠\n" +
            "        10♠ J♠  Q♠  K♠  A♥  2♥  3♥  4♥  5♥\n" +
            "      6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
            "    3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦\n" +
            "  A♣  2♣  3♣  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣\n" +
            "K♣  A♠  2♠  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠     Q♠\n" +
            "Draw: K♠, 3♥", t1.toString());
  }

  //test that triangle after removal from pyramid prints correctly
  @Test
  public void triangleAfterRemovalFromPyramid() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 7, 2);
    ex1.remove(6, 6, 6, 7);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("            A♠          2♠          3♠\n" +
            "          4♠  5♠      6♠  7♠      8♠  9♠\n" +
            "        10♠ J♠  Q♠  K♠  A♥  2♥  3♥  4♥  5♥\n" +
            "      6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
            "    3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦\n" +
            "  A♣  2♣  3♣  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣\n" +
            "K♣  A♠  2♠  3♠  4♠  5♠          8♠  9♠  10♠ J♠  Q♠\n" +
            "Draw: K♠, A♥", t1.toString());
  }

  //test that triangle with 8 rows prints correctly
  @Test
  public void triangle8() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 8, 2);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("              A♠              2♠              3♠\n" +
            "            4♠  5♠          6♠  7♠          8♠  9♠\n" +
            "          10♠ J♠  Q♠      K♠  A♥  2♥      3♥  4♥  5♥\n" +
            "        6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥  A♦  2♦  3♦  4♦\n" +
            "      5♦  6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦  A♣  2♣  3♣  4♣\n" +
            "    5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣  K♣  A♠  2♠  3♠  4♠  5♠\n" +
            "  6♠  7♠  8♠  9♠  10♠ J♠  Q♠  K♠  A♥  2♥  3♥  4♥  5♥  6♥  7♥\n" +
            "8♥  9♥  10♥ J♥  Q♥  K♥  A♦  2♦  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
            "Draw: J♦, Q♦", t1.toString());
  }

  //test that triangle with 7 rows prints correctly
  @Test
  public void triangle7() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 7, 2);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("            A♠          2♠          3♠\n" +
            "          4♠  5♠      6♠  7♠      8♠  9♠\n" +
            "        10♠ J♠  Q♠  K♠  A♥  2♥  3♥  4♥  5♥\n" +
            "      6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
            "    3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦\n" +
            "  A♣  2♣  3♣  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣\n" +
            "K♣  A♠  2♠  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\n" +
            "Draw: K♠, A♥", t1.toString());
  }

  //test that triangle with 5 rows prints correctly
  @Test
  public void triangle5() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 5, 2);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("        A♠      2♠      3♠\n" +
            "      4♠  5♠  6♠  7♠  8♠  9♠\n" +
            "    10♠ J♠  Q♠  K♠  A♥  2♥  3♥\n" +
            "  4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥\n" +
            "Q♥  K♥  A♦  2♦  3♦  4♦  5♦  6♦  7♦\n" +
            "Draw: 8♦, 9♦", t1.toString());
  }

  //test that triangle with 3 rows prints correctly
  @Test
  public void triangle3() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 3, 2);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("    A♠  2♠  3♠\n" +
            "  4♠  5♠  6♠  7♠\n" +
            "8♠  9♠  10♠ J♠  Q♠\n" +
            "Draw: K♠, A♥", t1.toString());
  }

  //test that triangle with 2 rows prints correctly
  @Test
  public void triangle2() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 2, 5);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("  A♠  2♠  3♠\n" +
            "4♠  5♠  6♠  7♠\n" +
            "Draw: 8♠, 9♠, 10♠, J♠, Q♠", t1.toString());
  }

  //test that triangle with 1 row prints correctly
  @Test
  public void triangle1() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 1, 3);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);
    assertEquals("A♠\n" +
            "Draw: 2♠, 3♠, 4♠", t1.toString());
  }

  //tests game over when no moves are left
  @Test
  public void gameOverNoMoves() {
    initData();
    List<Card> deck = ex1.getDeck();
    System.out.println(deck.size());
    ex1.startGame(deck, false, 1, 1);
    for (int i = 0; i < 103; i++) {
      ex1.discardDraw(0);
    }
    assertTrue(ex1.isGameOver());
  }

  //tests that startGame throws illegal argument if number of rows is greater than 9
  @Test(expected = IllegalArgumentException.class)
  public void numRowsGreater() {
    initData();
    ex1.startGame(ex1.getDeck(), false, 10, 2);
  }

}
