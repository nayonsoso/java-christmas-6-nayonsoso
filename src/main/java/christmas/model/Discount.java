package christmas.model;

import christmas.constants.Constants;

import java.util.EnumMap;

public class Discount {
    private final EnumMap<EventType, Integer> discount;

    public Discount(ReservationDate reservationDate, Orders orders) {
        this.discount = new EnumMap<>(EventType.class);
        if (checkPossibleToDiscount(orders)) {
            addChristmasDiscount(reservationDate);
            addWeekdayDiscount(reservationDate, orders);
            addWeekendDiscount(reservationDate, orders);
            addSpecialDiscount(reservationDate);
            addSpecialDiscount(orders);
        }
    }

    private boolean checkPossibleToDiscount(Orders orders) {
        return orders.getTotalPrice() >= Constants.MIN_PAYMENT_TO_DISCOUNT;
    }

    private void addChristmasDiscount(ReservationDate reservationDate) {
        if (reservationDate.getIsDuringDDayEvent()) {
            int christmasDiscount = calculateChristmasDiscount(reservationDate.getDate());
            this.discount.put(EventType.CHRISTMAS_D_DAY, christmasDiscount);
        }
    }

    private int calculateChristmasDiscount(int date) {
        return (date - 1) * Constants.D_DAY_ACCUMULATED_DISCOUNT + Constants.D_DAY_START_DISCOUNT;
    }

    private void addWeekdayDiscount(ReservationDate reservationDate, Orders orders) {
        if (!reservationDate.getIsWeekend()) {
            int weekdayDiscount = calculateWeekDayDiscount(orders);
            this.discount.put(EventType.WEEKDAY, weekdayDiscount);
        }
    }

    private int calculateWeekDayDiscount(Orders orders) {
        return orders.countDesserts() * Constants.YEAR;
    }

    private void addWeekendDiscount(ReservationDate reservationDate, Orders orders) {
        if (reservationDate.getIsWeekend()) {
            int weekendDiscount = calculateWeekendDiscount(orders);
            this.discount.put(EventType.WEEKEND, weekendDiscount);
        }
    }

    private int calculateWeekendDiscount(Orders orders) {
        return orders.countMains() * Constants.YEAR;
    }

    private void addSpecialDiscount(ReservationDate reservationDate) {
        if (reservationDate.getIsDuringSpecialEvent()) {
            this.discount.put(EventType.SPECIAL, Constants.SPECIAL_DISCOUNT);
        }
    }

    private void addSpecialDiscount(Orders orders) {
        if (orders.getTotalPrice() >= Constants.MIN_PAYMENT_TO_GET_GIFT) {
            this.discount.put(EventType.GIFT, Menu.getGiftMenuPrice());
        }
    }
}
