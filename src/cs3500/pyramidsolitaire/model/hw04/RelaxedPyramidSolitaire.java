package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import java.util.Random;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;

/**
 * Model for a game of Pyramid Solitaire using SolitaireCards. This model uses a single deck to
 * play, allows for the deck to be used only once, and keeps score based on the value of cards
 * remaining in the pyramid. This model differs from the Basic version in that a card that is
 * covered by only one other card can be removed with the one card covering it, assuming it is an
 * otherwise valid move.
 */
public class RelaxedPyramidSolitaire extends BasicPyramidSolitaire {
  /**
   * The default constructor for a game of relaxed pyramid solitaire.
   */
  public RelaxedPyramidSolitaire() {
    /*
    This constructor does not assign any values as the values are given when the
    user calls the startGame method. It is here only to allow creation of the
    RelaxedPyramidSolitaire class.
     */
  }

  /**
   * A constructor where a Random object can be given to the game to test random events.
   *
   * @param rand The random object for generation pseudorandom numbers.
   */
  public RelaxedPyramidSolitaire(Random rand) {
    this.randGenerator = rand;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    checkValidCard(row1, card1);
    checkValidCard(row2, card2);
    if (this.gamePyramid.get(row1).get(card1).getValue()
            + this.gamePyramid.get(row2).get(card2).getValue() != 13) {
      throw new IllegalArgumentException("Given cards do not combine for legal move.");
    }
    ArrayList<SolitaireCard> playableCards = this.getPlayableCards();
    if (!relaxedMove(row1, card1, row2, card2) && (
            !playableCards.contains(this.gamePyramid.get(row1).get(card1))
            || !playableCards.contains(this.gamePyramid.get(row2).get(card2)))) {
      throw new IllegalArgumentException("Given cards do not combine for legal move.");
    }

    this.gamePyramid.get(row1).set(card1, null);
    this.gamePyramid.get(row2).set(card2, null);
  }


  /**
   * Determines if two given cards combine for a "relaxed move" for a game of
   * RelaxedPyramidSolitaire. Method does not ensure that cards add to 13 for move validity.
   *
   * @param row1  row value of the first card.
   * @param card1 placement in row of first card.
   * @param row2  row value of second card.
   * @param card2 placement in row of second card.
   * @return True if the cards combine for a relaxed move, false otherwise.
   */
  private boolean relaxedMove(int row1, int card1, int row2, int card2) {
    if (getCardAt(row1, card1) == null || getCardAt(row2, card2) == null) {
      return false;
    }

    ArrayList<SolitaireCard> playableCards = this.getPlayableCards();

    if (cardBlocking(row1, card1, row2, card2)) {
      if (getCardAt(row2, card1) != null && getCardAt(row2, card1 + 1) != null) {
        return false;
      }
      return getCardAt(row1, card1).getValue() + getCardAt(row2, card2).getValue() == 13;
    } else if (cardBlocking(row2, card2, row1, card1)) {
      if (getCardAt(row1, card2) != null && getCardAt(row1, card2 + 1) != null) {
        return false;
      }
      return getCardAt(row1, card1).getValue() + getCardAt(row2, card2).getValue() == 13;
    } else {
      return false;
    }


  }

  /**
   * Determines if the first card is being blocked by the second card. All inputs are zero-indexed.
   *
   * @param row1  row value of the first card.
   * @param card1 placement in row of first card.
   * @param row2  row value of second card.
   * @param card2 placement in row of second card.
   * @return True if card2 blocks card1, false otherwise.
   */
  private boolean cardBlocking(int row1, int card1, int row2, int card2) {
    return row1 + 1 == row2 && (card1 == card2 || card1 + 1 == card2);
  }

  protected boolean checkPlayableCards() {
    Boolean playableLeft = false;
    ArrayList<SolitaireCard> playableCards = this.getPlayableCards();
    for (int i = 0; i < playableCards.size(); i++) {
      if (playableCards.get(i).getValue() == 13) {
        playableLeft = true;
      }
      for (int j = i + 1; j < playableCards.size(); j++) {
        if (playableCards.get(i).getValue() + playableCards.get(j).getValue() == 13
                || playableCards.get(j).getValue() == 13) {
          playableLeft = true;
        }
      }
    }

    for (int i = 0; i < this.gamePyramid.size() - 1; i++) {
      for (int j = 0; j < this.gamePyramid.get(i).size(); j++) {
        if (relaxedMove(i, j, i + 1, j) || relaxedMove(i, j, i + 1, j + 1)) {
          playableLeft = true;
        }
      }
    }

    return playableLeft;
  }
}
