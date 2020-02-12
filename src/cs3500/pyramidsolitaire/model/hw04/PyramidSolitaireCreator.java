package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;

/**
 * Class to create all different possible games of PyramidSolitaire.
 */
public class PyramidSolitaireCreator {

  /**
   * Constructor for a PyramidSolitaireCreator.
   */
  public PyramidSolitaireCreator() {
    //No fields to assign values to.
  }

  /**
   * Represents the possible types of PyramidSolitaire games that can be played.
   */
  public enum GameType {
    BASIC, RELAXED, TRIPEAKS;
  }

  /**
   * Creates a game of PyramidSolitaire, based on the given type.
   * @param type The type of PyramidSolitaire to be played
   * @return The model of a PyramidSolitaire game
   */
  public static PyramidSolitaireModel<SolitaireCard> create(GameType type) {
    switch (type) {
      case RELAXED:
        return new RelaxedPyramidSolitaire();
      case TRIPEAKS:
        return new TriPeaksPyramidSolitaire();
      default:
        return new BasicPyramidSolitaire();
    }
  }
}
