import no.ntnu.idatt2001.oblig3.cardgame.cards.PlayingCard;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayingCardTest {
  @Nested
  class MethodsAcceptValidParameters {
    @Test
    public void constructorCreatesNonNullObject() {
      PlayingCard test = new PlayingCard('D', 3);
      assertNotNull(test);
    }
  }

  @Nested
  class GetMethodsWorkingAsIntended {
    @Test
    public void getSuitReturnsCorrectSuit() {
      PlayingCard test = new PlayingCard('D', 5);
      assertEquals('D', test.getSuit());
    }

    @Test
    public void getFaceReturnsCorrectFace() {
      PlayingCard test = new PlayingCard('D', 5);
      assertEquals(5, test.getFace());
    }
  }
}
