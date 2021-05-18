package com.timmy._review._05tree;

import com.timmy.common.PrintUtils;
import com.timmy.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _04目标和的所有路径 {

    public static void main(String[] args) {
        _04目标和的所有路径 demo = new _04目标和的所有路径();
        TreeNode node3 = new TreeNode(3, new TreeNode(1), new TreeNode(2));
        TreeNode root = new TreeNode(5, new TreeNode(4), node3);

        List<List<Integer>> res = demo.pathSum(root, 9);
        for (List<Integer> re : res) {
            PrintUtils.print(re);
        }
    }

    /**
     * 1.理解题意
     * -输入一棵二叉树，和一个目标值target，找到从根节点出发到达树的叶子节点的路径，且路径上节点和为target值
     * 2。解题思路
     * -从根节点开始遍历，选择前序遍历，遍历过程中不断将节点值保存到集合中，并且计算路径的节点数值之和
     * -当遍历到叶子节点时，判断路径之后是否是否等于目标值，等于的话，则将该条路径保存到结果中
     * -采用递归方式，当遍历完当前节点后，需要将该节点保存到路径中，但是后面（右子树）的路径不需要该节点，
     * --所以在单层递归结束时需要将该节点从路径中删除出去（回溯算法）
     * 3。复杂度分析
     * -时间：需要遍历每个节点 - O(n)
     * -空间：最糟的情况是将所有的节点都放到路径集合中 - O(n)
     * 4.总结
     * -从根节点开始不断往下遍历节点，直到叶子节点，都放到路径集合中
     * -从根节点开始不断减少目标值，每次减少为遍历节点的数量，直到减少到0为止
     * -使用递归遍历，因为二叉树每个节点都有两个子节点，需要在递归结束之前收集路径节点，否者到了叶子节点下一层，就会出现遍历两次
     * -遍历完本次节点后，需要将当前节点从路径集合中删除，这样遍历下一个路径分支时，就不受影响了
     * -传递的目标值，因为递归传递的是数值的副本，往下传递值减少，归的时候拿到的还是当前原先不受影响的目标中间值
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumR(res, path, root, target);
        return res;
    }

    /**
     * 递归与回溯算法：
     *
     * @param res    所有结果
     * @param path   当前路径的节点集合
     * @param root   当前遍历的节点
     * @param target 当前遍历到该节点时，路径的节点之和，（当到达叶子节点，且target==0是，说明当前路径符合条件）
     */
    private void pathSumR(List<List<Integer>> res, List<Integer> path, TreeNode root, int target) {
        //1.参数与返回值
        //2。终止条件
        if (root == null) {
            return;
        }
        //3。单层递归逻辑实现
        path.add(root.val);
        target = target - root.val;

        if (root.left == null && root.right == null) {
            if (target == 0) {
                res.add(new ArrayList<Integer>(path));
            }
        } else {
            pathSumR(res, path, root.left, target);
            pathSumR(res, path, root.right, target);
        }

        //4。回溯，当前节点遍历完，需要从当前路径中删除
        path.remove(path.size() - 1);
    }

    /**
     * 给定一棵二叉树，一个目标值。请输出所有路径，需要满足根结点至叶子结点之和等于给定的目标值。
     * tree: [5,4,3,null,null,1,2]
     * 输入：target = 9
     * 输出：[[5,4], [5,3,1]]
     *
     * 解释：从根结点到叶子结点形成的路径有 3 条：[5, 4], [5, 3, 1], [5, 3, 2]，其中只有 [5, 4], [5, 3, 1] 形成的和为 9。
     */
}
