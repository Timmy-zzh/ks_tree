package com.timmy.lgsf._03tree._18binary_search_tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

public class _03删除二叉搜索树中的节点_450 {

    /**
     * root = [5,3,6,2,4,null,7]
     * key = 3
     * <p>
     * 5
     * / \
     * 3   6
     * / \   \
     * 2   4   7
     */
    public static void main(String[] args) {
//        TreeNode node6 = new TreeNode(6,null,  new TreeNode(7));
//        TreeNode node3 = new TreeNode(3,  new TreeNode(2), new TreeNode(4));
//        TreeNode root = new TreeNode(5, node3, node6);

        TreeNode root = new TreeNode(2);

        PrintUtils.printMid(root);
        System.out.println();
        _03删除二叉搜索树中的节点_450 demo = new _03删除二叉搜索树中的节点_450();

        TreeNode treeNode = demo.deleteRoot(root, 2);
        PrintUtils.printMid(treeNode);
        System.out.println();
    }

    /**
     * 1.理解题意
     * -输入一棵二叉搜索树和一个值key，删除二叉搜索树中的key值对应的节点
     * 2。解题思路
     * -要删除节点，首先要找到该节点
     * --因为二叉搜索树是中序遍历升序的，可以使用该特性进行判断，
     * ---如果等于当前节点的值，则对该节点进行删除
     * ---如果大于节点的值，从右子树中查找，否则进入左子树查找
     * -找到该节点后，接下来就需要删除该节点，要删除节点需要知道前驱节点，并且进行断开操作，有几种情况需要考虑
     * --如果删除的节点是叶子节点，则直接删除
     * --如果删除的节点存在左节点，则从左子树中查找最大值节点，将该节点值赋值给删除节点，并且断开左子树中最值节点的链接
     * --如果删除的节点不存在左节点，存在右节点，则从右子树中找到最小值（左子树最左侧节点），将该节点值赋值给删除节点，并断开链接
     */
    public TreeNode deleteRoot(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        //1。找到与key值相等的节点
        //创建虚拟头节点
        TreeNode head = new TreeNode(-1);
        head.left = root;

        TreeNode preNode = head;//前驱节点
        TreeNode dNode = root;
        while (dNode != null) {
            if (dNode.val == key) {
                break;
            } else if (dNode.val < key) {
                preNode = dNode;
                dNode = dNode.right;
            } else {
                preNode = dNode;
                dNode = dNode.left;
            }
        }

        //2。没有找到key值节点
        if (dNode == null) {
            return head.left;
        }

        //3.存在key值节点
        //3.1.key值节点是叶子节点,直接删除
        if (dNode.left == null && dNode.right == null) {
            if (dNode == root) {
                preNode.left = null;
            } else {
                if (preNode.val < dNode.val) {   //前驱节点 --》 右子节点 --断开
                    preNode.right = null;
                } else {
                    preNode.left = null;
                }
            }
        } else if (dNode.left != null) {
            //3.2.左子树不为空，则从左子树中查找最大值（最右侧节点）
            preNode = dNode;
            TreeNode ldNode = dNode.left;//左子树最大值
            while (ldNode.right != null) {
                preNode = ldNode;
                ldNode = ldNode.right;
            }

            if (preNode.val < ldNode.val) {   //前驱节点 --》 右子节点 --断开
                preNode.right = null;
            } else {
                preNode.left = null;
            }
            dNode.val = ldNode.val;
        } else {
            //3.3.左子树为空，右子树不为空，则从右子树中查找最小值节点（最左侧节点）
            preNode = dNode;
            TreeNode rdNode = dNode.right;
            while (rdNode.left != null) {
                preNode = rdNode;
                rdNode = rdNode.left;
            }
            if (preNode.val < rdNode.val) {   //前驱节点 --》 右子节点 --断开
                preNode.right = null;
            } else {
                preNode.left = null;
            }
            dNode.val = rdNode.val;
        }

        return head.left;
    }

    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
     * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * 一般来说，删除节点可分为两个步骤：
     *
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     *
     * 示例:
     *
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
     *
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
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
}
