package com.timmy._review._05tree._00tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class _03二叉树的后序遍历_145 {

    public static void main(String[] args) {
        TreeNode treeNode2 = new TreeNode(2, new TreeNode(1), null);
        TreeNode treeNode3 = new TreeNode(3, treeNode2, new TreeNode(4));
        TreeNode root = new TreeNode(5, treeNode3, new TreeNode(6));

        System.out.print("前序遍历：");
        PrintUtils.printPre(root);
        System.out.println();
        System.out.print("中序遍历：");
        PrintUtils.printMid(root);
        System.out.println();
        System.out.print("后序遍历：");
        PrintUtils.printNex(root);
        System.out.println();
        _03二叉树的后序遍历_145 demo = new _03二叉树的后序遍历_145();
        List<Integer> res = demo.postorderTraversal(root);
        PrintUtils.print(res);
    }

    /**
     * 因为后序遍历顺序是：左-右-根
     * 而前序遍历的顺序是：根-左-右
     * 所以可以反向思维：先按照前序遍历的思路，获取 跟右左 的遍历顺序，然后整个集合反转即是所求结果
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        stack.add(node);
        while (!stack.isEmpty()) {
            //取出根节点,并添加到集合中
            node = stack.pop();
            res.add(node.val);
            //添加左子节点，和右子节点，在while循环中，出栈的顺序就是 跟右左
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 后序遍历：左右根
     * 递归实现
     */
    public List<Integer> postorderTraversal_v1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

}
