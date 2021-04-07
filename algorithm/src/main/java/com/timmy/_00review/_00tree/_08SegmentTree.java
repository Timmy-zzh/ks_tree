package com.timmy._00review._00tree;

import com.timmy.common.PrintUtils;

/**
 * 线段树实现：
 * -输入一个原始数组，根据原始数组构建线段树
 * --线段树是一棵完全二叉树，节点的值代表数组某区间的元素和
 * --根节点为数组全部区域的元素和，叶子节点的值为原始数组中的下表对应元素值
 * -完全二叉树使用数组保存节点的值，根节点[i]与左右子节点的关系:左=(2*i+1)  右
 */
public class _08SegmentTree {

    int[] values;
    int length;

    public _08SegmentTree(int[] nums) {
        length = nums.length;
        this.values = new int[length * 4];
        build(nums, 0, 0, nums.length - 1);
    }

    /**
     * 根据给定的原始数组，构建线段树
     * -线段树的节点表示原始数组区间范围[left,right] 的元素和
     * -自顶向下不断递归遍历，直到节点表示的区间范围相等，即是当前节点的值
     * -再根据左右子节点的值求和得到根节点的元素值
     *
     * @param nums  原始数组
     * @param pos   线段树当前遍历的节点下标
     * @param left  当前节点表示的区间范围[left,right]
     * @param right
     */
    private void build(int[] nums, int pos, int left, int right) {
        System.out.println("pos:" + pos + " ,left:" + left + " ,right:" + right);
        if (left == right) {
            values[pos] = nums[left];
            return;
        }
        int mid = (left + right) / 2;
        build(nums, 2 * pos + 1, left, mid);
        build(nums, 2 * pos + 2, mid + 1, right);
        values[pos] = values[2 * pos + 1] + values[2 * pos + 2];
    }

    /**
     * 查询原始数组nums 区间范围[qleft,qright]的元素之和
     *
     * @param qleft
     * @param qright
     * @return
     */
    public int query(int qleft, int qright) {
        return query(values, 0, 0, length - 1, qleft, qright);
    }

    /**
     * 查询原始数组区间范围的元素之和
     * -分成三种情况：
     * --如果当前节点表示的区间范围在检索范围内，则返回当前节点的值
     * --如果在检索范围外，则返回0
     * --否则继续二分法拆分
     */
    private int query(int[] values, int pos, int left, int right, int qleft, int qright) {
        if (left >= qleft && right <= qright) {
            return values[pos];
        }
        if (left > qright || right < qleft) {
            return 0;
        }
        int mid = (left + right) / 2;
        int leftSum = query(values, 2 * pos + 1, left, mid, qleft, qright);
        int rightSum = query(values, 2 * pos + 2, mid + 1, right, qleft, qright);
        return leftSum + rightSum;
    }

    /**
     * 更新元素数组下标index的值为newValue
     *
     * @param index
     * @param newValue
     */
    public void update(int index, int newValue) {
        update(values, 0, 0, length - 1, index, newValue);
    }

    /**
     * 在原始数组范围[left,right] 内查找位置index并将线段树的值设置为新的值
     * -并更新根节点的值
     *
     * @param values
     * @param pos
     * @param left
     * @param right
     * @param index
     * @param newValue
     */
    private void update(int[] values, int pos, int left, int right, int index, int newValue) {
        System.out.println("update--- pos:" + pos + " ,left:" + left + " ,right:" + right);
        if (left == right && left == index) {
            values[pos] = newValue;
            return;
        }
        int mid = (left + right) / 2;
        if (index > mid) {//在右边节点范围
            update(values, 2 * pos + 2, mid + 1, right, index, newValue);
        } else {
            update(values, 2 * pos + 1, left, mid, index, newValue);
        }
        values[pos] = values[2 * pos + 1] + values[2 * pos + 2];
    }

    public void print() {
        PrintUtils.print(values);
    }
}
