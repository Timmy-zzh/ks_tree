package com.timmy.ks_tree;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class Collection {

    public static void main(String[] args) {
        //数组
        int[] intArr = new int[3];

        //List
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = new LinkedList<>();
        List<Character> characterList = new Vector<>();
        //遍历
        Iterator<Integer> iterator = integerList.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
        }

        //set
        Set<Float> floatSet = new HashSet<>();
        Set<Double> doubleSet = new LinkedHashSet<>();
        Set<Double> hashSet = new HashSet<>();

        Iterator<Float> floatIterator = floatSet.iterator();
        while(floatIterator.hasNext()){
            Float next = floatIterator.next();
        }

        //Map
        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();
        Map<Integer, Integer> hashtable = new Hashtable<>();

        Set<Integer> keySet = hashMap.keySet();
        Iterator<Integer> integerIterator = keySet.iterator();
        while(integerIterator.hasNext()){
            Integer next = integerIterator.next();
            String value = hashMap.get(next);
        }

        Set<Map.Entry<Integer, String>> entrySet = treeMap.entrySet();
        Iterator<Map.Entry<Integer, String>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()){
            Map.Entry<Integer, String> entry = entryIterator.next();
            Integer key = entry.getKey();
            String value = entry.getValue();
        }


        //
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(1,"111");
        sparseArray.put(2,"222");
        sparseArray.put(3,"333");
        sparseArray.put(2,"213424");
        System.out.println(sparseArray.toString());


    }
}
