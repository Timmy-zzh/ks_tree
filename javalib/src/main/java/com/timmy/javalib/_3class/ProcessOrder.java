package com.timmy.javalib._3class;

public class ProcessOrder {

    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println("=======================");
        p = new Child();
    }

    static class Child extends Parent {
        static {
            System.out.println("Child static block");
        }

        {
            System.out.println("Child not-static block");
        }

        public Child() {
            System.out.println("Child constructor");
        }
    }

    static class Parent {

        static {
            System.out.println("parent static block");
        }

        {
            System.out.println("parent not-static block");
        }

        public Parent() {
            System.out.println("parent constructor");
        }
    }
}
