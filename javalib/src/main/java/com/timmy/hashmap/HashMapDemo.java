package com.timmy.hashmap;

import java.util.HashMap;

public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "111");
        hashMap.put(4, "234");
        System.out.println(hashMap.toString());
        String s = hashMap.get(4);
        System.out.println(s);
        System.out.println(hashMap.toString());
    }
}
