package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;

/**
 * Model for a game of Pyramid Solitaire using SolitaireCards. This model uses two decks of cards to
 * play and arranges the game board into three Pyramids, which overlap for half of their height.
 * Score is kept as the sum of the value of all cards in the game board. Number of rows and draw
 * cards can be chosen, up the using the maximum number of cards in the game, 104. All rules of move
 * validity are equal to the Basic version.
 */
public class TriPeaksPyramidSolitaire extends BasicPyramidSolitaire {

  public TriPeaksPyramidSolitaire() {
    super();
  }

  public TriPeaksPyramidSolitaire(Random rand) {
    super(rand);
  }

  /**
   * Creates a deck of SolitaireCards that contains each card twice, for a total of 104 cards in a
   * deck.
   *
   * @return Two standard decks of SolitaireCards, combined into one list.
   */
  public List<SolitaireCard> getDeck() {
    List<SolitaireCard> doubleDeck = super.getDeck();
    doubleDeck.addAll(super.getDeck());
    return doubleDeck;
  }

  /**
   * Determines if the there are enough cards to play the game based on Pyramid size and number of
   * draw cards.
   *
   * @param numRows Number of rows for the Pyramid.
   * @param numDraw Number of draw cards.
   * @return If there are enough cards to play the game.
   */
  protected boolean enoughCards(int numRows, int numDraw) {
    int halfHeight = Math.round(numRows / 2);
    int pyramidSize = 0;
    int lastLength = halfHeight * 3;
    for (int i = 0; i < halfHeight; i++) {
      pyramidSize = pyramidSize + (i + 1) * 3;
    }
    for (int i = halfHeight; i < numRows; i++) {
      pyramidSize = pyramidSize + lastLength + 1;
      lastLength++;
    }

    return pyramidSize + numDraw <= 104;
  }

  /**
   * Checks if the given deck of cards is set-wise equal to a known good deck of cards.
   *
   * @param checkDeck The deck of cards to be checked for validity
   * @return True if deck is valid, false otherwise.
   */
  protected boolean validDeck(List<SolitaireCard> checkDeck) {
    BasicPyramidSolitaire bps = new BasicPyramidSolitaire();
    List<SolitaireCard> allCards = bps.getDeck();
    Boolean twoOfEach = true;
    for (SolitaireCard c : allCards) {
      int amountSoFar = 0;
      for (SolitaireCard n : checkDeck) {
        if (c.equals(n)) {
          amountSoFar++;
        }
      }
      if (amountSoFar != 2) {
        twoOfEach = false;
      }
    }
    return twoOfEach && checkDeck.size() == 104;
  }

  /**
   * Creates a TriPeak pyramid for a game of PyramidSolitaire.
   *
   * @param startingDeck The deck used to create the Pyramid.
   * @param numRows      The number of rows in the Pyramid.
   * @param numDraw      The number of draw cards.
   * @return The TriPeak pyramid as a List of Lists of SolitaireCards
   */
  protected List<List<SolitaireCard>> createPyramid(ArrayList<SolitaireCard> startingDeck,
                                                    int numRows, int numDraw) {
    int halfHeight = Math.round(numRows / 2);

    //pyramid creation
    ArrayList<List<SolitaireCard>> gameBoard = new ArrayList<List<SolitaireCard>>();
    int lastLength = halfHeight * 3;
    for (int i = 0; i < numRows; i++) {
      gameBoard.add(new ArrayList<SolitaireCard>());
      if (i < halfHeight) {
        int peaksPassed = 0;
        for (int n = 0; n < 3; n++) {
          for (int j = 0; j <= i; j++) {
            gameBoard.get(i).add(startingDeck.remove(0));
          }
          if (peaksPassed != 2) {
            for (int m = 0; m < halfHeight - (i + 1); m++) {
              gameBoard.get(i).add(null);
            }
          }
          peaksPassed++;
        }
      } else {
        for (int j = 0; j < lastLength + 1; j++) {
          gameBoard.get(i).add(startingDeck.remove(0));
        }
        lastLength++;
      }
    }
    return gameBoard;
  }
}
