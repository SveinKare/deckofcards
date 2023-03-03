package no.ntnu.idatt2001.oblig3.cardgame.cards;

import java.util.List;

public class CardHand {
  private final List<PlayingCard> hand;

  public CardHand(List<PlayingCard> hand) {
    if (hand == null) {
      throw new IllegalArgumentException("Card hand cannot be null");
    }
    if (hand.isEmpty()) {
      throw new IllegalArgumentException("Card hand cannot be empty");
    }
    this.hand = hand;
  }

  public List<PlayingCard> getHand() {
    return hand;
  }
}
