package christmas.view;

import christmas.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {
    private static final Scanner scanner = new Scanner(System.in);

    @DisplayName("숫자가 아닌 날짜 입력될 경우 에러 발생")
    @Test
    void validateOrderTestWithInvalidDateFormat() {
        Scanner scanner = new Scanner(System.in);
        InputStream in = new ByteArrayInputStream("숫자 아님".getBytes());
        System.setIn(in);

        assertThatThrownBy(InputView::readDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DATE_ERROR);
    }
}