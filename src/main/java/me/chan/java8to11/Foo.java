package me.chan.java8to11;

import java.util.function.*;

public class Foo {
    public static void main(String[] args) {

        RunSomething runSomething = (number) -> {
            return number + 10;
        };
        //RunSomething runSomething2 = (number) -> number + 10;

        System.out.println("runSomething = " + runSomething.doIt(1));

        Function<Integer, Integer> plus10 = (number) -> number + 10;
        System.out.println("plus10 = " + plus10.apply(1));

        Function<Integer, Integer> multiply2 = (number) -> number * 2;
        System.out.println("multiply2 = " + multiply2.apply(1));

        BiFunction<Integer, Integer, Boolean> booleanBiFunction = (number, number2) -> number == number2;
        System.out.println("booleanBiFunction = " + booleanBiFunction.apply(1, 2));

        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println("multiply2AndPlus10 = " + multiply2AndPlus10.apply(1));

        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiply2);
        System.out.println("plus10AndMultiply2 = " + plus10AndMultiply2.apply(1));

        Consumer<Integer> printT = (number) -> System.out.println("number = " + number);
        printT.accept(10);

        Supplier<Integer> get10 = () -> 10;
        System.out.println("get10 = " + get10.get());

        Predicate<String> startsWithChan = (string) -> string.startsWith("chan");
        Predicate<Integer> isEven = (number) -> number % 2 == 0;
        Predicate<Integer> is10 = (number) -> number == 10;
        System.out.println("startsWithChan = " + startsWithChan.test("chan"));
        System.out.println("isEven = " + isEven.test(1));
        System.out.println("negate = " + isEven.negate().test(1));
        System.out.println("and = " + isEven.and(is10).test(8));
        System.out.println("or = " + isEven.or(is10).test(8));

        // 입력값, 리턴값 타입 같을때
        UnaryOperator<Integer> unaryOperator = (number) -> number + 10;
        BinaryOperator<Integer> binaryOperator = (number, number2) -> number + number2;
        System.out.println("unaryOperator = " + unaryOperator.apply(1));
        System.out.println("binaryOperator = " + binaryOperator.apply(1, 2));

    }

}

