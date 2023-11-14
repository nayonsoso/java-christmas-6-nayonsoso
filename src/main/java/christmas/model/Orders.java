package christmas.model;

import java.util.EnumMap;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR;

public class Orders {
    EnumMap<Menu, Integer> orders;

    private void validateFormat(String inputOrder) {
        String input = removeWhiteSpace(inputOrder);
        String regex = "^[가-힣]+(-\\d+)(,[가-힣]+(-\\d+))*$";
        if (!input.matches(regex)) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
    }

    private String removeWhiteSpace(String userInput) {
        return userInput.replace(" ", "");
    }
}