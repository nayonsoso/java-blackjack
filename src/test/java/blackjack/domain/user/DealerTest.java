package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;

class DealerTest {
	@Test
	void Dealer_InputDealerName_GenerateInstance() {
		Dealer dealer = new Dealer("dealer");

		assertThat(dealer).isInstanceOf(Dealer.class);
		assertThat(dealer).isInstanceOf(User.class);
	}

	@Test
	void valueOf_InputDealerNameAndCards_GenerateInstance() {
		List<Card> cards = Arrays.asList(
			new Card(Symbol.SEVEN, Type.CLUB),
			new Card(Symbol.TWO, Type.DIAMOND));

		assertThat(new Dealer("dealer", cards)).isInstanceOf(Dealer.class)
			.extracting("hand").isEqualTo(cards);
	}

	@Test
	void canDraw_CurrentScoreLowerThanDrawableMaxScore_ReturnTrue() {
		assertThat(new Dealer("dealer").canDraw()).isTrue();
	}

	@ParameterizedTest
	@MethodSource("provideUndrawableDealer")
	void canDraw_CurrentScoreMoreThanDrawableMaxScore_ReturnFalse(Dealer dealer) {
		assertThat(dealer.canDraw()).isFalse();
	}

	private static Stream<Arguments> provideUndrawableDealer() {
		final int WORST_CASE_OF_DRAWABLE_COUNT = 12;

		Dealer dealer = new Dealer("dealer");
		Deck deck = new Deck();

		for (int i = 0; i < WORST_CASE_OF_DRAWABLE_COUNT; i++) {
			dealer.draw(deck);
		}
		return Stream.of(Arguments.of(dealer));
	}
}