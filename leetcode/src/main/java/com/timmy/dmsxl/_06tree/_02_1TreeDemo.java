package com.timmy.dmsxl._06tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class _02_1TreeDemo {
    public static void main(String[] args) {
        _02_1TreeDemo demo = new _02_1TreeDemo();

        TreeNode root = new TreeNode(3);
        TreeNode node21 = new TreeNode(2);
        TreeNode node22 = new TreeNode(2);
        root.left = node21;
        root.right = node22;

        TreeNode node31 = new TreeNode(3);
        TreeNode node42 = new TreeNode(4);
        TreeNode node43 = new TreeNode(4);
        TreeNode node34 = new TreeNode(3);
//        node21.left = node31;
//        node21.right = node42;
//        node22.left = node43;
//        node22.right = node34;

        node21.right = node31;
        node22.right = node34;


        boolean symmetric = demo.isSymmetric(root);
        System.out.println("result:" + symmetric);
    }


    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     * 解题思路：递归+判断镜像节点是否对称
     * 1。递归算法
     * --1.1.确定入参与返回值，
     * （入参为左右两个子树的节点，返回值为bool表示该两个节点比较后是否镜像对称）
     * --1.2.确定终止条件
     * --1.3.每层递归逻辑
     * （先判断入参两个节点为空的情况）
     * （再判断入参两个节点，左右子树的对称情况）
     * （如果左右子树都对称，再判断入参两个节点的值是否相等）
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        return realSymmetric(root.left, root.right);
    }

    private boolean realSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right != null) {
            return false;
        }
        //两个都不为null
        boolean bLeft = realSymmetric(left.left, right.right);
        boolean bRight = realSymmetric(left.right, right.left);
        if (bLeft && bRight) {
            return left.val == right.val;
        }
        return false;
    }


    /**
     * 257. 二叉树的所有路径
     * <p>
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 解题思路：递归+回溯/前序遍历
     * <p>
     * 递归不断往树的深层次查找路径
     * 1。入参与返回值：节点，
     * 2。终止条件：
     * --遇到叶子节点，将本次路径path保存到list集合中
     * 3。单层递归逻辑：
     * --前序遍历，遍历左子树，与右子树路径
     * --回溯
     */
    public List<String> binaryTreePaths(TreeNode root) {
        result = new ArrayList<>();
        pathList = new ArrayDeque<>();
        if (root == null) {
            return result;
        }
        pathList.add(String.valueOf(root.val));
        getTreePath(root);
        return result;
    }

    List<String> result;
    ArrayDeque<String> pathList;

    private void getTreePath(TreeNode treeNode) {
        if (treeNode.left == null && treeNode.right == null) {
            //找到叶子节点，将path添加到集合中
            StringBuilder sb = new StringBuilder();
            for (String s : pathList) {
                sb.append(s).append("->");
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            sb = sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
            result.add(sb.toString());
        }
        if (treeNode.left != null) {
            pathList.addLast(String.valueOf(treeNode.left.val));
            getTreePath(treeNode.left);
            pathList.removeLast();
        }
        if (treeNode.right != null) {
            pathList.addLast(String.valueOf(treeNode.right.val));
            getTreePath(treeNode.right);
            pathList.removeLast();
        }
    }
}
