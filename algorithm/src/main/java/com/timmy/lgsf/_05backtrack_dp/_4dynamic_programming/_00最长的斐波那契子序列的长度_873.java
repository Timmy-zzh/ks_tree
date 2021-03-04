package com.timmy.lgsf._05backtrack_dp._4dynamic_programming;

import java.util.HashMap;
import java.util.HashSet;

public class _00最长的斐波那契子序列的长度_873 {

    public static void main(String[] args) {
        _00最长的斐波那契子序列的长度_873 demo = new _00最长的斐波那契子序列的长度_873();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int res = demo.lenLongestFibSubseq(arr);
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2.解题思路：动态规划
     * 2。1。将斐波那契子序列中的两个连续项 A[i],A[j] 视为单节点(i,j),整个子序列是这些连续节点之间的路径
     * {1, 2, 3, 4, 5, 6, 7, 8}
     * -对于斐波那契的子序列 (A[0]=1, A[1]=2, A[2]=3, A[4]=5 ,A[7]=8 );
     * --节点之间的路径为(0,1)-(1,2)-(2,4)-(4,7)
     * -只有当 A[i] + A[j] == A[k] 时，两节点(i,j) 和 (j,k)才是连通的
     * 2。2。动态规划解题步骤
     * -1。想清楚原问题与子问题
     * --子问题为求解，以节点(i,j)结尾的斐波那契子序列的长度
     * -2。设计状态
     * --dp(i,j)的值为，节点(i,j)结尾的斐波那契子序列的长度
     * -3。设计状态转移方程
     * --if(A[k] + A[i] == A[j]) --> dp(i,j) = dp(k,i) +1
     * --目标值 =max( dp(i,j) )
     * -4.确定边界状态值
     * --dp(i,j) =2
     * <p>
     * 2.3.代码实现：
     * -使用HashMap 保存原始数组A中元素值与下表对应的关系
     * --这个HashMap用来判断 A[i] = A[k] - A[j] (i < j < k) ,通过元素k,j的值求解i的值是否存在
     * --如果存在，则通过dp(i,j) 的节点子序列的长度，求解出dp(j,k）节点的子序列的长度
     * -再使用HashMap保存节点(i,j)的子序列的长度，key值表示坐标[i,j],value值为当前节点对应的子序列长度
     *
     * @param arr
     * @return
     */
    public int lenLongestFibSubseq(int[] arr) {
        int N = arr.length;
        HashMap<Integer, Integer> index = new HashMap<>();
        //原始数组元素与下表的对应关系
        for (int i = 0; i < N; i++) {
            index.put(arr[i], i);
        }
        //保存节点（i,j）对应的子序列长度
        HashMap<Integer, Integer> nodeLenMap = new HashMap<>();
        int ans = 0;

        for (int k = 0; k < N; k++) {
            for (int j = 0; j < k; j++) {
                // 元素i是否存在
                int i = index.getOrDefault(arr[k] - arr[j], -1);
                if (i >= 0 && i < j) {
                    System.out.println("i:" + i + " ,j:" + j + " ,k:" + k);
                    //存在节点(i,j) -> (j,k) 节点，则可以根据节点（i，j）的子序列长度计算（j,k)的长度
                    int nodeLen = nodeLenMap.getOrDefault(i * N + j, 2);    //节点(i，j)的长度
                    System.out.println("nodeLen:" + nodeLen);
                    nodeLenMap.put(j * N + k, nodeLen + 1);
                    ans = Math.max(ans, nodeLen + 1);
                }
            }
        }
        return ans > 2 ? ans : 0;
    }

    /**
     * 1.理解题意
     * -输入一个递增的正整数数组，从数组中查找子序列，要求子序列满足斐波那契数组
     * -斐波那契数列满足条件： A[n] = A[n-1] + A[n-2]
     * -求原始数组中最长斐波那契数列的最长子序列的长度
     * 2.解题思路：暴力解法
     * -因为斐波那契数列中的元素都是从原始数组中获取的，切满足条件A[n] = A[n-1] + A[n-2]
     * -所以可以从起始元素 (i,j) 开始查找,判断生成的k元素是否存在，存在的话再判断(j,k)后面的数据是否存在，循环实现
     * -起始列表组合为两个元素，从原始数组中任意匹配，可通过两个for循环确定，然后再判断[i,j]的和在原始数组中是否存在
     * --存在的话继续往下循环判断，并且通过变量记录长度
     * 3。边界和细节问题
     * -起始元素的确定，两层for循环
     * -结果值的后续叠加结果，在原始数组中是否存在
     * --在开始时，通过HashSet保存数组中的所有元素值
     * <p>
     * TODO：优化 [1,2,3,4,5,6,7,8]
     * 存在重复计算的代码
     * -如：开始元素为[1,2] -->最后得到的斐波那契数列为[1,2,3,5,8]
     * --当开始元素为 [2,3] 时，最后的斐波那契数列为 [2,3,5,8]
     * --开始元素为   [3,5]时，也存在重复计算
     * ==这样第二次的计算是重复的，如何优化 --》通过保存上一次计算好的状态，下一次计算时拿过来用就可以处理
     *
     * @param arr
     * @return
     */
    public int lenLongestFibSubseq_v1(int[] arr) {
        int N = arr.length;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i : arr) {
            hashSet.add(i);
        }
        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int x = arr[j];
                int y = arr[i] + arr[j];
                int length = 2;
                while (hashSet.contains(y)) {   //不断替换，判断斐波那契数列的长度
                    length++;
                    ans = Math.max(ans, length);

                    int temp = y;
                    y = x + y;
                    x = temp;
                }
            }
        }
        return ans > 2 ? ans : 0;
    }

    /**
     * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
     * n >= 3
     * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
     * 给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
     * （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），
     * 而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
     *
     * 示例 1：
     * 输入: [1,2,3,4,5,6,7,8]
     * 输出: 5
     * 解释:
     * 最长的斐波那契式子序列为：[1,2,3,5,8] 。
     *
     * 示例 2：
     * 输入: [1,3,7,11,12,14,18]
     * 输出: 3
     * 解释:
     * 最长的斐波那契式子序列有：
     * [1,11,12]，[3,11,14] 以及 [7,11,18] 。
     *  
     * 提示：
     * 3 <= A.length <= 1000
     * 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
     * （对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%）
     *
     * 链接：https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence
     */
}
