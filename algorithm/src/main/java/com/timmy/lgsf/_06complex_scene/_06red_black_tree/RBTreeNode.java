package com.timmy.lgsf._06complex_scene._06red_black_tree;

/**
 * 红黑树节点类：
 * 包含：
 * -左，右子节点
 * -父节点
 * -数据域
 * -是否是红节点
 */
public class RBTreeNode {

    public int value;
    public RBTreeNode left;
    public RBTreeNode right;
    public boolean isRed;
    public RBTreeNode parent;

    public RBTreeNode(int value) {
        this.value = value;
        this.isRed = true;
    }

    public RBTreeNode(int value, boolean isRed) {
        this.value = value;
        this.isRed = isRed;
    }

    public boolean isBlack() {
        return !isRed;
    }
}
