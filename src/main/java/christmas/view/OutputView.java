package christmas.view;

import java.text.DecimalFormat;
import java.util.EnumMap;

public class OutputView {
    private static final String EVENT_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String MENU_START_MESSAGE = "<주문 메뉴>";
    private static final String MENU_QUANTITY = "%s %d개";
    private static final String PRICE_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String BENEFIT_LIST_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "<총혜택 금액>";
    private static final String PRICE_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";

    public void printEventPreview(){
        System.out.println(EVENT_PREVIEW_MESSAGE);
    }

    public void printMenuStart() {
        System.out.println(MENU_START_MESSAGE);
    }

    public void printMenu(String menu, Integer quantity) {
        System.out.printf(MENU_QUANTITY, menu, quantity);
        System.out.println();
    }

    public void printPriceBeforeDiscount(int price){
        System.out.println(PRICE_BEFORE_DISCOUNT_MESSAGE);
        printMoney(price);
    }

    public void printGiftMenu(){
        System.out.println(GIFT_MENU_MESSAGE);
        System.out.printf(MENU_QUANTITY, GIFT_MENU, GIFT_MENU_QUANTITY);
    }

    private void printMoney(int money){
        System.out.println(formatWithCommas(money));
    }

    private String formatWithCommas(int money){
        DecimalFormat df = new DecimalFormat("###,###원");
        return df.format(money);
    }
}
