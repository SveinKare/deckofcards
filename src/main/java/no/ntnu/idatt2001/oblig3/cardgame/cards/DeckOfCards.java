package no.ntnu.idatt2001.oblig3.cardgame.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {
  private final List<PlayingCard> deck;
  private final Random randomizer = new Random();

  public DeckOfCards() {
    deck = new ArrayList<>();

    char[] suits = {'S', 'H', 'D', 'C'};
    for (char suit : suits) {
      for (int j = 1; j <= 13; j++) {
        deck.add(new PlayingCard(suit, j));
      }
    }
  }

  public CardHand dealHand(int n) {
    List<PlayingCard> hand = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      hand.add(deck.get(randomizer.nextInt(52)));
    }
    return new CardHand(hand);
  }
}
