package me.chan.java8to11;

public class NamePrintImpl implements NamePrint {

    String name;

    public NamePrintImpl(String name) {
        this.name = name;
    }

    /*@Override
    public void printNameUpperCase() {
        // 재정의 가능
        System.out.println("hello "+this.name.toUpperCase());
    }*/

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
