import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class to hold tests for methods in the BasicPyramidSolitaire class.
 */
public class BasicPyramidSolitaireTest {

  ExampleDecks decks;
  ExampleBasicPyramidSolitaire bps;
  CardExamples cards;

  /**
   * Resets data when running tests on the same examples.
   */
  @Before
  public void initData() {
    this.decks = new ExampleDecks();
    this.bps = new ExampleBasicPyramidSolitaire();
    this.cards = new CardExamples();
  }

  @Test
  public void getDeck() {
    initData();
    assertEquals(this.decks.getDeckDeck, bps.bps1.getDeck());
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    assertEquals(this.decks.getDeckDeck, bps.bps1.getDeck());
    for (int i = 0; i < 5; i++) {
      bps.bps1.discardDraw(0);
    }
    assertEquals(this.decks.getDeckDeck, bps.bps1.getDeck());
    bps.bps1.remove(6, 3, 6, 6);
    assertEquals(this.decks.getDeckDeck, bps.bps1.getDeck());
  }

  @Test
  public void startGamePyramid() {
    initData();
    //check for correct creation of all legal pyramid sizes
    for (int n = 1; n <= 9; n++) {
      bps.bps1.startGame(decks.deck1, false, n, 1);
      int pyramidCounter = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < bps.bps1.getRowWidth(i); j++) {
          pyramidCounter++;
        }
      }
      assertEquals(n * (n + 1) / 2, pyramidCounter);
    }
  }

  @Test
  public void startGameDraw() {
    initData();
    //check for correct creation of all legal draw piles
    for (int n = 0; n < 51; n++) {
      bps.bps1.startGame(decks.deck1, false, 1, n);
      assertEquals(n, bps.bps1.getDrawCards().size());
    }
  }

  @Test
  public void startGameDeck() {
    initData();
    //count pyramid and prepare deck for counting
    for (int n = 1; n <= 9; n++) {
      bps.bps1.startGame(decks.deck1, false, n, 1);
      int pyramidCounter = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < bps.bps1.getRowWidth(i); j++) {
          pyramidCounter++;
        }
      }
      int nonDeckCards = pyramidCounter + 1;
      bps.bps1.discardDraw(0);

      //count deck size
      int deckCounter = 0;
      while (bps.bps1.getDrawCards().get(0) != null) {
        bps.bps1.discardDraw(0);
        deckCounter++;
      }
      assertEquals(52 - nonDeckCards, deckCounter);
    }
  }

  @Test
  public void startGameShuffleValidDeck() {
    initData();
    for (int i = 0; i < 10; i++) {
      BasicPyramidSolitaire randPyramid0 = new BasicPyramidSolitaire(new Random(i * 13 * 17));
      randPyramid0.startGame(decks.deck1, true, 7, 1);
      List<SolitaireCard> valid = randPyramid0.getDeck();
      List<SolitaireCard> randDeck = new ArrayList<SolitaireCard>();
      for (int j = 0; j < 7; j++) {
        for (int n = 0; n <= j; n++) {
          randDeck.add(randPyramid0.getCardAt(j, n));
        }
      }
      while (randPyramid0.getDrawCards().get(0) != null) {
        randDeck.add(randPyramid0.getDrawCards().get(0));
        randPyramid0.discardDraw(0);
      }
      assertEquals(true, valid.containsAll(randDeck) && randDeck.containsAll(valid)
              && randDeck.size() == 52);
    }


  }

  @Test
  public void startGameShuffleDifferentDecks() {
    initData();
    for (int i = 1; i < 11; i++) {
      BasicPyramidSolitaire randPyramid0 = new BasicPyramidSolitaire(new Random(i * 17));
      BasicPyramidSolitaire randPyramid1 = new BasicPyramidSolitaire(new Random(i * 13));
      randPyramid0.startGame(decks.deck1, true, 7, 1);
      randPyramid1.startGame(decks.deck1, true, 7, 1);

      boolean notAllSame = false;

      for (int j = 0; j < 7; j++) {
        for (int n = 0; n <= j; n++) {
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
    initData();
    ArrayList<SolitaireCard> badDeck = new ArrayList<SolitaireCard>();
    for (int i = 0; i < 26; i++) {
      badDeck.add(decks.deck1.get(i));
    }
    bps.bps1.startGame(badDeck, false, 5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameRepeatingDeckException() {
    initData();
    ArrayList<SolitaireCard> badDeck = new ArrayList<SolitaireCard>();
    for (int i = 0; i < 52; i++) {
      badDeck.add(decks.deck1.get(0));
    }
    bps.bps1.startGame(badDeck, false, 5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsNegException() {
    initData();
    bps.bps1.startGame(decks.deck1, false, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsZeroException1() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsLargeException() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 20, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsNineException() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 9, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumDrawLow() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 5, -1);

  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumDrawHigh() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 5, 70);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumDrawOver52() {
    initData();

    bps.bps1.startGame(decks.deck1, false, 9, 8);
  }

  @Test
  public void remove2Cards() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    assertEquals(null, bps.bps1.getCardAt(6, 3));
    assertEquals(null, bps.bps1.getCardAt(6, 6));
  }

  @Test(expected = IllegalStateException.class)
  public void remove2CardsNotStarted() {
    initData();
    bps.bps1.remove(0, 0, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNullRemoveCard1() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.remove(6, 3, 6, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNullRemoveCard2() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.remove(6, 0, 6, 6);
  }


  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(7, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds1() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(10, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds2() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(0, 3, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds3() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(0, -1, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds4() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(0, 0, -5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds5() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(0, 3, 7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds6() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(0, 3, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds7() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(0, 3, 1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsIllegalMoveDeck1() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsIllegalMoveAssignDeck() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNonPlayableCard1() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(5, 6, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNonPlayableCard2() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(6, 2, 1, 1);
  }


  @Test
  public void remove1Card() {
    initData();
    bps.bps1.startGame(decks.kingRemoval, false, 2, 1);
    bps.bps1.remove(1, 1);
    assertEquals(null, bps.bps1.getCardAt(1, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void remove1CardNotStarted() {
    initData();
    bps.bps1.remove(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalNullRemove() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.remove(6, 3, 6, 6);
  }


  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalMoveNine() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalMoveQueen() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 9, 1);
    bps.bps1.remove(8, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsLowRow() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighRow1() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighRow2() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsLowCard() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighCard1() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(5, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighCard2() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(6, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardNonPlayableCard() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.remove(1, 2);
  }


  @Test
  public void removeUsingDraw() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 2);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.removeUsingDraw(0, 6, 2);
    bps.bps1.removeUsingDraw(1, 5, 2);
    assertEquals(null, bps.bps1.getCardAt(6, 2));
    assertEquals(null, bps.bps1.getCardAt(5, 2));
    assertEquals(bps.bps1.getDrawCards().get(0), cards.heartFive);
    assertEquals(bps.bps1.getDrawCards().get(1), cards.heartThree);
  }

  @Test(expected = IllegalStateException.class)
  public void removeUsingDrawGameNotStarted() {
    initData();
    bps.bps1.removeUsingDraw(0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNullCard() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 2);
    bps.bps1.removeUsingDraw(0, 6, 2);
    bps.bps1.removeUsingDraw(1, 6, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNullDraw() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    for (int i = 0; i < 24; i++) {
      bps.bps1.discardDraw(0);
    }
    bps.bps1.removeUsingDraw(0, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowDraw() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.removeUsingDraw(-1, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighDraw() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 3);
    bps.bps1.removeUsingDraw(5, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowRow() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.removeUsingDraw(0, -1, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighRow() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.removeUsingDraw(0, 17, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowCard() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.removeUsingDraw(0, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighCard() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.removeUsingDraw(0, 2, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingBadMove() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.removeUsingDraw(0, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNonPlayable() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.removeUsingDraw(0, 6, 2);
    bps.bps1.removeUsingDraw(0, 2, 1);
  }

  @Test
  public void discardDraw() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 3);
    for (int i = 0; i < 21; i++) {
      SolitaireCard c = bps.bps1.getDrawCards().get(0);
      bps.bps1.discardDraw(0);
      assertNotEquals(c, bps.bps1.getDrawCards().get(0));
      assertEquals(3, bps.bps1.getDrawCards().size());
    }
    bps.bps1.discardDraw(0);
    assertEquals(null, bps.bps1.getDrawCards().get(0));
    assertEquals(3, bps.bps1.getDrawCards().size());
    bps.bps1.discardDraw(1);
    assertEquals(null, bps.bps1.getDrawCards().get(1));
    assertEquals(3, bps.bps1.getDrawCards().size());
    bps.bps1.discardDraw(2);
    assertEquals(null, bps.bps1.getDrawCards().get(2));
    assertEquals(3, bps.bps1.getDrawCards().size());

  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawLowDrawNum1() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 3);
    bps.bps1.discardDraw(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawLowDrawNum2() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 3);
    bps.bps1.discardDraw(-120);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawHighDrawNum1() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 3);
    bps.bps1.discardDraw(3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawHighDrawNum2() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 3);
    bps.bps1.discardDraw(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawDrawNull() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 3);
    for (int i = 0; i < 21; i++) {
      SolitaireCard c = bps.bps1.getDrawCards().get(0);
      bps.bps1.discardDraw(0);
      assertNotEquals(c, bps.bps1.getDrawCards().get(0));
      assertEquals(3, bps.bps1.getDrawCards().size());
    }
    bps.bps1.discardDraw(0);
    assertEquals(null, bps.bps1.getDrawCards().get(0));
    bps.bps1.discardDraw(0);
  }

  @Test(expected = IllegalStateException.class)
  public void discardDrawNotStarted() {
    initData();
    bps.bps1.discardDraw(0);
  }

  @Test
  public void getNumRows() {
    initData();
    for (int i = 1; i < 9; i++) {
      bps.bps1.startGame(decks.deck1, false, i, 1);
      assertEquals(i, bps.bps1.getNumRows());
    }
  }

  @Test
  public void getNumRowsNotStarted() {
    initData();
    assertEquals(-1, bps.bps1.getNumRows());
  }

  @Test
  public void getNumDraw() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 9, 6);
    assertEquals(6, bps.bps1.getNumDraw());
    bps.bps1.discardDraw(0);
    assertEquals(6, bps.bps1.getNumDraw());
    for (int i = 0; i < 6; i++) {
      assertEquals(6 - i, bps.bps1.getNumDraw());
      bps.bps1.discardDraw(i);
    }
    assertEquals(0, bps.bps1.getNumDraw());
  }

  @Test
  public void getNumDrawNotStarted() {
    initData();
    assertEquals(-1, bps.bps1.getNumDraw());
  }

  @Test
  public void getRowWidth() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 9, 1);
    for (int i = 0; i < 9; i++) {
      assertEquals(i + 1, bps.bps1.getRowWidth(i));
    }
  }

  @Test(expected = IllegalStateException.class)
  public void getRowWidthNotStarted() {
    initData();
    bps.bps1.getRowWidth(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getRowWidthBadRowLow() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 2, 1);
    bps.bps1.getRowWidth(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getRowWidthBadRowHigh() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 2, 1);
    bps.bps1.getRowWidth(2);
  }

  @Test
  public void isGameOverYesWin() {
    initData();
    bps.bps1.startGame(decks.winDeck, false, 1, 1);
    for (int i = 0; i < 50; i++) {
      bps.bps1.discardDraw(0);
    }
    bps.bps1.removeUsingDraw(0, 0, 0);
    assertEquals(true, bps.bps1.isGameOver());
  }

  @Test
  public void isGameOverYesLoss() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 9, 1);
    for (int i = 0; i < 7; i++) {
      bps.bps1.discardDraw(0);
    }
    for (SolitaireCard c : bps.bps1.getDrawCards()) {
      assertEquals(null, c);
    }
    assertEquals(true, bps.bps1.isGameOver());
  }

  @Test
  public void isGameOverStillDrawCards() {
    initData();
    bps.bps1.startGame(decks.lastPlayableDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.removeUsingDraw(0, 6, 2);
    bps.bps1.removeUsingDraw(0, 5, 2);
    bps.bps1.remove(6, 4);
    for (int i = 0; i < 21; i++) {
      bps.bps1.discardDraw(0);
    }
    assertEquals(false, bps.bps1.isGameOver());
    bps.bps1.discardDraw(0);
    assertEquals(true, bps.bps1.isGameOver());
  }

  @Test
  public void isGameOverStillDeckCards() {
    initData();
    bps.bps1.startGame(decks.winNoDrawDeck, false, 2, 0);
    bps.bps1.remove(1, 0);
    bps.bps1.remove(1, 1);
    bps.bps1.remove(0, 0);
    assertEquals(true, bps.bps1.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsPyramid() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 4);
    for (int i = 0; i < 24; i++) {
      bps.bps1.discardDraw(0);
    }
    assertEquals(false, bps.bps1.isGameOver());
    bps.bps1.remove(6, 3, 6, 6);
    assertEquals(true, bps.bps1.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsDraw() {
    initData();
    bps.bps1.startGame(decks.lastPlayableDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.removeUsingDraw(0, 6, 2);
    bps.bps1.removeUsingDraw(0, 5, 2);
    bps.bps1.remove(6, 4);
    for (int i = 0; i < 21; i++) {
      bps.bps1.discardDraw(0);
    }
    assertEquals(false, bps.bps1.isGameOver());
    bps.bps1.removeUsingDraw(0, 6, 0);
    assertEquals(true, bps.bps1.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsKing() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    bps.bps1.remove(6, 3, 6, 6);
    bps.bps1.removeUsingDraw(0, 6, 2);
    bps.bps1.removeUsingDraw(0, 5, 2);
    for (int i = 0; i < 22; i++) {
      bps.bps1.discardDraw(0);
    }
    assertEquals(false, bps.bps1.isGameOver());
    bps.bps1.remove(6, 4);
    assertEquals(true, bps.bps1.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void isGameOverNotStarted() {
    initData();
    bps.bps1.isGameOver();
  }

  @Test
  public void getScore() {
    initData();
    bps.bps1.startGame(decks.assignmentDeck, false, 7, 1);
    assertEquals(183, bps.bps1.getScore());
    bps.bps1.remove(6, 3, 6, 6);
    assertEquals(170, bps.bps1.getScore());
    bps.bps1.removeUsingDraw(0, 6, 2);
    assertEquals(161, bps.bps1.getScore());
    bps.bps1.removeUsingDraw(0, 5, 2);
    assertEquals(156, bps.bps1.getScore());
    bps.bps1.remove(6, 4);
    assertEquals(143, bps.bps1.getScore());

  }

  @Test(expected = IllegalStateException.class)
  public void getScoreNotStarted() {
    initData();
    bps.bps1.getScore();
  }

  @Test
  public void getCardAt() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    ArrayList<SolitaireCard> expected = new ArrayList<SolitaireCard>(
            Arrays.asList(cards.clubAce, cards.diamondAce,
                    cards.spadeAce, cards.heartAce, cards.clubTwo, cards.diamondTwo,
                    cards.spadeTwo, cards.heartTwo, cards.clubThree, cards.diamondThree,
                    cards.spadeThree, cards.heartThree, cards.clubFour, cards.diamondFour,
                    cards.spadeFour, cards.heartFour, cards.clubFive, cards.diamondFive,
                    cards.spadeFive, cards.heartFive, cards.clubSix, cards.diamondSix,
                    cards.spadeSix, cards.heartSix, cards.clubSeven, cards.diamondSeven,
                    cards.spadeSeven, cards.heartSeven));
    int counter = 0;
    for (int i = 0; i < bps.bps1.getNumRows(); i++) {
      for (int j = 0; j < bps.bps1.getRowWidth(i); j++) {
        assertEquals(expected.get(counter), bps.bps1.getCardAt(i, j));
        counter++;
      }
    }
  }

  @Test(expected = IllegalStateException.class)
  public void getCardAtNotStarted() {
    initData();
    bps.bps1.getCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadRowLow() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.getCardAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadRowHigh() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.getCardAt(10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadCardLow() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.getCardAt(3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadCardHigh() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 7, 1);
    bps.bps1.getCardAt(4, 7);
  }


  @Test
  public void getDrawCards() {
    initData();
    bps.bps1.startGame(decks.deck1, false, 1, 51);
    decks.deck1.remove(0);
    for (int i = 0; i < 51; i++) {
      assertEquals(decks.deck1.get(i), bps.bps1.getDrawCards().get(i));
    }
  }

  @Test(expected = IllegalStateException.class)
  public void getDrawCardsNotStarted() {
    initData();
    bps.bps1.getDrawCards();
  }
}