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

        System.out.println("=================================");
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        String s5 = "a" + "b";
        String s6 = s1 + "b";
        StringBuffer s7 = new StringBuffer("abc");
        String s8 = s7.substring(3);
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);
        System.out.println(s4 == s6);
        System.out.println(s4 == s7.toString());
        System.out.println(s4 == s8);
    }
}
