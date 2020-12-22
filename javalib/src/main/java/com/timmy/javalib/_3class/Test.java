package com.timmy.javalib._3class;

import java.io.Serializable;

public class Test implements Serializable, Cloneable {

    private int num = 1;

    public synchronized int add(int i) {
        num = num + i;
        return num;
    }
}
