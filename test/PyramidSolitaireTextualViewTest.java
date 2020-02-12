import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import static junit.framework.TestCase.assertEquals;

/**
 * Class to hold tests for methods in the PyramidSolitaireTextualView class.
 */
public class PyramidSolitaireTextualViewTest {

  ExampleDecks decks = new ExampleDecks();
  PyramidSolitaireTextualView textView;
  PyramidSolitaireTextualView textViewRender;
  Appendable out;
  ExampleBasicPyramidSolitaire bps = new ExampleBasicPyramidSolitaire();

  @Before
  public void initData() {
    this.textView = new PyramidSolitaireTextualView(bps.bps1);
    this.out = new StringBuilder();
    this.textViewRender = new PyramidSolitaireTextualView(bps.bps1, this.out);
  }

  @Test
  public void testToStringFullDeck() {
    bps.bps1.startGame(decks.deck1, false, 9, 7);
    assertEquals(textView.toString(),
            "                A♣\n"
                    + "              A♦  A♠\n"
                    + "            A♥  2♣  2♦\n"
                    + "          2♠  2♥  3♣  3♦\n"
                    + "        3♠  3♥  4♣  4♦  4♠\n"
                    + "      4♥  5♣  5♦  5♠  5♥  6♣\n"
                    + "    6♦  6♠  6♥  7♣  7♦  7♠  7♥\n"
                    + "  8♣  8♦  8♠  8♥  9♣  9♦  9♠  9♥\n"
                    + "10♣ 10♦ 10♠ 10♥ J♣  J♦  J♠  J♥  Q♣\n"
                    + "Draw: Q♦, Q♠, Q♥, K♣, K♦, K♠, K♥");
  }

  @Test
  public void testToStringAssignmentDeck() {
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.removeUsingDraw(0, 6, 2);
    bps.bps1.removeUsingDraw(0, 5, 2);
    assertEquals(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  10♣ 10♥\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦  4♦      6♦  7♦  8♦\n"
                    + "9♦  10♦         K♦  A♥\n"
                    + "Draw: 5♥", textView.toString());
  }

  @Test
  public void testToStringNullDraw() {
    bps.bps1.startGame(decks.winNoDrawDeck, false, 1, 3);
    for (int i = 0; i < 48; i++) {
      bps.bps1.discardDraw(0);
    }
    assertEquals("K♠\nDraw: Q♦, K♣, A♣", textView.toString());
    bps.bps1.discardDraw(0);
    assertEquals("K♠\nDraw: K♣, A♣", textView.toString());
    bps.bps1.discardDraw(1);
    assertEquals("K♠\nDraw: A♣", textView.toString());
    bps.bps1.discardDraw(2);
    assertEquals("K♠\nDraw:", textView.toString());


  }

  @Test
  public void testToStringGameOver() {
    bps.bps1.startGame(decks.deck1, false, 1, 3);
    for (int i = 0; i < 48; i++) {
      bps.bps1.discardDraw(0);
    }
    bps.bps1.discardDraw(0);
    bps.bps1.discardDraw(1);
    bps.bps1.discardDraw(2);
    assertEquals("Game over. Score: 1", textView.toString());
  }

  @Test
  public void testToStringWin() {
    bps.bps1.startGame(decks.winDeck, false, 1, 1);
    for (int i = 0; i < 50; i++) {
      bps.bps1.discardDraw(0);
    }
    bps.bps1.removeUsingDraw(0, 0, 0);
    assertEquals("You win!", textView.toString());
  }

  @Test
  public void testToStringNotStarted() {
    assertEquals("", textView.toString());
  }

  @Test
  public void testRenderFullDeck() throws IOException {
    bps.bps1.startGame(decks.deck1, false, 9, 7);
    textViewRender.render();
    assertEquals(out.toString(),
            "                A♣\n"
                    + "              A♦  A♠\n"
                    + "            A♥  2♣  2♦\n"
                    + "          2♠  2♥  3♣  3♦\n"
                    + "        3♠  3♥  4♣  4♦  4♠\n"
                    + "      4♥  5♣  5♦  5♠  5♥  6♣\n"
                    + "    6♦  6♠  6♥  7♣  7♦  7♠  7♥\n"
                    + "  8♣  8♦  8♠  8♥  9♣  9♦  9♠  9♥\n"
                    + "10♣ 10♦ 10♠ 10♥ J♣  J♦  J♠  J♥  Q♣\n"
                    + "Draw: Q♦, Q♠, Q♥, K♣, K♦, K♠, K♥");
  }

  @Test
  public void testRenderAssignmentDeck() throws IOException {
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.removeUsingDraw(0, 6, 2);
    bps.bps1.removeUsingDraw(0, 5, 2);
    textViewRender.render();
    assertEquals(
            "            A♣\n"
                    + "          2♣  3♣\n"
                    + "        4♣  5♣  6♣\n"
                    + "      7♣  8♣  10♣ 10♥\n"
                    + "    J♣  Q♣  K♣  A♦  2♦\n"
                    + "  3♦  4♦      6♦  7♦  8♦\n"
                    + "9♦  10♦         K♦  A♥\n"
                    + "Draw: 5♥", out.toString());
  }

  @Test
  public void testRenderNullDraw() throws IOException {
    bps.bps1.startGame(decks.winNoDrawDeck, false, 1, 3);
    for (int i = 0; i < 48; i++) {
      bps.bps1.discardDraw(0);
    }
    textViewRender.render();
    assertEquals("K♠\nDraw: Q♦, K♣, A♣", out.toString());
    bps.bps1.discardDraw(0);
    textViewRender.render();
    assertEquals("K♠\nDraw: Q♦, K♣, A♣" + "K♠\nDraw: K♣, A♣", out.toString());
    bps.bps1.discardDraw(1);
    textViewRender.render();
    assertEquals("K♠\nDraw: Q♦, K♣, A♣" + "K♠\nDraw: K♣, A♣" + "K♠\nDraw: A♣",
            out.toString());
    bps.bps1.discardDraw(2);
    textViewRender.render();
    assertEquals(
            "K♠\nDraw: Q♦, K♣, A♣" + "K♠\nDraw: K♣, A♣" + "K♠\nDraw: A♣" + "K♠\nDraw:",
            out.toString());


  }

  @Test
  public void testRenderGameOver() throws IOException {
    bps.bps1.startGame(decks.deck1, false, 1, 3);
    for (int i = 0; i < 48; i++) {
      bps.bps1.discardDraw(0);
    }
    bps.bps1.discardDraw(0);
    bps.bps1.discardDraw(1);
    bps.bps1.discardDraw(2);
    textViewRender.render();
    assertEquals("Game over. Score: 1", out.toString());
  }

  @Test
  public void testRenderWin() throws IOException {
    bps.bps1.startGame(decks.winDeck, false, 1, 1);
    for (int i = 0; i < 50; i++) {
      bps.bps1.discardDraw(0);
    }
    bps.bps1.removeUsingDraw(0, 0, 0);
    this.textViewRender.render();
    assertEquals("You win!", out.toString());
  }

  @Test
  public void testRenderNotStarted() throws IOException {
    assertEquals("", this.out.toString());
    textViewRender.render();
    assertEquals("", this.out.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderFailAppend() throws IOException {
    AppendableExceptionTester a = new AppendableExceptionTester();
    PyramidSolitaireTextualView textView = new PyramidSolitaireTextualView(bps.bps1, a);
    textView.render();
  }

  /**
   * Class to test an object that extends Appendable throwing an IOException.
   */
  private class AppendableExceptionTester implements Appendable {
    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException(
              "This class is for testing behavior when Appendable throws an IOException");
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException(
              "This class is for testing behavior when Appendable throws an IOException");
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException(
              "This class is for testing behavior when Appendable throws an IOException");
    }
  }


}
