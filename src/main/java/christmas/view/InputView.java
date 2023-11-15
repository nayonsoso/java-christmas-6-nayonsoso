package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.ErrorMessage;

public class InputView {
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    private static final String REQUEST_DATE = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";
    private static final String REQUEST_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static int readDateOn(int month) {
        System.out.printf(START_MESSAGE, month);
        System.out.printf(REQUEST_DATE, month);
        String inputDate = Console.readLine();
        validateInt(inputDate);
        return Integer.parseInt(inputDate);
    }

    public static String readOrder() {
        System.out.println(REQUEST_MENU);
        return Console.readLine();
    }

    private static void validateInt(String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR);
        }
    }
}