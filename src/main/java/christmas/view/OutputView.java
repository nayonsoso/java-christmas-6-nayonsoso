package christmas.view;

import christmas.constants.Constants;
import christmas.constants.ErrorMessage;

import java.text.DecimalFormat;

public class OutputView {
    private static final String EVENT_PREVIEW_MESSAGE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    private static final String MENU_START_MESSAGE = "\n<주문 메뉴>";
    private static final String GIFT_MENU_MESSAGE = "\n<증정 메뉴>";
    private static final String MENU_QUANTITY_FORMAT = "%s %d개\n";

    private static final String PAYMENT_BEFORE_DISCOUNT_MESSAGE = "\n<할인 전 총주문 금액>";
    private static final String PAYMENT_AFTER_DISCOUNT_MESSAGE = "\n<할인 후 예상 결제 금액>";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "\n<총혜택 금액>";
    private static final String PLUS_MONEY_FORMAT = "###,###원";
    private static final String MINUS_MONEY_FORMAT = "-###,###원";

    private static final String BENEFIT_LIST_MESSAGE = "\n<혜택 내역>";
    private static final String BENEFIT_FORMAT = "%s: %s\n";

    private static final String EVENT_BADGE_MESSAGE = "\n<%d월 이벤트 배지>\n";
    private static final String NO_DATA = "없음";

    public static void printEventPreview(int month, int date) {
        System.out.printf(EVENT_PREVIEW_MESSAGE, month, date);
    }

    public static void printMenuStartMessage() {
        System.out.println(MENU_START_MESSAGE);
    }

    public static void printMenu(String menu, int quantity) {
        System.out.printf(MENU_QUANTITY_FORMAT, menu, quantity);
    }

    public static void printPaymentBeforeDiscount(int payment) {
        System.out.println(PAYMENT_BEFORE_DISCOUNT_MESSAGE);
        System.out.println(formatWithCommas(payment));
    }

    public static void printGiftMenuStartMessage() {
        System.out.println(GIFT_MENU_MESSAGE);
    }

    public static void printGiftMenu(String giftMenu, int quantity) {
        System.out.printf(MENU_QUANTITY_FORMAT, giftMenu, quantity);
    }

    public static void printNoDataMessage() {
        System.out.println(NO_DATA);
    }

    public static void printBenefitStartMessage() {
        System.out.println(BENEFIT_LIST_MESSAGE);
    }

    public static void printBenefit(String event, int discount) {
        System.out.printf(BENEFIT_FORMAT, event, formatMinusWithCommas(discount));
    }

    public static void printTotalBenefitStartMessage() {
        System.out.println(TOTAL_BENEFIT_AMOUNT_MESSAGE);
    }

    public static void printTotalBenefit(int totalBenefit) {
        System.out.println(formatMinusWithCommas(totalBenefit));
    }

    public static void printPaymentAfterDiscount(int paymentAfterDiscount) {
        System.out.println(PAYMENT_AFTER_DISCOUNT_MESSAGE);
        System.out.println(formatWithCommas(paymentAfterDiscount));
    }

    public static void printEventBadgeStartMessage() {
        System.out.printf(EVENT_BADGE_MESSAGE, Constants.MONTH);
    }

    public static void printEventBadge(String badge) {
        System.out.println(badge);
    }

    public static void printError(String message) {
        System.out.printf("%s %s\n\n", ErrorMessage.ERROR_PREFIX, message);
    }

    private static String formatWithCommas(int money) {
        DecimalFormat df = new DecimalFormat(PLUS_MONEY_FORMAT);
        return df.format(money);
    }

    private static String formatMinusWithCommas(int money) {
        if (money == 0) {
            return formatWithCommas(money);
        }
        DecimalFormat df = new DecimalFormat(MINUS_MONEY_FORMAT);
        return df.format(money);
    }
}