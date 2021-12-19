package me.chan.java8to11;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("마늘간장")
public class EtcApp {
    public static void main(String[] args) {
        Chicken[] chickens = EtcApp.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println("c = " + c.value());
        });

        ChickenContainer chickenContainer = EtcApp.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println("c = " + c.value());
        });


    }
}
