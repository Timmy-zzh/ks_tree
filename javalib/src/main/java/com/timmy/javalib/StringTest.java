package com.timmy.javalib;

public class StringTest {

    public static void main(String[] args) {
        String str = "123";
        String str2 = new String("123");
        String str3 = str + "456";

        System.out.println(str == str2);
        System.out.println(str.equals(str2));
        System.out.println(str==str3);
        System.out.println(str3.length());
    }
}
