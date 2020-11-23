package com.timmy.javalib._3class;

public class _2ClassLoader {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("------------------------");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("------------------------");
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
