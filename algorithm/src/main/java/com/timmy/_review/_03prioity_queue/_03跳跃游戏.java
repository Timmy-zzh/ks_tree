package com.timmy._review._03prioity_queue;

public class _03跳跃游戏 {

    public static void main(String[] args) {
        _03跳跃游戏 demo = new _03跳跃游戏();
        int[] heights = {3, 1, 6, 20, 10, 20};
        int res = demo.furthestBuilding(heights, 5, 1);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入一个数组，表示阶梯的高度，现在需要从第一个阶梯开始往后移动，如果后面的阶梯低或高度相等，可直接移动
     * -如果后面的阶梯更高，可使用砖块或梯子进行移动，不过砖块和梯子数目有限
     * 2。模拟运行
     * -遍历所有阶梯，当遇到后面阶梯更高时，可以使用砖块或梯子进行移动，因为梯子可以无视高度差
     * -所以需要先使用砖块进行替补，如果砖块不够再使用梯子
     * 3。复杂度分析
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
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
