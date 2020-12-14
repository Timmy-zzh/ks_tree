package com.timmy.testlib;

public class Demo {

    public static int staticValue = 123;
    public int localValue = 234;

    public static void main(String[] args) {
        Demo.staticValue = 333;
        Demo demo = new Demo();
        demo.setStaticValue(444);
    }

    public void setStaticValue(int staticValue) {
        Demo.staticValue = staticValue;
    }
}
