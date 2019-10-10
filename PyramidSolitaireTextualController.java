package cs3500.pyramidsolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

/**
 * This class models a controller for textual interaction of the game.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {

  private Readable in;
  private Appendable out;
  private Scanner sc;
  private PyramidSolitaireTextualView text;
  private Boolean hasQuit;

  /**
   * Creates an instance of the Pyramid Solitaire Textual Controller.
   *
   * @param rd the readable
   * @param ap the appendable
   * @throws IllegalArgumentException if the readable or appendable are null
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap)
          throws IllegalArgumentException {
    //if either rd or ap are null, immediately get out of there
    if (rd == null || ap == null) {
      throw new IllegalArgumentException();
    }
    this.in = rd;
    this.out = ap;
    this.sc = new Scanner(rd);
    this.hasQuit = false;
  }


  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle,
                           int numRows, int numDraw) {
    //first check if model itself is null, if it is, throw exception
    if (model == null) {
      throw new IllegalArgumentException("model is null");
    }

    //try to start the game
    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      //if an illegal argument exception is caught, throw illegal state
      throw new IllegalStateException();
    }

    text = new PyramidSolitaireTextualView(model, out);
    List<Integer> numbers;

    //determine if the appendables are null
    if (in == null || out == null) {
      throw new IllegalStateException("Readable could not be read from or appendable "
              + "could not be appended");
    }

    //render the starting configuration of the game
    renderGame(model);

    //keep going until player has quit, there are no more inputs, or the game is won/lost
    while (!hasQuit && sc.hasNext() && !model.isGameOver()) {
      try {
        sc.hasNext();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("Ran out of inputs");
      }
      //find the next move
      String nextMove = determineMove(model);
      //as long as that next move isn't null
      if (nextMove != null) {
        //determine which it is
        if (nextMove.equals("rm1")) {
          //for rm1, get two cards
          numbers = getNumbersTwoCards();

          if (numbers != null) {
            //make the move and render it, catching potential exceptions
            try {
              model.remove(numbers.get(0) - 1, numbers.get(1) - 1);
              renderGame(model);
            } catch (IllegalArgumentException e) {
              try {
                out.append("Invalid move. Play again. Card position invalid.\n");
              } catch (IOException g) {
                throw new IllegalStateException();
              }
            }

          }

        } else if (nextMove.equals("rm2")) {
          //if the move is rm2, get four cards
          numbers = getNumbersFourCards();

          if (numbers != null) {
            //make the move and render it, catching potential exceptions
            try {
              model.remove(numbers.get(0) - 1, numbers.get(1) - 1,
                      numbers.get(2) - 1, numbers.get(3) - 1);
              renderGame(model);
            } catch (IllegalArgumentException e) {
              try {
                out.append("Invalid move. Play again. Card position invalid.\n");
              } catch (IOException g) {
                throw new IllegalStateException();
              }
            }
          }

        } else if (nextMove.equals("rmwd")) {
          //if rmwd, get three cards
          numbers = getNumbersWithDraw();

          if (numbers != null) {
            //make the move and render it, catching any potential exceptions
            try {
              model.removeUsingDraw(numbers.get(0) - 1, numbers.get(1) - 1, numbers.get(2) - 1);
              renderGame(model);
            } catch (IllegalArgumentException e) {
              try {
                out.append("Invalid move. Play again. Card position invalid.\n");
              } catch (IOException g) {
                throw new IllegalStateException();
              }
            }
          }
        } else if (nextMove.equals("dd")) {
          //if dd, get one card
          numbers = getNumbersDiscard();

          if (numbers != null) {

            try {
              //make the move and render it, catching any potential exceptions
              model.discardDraw(numbers.get(0) - 1);
              renderGame(model);
            } catch (IllegalArgumentException e) {
              try {
                out.append("Invalid move. Play again. Card position invalid.\n");
              } catch (IOException g) {
                throw new IllegalStateException();
              }
            }

          }

        }
      }

      //otherwise invalid
      if (nextMove != null) {
        if (nextMove.equals("Invalid command")) {
          try {
            out.append("Invalid command. Please try again.\n");
          } catch (IOException e) {
            throw new IllegalStateException();
          }

        }

      }
    }


  }

  /**
   * Renders the game.
   *
   * @param model the model of pyramid solitaire
   * @param <K>   the generalization for card
   */
  private <K> void renderGame(PyramidSolitaireModel<K> model) {
    try {
      text.render();
    } catch (IOException e) {
      throw new IllegalStateException("Bad input");
    }
  }

  /**
   * Gets the cards for the dd command.
   * @param <K> generalization for card
   * @return null if there aren't cards to complete the move, the list of cards otherwise
   */
  private <K> List<Integer> getNumbersDiscard() {
    List<Integer> numbers = new ArrayList<>();

    Integer firstVal = getNextVal();

    if (firstVal == null) {
      return null;
    }

    numbers.add(firstVal);

    return numbers;
  }

  /**
   * Gets the cards for the rmwd command.
   * @param <K> generalization for card
   * @return null if there aren't cards to complete the move, the list of cards otherwise
   */
  private <K> List<Integer> getNumbersWithDraw() {
    List<Integer> numbers = new ArrayList<>();

    Integer firstVal = getNextVal();
    Integer secondVal = getNextVal();
    Integer thirdVal = getNextVal();

    if (firstVal == null || secondVal == null || thirdVal == null) {
      return null;
    }

    numbers.add(firstVal);
    numbers.add(secondVal);
    numbers.add(thirdVal);

    return numbers;
  }

  /**
   * Gets the cards for the rm2 command.
   * @param <K> generalization for card
   * @return null if there aren't cards to complete the move, the list of cards otherwise
   */
  private <K> List<Integer> getNumbersFourCards() {
    List<Integer> numbers = new ArrayList<>();

    Integer firstVal = getNextVal();
    Integer secondVal = getNextVal();
    Integer thirdVal = getNextVal();
    Integer fourthVal = getNextVal();

    if (firstVal == null || secondVal == null || thirdVal == null || fourthVal == null) {
      return null;
    }

    numbers.add(firstVal);
    numbers.add(secondVal);
    numbers.add(thirdVal);
    numbers.add(fourthVal);

    return numbers;
  }

  /**
   * Gets the cards for the rm1 command.
   * @param <K> generalization for card
   * @return null if there aren't cards to complete the move, the list of cards otherwise
   */
  private <K> List<Integer> getNumbersTwoCards() {
    List<Integer> numbers = new ArrayList<>();

    Integer firstVal = getNextVal();
    Integer secondVal = getNextVal();

    if (firstVal == null || secondVal == null) {
      return null;
    }

    numbers.add(firstVal);
    numbers.add(secondVal);

    return numbers;
  }

  /**
   * Gets the value of the next card.
   * @return null if there is no next card or q/Q has been inputted, the next number otherwise
   */
  private Integer getNextVal() {
    //first assume the next value and next num are bogus
    String nextVal = "NaN";
    int nextNum = 0;

    //as long as the next value is bogus, keep retrying
    while (nextVal.equals("NaN")) {

      //if there is no next element, return null
      try {
        nextVal = sc.next();
      } catch (NoSuchElementException e) {
        return null;
      }

      //if it's q or Q, quit and return null
      if (nextVal.equals("q") || nextVal.equals("Q")) {
        quit();
        return null;
      }


      //try to get a number, if the next card is a number set it, otherwise invalid move
      try {
        nextNum = Integer.parseInt(nextVal);
        nextVal = "Good num";
      } catch (NumberFormatException e) {
        nextVal = "NaN";
        try {
          out.append("Invalid move. Play again. Input not a number.\n");
        } catch (IOException i) {
          throw new IllegalStateException("Bad input");
        }

      }


    }

    //return the numeric value of the card
    return nextNum;


  }

  /**
   * Renders the quit output for the game.
   */
  private void quit() {
    try {
      out.append("Game quit!\n").append("State of game when quit:\n");
      text.render();
      hasQuit = true;
    } catch (IOException e) {
      throw new IllegalStateException("Bad appendable");
    }
  }


  /**
   * Determines the next command.
   * @param model the model of the game
   * @param <K> the card generalization
   * @return null if there is no next element or q/Q, next command otherwise
   */
  private <K> String determineMove(PyramidSolitaireModel<K> model) {
    //if there is no next element, there is no next move
    if (!sc.hasNext()) {
      return null;
    }
    String move = sc.next();
    if (move.equals("rm1") || move.equals("rm2") || move.equals("rmwd") || move.equals("dd")) {
      return move;
    }

    if (move.equals("q") || move.equals("Q")) {
      quit();
      return null;
    } else {
      return "Invalid command";
    }
  }

}
