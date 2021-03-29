package com.timmy._00review._00recursive;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;


public class _03找到所有长度为n的中心对称数_247 {

    public static void main(String[] args) {
        _03找到所有长度为n的中心对称数_247 demo = new _03找到所有长度为n的中心对称数_247();
        List<String> res = demo.helper(4);
        PrintUtils.printStr(res);
    }

    /**
     * 1.理解题意
     * -中心对称数，当只有一个数字时，存在三种情况是对称数： 0,1,8
     * -有两个数字时，对应的对称数有：11，69，88，96
     * -有三个数字时，在只有1个数字的结果基础上，在左右添加11，69，88，96，最终结果为：
     * [101，609,808,906] , [111,619,818,916], [181,689,888,986]
     * -当n>3时，存在的对称数结果为n-2的结果遍历后，每个元素的前后都添加11，69，88，96
     *
     * @param n
     * @return
     */
    public List<String> helper(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        if (n == 1) {
            res.add("0");
            res.add("1");
            res.add("8");
            return res;
        }
        if (n == 2) {
            res.add("11");
            res.add("69");
            res.add("88");
            res.add("96");
            return res;
        }
        List<String> helper = helper(n - 2);
        for (String s : helper) {
            res.add("1"+s+"1");
            res.add("6"+s+"9");
            res.add("8"+s+"8");
            res.add("9"+s+"6");
        }
        return res;
    }

    /**
     * 示例
     * 输入:  n = 2
     * 输出: ["11","69","88","96"]
     */
}
