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


    @DisplayName("메뉴판에 없는 주문 들어올 경우 에러 발생")
    @ValueSource(strings = {"양송이스푸-1", "크리스마스파스타-2, 시저샐러두-1"})
    @ParameterizedTest
    void validateExistingMenuTest(String userInput) {
        assertThatThrownBy(() -> new Orders(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_ERROR);
    }
}