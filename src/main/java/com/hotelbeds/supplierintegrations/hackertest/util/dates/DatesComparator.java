package com.hotelbeds.supplierintegrations.hackertest.util.dates;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatesComparator {


    // Entry example Thu, 21 Dec 2000 16:01:07 +0200
    public static String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    public static long getDiffInMinutes(String date1, String date2){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        ZonedDateTime firstDate = ZonedDateTime.parse(date1,dateTimeFormatter);
        ZonedDateTime secondDate = ZonedDateTime.parse(date2,dateTimeFormatter);

        return ChronoUnit.MINUTES.between(firstDate, secondDate);
    }

}
