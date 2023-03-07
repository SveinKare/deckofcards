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

  /**
   * Checks if the hand is a flush.
   *
   * @return {@code true} if it is a flush, {@code false} if not.
   */
  public boolean isHandFlush() {
    return hand.stream()
                   .map(PlayingCard::getSuit)
                   .distinct()
                   .toList().size() == 1;
  }

  /**
   * Checks if the hand has the queen of spades.
   *
   * @return {@code true} if the queen of spades is in the hand, {@code false} if not.
   */
  public boolean isQueenOfSpadesInHand() {
    return hand.stream()
            .anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
  }

  /**
   * Sums up the faces of all the cards in the hand. Ace is counted as 1.
   *
   * @return The sum of all the cards in the hand.
   */
  public int sumCards() {
    return hand.stream()
            .map(PlayingCard::getFace)
            .reduce(0, Integer::sum);
  }

  /**
   * Gets a list of all the cards of the suit hearts currently in the hand.
   *
   * @return A list of all the cards of the suit hearts.
   */
  public String getAllHeartCards() {
    List<PlayingCard> hearts = hand.stream()
            .filter(card -> card.getSuit() == 'H')
            .toList();
    return hearts.isEmpty() ? "No hearts" : hearts.stream()
            .map(card -> card.getAsString() + " ")
            .reduce("", String::concat);
  }
}
