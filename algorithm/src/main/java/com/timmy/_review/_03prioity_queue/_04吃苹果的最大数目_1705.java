package com.timmy._review._03prioity_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _04吃苹果的最大数目_1705 {

    public static void main(String[] args) {
        _04吃苹果的最大数目_1705 demo = new _04吃苹果的最大数目_1705();
//        int[] apples = {1, 2, 3, 5, 2};
//        int[] days = {3, 2, 1, 4, 2};
        int[] apples = {3, 0, 0, 0, 0, 2};
        int[] days = {3, 0, 0, 0, 0, 2};
        int res = demo.eatenApples(apples, days);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -有一棵特殊的树，在n天时间，每天会长出苹果 apple[i] ; 且这些苹果会在几天时间后腐烂 days[i] ,无法食用
     * -在n天时间内，也可能不长苹果，这是apple[i] =0; days[i]=0
     * -一天吃一个苹果，最多可以吃多少个苹果
     * 2。模拟运行
     * -每天长出来的苹果，先保存起来，然后去掉已经过期的苹果，最后吃掉一个苹果（吃掉保质期最短的苹果）
     * -将每天长出的苹果和对应的保质期封装成一个类
     * -使用优先级队列（小顶堆，保质期判断），保存每天长出的苹果，今天新长出的苹果进行保存
     * -去除到今天时已经过期的苹果
     * -今天吃掉一个苹果，要求该苹果的保质期最短
     */
    public int eatenApples(int[] apples, int[] days) {
        int eatNum = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.bad - n2.bad;
            }
        });

        int n = apples.length;
        for (int i = 0; i < n || !queue.isEmpty(); i++) {
            //1.今天生成的苹果保存
            if (i < n && apples[i] > 0) {
                queue.offer(new Node(apples[i], days[i] + i));
            }

            //2.去除过期的苹果
            while (!queue.isEmpty() && queue.peek().bad <= i) {
                queue.poll();
            }

            //3.今天消耗的苹果
            if (!queue.isEmpty()) {
                Node node = queue.peek();
                node.num--;
                eatNum++;
                if (node.num == 0) {
                    queue.poll();
                }
            }
        }
        return eatNum;
    }

    class Node {
        //苹果数量
        public int num;
        //苹果过期时间
        public int bad;

        public Node(int num, int bad) {
            this.num = num;
            this.bad = bad;
        }
    }

    /**
     * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这
     * 些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。
     * 也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
     *
     * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
     * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
     *
     * 示例 1：
     * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
     * 输出：7
     * 解释：你可以吃掉 7 个苹果：
     * - 第一天，你吃掉第一天长出来的苹果。
     * - 第二天，你吃掉一个第二天长出来的苹果。
     * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
     * - 第四天到第七天，你吃的都是第四天长出来的苹果。
     *
     * 示例 2：
     * 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
     * 输出：5
     * 解释：你可以吃掉 5 个苹果：
     * - 第一天到第三天，你吃的都是第一天长出来的苹果。
     * - 第四天和第五天不吃苹果。
     * - 第六天和第七天，你吃的都是第六天长出来的苹果。
     *
     * 提示：
     * apples.length == n
     * days.length == n
     * 1 <= n <= 2 * 104
     * 0 <= apples[i], days[i] <= 2 * 104
     * 只有在 apples[i] = 0 时，days[i] = 0 才成立
     *
     * 链接：https://leetcode-cn.com/problems/maximum-number-of-eaten-apples
     */
}
