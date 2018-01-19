package ru.carbay.promoter.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

    private static final SimpleDateFormat sdf_moscow = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    static {
        sdf_moscow.setTimeZone(moscowTimeZone());
    }


    public static TimeZone moscowTimeZone() {
        return TimeZone.getTimeZone("Etc/GMT-3");
    }

    public static String nowMoscowTimeAsString() {
        return sdf_moscow.format(new Date());
    }

}
