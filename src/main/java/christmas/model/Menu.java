package christmas.model;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, false),
    TAPAS("타파스", 5_500, false),
    CAESAR_SALAD("시저샐러드", 8_000, false),

    T_BONE_STEAK("티본스테이크", 55_000, false),
    BBQ_RIBS("바비큐립", 54_000, false),
    SEAFOOD_PASTA("해산물파스타", 35_000, false),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, false),

    CHOCOLATE_CAKE("초코케이크", 15_000, false),
    ICE_CREAM("아이스크림", 5_000, false),

    ZERO_COLA("제로콜라", 3_000, true),
    RED_WINE("레드와인", 60_000, true),
    CHAMPAGNE("샴페인", 25_000, true);

    private final String name;
    private final int price;
    private final boolean isDrink;

    Menu(String name, int price, boolean isDrink) {
        this.name = name;
        this.price = price;
        this.isDrink = isDrink;
    }

    public static Optional<Menu> findByName(String input) {
        return Arrays.stream(values())
                .filter(menu -> input.equals(menu.getName()))
                .findFirst();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean isDrink() {
        return isDrink;
    }
}