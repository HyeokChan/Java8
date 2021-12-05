package me.chan.java8to11;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("chan");
        names.add("keesun");
        names.add("toby");
        names.add("foo");

        List<String> collect = names.stream().map((s) -> {
            /**
             * 중개형 오퍼레이터,
             * 종료형 오퍼레이터가 동작하기 전까지는 의미가 없다.
             * 실행되지 않는다 -> .collect 코드가 없으면 s 출력이 안된다.
             */
            System.out.println("s = " + s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        // collect 종료형 오퍼레이터

        collect.forEach(System.out::println);
        // 스트림은 원본에 영향을 주지 않는다.
        names.forEach(System.out::println);

        /**
         * 스트림을 사용하지 않는 경우,
         * 병렬적으로 사용하기 힘들다.
          */
        for (String name : names) {
            if(name.startsWith("c")){
                System.out.println("name = " + name.toUpperCase());
            }
        }

        /**
         * 스트림을 사용하는 경우 병렬처리가 가능하다
         * parallelStream
         */
        List<String> collect2 = names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect2.forEach(System.out::println);






    }

}
