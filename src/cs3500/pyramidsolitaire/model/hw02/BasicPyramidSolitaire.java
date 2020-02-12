package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Model for a game of Pyramid Solitaire using SolitaireCards. This model uses a single deck to
 * play, allows for the deck to be used only once, and keeps score based on the value of cards
 * remaining in the pyramid.
 */
public class BasicPyramidSolitaire implements PyramidSolitaireModel<SolitaireCard> {
  protected List<List<SolitaireCard>> gamePyramid;
  protected List<SolitaireCard> gameDeck;
  protected List<SolitaireCard> gameDraw;
  protected boolean gameStarted;
  protected Random randGenerator;

  /**
   * The default constructor for a game of pyramid solitaire.
   */
  public BasicPyramidSolitaire() {
    this.gameStarted = false;
    this.randGenerator = new Random();
  }

  /**
   * A constructor where a Random object can be given to the game to test random events.
   *
   * @param rand The random object for generation pseudorandom numbers.
   */
  public BasicPyramidSolitaire(Random rand) {
    this.gameStarted = false;
    this.randGenerator = rand;
  }

  @Override
  public List<SolitaireCard> getDeck() {
    ArrayList<SolitaireCard> standardDeck = new ArrayList<SolitaireCard>();
    for (CardSuit s : CardSuit.values()) {
      for (CardFace f : CardFace.values()) {
        standardDeck.add(new SolitaireCard(f, s));
      }
    }
    return standardDeck;
  }

  @Override
  public void startGame(List<SolitaireCard> deck, boolean shouldShuffle, int numRows, int numDraw) {
    //resets game from previous state.
    this.gamePyramid = null;
    this.gameDeck = null;
    this.gameDraw = null;
    this.gameStarted = false;


    if (deck == null || !validDeck(deck)) {
      throw new IllegalArgumentException("Given deck is not valid.");
    }
    if (!enoughCards(numRows, numDraw) || numDraw < 0 || numRows < 1) {
      throw new IllegalArgumentException("Given row and/or draw amounts are not valid.");
    }

    this.gameStarted = true;
    ArrayList<SolitaireCard> startingDeck = new ArrayList<SolitaireCard>();

    //shuffle deck if needed
    if (shouldShuffle) {
      startingDeck = this.shuffle(deck);
    } else {
      for (SolitaireCard c : deck) {
        startingDeck.add(c);
      }
    }

    //pyramid creation
    this.gamePyramid = createPyramid(startingDeck, numRows, numDraw);

    //draw creation
    this.gameDraw = new ArrayList<SolitaireCard>();
    for (int i = 0; i < numDraw; i++) {
      this.gameDraw.add(startingDeck.remove(0));
    }

    //deck creation
    this.gameDeck = startingDeck;

  }

  /**
   * Determines if the there are enough cards to play the game based on Pyramid size and number of
   * draw cards.
   *
   * @param numRows Number of rows for the Pyramid.
   * @param numDraw Number of draw cards.
   * @return If there are enough cards to play the game.
   */
  protected boolean enoughCards(int numRows, int numDraw) {
    return (numRows * (numRows + 1) / 2) + numDraw <= 52;
  }

  /**
   * Creates the pyramid for a game of BasicPyramidSolitaire.
   * @param startingDeck Deck to create the pyramid with.
   * @param numRows Number of rows
   * @param numDraw Number of draw cards
   * @return The Pyramid in as a List of Lists of SolitareCard
   */
  protected List<List<SolitaireCard>> createPyramid(ArrayList<SolitaireCard> startingDeck,
                                                    int numRows, int numDraw) {
    ArrayList<List<SolitaireCard>> gameBoard = new ArrayList<List<SolitaireCard>>();
    for (int i = 0; i < numRows; i++) {
      gameBoard.add(new ArrayList<SolitaireCard>());
      for (int j = 0; j <= i; j++) {
        gameBoard.get(i).add(startingDeck.remove(0));
      }
    }
    return gameBoard;
  }

  /**
   * Checks if the given deck of cards is set-wise equal to a known good deck of cards.
   *
   * @param checkDeck The deck of cards to be checked for validity
   * @return True if deck is valid, false otherwise.
   */
  protected boolean validDeck(List<SolitaireCard> checkDeck) {
    List<SolitaireCard> goodDeck = this.getDeck();
    return (goodDeck.containsAll(checkDeck) && checkDeck.containsAll(goodDeck)
            && checkDeck.size() == 52);
  }

  /**
   * Uses the given deck of cards as a starting point for shuffling a deck of cards. Does not mutate
   * given list of cards.
   *
   * @param deck The deck to be shuffled
   * @return A shuffled deck of playing cards
   */
  protected ArrayList<SolitaireCard> shuffle(List<SolitaireCard> deck) {
    ArrayList<SolitaireCard> shuffledDeck = new ArrayList<SolitaireCard>();
    ArrayList<SolitaireCard> mutableDeck = new ArrayList<SolitaireCard>();
    for (SolitaireCard c : deck) {
      mutableDeck.add(c);
    }
    for (int i = 0; i < deck.size(); i++) {
      int randCard = randGenerator.nextInt(deck.size() - i);
      shuffledDeck.add(mutableDeck.remove(randCard));
    }
    return shuffledDeck;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    checkValidCard(row1, card1);
    checkValidCard(row2, card2);
    if (this.gamePyramid.get(row1).get(card1).getValue()
            + this.gamePyramid.get(row2).get(card2).getValue() != 13) {
      throw new IllegalArgumentException("Given cards do not combine for legal move.");
    }
    ArrayList<SolitaireCard> playableCards = this.getPlayableCards();
    if (!playableCards.contains(this.gamePyramid.get(row1).get(card1))
            || !playableCards.contains(this.gamePyramid.get(row2).get(card2))) {
      throw new IllegalArgumentException("Given cards do not combine for legal move.");
    }

    this.gamePyramid.get(row1).set(card1, null);
    this.gamePyramid.get(row2).set(card2, null);
  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {
    checkValidCard(row, card);
    if (this.gamePyramid.get(row).get(card).getValue() != 13) {
      throw new IllegalArgumentException("Given card does not lead to legal move");
    }
    ArrayList<SolitaireCard> playableCards = this.getPlayableCards();
    if (!playableCards.contains(this.gamePyramid.get(row).get(card))) {
      throw new IllegalArgumentException("Given cards do not combine for legal move.");
    }

    this.gamePyramid.get(row).set(card, null);
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    checkValidCard(row, card);
    checkValidDraw(drawIndex);
    if (this.gamePyramid.get(row).get(card).getValue()
            + this.gameDraw.get(drawIndex).getValue() != 13) {
      throw new IllegalArgumentException("Given cards do not combine for legal move.");
    }
    ArrayList<SolitaireCard> playableCards = this.getPlayableCards();
    if (!playableCards.contains(this.gamePyramid.get(row).get(card))) {
      throw new IllegalArgumentException("Given cards do not combine for legal move.");
    }

    if (!this.gameDeck.isEmpty()) {
      this.gameDraw.set(drawIndex, this.gameDeck.remove(0));
    } else {
      this.gameDraw.set(drawIndex, null);
    }

    this.gamePyramid.get(row).set(card, null);

  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    checkValidDraw(drawIndex);

    this.gameDraw.remove(drawIndex);
    if (this.gameDeck.isEmpty()) {
      this.gameDraw.add(drawIndex, null);
    } else {
      this.gameDraw.add(drawIndex, this.gameDeck.remove(0));
    }
  }

  protected void checkValidCard(int row, int card) {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not been started");
    }
    if (row >= this.gamePyramid.size() || row < 0
            || card >= this.getRowWidth(row) || card < 0) {
      throw new IllegalArgumentException("Given arguments are not within array bounds");
    }
    if (this.gamePyramid.get(row).get(card) == null) {
      throw new IllegalArgumentException("One or more of given cards is null");
    }
  }

  private void checkValidDraw(int drawIndex) {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not been started");
    }
    if (drawIndex < 0 || drawIndex >= this.gameDraw.size()
            || this.gameDraw.get(drawIndex) == null) {
      throw new IllegalArgumentException("Given draw card index is invalid");
    }
    if (this.gameDraw.get(drawIndex) == null) {
      throw new IllegalArgumentException("Draw card is null");
    }
  }

  @Override
  public int getNumRows() {
    if (!gameStarted) {
      return -1;
    }
    int height = this.gamePyramid.size();
    return height;
  }

  @Override
  public int getNumDraw() {
    if (!this.gameStarted) {
      return -1;
    }
    int nonNullCards = 0;
    for (SolitaireCard c : this.gameDraw) {
      if (c != null) {
        nonNullCards++;
      }
    }
    return nonNullCards;
  }

  @Override
  public int getRowWidth(int row) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started");
    }
    if (row >= this.gamePyramid.size() || row < 0) {
      throw new IllegalArgumentException(String.format("The given row, %s, is invalid", row));
    }
    List<SolitaireCard> x = this.gamePyramid.get(row);
    int rowWidth = x.size();
    return rowWidth;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started");
    }

    //checks if pyramid is empty
    boolean emptyPyramid = true;
    for (List<SolitaireCard> ar : this.gamePyramid) {
      for (SolitaireCard c : ar) {
        if (c != null) {
          emptyPyramid = false;
          break;
        }
      }
    }
    if (emptyPyramid) {
      return true;
    }

    //checks if draw pile has cards
    boolean emptyDraw = true;
    for (SolitaireCard c : gameDraw) {
      if (c != null) {
        emptyDraw = false;
        break;
      }
    }

    //checks if deck has cards
    if (!this.gameDeck.isEmpty() || !emptyDraw) {
      return false;
    }

    //checks if pyramid has a playable pair or playable king
    return !this.checkPlayableCards();
  }

  protected boolean checkPlayableCards() {
    ArrayList<SolitaireCard> playableCards = this.getPlayableCards();
    for (int i = 0; i < playableCards.size(); i++) {
      if (playableCards.get(i).getValue() == 13) {
        return true;
      }
      for (int j = i + 1; j < playableCards.size(); j++) {
        if (playableCards.get(i).getValue() + playableCards.get(j).getValue() == 13
                || playableCards.get(j).getValue() == 13) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Finds which cards in the card pyramid are playable.
   *
   * @return The list of playable cards
   */
  protected ArrayList<SolitaireCard> getPlayableCards() {
    ArrayList<SolitaireCard> playableCards = new ArrayList<SolitaireCard>();

    playableCards.addAll(this.gameDeck);
    for (SolitaireCard c : gameDraw) {
      if (c != null) {
        playableCards.add(c);
        break;
      }
    }

    //finds all playable cards in the pyramid, except the bottom most row to avoid IndexOutOfBounds
    for (int i = 0; i < this.gamePyramid.size() - 1; i++) {
      for (int j = 0; j < this.gamePyramid.get(i).size(); j++) {
        if (gamePyramid.get(i).get(j) != null) {
          if (gamePyramid.get(i + 1).get(j) == null && gamePyramid.get(i + 1).get(j + 1) == null) {
            playableCards.add(gamePyramid.get(i).get(j));
          }
        }
      }
    }

    //finds all playable cards only in bottom row of pyramid.
    for (SolitaireCard c : this.gamePyramid.get(gamePyramid.size() - 1)) {
      if (c != null) {
        playableCards.add(c);
      }
    }
    return playableCards;
  }

  @Override
  public int getScore() throws IllegalStateException {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not been started");
    }

    int score = 0;

    for (List<SolitaireCard> ar : this.gamePyramid) {
      for (SolitaireCard c : ar) {
        if (c != null) {
          score += c.getValue();
        }
      }
    }

    return score;
  }

  @Override
  public SolitaireCard getCardAt(int row, int card) throws IllegalStateException {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not been started");
    }
    if (row < 0 || row >= this.getNumRows() || card < 0
            || card >= this.gamePyramid.get(row).size()) {
      throw new IllegalArgumentException("Given arguments are not in bounds");
    }

    return this.gamePyramid.get(row).get(card);
  }

  @Override
  public List<SolitaireCard> getDrawCards() throws IllegalStateException {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not been started");
    }
    ArrayList drawPile = new ArrayList<SolitaireCard>();
    //copies drawPile to avoid player mutation.
    for (SolitaireCard c : this.gameDraw) {
      // note that card cannot be mutated as all fields are final.
      drawPile.add(c);
    }

    return drawPile;
  }

}
