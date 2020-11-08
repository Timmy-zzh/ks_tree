package com.timmy.javalib._1memory;

public class JvmDemo {

    public static void main(String[] args) {
        add(0);
    }

    public static int add(int k) {
        int a = 1;
        int b = 2;
        return k + a + b;
    }

    public int add1() {
        int i = 15;
        int j = 2;
        int result = i + j;
        return result + 10;
    }


}
