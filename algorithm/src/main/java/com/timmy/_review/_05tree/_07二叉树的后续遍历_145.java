package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _07二叉树的后续遍历_145 {

    public static void main(String[] args) {
        _07二叉树的后续遍历_145 demo = new _07二叉树的后续遍历_145();

        TreeNode treeNode2 = new TreeNode(2, new TreeNode(1), null);
        TreeNode treeNode3 = new TreeNode(3, treeNode2, new TreeNode(4));
        TreeNode root = new TreeNode(5, treeNode3, new TreeNode(6));

        System.out.print("前序遍历：");
        PrintUtils.printPre(root);
        System.out.println();
        System.out.print("中序遍历：");
        PrintUtils.printMid(root);
        System.out.println();
        System.out.print("后续遍历：");
        PrintUtils.printNex(root);
        System.out.println();

        List<Integer> res = demo.postorderTraversal(root);
        PrintUtils.print(res);
    }

    /**
     * 后续遍历：左 右 根
     * 迭代法：
     * -先获取最左边的叶子节点放到栈中，当遍历到最左侧节点为空时，从栈顶取出节点，该节点可能存在右子树，
     * --后序遍历需要先遍历左右子树，所以先栈顶元素先不出栈，只获取peek，然后遍历节点转移到右子节点，
     * --而右子节点也可能存在左右子节点情况，所以需要从头开始遍历其左右子节点，
     * -如果右子节点为null，则这个时候才真正将栈顶元素出栈，
     * <p>
     * 3.总结：
     * 只有两种情况需要从栈中取出栈顶元素，
     * -一种是当前节点的右子节点为null
     * -另一个是当前节点的右子节点，在上次已经遍历过了
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode pre = null;

        while (node != null || !stack.isEmpty()) {
            //1。不断获取左子节点，保存到栈中
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            //2.当遍历到最左侧叶子节点的左侧空节点时，取出栈顶元素（上层节点）
            node = stack.peek();

            /**
             * 3.判断右子节点是否为null,
             * -为null则栈顶元素出栈,遍历节点设置为null，这样才能获取栈顶元素
             * -或者pre==node.right，说明当前遍历的节点的右子节点是上次已经遍历过的，则遍历当前节点
             */
            if (node.right == null || pre == node.right) {
                TreeNode pop = stack.pop();
                res.add(pop.val);
                //右侧节点为null，取出其父节点，再遍历父节点-父节点while循环又push到栈中，导致的结果是一直在存取父节点
                //增加一个变量，标记上次已经遍历过的节点，
                pre = node;
                // node设置为null很关键，这样才能从栈中获取节点
                node = null;
            } else {
                //4.右子节点不为null，遍历右子节点
                node = node.right;
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal_err(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            //1。不断获取左子节点，保存到栈中
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            //2.当遍历到最左侧叶子节点的左侧空节点时，取出栈顶元素（上层节点）
            TreeNode peek = stack.peek();
            node = peek.right;

            //3.判断右子节点是否为null,为null则栈顶元素出栈
            if (node == null) {
                TreeNode pop = stack.pop();
                res.add(pop.val);
                //右侧节点为null，取出其父节点，再遍历父节点-父节点while循环又push到栈中，导致的结果是一直在存取父节点
                //增加一个变量，标记上次已经遍历过的节点，
                node = pop;
            }
//            else {     // 右子节点不为null，则添加到栈中
//                stack.push(node);
//            }
        }

        return res;
    }

    /**
     * 后续遍历：左 右 根
     * 递归遍历法
     */
    public List<Integer> postorderTraversal_v1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversalR(root, res);
        return res;
    }

    private void postorderTraversalR(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorderTraversalR(root.left, res);
        postorderTraversalR(root.right, res);
        res.add(root.val);
    }

    /**
     * 给定一个二叉树，返回它的 后序 遍历。
     *
     * 示例:
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [3,2,1]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
     * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
     */
}
