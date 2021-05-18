package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _05中序遍历_94 {

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
        _05中序遍历_94 demo = new _05中序遍历_94();
        List<Integer> res = demo.inorderTraversal(root);
        PrintUtils.print(res);
    }

    /**
     * 中序遍历：迭代法
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            //先遍历到最左侧叶子节点
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //遍历到最左侧叶子节点的空节点，取出栈顶元素
            TreeNode pop = stack.pop();
            res.add(pop.val);       //遍历栈顶元素，保存到集合汇总
            //然后遍历栈顶元素的右子节点
            node = pop.right;
        }
        return res;
    }

    /**
     * 中序遍历：左 - 根 - 右
     * -递归算法
     */
    public List<Integer> inorderTraversal_v1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversalR(res, root);
        return res;
    }

    private void inorderTraversalR(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversalR(res, root.left);
        res.add(root.val);
        inorderTraversalR(res, root.right);
    }

    /**
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     *
     * 示例 1：
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     *
     * 示例 2：
     * 输入：root = []
     * 输出：[]
     *
     * 示例 3：
     * 输入：root = [1]
     * 输出：[1]
     *
     * 示例 4：
     * 输入：root = [1,2]
     * 输出：[2,1]
     *
     * 示例 5：
     * 输入：root = [1,null,2]
     * 输出：[1,2]
     *
     * 提示：
     * 树中节点数目在范围 [0, 100] 内
     * -100 <= Node.val <= 100
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
     */
}
