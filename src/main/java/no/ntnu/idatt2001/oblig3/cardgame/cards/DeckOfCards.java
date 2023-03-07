package no.ntnu.idatt2001.oblig3.cardgame.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a deck of playing cards.
 *
 * @author Svein Kåre Sørestad
 * @version 1.0
 */
public class DeckOfCards {
  private List<PlayingCard> deck;
  private final Random randomizer = new Random();

  /**
   * Constructor. Creates a new deck and puts all 52 cards in a card deck in it.
   */
  public DeckOfCards() {
    deck = new ArrayList<>();
    shuffleDeck();
  }

  /**
   * Resets the deck.
   */
  private void shuffleDeck() {
    deck = new ArrayList<>();
    char[] suits = {'S', 'H', 'D', 'C'};
    for (char suit : suits) {
      for (int j = 1; j <= 13; j++) {
        deck.add(new PlayingCard(suit, j));
      }
    }
  }

  /**
   * Deals a random card hand.
   *
   * @param n (int) The amount of cards to be dealt.
   * @return        A randomly dealt card hand.
   */
  public CardHand dealHand(int n) {
    if (n < 1) {
      throw new IllegalArgumentException("Parameter cannot be less than 0.");
    }
    shuffleDeck();
    List<PlayingCard> hand = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int index = randomizer.nextInt(deck.size());
      hand.add(deck.get(index));
      deck.remove(index);
    }
    return new CardHand(hand);
  }
}
