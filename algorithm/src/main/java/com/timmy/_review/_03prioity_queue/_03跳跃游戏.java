package com.timmy._review._03prioity_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _03跳跃游戏 {

    public static void main(String[] args) {
        _03跳跃游戏 demo = new _03跳跃游戏();
        int[] heights = {3, 1, 6, 20, 10, 20};
        int res = demo.furthestBuilding(heights, 5, 1);
        System.out.println("res:" + res);
    }

    /**
     * 1。理解题意
     * -输入一个数组，数组元素表示每个阶梯的高度，现在要从第一个阶梯往后移动
     * -移动规则如下：
     * --后面阶梯更低，则直接移动
     * --后面阶梯更高，可以使用砖块，和梯子，其中砖块数量一定，梯子可以忽略高度差，且数量一定
     * 2。模拟运行
     * 优先级队列解法：使用优先级队列保存每个阶梯的高度差
     * -遍历所有阶梯，如果下一个阶梯更低，则直接移动
     * -如果下一个阶梯更高，则将高度差保存到优先级队列中（大顶堆），然后判断砖块的总体消耗数
     * -如果砖块够用，则往后移动，如果砖块不够用，则用梯子，梯子也用完了，则移动不了了
     * 2.2.核心：先用砖块，再用梯子
     * -使用梯子要用梯子消耗掉最大的高度
     * 3。复杂度分析
     * -时间：n
     * -空间：n
     * 4。总结
     * -优先级队列保存了所有的高度差，相当于记事本一样的功能
     * -当砖块不够用的时候，这个时候需要使用梯子，那梯子消耗在哪一个高度差，才能使位置移动最靠后？
     * -使用优先级队列保存所有的高度差，当使用梯子时，消耗掉最大的高度差
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int res = 0;

        //大顶堆保存所有的高度差，
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t2 - t1;
            }
        });

        int preH = heights[0];
        int diffH;//高度差
        int costSum = 0;//需要消耗的砖块总数

        for (int i = 1; i < heights.length; i++) {
            diffH = heights[i] - preH;

            if (diffH <= 0) {
                res = i;
            } else {
                //如果砖块不够用，则需要使用梯子
                costSum += diffH;
                queue.add(diffH);

                while (!queue.isEmpty() && costSum > bricks && ladders > 0) {
                    //先用梯子消耗掉最高的高度差
                    costSum -= queue.poll();
                    ladders--;
                }

                //砖块
                if (costSum <= bricks) {
                    res = i;
                } else {
                    break;
                }
            }
            preH = heights[i];
        }

        return res;
    }

    /**
     * todo error
     * 1.理解题意
     * -输入一个数组，表示阶梯的高度，现在需要从第一个阶梯开始往后移动，如果后面的阶梯低或高度相等，可直接移动
     * -如果后面的阶梯更高，可使用砖块或梯子进行移动，不过砖块和梯子数目有限
     * 2。模拟运行
     * -遍历所有阶梯，当遇到后面阶梯更高时，可以使用砖块或梯子进行移动，因为梯子可以无视高度差
     * -所以需要先使用砖块进行替补，如果砖块不够再使用梯子
     * 3。复杂度分析
     * 4。总结：
     * -砖块够用，就用砖块，这个逻辑是不对的，当砖块够多时，前面出现的大的高度差使用了砖块填充，
     * -会导致后面小的高度差不够砖块而使用梯子进行消耗，而这个提交消耗的高度差可能很小
     * --解决方法：将所有的高度差进行保存，当砖块不够时，是用梯子消耗掉最大的高度差
     */
    public int furthestBuilding_v1(int[] heights, int bricks, int ladders) {
        int res = 0;
        int preH = heights[0];
        for (int i = 1; i < heights.length; i++) {
            //高度差
            int diff = heights[i] - preH;
            preH = heights[i];
            if (diff <= 0) {
                res = i;
            } else {
                // 后面阶梯更高，-》先使用砖块，再使用楼梯
                if (bricks >= diff) {
                    bricks -= diff;
                    res = i;
                } else if (ladders > 0) {
                    ladders--;
                    res = i;
                } else {
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 【题目】假设你正在玩跳跃游戏，从低处往高处跳的时候，可以有两种方法。
     * 方法一：塞砖块，但是你拥有砖块数是有限制的。为了简单起见，高度差就是你需要砖块数。
     * 方法二：用梯子，梯子可以无视高度差（你可以认为再高也能爬上去），但是梯子的个数是有限的(一个只能用一次)。
     * 其他无论是平着跳，还是从高处往低处跳，不需要借助什么就可以完成（在这道题中我们默认无论从多高跳下来，也摔不死）。
     *
     * 给你一个数组，用来表示不同的高度。假设你总是站在 index = 0 的高度开始。那么请问，你最远能跳到哪里?
     * 输入：[3, 1, 6, 20, 10, 20], bricks = 5, landers = 1
     * 输出：4
     *
     * 解释：
     * Step 1. 从 3 跳到 1 时，因为是从高往低处跳，直接跳就可以了
     * Step 2. 从 1 到 6 时，用掉 5 个砖块
     * Step 3. 从 6 到 20 时，用掉梯子
     * Step 4. 从 20 到 10 可以直接跳
     * Step 5.到 10 这里就停住了，没有东西可以帮助你跳到 20 了，所以只能跳到下标 index = 4 这里。
     */
}
