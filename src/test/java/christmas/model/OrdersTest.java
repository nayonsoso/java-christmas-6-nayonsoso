package christmas.model;

import christmas.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {
    @DisplayName("잘못된 형식의 주문 들어올 경우 에러 발생")
    @ValueSource(strings = {"가", "-1", "가-1,", "가--1", "가-1,나-"})
    @ParameterizedTest
    void validateFormatTest(String userInput) {
        assertThatThrownBy(() -> new Orders(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_ERROR);
    }
}