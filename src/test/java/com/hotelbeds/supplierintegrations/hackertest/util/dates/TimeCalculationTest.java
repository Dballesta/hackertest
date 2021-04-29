package com.hotelbeds.supplierintegrations.hackertest.util.dates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculationTest {

    @Test
    void getDiffInMinutesShouldReturn0() {
        String date1 = "Thu, 21 Dec 2000 16:00:00 +0200";
        String date2 = "Thu, 21 Dec 2000 16:00:00 +0200";

        long diffInMin = TimeCalculation.getDiffInMinutes(date1,date2);

        assertEquals(0,diffInMin);
    }

    @Test
    void getDiffInMinutesWithFirstDate59SecondsAfterSecondDateShouldReturn0() {
        String date1 = "Thu, 21 Dec 2000 16:00:59 +0200";
        String date2 = "Thu, 21 Dec 2000 16:00:00 +0200";

        long diffInMin = TimeCalculation.getDiffInMinutes(date1,date2);

        assertEquals(0,diffInMin);
    }

    @Test
    void getDiffInMinutesWithSecondDate59SecondsAfterFirstDateShouldReturn0() {
        String date1 = "Thu, 21 Dec 2000 16:00:00 +0200";
        String date2 = "Thu, 21 Dec 2000 16:00:59 +0200";

        long diffInMin = TimeCalculation.getDiffInMinutes(date1,date2);

        assertEquals(0,diffInMin);
    }

    @Test
    void getDiffInMinutesWithFirstDate2MinutesAfterSecondDateDiffShouldReturn2() {
        String date1 = "Thu, 21 Dec 2000 16:02:00 +0200";
        String date2 = "Thu, 21 Dec 2000 16:00:00 +0200";

        long diffInMin = TimeCalculation.getDiffInMinutes(date1,date2);

        assertEquals(2,diffInMin);
    }

    @Test
    void getDiffInMinutesWithSecondDate2MinutesAfterFirstDateDiffShouldReturn2() {
        String date1 = "Thu, 21 Dec 2000 16:00:00 +0200";
        String date2 = "Thu, 21 Dec 2000 16:02:00 +0200";

        long diffInMin = TimeCalculation.getDiffInMinutes(date1,date2);

        assertEquals(2,diffInMin);
    }

    @Test
    void getDiffInMinutesWithDifferentTimeZoneDiffShouldReturn60() {
        String date1 = "Thu, 21 Dec 2000 16:00:00 +0200";
        String date2 = "Thu, 21 Dec 2000 16:00:00 +0100";

        long diffInMin = TimeCalculation.getDiffInMinutes(date1,date2);

        assertEquals(60,diffInMin);
    }




}