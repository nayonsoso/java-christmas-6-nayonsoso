package christmas.view;

import christmas.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {

    @DisplayName("숫자가 아닌 날짜 입력될 경우 에러 발생")
    @Test
    void validateOrderTestWithInvalidDateFormat() {
        InputStream in = new ByteArrayInputStream("숫자 아님".getBytes());
        System.setIn(in);

        assertThatThrownBy(() -> InputView.readDateOn(12))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DATE_ERROR);
    }
}