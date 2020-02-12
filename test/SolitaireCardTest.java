import org.junit.Test;

import cs3500.pyramidsolitaire.model.hw02.CardFace;
import cs3500.pyramidsolitaire.model.hw02.CardSuit;
import cs3500.pyramidsolitaire.model.hw02.SolitaireCard;

import static junit.framework.TestCase.assertEquals;


/**
 * Class to hold tests for methods in the SolitaireCard class.
 */
public class SolitaireCardTest {
  CardExamples cards = new CardExamples();

  @Test
  public void testToStringAces() {
    assertEquals("A♣", cards.clubAce.toString());
    assertEquals("A♠", cards.spadeAce.toString());
    assertEquals("A♥", cards.heartAce.toString());
    assertEquals("A♦", cards.diamondAce.toString());
  }

  @Test
  public void testToStringTwos() {
    assertEquals("2♣", cards.clubTwo.toString());
    assertEquals("2♠", cards.spadeTwo.toString());
    assertEquals("2♥", cards.heartTwo.toString());
    assertEquals("2♦", cards.diamondTwo.toString());
  }

  @Test
  public void testToStringThrees() {
    assertEquals("3♣", cards.clubThree.toString());
    assertEquals("3♠", cards.spadeThree.toString());
    assertEquals("3♥", cards.heartThree.toString());
    assertEquals("3♦", cards.diamondThree.toString());
  }

  @Test
  public void testToStringFours() {
    assertEquals("4♣", cards.clubFour.toString());
    assertEquals("4♠", cards.spadeFour.toString());
    assertEquals("4♥", cards.heartFour.toString());
    assertEquals("4♦", cards.diamondFour.toString());
  }

  @Test
  public void testToStringFives() {
    assertEquals("5♣", cards.clubFive.toString());
    assertEquals("5♠", cards.spadeFive.toString());
    assertEquals("5♥", cards.heartFive.toString());
    assertEquals("5♦", cards.diamondFive.toString());
  }

  @Test
  public void testToStringSix() {
    assertEquals("6♣", cards.clubSix.toString());
    assertEquals("6♠", cards.spadeSix.toString());
    assertEquals("6♥", cards.heartSix.toString());
    assertEquals("6♦", cards.diamondSix.toString());
  }

  @Test
  public void testToStringSeven() {
    assertEquals("7♣", cards.clubSeven.toString());
    assertEquals("7♠", cards.spadeSeven.toString());
    assertEquals("7♥", cards.heartSeven.toString());
    assertEquals("7♦", cards.diamondSeven.toString());
  }

  @Test
  public void testToStringEight() {
    assertEquals("8♣", cards.clubEight.toString());
    assertEquals("8♠", cards.spadeEight.toString());
    assertEquals("8♥", cards.heartEight.toString());
    assertEquals("8♦", cards.diamondEight.toString());
  }

  @Test
  public void testToStringNine() {
    assertEquals("9♣", cards.clubNine.toString());
    assertEquals("9♠", cards.spadeNine.toString());
    assertEquals("9♥", cards.heartNine.toString());
    assertEquals("9♦", cards.diamondNine.toString());
  }

  @Test
  public void testToStringTen() {
    assertEquals("10♣", cards.clubTen.toString());
    assertEquals("10♠", cards.spadeTen.toString());
    assertEquals("10♥", cards.heartTen.toString());
    assertEquals("10♦", cards.diamondTen.toString());
  }

  @Test
  public void testToStringJack() {
    assertEquals("J♣", cards.clubJack.toString());
    assertEquals("J♠", cards.spadeJack.toString());
    assertEquals("J♥", cards.heartJack.toString());
    assertEquals("J♦", cards.diamondJack.toString());
  }

  @Test
  public void testToStringQueen() {
    assertEquals("Q♣", cards.clubQueen.toString());
    assertEquals("Q♠", cards.spadeQueen.toString());
    assertEquals("Q♥", cards.heartQueen.toString());
    assertEquals("Q♦", cards.diamondQueen.toString());
  }

  @Test
  public void testToStringKing() {
    assertEquals("K♣", cards.clubKing.toString());
    assertEquals("K♠", cards.spadeKing.toString());
    assertEquals("K♥", cards.heartKing.toString());
    assertEquals("K♦", cards.diamondKing.toString());
  }


  @Test
  public void testEqualsTrue() {
    assertEquals(true, cards.clubAce.equals((cards.clubAce)));
    assertEquals(true, cards.spadeNine.equals(new SolitaireCard(CardFace.Nine, CardSuit.Spade)));
    SolitaireCard secondDiamondThree = new SolitaireCard(CardFace.Three, CardSuit.Diamond);
    assertEquals(true, secondDiamondThree.equals(cards.diamondThree));
  }

  @Test
  public void testEqualsFalse() {
    assertEquals(false, cards.clubAce.equals((cards.spadeKing)));
    assertEquals(false, cards.spadeNine.equals(new SolitaireCard(CardFace.Eight, CardSuit.Spade)));
    assertEquals(false, cards.spadeNine.equals(new SolitaireCard(CardFace.Nine, CardSuit.Club)));
    SolitaireCard secondDiamondThree = new SolitaireCard(CardFace.Three, CardSuit.Diamond);
    assertEquals(false, secondDiamondThree.equals(cards.clubFour));
  }

  @Test
  public void testHashCode() {
    assertEquals(true, cards.clubEight.hashCode() == cards.clubEight.hashCode());
    SolitaireCard secondDiamondThree = new SolitaireCard(CardFace.Three, CardSuit.Diamond);
    assertEquals(true, secondDiamondThree.hashCode() == cards.diamondThree.hashCode());
  }
}