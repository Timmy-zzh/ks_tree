package com.timmy.lgsf._01basic._1array;

public class _00整数反转_7 {

    public static void main(String[] args) {
        _00整数反转_7 demo = new _00整数反转_7();
        int[] arr = new int[]{123, -345, Integer.MIN_VALUE, Integer.MAX_VALUE};
        for (int i = 0; i < arr.length; i++) {
            int reverser = demo.reverser(arr[i]);
            System.out.println(arr[i] + " --> " + reverser);
        }
    }

    /**
     * 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * 输入: 123
     * 输出: 321
     * <p>
     * 示例 2:
     * 输入: -123
     * 输出: -321
     * <p>
     * 示例 3:
     * 输入: 120
     * 输出: 21
     * <p>
     * 注意:
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
     * 请根据这个假设，如果反转后整数溢出那么就返回 0。
     */

    /**
     * 最优解法：使用数学思维特性
     * 1。循环拿到数字的个位数， -- 不断取模
     * 2。然后再处理成反转的结果 -- 原来基础上*10 + 新数字
     */
    public int reverser(int x) {
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE) {
            return 0;
        }
        //符号处理
        int sign = x < 0 ? -1 : 1;
        x = x * sign;

        int result = 0;
        int last;
        while ((last = x % 10) != x) {
            result = result * 10 + last;
            x = x / 10;
        }

        //最高位处理,防止数据溢出，使用long类型接收
        if (last != 0) {
            long longV = result;
            longV = longV * 10 + last;
            if (longV > Integer.MAX_VALUE) {
                result = 0;
            } else {
                result = (int) longV;
            }
        }

        return result * sign;
    }

    /**
     * 解题思路：首尾交换处理
     * 1。整数转字符串，字符串转字符数组
     * 2。遍历字符数组，数组元素首尾交换
     * 3。字符数组转字符串，字符串转整数
     */
    public int reverser_v2(int x) {
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE) {
            return 0;
        }
        //符号处理
        int sign = x < 0 ? -1 : 1;
        x = x * sign;

        //整数-》字符串-》字符数组
        String strX = String.valueOf(x);
        char[] chars = strX.toCharArray();

        //字符数组遍历，并用新数组接收存储
        int length = chars.length;
        for (int i = 0, j = length - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        //新字符数组-》字符，-》转整数
        String newStr = String.valueOf(chars);
        //防止数值溢出，使用long类型接收
        long longV = Long.valueOf(newStr);
        boolean b = longV > Integer.MAX_VALUE || longV < Integer.MIN_VALUE;
        int newInt = b ? 0 : (int) longV;
        return newInt * sign;
    }

    /**
     * 解题思路：
     * ==将整数反转然后输出
     * 1。先将整数转成字符串，字符串再转成字符数组
     * 2。遍历字符数组，将遍历得到的字符保存到新的字符数组中，（用新字符数组接收）
     * 3。将新的字符数组转换成整数
     * ==边界处理
     * ==细节处理：
     * 1。符号位处理
     * 2。int类型数据溢出
     */
    public int reverser_v1(int x) {
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE) {
            return 0;
        }
        //符号处理
        int sign = x < 0 ? -1 : 1;
        //x = x < 0 ? x * -1 : x;
        x = x * sign;

        //整数-》字符串-》字符数组
        String strX = String.valueOf(x);
        char[] chars = strX.toCharArray();

        //字符数组遍历，并用新数组接收存储
        char[] newChars = new char[chars.length];
        int length = chars.length;
        // [123] --> [321]
        for (int i = 0; i < length; i++) {
            newChars[i] = chars[length - 1 - i];
        }

        //新字符数组-》字符，-》转整数
        String newStr = String.valueOf(newChars);
        //防止数值溢出，使用long类型接收
        long longV = Long.valueOf(newStr);
        boolean b = longV > Integer.MAX_VALUE || longV < Integer.MIN_VALUE;
        int newInt = b ? 0 : (int) longV;
//        Integer.parseInt(newStr);
        return newInt * sign;
    }
}
