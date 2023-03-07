package blackjack.model.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameTest {

    @ParameterizedTest
    @DisplayName("이름이 빈 문자일 경우 예외를 던진다.")
    @ValueSource(strings = {"", " "})
    void name_exception_if_blank(String input) {
        assertThatThrownBy(() -> new Name(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 빈 문자열일 수 없습니다.");

    }

}