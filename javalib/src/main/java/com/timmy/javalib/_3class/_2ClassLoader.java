package com.timmy.javalib._3class;

public class _2ClassLoader {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    /**
     * 查看JVM提供的三个类加载器，加载的类有那些
     */
    private static void test1() {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("------------------------");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("------------------------");
        System.out.println(System.getProperty("sun.boot.class.path"));
    }

    /**
     * 类加载方法 loadClass
     */
    private static void test2() {
        String str = new String();
        _2ClassLoader demo = new _2ClassLoader();
        ClassLoader classLoader = demo.getClass().getClassLoader();
        try {
            Class<?> stringClass = classLoader.loadClass("java.lang.String");
//            stringClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(classLoader);
    }

    private static void test3() {
        _2ClassLoader demo = new _2ClassLoader();
        ClassLoader classLoader = _2ClassLoader.class.getClassLoader();
        System.out.println("classLoader is "+classLoader);

        ClassLoader parent = classLoader.getParent();
        System.out.println("parent is "+parent);

        ClassLoader bootstrp = parent.getParent();
        System.out.println("bootstrp is "+bootstrp);
    }
}
