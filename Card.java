package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;

/**
 * Class that models a playing card.
 */
public class Card {

  private final String suit;
  private final int value;

  /**
   * Creates an instance of the card class.
   *
   * @param suit  The suit of the card
   * @param value the value of the card
   */
  public Card(String suit, int value) {
    this.suit = suit;
    this.value = value;
  }

  /**
   * Obtains the numeric value of the card.
   *
   * @return the value of the card
   */
  public int getValue() {
    return value;
  }

  /**
   * Obtains the suit of the card.
   * @return the suit of the card
   */
  public String getSuit() {
    return suit;
  }

  @Override
  public String toString() {
    String suitPic;
    String valuePic;
    switch (suit) {
      case "Clubs":
        suitPic = "♣";
        break;
      case "Hearts":
        suitPic = "♥";
        break;
      case "Diamonds":
        suitPic = "♦";
        break;
      case "Spades":
        suitPic = "♠";
        break;
      default:
        suitPic = "Error";
        break;
    }
    switch (value) {
      case 1:
        valuePic = "A";
        break;
      case 11:
        valuePic = "J";
        break;
      case 12:
        valuePic = "Q";
        break;
      case 13:
        valuePic = "K";
        break;
      default:
        valuePic = "H";
    }
    if (valuePic.equals("H")) {
      return value + suitPic;
    }
    return valuePic + suitPic;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Card)) {
      return false;
    }
    Card c = (Card) o;

    return (c.suit.equals(this.suit) && c.value == this.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, suit);
  }


}


