package com.timmy.javalib._2gc;

import java.lang.ref.SoftReference;

/**
 * 软引用：只会在内存不足的情况下被回收
 */
public class _6SoftReferenceNormal {

    static class SoftObject {
        //120M
        byte[] data = new byte[120 * 1024 * 1024];
    }

    public static void main(String[] args) {
        printMemeory();
        SoftReference<SoftObject> softReference = new SoftReference<>(new SoftObject());
        printMemeory();
        System.out.println("GC前 软引用：" + softReference.get());
        System.gc();
        System.out.println("GC后 软引用：" + softReference.get());
        printMemeory();
        //再分配120M内存
        SoftObject softObject = new SoftObject();
        System.out.println("再分配120M内存 软引用：" + softReference.get());
        printMemeory();
    }

    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }
}
