package com.timmy.lgsf._05backtrack_dp._3backtrack_bit;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _02二进制手表_401 {

    public static void main(String[] args) {
        _02二进制手表_401 demo = new _02二进制手表_401();
        List<String> res = demo.readBinaryWatch(2);
//        List<String> res = demo.readBinaryWatch(1);
        PrintUtils.printStr(res);
    }

    /**
     * 1.理解题意
     * -二进制表：
     * --小时范围[0,11] --通过4个位表示
     * --分钟范围[0,59] --通过6个位表示
     * -所以表盘上一共有10个led灯，用来表示当前的时间，当点亮了num个灯时，说明10个灯中有nums个等点亮，而这nums个等是可以随意组合的
     * 2。解题思路：回溯法+位运算+剪枝
     * -使用一个大小为2的数组保存最后的点亮的时间-[0,0] -->最后可能为[4,8] ->输出{"4:08"}
     * -遍历led灯，从第0个开始，第i个可以点亮小时，也可以点亮分钟；通过回溯处理
     * --已经点亮的led灯，第二次循环不可以重复点亮，可以通过位运算& 判断
     * 2.1.剪枝处理：小时最大为11，分钟最大为59
     *
     * @param num
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        //小时和分钟可选的集合
        int[] hours = {1, 2, 4, 8};
        int[] mins = {1, 2, 4, 8, 16, 32};
        //保存最后时间的结果
        int[] selectTime = new int[2];
        backtrack(res, hours, mins, num, selectTime, 0);
        return res;
    }

    private void backtrack(List<String> res, int[] hours, int[] mins, int n, int[] selectTime, int index) {
        if (index == n) {
            PrintUtils.print(selectTime);
            res.add(format(selectTime));
            return;
        }

        for (int i = 0; i < hours.length; i++) {
            if (selectTime[0] + hours[i] <= 11 && (selectTime[0] & hours[i]) == 0) {
                selectTime[0] += hours[i];
                backtrack(res, hours, mins, n, selectTime, index + 1);
                selectTime[0] -= hours[i];
            }
        }
        for (int i = 0; i < mins.length; i++) {
            if (selectTime[1] + mins[i] <= 59 && (selectTime[1] & mins[i]) == 0) {
                selectTime[1] += mins[i];
                backtrack(res, hours, mins, n, selectTime, index + 1);
                selectTime[1] -= mins[i];
            }
        }
    }

    //时间数组转换成字符串
    private String format(int[] selectTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(selectTime[0])
                .append(":");
        if (selectTime[1] < 10) {
            sb.append("0");
        }
        sb.append(selectTime[1]);
        return sb.toString();
    }

    /**
     * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
     * 每个 LED 代表一个 0 或 1，最低位在右侧。
     * 例如，上面的二进制手表读取 “3:25”。
     * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
     *
     * 示例：
     * 输入: n = 1
     * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     *  
     * 提示：
     * 输出的顺序没有要求。
     * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
     * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
     * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
     *
     * 链接：https://leetcode-cn.com/problems/binary-watch
     */
}
