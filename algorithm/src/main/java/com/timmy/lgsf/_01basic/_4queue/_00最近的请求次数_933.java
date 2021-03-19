package com.timmy.lgsf._01basic._4queue;

public class _00最近的请求次数_933 {

    public static void main(String[] args) {

        RecentCounter recentCounter = new RecentCounter();
        int[] times = new int[]{1, 100, 3001, 3002};
        for (int i = 0; i < times.length; i++) {
            int ping = recentCounter.ping(times[i]);
            System.out.println("ping:"+ping);
        }
    }


}
