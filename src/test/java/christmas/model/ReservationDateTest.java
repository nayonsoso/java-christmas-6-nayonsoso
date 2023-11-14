package christmas.model;

import christmas.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationDateTest {
    @DisplayName("날짜 저장 정상 작동 테스트")
    @ValueSource(ints = {1,15,31})
    @ParameterizedTest
    void reservationDateTest(int date) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.getDate()).isEqualTo(date);
    }

    @DisplayName("날짜 범위를 벗어난 경우 에러 발생")
    @ValueSource(ints = {-1, 0, 32})
    @ParameterizedTest
    void validateDateTest(int date) {
        assertThatThrownBy(() -> new ReservationDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DATE_ERROR);
    }

    @DisplayName("크리스마스 디데이 이벤트 해당시 IsDuringDDayEvent 에 true 저장됨")
    @ValueSource(ints = {1, 10, 25})
    @ParameterizedTest
    void checkDuringDDayEventTestExpectingTrue(int date) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.getIsDuringDDayEvent()).isTrue();
    }

    @DisplayName("크리스마스 디데이 이벤트 해당되지 않을시 IsDuringDDayEvent 에 false 저장됨")
    @ValueSource(ints = {26, 29, 31})
    @ParameterizedTest
    void checkDuringDDayEventTestExpectingFalse(int date) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.getIsDuringDDayEvent()).isFalse();
    }

    @DisplayName("주말일 경우 IsWeekend 에 true 저장됨")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @ParameterizedTest
    void checkWeekendTestExpectingTrue(int date) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.getIsWeekend()).isTrue();
    }

    @DisplayName("주말 아닐 경우 IsWeekend 에 false 저장됨")
    @ValueSource(ints = {3, 7, 10})
    @ParameterizedTest
    void checkWeekendTestExpectingFalse(int date) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.getIsWeekend()).isFalse();
    }
}