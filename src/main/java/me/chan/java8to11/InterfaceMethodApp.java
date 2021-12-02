package me.chan.java8to11;

import java.util.*;
import java.util.stream.Collectors;

public class InterfaceMethodApp {
    public static void main(String[] args) {
        /**
         * 인터페이스 기본 메소드와 스태틱 메소드
         */
        NamePrint namePrint = new NamePrintImpl("chan");
        namePrint.printName();
        namePrint.printNameUpperCase();

        /**
         * static method
         */
        NamePrint.printAnything();

        List<String> names = new ArrayList<>();
        names.add("chan");
        names.add("keesun");
        names.add("toby");
        names.add("foo");
        names.forEach((s) -> System.out.println(s));
        System.out.println("InterfaceMethodApp.main");

        /**
         * spliterator()와, trySplit()을 사용하면,
         * 처음 spliterator 변수에는 전체 값이 담기고
         * trySplit()가 실행될 때, 반을 짤라서 spliterator2 변수에 담아준다.
         * spliterator 변수의 반이 사라지는 것.
         */
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> spliterator2 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("InterfaceMethodApp.main");
        while (spliterator2.tryAdvance(System.out::println));

        List<String> collect = names.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("K"))
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);

        names.removeIf(s -> s.startsWith("k"));
        names.forEach(System.out::println);

        System.out.println("InterfaceMethodApp.main");

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        // thenComparing(Comparator<>) 으로 조건 추가 가능 
        names.sort(compareToIgnoreCase.reversed());
        names.forEach(System.out::println);

    }
}
