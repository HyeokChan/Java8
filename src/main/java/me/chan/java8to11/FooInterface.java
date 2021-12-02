package me.chan.java8to11;

public interface FooInterface {
    void printName();

    // default 키워드를 통해
    // 이 인터페이스를 구현한 모든 클래스에 메소드를 구현하지 않아도 기능을 제공할 수 있다.

    /**
     * default method
     * @implSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 출력한다.
     */
    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    /**
     * static method
     * 인터페이스만을 통해 호출할 수 있다.
     */
    static void printAnything() {
        System.out.println("Anything");
    }

    String getName();


}
