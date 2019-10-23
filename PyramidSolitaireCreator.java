package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Factory class to create a pyramid solitaire game.
 */
public class PyramidSolitaireCreator {

  /**
   * The type of pyramid solitaire game that can be played. Either basic, relaxed,
   * or tripeaks.
   */
  public enum GameType {
    BASIC, RELAXED, TRIPEAKS;
  }

  /**
   * Creates a pyramid solitaire game of the given type.
   * @param type the type of game to play, either basic, relaxed, or tripeaks
   * @return the given pyramid solitaire game
   */
  public static PyramidSolitaireModel<Card> create(GameType type) {
    if (type == GameType.BASIC) {
      return new BasicPyramidSolitaire();
    }

    if (type == GameType.RELAXED) {
      return new RelaxedPyramidSolitaire();
    }

    if (type == GameType.TRIPEAKS) {
      return new TripeaksPyramidSolitaire();
    }

    else {
      throw new IllegalArgumentException("Game type invalid");
    }
  }

}

