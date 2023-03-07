import no.ntnu.idatt2001.oblig3.cardgame.cards.DeckOfCards;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckOfCardsTest {
  @Nested
  class MethodsAcceptValidParameters {
    @Test
    public void constructorCreatesNonNullObject() {
      DeckOfCards deck = new DeckOfCards();
      assertNotNull(deck);
    }

    @Test
    public void dealHandAcceptsIntegersGreaterThan0() {
      DeckOfCards deck = new DeckOfCards();
      assertDoesNotThrow(() -> deck.dealHand(5));
    }
  }

  @Nested
  class MethodsThrowCorrectExceptions {
    @Test
    public void dealHandThrowsExceptionWhenGivenParameterLowerThan1() {
      DeckOfCards deck = new DeckOfCards();
      assertThrows(IllegalArgumentException.class, () -> deck.dealHand(0));
    }
  }
}
