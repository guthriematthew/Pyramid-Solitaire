package cs3500.pyramidsolitaire.model.hw02;

/**
 * Represents a card in a game of Pyramid Solitaire.
 */
public class SolitaireCard implements Card {
  private final CardFace face;
  private final CardSuit suit;

  /**
   * Default constructor for a SolitaireCard.
   *
   * @param f represents the face/value of the card
   * @param s represents the suit of the card
   */
  public SolitaireCard(CardFace f, CardSuit s) {
    this.face = f;
    this.suit = s;
  }

  @Override
  public String toString() {
    String ans = "";

    switch (face) {
      case Ace:
        ans = ans + "A";
        break;
      case Two:
        ans = ans + "2";
        break;
      case Three:
        ans = ans + "3";
        break;
      case Four:
        ans = ans + "4";
        break;
      case Five:
        ans = ans + "5";
        break;
      case Six:
        ans = ans + "6";
        break;
      case Seven:
        ans = ans + "7";
        break;
      case Eight:
        ans = ans + "8";
        break;
      case Nine:
        ans = ans + "9";
        break;
      case Ten:
        ans = ans + "10";
        break;
      case Jack:
        ans = ans + "J";
        break;
      case Queen:
        ans = ans + "Q";
        break;
      default:
        ans = ans + "K";
        break;
    }

    switch (suit) {
      case Diamond:
        ans = ans + "♦";
        break;
      case Spade:
        ans = ans + "♠";
        break;
      case Heart:
        ans = ans + "♥";
        break;
      default:
        ans = ans + "♣";
        break;
    }

    return ans;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SolitaireCard)) {
      return false;
    }
    SolitaireCard that = (SolitaireCard) o;

    return this.face.equals(that.face) && this.suit.equals(that.suit);
  }

  @Override
  public int hashCode() {
    return face.hashCode() * suit.hashCode() * 13;
  }


  /**
   * Determines the int value of this card and returns it. A is 1, K is 13.
   *
   * @return The value of the card.
   */
  public int getValue() {
    int cardValue = 0;
    switch (face) {
      case Ace:
        cardValue = 1;
        break;
      case Two:
        cardValue = 2;
        break;
      case Three:
        cardValue = 3;
        break;
      case Four:
        cardValue = 4;
        break;
      case Five:
        cardValue = 5;
        break;
      case Six:
        cardValue = 6;
        break;
      case Seven:
        cardValue = 7;
        break;
      case Eight:
        cardValue = 8;
        break;
      case Nine:
        cardValue = 9;
        break;
      case Ten:
        cardValue = 10;
        break;
      case Jack:
        cardValue = 11;
        break;
      case Queen:
        cardValue = 12;
        break;
      default:
        cardValue = 13;
        break;
    }
    return cardValue;
  }
}
