package com.timmy._review._00tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.Arrays;

public class _05重建二叉树_剑指Offer07 {

    public static void main(String[] args) {
        _05重建二叉树_剑指Offer07 demo = new _05重建二叉树_剑指Offer07();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode treeNode = demo.buildTree(preorder, inorder);
        System.out.println("前序：");
        PrintUtils.printPre(treeNode);
        System.out.println();
        System.out.println("中序");
        PrintUtils.printMid(treeNode);
    }

    /**
     * 1.理解题意
     * -输入二叉树的前序遍历和中序遍历的结果，然后进行二叉树的重建
     * 2。解题思路
     * -前序遍历顺序是：根-左-右 ； 中序遍历顺序是：左-根-右
     * --因为前序遍历的第一个元素一定是子树的根节点，所有取根节点在中序遍历中查找其位置，
     * --根据根节点对中序遍历进行分割，分成中序遍历的左子树和右子树，
     * -又因为中序遍历是以左根右顺序，所以根节点的前一个节点为左子树的最后最后一个节点
     * --根据这个节点对前序遍历进行分割，分割出前序遍历的左子树和右子树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return travel(preorder, inorder);
    }

    /**
     * 1.前序遍历的第一个元素一定是子树的根节点
     * 2。根据根节点对中序遍历中线分割，分成中序遍历的左子树和右子树
     * 3。再根据左右子树的大小，切分前序遍历的左右子树
     *
     * @param preorder 前序遍历：根 左 右
     * @param inorder  中序遍历：左 根 右
     * @return
     */
    private TreeNode travel(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        //如果前序遍历中只有一个元素，则后序不需要在切割了，直接返回当前节点
        if (preorder.length == 1) {
            return root;
        }

        //切割，根据根节点元素值，切割中序遍历
        int inIndex = getIndex(preorder[0], inorder);
        //切割中序左子树，和中序右子树
        int[] inLeftArr = Arrays.copyOfRange(inorder, 0, inIndex);
        int[] inRightArr = Arrays.copyOfRange(inorder, inIndex + 1, inorder.length);

        // 或者按照中序遍历后左右子树的大小进行切割
        int[] preLeftArr = Arrays.copyOfRange(preorder, 1, 1 + inLeftArr.length);
        int[] preRightArr = Arrays.copyOfRange(preorder, 1 + inLeftArr.length, preorder.length);

        root.left = travel(preLeftArr, inLeftArr);
        root.right = travel(preRightArr, inRightArr);

        return root;
    }

    /**
     * 求value在数组中的下标位置
     */
    private int getIndex(int value, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            if (value == inorder[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * 例如，给出
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 限制：
     * 0 <= 节点个数 <= 5000
     * 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     *
     * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
     */
}
