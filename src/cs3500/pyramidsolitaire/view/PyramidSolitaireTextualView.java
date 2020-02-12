package cs3500.pyramidsolitaire.view;

import java.io.IOException;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Represents a text based view of a game of pyramid solitaire.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {
  private final PyramidSolitaireModel<?> model;
  private Appendable out;

  /**
   * Constructor for a PyramidSolitaireModel where rendered text will be appended to System.out.
   * @param model The model for which the view will be constructed.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
    this.out = System.out;
  }

  /**
   * Default constructor for a PyramidSolitaireModel.
   * @param model The model for which the view will be constructed.
   * @param ap The Appendable object to which rendered text will be appended to.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable ap) {
    this.model = model;
    this.out = ap;
  }

  @Override
  public String toString() {
    String gameString = "";

    // exception/error checking
    if (this.model.getNumRows() == -1) {
      return gameString;
    } else if (this.model.getCardAt(0, 0) == null) {
      return "You win!";
    } else if (this.model.isGameOver()) {
      return "Game over. Score: " + this.model.getScore();
    }

    int rows = this.model.getNumRows();

    //pyramid drawing
    for (int i = 0; i < rows; i++) {
      int width = this.model.getRowWidth(i);
      //padding at front of line
      for (int k = 0; k < (this.model.getNumRows() - 1 - i) * 2; k++) {
        gameString = gameString + " ";
      }
      for (int j = 0; j < width; j++) {
        //if card is null
        if (this.model.getCardAt(i, j) == null) {
          for (int n = j; n < width; n++) {
            if (this.model.getCardAt(i, n) != null) {
              gameString = gameString + "    ";
              break;
            }
          }
        }
        //if card in non-null
        else {
          gameString = gameString + this.model.getCardAt(i, j).toString();
          if (j != width - 1) {
            for (int n = j + 1; n < width; n++) {
              if (this.model.getCardAt(i, n) != null) {
                gameString = gameString + " ";
                if (this.model.getCardAt(i, j).toString().length() == 2) {
                  gameString = gameString + " ";
                }
                break;
              }
            }
          }
        }

      }
      gameString = gameString + "\n";
    }

    //draw pile drawing
    List<?> draw = this.model.getDrawCards();
    gameString = gameString + "Draw:";
    for (int i = 0; i < draw.size(); i++) {
      if (draw.get(i) != null) {
        gameString = gameString + " ";
        String currentCard = draw.get(i).toString();
        gameString = gameString + currentCard;
        if (i != draw.size() - 1) {
          gameString = gameString + ",";
        }
      }
    }

    return gameString;
  }

  @Override
  public void render() throws IOException {
    this.out.append(this.toString());
  }
}
