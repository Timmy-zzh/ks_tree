package com.timmy.practice._09month;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _02二叉树中序遍历 {

    public static void main(String[] args) {
        _02二叉树中序遍历 demo = new _02二叉树中序遍历();
        TreeNode node20 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), node20);
        PrintUtils.printLevel(root);
        List<Integer> res = new ArrayList<>();
//        demo.midTraversal(root, res);
        demo.midTraversal_molis(root, res);
        PrintUtils.print(res);
    }

    /**
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 中序遍历： 9, 3, 15, 20, 7,
     */

    /**
     * 2。莫里斯遍历
     * -中序遍历顺序是左根右，当前树的结构是根节点有两个指针指向其左右子节点
     * -莫里斯遍历的节点目标是将树结构变成一个链表结构，
     * -从整体来看的话，可以看作先遍历完左子树节点，左子树的右子节点指向根节点，然后接着遍历右子树
     * -操作：
     * --从根节点开始，先找到左子节点（作为标记节点），然后不断便利找到左子节点的最右侧叶子节点（标记节点转移到当前节点），
     * ---然后设置该节点的右子节点指向根节点，相当于增加了一条辅助线
     * --接着根节点移动到左子节点，继续上面的操作
     * --直到根节点移动到最左侧的叶子节点，以为不存在子节点，将当前叶子节点添加到结果集合中，并且根节点转移到其右子节点（上层根节点）
     * --重点：
     * --这次遍历时，因为标记节点的最后右子节点指向根节点，说明遍历到辅助标记节点，需要断开辅助线
     * --并且将根节点添加到结果集合中，并且根节点转移到右子节点
     */
    public void midTraversal_molis(TreeNode root, List<Integer> res) {
        while (root != null) {
            if (root.left != null) {
                //1。寻找左子节点，并作为标记节点
                TreeNode exPoint = root.left;
                //一直遍历，直到标记节点的最右侧叶子节点
                while (exPoint.right != null && exPoint.right != root) {
                    exPoint = exPoint.right;
                }
                //2。1。情况1，遍历找到最右侧叶子节点--添加辅助线指向根节点
                if (exPoint.right == null) {
                    exPoint.right = root;
                    root = root.left;
                } else {
                    //2.2.情况2,遍历到辅助线 -- 断开辅助线，根节点添加到结果集合中，并将根节点指向右子节点
                    res.add(root.val);
                    exPoint.right = null;
                    root = root.right;
                }
            } else {
                //3。遍历到最左侧叶子节点-根节点添加到结果集中，根节点指向右子节点
                res.add(root.val);
                root = root.right;
            }
        }
    }

    /**
     * 二叉树中序遍历:迭代
     * 左 - 根 - 右
     * 从树的根节点开始遍历
     * -先不断遍历获取树的左子节点，直到最左侧的叶子节点
     * -然后从栈中取出栈顶元素，将该栈顶元素存放在集合中，
     * -并且开始遍历该元素的左子节点
     */
    public void midTraversal_v1(TreeNode node, List<Integer> res) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            //1.先遍历到最左侧的叶子节点,并存放到stack中
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //2.node为null，说明遍历到最左侧叶子节点了，从stack中取出元素,并从节点的右子节点开始从头遍历
            TreeNode pop = stack.pop();
            res.add(pop.val);
            //3.遍历右子节点
            node = pop.right;
        }
    }

    /**
     * 二叉树中序遍历-递归
     */
    public void midTraversal(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        midTraversal(node.left, res);
        res.add(node.val);
        midTraversal(node.right, res);
    }

    /**
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
}
