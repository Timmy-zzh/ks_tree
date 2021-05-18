package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _06删除二叉搜索树中的节点_450 {

    public static void main(String[] args) {
        _06删除二叉搜索树中的节点_450 demo = new _06删除二叉搜索树中的节点_450();
        TreeNode node6 = new TreeNode(6, null, new TreeNode(7));
        TreeNode node3 = new TreeNode(3, new TreeNode(2), new TreeNode(4));
        TreeNode root = new TreeNode(5, node3, node6);
        System.out.println("mid--");
        PrintUtils.printMid(root);
        System.out.println();
        TreeNode res = demo.deleteNode(root, 3);
        PrintUtils.printMid(res);
    }

    /**
     * 1.理解题意
     * -输入一棵二叉搜索树，和一个值key，遍历这颗二叉树中的所有节点，找到节点值为key的节点，然后删除该节点，并返回删除后的树的根节点
     * 2。解题思路
     * -遍历二叉搜索树，因为是二叉搜索树具有特点：左子树 < 根节点 < 右子树，可以使用这个特性进行节点的遍历
     * -节点删除前是二叉搜索树，需要保持节点删除后还是二叉搜索树
     * 2。1。节点删除的情况：
     * -删除的节点是叶子节点，则该节点直接删除
     * -删除的节点只有左子树，则从左子树中找到最大的节点值，然后进行交换，删除的节点转移到左子树的叶子节点，继续遍历删除
     * -删除节点只有右子树，则从右子树中找到最小的节点值，然后交换后删除
     * -删除节点存在左右子树，从左子树中找最小值，交换删除
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {   //删除节点在左子树
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {    //删除节点在右子树
            root.right = deleteNode(root.right, key);
        } else {
            //删除节点，等于当前节点

            //删除节点是叶子节点
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.right == null) {
                //删除节点只有左子树,找到左子树最大值（最右边的）
                TreeNode maxNode = root.left;
                while (maxNode.right != null) {
                    maxNode = maxNode.right;
                }
                //swap
                swapNodeValue(root, maxNode);

                root.left = deleteNode(root.left, key);
            } else if (root.left == null) {    //只有右子树
                TreeNode minNode = root.right;
                while (minNode.left != null) {
                    minNode = minNode.left;
                }
                swapNodeValue(root, minNode);

                root.right = deleteNode(root.right, key);
            } else {
                //左右子树都存在
                //删除节点只有左子树,找到左子树最大值（最右边的）
                TreeNode maxNode = root.left;
                while (maxNode.right != null) {
                    maxNode = maxNode.right;
                }
                //swap
                swapNodeValue(root, maxNode);

                root.left = deleteNode(root.left, key);
            }
        }
        return root;
    }

    private void swapNodeValue(TreeNode root, TreeNode maxNode) {
        int temp = root.val;
        root.val = maxNode.val;
        maxNode.val = temp;
    }

    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
     * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     *
     * 示例:
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     *
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     *
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     *
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
     *
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
     */
}
