package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Johann Stolz 13.07.2018
 */
public class DateTimeHandler {
    private final LocalDate startDay;
    private final LocalDate endDay;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public DateTimeHandler(String startDay, String endDay, String startTime, String endTime) {
        if (startDay == null || startDay.isEmpty()) {
            this.startDay = LocalDate.MIN;
        } else {
            this.startDay = LocalDate.parse(startDay);
        }

        if (endDay == null || endDay.isEmpty()) {
            this.endDay = LocalDate.MIN;
        } else {
            this.endDay = LocalDate.parse(endDay);
        }

        if (startTime == null || startTime.isEmpty()) {
            this.startTime = LocalTime.MIN;
        } else {
            this.startTime = LocalTime.parse(startTime);
        }

        if (endTime == null || endTime.isEmpty()) {
            this.endTime = LocalTime.MIN;
        } else {
            this.endTime = LocalTime.parse(endTime);
        }
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

}
