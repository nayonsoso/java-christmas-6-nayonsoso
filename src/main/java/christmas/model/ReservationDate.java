package christmas.model;

import christmas.constants.ErrorMessage;
import christmas.constants.Constants;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReservationDate {
    private final int date;
    private final boolean isDuringDDayEvent;
    private final boolean isWeekend;
    private final boolean isDuringSpecialEvent;

    ReservationDate(int date){
        validate(date);
        this.isDuringDDayEvent = checkDuringDDayEvent(date);
        this.isWeekend = checkWeekend(date);
        this.isDuringSpecialEvent = checkDuringSpecialEvent(date);
        this.date = date;
    }

    private void validate(int date){
        if(date < Constants.MONTH_START_DATE || date > Constants.MONTH_END_DATE){
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR);
        }
    }

    private boolean checkDuringDDayEvent(int date){
        return date >= Constants.D_DAY_START_DATE && date <= Constants.D_DAY_END_DATE;
    }

    private boolean checkWeekend(int date){
        DayOfWeek dayOfWeek = LocalDate.of(Constants.YEAR, Constants.MONTH, date).getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private boolean checkDuringSpecialEvent(int date){
        SpecialEventDate specialEventDate = new SpecialEventDate();
        return specialEventDate.contains(date);
    }

    public boolean getIsDuringDDayEvent(){
        return this.isDuringDDayEvent;
    }

    public boolean getIsWeekend(){
        return this.isWeekend;
    }

    public boolean getIsDuringSpecialEvent(){
        return this.isDuringSpecialEvent;
    }
}