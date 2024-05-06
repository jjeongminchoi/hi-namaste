package com.jm.hinamaste.global;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static java.time.DayOfWeek.*;
import static java.time.DayOfWeek.MONDAY;

@Component
public class DateUtil {

    public LocalDate getCurrentDate(LocalDateTime currentDateTime) {
        return LocalDate.of(currentDateTime.getYear(), currentDateTime.getMonth(), currentDateTime.getDayOfMonth());
    }

    public boolean isWeekend(DayOfWeek currentDayOfWeek) {
        return currentDayOfWeek == SATURDAY || currentDayOfWeek == SUNDAY;
    }

    public LocalDate getMondayOfNextWeek(LocalDate currentDate) {
        return currentDate.plusDays(2).with(MONDAY);
    }

    public LocalDate getFridayOfNextWeek(LocalDate currentDate) {
        return currentDate.plusDays(2).with(FRIDAY);
    }

    public LocalDate getMondayOfThisWeek(LocalDate currentDate) {
        return currentDate.with(MONDAY);
    }

    public LocalDate getFridayOfThisWeek(LocalDate currentDate) {
        return currentDate.with(FRIDAY);
    }

    public LocalDate getFirstDayOfNextMonth(LocalDate currentDate) {
        return LocalDate.of(currentDate.getYear(), currentDate.getMonth().plus(1), 1);
    }

    public LocalDate getLastDayOfNextMonth(LocalDate currentDate) {
        return LocalDate.of(currentDate.getYear(), currentDate.getMonth().plus(1), currentDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth());
    }

    public LocalDate getLastDayOfThisMonth(LocalDate currentDate) {
        return LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth());
    }

    public LocalDate getFirstDayOfThisMonth(LocalDate currentDate) {
        return LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
    }
}
