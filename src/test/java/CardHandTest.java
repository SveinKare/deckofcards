import no.ntnu.idatt2001.oblig3.cardgame.cards.CardHand;
import no.ntnu.idatt2001.oblig3.cardgame.cards.PlayingCard;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CardHandTest {
  @Nested
  class MethodsAcceptValidParameters {
    @Test
    public void constructorCreatesNonNullObject() {
      List<PlayingCard> cards = new ArrayList<>();
      cards.add(new PlayingCard('D', 5));
      cards.add(new PlayingCard('S', 3));
      CardHand hand = new CardHand(cards);
      assertNotNull(hand);
    }
  }
  @Nested
  class GetMethodWorkingAsIntended {
    @Test
    public void getHandReturnsCorrectList() {
      List<PlayingCard> cards = new ArrayList<>();
      cards.add(new PlayingCard('D', 5));
      cards.add(new PlayingCard('S', 3));
      CardHand hand = new CardHand(cards);
      assertEquals(cards, hand.getHand());
    }
  }

  @Nested
  class MethodsThrowCorrectExceptions {
    @Test
    public void constructorThrowsExceptionWhenGivenNullAsParameter() {
      assertThrows(IllegalArgumentException.class, () -> new CardHand(null));
    }

    @Test
    public void constructorThrowsExceptionWhenGivenEmptyListAsParameter() {
      List<PlayingCard> card = new ArrayList<>();
      assertThrows(IllegalArgumentException.class, () -> new CardHand(card));
    }
  }
}
