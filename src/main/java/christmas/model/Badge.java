package christmas.model;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minBenefitAmount;

    Badge(String name, int minBenefitAmount){
        this.name = name;
        this.minBenefitAmount = minBenefitAmount;
    }

    public static String getBadgeFor(int totalBenefitAmount){
        if(totalBenefitAmount < STAR.minBenefitAmount){
            return null;
        }
        if(totalBenefitAmount < TREE.minBenefitAmount){
            return STAR.name;
        }
        if(totalBenefitAmount < SANTA.minBenefitAmount){
            return TREE.name;
        }
        return SANTA.name;
    }
}