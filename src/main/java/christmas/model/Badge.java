package christmas.model;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minDiscount;

    Badge(String name, int minDiscount){
        this.name = name;
        this.minDiscount = minDiscount;
    }

    public static String getBadgeFor(int totalDiscount){
        if(totalDiscount < STAR.minDiscount){
            return null;
        }
        if(totalDiscount < TREE.minDiscount){
            return STAR.name;
        }
        if(totalDiscount < SANTA.minDiscount){
            return TREE.name;
        }
        return SANTA.name;
    }
}