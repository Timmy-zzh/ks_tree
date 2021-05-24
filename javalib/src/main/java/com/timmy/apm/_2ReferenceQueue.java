package com.timmy.apm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 */
public class _2ReferenceQueue {

    static class WorkObject {
        //1kb
        byte[] data = new byte[1024];
    }

    /**
     * 因为使用的直接 new WorkObject()，在虚拟机栈中没有引用，可以回收
     */
    public static void main(String[] args) {
//    public static void main_v1(String[] args) {
        //队列
        ReferenceQueue<WorkObject> referenceQueue = new ReferenceQueue<>();
        WeakReference<WorkObject> weakReference = new WeakReference<>(new WorkObject(), referenceQueue);
        System.out.println("----gc before:" + weakReference.get());
        System.out.println("====referenceQueue size:" + referenceQueue.poll());
        System.out.println();
        System.gc();

        // 软引用WeakReference 中的对象被回收后，会将该WeakReference对象存放到队列ReferenceQueue中
        System.out.println("----gc after:" + weakReference.get());
        System.out.println("====referenceQueue size:" + referenceQueue.poll());
    }

    /**
     * WorkObject workObject = new WorkObject();
     * 在虚拟机栈中有强引用（是GC Root），不可被回收
     */
    public static void main_v2(String[] args) {
//    public static void main(String[] args) {
        WorkObject workObject = new WorkObject();
        ReferenceQueue<WorkObject> referenceQueue = new ReferenceQueue<>();
        WeakReference<WorkObject> weakReference = new WeakReference<>(workObject, referenceQueue);
        System.out.println("----gc before:" + weakReference.get());
        System.out.println("====referenceQueue size:" + referenceQueue.poll());
        System.out.println();
        System.gc();

        //因为是强引用，所有没有回收，泄漏的对象没有存放到队列ReferenceQueue中
        System.out.println("----gc after:" + weakReference.get());
        System.out.println("====referenceQueue size:" + referenceQueue.poll());
    }

    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }
}
