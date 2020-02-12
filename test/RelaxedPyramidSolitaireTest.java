import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Class for testing the RelaxedPyramidSolitaire class.
 */
public class RelaxedPyramidSolitaireTest {
  ExampleDecks decks;
  RelaxedPyramidSolitaire rps;
  CardExamples cards;

  /**
   * Resets data when running tests on the same examples.
   */
  @Before
  public void initData() {
    this.decks = new ExampleDecks();
    this.rps = new RelaxedPyramidSolitaire();
    this.cards = new CardExamples();
  }

  @Test
  public void removeLeftCorrect() {
    rps.startGame(decks.relaxedDeckLeft, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubAce, rps.getCardAt(0, 0));
    assertEquals(cards.heartQueen, rps.getCardAt(1, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 1));
    rps.remove(1, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(null, rps.getCardAt(1, 1));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    assertEquals(false, rps.isGameOver());
    rps.remove(0, 0, 1, 0);
    assertEquals(true, rps.isGameOver());
    assertEquals(null, rps.getCardAt(0, 0));
    assertEquals(null, rps.getCardAt(1, 0));
  }

  @Test
  public void removeLeftCorrectCoveredSecond() {
    rps.startGame(decks.relaxedDeckLeft, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubAce, rps.getCardAt(0, 0));
    assertEquals(cards.heartQueen, rps.getCardAt(1, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 1));
    rps.remove(1, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(null, rps.getCardAt(1, 1));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    assertEquals(false, rps.isGameOver());
    rps.remove(1, 0, 0, 0);
    assertEquals(true, rps.isGameOver());
    assertEquals(null, rps.getCardAt(0, 0));
    assertEquals(null, rps.getCardAt(1, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeLeftCovered() {
    rps.startGame(decks.relaxedDeckLeft, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubAce, rps.getCardAt(0, 0));
    assertEquals(cards.heartQueen, rps.getCardAt(1, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 1));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    rps.remove(0, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeLeftNotThirteen() {
    rps.startGame(decks.relaxedDeckLeft, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubAce, rps.getCardAt(0, 0));
    assertEquals(cards.heartQueen, rps.getCardAt(1, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 1));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    rps.remove(0, 0, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeLeftWithNull() {
    rps.startGame(decks.relaxedDeckLeft, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubAce, rps.getCardAt(0, 0));
    assertEquals(cards.heartQueen, rps.getCardAt(1, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 1));
    rps.remove(1, 1);
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    rps.remove(0, 0, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeLeftBothCovered() {
    rps.startGame(decks.relaxedDeckLeft, false, 3, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubAce, rps.getCardAt(0, 0));
    assertEquals(cards.heartQueen, rps.getCardAt(1, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 1));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    rps.remove(0, 0, 1, 0);
  }

  @Test
  public void removeRightCorrect() {
    rps.startGame(decks.relaxedDeckRight, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubSeven, rps.getCardAt(0, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 0));
    assertEquals(cards.heartSix, rps.getCardAt(1, 1));
    rps.remove(1, 0);
    assertEquals(false, rps.isGameOver());
    assertEquals(null, rps.getCardAt(1, 0));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    assertEquals(false, rps.isGameOver());
    rps.remove(0, 0, 1, 1);
    assertEquals(true, rps.isGameOver());
    assertEquals(null, rps.getCardAt(0, 0));
    assertEquals(null, rps.getCardAt(1, 0));
  }

  @Test
  public void removeRightCorrectCoveredSecond() {
    rps.startGame(decks.relaxedDeckRight, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubSeven, rps.getCardAt(0, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 0));
    assertEquals(cards.heartSix, rps.getCardAt(1, 1));
    rps.remove(1, 0);
    assertEquals(false, rps.isGameOver());
    assertEquals(null, rps.getCardAt(1, 0));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    assertEquals(false, rps.isGameOver());
    rps.remove(1, 1, 0, 0);
    assertEquals(true, rps.isGameOver());
    assertEquals(null, rps.getCardAt(0, 0));
    assertEquals(null, rps.getCardAt(1, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeRightCovered() {
    rps.startGame(decks.relaxedDeckRight, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubSeven, rps.getCardAt(0, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 0));
    assertEquals(cards.heartSix, rps.getCardAt(1, 1));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    rps.remove(0, 0, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeRightNotThirteen() {
    rps.startGame(decks.relaxedDeckRight, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubSeven, rps.getCardAt(0, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 0));
    assertEquals(cards.heartSix, rps.getCardAt(1, 1));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    rps.remove(0, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeRightWithNull() {
    rps.startGame(decks.relaxedDeckRight, false, 2, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubSeven, rps.getCardAt(0, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 0));
    assertEquals(cards.heartSix, rps.getCardAt(1, 1));
    rps.remove(1, 0);
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    rps.remove(0, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeRightBothCovered() {
    rps.startGame(decks.relaxedDeckLeft, false, 3, 1);
    assertEquals(false, rps.isGameOver());
    assertEquals(cards.clubAce, rps.getCardAt(0, 0));
    assertEquals(cards.heartQueen, rps.getCardAt(1, 0));
    assertEquals(cards.clubKing, rps.getCardAt(1, 1));
    for (int i = 0; i < 49; i++) {
      rps.discardDraw(0);
    }
    rps.remove(0, 0, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeVeryFar() {
    rps.startGame(decks.deck1, false, 3, 1);
    rps.remove(8, 0, 3, 2);
  }

  @Test
  public void getDeck() {
    assertEquals(this.decks.getDeckDeck, rps.getDeck());
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    assertEquals(this.decks.getDeckDeck, rps.getDeck());
    for (int i = 0; i < 5; i++) {
      rps.discardDraw(0);
    }
    assertEquals(this.decks.getDeckDeck, rps.getDeck());
    rps.remove(6, 3, 6, 6);
    assertEquals(this.decks.getDeckDeck, rps.getDeck());
  }

  @Test
  public void startGamePyramid() {
    //check for correct creation of all legal pyramid sizes
    for (int n = 1; n <= 9; n++) {
      rps.startGame(decks.deck1, false, n, 1);
      int pyramidCounter = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < rps.getRowWidth(i); j++) {
          pyramidCounter++;
        }
      }
      assertEquals(n * (n + 1) / 2, pyramidCounter);
    }
  }

  @Test
  public void startGameDraw() {
    //check for correct creation of all legal draw piles
    for (int n = 0; n < 51; n++) {
      rps.startGame(decks.deck1, false, 1, n);
      assertEquals(n, rps.getDrawCards().size());
    }
  }

  @Test
  public void startGameDeck() {
    //count pyramid and prepare deck for counting
    for (int n = 1; n <= 9; n++) {
      rps.startGame(decks.deck1, false, n, 1);
      int pyramidCounter = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < rps.getRowWidth(i); j++) {
          pyramidCounter++;
        }
      }
      int nonDeckCards = pyramidCounter + 1;
      rps.discardDraw(0);

      //count deck size
      int deckCounter = 0;
      while (rps.getDrawCards().get(0) != null) {
        rps.discardDraw(0);
        deckCounter++;
      }
      assertEquals(52 - nonDeckCards, deckCounter);
    }
  }

  @Test
  public void startGameShuffleValidDeck() {
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
    ArrayList<SolitaireCard> badDeck = new ArrayList<SolitaireCard>();
    for (int i = 0; i < 26; i++) {
      badDeck.add(decks.deck1.get(i));
    }
    rps.startGame(badDeck, false, 5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameRepeatingDeckException() {
    ArrayList<SolitaireCard> badDeck = new ArrayList<SolitaireCard>();
    for (int i = 0; i < 52; i++) {
      badDeck.add(decks.deck1.get(0));
    }
    rps.startGame(badDeck, false, 5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsNegException() {
    rps.startGame(decks.deck1, false, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsZeroException1() {
    rps.startGame(decks.deck1, false, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsLargeException() {
    rps.startGame(decks.deck1, false, 20, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumRowsNineException() {
    rps.startGame(decks.deck1, false, 9, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumDrawLow() {
    rps.startGame(decks.deck1, false, 5, -1);

  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumHighLow() {
    rps.startGame(decks.deck1, false, 5, 70);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameNumDrawOver52() {

    rps.startGame(decks.deck1, false, 9, 8);
  }

  @Test
  public void remove2Cards() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    rps.remove(6, 3, 6, 6);
    assertEquals(null, rps.getCardAt(6, 3));
    assertEquals(null, rps.getCardAt(6, 6));
  }

  @Test(expected = IllegalStateException.class)
  public void remove2CardsNotStarted() {
    rps.remove(0, 0, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNullRemoveCard1() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    rps.remove(6, 3, 6, 6);
    rps.remove(6, 3, 6, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNullRemoveCard2() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    rps.remove(6, 3, 6, 6);
    rps.remove(6, 0, 6, 6);
  }


  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(7, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds1() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(10, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds2() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(0, 3, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds3() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(0, -1, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds4() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(0, 0, -5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds5() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(0, 3, 7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds6() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(0, 3, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsBadBounds7() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(0, 3, 1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsIllegalMoveDeck1() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsIllegalMoveAssignDeck() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    rps.remove(6, 0, 6, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNonPlayableCard1() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(5, 6, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove2CardsNonPlayableCard2() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(6, 2, 1, 1);
  }


  @Test
  public void remove1Card() {
    rps.startGame(decks.kingRemoval, false, 2, 1);
    rps.remove(1, 1);
    assertEquals(null, rps.getCardAt(1, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void remove1CardNotStarted() {
    rps.remove(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalNullRemove() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    rps.remove(6, 3, 6, 6);
    rps.remove(6, 3, 6, 6);
  }


  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalMoveNine() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    rps.remove(6, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardIllegalMoveQueen() {
    rps.startGame(decks.deck1, false, 9, 1);
    rps.remove(8, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsLowRow() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighRow1() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighRow2() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsLowCard() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighCard1() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(5, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardBadBoundsHighCard2() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(6, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void remove1CardNonPlayableCard() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.remove(1, 2);
  }


  @Test
  public void removeUsingDraw() {
    rps.startGame(decks.assignmentDeck, false, 7, 2);
    rps.remove(6, 3, 6, 6);
    rps.removeUsingDraw(0, 6, 2);
    rps.removeUsingDraw(1, 5, 2);
    assertEquals(null, rps.getCardAt(6, 2));
    assertEquals(null, rps.getCardAt(5, 2));
    assertEquals(rps.getDrawCards().get(0), cards.heartFive);
    assertEquals(rps.getDrawCards().get(1), cards.heartThree);
  }

  @Test(expected = IllegalStateException.class)
  public void removeUsingDrawGameNotStarted() {
    rps.removeUsingDraw(0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNullCard() {
    rps.startGame(decks.assignmentDeck, false, 7, 2);
    rps.removeUsingDraw(0, 6, 2);
    rps.removeUsingDraw(1, 6, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNullDraw() {
    rps.startGame(decks.deck1, false, 7, 1);
    for (int i = 0; i < 24; i++) {
      rps.discardDraw(0);
    }
    rps.removeUsingDraw(0, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowDraw() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.removeUsingDraw(-1, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighDraw() {
    rps.startGame(decks.deck1, false, 7, 3);
    rps.removeUsingDraw(5, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowRow() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.removeUsingDraw(0, -1, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighRow() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.removeUsingDraw(0, 17, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawLowCard() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.removeUsingDraw(0, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawHighCard() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.removeUsingDraw(0, 2, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingBadMove() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.removeUsingDraw(0, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeUsingDrawNonPlayable() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.removeUsingDraw(0, 6, 2);
    rps.removeUsingDraw(0, 2, 1);
  }

  @Test
  public void discardDraw() {
    rps.startGame(decks.assignmentDeck, false, 7, 3);
    for (int i = 0; i < 21; i++) {
      SolitaireCard c = rps.getDrawCards().get(0);
      rps.discardDraw(0);
      assertNotEquals(c, rps.getDrawCards().get(0));
      assertEquals(3, rps.getDrawCards().size());
    }
    rps.discardDraw(0);
    assertEquals(null, rps.getDrawCards().get(0));
    assertEquals(3, rps.getDrawCards().size());
    rps.discardDraw(1);
    assertEquals(null, rps.getDrawCards().get(1));
    assertEquals(3, rps.getDrawCards().size());
    rps.discardDraw(2);
    assertEquals(null, rps.getDrawCards().get(2));
    assertEquals(3, rps.getDrawCards().size());

  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawLowDrawNum1() {
    rps.startGame(decks.assignmentDeck, false, 7, 3);
    rps.discardDraw(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawLowDrawNum2() {
    rps.startGame(decks.assignmentDeck, false, 7, 3);
    rps.discardDraw(-120);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawHighDrawNum1() {
    rps.startGame(decks.assignmentDeck, false, 7, 3);
    rps.discardDraw(3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawHighDrawNum2() {
    rps.startGame(decks.assignmentDeck, false, 7, 3);
    rps.discardDraw(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void discardDrawDrawNull() {
    rps.startGame(decks.assignmentDeck, false, 7, 3);
    for (int i = 0; i < 21; i++) {
      SolitaireCard c = rps.getDrawCards().get(0);
      rps.discardDraw(0);
      assertNotEquals(c, rps.getDrawCards().get(0));
      assertEquals(3, rps.getDrawCards().size());
    }
    rps.discardDraw(0);
    assertEquals(null, rps.getDrawCards().get(0));
    rps.discardDraw(0);
  }

  @Test(expected = IllegalStateException.class)
  public void discardDrawNotStarted() {
    rps.discardDraw(0);
  }

  @Test
  public void getNumRows() {
    for (int i = 1; i < 9; i++) {
      rps.startGame(decks.deck1, false, i, 1);
      assertEquals(i, rps.getNumRows());
    }
  }

  @Test
  public void getNumRowsNotStarted() {
    assertEquals(-1, rps.getNumRows());
  }

  @Test
  public void getNumDraw() {
    rps.startGame(decks.deck1, false, 9, 6);
    assertEquals(6, rps.getNumDraw());
    rps.discardDraw(0);
    assertEquals(6, rps.getNumDraw());
    for (int i = 0; i < 6; i++) {
      assertEquals(6 - i, rps.getNumDraw());
      rps.discardDraw(i);
    }
    assertEquals(0, rps.getNumDraw());
  }

  @Test
  public void getNumDrawNotStarted() {
    assertEquals(-1, rps.getNumDraw());
  }

  @Test
  public void getRowWidth() {
    rps.startGame(decks.deck1, false, 9, 1);
    for (int i = 0; i < 9; i++) {
      assertEquals(i + 1, rps.getRowWidth(i));
    }
  }

  @Test(expected = IllegalStateException.class)
  public void getRowWidthNotStarted() {
    rps.getRowWidth(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getRowWidthBadRowLow() {
    rps.startGame(decks.deck1, false, 2, 1);
    rps.getRowWidth(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getRowWidthBadRowHigh() {
    rps.startGame(decks.deck1, false, 2, 1);
    rps.getRowWidth(2);
  }

  @Test
  public void isGameOverYesWin() {
    rps.startGame(decks.winDeck, false, 1, 1);
    for (int i = 0; i < 50; i++) {
      rps.discardDraw(0);
    }
    rps.removeUsingDraw(0, 0, 0);
    assertEquals(true, rps.isGameOver());
  }

  @Test
  public void isGameOverYesLoss() {
    rps.startGame(decks.deck1, false, 9, 1);
    for (int i = 0; i < 7; i++) {
      rps.discardDraw(0);
    }
    for (SolitaireCard c : rps.getDrawCards()) {
      assertEquals(null, c);
    }
    assertEquals(true, rps.isGameOver());
  }

  @Test
  public void isGameOverStillDrawCards() {
    rps.startGame(decks.lastPlayableDeck, false, 7, 1);
    rps.remove(6, 3, 6, 6);
    rps.removeUsingDraw(0, 6, 2);
    rps.removeUsingDraw(0, 5, 2);
    rps.remove(6, 4);
    for (int i = 0; i < 21; i++) {
      rps.discardDraw(0);
    }
    assertEquals(false, rps.isGameOver());
    rps.discardDraw(0);
    assertEquals(true, rps.isGameOver());
  }

  @Test
  public void isGameOverStillDeckCards() {
    rps.startGame(decks.winNoDrawDeck, false, 2, 0);
    rps.remove(1, 0);
    rps.remove(1, 1);
    rps.remove(0, 0);
    assertEquals(true, rps.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsPyramid() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    rps.remove(6, 4);
    for (int i = 0; i < 24; i++) {
      rps.discardDraw(0);
    }
    assertEquals(false, rps.isGameOver());
    rps.remove(6, 3, 6, 6);
    assertEquals(true, rps.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsDraw() {
    rps.startGame(decks.lastPlayableDeck, false, 7, 1);
    rps.remove(6, 3, 6, 6);
    rps.removeUsingDraw(0, 6, 2);
    rps.removeUsingDraw(0, 5, 2);
    rps.remove(6, 4);
    for (int i = 0; i < 21; i++) {
      rps.discardDraw(0);
    }
    assertEquals(false, rps.isGameOver());
    rps.removeUsingDraw(0, 6, 0);
    rps.remove(6, 1, 5, 0);
    assertTrue(rps.isGameOver());
  }

  @Test
  public void isGameOverPlayableCardsKing() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    rps.remove(6, 3, 6, 6);
    rps.removeUsingDraw(0, 6, 2);
    rps.removeUsingDraw(0, 5, 2);
    for (int i = 0; i < 22; i++) {
      rps.discardDraw(0);
    }
    assertEquals(false, rps.isGameOver());
    rps.remove(6, 4);
    assertEquals(true, rps.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void isGameOverNotStarted() {
    rps.isGameOver();
  }

  @Test
  public void getScore() {
    rps.startGame(decks.assignmentDeck, false, 7, 1);
    assertEquals(183, rps.getScore());
    rps.remove(6, 3, 6, 6);
    assertEquals(170, rps.getScore());
    rps.removeUsingDraw(0, 6, 2);
    assertEquals(161, rps.getScore());
    rps.removeUsingDraw(0, 5, 2);
    assertEquals(156, rps.getScore());
    rps.remove(6, 4);
    assertEquals(143, rps.getScore());

  }

  @Test(expected = IllegalStateException.class)
  public void getScoreNotStarted() {
    rps.getScore();
  }

  @Test
  public void getCardAt() {
    rps.startGame(decks.deck1, false, 7, 1);
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
    for (int i = 0; i < rps.getNumRows(); i++) {
      for (int j = 0; j < rps.getRowWidth(i); j++) {
        assertEquals(expected.get(counter), rps.getCardAt(i, j));
        counter++;
      }
    }
  }

  @Test(expected = IllegalStateException.class)
  public void getCardAtNotStarted() {
    rps.getCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadRowLow() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.getCardAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadRowHigh() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.getCardAt(10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadCardLow() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.getCardAt(3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardBadCardHigh() {
    rps.startGame(decks.deck1, false, 7, 1);
    rps.getCardAt(4, 7);
  }


  @Test
  public void getDrawCards() {
    rps.startGame(decks.deck1, false, 1, 51);
    decks.deck1.remove(0);
    for (int i = 0; i < 51; i++) {
      assertEquals(decks.deck1.get(i), rps.getDrawCards().get(i));
    }
  }

  @Test(expected = IllegalStateException.class)
  public void getDrawCardsNotStarted() {
    rps.getDrawCards();
  }
}