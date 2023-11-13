package christmas.view;

import java.text.DecimalFormat;
import java.util.EnumMap;

public class OutputView {
    private static final String EVENT_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String MENU_START_MESSAGE = "<주문 메뉴>";
    private static final String MENU_QUANTITY = "%s %d개";
    private static final String PRICE_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String GIFT_MENU = "샴페인";
    private static final int GIFT_MENU_QUANTITY = 1;
    private static final String BENEFIT_LIST_MESSAGE = "<혜택 내역>";
    private static final String BENEFIT = "%s: %s";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "<총혜택 금액>";
    private static final String PRICE_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";

    public static void printEventPreview(int date){
        System.out.printf(EVENT_PREVIEW_MESSAGE, date);
        System.out.println();
    }

    public static void printMenuStart() {
        System.out.println();
        System.out.println(MENU_START_MESSAGE);
    }

    public static void printMenu(String menu, Integer quantity) {
        System.out.printf(MENU_QUANTITY, menu, quantity);
        System.out.println();
    }

    public static void printPriceBeforeDiscount(int price){
        System.out.println();
        System.out.println(PRICE_BEFORE_DISCOUNT_MESSAGE);
        System.out.println(formatWithCommas(price));
    }

    public static void printGiftMenu(){
        System.out.println();
        System.out.println(GIFT_MENU_MESSAGE);
        System.out.printf(MENU_QUANTITY, GIFT_MENU, GIFT_MENU_QUANTITY);
    }

    public static void printBenefitList(String event, int discount){
        System.out.println(BENEFIT_LIST_MESSAGE);
        System.out.printf(BENEFIT, event, formatMinusWithCommas(discount));
    }

    public static void printTotalBenefit(int totalBenefit){
        System.out.println(TOTAL_BENEFIT_AMOUNT_MESSAGE);
        System.out.println(formatMinusWithCommas(totalBenefit));
    }

    public static void printAfterDiscount(int priceAfterDiscount){
        System.out.println(PRICE_AFTER_DISCOUNT_MESSAGE);
        System.out.println(formatWithCommas(priceAfterDiscount));
    }

    public static void printEventBadge(String badge){
        System.out.println(EVENT_BADGE_MESSAGE);
        System.out.println(badge);
    }

    private static String formatWithCommas(int money){
        DecimalFormat df = new DecimalFormat("###,###원");
        return df.format(money);
    }

    private static String formatMinusWithCommas(int money){
        DecimalFormat df = new DecimalFormat("-###,###원");
        return df.format(money);
    }
}
