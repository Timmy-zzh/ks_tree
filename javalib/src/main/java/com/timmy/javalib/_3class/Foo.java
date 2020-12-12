package com.timmy.javalib._3class;

public class Foo {

    public static void main(String[] args) {
//        new Foo().print();
//        ClassInit.value = 2;
//        ClassInit classInit = new ClassInit();

        Child.value = 3;
    }

    public void print() {
        int superCode = super.hashCode();
        System.out.println("superCode is:" + superCode);

        int thisCode = hashCode();
        System.out.println("thisCode is:" + thisCode);
    }

    @Override
    public int hashCode() {
        return 123;
    }
}
