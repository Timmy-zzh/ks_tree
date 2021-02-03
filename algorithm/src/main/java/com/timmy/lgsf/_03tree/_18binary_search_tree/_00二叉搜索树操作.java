package com.timmy.lgsf._03tree._18binary_search_tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _00二叉搜索树操作 {

    //9, 3, 15, 20, 7,
    public static void main(String[] args) {
        TreeNode node20 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), node20);

        _00二叉搜索树操作 demo = new _00二叉搜索树操作();
        TreeNode head = null;
//        int[] nums = {5, 2, 8, 5, 9, 7, 3, 18, 10};
        int[] nums = {5, 2, 8, 5, 9};
        for (int val : nums) {
            head = demo.insert(head, val);
        }
        PrintUtils.printMid(head);
        System.out.println();

//        boolean searchBST = demo.searchBST(head, 15);
//        System.out.println("searchBST:" + searchBST);

//        PrintUtils.printMid(root);
//        System.out.println();
//        int[] validBST = demo.isValidBST(root);

        int[] validBST = demo.isValidBST(head);
        PrintUtils.print(validBST);
    }

    /**
     * 判断一棵二叉树是否是二叉搜索树？
     * 1。理解题意
     * -二叉搜索树有个特点是 中序遍历是一个升序序列
     * --我们只要使用一个值记录中序遍历中上一个节点的值，并与当前节点值进行判断，是二叉搜索树的话 前一个节点值 小于 当前节点值
     * --从整理来看，再用一个值记录左边区域是否是一颗升序的二叉搜索树， 例如，如果左子树已经不是一棵二叉搜索树了，加上当前节点也不会是二叉搜索树
     * --再用一个值保存左边区域节点的累加和
     * 2。解题思路
     * -使用一个数组保存上述的值，
     * --左边区域 是否是一个二叉搜索树 -- 1/0
     * --上一个节点的值 -- 用于与当前节点判断是否是升序
     * --左边区域节点的累加和
     * 3.
     */
    private int[] isValidBST(TreeNode root) {
        int[] res = new int[3];
        res[0] = 0;
        res[1] = Integer.MIN_VALUE;
        res[2] = 0;
        helper(root, res);
        return res;
    }

    // 递归 中序遍历
    private void helper(TreeNode root, int[] res) {
        if (root == null) {
            res[0] = 1;
            return;
        }
        helper(root.left, res);
        //判断前面节点是否是二叉搜索树？
        //判断上一个节点与当前节点值是否升序？
        if (res[0] == 0 || res[1] > root.val) {
            //前面节点不是二叉所搜树，或前一个节点值大于当前节点值
            res[0] = 0;
            res[2] = 0;
            return;
        }
        res[0] = 1;
        res[1] = root.val;
        res[2] = res[2] + root.val;
        helper(root.right, res);
    }

    /**
     * 迭代法实现：
     */
    private boolean searchBST(TreeNode root, int target) {
        while (root != null) {
            if (root.val == target) {
                return true;
            }
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return false;
    }

    /**
     * 1.理解题意
     * -给定一个二叉搜索树和一个目标值，查找目标值在二叉搜索树中是否存在
     * -存在返回true，否则返回false
     * 2。解题思路
     * -递归调用判断
     * -如果节点为空，返回false
     * -如果节点值与目标值相等，返回true
     * --如果目标值小于跟节点值，判断左子树中是否存在目标值，否则判断右子树
     */
    private boolean searchBST_v1(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target) {
            return true;
        } else if (target < root.val) { //左子树中查找
            return searchBST_v1(root.left, target);
        } else {
            return searchBST_v1(root.right, target);
        }
    }

    /**
     * 1。理解题意
     * -有一个无序的数组 [5,2,8,5,9]
     * -将该数组中所有数据组合成一个二叉搜索树，并返回跟节点
     * 2。解题思路
     * -遍历数组，每次插入一个元素到二叉搜索树中
     * --如果根节点为null，则以当前元素值创建跟节点
     * --如果元素值小于当前节点，插入左子树，否则插入右子树
     */
    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        } else if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

}
