package cs3500.pyramidsolitaire.view;

import java.util.ArrayList;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;

/**
 * This class provides a main method that allows a person to play the game,
 * therefore seeing the textual view in action.
 */
public class PyramidTextualPlayer {

  /**
   * Main method to run prints of pyramid solitaire.
   * @param args the inputs to the program
   */
  public static void main(String[] args) {

    BasicPyramidSolitaire ex1 = new BasicPyramidSolitaire();
    ArrayList<Card> myDeck = (ArrayList<Card>) ex1.getDeck();
    //ex1.startGame(myDeck, false, 4, 2);
    ex1.startGame(myDeck, false, 7, 2);
    //ex1.remove(6, 2, 6, 6);
    PyramidSolitaireTextualView t1 = new PyramidSolitaireTextualView(ex1);


    System.out.println(t1.toString());

  }
}
