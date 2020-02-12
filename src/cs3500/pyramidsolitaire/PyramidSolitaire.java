package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;

/**
 * A class containing a main method that allows for the playing of a game of PyramidSolitaire,
 * regardless of game type.
 */
public final class PyramidSolitaire {

  /**
   * Starts a game of PyramidSolitaire that is playable in the console.
   * @param args The initial commands to begin the game, including the game type, number of
   *             rows, and number of draw cards.
   */
  public static void main(String[] args) {
    String command = args[0];
    command.toLowerCase();
    PyramidSolitaireModel<SolitaireCard> model;
    switch (command) {
      case "basic":
        PyramidSolitaireCreator creator = new PyramidSolitaireCreator();
        model = creator.create(PyramidSolitaireCreator.GameType.BASIC);
        break;
      case "relaxed":
        creator = new PyramidSolitaireCreator();
        model = creator.create(PyramidSolitaireCreator.GameType.RELAXED);
        break;
      case "tripeaks":
        creator = new PyramidSolitaireCreator();
        model = creator.create(PyramidSolitaireCreator.GameType.TRIPEAKS);
        break;
      default:
        throw new IllegalArgumentException();
    }

    int rows = 7;
    int draw = 3;

    if (args.length == 3) {
      rows = Integer.parseInt(args[1]);
      draw = Integer.parseInt(args[2]);
    }

    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController();
    controller.playGame(model, model.getDeck(), true, rows, draw);
  }
}