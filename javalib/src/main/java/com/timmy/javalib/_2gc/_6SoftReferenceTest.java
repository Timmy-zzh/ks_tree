package com.timmy.javalib._2gc;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

/**
 * 软引用：只会在内存不足的情况下被回收
 * 在有集合持有的情况下处理
 */
public class _6SoftReferenceTest {

    static class SoftObject {
        //1kb
        byte[] data = new byte[1024];
    }

    public static int removeSoftRefs = 0;
    public static int CACHE_INITL_CAPACITY = 100 * 1024;
    //静态集合保存软引用，会导致这些软引用对象本身无法被垃圾回收器回收
    public static Set<SoftReference<SoftObject>> cache = new HashSet<>();

    public static ReferenceQueue<SoftObject> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) {
        for (int i = 0; i < CACHE_INITL_CAPACITY; i++) {
            SoftObject obj = new SoftObject();
            cache.add(new SoftReference<SoftObject>(obj, referenceQueue));

            clearUselessReference();
            if (i % 10000 == 0) {
                System.out.println("size of cache:" + cache.size());
            }
        }
        System.out.println("end removeSoftRefs:" + removeSoftRefs);
    }

    private static void clearUselessReference() {
        Reference<? extends SoftObject> reference = referenceQueue.poll();
        while (reference != null) {
            if (cache.remove(reference)) {
                removeSoftRefs++;
            }
            reference = referenceQueue.poll();
        }
    }

    private static void printMemeory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println(" ,total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }
}
