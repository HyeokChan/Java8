package me.chan.java8to11;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
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

        /**
         * **********************************************************************
         */
        System.out.println("StreamApp.main");

        ArrayList<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        ArrayList<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> chanEvents = new ArrayList<>();
        chanEvents.add(springClasses);
        chanEvents.add(javaClasses);


        System.out.println("\nspring 으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println("oc = " + oc.getTitle()));

        System.out.println("\nclose 되지 않은 수업");
        springClasses.stream()
                .filter(oc -> !oc.isClosed())
                .forEach(oc -> System.out.println("oc = " + oc.getTitle()));
        System.out.println("\nclose 되지 않은 수업2 method reference");
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println("oc = " + oc.getTitle()));

        System.out.println("\n수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                // forEach에 자동으로 String 형이 들어간다. (getTitle)
                .forEach(System.out::println);

        System.out.println("\n두 수업 목록에 들어있는 모든 수업 아이디 출력");
        chanEvents.stream().flatMap(list -> list.stream())
                .map(OnlineClass::getId)
                .forEach(System.out::println);

        System.out.println("\n10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\n자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream()
                .anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println("test = " + test);

        System.out.println("\n스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> spring = springClasses.stream()
                .map(OnlineClass::getTitle)
                .filter(t -> t.contains("spring"))
                .collect(Collectors.toList());
        spring.forEach(System.out::println);

    }

}
