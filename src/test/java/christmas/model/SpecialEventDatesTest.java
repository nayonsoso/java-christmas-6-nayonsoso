package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialEventDatesTest {
    @DisplayName("12월의 일요일과 크리스마스에 대해 contains() 가 true 리턴")
    @ValueSource(ints = {3, 10, 17, 24, 31, 25})
    @ParameterizedTest
    void specialEventDateTestContaining(int date) {
        SpecialEventDates specialEventDates = new SpecialEventDates();
        assertThat(specialEventDates.contains(date)).isTrue();
    }

    @DisplayName("이외의 날짜에 대해 contains() 가 false 리턴")
    @ValueSource(ints = {4, 11, 18, 23, 30})
    @ParameterizedTest
    void specialEventDateTestNotContaining(int date) {
        SpecialEventDates specialEventDates = new SpecialEventDates();
        assertThat(specialEventDates.contains(date)).isFalse();
    }
}