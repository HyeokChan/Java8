package me.chan.java8to11;

@FunctionalInterface
public interface RunSomething {

    // 인터페이스에 추상 메소드가 1개면 함수형 인터페이스가 된다.
    int doIt(int number);

}
