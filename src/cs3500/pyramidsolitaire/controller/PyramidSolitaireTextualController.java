package cs3500.pyramidsolitaire.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

/**
 * Controller for a game of Pyramid Solitaire. Runs the game by interfacing with the model and
 * view to transfer inputs and outputs.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {
  private Readable in;
  private Appendable out;

  /**
   * The default constructor for a PyramidSolitaireTextualController.
   * @param rd The readable object from which data will be read and given to the model
   * @param ap The appendable object to which data will be added to be read by the view.
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException(
              "PyramidSolitaireTextualController was given a null argument");
    }
    this.in = rd;
    this.out = ap;
  }

  /**
   * The simplified constructor for a PyramidSolitaireTextualController. Uses the console input
   * and output for taking in moves and receiving appending the game information.
   */
  public PyramidSolitaireTextualController() {
    this.in = new InputStreamReader(System.in);
    this.out = System.out;
  }

  @Override
  public <K> void playGame(
          PyramidSolitaireModel<K> model, List<K> deck,
          boolean shuffle, int numRows, int numDraw) {
    if (model == null) {
      throw new IllegalArgumentException("Given model must be non-null");
    }
    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("The game could not be started");
    }

    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model, this.out);
    try {
      view.render();
      this.out.append("\n");
      Integer score = model.getScore();
      this.out.append("Score: " + score.toString() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not append to output.");
    }
    runGame(model, view);

  }

  /**
   * Handles playing a game of Pyramid Solitaire until the user quits, wins, or loses.
   * @param model The model on which the game is played.
   * @param view The view of the game which displays the game in the model's current state.
   * @param <K> The type of cards being used to play Pyramid Solitaire.
   */
  private <K> void runGame(PyramidSolitaireModel<K> model, PyramidSolitaireTextualView view) {
    Scanner s = new Scanner(this.in);
    Boolean gameQuit = false;
    while (!model.isGameOver() && !gameQuit) {
      String command;
      try {
        command = s.next();
        switch (command) {
          case "q":
          case "Q":
            gameQuit = true;
            break;
          case "rm1":
            ArrayList<String> commands = getCommand(2, s);
            gameQuit = (commands.contains("q") || commands.contains("Q"));
            if (gameQuit) {
              break;
            }
            try {
              model.remove(Integer.parseInt(commands.get(0)), Integer.parseInt(commands.get(1)));
            } catch (IllegalArgumentException e) {
              transmit("Single remove was invalid.");
            }
            break;
          case "rm2":
            commands = getCommand(4, s);
            gameQuit = (commands.contains("q") || commands.contains("Q"));
            if (gameQuit) {
              break;
            }
            try {
              model.remove(Integer.parseInt(commands.get(0)), Integer.parseInt(commands.get(1)),
                      Integer.parseInt(commands.get(2)), Integer.parseInt(commands.get(3)));
            } catch (IllegalArgumentException e) {
              transmit("Two card remove was invalid.");
            }
            break;
          case "rmwd":
            commands = getCommand(3, s);
            gameQuit = (commands.contains("q") || commands.contains("Q"));
            if (gameQuit) {
              break;
            }
            try {
              model.removeUsingDraw(Integer.parseInt(commands.get(0)),
                      Integer.parseInt(commands.get(1)),
                      Integer.parseInt(commands.get(2)));
            } catch (IllegalArgumentException e) {
              transmit("Removing with draw failed.");
            }

            break;
          case "dd":
            commands = getCommand(1, s);
            gameQuit = (commands.contains("q") || commands.contains("Q"));
            if (gameQuit) {
              break;
            }
            try {
              model.discardDraw(Integer.parseInt(commands.get(0)));
            } catch (IllegalArgumentException e) {
              transmit("Discarding from draw pile failed.");
            }
            break;
          default:
            transmit("Given command was invalid.");
        }
        if (model.isGameOver()) {
          try {
            view.render();
          } catch (IOException e) {
            throw new IllegalStateException("Failed append.");
          }
        } else {
          if (gameQuit) {
            try {
              this.out.append("Game quit!\nState of game when quit:\n");
            } catch (IOException e) {
              throw new IllegalStateException("Failed append.");
            }
          }
          try {
            view.render();
            this.out.append("\n");
            Integer score = model.getScore();
            this.out.append("Score: " + score.toString() + "\n");
          } catch (IOException e) {
            throw new IllegalStateException("Failed append.");
          }
        }
      } catch (java.util.NoSuchElementException e) {
        return;
      }


    }
  }

  /**
   * Sends an error message to the user, or throws an IllegalStateException if appending fails.
   * @param s String detailing why move was invalid.
   */
  private void transmit(String s) {
    try {
      out.append("Invalid move. Play again. " + s + "\n");
    } catch (IOException f) {
      throw new IllegalStateException("Failed append.");
    }
  }

  /**
   * Waits for the user to enter the correct number of inputs for the command given.
   * @param numsNeeded The amount of numbers the user needs to enter for the specific command
   *                   they have chosen.
   * @param s The Scanner object to read inputs from the user.
   * @return The commands given by the user.
   */
  private ArrayList<String> getCommand(int numsNeeded, Scanner s) {
    int numsLeft = numsNeeded;
    ArrayList<String> commands = new ArrayList<String>();
    while (numsLeft != 0) {
      if (s.hasNextInt()) {
        Integer n = s.nextInt() - 1;
        commands.add(n.toString());
        numsLeft--;
      } else if (s.hasNext()) {
        String next = s.next();
        if (next.equals("Q") || next.equals("q")) {
          commands.add("Q");
          break;
        }
      }
    }
    return commands;
  }

}
