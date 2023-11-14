package christmas.model;

import christmas.constants.Constants;

import java.util.EnumMap;

public class Discount {
    private final EnumMap<EventType, Integer> discount;

    public Discount(ReservationDate reservationDate, Orders orders) {
        this.discount = new EnumMap<>(EventType.class);
        if (checkPossibleToDiscount(orders)) {
            addChristmasDiscount(reservationDate);
        }
    }

    private boolean checkPossibleToDiscount(Orders orders) {
        return orders.getTotalPrice() >= Constants.MIN_PAYMENT_TO_DISCOUNT;
    }

    private void addChristmasDiscount(ReservationDate reservationDate) {
        if (reservationDate.getIsDuringDDayEvent()) {
            int christmasDiscount = calculateChristmasDiscount(reservationDate.getDate();
            this.discount.put(EventType.CHRISTMAS_D_DAY, christmasDiscount);
        }
    }

    private int calculateChristmasDiscount(int date) {
        return (date - 1) * Constants.D_DAY_ACCUMULATED_DISCOUNT + Constants.D_DAY_START_DISCOUNT;
    }
}
