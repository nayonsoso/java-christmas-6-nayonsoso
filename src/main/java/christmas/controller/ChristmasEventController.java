package christmas.controller;

import christmas.constants.Constants;
import christmas.model.*;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasEventController {
    public void run() {
        ReservationDate reservationDate = initDate();
        Orders orders = initOrder();
        Discount discount = initDiscount(reservationDate, orders);

        printEventPreview(reservationDate);
        printOrderedMenus(orders);
        printPriceBeforeDiscount(orders);
        printGiftMenu(discount);
        printBenefitList(discount);
        printTotalBenefit(discount);
        printAfterDiscount(orders, discount);
        printEventBadge(discount);
    }

    private ReservationDate initDate() {
        try {
            int date = InputView.readDateOn(Constants.MONTH);
            return new ReservationDate(date);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return initDate();
        }
    }

    private Orders initOrder() {
        try {
            String inputOrder = InputView.readOrder();
            return new Orders(inputOrder);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return initOrder();
        }
    }

    private Discount initDiscount(ReservationDate date, Orders orders) {
        return new Discount(date, orders);
    }

    private void printEventPreview(ReservationDate date) {
        OutputView.printEventPreview(Constants.MONTH, date.getDate());
    }

    private void printOrderedMenus(Orders orders) {
        OutputView.printMenuStartMessage();
        orders.getOrders().forEach((key, value) -> OutputView.printMenu(key.getName(), value));
    }

    private void printPriceBeforeDiscount(Orders orders) {
        OutputView.printPriceBeforeDiscount(orders.getTotalPrice());
    }

    private void printGiftMenu(Discount discount) {
        OutputView.printGiftMenuStartMessage();
        if (discount.checkGiftIncluded()) {
            OutputView.printGiftMenu(Menu.getGiftMenuName(), Menu.getGiftMenuQuantity());
        }
        if (!discount.checkGiftIncluded()) {
            OutputView.printNoDataMessage();
        }
    }

    private void printBenefitList(Discount discount) {
        OutputView.printBenefitListStartMessage();
        if (discount.checkAnyEventIncluded()) {
            discount.getDiscount().forEach((key, value) -> OutputView.printBenefitList(key.getName(), value));
        }
        if (!discount.checkAnyEventIncluded()) {
            OutputView.printNoDataMessage();
        }
    }

    private void printTotalBenefit(Discount discount) {
        OutputView.printTotalBenefitStartMessage();
        OutputView.printTotalBenefit(discount.getTotalBenefitAmount());
    }

    private void printAfterDiscount(Orders orders, Discount discount) {
        OutputView.printAfterDiscount(orders.getTotalPrice() - discount.getTotalDiscount());
    }

    private void printEventBadge(Discount discount) {
        OutputView.printEventBadgeStartMessage();
        String badgeName = Badge.getBadgeFor(discount.getTotalBenefitAmount());
        if (badgeName != null) {
            OutputView.printEventBadge(Badge.getBadgeFor(discount.getTotalBenefitAmount()));
        }
        if (badgeName == null) {
            OutputView.printNoDataMessage();
        }
    }
}