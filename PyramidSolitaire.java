package cs3500.pyramidsolitaire;

import java.util.Scanner;

import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;

public final class PyramidSolitaire {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String game = sc.next();
    if (sc.hasNextInt()) {
      int numRows = sc.nextInt();
      int numDraw = sc.nextInt();
    }

    if (game.equals("basic")) {
      //add to factory creator
    }

    else if (game.equals("relaxed")) {
      //add to factory creator
    }

    else if (game.equals("tripeaks")) {
      //add to factory creator
    }

    else {
      System.out.println("Unknown game type. Please try again.");
    }
  }
}
