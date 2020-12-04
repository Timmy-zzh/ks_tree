package com.timmy.leetcode._202008;

public class _0819_practice {

    public static void main(String[] args) {
//        System.out.println("-----------------------------------------");
//        _0819_practice practice = new _0819_practice();
////        int result = practice.divide(10, 3);
////        int result = practice.divide(7, -3);
////        int result = practice.divide(-2147483648, -1);
////        int result = practice.divide(-2147483648, 2);
//        int result = practice.divide(2147483647, 3);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
//        _0819_practice practice = new _0819_practice();
////        int[] nums = {4, 5, 6, 7, 0, 1, 2};
////        int target = 0;
////        int[] nums = {4, 5, 6, 7, 0, 1, 2};
////        int target = 3;
//        int[] nums = {1, 3};
//        int target = 3;
////        int[] nums = {5, 1, 3};
////        int target = 5;
//        int result = practice.search(nums, target);
//        System.out.println("result:" + result);
//        System.out.println("-----------------------------------------");

//        System.out.println("-----------------------------------------");
//        _0819_practice practice = new _0819_practice();
////        int[] nums = {5, 7, 7, 8, 8, 10};
////        int target = 8;
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 6;
//        int[] result = practice.searchRange(nums, target);
//        PrintUtils.print(result);
//        System.out.println("-----------------------------------------");

        System.out.println("-----------------------------------------");
        _0819_practice practice = new _0819_practice();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean result = practice.isValidSudoku(board);
        System.out.println("result:" + result);
        System.out.println("-----------------------------------------");


    }

    /**
     * 36. 有效的数独
     * <p>
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * <p>
     * 输入:
     * {
     * ["5","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: true
     * <p>
     * 思路：遍历二维矩阵所有元素（数字），判断
     * 1。行，列，只能出现一次
     * 2。3x3宫阁内只能出现一次
     */
    public boolean isValidSudoku(char[][] board) {
        boolean result = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                //判断列
                for (int k = 0; k < 9; k++) {
                    if (k == j || board[i][k] == '.') {
                        continue;
                    }
                    if (board[i][j] == board[i][k]) {
                        result = false;
                        break;
                    }
                }
                //判断行
                for (int k = 0; k < 9; k++) {
                    if (k == i || board[k][j] == '.') {
                        continue;
                    }
                    if (board[i][j] == board[k][j]) {
                        result = false;
                        break;
                    }
                }
                //判断宫阁
                int is = i / 3 * 3;
                int js = j / 3 * 3;
                for (int xi = is; xi < is + 3; xi++) {
                    for (int xj = js; xj < js + 3; xj++) {
                        if ((xi == i && xj == j) || board[xi][xj] == '.') {
                            continue;
                        }
                        if (board[xi][xj] == board[i][j]) {
                            result = false;
                            break;
                        }
                    }
                    if (!result) {
                        break;
                    }
                }
                if (!result) {
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * <p>
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * <p>
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * <p>
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     * <p>
     * 思路：二分查找法
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                result[0] = result[1] = mid;
                left = right = mid;
                while (left > 0 && nums[--left] == target) {
                    result[0] = left;
                }
                while (right < nums.length - 1 && nums[++right] == target) {
                    result[1] = right;
                }
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    /**
     * 33. 搜索旋转排序数组
     * <p>
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 你可以假设数组中不存在重复的元素。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * <p>
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * <p>
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     * <p>
     * 思路：分治法
     * 分三种情况
     */
    public int search(int[] nums, int target) {
        return nav(nums, 0, nums.length - 1, target);
    }

    private int nav(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            if (nums[left] == target) {
                return left;
            }
            return -1;
        }
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[left] < nums[mid]) {    //左边是升序
            if (target < nums[left]) {
                return nav(nums, mid + 1, right, target);
            } else if (nums[mid] < target) {
                return nav(nums, mid + 1, right, target);
            } else {
                return nav(nums, left, mid - 1, target);
            }
        } else if (nums[mid] < nums[right]) {
            if (target > nums[right]) {
                return nav(nums, left, mid - 1, target);
            } else if (nums[mid] < target) {
                return nav(nums, mid + 1, right, target);
            } else {
                return nav(nums, left, mid - 1, target);
            }
        } else {
            if (right - left == 1) {
                if (target == nums[left]) {
                    return left;
                } else if (target == nums[right]) {
                    return right;
                }
                return -1;
            } else {
                if (nums[mid] < target) {
                    return nav(nums, mid + 1, right, target);
                } else {
                    return nav(nums, left, mid - 1, target);
                }
            }
        }
    }

    /**
     * 29. 两数相除
     * <p>
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * <p>
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
     * <p>
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * 解释: 7/-3 = truncate(-2.33333..) = -2
     * <p>
     * 思路：
     * 用减法，每次减去除数，
     * 注意正负号问题
     */
    public int divide(int dividend, int divisor) {
        int result = 0;
        boolean isZheng;    //是否是整数
        if (dividend == 0) {
            return 0;
        }
        //对1和-1做特殊处理
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            }
            return Integer.MAX_VALUE;
        }

        if (dividend > 0) {
            isZheng = divisor > 0;
        } else {
            isZheng = divisor < 0;
        }

        long tempOne = dividend;
        long tempTwo = divisor;
        tempOne = tempOne > 0 ? tempOne : -tempOne;
        tempTwo = tempTwo > 0 ? tempTwo : -tempTwo;
        while (tempOne > 0) {
            tempOne -= tempTwo;
            if (tempOne >= 0) {
                result = isZheng ? result + 1 : result - 1;
            }
        }
        return result;
    }
}
