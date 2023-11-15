package christmas.model;

import christmas.constants.Constants;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Benefit {
    private final EnumMap<EventType, Integer> benefit;

    public Benefit(ReservationDate reservationDate, Orders orders) {
        this.benefit = new EnumMap<>(EventType.class);
        if (checkPossibleToGetBenefit(orders)) {
            addChristmasDDayBenefit(reservationDate);
            addWeekdayBenefit(reservationDate, orders);
            addWeekendBenefit(reservationDate, orders);
            addSpecialBenefit(reservationDate);
            addGiftBenefit(orders);
        }
    }

    private boolean checkPossibleToGetBenefit(Orders orders) {
        return orders.getTotalPayment() >= Constants.MIN_PAYMENT_TO_GET_BENEFIT;
    }

    private void addChristmasDDayBenefit(ReservationDate reservationDate) {
        if (reservationDate.checkDuringDDayEvent()) {
            int christmasDDayDiscount = calculateChristmasDDayDiscount(reservationDate.getDate());
            this.benefit.put(EventType.CHRISTMAS_D_DAY, christmasDDayDiscount);
        }
    }

    private int calculateChristmasDDayDiscount(int date) {
        return (date - 1) * Constants.D_DAY_ACCUMULATED_DISCOUNT + Constants.D_DAY_START_DISCOUNT;
    }

    private void addWeekdayBenefit(ReservationDate reservationDate, Orders orders) {
        if (!reservationDate.checkWeekend() && orders.countDesserts() != 0) {
            int weekdayDiscount = calculateWeekDayDiscount(orders);
            this.benefit.put(EventType.WEEKDAY, weekdayDiscount);
        }
    }

    private int calculateWeekDayDiscount(Orders orders) {
        return orders.countDesserts() * Constants.YEAR;
    }

    private void addWeekendBenefit(ReservationDate reservationDate, Orders orders) {
        if (reservationDate.checkWeekend() && orders.countMains() != 0) {
            int weekendDiscount = calculateWeekendDiscount(orders);
            this.benefit.put(EventType.WEEKEND, weekendDiscount);
        }
    }

    private int calculateWeekendDiscount(Orders orders) {
        return orders.countMains() * Constants.YEAR;
    }

    private void addSpecialBenefit(ReservationDate reservationDate) {
        if (reservationDate.checkDuringSpecialEvent()) {
            this.benefit.put(EventType.SPECIAL, Constants.SPECIAL_DISCOUNT);
        }
    }

    private void addGiftBenefit(Orders orders) {
        if (orders.getTotalPayment() >= Constants.MIN_PAYMENT_TO_GET_GIFT) {
            this.benefit.put(EventType.GIFT, Menu.getGiftMenuPrice() * Menu.getGiftMenuQuantity());
        }
    }

    public Map<EventType, Integer> getBenefit() {
        return Collections.unmodifiableMap(this.benefit);
    }

    public int getTotalBenefitAmount() {
        return this.benefit.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getTotalDiscountAmount() {
        int totalBenefit = getTotalBenefitAmount();
        if (checkGiftIncluded()) {
            return totalBenefit - (Menu.getGiftMenuPrice() * Menu.getGiftMenuQuantity());
        }
        return totalBenefit;
    }

    public boolean checkAnyEventIncluded() {
        return this.benefit.size() != 0;
    }

    public boolean checkGiftIncluded() {
        return this.benefit.get(EventType.GIFT) != null;
    }
}