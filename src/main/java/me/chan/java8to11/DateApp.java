package me.chan.java8to11;

import java.util.Date;

public class DateApp {
    public static void main(String[] args) {
        Date date = new Date();
        long time = date.getTime();
        System.out.println("기계용 시간 EPOCK = " + time);



    }
}
