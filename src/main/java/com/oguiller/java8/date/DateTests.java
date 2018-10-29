package com.oguiller.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class DateTests {

    public static void main(String... args) {
        LocalDate localDate = LocalDate.now();

        LocalDate.of(1982, 07, 17);

        LocalDate.parse("1982-05-17");

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        DayOfWeek saturday = LocalDate.parse("1982-07-17").getDayOfWeek();

        System.out.println(saturday);

        DayOfWeek monday = LocalDate.parse("1982-05-17").getDayOfWeek();

        System.out.println(monday);

        boolean notBefore = LocalDate.parse("2016-06-12")
                .isBefore(LocalDate.parse("2016-06-11"));

        boolean isAfter = LocalDate.parse("2016-06-12")
                .isAfter(LocalDate.parse("2016-06-11"));

        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12")
                .with(TemporalAdjusters.firstDayOfMonth());

        // The LocalTime represents time without a date.

        LocalTime now = LocalTime.now();
        System.out.println("Now: " + now);

        LocalTime sixThirty = LocalTime.parse("06:30");
        System.out.println("Six thirty: " + sixThirty);

        LocalTime sevenThirty = LocalTime.of(7, 30);

        LocalTime eightThirty = sevenThirty.plus(1, ChronoUnit.HOURS);

        boolean isBefore = sixThirty.isBefore(sevenThirty);

        /**
         * Java 8 provides ZonedDateTime when we need to deal with time zone specific date and time. The ZoneId is an
         * identifier used to represent different zones. There are about 40 different time zones and the ZoneId are
         * used to represent them as follows.
         * In this code snippet we create a Zone for Paris:
         */
        ZoneId zoneId = ZoneId.of("Japan");

        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();

        allZoneIds.stream().forEach(zoneIdInStream -> System.out.println(zoneIdInStream));

        LocalDateTime today = LocalDateTime.now();
        ZonedDateTime todayInJapan = ZonedDateTime.of(today, zoneId);

        System.out.println("Today in Japan: " + todayInJapan);

        LocalDate initialDate = LocalDate.parse("2007-05-10");

        LocalDate finalDate = initialDate.plus(Period.ofDays(5));

        // The Period class is widely used to modify values of given a date or to obtain the difference between two dates:
        // The Period class uses the units year, month and day to represent a period of time.
        int five = Period.between(finalDate, initialDate).getDays();

        long fiveDays = ChronoUnit.DAYS.between(initialDate, initialDate);

        // Similar to Period, the Duration class is use to deal with Time. In the following code we create a LocalTime
        // of 6:30 am and then add a duration of 30 seconds to make a LocalTime of 06:30:30am:

        LocalTime initialTime = LocalTime.of(6, 30, 0);

        /**
         * The Duration class represents an interval of time in seconds or nanoseconds and is most suited for handling
         * shorter amounts of time, in cases that require more precision.
         */

        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));

        long thirty = Math.abs(Duration.between(finalTime, initialTime).getSeconds());

        System.out.println("Duration between is: " + thirty);

        thirty = Math.abs(ChronoUnit.SECONDS.between(finalTime, initialTime));

        System.out.println("Duration between is: " + thirty);

        // Compatibility with Date and Calendar

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        // Java 8 has added the toInstant() method which helps to convert existing Date and Calendar instance to new
        // Date Time API as in the following code snippet:
        LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());

        // Java 8 provides APIs for the easy formatting of Date and Time:
        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);

        String localDateString = localDateTime.format(DateTimeFormatter.ISO_DATE);
        System.out.println("Date is: " + localDateString);

        String formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("Formatted Date is: " + formattedDate);

        String dateWithUKLocale = localDateTime
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withLocale(Locale.UK));

        System.out.println("Formatted Date with UK Locale is: " + dateWithUKLocale);


    }
}
