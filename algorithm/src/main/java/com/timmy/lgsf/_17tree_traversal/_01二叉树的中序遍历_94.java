package com.timmy.lgsf._17tree_traversal;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _01二叉树的中序遍历_94 {

    //9, 3, 15, 20, 7,
    public static void main(String[] args) {
        TreeNode node20 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), node20);

        _01二叉树的中序遍历_94 demo = new _01二叉树的中序遍历_94();
        List<Integer> list = demo.inorderTraversal(root);
        PrintUtils.print(list);
    }

    /**
     * 莫里斯遍历
     * 1.创建辅助线
     * -左子树中序遍历的最后节点，（左子树的最右节点），将最后节点的右子节点指向跟节点
     * -跟节点左移，重复上诉操作，直到跟节点左移到最下方节点
     * -当跟节点的左节点不为空，则跟节点上移（重要通过辅助线），并获取到辅助节点，擦除辅助节点，还原原始左子树
     * -重复上述步骤
     */
    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode exPoint;  //辅助节点
        while (root != null) {
            if (root.left != null) {
                //辅助节点等于左子节点
                exPoint = root.left;
                //找到左子树的最后节点，并链接辅助线，从最后节点链接到跟节点
                //  exPoint.right != root 非常重要，跟节点上移操作时，终止判断，
                while (exPoint.right != null && exPoint.right != root) {
                    exPoint = exPoint.right;
                }

                if (exPoint.right == null) {
                    exPoint.right = root;   //增加辅助线，并将跟节点左移
                    root = root.left;
                } else { //收集，跟节点上移，辅助线擦除
                    list.add(root.val);
                    root = root.right;
                    exPoint.right = null;
                }
            } else {
                //已经左移到最左下方的节点
                //收集，并上移跟节点
                list.add(root.val);
                root = root.right;
            }
        }

        return list;
    }

    /**
     * 迭代法：
     * -使用栈模拟递归调用
     * --1。不断获取左节点，存入栈中，直到左节点为空
     * --2。此时栈顶元素就是最左侧的左节点，出栈，并将该节点值保存到集合中，
     * --3。接着获取当前节点的右子节点，重复步骤1
     */
    private List<Integer> inorderTraversal_v2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            //不断获取节点的左节点
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //左子节点为空，当前节点出栈，
            TreeNode pop = stack.pop();
            list.add(pop.val);
            //重复右子节点,右子节点为空，不断冲栈顶元素取数据
            node = pop.right;
        }
        return list;
    }

    /**
     * 1。理解题意
     * -输入树的跟节点，根据跟节点，进行中序遍历
     * 2。解题思路
     * -递归遍历，中序遍历为：左 中 右
     * --从根节点开始，不断获取节点的左节点，直到左节点为null，调用栈返回，
     * --接着获取当前节点的值
     * --然后调用从当前节点的右节点开始递归调用，右节点也为null，整个调用栈返回
     * ---说明当前节点的树遍历结束，然后调用上层节点，-》上层节点的右子树
     * 3。边界与细节问题
     * -递归三要素：入参与返回值，终止条件，单层递归逻辑
     */
    private List<Integer> inorderTraversal_v1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    /**
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     *
     * 示例 1：
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     *
     * 示例 2：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
     */

}
