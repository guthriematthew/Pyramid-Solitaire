import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksPyramidSolitaire;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class for testing the TriPeaksPyramidSolitaire class.
 */
public class TriPeaksPyramidSolitaireTest {

  ExampleDecks decks;
  TriPeaksPyramidSolitaire tps;
  CardExamples cards;

  /**
   * Resets data when running tests on the same examples.
   */
  @Before
  public void initData() {
    this.decks = new ExampleDecks();
    this.tps = new TriPeaksPyramidSolitaire();
    this.cards = new CardExamples();
  }

  @Test
  public void getDeck() {
    assertEquals(this.decks.getDeckDeckTri, tps.getDeck());
    tps.startGame(decks.assignmentDeckTri, false, 7, 1);
    assertEquals(this.decks.getDeckDeckTri, tps.getDeck());
    for (int i = 0; i < 5; i++) {
      tps.discardDraw(0);
    }

    assertEquals(this.decks.getDeckDeckTri, tps.getDeck());
    tps.remove(6, 11, 6, 2);
    assertEquals(this.decks.getDeckDeckTri, tps.getDeck());
  }

  @Test
  public void startGamePyramid() {
    //check for correct creation of all legal pyramid sizes
    int totalSize = 0;
    tps.startGame(decks.deck1Tri, false, 1, 1);
    assertEquals(1, tps.getRowWidth(0));
    totalSize++;
    assertEquals(1, totalSize);
    tps.startGame(decks.deck1Tri, false, 2, 1);
    totalSize = 0;
    assertEquals(3, tps.getRowWidth(0));
    totalSize += 3;
    assertEquals(4, tps.getRowWidth(1));
    totalSize += 4;
    assertEquals(7, totalSize);
    tps.startGame(decks.deck1Tri, false, 3, 1);
    totalSize = 0;
    assertEquals(3, tps.getRowWidth(0));
    totalSize += 3;
    assertEquals(4, tps.getRowWidth(1));
    totalSize += 4;
    assertEquals(5, tps.getRowWidth(2));
    totalSize += 5;
    assertEquals(12, totalSize);
    tps.startGame(decks.deck1Tri, false, 4, 1);
    totalSize = 0;
    assertEquals(5, tps.getRowWidth(0));
    totalSize += 5;
    assertEquals(6, tps.getRowWidth(1));
    totalSize += 6;
    assertEquals(7, tps.getRowWidth(2));
    totalSize += 7;
    assertEquals(8, tps.getRowWidth(3));
    totalSize += 8;
    assertEquals(26, totalSize);
    tps.startGame(decks.deck1Tri, false, 5, 1);
    totalSize = 0;
    assertEquals(5, tps.getRowWidth(0));
    totalSize += 5;
    assertEquals(6, tps.getRowWidth(1));
    totalSize += 6;
    assertEquals(7, tps.getRowWidth(2));
    totalSize += 7;
    assertEquals(8, tps.getRowWidth(3));
    totalSize += 8;
    assertEquals(9, tps.getRowWidth(4));
    totalSize += 9;
    assertEquals(35, totalSize);
    tps.startGame(decks.deck1Tri, false, 6, 1);
    totalSize = 0;
    assertEquals(7, tps.getRowWidth(0));
    totalSize += 7;
    assertEquals(8, tps.getRowWidth(1));
    totalSize += 8;
    assertEquals(9, tps.getRowWidth(2));
    totalSize += 9;
    assertEquals(10, tps.getRowWidth(3));
    totalSize += 10;
    assertEquals(11, tps.getRowWidth(4));
    totalSize += 11;
    assertEquals(12, tps.getRowWidth(5));
    totalSize += 12;
    assertEquals(57, totalSize);
    tps.startGame(decks.deck1Tri, false, 7, 1);
    totalSize = 0;
    assertEquals(7, tps.getRowWidth(0));
    totalSize += 7;
    assertEquals(8, tps.getRowWidth(1));
    totalSize += 8;
    assertEquals(9, tps.getRowWidth(2));
    totalSize += 9;
    assertEquals(10, tps.getRowWidth(3));
    totalSize += 10;
    assertEquals(11, tps.getRowWidth(4));
    totalSize += 11;
    assertEquals(12, tps.getRowWidth(5));
    totalSize += 12;
    assertEquals(13, tps.getRowWidth(6));
    totalSize += 13;
    assertEquals(70, totalSize);
    tps.startGame(decks.deck1Tri, false, 8, 1);
    totalSize = 0;
    assertEquals(9, tps.getRowWidth(0));
    totalSize += 9;
    assertEquals(10, tps.getRowWidth(1));
    totalSize += 10;
    assertEquals(11, tps.getRowWidth(2));
    totalSize += 11;
    assertEquals(12, tps.getRowWidth(3));
    totalSize += 12;
    assertEquals(13, tps.getRowWidth(4));
    totalSize += 13;
    assertEquals(14, tps.getRowWidth(5));
    totalSize += 14;
    assertEquals(15, tps.getRowWidth(6));
    totalSize += 15;
    assertEquals(16, tps.getRowWidth(7));
    totalSize += 16;
    assertEquals(100, totalSize);
  }

  @Test
  public void startGameDraw() {
    //check for correct creation of all legal draw piles
    for (int n = 0; n < 103; n++) {
      tps.startGame(decks.assignmentDeckTri, false, 1, n);
      assertEquals(n, tps.getDrawCards().size());
    }
  }

  @Test
  public void startGameDeck() {
    //count pyramid and prepare deck for counting
    for (int n = 1; n <= 8; n++) {
      tps.startGame(decks.assignmentDeckTri, false, n, 1);
      int halfHeight = Math.round(n / 2);
      int lastCount = halfHeight * 3;
      int pyramidCounter = 0;
      for (int i = 0; i < n; i++) {
        if (i < halfHeight) {
          pyramidCounter = pyramidCounter + 3 * (i + 1);
        } else {
          pyramidCounter = pyramidCounter + lastCount + 1;
          lastCount++;
        }
      }
      int nonDeckCards = pyramidCounter + 1;
      tps.discardDraw(0);

      //count deck size
      int deckCounter = 0;
      while (tps.getDrawCards().get(0) != null) {
        tps.discardDraw(0);
        deckCounter++;
      }
      assertEquals(104 - nonDeckCards, deckCounter);
    }
  }

  @Test
  public void startGameShuffleValidDeck() {
    for (int i = 0; i < 10; i++) {
      TriPeaksPyramidSolitaire randPyramid0 = new TriPeaksPyramidSolitaire(new Random(i * 13 * 17));
      randPyramid0.startGame(decks.assignmentDeckTri, true, 7, 1);
      List<SolitaireCard> valid = randPyramid0.getDeck();
      List<SolitaireCard> randDeck = new ArrayList<SolitaireCard>();
      for (int j = 0; j < 7; j++) {
        for (int n = 0; n < randPyramid0.getRowWidth(j); n++) {
          if (randPyramid0.getCardAt(j, n) != null) {
            randDeck.add(randPyramid0.getCardAt(j, n));
          }
        }
      }
      while (randPyramid0.getDrawCards().get(0) != null) {
        randDeck.add(randPyramid0.getDrawCards().get(0));
        randPyramid0.discardDraw(0);
      }
      assertEquals(true, valid.containsAll(randDeck) && randDeck.containsAll(valid)
              && randDeck.size() == 104);
    }


  }

  @Test
  public void startGameShuffleDifferentDecks() {
    for (int i = 1; i < 11; i++) {
      TriPeaksPyramidSolitaire randPyramid0 = new TriPeaksPyramidSolitaire(new Random(i * 17));
      TriPeaksPyramidSolitaire randPyramid1 = new TriPeaksPyramidSolitaire(new Random(i * 13));
      randPyramid0.startGame(decks.deck1Tri, true, 7, 1);
      randPyramid1.startGame(decks.deck1Tri, true, 7, 1);

      boolean notAllSame = false;

      for (int j = 0; j < 7; j++) {
        for (int n = 0; n <= randPyramid0.getRowWidth(j); n++) {
          if (!(randPyramid0.getCardAt(j, n).equals(randPyramid1.getCardAt(j, n)))) {
            notAllSame = true;
            break;
          }
        }
      }

      assertEquals(true, notAllSame);
    }


  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameSmallDeckException() {
    ArrayList<SolitaireCard> badDeck = new ArrayList<SolitaireCard>();
    for (int i = 0; i < 26; i++) {
      badDeck.add(decks.deck1Tri.get(i));
    }
    tps.startGame(badDeck, false, 5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameRepeatingDeckException() {
    ArrayList<SolitaireCard> badDeck = new ArrayList<SolitaireCard>();
    for (int i = 0; i < 52; i++) {
      badDeck.add(decks.deck1Tri.get(0));
    }
    tps.startGame(badDeck, false, 5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsNegException() {
    tps.startGame(decks.deck1Tri, false, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsZeroException1() {
    tps.startGame(decks.deck1Tri, false, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsLargeException() {
    tps.startGame(decks.deck1Tri, false, 9, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsEightException() {
    tps.startGame(decks.deck1Tri, false, 8, 17);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumDrawLow() {
    tps.startGame(decks.deck1Tri, false, 5, -1);

  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumDrawHigh() {
    tps.startGame(decks.deck1Tri, false, 5, 72);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumDrawOver104() {
    tps.startGame(decks.deck1Tri, false, 1, 104);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameBothLow() {
    tps.startGame(decks.deck1Tri, false, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameBothHigh() {
    tps.startGame(decks.deck1Tri, false, 9, 1);
  }

  @Test
  public void remove2Cards() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 1);
    tps.remove(6, 2, 6, 11);
    assertEquals(null, tps.getCardAt(6, 2));
    assertEquals(null, tps.getCardAt(6, 11));
  }

  @Test(expected = IllegalStateException.class)
  public void remove2CardsNotStarted() {
    tps.remove(0, 0, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNullRemoveCard1() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 1);
    tps.remove(6, 3, 6, 6);
    tps.remove(6, 3, 6, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNullRemoveCard2() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 1);
    tps.remove(6, 3, 6, 6);
    tps.remove(6, 0, 6, 6);
  }


  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(7, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds1() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(10, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds2() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(0, 3, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds3() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(0, -1, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds4() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(0, 0, -5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds5() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(0, 3, 7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds6() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(0, 3, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds7() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(0, 3, 1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsIllegalMoveDeck1() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsIllegalMoveAssignDeck() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 1);
    tps.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNonPlayableCard1() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(5, 13, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNonPlayableCard2() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(6, 2, 1, 1);
  }


  @Test
  public void remove1Card() {
    tps.startGame(decks.winNoDrawDeckTri, false, 2, 1);
    tps.remove(1, 1);
    assertEquals(null, tps.getCardAt(1, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void remove1CardNotStarted() {
    tps.remove(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalNullRemove() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 1);
    tps.remove(6, 3, 6, 6);
    tps.remove(6, 3, 6, 6);
  }


  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalFive() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 1);
    tps.remove(6, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalMoveQueen() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(6, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsLowRow() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighRow1() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighRow2() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsLowCard() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighCard1() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(5, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighCard2() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(6, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardNonPlayableCard() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.remove(1, 2);
  }


  @Test
  public void removeUsingDraw() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 2);
    tps.discardDraw(0);

    tps.removeUsingDraw(0, 6, 11);
    tps.removeUsingDraw(1, 6, 12);
    assertEquals(null, tps.getCardAt(6, 11));
    assertEquals(null, tps.getCardAt(6, 11));
    assertEquals(tps.getDrawCards().get(0), cards.diamondThree);
    assertEquals(tps.getDrawCards().get(1), cards.diamondFour);
  }

  @Test(expected = IllegalStateException.class)
  public void removeUsingDrawGameNotStarted() {
    tps.removeUsingDraw(0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNullCard() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 2);
    tps.removeUsingDraw(0, 6, 2);
    tps.removeUsingDraw(1, 6, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNullDraw() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    for (int i = 0; i < 24; i++) {
      tps.discardDraw(0);
    }
    tps.removeUsingDraw(0, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowDraw() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.removeUsingDraw(-1, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighDraw() {
    tps.startGame(decks.deck1Tri, false, 7, 3);
    tps.removeUsingDraw(5, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowRow() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.removeUsingDraw(0, -1, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighRow() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.removeUsingDraw(0, 17, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowCard() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.removeUsingDraw(0, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighCard() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.removeUsingDraw(0, 2, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingBadMove() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.removeUsingDraw(0, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNonPlayable() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.removeUsingDraw(0, 6, 2);
    tps.removeUsingDraw(0, 2, 1);
  }

  @Test
  public void discardDraw() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 3);
    for (int i = 0; i < 37; i++) {
      SolitaireCard c = tps.getDrawCards().get(0);
      tps.discardDraw(0);
      assertNotEquals(c, tps.getDrawCards().get(0));
      assertEquals(3, tps.getDrawCards().size());
    }
    tps.discardDraw(0);
    assertEquals(null, tps.getDrawCards().get(0));
    assertEquals(3, tps.getDrawCards().size());
    tps.discardDraw(1);
    assertEquals(null, tps.getDrawCards().get(1));
    assertEquals(3, tps.getDrawCards().size());
    tps.discardDraw(2);
    assertEquals(null, tps.getDrawCards().get(2));
    assertEquals(3, tps.getDrawCards().size());

  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawLowDrawNum1() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 3);
    tps.discardDraw(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawLowDrawNum2() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 3);
    tps.discardDraw(-120);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawHighDrawNum1() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 3);
    tps.discardDraw(3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawHighDrawNum2() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 3);
    tps.discardDraw(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawDrawNull() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 3);
    for (int i = 0; i < 95; i++) {
      SolitaireCard c = tps.getDrawCards().get(0);
      tps.discardDraw(0);
      assertNotEquals(c, tps.getDrawCards().get(0));
      assertEquals(3, tps.getDrawCards().size());
    }
    tps.discardDraw(0);
    assertEquals(null, tps.getDrawCards().get(0));
    tps.discardDraw(0);
  }

  @Test(expected = IllegalStateException.class)
  public void discardDrawNotStarted() {
    tps.discardDraw(0);
  }

  @Test
  public void getNumRows() {
    for (int i = 1; i < 9; i++) {
      tps.startGame(decks.deck1Tri, false, i, 1);
      assertEquals(i, tps.getNumRows());
    }
  }

  @Test
  public void getNumRowsNotStarted() {
    assertEquals(-1, tps.getNumRows());
  }

  @Test
  public void getNumDraw() {
    tps.startGame(decks.deck1Tri, false, 8, 15);
    assertEquals(15, tps.getNumDraw());
    tps.discardDraw(0);
    assertEquals(15, tps.getNumDraw());
    for (int i = 0; i < 15; i++) {
      assertEquals(15 - i, tps.getNumDraw());
      tps.discardDraw(i);
    }
    assertEquals(0, tps.getNumDraw());
  }

  @Test
  public void getNumDrawNotStarted() {
    assertEquals(-1, tps.getNumDraw());
  }

  @Test
  public void getRowWidth() {
    tps.startGame(decks.deck1Tri, false, 1, 1);
    assertEquals(1, tps.getRowWidth(0));
    tps.startGame(decks.deck1Tri, false, 2, 1);
    assertEquals(3, tps.getRowWidth(0));
    assertEquals(4, tps.getRowWidth(1));
    tps.startGame(decks.deck1Tri, false, 3, 1);
    assertEquals(3, tps.getRowWidth(0));
    assertEquals(4, tps.getRowWidth(1));
    assertEquals(5, tps.getRowWidth(2));
    tps.startGame(decks.deck1Tri, false, 4, 1);
    assertEquals(5, tps.getRowWidth(0));
    assertEquals(6, tps.getRowWidth(1));
    assertEquals(7, tps.getRowWidth(2));
    assertEquals(8, tps.getRowWidth(3));
    tps.startGame(decks.deck1Tri, false, 5, 1);
    assertEquals(5, tps.getRowWidth(0));
    assertEquals(6, tps.getRowWidth(1));
    assertEquals(7, tps.getRowWidth(2));
    assertEquals(8, tps.getRowWidth(3));
    assertEquals(9, tps.getRowWidth(4));
    tps.startGame(decks.deck1Tri, false, 6, 1);
    assertEquals(7, tps.getRowWidth(0));
    assertEquals(8, tps.getRowWidth(1));
    assertEquals(9, tps.getRowWidth(2));
    assertEquals(10, tps.getRowWidth(3));
    assertEquals(11, tps.getRowWidth(4));
    assertEquals(12, tps.getRowWidth(5));
    tps.startGame(decks.deck1Tri, false, 7, 1);
    assertEquals(7, tps.getRowWidth(0));
    assertEquals(8, tps.getRowWidth(1));
    assertEquals(9, tps.getRowWidth(2));
    assertEquals(10, tps.getRowWidth(3));
    assertEquals(11, tps.getRowWidth(4));
    assertEquals(12, tps.getRowWidth(5));
    assertEquals(13, tps.getRowWidth(6));
    tps.startGame(decks.deck1Tri, false, 8, 1);
    assertEquals(9, tps.getRowWidth(0));
    assertEquals(10, tps.getRowWidth(1));
    assertEquals(11, tps.getRowWidth(2));
    assertEquals(12, tps.getRowWidth(3));
    assertEquals(13, tps.getRowWidth(4));
    assertEquals(14, tps.getRowWidth(5));
    assertEquals(15, tps.getRowWidth(6));
    assertEquals(16, tps.getRowWidth(7));

  }

  @Test(expected = IllegalStateException.class)
  public void getRowWidthNotStarted() {
    tps.getRowWidth(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getRowWidthBadRowLow() {
    tps.startGame(decks.deck1Tri, false, 2, 1);
    tps.getRowWidth(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getRowWidthBadRowHigh() {
    tps.startGame(decks.deck1Tri, false, 2, 1);
    tps.getRowWidth(2);
  }

  @Test
  public void isGameOverYesWin() {
    tps.startGame(decks.winNoDrawDeckTri, false, 1, 1);
    for (int i = 0; i < 102; i++) {
      tps.discardDraw(0);
    }
    tps.remove(0, 0);
    assertEquals(true, tps.isGameOver());
  }

  @Test
  public void isGameOverYesLoss() {
    tps.startGame(decks.deck1Tri, false, 8, 1);
    tps.remove(7, 0, 7, 4);
    tps.remove(7, 1, 7, 5);
    tps.remove(7, 2, 7, 6);
    tps.remove(7, 3, 7, 7);

    for (int i = 0; i < 16; i++) {
      tps.discardDraw(0);
    }
    for (SolitaireCard c : tps.getDrawCards()) {
      assertEquals(null, c);
    }
    assertEquals(true, tps.isGameOver());
  }

  @Test
  public void isGameOverStillDrawCards() {
    tps.startGame(decks.winNoDrawDeckTri, false, 2, 5);
    tps.remove(1, 0);
    tps.remove(1, 1);
    tps.remove(1, 2);
    tps.remove(1, 3);
    tps.remove(0, 0);
    tps.remove(0, 1);
    tps.remove(0, 2);
    assertEquals(true, tps.isGameOver());
  }

  @Test
  public void isGameOverStillDeckCards() {
    tps.startGame(decks.winNoDrawDeckTri, false, 2, 0);
    tps.remove(1, 0);
    tps.remove(1, 1);
    tps.remove(1, 2);
    tps.remove(1, 3);
    tps.remove(0, 0);
    tps.remove(0, 1);
    tps.remove(0, 2);
    assertEquals(true, tps.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsPyramid() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    for (int i = 0; i < 40; i++) {
      tps.discardDraw(0);
    }
    assertEquals(false, tps.isGameOver());
    tps.remove(6, 0);
    assertEquals(true, tps.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsDraw() {
    tps.startGame(decks.lastPlayableDeckTri, false, 2, 1);
    tps.remove(1, 2, 1, 3);
    assertEquals(false, tps.isGameOver());
    for (int i = 0; i < 96; i++) {
      tps.discardDraw(0);
    }
    tps.removeUsingDraw(0, 1, 1);
    assertEquals(true, tps.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsKing() {
    tps.startGame(decks.lastKingDeckTri, false, 2, 1);
    for (int i = 0; i < 97; i++) {
      tps.discardDraw(0);
    }
    assertEquals(false, tps.isGameOver());
    tps.remove(1, 2, 1, 3);
    assertEquals(false, tps.isGameOver());
    tps.remove(1, 1);
    assertEquals(true, tps.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void isGameOverNotStarted() {
    tps.isGameOver();
  }

  @Test
  public void getScore() {
    tps.startGame(decks.assignmentDeckTri, false, 7, 1);
    assertEquals(443, tps.getScore());
    tps.remove(6, 2, 6, 11);
    assertEquals(430, tps.getScore());
    tps.discardDraw(0);
    assertEquals(430, tps.getScore());
    tps.removeUsingDraw(0, 6, 12);
    assertEquals(418, tps.getScore());
    tps.remove(6, 0);
    assertEquals(405, tps.getScore());

  }

  @Test(expected = IllegalStateException.class)
  public void getScoreNotStarted() {
    tps.getScore();
  }

  @Test
  public void getCardAt() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    ArrayList<SolitaireCard> expected = new ArrayList<SolitaireCard>(
            Arrays.asList(cards.clubAce, cards.diamondAce,
                    cards.spadeAce, cards.heartAce, cards.clubTwo, cards.diamondTwo,
                    cards.spadeTwo, cards.heartTwo, cards.clubThree, cards.diamondThree,
                    cards.spadeThree, cards.heartThree, cards.clubFour, cards.diamondFour,
                    cards.spadeFour, cards.heartFour, cards.clubFive, cards.diamondFive,
                    cards.spadeFive, cards.heartFive, cards.clubSix, cards.diamondSix,
                    cards.spadeSix, cards.heartSix, cards.clubSeven, cards.diamondSeven,
                    cards.spadeSeven, cards.heartSeven, cards.clubEight, cards.diamondEight,
                    cards.spadeEight, cards.heartEight, cards.clubNine, cards.diamondNine,
                    cards.spadeNine, cards.heartNine, cards.clubTen, cards.diamondTen,
                    cards.spadeTen, cards.heartTen, cards.clubJack, cards.diamondJack,
                    cards.spadeJack, cards.heartJack, cards.clubQueen, cards.diamondQueen,
                    cards.spadeQueen, cards.heartQueen, cards.clubKing, cards.diamondKing,
                    cards.spadeKing, cards.heartKing, cards.clubAce, cards.diamondAce,
                    cards.spadeAce, cards.heartAce, cards.clubTwo, cards.diamondTwo,
                    cards.spadeTwo, cards.heartTwo, cards.clubThree, cards.diamondThree,
                    cards.spadeThree, cards.heartThree, cards.clubFour, cards.diamondFour,
                    cards.spadeFour, cards.heartFour, cards.clubFive, cards.diamondFive,
                    cards.spadeFive, cards.heartFive, cards.clubSix, cards.diamondSix,
                    cards.spadeSix, cards.heartSix, cards.clubSeven, cards.diamondSeven,
                    cards.spadeSeven, cards.heartSeven, cards.clubEight, cards.diamondEight,
                    cards.spadeEight, cards.heartEight, cards.clubNine, cards.diamondNine,
                    cards.spadeNine, cards.heartNine, cards.clubTen, cards.diamondTen,
                    cards.spadeTen, cards.heartTen, cards.clubJack, cards.diamondJack,
                    cards.spadeJack, cards.heartJack, cards.clubQueen, cards.diamondQueen,
                    cards.spadeQueen, cards.heartQueen, cards.clubKing, cards.diamondKing,
                    cards.spadeKing, cards.heartKing));
    int counter = 0;
    for (int i = 0; i < tps.getNumRows(); i++) {
      for (int j = 0; j < tps.getRowWidth(i); j++) {
        if (tps.getCardAt(i, j) != null) {
          assertEquals(expected.get(counter), tps.getCardAt(i, j));
          counter++;
        }
      }
    }
  }

  @Test(expected = IllegalStateException.class)
  public void getCardAtNotStarted() {
    tps.getCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadRowLow() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.getCardAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadRowHigh() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.getCardAt(10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadCardLow() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.getCardAt(3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadCardHigh() {
    tps.startGame(decks.deck1Tri, false, 7, 1);
    tps.getCardAt(4, 20);
  }


  @Test
  public void getDrawCards() {
    tps.startGame(decks.deck1Tri, false, 1, 51);
    decks.deck1Tri.remove(0);
    for (int i = 0; i < 51; i++) {
      assertEquals(decks.deck1Tri.get(i), tps.getDrawCards().get(i));
    }
  }

  @Test(expected = IllegalStateException.class)
  public void getDrawCardsNotStarted() {
    tps.getDrawCards();
  }

  @Test(expected = IllegalArgumentException.class)
  public void notRelaxedTopFirst() {
    tps.startGame(decks.deck1Tri, false, 7, 1);

    for (int i = 0; i < 32; i++) {
      tps.discardDraw(0);
    }
    tps.removeUsingDraw(0, 6, 4);
    tps.remove(5, 4, 6, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void notRelaxedTopSecond() {
    tps.startGame(decks.deck1Tri, false, 7, 1);

    for (int i = 0; i < 32; i++) {
      tps.discardDraw(0);
    }
    tps.removeUsingDraw(0, 6, 4);
    tps.remove(6, 5, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void deckRepeatsQueen() {
    tps.startGame(decks.QueenRepeatTri, false, 7, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void deckRepeatsAce52() {
    tps.startGame(decks.AceRepeat52, false, 7, 1);
  }

}