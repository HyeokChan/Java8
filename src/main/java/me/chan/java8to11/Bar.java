package me.chan.java8to11;

public interface Bar {
    /**
     * extends FooInterface 사용 시
     * Bar를 구현하는 구현체가 FooInterface의 default printNameUppercase 메소드를
     * 사용하지 않게 하기 위해서 추상 메소드를 다시선언한다.
     * 대신, Bar를 구현하는 구현체가 직접 printNameUpperCase 메소드를 구현해야한다.
     */
    //void printNameUpperCase();

    default void printNameUpperCase(){
        System.out.println("BAR");
    }

}
