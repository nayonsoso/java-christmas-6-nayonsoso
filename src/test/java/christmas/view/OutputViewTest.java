package christmas.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class OutputViewTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStream() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void restoresStream() {
        System.setOut(System.out);
    }

    @Test
    @DisplayName("주어진 정수를 세 자리마다 쉼표를 찍어 출력한다.")
    void printMoneyTest() {
        int money = 1234567890;
        OutputView.printPriceBeforeDiscount(money);
        assertThat(output.toString()).contains("1,234,567,890원");
    }

    @Test
    @DisplayName("주어진 정수를 세 자리마다 쉼표를 찍어 출력하며, 맨 앞에 -를 붙인다.")
    void printMinusMoneyTest() {
        int money = 1234567890;
        OutputView.printTotalBenefit(money);
        assertThat(output.toString()).contains("-1,234,567,890원");
    }
}