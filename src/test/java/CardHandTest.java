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
  class AccessorMethodsWorkingAsIntended {
    @Test
    public void getHandReturnsCorrectList() {
      List<PlayingCard> cards = new ArrayList<>();
      cards.add(new PlayingCard('D', 5));
      cards.add(new PlayingCard('S', 3));
      CardHand hand = new CardHand(cards);
      assertEquals(cards, hand.getHand());
    }

    @Test
    public void isHandFlushReturnsTrueWhenHandIsFlush() {
      List<PlayingCard> list = new ArrayList<>();
      list.add(new PlayingCard('D', 2));
      list.add(new PlayingCard('D', 3));
      list.add(new PlayingCard('D', 4));
      list.add(new PlayingCard('D', 5));
      list.add(new PlayingCard('D', 6));
      CardHand hand = new CardHand(list);
      assertTrue(hand.isHandFlush());
    }

    @Test
    public void isQueenOfSpadesInHandReturnsTrueWhenCardIsInHand() {
      List<PlayingCard> list = new ArrayList<>();
      list.add(new PlayingCard('S', 12));
      list.add(new PlayingCard('D', 3));
      list.add(new PlayingCard('D', 4));
      list.add(new PlayingCard('D', 5));
      list.add(new PlayingCard('D', 6));
      CardHand hand = new CardHand(list);
      assertTrue(hand.isQueenOfSpadesInHand());
    }

    @Test
    public void sumCardReturnsCorrectSum() {
      List<PlayingCard> list = new ArrayList<>();
      list.add(new PlayingCard('S', 12));
      list.add(new PlayingCard('D', 3));
      list.add(new PlayingCard('D', 4));
      list.add(new PlayingCard('D', 5));
      list.add(new PlayingCard('D', 6));
      CardHand hand = new CardHand(list);
      assertEquals(30, hand.sumCards());
    }

    @Test
    public void getAllHeartCardsReturnsCorrectCards() {
      List<PlayingCard> list = new ArrayList<>();

      list.add(new PlayingCard('H', 12));
      list.add(new PlayingCard('D', 3));
      list.add(new PlayingCard('H', 4));
      list.add(new PlayingCard('D', 5));
      list.add(new PlayingCard('D', 6));
      CardHand hand = new CardHand(list);
      assertEquals("H12 H4 ", hand.getAllHeartCards());
    }

    @Test
    public void getAllHeartsReturnsCorrectStringWhenNoHeartCardsArePresent() {
      List<PlayingCard> list = new ArrayList<>();
      list.add(new PlayingCard('D', 12));
      list.add(new PlayingCard('D', 3));
      list.add(new PlayingCard('D', 4));
      list.add(new PlayingCard('D', 5));
      list.add(new PlayingCard('D', 6));
      CardHand hand = new CardHand(list);
      assertEquals("No hearts", hand.getAllHeartCards());
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
