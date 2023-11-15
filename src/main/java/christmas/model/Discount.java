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
            addGiftDiscount(orders);
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
        if (!reservationDate.getIsWeekend() && orders.countDesserts() != 0) {
            int weekdayDiscount = calculateWeekDayDiscount(orders);
            this.discount.put(EventType.WEEKDAY, weekdayDiscount);
        }
    }

    private int calculateWeekDayDiscount(Orders orders) {
        return orders.countDesserts() * Constants.YEAR;
    }

    private void addWeekendDiscount(ReservationDate reservationDate, Orders orders) {
        if (reservationDate.getIsWeekend() && orders.countMains() != 0) {
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

    private void addGiftDiscount(Orders orders) {
        if (orders.getTotalPrice() >= Constants.MIN_PAYMENT_TO_GET_GIFT) {
            this.discount.put(EventType.GIFT, Menu.getGiftMenuPrice());
        }
    }

    public EnumMap<EventType, Integer> getDiscount(){
        return this.discount;
    }

    public int getTotalBenefitAmount(){
        return this.discount.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getTotalDiscount(){
        return this.discount.values().stream().mapToInt(Integer::intValue).sum() - Menu.getGiftMenuPrice();
    }

    public boolean checkAnyEventIncluded(){
        return this.discount.size() != 0;
    }

    public boolean checkGiftIncluded(){
        return this.discount.get(EventType.GIFT) != null;
    }
}