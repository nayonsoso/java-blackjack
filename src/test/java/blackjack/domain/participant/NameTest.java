package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class NameTest {
    @Test
    @DisplayName("이름에 딜러가 들어가는지 테스트")
    void dealerNameFormatTest() {
        assertThatNoException().isThrownBy(() -> new Name("딜러"));
    }

    @Test
    @DisplayName("이름에 영문자가 들어가는지 테스트")
    void alphabetNameFormatTest() {
        assertThatNoException().isThrownBy(() -> new Name("AaBb"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "po bi", "세종대왕", "123", "!@#$%"})
    @DisplayName("이름에 영문자가 들어오지 않으면 예외를 던지는지 테스트")
    void throwExceptionWhenNameNotEnglish(final String name) {
        Assertions.assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("사람 이름은 영문자만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa", "aaa", "aaaa", "aaaaa"})
    @DisplayName("2글자 이상 5글자 이하인 이름이 들어가는지 테스트")
    void validLengthTest(final String name) {
        assertThatNoException().isThrownBy(() -> new Name(name));
    }

    @Test
    @DisplayName("2글자 미만의 이름이 들어오면 예외륻 던지는지 테스트")
    void throwExceptionWhenNameLengthLessTwo() {
        final String name = "a";

        Assertions.assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자 이름은 2글자 이상 5글자 이하만 가능합니다.");
    }

    @Test
    @DisplayName("5글자 초과의 이름이 들어오면 예외륻 던지는지 테스트")
    void throwExceptionWhenNameLengthGreaterFive() {
        final String name = "abcdef";

        Assertions.assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자 이름은 2글자 이상 5글자 이하만 가능합니다.");
    }
}
