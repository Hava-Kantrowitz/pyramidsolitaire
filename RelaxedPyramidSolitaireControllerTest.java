import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;

import static org.junit.Assert.assertEquals;

/**
 * Tests the controller.
 */
public class RelaxedPyramidSolitaireControllerTest {

  private static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }

  private static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line);
      }
    };
  }

  private void testRun(PyramidSolitaireModel<Card> model, List<Card> deck,
                       boolean shouldShuffle, int numRows, int numDraw,
                       Interaction... interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    PyramidSolitaireController controller =
            new PyramidSolitaireTextualController(input, actualOutput);
    controller.playGame(model, deck, shouldShuffle, numRows, numDraw);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  //test rm2
  @Test
  public void testrm2() throws IOException {
    Interaction[] interactions = {
            inputs("rm2 7 3 7 7 q"),
            prints("            A♠\n"
                    + "          2♠  3♠\n"
                    + "        4♠  5♠  6♠\n"
                    + "      7♠  8♠  9♠  10♠\n"
                    + "    J♠  Q♠  K♠  A♥  2♥\n"
                    + "  3♥  4♥  5♥  6♥  7♥  8♥\n"
                    + "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n"
                    + "Draw: 3♦, 4♦\n"
                    + "Score: 185\n"
                    + "            A♠\n"
                    + "          2♠  3♠\n"
                    + "        4♠  5♠  6♠\n"
                    + "      7♠  8♠  9♠  10♠\n"
                    + "    J♠  Q♠  K♠  A♥  2♥\n"
                    + "  3♥  4♥  5♥  6♥  7♥  8♥\n"
                    + "9♥  10♥     Q♥  K♥  A♦\n"
                    + "Draw: 3♦, 4♦\n"
                    + "Score: 172\n"
                    + "Game quit!\n"
                    + "State of game when quit:\n"
                    + "            A♠\n"
                    + "          2♠  3♠\n"
                    + "        4♠  5♠  6♠\n"
                    + "      7♠  8♠  9♠  10♠\n"
                    + "    J♠  Q♠  K♠  A♥  2♥\n"
                    + "  3♥  4♥  5♥  6♥  7♥  8♥\n"
                    + "9♥  10♥     Q♥  K♥  A♦\n"
                    + "Draw: 3♦, 4♦\n"
                    + "Score: 172\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test rm1
  @Test
  public void testrm1() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 7 5 q"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test rmwd
  @Test
  public void testrmwd() throws IOException {
    Interaction[] interactions = {
            inputs("rmwd 1 7 2"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥      J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 5♦, 4♦\n" +
                    "Score: 175\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test dd
  @Test
  public void testdd() throws IOException {
    Interaction[] interactions = {
            inputs("dd 1"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 5♦, 4♦\n" +
                    "Score: 185\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test with q in first position
  @Test
  public void testqFirstPos() throws IOException {
    Interaction[] interactions = {
            inputs("q rm1 7 5"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test with q in position of a number
  @Test
  public void testQNumPos() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 Q 5"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test with q in position of a command
  @Test
  public void testqCommandPos() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 7 5 q 3 4"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test with junk input, card shift
  @Test
  public void testJunkInput() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 hava 7 5 Q"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "Invalid move. Play again. Input not a number.\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test with an invalid card rm1
  @Test
  public void testInvalidRm1() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 9 7 5"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "Invalid move. Play again. Card position invalid.\n" +
                    "Invalid command. Please try again.\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test with an invalid card rm2
  @Test
  public void testInvalidRm2() throws IOException {
    Interaction[] interactions = {
            inputs("rm2 10 50 1 5"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "Invalid move. Play again. Card position invalid.\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test with an invalid card rmwd
  @Test
  public void testInvalidRmwd() throws IOException {
    Interaction[] interactions = {
            inputs("rmwd 1 7 1"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "Invalid move. Play again. Card position invalid.\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test with an invalid card dd
  @Test
  public void testInvalidDd() throws IOException {
    Interaction[] interactions = {
            inputs("dd -1"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "Invalid move. Play again. Card position invalid.\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test two correct plays
  @Test
  public void testInvalidMultipleCorrectPlays() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 7 5 rm2 7 4 7 6 rm2 6 4 6 5"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥              2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 159\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥          8♥\n" +
                    "9♥  10♥ J♥              2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 146\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test a correct play and incorrect play
  @Test
  public void testCorrectIncorrect() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 7 5 rm2 7 1 6 2 rm2 ghyd 4 6 5 7"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n" +
                    "Invalid move. Play again. Card position invalid.\n" +
                    "Invalid move. Play again. Input not a number.\n" +
                    "Invalid move. Play again. Card position invalid.\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //test an incorrect play and a correct play
  @Test
  public void testIncorrectCorrect() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 8 3 rmwd 1 7 2"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "Invalid move. Play again. Card position invalid.\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥      J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 5♦, 4♦\n" +
                    "Score: 175\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }


  //command with nothing after it
  @Test
  public void testCommandNothing() throws IOException {
    Interaction[] interactions = {
            inputs("dd"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //tests a win
  @Test
  public void testWin() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 1 1"),
            prints("K♠\n" +
                    "Draw: A♠\n" +
                    "Score: 13\n" +
                    "You win!\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    Card kingCard = deck.get(12);
    deck.remove(12);
    deck.add(0, kingCard);
    testRun(model, deck, false, 1, 1, interactions);
  }

  //tests a win not at end
  @Test
  public void testWinNotEnd() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 1 1 rm2 3 4 5 6"),
            prints("K♠\n" +
                    "Draw: A♠\n" +
                    "Score: 13\n" +
                    "You win!\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    Card kingCard = deck.get(12);
    deck.remove(12);
    deck.add(0, kingCard);
    testRun(model, deck, false, 1, 1, interactions);
  }

  //tests a loss
  @Test
  public void testLoss() throws IOException {
    Interaction[] interactions = {
            inputs("dd 1 dd 2 dd 3 dd 1 dd 2 dd 4 dd 5 rm1 9 3 rm2 9 2 9 4 rm2 9 1 9 5"),
            prints("                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦  K♦  A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: 7♣, 8♣, 9♣, 10♣, J♣\n" +
                    "Score: 294\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦  K♦  A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: Q♣, 8♣, 9♣, 10♣, J♣\n" +
                    "Score: 294\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦  K♦  A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: Q♣, K♣, 9♣, 10♣, J♣\n" +
                    "Score: 294\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦  K♦  A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: Q♣, K♣, null10♣, J♣\n" +
                    "Score: 294\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦  K♦  A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: nullK♣, null10♣, J♣\n" +
                    "Score: 294\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦  K♦  A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: nullnullnull10♣, J♣\n" +
                    "Score: 294\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦  K♦  A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: nullnullnullnullJ♣\n" +
                    "Score: 294\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦  K♦  A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: \n" +
                    "Score: 294\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦  Q♦      A♣  2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: \n" +
                    "Score: 281\n" +
                    "                A♠\n" +
                    "              2♠  3♠\n" +
                    "            4♠  5♠  6♠\n" +
                    "          7♠  8♠  9♠  10♠\n" +
                    "        J♠  Q♠  K♠  A♥  2♥\n" +
                    "      3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "    9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "  3♦  4♦  5♦  6♦  7♦  8♦  9♦  10♦\n" +
                    "J♦              2♣  3♣  4♣  5♣  6♣\n" +
                    "Draw: \n" +
                    "Score: 268\n" +
                    "Game over. Score: 255\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 9, 5, interactions);
  }

  //tests white space not at end
  @Test
  public void testWhiteSpace() throws IOException {
    Interaction[] interactions = {
            inputs("rm2 7 3 7 7"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥     Q♥  K♥  A♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    List<Card> deck = model.getDeck();
    testRun(model, deck, false, 7, 2, interactions);
  }

  //tests throws illegal state exception for illegal arguments
  @Test(expected = IllegalStateException.class)
  public void testThrowsState() throws IOException {
    Interaction[] interactions = {
            inputs("rm1 7 5 rm2 7 1 6 2 rm2 ghyd 4 6 5 7"),
            prints("            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥  K♥  A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 185\n" +
                    "            A♠\n" +
                    "          2♠  3♠\n" +
                    "        4♠  5♠  6♠\n" +
                    "      7♠  8♠  9♠  10♠\n" +
                    "    J♠  Q♠  K♠  A♥  2♥\n" +
                    "  3♥  4♥  5♥  6♥  7♥  8♥\n" +
                    "9♥  10♥ J♥  Q♥      A♦  2♦\n" +
                    "Draw: 3♦, 4♦\n" +
                    "Score: 172\n" +
                    "Invalid move. Play again. Card position invalid.\n" +
                    "Invalid move. Play again. Input not a number.\n" +
                    "Invalid move. Play again. Card position invalid.\n")
    };
    PyramidSolitaireModel<Card> model = new RelaxedPyramidSolitaire();
    testRun(model, null, false, 7, 2, interactions);
  }

}
