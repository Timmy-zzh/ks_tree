package com.timmy._review._01stack;

import java.util.Stack;

public class _03大鱼吃小鱼 {

    public static void main(String[] args) {
        _03大鱼吃小鱼 demo = new _03大鱼吃小鱼();
        int[] Size = {4, 2, 5, 3, 1};
        int[] Dir = {1, 1, 0, 0, 0};
        int res = demo.solution(Size, Dir);
        System.out.println("res:" + res);
    }

    /**
     * 1.理解题意
     * -输入两个int数组，两个数组长度相等，一个表示鱼的大小，一个表示鱼的游动方向
     * -当鱼的游动方向相同后者相背时，没有问题
     * -当两条鱼游动方向相对时，小的鱼会被大的鱼吃掉
     * 2。模拟运行
     * -遍历一条条鱼，使用栈保存留下来的鱼的下标
     * -当栈为空时，鱼入栈
     * -当栈中有鱼时，判断栈顶的鱼，与当前遍历的鱼，进行大小和方向的判断
     * --方向情况：
     * ---两条鱼同一方向游动：新鱼入栈
     * ---两条鱼不同方向游动：
     * ----栈顶鱼向左游，新鱼向右游动：新鱼入栈
     * ----栈顶鱼向右游，新鱼向左游动：判断鱼大小
     * ------如果新鱼比栈顶鱼size 更小，新鱼被吃掉
     * ------如果新鱼比栈顶鱼更大，则栈顶鱼被吃掉，-在用新鱼与栈中剩下的鱼进行大小和游动方向的判断，看是否还满足栈顶鱼出栈
     * --最后更大的鱼入栈
     * 3.细节与边界问题
     * -一次可能出栈多个元素，知道满足条件为止
     * -栈中元素保存的是鱼的下标位置
     */
    public int solution(int[] Size, int[] Dir) {
        if (Size.length <= 1) {
            return Size.length;
        }
        int RIGHT = 1;
        int LEFT = 0;

        //使用栈保存鱼位置的下标
        Stack<Integer> stack = new Stack<>();
        //遍历所有的鱼，
        int length = Size.length;
        for (int i = 0; i < length; i++) {
            //新鱼的大小和方向
            int size = Size[i];
            int dir = Dir[i];
            boolean isEat = false;//    新鱼是否被吃掉
            //新鱼方向向左，栈顶鱼方向向右
            while (!stack.isEmpty() && dir == LEFT && Dir[stack.peek()] == RIGHT) {
                //判断大小,新鱼更大-出栈；新鱼更小-被吃掉，不出栈
                if (size < Size[stack.peek()]) {
                    isEat = true;
                    break;
                }
                //,新鱼更大-出栈；
                stack.pop();
            }
            //新鱼更大,没有被吃掉，入栈
            if (!isEat) {
                stack.push(i);
            }
        }
        return stack.size();
    }

    /**
     * 在水中有许多鱼，可以认为这些鱼停放在 x 轴上。再给定两个数组 Size，Dir，
     * Size[i] 表示第 i 条鱼的大小，
     * Dir[i] 表示鱼的方向 （0 表示向左游，1 表示向右游）。
     *
     * 这两个数组分别表示鱼的大小和游动的方向，并且两个数组的长度相等。鱼的行为符合以下几个条件:
     *
     * 所有的鱼都同时开始游动，每次按照鱼的方向，都游动一个单位距离；
     * 当方向相对时，大鱼会吃掉小鱼；
     * 鱼的大小都不一样。
     *
     * 输入：Size = [4, 2, 5, 3, 1], Dir = [1, 1, 0, 0, 0]
     * 输出：3
     */
}
