package me.chan.java8to11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        Greeting greeting = new Greeting();
        /**
         * method reference
         */
        UnaryOperator<String> hi = Greeting::hi;
        System.out.println(hi.apply("chan"));

        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("chan" ));

        Supplier<Greeting> greetingSupplier = Greeting::new;
        Greeting greeting1 = greetingSupplier.get();
        System.out.println("greeting1 = " + greeting1.getName());

        Function<String, Greeting> stringGreetingFunction = Greeting::new;
        Greeting greeting2 = stringGreetingFunction.apply("chan");
        System.out.println("greeting2 = " + greeting2.getName());

        /**
         * 불특정 다수의 인스턴스를 참조하는 메소드
         */
        String[] names = {"keesun", "toby", "chan"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));

        /**
         * 인터페이스 기본 메소드와 스태틱 메소드
         */
        FooInterface fooInterface = new DefaultFoo("chan");
        fooInterface.printName();
        fooInterface.printNameUpperCase();

        /**
         * static method
         */
        FooInterface.printAnything();




    }
}
