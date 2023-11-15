package christmas.model;

import christmas.constants.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BenefitTest {

    private static final int christmasDiscountAt2 = Constants.D_DAY_START_DISCOUNT + Constants.D_DAY_ACCUMULATED_DISCOUNT;
    private static final int christmasDiscountAt3 = Constants.D_DAY_START_DISCOUNT + Constants.D_DAY_ACCUMULATED_DISCOUNT * 2;
    private static final int christmasDiscountAt4 = Constants.D_DAY_START_DISCOUNT + Constants.D_DAY_ACCUMULATED_DISCOUNT * 3;
    private static final int giftPrice = Menu.getGiftMenuPrice();

    static Stream<Arguments> reservationDateAndOrders() throws Throwable {
        return Stream.of(
                Arguments.of("할인받기 위한 금액 미만", 2, "타파스-1", List.of(), 0, false),
                // 크리스마스 디데이 이벤트 중
                Arguments.of("크리스마스, 주말-메인x", 2, "타파스-1, 아이스크림-1",
                        List.of(EventType.CHRISTMAS_D_DAY), christmasDiscountAt2, false),
                Arguments.of("크리스마스, 주말-메인o", 2, "타파스-1, 크리스마스파스타-3",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.WEEKEND), christmasDiscountAt2 + Constants.YEAR * 3, false),
                Arguments.of("크리스마스, 주말-메인x, 증정", 2, "레드와인-3, 초코케이크-1",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.GIFT), christmasDiscountAt2 + giftPrice, true),
                Arguments.of("크리스마스, 주말-메인o, 증정", 2, "레드와인-1, 티본스테이크-2",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.WEEKEND, EventType.GIFT), christmasDiscountAt2 + giftPrice + Constants.YEAR * 2, true),
                Arguments.of("크리스마스, 평일-디저트x", 4, "바비큐립-1",
                        List.of(EventType.CHRISTMAS_D_DAY), christmasDiscountAt4, false),
                Arguments.of("크리스마스, 평일-디저트o", 4, "초코케이크-1",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.WEEKDAY), christmasDiscountAt4 + Constants.YEAR, false),
                Arguments.of("크리스마스, 평일-디저트x, 증정", 4, "크리스마스파스타-3, 티본스테이크-1",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.GIFT), christmasDiscountAt4 + giftPrice, true),
                Arguments.of("크리스마스, 평일-디저트o, 증정", 4, "해산물파스타-3, 초코케이크-1",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.WEEKDAY, EventType.GIFT), christmasDiscountAt4 + Constants.YEAR + giftPrice, true),
                Arguments.of("크리스마스, 평일-디저트x, 특별", 3, "티본스테이크-1",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.SPECIAL), christmasDiscountAt3 + Constants.SPECIAL_DISCOUNT, false),
                Arguments.of("크리스마스, 평일-디저트o, 특별", 3, "초코케이크-1",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.WEEKDAY, EventType.SPECIAL), christmasDiscountAt3 + Constants.SPECIAL_DISCOUNT + Constants.YEAR, false),
                Arguments.of("크리스마스, 평일-디저트x, 특별, 증정", 3, "레드와인-3, 티본스테이크-1",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.SPECIAL, EventType.GIFT), christmasDiscountAt3 + Constants.SPECIAL_DISCOUNT + giftPrice, true),
                Arguments.of("크리스마스, 평일-디저트o, 특별, 증정", 3, "레드와인-3, 초코케이크-1",
                        List.of(EventType.CHRISTMAS_D_DAY, EventType.WEEKDAY, EventType.SPECIAL, EventType.GIFT), christmasDiscountAt3 + Constants.SPECIAL_DISCOUNT + Constants.YEAR + giftPrice, true),
                // 크리스마스 디데이 이벤트 외
                Arguments.of("주말-메인x", 29, "타파스-1, 아이스크림-1", List.of(), 0, false),
                Arguments.of("주말-메인o", 29, "타파스-1, 티본스테이크-1", List.of(EventType.WEEKEND), Constants.YEAR, false),
                Arguments.of("주말-메인x, 증정", 29, "레드와인-3, 아이스크림-1", List.of(EventType.GIFT), giftPrice, true),
                Arguments.of("주말-메인o, 증정", 29, "레드와인-3, 티본스테이크-1", List.of(EventType.WEEKEND, EventType.GIFT), giftPrice + Constants.YEAR, true),
                Arguments.of("평일-디저트x", 26, "티본스테이크-1", List.of(), 0, false),
                Arguments.of("평일-디저트o", 26, "초코케이크-1", List.of(EventType.WEEKDAY), Constants.YEAR, false),
                Arguments.of("평일-디저트x, 증정", 26, "레드와인-3, 티본스테이크-1", List.of(EventType.GIFT), giftPrice, true),
                Arguments.of("평일-디저트o, 증정", 26, "레드와인-3, 초코케이크-1", List.of(EventType.WEEKDAY, EventType.GIFT), Constants.YEAR + giftPrice, true),
                Arguments.of("평일-디저트x, 특별", 31, "티본스테이크-1", List.of(EventType.SPECIAL), Constants.SPECIAL_DISCOUNT, false),
                Arguments.of("평일-디저트o, 특별", 31, "초코케이크-1", List.of(EventType.WEEKDAY, EventType.SPECIAL), Constants.SPECIAL_DISCOUNT + Constants.YEAR, false),
                Arguments.of("평일-디저트x, 특별, 증정", 31, "레드와인-3, 티본스테이크-1", List.of(EventType.GIFT, EventType.SPECIAL), Constants.SPECIAL_DISCOUNT + giftPrice, true),
                Arguments.of("평일-디저트o, 특별, 증정", 31, "레드와인-3, 초코케이크-1", List.of(EventType.WEEKDAY, EventType.GIFT, EventType.SPECIAL), Constants.SPECIAL_DISCOUNT + Constants.YEAR + giftPrice, true)
        );
    }

    @DisplayName("할인 내역 저장 정상 작동 테스트")
    @ParameterizedTest(name = "주문 유형 : {0}")
    @MethodSource("reservationDateAndOrders")
    void reservationDateTest(String type, int date, String order, List<EventType> events, int expectedBenefit, boolean isGiftIncluded) {
        ReservationDate reservationDate = new ReservationDate(date);
        Orders orders = new Orders(order);
        Benefit benefit = new Benefit(reservationDate, orders);

        assertThat(benefit.getBenefit().keySet()).containsExactlyInAnyOrderElementsOf(events);
        assertThat(benefit.getTotalBenefitAmount()).isEqualTo(expectedBenefit);
        assertThat(benefit.checkGiftIncluded()).isEqualTo(isGiftIncluded);
        assertThat(benefit.checkAnyEventIncluded()).isEqualTo(events.size() != 0);
        if (benefit.checkGiftIncluded()) {
            assertThat(benefit.getTotalDiscountAmount()).isEqualTo(expectedBenefit - 25_000);
        }
        if (!benefit.checkGiftIncluded()) {
            assertThat(benefit.getTotalDiscountAmount()).isEqualTo(expectedBenefit);
        }
    }
}