package ru.carbay.promoter.utils;

import java.time.LocalDate;
import java.util.TimeZone;

public class TimeUtils {

    public static TimeZone moscowTimeZone() {
        return TimeZone.getTimeZone("Etc/GMT-3");
    }
}
