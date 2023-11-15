package christmas.model;

import christmas.constants.ErrorMessage;
import christmas.constants.Constants;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReservationDate {
    private final int date;

    public ReservationDate(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if (date < Constants.MONTH_START_DATE || date > Constants.MONTH_END_DATE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR);
        }
    }

    public boolean checkDuringDDayEvent() {
        return this.date >= Constants.D_DAY_START_DATE && this.date <= Constants.D_DAY_END_DATE;
    }

    public boolean checkWeekend() {
        DayOfWeek dayOfWeek = LocalDate.of(Constants.YEAR, Constants.MONTH, this.date).getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean checkDuringSpecialEvent() {
        SpecialEventDates specialEventDates = new SpecialEventDates();
        return specialEventDates.contains(this.date);
    }

    public int getDate() {
        return this.date;
    }
}