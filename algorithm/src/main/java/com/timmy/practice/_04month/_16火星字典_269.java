package com.timmy.practice._04month;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 1.根据字符串出现顺序，构建图-邻接表
 * 2。根据邻接表进行深度优先遍历，并获取路径上的字符
 */
public class _16火星字典_269 {

    public static void main(String[] args) {
        _16火星字典_269 demo = new _16火星字典_269();
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String res = demo.alienOrder(words);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -给到一个字符串数组，每个字符串元素是一个火星单词，给到的字符串数组是按顺序排列的，不过和字母顺序不同
     * -现在根据给到的字符串数组，找出可以推断出的字母顺序
     * 2。解题思路
     * -数组中相邻字符串因为是排好序的，所以可以根据相邻两个字符串进行推导
     * --如果两个字符串，相同位置的字符不同，则前一个字符的顺序在后一个字符的前面
     * --如果相同位置的字符相同，则比较后面位置的字符，
     * 2。1。根据上面的字符顺序特性，可以使用邻接表的方式存储各个字符的顺序关系
     * --使用单个字符作为key，字符集合为value，为字符key后面顺序的字符集合
     * 2。2。根据表示顺序的图结构（邻接表），进行深度优先遍历
     * -dfs一条道走到底，先获取最后顺序的字符，并存放到栈Stack中，通过这样的遍历获取全部倒序排序的自负集合
     * 3。边界和细节问题
     * -重复遍历处理--visited控制
     * -环处理：Set集合判断该条链上的节点是否已经遍历过了，遍历过还遍历说明存在环
     */
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adj = new HashMap<>();
        //1。根据字符串数组中，两两字符串的顺序，构建邻接表
        int n = words.length;
        for (int i = 1; i < n; i++) {
            String preWord = words[i - 1];//前一个字符串
            String currWord = words[i]; //当前字符串

            //比较两个字符串中的字符
            int length1 = preWord.length();
            int length2 = currWord.length();

            int len = Math.max(length1, length2);
            for (int j = 0; j < len; j++) {
                Character preCh = j < length1 ? preWord.charAt(j) : null;
                Character currCh = j < length2 ? currWord.charAt(j) : null;
                //为字符准备空间
                if (preCh != null && !adj.containsKey(preCh)) {
                    adj.put(preCh, new ArrayList<Character>());
                }
                if (currCh != null && !adj.containsKey(currCh)) {
                    adj.put(currCh, new ArrayList<Character>());
                }
//                System.out.println("pre:" + preCh + " ,curr:" + currCh);
                //判断相同位置的两个字符是否相同
                if (preCh != null && currCh != null
                        && preCh != currCh
                        && !adj.get(preCh).contains(currCh)) {
//                    System.out.println("====");
                    adj.get(preCh).add(currCh);
                }
            }
        }

        Set<Map.Entry<Character, List<Character>>> entries = adj.entrySet();
        for (Map.Entry<Character, List<Character>> entry : entries) {
            System.out.println(entry.getKey() + " -- ");
            List<Character> value = entry.getValue();
            PrintUtils.printCh(value);
        }


        //2。对邻接表进行深度优先遍历，并使用Stack保存遍历到的字符
        // 需要方式重复遍历，和环出现处理
        Set<Character> visited = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        Set<Character> loop = new HashSet<>();

        for (Map.Entry<Character, List<Character>> entry : entries) {
            if (!visited.contains(entry.getKey())) {
                if (!dfs(adj, entry.getKey(), visited, stack, loop)) {
                    return "";
                }
            }
        }

        //将栈中的节点输出
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 深度优先遍历：
     * -根据当前遍历的节点，查找与之相邻的节点集合，对集合遍历并进行深度优先遍历
     * --为防止重复遍历，对相邻节点先判断是否已经遍历过，遍历过则过滤
     * -为防止出现环，在这条路径上，需要将已经遍历过的节点保存到loop集合中，如果当前节点的相邻节点在loop存在
     * --则说明存在环，直接返回，推出递归需要将路径上的节点删除
     * -遍历到路径最后一个元素才添加节点
     *
     * @param adj
     * @param preCh
     * @param visited
     * @param stack
     * @param loop
     * @return
     */
    private boolean dfs(Map<Character, List<Character>> adj,
                        Character preCh,
                        Set<Character> visited,
                        Stack<Character> stack,
                        Set<Character> loop) {
        System.out.println("dfs:" + preCh);
        visited.add(preCh);
        loop.add(preCh);

        //相邻节点集合
        List<Character> linkNodes = adj.get(preCh);
        for (int i = 0; i < linkNodes.size(); i++) {
            Character linkNode = linkNodes.get(i);
            if (loop.contains(linkNode)) {   //相邻节点已在dfs路径上，存在环，退出
                return false;
            }
            if (!visited.contains(linkNode)) {
                dfs(adj, linkNode, visited, stack, loop);
            }
        }

        loop.remove(preCh);
        stack.push(preCh);
        return true;
    }

    /**
     * LeetCode  第 269 题，火星字典：现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。
     * 假设，您并不知道其中字母之间的先后顺序。但是，会收到词典中获得一个不为空的单词列表。
     * 因为是从词典中获得的，所以该单词列表内的单词已经按这门新语言的字母顺序进行了排序。
     * 您需要根据这个输入的列表，还原出此语言中已知的字母顺序。
     *
     * 示例 1
     * 输入:
     * [ "wrt", "wrf","er","ett", "rftt"]
     * 输出: "wertf"
     *
     * 示例 2
     * 输入:
     * [ "z", "x"]
     * 输出: "zx"
     *
     * 示例 3
     * 输入:
     * [ "z",  "x","z"] 
     * 输出: "" 
     * 解释: 此顺序是非法的，因此返回 ""。
     */
}
