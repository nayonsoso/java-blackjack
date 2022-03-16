package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayStatusTest {

    @ParameterizedTest
    @CsvSource(value = {"21:HIT", "22:BUST"}, delimiter = ':')
    @DisplayName("카드 목록의 합에 따라 HIT이나 BUST를 반환한다.")
    void hitOrBust(int sum, PlayStatus expected) {
        assertThat(PlayStatus.hitOrBust(sum)).isEqualTo(expected);
    }
}