package cs3500.pyramidsolitaire.view;

import java.io.IOException;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Models a textual representation of the pyramid solitaire game.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {
  private final PyramidSolitaireModel<?> model;
  private Appendable ap;

  /**
   * Constructs an instance of the textual view.
   * @param model the model to view
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
    this.ap = null;
  }

  /**
   * Constructs an instance of the textual view.
   * @param model the model to view
   * @param ap the appendable to render to
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }


  @Override
  public String toString() {
    try {
      model.isGameOver();
    } catch (IllegalStateException e) {
      return "";
    }

    if (pyramidEmpty()) {
      return "You win!";
    }

    if (noMovesRemain()) {
      return "Game over. Score: " + model.getScore();
    }

    String result = "";

    int existingIndex = 1;
    for (int d = 0; d < model.getNumRows(); d++) {
      if (d == model.getNumRows() - 1) {
        for (int k = 0; k < model.getRowWidth(d); k++) {
          if (model.getCardAt(d, k) != null) {
            existingIndex = k;
          }
        }
      }
    }


    for (int i = 0; i < model.getNumRows(); i++) {
      for (int h = model.getNumRows() - i; h > 1; h--) {
        result += "  ";
      }
      for (int j = 0; j < model.getNumRows(); j++) {
        Card playingCard = (Card) model.getCardAt(i, j);
        if (playingCard == null && j < model.getRowWidth(i)) {
          if (j <= existingIndex) {
            result += "    ";
          }

        }
        if (playingCard != null) {

          if (j != existingIndex || i != model.getNumRows() - 1) {
            if (playingCard.getValue() == 10) {
              result += playingCard.toString();
              if (j != model.getRowWidth(i) - 1) {
                result += " ";
              }

            } else {
              result += playingCard.toString();
              if (j != model.getRowWidth(i) - 1) {
                result += "  ";
              }


            }
          }


          else {
            result += playingCard.toString();
          }

        }

        if (j == model.getRowWidth(i) - 1) {
          result += "\n";
        }

      }
    }

    if (model.getNumDraw() != 0) {
      result += "Draw: ";
      int numNull = 0;
      for (int h = 0; h < model.getNumDraw(); h++) {
        if (model.getDrawCards().get(h) == null) {
          numNull++;
        }
      }
      if (numNull != model.getNumDraw()) {

        for (int k = 0; k < model.getNumDraw(); k++) {
          if (k != model.getNumDraw() - 1) {
            if (model.getDrawCards().get(k) != null) {
              result += model.getDrawCards().get(k).toString() + ", ";
            } else {
              result += "null";
            }
          } else {
            if (model.getDrawCards().get(k) == null) {
              result += "null";
            } else {
              result += model.getDrawCards().get(k).toString();
            }
          }
        }
      }

    }


    return result;
  }

  private boolean noMovesRemain() {
    return (model.isGameOver());
  }

  private boolean pyramidEmpty() {
    return (model.getScore() == 0);
  }

  @Override
  public void render() throws IOException {
    //if ap is null, throw io exception
    if (ap == null) {
      throw new IOException("Appendable is null");
    }
    //otherwise call the to string and give a new line
    String myString = toString();
    ap.append(myString).append("\n");

    //if the game isn't over yet, render the score
    if (!model.isGameOver()) {
      ap.append("Score: " + model.getScore());
      ap.append("\n");
    }
  }
}
