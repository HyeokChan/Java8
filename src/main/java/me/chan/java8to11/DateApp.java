package me.chan.java8to11;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateApp {
    public static void main(String[] args) {
        // 기계적인 시간
        Instant instant = Instant.now();
        // 기준시 UTC, GMT
        System.out.println("instant = " + instant);
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("zoneId = " + zoneId);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        System.out.println("zonedDateTime = " + zonedDateTime);

        // 사람
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        LocalDateTime birthDay = LocalDateTime.of(1982, Month.APRIL, 15, 0, 0);
        System.out.println("birthDay = " + birthDay);
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("nowInKorea = " + nowInKorea);

        // 기간 표현(사람) Period
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2020, Month.JANUARY, 15);
        Period period = Period.between(today, thisYearBirthday);
        System.out.println("period = " + period.getDays());
        Period until = today.until(thisYearBirthday);
        System.out.println("until = " + until.get(ChronoUnit.DAYS));

        // 기간표현(기계) Duration
        Instant nowInstance = Instant.now();
        Instant plus = nowInstance.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(nowInstance, plus);
        System.out.println("between = " + between.getSeconds());

        // 포맷팅
        LocalDateTime nowFormat = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("now1 = " + nowFormat.format(dateTimeFormatter));

        // 파싱
        LocalDate parse = LocalDate.parse("07/15/1982", dateTimeFormatter);
        System.out.println("parse = " + parse);

        // date -> instamce
        Date date = new Date();
        Instant instant1 = date.toInstant();
        // instance -> date
        Date from = Date.from(instant1);

        // GregorianCalendar -> instance -> LocalDateTime
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime localDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        
        ZoneId pst = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(pst);


    }
}
