package com.timmy._review._08binary_search._00binary_search;

public class _03不定长的边界 {

    public static void main(String[] args) {
        _03不定长的边界 demo = new _03不定长的边界();
        String[] times = new String[]{
                "20210101", "202101012", "20210103", "20210103",
                "20210103", "20210103", "20210103", "20210103",
                "20210103", "20210103", "20210103", "20210103",
                "20210103", "20210103", "20210103", "20210103",
                "20210103", "20210103", "20210103", "20210103",
                null, null, null, null};
        int length = demo.getTimesLength(times);
        System.out.println("res:" + length);
    }

    /**
     * 1.理解题意
     * -不知道具体长度的日志问题，要求日志的长度，日志的长度是从到某一个位置的值不为null
     * 2。解题思路
     * -采用二分搜索法：
     * --先找到为null的长度，right
     * --然后从[0,right]范围中，继续搜索找到最后一个不为null的元素，即是日志长度
     */
    private int getTimesLength(String[] times) {
//        int length = 1;
//        while (times[length] != null && length * 2 < times.length) {
//            length = length * 2;
//        }
//        System.out.println("length:" + length);
        //
        int low = 0, high = times.length - 1;
        int middle;
        while (low <= high) {
            middle = (low + high) / 2;
            if (times[middle] == null && times[middle - 1] != null) {
                return middle;
            }
            if (times[middle] == null) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 有一个不知道具体长度的日志文件，里面记录了每次登录的时间戳，已知日志是按顺序从头到尾记录的，
     * 没有记录日志的地方为空，要求档期日志的长度
     */
}
