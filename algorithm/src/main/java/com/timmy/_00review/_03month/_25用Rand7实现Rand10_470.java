package com.timmy._00review._03month;

import java.util.Random;

/**
 * 经典题解：
 * https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/cong-pao-ying-bi-kai-shi-xun-xu-jian-jin-ba-zhe-da/#comment
 */
public class _25用Rand7实现Rand10_470 {

    public static void main(String[] args) {
        _25用Rand7实现Rand10_470 demo = new _25用Rand7实现Rand10_470();

        //求抛硬币的结果[0,1]概率
        int n = 10000;
        int[] coins = new int[2];
        for (int i = 0; i < n; i++) {
            // res结果是抛硬币的结果{0,1}
            int res = demo.rand7();
            System.out.println(res);
//            coins[res]++;
        }
        System.out.println("0概率次数：" + coins[0]);
        System.out.println("1概率次数：" + coins[1]);
    }

    /**
     * 前置知识点1：
     * 1。抛硬币，原先概率不均匀，产生概率均匀的结果
     * 2。抛硬币，原先概率均匀，要求产生概率不均匀的结果
     * 3。已知rand_M 的概率，求rand_N
     * <p>
     * -1.例如，抛硬币正面与反面[0,1],结果概率为[0.4,0.6],要 求抛硬币后正与反面的概率相同为0.5
     * --抛一次的概率为[0.4,0.6] 那抛两次的结果如下：
     * [0,0] = 0.4*0.4 = 0.16
     * [1,0] = 0.6*0.4 = 0.24
     * [0,1] = 0.4*0.6 = 0.24
     * [1,1] = 0.6*0.6 = 0.36
     * ==》 可以看出结果[1,0] 与 [0,1] 的抛币概率相同，则我们可以取这两种结果
     * --如果不是这两种结果，则继续抛硬币
     */

    public int coin_new1() {
        while (true) {
            int a = coin1();//第一次抛硬币结果
            //与第二次抛硬币结果相比较
            //不相同的概率，相等
            if (coin1() != a) {
                return a;
            }
        }
    }

    //抛硬币正面与反面[0,1],结果概率为[0.4,0.6],
    public int coin1() {
        return 1;
    }


    /**
     * 前置知识点1：
     * 2。抛硬币，原先概率均匀，要求产生概率不均匀的结果
     * <p>
     * -1.例如，原先抛硬币的结果[0,1]的概率为[0.5,0.5] ,现在要让抛硬币的结果[0,1]概率为[1/4,3/4]
     * -因为一次抛硬币结果[0,1]的概率为[0.5,0.5]
     * --两次抛硬币的概率为：
     * [0,0] = 0.5*0.5 = 0.25
     * [0,1] = 0.5*0.5 = 0.25
     * [1,0] = 0.5*0.5 = 0.25
     * [1,1] = 0.5*0.5 = 0.25
     * --四种结果的概率都为1/4；则对两次抛硬币的结果进行&处理，
     * 我们现在选定两次抛硬币的结果为[1,1]则返回1，其他的氢气返回0
     */

    public int coin_new2() {
//        while (true) {
//            int a = coin2();
//            int b = coin2();
//            if ((a & b) == 1) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }

        return coin2() | coin2();
    }

    //抛硬币正面与反面[0,1],结果概率为[0.5,0.5],
    public int coin2() {
        return 1;
    }


    /**
     * 1.理解题意
     * -根据rand7的求rand10的结果
     * 2.解题思路
     * -rand7的值范围为[1,7] , 而求rand10 的值范围为[1,10]
     * - rand7()-1 的值范围是[0,6], -->推出 (rand7-1)*7 的值范围为 [0,42]
     * -再加上rand7 --》   (rand7-1)*7 + rand7 的值范围为[1,49]
     * 则我们可以取1到40范围的值，然后进行求余得到[1,10]范围的结果
     *
     * @return
     */
    public int rand10() {
        while (true) {
            //res范围[1,49]
            int res = (rand7() - 1) * 7 + rand7();
            //取[1,40]
            if (res <= 40) {
                return res % 10 + 1;
            }

            //大于40范围[41,49]，取[1,9]继续处理
            res = res - 40;  //rand9
            res = (res - 1) * 7 + rand7();    //rand63
            if (res <= 60) {
                return res % 10 + 1;
            }

            //大雨60 --范围值在[61.63]
            res = res - 60; //rand3
            res = (res - 1) * 7 + rand7(); //rand21
            if (res <= 20) {
                return res % 10 + 1;
            }
        }
    }

    private Random random = new Random();

    public int rand7() {
        return random.nextInt(8);
    }

    /**
     * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
     * 不要使用系统的 Math.random() 方法。
     *
     * 示例 1:
     * 输入: 1
     * 输出: [7]
     *
     * 示例 2:
     * 输入: 2
     * 输出: [8,4]
     *
     * 示例 3:
     * 输入: 3
     * 输出: [8,1,10]
     *  
     * 提示:
     * rand7 已定义。
     * 传入参数: n 表示 rand10 的调用次数。
     *
     * 进阶:
     * rand7()调用次数的 期望值 是多少 ?
     * 你能否尽量少调用 rand7() ?
     *
     * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
     */

}
