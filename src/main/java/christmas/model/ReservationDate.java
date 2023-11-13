package christmas.model;

import christmas.constants.ErrorMessage;
import christmas.constants.Constants;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReservationDate {
    private final int date;
    private final boolean isWeekend;
    private final int date;
    private final int date;

    ReservationDate(int date){
        validate(date);
        this.isWeekend = checkWeekend(date);
        this.date = date;
    }

    private void validate(int date){
        if(date < Constants.MONTH_START_DATE || date > Constants.MONTH_END_DATE){
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR);
        }
    }

    private boolean checkWeekend(int date){
        DayOfWeek dayOfWeek = LocalDate.of(Constants.YEAR, Constants.MONTH, date).getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}