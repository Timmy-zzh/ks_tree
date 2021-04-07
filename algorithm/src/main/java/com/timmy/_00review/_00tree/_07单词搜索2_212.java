package com.timmy._00review._00tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TrieNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 字典树复习：
 * 1。字典树的创建
 * 2。字典树的搜索
 * <p>
 * TODO ：字典树+二维矩阵遍历（DFS）+ 回溯算法
 * --深入研究
 */
class _07单词搜索2_212 {

    public static void main(String[] args) {
        _07单词搜索2_212 demo = new _07单词搜索2_212();
//        char[][] board = {
//                {'o', 'a', 'a', 'n'},
//                {'e', 't', 'a', 'e'},
//                {'i', 'h', 'k', 'r'},
//                {'i', 'f', 'l', 'v'}};
//        String[] words = {"oath", "pea", "eat", "rain"};
//
//        char[][] board = {
//                {'a', 'a'}};
//        String[] words = {"aaa"};

//        char[][] board = {
//                {'a', 'b'},
//                {'c', 'd'}};
//        String[] words = {"abcb"};

        char[][] board = {
                {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain", "oathi", "oathk", "oathf", "oate", "oathii", "oathfi", "oathfii"};

//        char[][] board = {
//                {'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}};
//        String[] words = {"oa", "oaa"};


        List<String> res = demo.findWords(board, words);
        System.out.println("--res :");
        PrintUtils.printStr(res);
    }

    /**
     * 1。理解题意
     * -输入一个字符矩阵，和一个单词数组，找出在字符矩阵和单词数组中都出现的单词
     * --将给定的字符串数组构建成字典树，字符矩阵的每个元素位置的字符都可以作为单词的开头
     * --矩阵中的字符组成单词的规则是沿着横竖四个方向延伸
     * 2。解题思：字典树+深度优先遍历
     * -将给定的单词数组构建成为一个字典树
     * -然后遍历二维字符矩阵，以遍历到的字符作为单词字典树的头节点，进行深度优先遍历，
     * -如果字典树节点的isEnd为true，说明找到一个结果，并存放到集合中
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        _07DictionaryTree dictionaryTree = new _07DictionaryTree();
        dictionaryTree.build(words);
        dictionaryTree.print();
        boolean[][] visited;

        TrieNode node = dictionaryTree.getRoot();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                visited = new boolean[board.length][board[i].length];
                visited[i][j] = true;
                int index = board[i][j] - 'a';
                if (node.children[index] != null) {
                    dfs(node.children[index], board, i, j, set, visited);
                }
            }
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            res.add(iterator.next());
        }
        return res;
    }

    /**
     * 先判断当前节点的isEnd是否true，为true则添加到res集合中
     * 否则：
     * -从[i,j] 四周进行判断存在node节点的下个节点
     *
     * @param node
     * @param board
     * @param i
     * @param j
     * @param res
     */
    int[][] direct = new int[][]{
            {-1, 0}, //左
            {1, 0}, //右
            {0, -1}, //上
            {0, 1}, //下
    };

    private void dfs(TrieNode node, char[][] board, int i, int j, Set<String> res, boolean[][] visited) {
        System.out.println("i:" + i + " ,j:" + j + " ,node:" + node.word);
        if (node.isEnd) {
            res.add(node.word);
        }
        //四个方向
        for (int[] dir : direct) {
            //判断其中一个方向dir是否合法
            int newX = dir[0] + i;
            int newY = dir[1] + j;
            if (check(newX, newY, board) && !visited[newX][newY]) {
                int index = board[newX][newY] - 'a';
                if (node.children[index] != null) {
                    visited[newX][newY] = true;
                    dfs(node.children[index], board, newX, newY, res, visited);
                    visited[newX][newY] = false;
                }
            }
        }
    }

    private boolean check(int newX, int newY, char[][] board) {
        int row = board.length;
        int col = board[0].length;
        return newX >= 0 && newX < row && newY >= 0 && newY < col;
    }

    /**
     * 字典树复习：
     * -创建
     * -检索
     * -插入
     */
    public List<String> findWords_v1(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        _07DictionaryTree dictionaryTree = new _07DictionaryTree();
        dictionaryTree.build(words);
        dictionaryTree.print();

        // 在字典树中搜索某个单词是否存在
        boolean searchRes = dictionaryTree.search("eat");
        System.out.println("searchRes:" + searchRes);

        // 在当前字典树中插入一个单词
        dictionaryTree.insert("eatt");
        searchRes = dictionaryTree.search("eayt");
        System.out.println("---searchRes:" + searchRes);

        return res;
    }

    /**
     * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，
     * 找出所有同时在二维网格和字典中出现的单词。
     * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母在一个单词中不允许被重复使用。
     *
     * 示例 1：
     * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
     * words = ["oath","pea","eat","rain"]
     * 输出：["eat","oath"]
     *
     * 示例 2：
     * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
     * 输出：[]
     *
     * 提示：
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 12
     * board[i][j] 是一个小写英文字母
     * 1 <= words.length <= 3 * 104
     * 1 <= words[i].length <= 10
     * words[i] 由小写英文字母组成
     * words 中的所有字符串互不相同
     *
     * 链接：https://leetcode-cn.com/problems/word-search-ii
     */
}
