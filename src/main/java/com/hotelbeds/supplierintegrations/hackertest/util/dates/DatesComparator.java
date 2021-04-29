package com.hotelbeds.supplierintegrations.hackertest.util.dates;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DatesComparator {


    // Entry example Thu, 21 Dec 2000 16:01:07 +0200
    public static String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    public static long getDiffInMinutes(String date1, String date2){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withLocale(Locale.ENGLISH);

        ZonedDateTime firstDate = ZonedDateTime.parse(date1,dateTimeFormatter);
        ZonedDateTime secondDate = ZonedDateTime.parse(date2,dateTimeFormatter);

        return Math.abs(ChronoUnit.MINUTES.between(firstDate, secondDate));
    }

}
