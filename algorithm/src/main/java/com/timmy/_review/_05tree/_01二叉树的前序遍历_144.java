package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _01二叉树的前序遍历_144 {

    public static void main(String[] args) {
        TreeNode treeNode2 = new TreeNode(2, new TreeNode(1), null);
        TreeNode treeNode3 = new TreeNode(3, treeNode2, new TreeNode(4));
        TreeNode root = new TreeNode(5, treeNode3, new TreeNode(6));

        System.out.print("中序遍历：");
        PrintUtils.printMid(root);
        System.out.println();
        System.out.print("前序遍历：");
        PrintUtils.printPre(root);
        System.out.println();
        _01二叉树的前序遍历_144 demo = new _01二叉树的前序遍历_144();
        List<Integer> res = demo.preorderTraversal(root);
        PrintUtils.print(res);
    }

    /**
     * 2。使用栈完成前序遍历
     * -先不断遍历左子节点，将所有的左子节点保存到栈中
     * -遍历到最左侧叶子节点时，子节点为空，则需要将栈顶元素取出来，并将遍历节点转移到栈顶元素的右子节点
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = root;
        while (head != null || !stack.isEmpty()) {
            //先遍历所有的左侧子节点，保存到栈中
            while (head != null) {
                res.add(head.val);
                stack.push(head);
                head = head.left;
            }
            //遍历到最左侧的叶子节点，取出栈顶元素
            TreeNode pop = stack.pop();
            head = pop.right;
        }
        return res;
    }

    /**
     * 1。前序遍历：
     * 根 - 左子树 - 右子树
     * 2。递归写法
     * 递归三要素
     * -入参与返回值 -- 当前遍历的节点不断移动
     * -终止条件 -- 直到当前遍历的节点为空
     * -单层递归逻辑 -- 先遍历当前节点，然后遍历左子节点，右子节点
     * 3.复杂度分析
     * -时间：需要遍历每个节点- O(n)
     * -空间：递归调用的层级，与树的高度相关 - O(H)
     */
    public List<Integer> preorderTraversal_v1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preTraversal(root, res);
        return res;
    }

    private void preTraversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            preTraversal(root.left, res);
            preTraversal(root.right, res);
        }
    }

}
