package christmas.model;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuType.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN),
    BBQ_RIBS("바비큐립", 54_000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),

    ZERO_COLA("제로콜라", 3_000, MenuType.DRINK),
    RED_WINE("레드와인", 60_000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25_000, MenuType.DRINK);

    private final String name;
    private final int price;
    private final MenuType menuType;

    Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Optional<Menu> findByName(String input) {
        return Arrays.stream(values())
                .filter(menu -> input.equals(menu.name))
                .findFirst();
    }

    public static String getGiftMenuName() {
        return CHAMPAGNE.name;
    }

    public static int getGiftMenuQuantity() {
        return 1;
    }

    public static int getGiftMenuPrice() {
        return CHAMPAGNE.price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean isDrink() {
        return this.menuType == MenuType.DRINK;
    }

    public boolean isDessert() {
        return this.menuType == MenuType.DESSERT;
    }

    public boolean isMain() {
        return this.menuType == MenuType.MAIN;
    }
}