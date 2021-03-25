package com.timmy.lgsf._03tree.review;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.Stack;

public class _02二叉树遍历_迭代法 {

    public static void main(String[] args) {
        TreeNode treeNode2 = new TreeNode(2, new TreeNode(1), null);
        TreeNode treeNode3 = new TreeNode(3, treeNode2, new TreeNode(4));
        TreeNode root = new TreeNode(5, treeNode3, new TreeNode(6));

        PrintUtils.printNex(root);
        System.out.println();
        _02二叉树遍历_迭代法 demo = new _02二叉树遍历_迭代法();
        demo.nextTraveral(root);
    }

    /**
     * 后序遍历：左右根 -- 反序：根右左（使用stack保存） -》再反就是最后结果了
     * 使用另外一个stack保存遍历到的节点
     * -根出栈，左子节点入栈，右子节点入栈
     * --》然后栈顶元素出栈
     */
    public void nextTraveral(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        Stack<TreeNode> temp = new Stack<>();

        while (!stack.isEmpty()) {
            //根节点出栈
            TreeNode node = stack.pop();
            temp.add(node);

            //左子树，右子树入栈
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        while (!temp.isEmpty()) {
            System.out.println(temp.pop().val);
        }
    }

    /**
     * 前序遍历： 根-左-右
     * -入栈顺序应该为： 根出栈-》右子节点入栈-》左子节点入栈
     * --然后 栈顶元素出栈：（这样出栈的顺序才是根左右）
     */
    public void preTraveral(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            //1。栈顶元素出栈
            TreeNode node = stack.pop();
            System.out.println(node.val);

            //2。右子节点，左子节点入栈
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
    }

    /**
     * 中序遍历：迭代法
     * -使用栈Stack 模拟递归调用
     * -中序遍历的顺序是 左-根-右，
     * --从根节点开始遍历，不断获取左子节点，并保存到stack中，当遍历到最左边的叶子节点时，栈顶元素出栈，
     * --并将遍历节点转移到右子节点，再继续将右子节点的左子节点保存到stack中，直到左边的叶子节点，
     * --如果右子节点为null，则继续出栈
     *
     * @param root
     */
    public void midTraveral(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        //4.右子节点有可能为null，则继续从stack中取出栈顶节点
        while (node != null || !stack.isEmpty()) {
            //1.从当前节点开始，不断遍历左子节点，并存放到栈中
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            //2.已经遍历到叶子节点了，则从stack中取出最后保存到栈中的节点
            TreeNode pop = stack.pop();
            System.out.println(pop.val);

            //3.查看右子节点
            node = pop.right;
        }
    }
}
