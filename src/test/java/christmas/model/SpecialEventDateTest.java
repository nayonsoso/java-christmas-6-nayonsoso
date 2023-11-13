package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialEventDateTest {
    @DisplayName("12월의 일요일과 크리스마스 포함될 시 true 리턴 테스트")
    @ValueSource(ints = {3, 10, 17, 24, 31, 25})
    @ParameterizedTest
    void specialEventDateTestContaining(int date) {
        SpecialEventDate specialEventDate = new SpecialEventDate();
        assertThat(specialEventDate.contains(date)).isTrue();
    }

    @DisplayName("이외의 날짜 포함될 시 false 리턴 테스트")
    @ValueSource(ints = {4, 11, 18, 23, 30})
    @ParameterizedTest
    void specialEventDateTestNotContaining(int date) {
        SpecialEventDate specialEventDate = new SpecialEventDate();
        assertThat(specialEventDate.contains(date)).isFalse();
    }
}