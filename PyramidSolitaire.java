package cs3500.pyramidsolitaire;

import java.io.InputStreamReader;
import java.util.Scanner;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;

/**
 * Allows user to play pyramid solitaire game.
 */
public final class PyramidSolitaire {

  /**
   * Allows user to start playing the pyramid solitaire game.
   * @param args the arguments to the function, in this case the gametype
   *             and the number of rows/draw cards if desired
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String game = "";
    boolean isInteger;
    int numRows = 7;
    int numDraw = 3;

    for (int i = 0; i < args.length; i++) {
      try {
        Integer.parseInt(args[i]);
        isInteger = true;
      } catch (NumberFormatException e) {
        isInteger = false;
      } catch (NullPointerException p) {
        break;
      }
      if (isInteger) {
        numRows = Integer.parseInt(args[i]);
        try {
          Integer.parseInt(args[i + 1]);
          isInteger = true;
        } catch (NumberFormatException e) {
          isInteger = false;
        } catch (NullPointerException | ArrayIndexOutOfBoundsException p) {
          break;
        }
        if (isInteger) {
          numDraw = Integer.parseInt(args[i + 1]);
          break;
        }

      }
      game += args[i];
    }

    PyramidSolitaireModel solitaire;

    switch (game) {
      case "basic":
        solitaire = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.BASIC);
        break;
      case "relaxed":
        solitaire = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.RELAXED);
        break;
      case "tripeaks":
        solitaire = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.TRIPEAKS);
        break;
      default:
        System.out.println(String.format("Unknown game type %s. Please try again.", game));
        solitaire = null;
        break;
    }

    try {
      if (solitaire != null) {
        new PyramidSolitaireTextualController(new InputStreamReader(System.in), System.out)
                .playGame(solitaire, solitaire.getDeck(), true, numRows, numDraw);
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }

  }
}
