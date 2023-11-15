package christmas.model;

import christmas.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrdersTest {
    static Stream<Arguments> invalidOrders() throws Throwable {
        return Stream.of(
                Arguments.of("메뉴만", "양송이수프"),
                Arguments.of("숫자만", "-1"),
                Arguments.of("대쉬로 끝남", "양송이수프-"),
                Arguments.of("쉼표로 끝남", "양송이수프-1,"),
                Arguments.of("메뉴판에 없는 주문", "양송이수프프-1"),
                Arguments.of("최소 주문 수 미만 주문", "양송이수프-0"),
                Arguments.of("숫자가 아닌 수로 주문", "양송이수프-하나요"),
                Arguments.of("중복된 주문", "양송이스프-1, 양송이수프-2"),
                Arguments.of("음료만 주문", "제로콜라-1, 레드와인-2"),
                Arguments.of("최대 주문 수 초과 주문", "양송이수프-20, 레드와인-1")
        );
    }

    static Stream<Arguments> validOrders() throws Throwable {
        return Stream.of(
                Arguments.of("하나만", "양송이수프-10"),
                Arguments.of("둘 이상", "양송이수프-2, 레드와인-1, 시저샐러드-3"),
                Arguments.of("최소 주문", "양송이수프-1"),
                Arguments.of("최대 주문", "양송이수프-19, 레드와인-1")
        );
    }

    @DisplayName("주문 유효성검사 에러 처리 테스트")
    @ParameterizedTest(name = "주문 유형 : {0}")
    @MethodSource("invalidOrders")
    void validateOrderExceptionTest(String type, String userInput) {
        assertThatThrownBy(() -> new Orders(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_ERROR);
    }

    @DisplayName("주문 유효성검사 정상 작동 테스트")
    @ParameterizedTest(name = "주문 유형 : {0}")
    @MethodSource("validOrders")
    void validateOrderTest(String type, String userInput) {
        Orders orders = new Orders(userInput);
    }


    @DisplayName("주문 정보 저장 테스트")
    @Test
    void orderTest() {
        String userInput = "양송이수프-1, 티본스테이크-2, 초코케이크-3, 레드와인-4";
        Set<Menu> menus = Set.of(Menu.MUSHROOM_SOUP, Menu.T_BONE_STEAK, Menu.CHOCOLATE_CAKE, Menu.RED_WINE);
        Orders orders = new Orders(userInput);
        assertThat(orders.getOrders().keySet()).containsExactlyInAnyOrderElementsOf(menus);
        assertThat(orders.getOrders().get(Menu.MUSHROOM_SOUP)).isEqualTo(1);
        assertThat(orders.getOrders().get(Menu.T_BONE_STEAK)).isEqualTo(2);
        assertThat(orders.getOrders().get(Menu.CHOCOLATE_CAKE)).isEqualTo(3);
        assertThat(orders.getOrders().get(Menu.RED_WINE)).isEqualTo(4);
    }

    @DisplayName("전체 가격 계산 테스트")
    @Test
    void getTotalPriceTest() {
        String userInput = "양송이수프-1, 티본스테이크-2, 초코케이크-3, 레드와인-4";
        int totalPrice = Menu.MUSHROOM_SOUP.getPrice() + Menu.T_BONE_STEAK.getPrice() * 2
                + Menu.CHOCOLATE_CAKE.getPrice() * 3 + Menu.RED_WINE.getPrice() * 4;
        Orders orders = new Orders(userInput);
        assertThat(orders.getTotalPayment()).isEqualTo(totalPrice);
    }

    @DisplayName("디저트 수 계산 테스트")
    @Test
    void countDessertTest() {
        String userInput = "티본스테이크-2, 초코케이크-3, 아이스크림-4";
        Orders orders = new Orders(userInput);
        assertThat(orders.countDesserts()).isEqualTo(7);
    }

    @DisplayName("메인디쉬 수 계산 테스트")
    @Test
    void countMainTest() {
        String userInput = "티본스테이크-2, 바비큐립-3, 해산물파스타-4";
        Orders orders = new Orders(userInput);
        assertThat(orders.countMains()).isEqualTo(9);
    }
}