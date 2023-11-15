package christmas.controller;

import christmas.constants.Constants;
import christmas.model.*;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasEventController {
    public void run() {
        ReservationDate reservationDate = initDate();
        Orders orders = initOrder();
        Benefit benefit = initBenefit(reservationDate, orders);

        printEventPreview(reservationDate);
        printOrderedMenus(orders);
        printPaymentBeforeDiscount(orders);
        printGiftMenu(benefit);
        printBenefitList(benefit);
        printTotalBenefit(benefit);
        printAfterDiscount(orders, benefit);
        printEventBadge(benefit);
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

    private Benefit initBenefit(ReservationDate date, Orders orders) {
        return new Benefit(date, orders);
    }

    private void printEventPreview(ReservationDate date) {
        OutputView.printEventPreview(Constants.MONTH, date.getDate());
    }

    private void printOrderedMenus(Orders orders) {
        OutputView.printMenuStartMessage();
        orders.getOrders().forEach((key, value) -> OutputView.printMenu(key.getName(), value));
    }

    private void printPaymentBeforeDiscount(Orders orders) {
        OutputView.printPaymentBeforeDiscount(orders.getTotalPayment());
    }

    private void printGiftMenu(Benefit benefit) {
        OutputView.printGiftMenuStartMessage();
        if (benefit.checkGiftIncluded()) {
            OutputView.printGiftMenu(Menu.getGiftMenuName(), Menu.getGiftMenuQuantity());
        }
        if (!benefit.checkGiftIncluded()) {
            OutputView.printNoDataMessage();
        }
    }

    private void printBenefitList(Benefit benefit) {
        OutputView.printBenefitStartMessage();
        if (benefit.checkAnyEventIncluded()) {
            benefit.getBenefit().forEach((key, value) -> OutputView.printBenefit(key.getName(), value));
        }
        if (!benefit.checkAnyEventIncluded()) {
            OutputView.printNoDataMessage();
        }
    }

    private void printTotalBenefit(Benefit benefit) {
        OutputView.printTotalBenefitStartMessage();
        OutputView.printTotalBenefit(benefit.getTotalBenefitAmount());
    }

    private void printAfterDiscount(Orders orders, Benefit benefit) {
        OutputView.printPaymentAfterDiscount(orders.getTotalPayment() - benefit.getTotalDiscountAmount());
    }

    private void printEventBadge(Benefit benefit) {
        OutputView.printEventBadgeStartMessage();
        String badgeName = Badge.getBadgeFor(benefit.getTotalBenefitAmount());
        if (badgeName != null) {
            OutputView.printEventBadge(Badge.getBadgeFor(benefit.getTotalBenefitAmount()));
        }
        if (badgeName == null) {
            OutputView.printNoDataMessage();
        }
    }
}