package christmas.model;

import christmas.constants.Constants;

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

    private EnumMap<Menu, Integer> initOrder(String userInput){
        String[] splitOrder = splitOrder(userInput);
        EnumMap<Menu, Integer> orders = new EnumMap<>(Menu.class);
        for(String order : splitOrder) {
            Menu menu = getMenu(order.split("-")[0]);
            int quantity = getQuantity(order.split("-")[1]);
            validateNoDuplicate(menu);
            orders.put(menu, quantity);
        }
        return orders;
    }

    private String[] splitOrder(String inputOrder) {
        return inputOrder.split(",");
    }

    private Menu getMenu(String menuInput) {
        return Menu.findByName(menuInput)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER_ERROR));
    }

    private int getQuantity(String quantityInput) {
        int quantity = Integer.parseInt(quantityInput);
        if (quantity < Constants.MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
        return quantity;
    }

    private void validateNoDuplicate(Menu menu){
        if(orders.get(menu) == null){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
    }

    private void validateOrders(EnumMap<Menu, Integer> orders){
        validateNotOnlyDrink(orders);
        validateTotalQuantity(orders);
    }

    private void validateNotOnlyDrink(EnumMap<Menu, Integer> orders) {
        if (orders.keySet().stream().noneMatch(Menu::isDrink)) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
    }

    private void validateTotalQuantity(EnumMap<Menu, Integer> orders){
        int sum = calculateTotalQuantity(orders);
        if(sum< Constants.MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
    }

    private int calculateTotalQuantity(EnumMap<Menu, Integer> orders){
        return orders.values().stream().mapToInt(Integer::intValue).sum();
    }
}