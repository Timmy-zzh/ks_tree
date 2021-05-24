package com.timmy.apm;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 */
public class _1WorkReference {

    static class WorkObject {
        //1kb
        byte[] data = new byte[1024];
    }

    /**
     * 因为使用的直接 new WorkObject()，在虚拟机栈中没有引用，可以回收
     *
     * @param args
     */
    public static void main_v1(String[] args) {
//    public static void main(String[] args) {
        WeakReference<WorkObject> weakReference = new WeakReference<>(new WorkObject());
        System.out.println("----gc before:" + weakReference.get());
        System.gc();
        System.out.println("----gc after:" + weakReference.get());
    }

    /**
     * WorkObject workObject = new WorkObject();
     * 在虚拟机栈中有强引用（是GC Root），不可被回收
     */
//    public static void main_v1(String[] args) {
    public static void main(String[] args) {
        WorkObject workObject = new WorkObject();
        WeakReference<WorkObject> weakReference = new WeakReference<>(workObject);
        System.out.println("----gc before:" + weakReference.get());
        System.gc();
        System.out.println("----gc after:" + weakReference.get());
    }

    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }
}
