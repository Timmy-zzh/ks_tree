package com.timmy.javalib._3class;

public class ClassInit {

    public static int value = 100;

    //静态代码块在初始化阶段执行
    static {
        System.out.println("ClassInit static block ");
    }

    //非静态代码块只在创建对象实例时被执行
    {
        System.out.println("ClassInit not static block ");
    }
}
