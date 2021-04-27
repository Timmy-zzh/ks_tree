package com.timmy._review._00tree;

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
     * 前序遍历：根-左-右
     * -使用栈保存树中遍历到的节点，
     * --然后添加右子节点，
     * --最后添加右子节点，因为最后添加的左子节点最先出栈
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }

    /**
     * 2.迭代法实现：使用stack模拟递归调用
     * -使用stack保存遍历到的节点
     * -以当前节点为根节点，先遍历获取到根节点的数据
     * --然后遍历根节点的左子节点，再以左子节点为根节点，继续遍历
     * ---直到左子节点为null，取出栈顶元素，栈顶元素就是整个树的最左边的叶子节点，
     * --遍历最左节点的右子节点，再以右子节点为根节点进行遍历
     */
    public List<Integer> preorderTraversal_v2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            //根左右，1.node不为null，则一直遍历左子节点
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            //2.当遍历到最左边的叶子节点，则栈顶节点出栈
            node = stack.pop();
            /**
             * 3.遍历右子节点
             * -如果右子节点为null，则从栈中取栈顶元素
             * -如果右子节点不为null，则遍历右子节点的左子节点
             */
            node = node.right;
        }
        return res;
    }

    /**
     * 1.前序遍历
     * - 根-左-右
     * 2。递归实现
     */
    public List<Integer> preorderTraversal_v1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

}
