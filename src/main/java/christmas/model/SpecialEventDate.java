package christmas.model;

import christmas.constants.Constants;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SpecialEventDate {

    private final Set<Integer> specialDates = new HashSet<>();

    SpecialEventDate() {
        int sunday = findFirstSunday();
        while (sunday <= Constants.MONTH_END_DATE) {
            specialDates.add(sunday);
            sunday += 7;
        }
        specialDates.add(25);
    }

    private int findFirstSunday() {
        int startDate = 1;
        DayOfWeek dayOfWeek = LocalDate.of(Constants.YEAR, Constants.MONTH, startDate).getDayOfWeek();
        while (dayOfWeek != DayOfWeek.SUNDAY) {
            startDate++;
            dayOfWeek = LocalDate.of(Constants.YEAR, Constants.MONTH, startDate).getDayOfWeek();
        }
        return startDate;
    }

    public boolean contains(int date) {
        return this.specialDates.contains(date);
    }
}