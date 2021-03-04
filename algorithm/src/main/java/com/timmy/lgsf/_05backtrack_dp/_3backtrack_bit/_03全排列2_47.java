package com.timmy.lgsf._05backtrack_dp._3backtrack_bit;

import com.timmy.common.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO 常考题
public class _03全排列2_47 {

    public static void main(String[] args) {
        _03全排列2_47 demo = new _03全排列2_47();
//        int[] nums = {1, 1, 2};
        int[] nums = {1, 2, 1};
        List<List<Integer>> res = demo.permuteUnique(nums);
        System.out.println("res");
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * -输入一个数组，数组中的元素可能重复，对数据中的元素重新排列，然后返回排列好的集合
     * -有多种排列方式，因为存在重复元素，所以存在数值相同的排列，需要剔除
     * 2。解题思路：回溯算法
     * -使用一个数组保存每次排序好的排列，遍历原始数组，
     * -每个元素都可以保存在排序数组的任意位置，
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> itemList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        //排序
        Arrays.sort(nums);
        System.out.println("sort--");
        PrintUtils.print(nums);
        backtrack(res, itemList, visited, nums, 0);
        return res;
    }

    /**
     * <p>
     * 全排列重复问题处理：
     * 但题目解到这里并没有满足「全排列不重复」 的要求，在上述的递归函数中我们会生成大量重复的排列，
     * 因为对于第 \textit{idx}idx 的位置，如果存在重复的数字 ii，我们每次会将重复的数字都重新填上
     * 去并继续尝试导致最后答案的重复，因此我们需要处理这个情况。
     * <p>
     * 要解决重复问题，我们只要设定一个规则，保证在填第 \textit{idx}idx 个数的时候重复数字只会被填入一次即可。
     * 而在本题解中，我们选择对原数组排序，保证相同的数字都相邻，然后每次填入的数一定是这个数所在重复数集合中「
     * 从左往右第一个未被填过的数字」，即如下的判断条件：
     * <p>
     * C++
     * <p>
     * if (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]) {
     * continue;
     * }
     * 这个判断条件保证了对于重复数的集合，一定是从左往右逐个填入的。
     * <p>
     * 假设我们有 33 个重复数排完序后相邻，那么我们一定保证每次都是拿从左往右第一个未被填过的数字，
     * 即整个数组的状态其实是保证了 [未填入，未填入，未填入] 到 [填入，未填入，未填入]，
     * 再到 [填入，填入，未填入]，最后到 [填入，填入，填入] 的过程的，因此可以达到去重的目标。
     * <p>
     * 链接：https://leetcode-cn.com/problems/permutations-ii/solution/quan-pai-lie-ii-by-leetcode-solution/
     */
    /**
     * @param res      最后用户保存的结果
     * @param itemList 每次遍历保存的集合
     * @param visited  表示当前元素是否已经遍历过
     * @param nums     排序后的数组
     * @param index    当前遍历到的元素
     */
    private void backtrack(List<List<Integer>> res, List<Integer> itemList, boolean[] visited,
                           int[] nums, int index) {
        if (index == nums.length) {
            System.out.println("-----item---");
            PrintUtils.print(itemList);
            res.add(new ArrayList<Integer>(itemList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {    //已经遍历过
                continue;
            }
            //去重，当存在相同的元素时，且当前i元素是第一次添加
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            itemList.add(nums[i]);
            visited[i] = true;
            backtrack(res, itemList, visited, nums, index + 1);
            visited[i] = false;
            itemList.remove(index);
        }
    }

//    private void backtrack(List<List<Integer>> res, int[] target, int[] nums, int index) {
//        if (index == nums.length) {
//            //判断是否有相同的排列
//            //数组转成集合
////            List<Object> list = Arrays.stream(target).boxed().collect(Collectors.toList());
//            List<Integer> list = new ArrayList<>();
//            for (int i : target) {
//                list.add(i);
//            }
//            PrintUtils.print(list);
//            res.add(list);
//            return;
//        }
//        int num = nums[index];
//        for (int i = 0; i < target.length; i++) {
//            if (target[i] == 0) {
//                target[i] += num;
//                backtrack(res, target, nums, index + 1);
//                target[i] -= num;
//            }
//        }
//    }


    /**
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     *
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     *
     * 示例 2：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *  
     * 提示：
     * 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     *
     * 链接：https://leetcode-cn.com/problems/permutations-ii
     */
}
