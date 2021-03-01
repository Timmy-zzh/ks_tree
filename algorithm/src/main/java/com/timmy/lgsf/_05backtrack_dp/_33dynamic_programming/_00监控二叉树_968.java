package com.timmy.lgsf._05backtrack_dp._33dynamic_programming;

import com.timmy.common.TreeNode;

public class _00监控二叉树_968 {

    public static void main(String[] args) {
        _00监控二叉树_968 demo = new _00监控二叉树_968();

        TreeNode node1l = new TreeNode(0,new TreeNode(0), new TreeNode(0));
        TreeNode root = new TreeNode(0,node1l,null);

//        TreeNode node1 = new TreeNode(0, null, new TreeNode(0));
//        TreeNode node2 = new TreeNode(0, node1, null);
//        TreeNode node3 = new TreeNode(0, node2, null);
//        TreeNode root = new TreeNode(0, node3, null);

        int res = demo.minCameraCover(root);
        System.out.println("res:" + res);
    }

    /**
     * 1.
     * 2.动态规划：
     * -递归，后序便利
     * --参数与返回值，参数为当前根节点表示的树，返回值为当前节点的状态值
     * --终止条件：空节点，返回已覆盖状态
     * 每个节点的状态分类：
     * -无覆盖：0
     * -放置了摄像头：1
     * -有覆盖：2 （已覆盖，表示当前节点没有放置摄像头，当时下层子节点放置了摄像头）
     * <p>
     * 2。1。原问题分解为子问题
     * -根节点是否放置摄像头，根据左右子节点
     * --如果左右子节点又一个是无覆盖，则放置
     * --则左右子节点的状态为有覆盖2，或者放置了摄像头1
     * ---如果左右子节点其中有一个放置了摄像头，则当前节点的状态为有覆盖
     * ---否则，左右子节点的状态都是有覆盖的状态，则当前节点的状态为无覆盖
     * 3。边界和细节问题
     * 后序遍历，返回的是当前节点的状态
     * -根节点的状态，是根据左右子节点的状态进行判断的
     * -空节点状态为有覆盖
     * -最后根据根节点的状态，判断跟节点是否需要添加摄像头（如果为无覆盖，则放置摄像头）
     *
     * @param root
     * @return
     */
    private final int NOT_CONVER = 0;
    private final int HAS_CAMERA = 1;
    private final int CONVERED = 2;
    int res = 0;//代表放置摄像头的个数

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int status = nextOrder(root);
        if (status == NOT_CONVER) {
            res++;
        }
        return res;
    }

    private int nextOrder(TreeNode root) {
        if (root == null) {
            return CONVERED;
        }
        int left = nextOrder(root.left);
        int right = nextOrder(root.right);
        if (left == NOT_CONVER || right == NOT_CONVER) {
            res++;
            return HAS_CAMERA;
        }
        if (left == HAS_CAMERA || right == HAS_CAMERA) {
            return CONVERED;
        }
        //左右子节点都是有覆盖状态，当前节点状态设置为无覆盖，让上层节点去添加摄像头
        return NOT_CONVER;
    }

    /**
     * 1.理解题意
     * -输入一颗树，要求在树的某些节点上放置摄像头，摄像头可以监视自身，父节点和子节点，
     * -要求放置的摄像头可以监视树上所有节点，求放置最少摄像头的个数，能满足上述条件
     * 2。解题思路：
     * 2。1。从上到下遍历，暴力解法，使用辅助变量
     * -使用辅助变量interval：表示上层节点有多少层没有放置摄像头
     * 则当前节点为跟节点的树所需最小摄像头取决于interval+左右子树的状态，需进行分类讨论
     * 2。2。
     * -interval=2，表示当前节点上面都没有放置摄像头，则当前节点必须放置摄像头
     * -interval=1，且左右子节点都不存在
     * --此节点必须放置
     * -interval=1，左右子节点都存在
     * --放置情况有三种情况：根节点，左子节点，右子节点
     * -interval=1，左右子节点只存在一个
     * --放置情况有两种情况：根节点，左子节点/右子节点
     * -interval=0
     * --放置情况有三种情况：根节点，左子节点，右子节点
     * 2。3。递归
     * -参数与返回值：当前根节点，interval-以当前节点为根节点的上层有多少层没有放置摄像头
     * --返回值表示当前根节点，放置的最小摄像头个数可以监视树上所有的节点
     * -终止条件：遍历到空节点，返回0，不用放置摄像头
     * -单层递归逻辑
     * 2。4。原始树的根节点interval的值
     * -如果为2，则当前跟节点一定要放置摄像头--不合适
     * -如果为0，表示上层节点放置了摄像头--也不符合逻辑
     * --设置为1，根据三种不同情况进行判断
     *
     * @param root
     * @return
     */
    public int minCameraCover_v1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return search(root, 1);
    }

    private int search(TreeNode root, int interval) {
        //递归到空节点，返回0，没有放置摄像头
        if (root == null) {
            return 0;
        }
        /**
         * interval==1，表示上层节点没有监视到当前根节点，
         * 分三种情况：
         * -如果左右子节点都为空：则当前节点必须放置摄像头
         * else
         * -根节点放置：root.val=1,往下层传递的interval为0，表示上层节点放置了摄像头
         * -或者左右子节点放置了摄像头，往下传递左右子节点为根节点时，interval的值根据情况判断
         * --最后根据不同情况判断那种情况放置的摄像头个数最少，则返回
         */
        if (interval == 1 && root.left == null && root.right == null) {
            //interval==1，表示上层节点没有监视到当前根节点,又没有左右子节点
            //->放置摄像头
            root.val = 1;
            return 1;
        }

        if (interval == 1 && root.left != null && root.right != null) {
            //左右子节点都存在，或其中一个子节点存在
            //1。摄像头放置在根节点
            //-当前根节点的摄像头个数为：左子树摄像头个数 + 右子树摄像头个数 + 1
            root.val = 1;
            int x = search(root.left, 0) + search(root.right, 0) + 1;

            //2。摄像头放在左子节点
            root.val = 0;
            int y = search(root.left, interval + 1) + search(root.right, interval);

            //3。摄像头放置在右子节点
            root.val = 0;
            int z = search(root.left, interval) + search(root.right, interval + 1);
            return Math.min(x, Math.min(y, z));
        }

        if (interval == 2) {    // 必须放置摄像头了
            root.val = 1;
            return search(root.left, 0) + search(root.right, 0) + 1;
        } else {
            //interval=0,或者interval=1，但是左右子节点只有一个不为空
            //摄像头放置有两种情况：放置在根节点， 防止在左右子节点中的一个
            root.val = 1;
            int x = search(root.left, 0) + search(root.right, 0) + 1;

            //放置在左右子节点上，只有一种情况会成功
            root.val = 0;
            int y = search(root.left, interval + 1) + search(root.right, interval + 1);
            if (x < y) {
                root.val = 1;
                return x;
            } else {
                return y;
            }
        }
    }

    /**
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * 计算监控树的所有节点所需的最小摄像头数量。
     *
     * 示例 1：
     * 输入：[0,0,null,0,0]
     * 输出：1
     * 解释：如图所示，一台摄像头足以监控所有节点。
     *
     * 示例 2：
     * 输入：[0,0,null,0,null,0,null,null,0]
     * 输出：2
     * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
     *
     * 提示：
     * 给定树的节点数的范围是 [1, 1000]。
     * 每个节点的值都是 0。
     *
     * 链接：https://leetcode-cn.com/problems/binary-tree-cameras
     */
}
