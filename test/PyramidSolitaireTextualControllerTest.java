import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;

import static org.junit.Assert.assertEquals;

/**
 * Class to hold tests for methods in the PyramidSolitaireTextualController class.
 */
public class PyramidSolitaireTextualControllerTest {
  ExampleDecks decks = new ExampleDecks();
  ExampleBasicPyramidSolitaire bps = new ExampleBasicPyramidSolitaire();
  CardExamples cards = new CardExamples();
  PyramidSolitaireTextualController controller1;

  @Before
  public void initData() {
    controller1 = new PyramidSolitaireTextualController(new StringReader(""), new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void runGameNullReadableConstructor() {
    controller1 = new PyramidSolitaireTextualController(null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void runGameNullAppendableConstructor() {
    controller1 = new PyramidSolitaireTextualController(new StringReader(""), null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void runGameNullModel() {
    controller1.playGame(null, decks.deck1, false, 2, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void runGameGameStartFailRows() {
    controller1.playGame(bps.bps1, decks.deck1, false, 10, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void runGameGameStartFailNumDraw() {
    controller1.playGame(bps.bps1, decks.deck1, false, 7, -1);
  }

  @Test(expected = IllegalStateException.class)
  public void runGameGameStartFailBadDeck() {
    controller1.playGame(bps.bps1, new ArrayList<SolitaireCard>(), false, 7, 1);
  }

  @Test
  public void runGameQuitGameUpperQ() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1 6 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("Q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));


  }

  @Test
  public void runGameQuitGameLowerQ() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1 6 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));


  }

  @Test
  public void runGameQuitDuringCommand() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1 6 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("rm1 7 q 5\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));


  }

  @Test
  public void runGameWaitForCommand() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1\n"),
            Interaction.inputs("6\n"),
            Interaction.inputs("3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));


  }

  @Test
  public void runGameQuitWhilePlayingBigQ() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  10♣ 10♥\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                    + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                    + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1\n"),
            Interaction.inputs("6\n"),
            Interaction.inputs("Q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"));


  }

  @Test
  public void runGameQuitWhilePlayingSmallQ() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  10♣ 10♥\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                    + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                    + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1\n"),
            Interaction.inputs("q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"));


  }

  @Test
  public void runGameWaitForCommandBadDrawCommands() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  10♣ 10♥\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                    + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                    + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd\n"),
            Interaction.inputs("foo\n"),
            Interaction.inputs("1\n"),
            Interaction.inputs("bar\n"),
            Interaction.inputs("6\n"),
            Interaction.inputs("baz\n"),
            Interaction.inputs("3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));


  }

  @Test
  public void runGameWaitForCommandRemove1() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck,
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1\n"),
            Interaction.inputs("6\n"),
            Interaction.inputs("3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("rm1\n"),
            Interaction.inputs("7\n"),
            Interaction.inputs("5\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦             A♥\n"
                            + "Draw: 5♥\nScore: 143"),
            Interaction.inputs("q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦             A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 143"));


  }

  @Test
  public void runGameWaitForCommandRemove2() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck,
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2\n"),
            Interaction.inputs("7\n"),
            Interaction.inputs("4\n"),
            Interaction.inputs("7\n"),
            Interaction.inputs("7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1\n"),
            Interaction.inputs("6\n"),
            Interaction.inputs("3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));


  }

  @Test
  public void runGameQuitWhileWaitForCommand() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1\n"),
            Interaction.inputs("6\n"),
            Interaction.inputs("3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("rm1 7\n"),
            Interaction.inputs("q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));


  }

  @Test
  public void runGameBadCommand() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("foo 1 7 3 rmwd 1 7 3\n\n"),
            Interaction.prints(
                    "Invalid move. Play again. Given command was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.prints(
                    "Invalid move. Play again. Given command was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.prints(
                    "Invalid move. Play again. Given command was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.prints(
                    "Invalid move. Play again. Given command was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1 6 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.inputs("q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));


  }

  @Test
  public void runGameInvalidSingleRemove() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 6 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Single remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 7 4\n"),
            Interaction.prints(
                    "Invalid move. Play again. Single remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 7 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Single remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 0 4\n"),
            Interaction.prints(
                    "Invalid move. Play again. Single remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 10 4\n"),
            Interaction.prints(
                    "Invalid move. Play again. Single remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 7 -1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Single remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 7 24\n"),
            Interaction.prints(
                    "Invalid move. Play again. Single remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 7 5\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣          A♥\n"
                            + "Draw: 4♥\nScore: 157"));


  }

  @Test
  public void runGameInvalidDoubleRemove() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 0 5 7 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 9 5 7 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 -1 7 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 8 7 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 5 0 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 5 8 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 5 7 0\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 5 7 8\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 5 7 0\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 5 7 0\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 4 7 0\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm2 7 0 7 4\n"),
            Interaction.prints(
                    "Invalid move. Play again. Two card remove was invalid.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"));


  }

  @Test
  public void runGameInvalidDrawRemove() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 0 7 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Removing with draw failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 3 7 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Removing with draw failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 0 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Removing with draw failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 9 1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Removing with draw failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 -1\n"),
            Interaction.prints(
                    "Invalid move. Play again. Removing with draw failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 9\n"),
            Interaction.prints(
                    "Invalid move. Play again. Removing with draw failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 2\n"),
            Interaction.prints(
                    "Invalid move. Play again. Removing with draw failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 1\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "    10♦ 9♣      K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1 3 2\n"),
            Interaction.prints(
                    "Invalid move. Play again. Removing with draw failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "    10♦ 9♣      K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"));


  }

  @Test
  public void runGameInvalidDiscard() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("dd 0\n"),
            Interaction.prints(
                    "Invalid move. Play again. Discarding from draw pile failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("dd 2\n"),
            Interaction.prints(
                    "Invalid move. Play again. Discarding from draw pile failed.\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"));
  }

  @Test
  public void runGameInvalidDiscardBadCommands() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  10♣ 10♥\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                    + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                    + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("dd\n"),
            Interaction.inputs("foo\n"),
            Interaction.inputs("bar\n"),
            Interaction.inputs("1\n"),
            Interaction.prints(
                            "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 8♠\nScore: 170"));
  }

  @Test
  public void runGameWin() throws IOException {
    testRun2(bps.bps1, decks.winDeck, 1, 1,
            Interaction.inputs("dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\nrmwd 1 1 1"),
            Interaction.prints("A♣\nDraw: 2♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♦\nScore: 1"),
            Interaction.prints("You win!"));
  }

  @Test
  public void runGameWinBogusInputs() throws IOException {
    testRun2(bps.bps1, decks.winDeck, 1, 1,
            Interaction.inputs("dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "foo\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\nrmwd 1 1 1"),
            Interaction.prints("A♣\nDraw: 2♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♣\nScore: 1\n"
            + "Invalid move. Play again. Given command was invalid."),
            Interaction.prints("A♣\nDraw: K♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♦\nScore: 1"),
            Interaction.prints("You win!"));
  }

  @Test
  public void runGameLose() throws IOException {
    testRun2(bps.bps1, decks.lastPlayableDeck, 1, 1,
            Interaction.inputs("dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\n"),
            Interaction.prints("A♣\nDraw: 2♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♠\nScore: 1"),
            Interaction.prints("Game over. Score: 1"));
  }

  @Test
  public void runGameMoveAfterQuitting() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("Q\n"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.inputs("rmwd 1 6 3\n"));
  }

  @Test
  public void runGameMoveAfterWinning() throws IOException {
    testRun2(bps.bps1, decks.winDeck, 1, 1,
            Interaction.inputs("dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\nrmwd 1 1 1\n"),
            Interaction.prints("A♣\nDraw: 2♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♦\nScore: 1"),
            Interaction.prints("You win!"),
            Interaction.inputs("rm2 4 6 2 3"));
  }

  @Test
  public void runGameMoveAfterLosing() throws IOException {
    testRun2(bps.bps1, decks.winDeck, 1, 1,
            Interaction.inputs("dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\n"),
            Interaction.prints("A♣\nDraw: 2♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♣\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♦\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 8♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♥\nScore: 1"),
            Interaction.prints("A♣\nDraw: A♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 5♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 9♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 2♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 3♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 4♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 6♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 7♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: 10♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: J♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: K♠\nScore: 1"),
            Interaction.prints("A♣\nDraw: Q♦\nScore: 1"),
            Interaction.prints("Game over. Score: 1"),
            Interaction.inputs("rm2 4 6 2 3"));
  }

  @Test
  public void runGameMultipleCommands() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),

            Interaction.inputs("rm2 7 4 7 7 rmwd 1 7 3 rmwd 1 6 3 Q\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\nScore: 156"),
            Interaction.prints(
                    "Game quit!\nState of game when quit:\n"
                            + "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦      6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 5♥\n"
                            + "Score: 156"));
  }

  @Test
  public void runGameDiscardEmptyDeck() throws IOException {
    testRun2(bps.bps1, decks.winNoDrawDeck2, 1, 1,
            Interaction.inputs("dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"
                    + "dd 1\ndd 1\ndd 1\ndd 1\ndd 1\n"),
            Interaction.prints("K♠\nDraw: 2♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 3♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 4♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 5♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 6♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 7♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 8♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 10♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 10♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: J♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: Q♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: K♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: A♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 2♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 3♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 4♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 5♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 6♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 7♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 8♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 9♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 10♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 9♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: J♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: K♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: A♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: 2♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: 4♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: 8♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: 5♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: 3♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: 6♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: 7♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: 8♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: 9♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: J♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: Q♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: K♥\nScore: 13"),
            Interaction.prints("K♠\nDraw: A♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: 5♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: 9♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: 2♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: 3♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: Q♦\nScore: 13"),
            Interaction.prints("K♠\nDraw: 6♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: 7♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: 10♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: J♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: Q♠\nScore: 13"),
            Interaction.prints("K♠\nDraw: A♣\nScore: 13"),
            Interaction.prints("K♠\nDraw: 4♠\nScore: 13"),
            Interaction.prints("K♠\nDraw:\nScore: 13"),
            Interaction.prints("Invalid move. Play again. Discarding from draw pile failed.\n"
                    + "K♠\nDraw:\nScore: 13"));
  }

  @Test
  public void runGameRemoveOneCorrect() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rm1 7 5"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣          A♥\n"
                            + "Draw: 4♥\nScore: 157"));
  }

  @Test
  public void runGameRemoveTwoCorrect() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"));
  }

  @Test
  public void runGameRemoveWithDrawCorrect() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("rmwd 1 7 3\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦         K♦  A♥\n"
                            + "Draw: 8♠\nScore: 161"));
  }

  @Test
  public void runGameDiscardCorrect() throws IOException {
    testRun(bps.bps1, decks.assignmentDeck, Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣  J♦  K♦  A♥  2♥\n"
                            + "Draw: 4♥\nScore: 183"),
            Interaction.inputs("rm2 7 4 7 7\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 4♥\nScore: 170"),
            Interaction.inputs("dd 1\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 8♠\nScore: 170"),
            Interaction.inputs("dd 1\n"),
            Interaction.prints(
                    "            A♣\n"
                            + "          2♣  3♣\n"
                            + "        4♣  5♣  6♣\n"
                            + "      7♣  8♣  10♣ 10♥\n"
                            + "    J♣  Q♣  K♣  A♦  2♦\n"
                            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
                            + "9♦  10♦ 9♣      K♦  A♥\n"
                            + "Draw: 5♥\nScore: 170"));
  }

  /**
   * Tests a simulation of a user playing Pyramid Solitaire based on given inputs and outputs.
   *
   * @param model        The model to have the game simulated on
   * @param deck         The deck of cards to be played with
   * @param numRows      The number of rows for the pyramid
   * @param numDraw      The number of draw cards for the game
   * @param interactions All input and output interactions
   */
  void testRun2(PyramidSolitaireModel model,
                List<SolitaireCard> deck, int numRows,
                int numDraw, Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    PyramidSolitaireController controller = new PyramidSolitaireTextualController(
            input, actualOutput);
    try {
      controller.playGame(model, deck, false, numRows, numDraw);
    } catch (IllegalStateException e) {
      assertEquals(expectedOutput.toString(), actualOutput.toString());
    }
    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  /**
   * Tests a simulation of a user playing Pyramid Solitaire based on given inputs and outputs.
   *
   * @param model        The model to have play simulated on
   * @param deck         The deck of cards to be played with
   * @param interactions All input and output interactions
   */
  void testRun(PyramidSolitaireModel model,
               List<SolitaireCard> deck, Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    PyramidSolitaireController controller = new PyramidSolitaireTextualController(
            input, actualOutput);
    try {
      controller.playGame(model, deck, false, 7, 1);
    } catch (IllegalStateException e) {
      assertEquals(expectedOutput.toString(), actualOutput.toString());
    }
    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  /**
   * An interaction with the user consists of some input to send the program and some output to
   * expect.  We represent it as an object that takes in two StringBuilders and produces the
   * intended effects on them.
   */
  private interface Interaction {
    static Interaction prints(String... lines) {
      return (input, output) -> {
        for (String line : lines) {
          output.append(line);
          //this is to model correct behavior in game, as otherwise expected will be incorrect
          if (!(line.contains("Game over") || line.contains("You win"))) {
            output.append("\n");
          }
        }
      };
    }

    static Interaction inputs(String in) {
      return (input, output) -> {
        input.append(in);
      };
    }

    void apply(StringBuilder in, StringBuilder out);
  }

}