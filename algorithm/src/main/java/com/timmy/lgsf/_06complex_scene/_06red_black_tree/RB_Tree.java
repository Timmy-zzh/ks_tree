package com.timmy.lgsf._06complex_scene._06red_black_tree;

/**
 * 红黑树数据结构功能实现：
 * 1。红黑树的创建，- 节点插入
 * 2。查找
 * 3。删除
 */
public class RB_Tree {

    RBTreeNode root;

    /**
     * 节点查找：根据输入的数据查看与vlaue值相等的节点
     * -根据二叉搜索树特性，进行左右子树的遍历
     *
     * @param vlaue
     * @return
     */
    public RBTreeNode find(int vlaue) {
        RBTreeNode node = root;
        while (node != null) {
            if (node.value == vlaue) {
                return node;
            } else if (vlaue < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    /**
     * 删除节点：
     * -1。查找删除节点，不存在直接返回
     * -2。情况1：删除节点有两个子节点
     * --用后继节点的值覆盖删除节点的值
     * --删除节点变为后继节点
     * -3。优先选择左节点为替代节点
     * -4。存在替代节点
     * --情况2：删除节点为红色，直接替换掉删除节点
     * --情况3：删除节点为黑色，先替换删除节点再自平衡
     * 5。不存在替代节点
     * --情况4：删除节点为黑色，先做自平衡再删除
     * --情况5：删除节点为红色，直接删除
     *
     * @param value
     */
    public void delete(int value) {
        RBTreeNode node = find(value);
        if (node != null) {
            deleteNode(node);
        }
    }

    private void deleteNode(RBTreeNode node) {
        //1。情况1：删除节点有两个子节点
        if (node.left != null && node.right != null) {
            //查找后继节点
            RBTreeNode nextNode = getNextNode(node);
            //将后继节点的值与删除节点交换，删除节点转移到后继节点
            node.value = nextNode.value;
            node = nextNode;
        }

        //2。选择替代节点
        RBTreeNode replaceNode = node.left != null ? node.left : node.right;
        if (replaceNode != null) {
            //存在替代节点
            //--情况2：删除节点为红色，直接替换掉删除节点
            //--情况3：删除节点为黑色，先替换删除节点再自平衡

        } else if (node.parent == null) {
            //删除节点为根节点
            root = null;
        } else {
            //不存在替代节点
            //--情况4：删除节点为黑色，先做自平衡再删除
            //--情况5：删除节点为红色，直接删除
        }
    }

    /**
     * 查找当前节点node的后继节点
     * -如果当前节点存在右子节点，则从右子树中查找最小的节点（遍历查找右子节点的左子节点）
     * -如果当前节点不存在右子节点，则不断往上遍历，查找上层节点
     *
     * @param node
     * @return
     */
    private RBTreeNode getNextNode(RBTreeNode node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            RBTreeNode parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    /**
     * 数据插入:
     * 节点插入有如下情况：
     * -1。情况1：空节点，新节点设为根节点
     * -2。查找插入位置（查找到插入节点的父节点）
     * -3。新节点挂到父节点下
     * -4。情况2：父节点为黑色，直接返回
     * -5。父节点为红色，需进行插入自平衡处理
     * --父节点为红色循环处理
     * --5。1。获取爷爷，叔伯节点
     * --5。2。情况3：父红叔红，父黑叔黑爷变红，继续循环
     * --5。3。情况4：父红叔黑，爷父新成<型，-》以父节点作为中心点进行左旋
     * --5。4。情况5：父红叔黑，爷父新成/型，-》父黑爷红变色，以爷节点为中心进行右旋
     * --5。5。情况6：父红叔黑，爷父新成>型，-》以父节点为中心进行右旋
     * --5。6。情况7：父红叔黑，爷父新成\型，-》父黑爷红变色，以爷节点为中心进行左旋
     * 保证根节点为黑色
     *
     * @param value
     */
    public void insert(int value) {
        System.out.println("插入元素：" + value);
        //1.创建需要插入的节点
        RBTreeNode node = new RBTreeNode(value);
        //情况1，跟节点为空，--》新插入的节点为跟节点
        if (root == null) {
            root = node;
            root.isRed = false;
            return;
        }
        //2.根节点不为空，查找插入节点的位置，和父节点位置
        RBTreeNode insertNode = root;
        RBTreeNode parent = null;
        //记录插入的节点是父节点的左子节点还是右子节点？
        boolean isLeftChild = false;
        //根据二叉搜索树特性，进行便利，找到插入点的位置
        while (insertNode != null) {
            parent = insertNode;
            if (value < insertNode.value) {//小于根节点元素值，插入位置在左子树中
                isLeftChild = true;
                insertNode = insertNode.left;
            } else if (value > insertNode.value) {//大于，插入位置在右子树中
                isLeftChild = false;
                insertNode = insertNode.right;
            } else { //插入数据已经存在，不处理，直接返回
                return;
            }
        }

        //3.将新节点插入到父节点的下面,与父节点相关联
        if (isLeftChild) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        node.parent = parent;

        //情况2：插入节点的父节点是黑色，则处理结束
        if (!parent.isRed) {
            return;
        }
        print();

        //如果父节点是红色，而新插入的节点默认也是红色，则不符合红黑色的规则（相邻节点不同为红色）--》进行插入自平衡处理
        balanceInsert(node);

    }

    /**
     * --父节点为红色循环处理
     * --5。1。获取爷爷，叔伯节点
     * --5。2。情况3：父红叔红，父黑叔黑爷变红，继续循环
     * --5。3。情况4：父红叔黑，爷父新成<型，-》以父节点作为中心点进行左旋
     * --5。4。情况5：父红叔黑，爷父新成/型，-》父黑爷红变色，以爷节点为中心进行右旋
     * --5。5。情况6：父红叔黑，爷父新成>型，-》以父节点为中心进行右旋
     * --5。6。情况7：父红叔黑，爷父新成\型，-》父黑爷红变色，以爷节点为中心进行左旋
     *
     * @param node
     */
    private void balanceInsert(RBTreeNode node) {
        System.out.println("balanceInsert" + node.value);
        //父节点，爷爷节点
        RBTreeNode parent, grandpa;
        while ((parent = node.parent) != null && parent.isRed) {  //父节点为红色
//            System.out.println("node:" + node.value);
            //获取叔伯节点
            grandpa = parent.parent;
            RBTreeNode uncle = grandpa.left;
            if (uncle != null && uncle == parent) {
                uncle = grandpa.right;
            }

            //情况3，父红叔红，--》变色：父黑叔黑，爷红，然后以爷爷节点为基准，继续向上判断是否自平衡
            if (uncle != null && uncle.isRed) {
                System.out.println("情况3");
                parent.isRed = false;
                uncle.isRed = false;
                grandpa.isRed = true;
                node = grandpa;
                continue;
            }

            //父节点在爷爷节点的左边
            if (parent == grandpa.left) {
                //情况4：父红叔黑，爷父新成<型，-》以父节点作为中心点进行左旋
                //node节点在父节点的右边
                if (node == parent.right) {
                    System.out.println("情况4-左旋");
                    leftRotate(parent);
                    //变成情况5，node节点与父节点交换
                    RBTreeNode temp = node;
                    node = parent;
                    parent = temp;
                } else {
                    System.out.println("情况5-父黑爷红变色,右旋");
                    //情况5：父红叔黑，爷父新成/型，-》父黑爷红变色，以爷节点为中心进行右旋
                    parent.isRed = false;
                    grandpa.isRed = true;
                    rightRotate(grandpa);
                }
            } else {
                //情况6：父红叔黑，爷父新成>型，-》以父节点为中心进行右旋
                if (node == parent.left) {
                    System.out.println("情况6-右旋");
                    rightRotate(parent);
                    RBTreeNode temp = node;
                    node = parent;
                    parent = temp;
                } else {
                    System.out.println("情况7-父黑爷红变色,左旋");
                    //情况7：父红叔黑，爷父新成\型，-》父黑爷红变色，以爷节点为中心进行左旋
                    parent.isRed = false;
                    grandpa.isRed = true;
                    leftRotate(grandpa);
                }
            }
        }
        root.isRed = false;
    }

    /**
     * 左旋：
     * 1。理解题意：
     * -输入左旋操作的旋转中心节点
     * -左旋：以某节点作为旋转节点，其右子节点变为旋转节点的父节点，右子节点的左子节点变为旋转节点的右子节点，左子节点保持不变
     * 2。解题思路
     * -1。获取旋转节点的父节点，右子节点
     * -2。右子节点变为父节点的孩子
     * -3。右子节点变为旋转节点的父节点
     * -4。右子的左孩子变为旋转节点的右子
     * -5。旋转节点变为右子左孩子的父节点
     * -6。旋转节点变为右子节点的左孩子
     *
     * @param node
     */
    public void leftRotate(RBTreeNode node) {
        //1。获取父节点和右子节点
        RBTreeNode parent = node.parent;
        RBTreeNode right = node.right;

        //2。旋转节点的右子节点变为父节点的孩子节点
        if (parent == null) { //parent为空，right节点变为跟节点
            root = right;
            right.parent = null;
        } else {
            //right节点变为父节点的子节点，需要区分是左孩子还是右孩子？
            if (parent.left == node) {
                parent.left = right;
            } else {
                parent.right = right;
            }
            right.parent = parent;
        }

        //3。右子节点right与旋转节点node关联
        node.parent = right;
        node.right = right.left;
        if (right.left != null) {
            right.left.parent = node;
        }
        right.left = node;
    }

    /**
     * 1。右旋：以某节点作为旋转节点，其左子节点变为旋转节点的父节点，左子节点的右子节点变为旋转节点的左子节点，右子节点保持不变
     * 2。实现步骤
     * -1。获取父节点和左子节点
     * -2。左子节点变为父节点的孩子节点
     * -3。左子节点变为旋转节点的父节点
     * -4。左子节点的右孩子变为旋转节点的左孩子
     * -5。旋转节点变为左子节点右节点的父节点
     * -6。旋转节点变为左子节点的右孩子
     *
     * @param node 旋转节点
     */
    public void rightRotate(RBTreeNode node) {
        //1。获取父节点和左子节点
        RBTreeNode parent = node.parent;
        RBTreeNode left = node.left;

        //2.左子节点变为父节点的孩子节点，将左子节点与父节点关联
        if (parent == null) {  //父节点为null，left节点变为根节点
            root = left;
            left.parent = null;
        } else {
            if (parent.left == node) {
                parent.left = left;
            } else {
                parent.right = left;
            }
            left.parent = parent;
        }

        //3。左子节点与旋转节点关联
        node.parent = left;
        //左子节点的右子节点处理
        node.left = left.right;
        if (left.right != null) {
            left.right.parent = node;
        }
        left.right = node;
    }

    /**
     * 打印：
     * 中序遍历，节点是红还是黑
     */
    public void print() {
        System.out.println("----------前序排序：根左右----------");
        printTreePre(root);
        System.out.println();
        System.out.println("----------中序排序：左根右----------");
        printTreeMid(root);
        System.out.println();
        System.out.println();
    }

    //前序排序：根左右
    private void printTreePre(RBTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + (root.isBlack() ? "黑 ，" : "红 ，"));
        printTreeMid(root.left);
        printTreeMid(root.right);
    }

    //中序排序：左根右
    private void printTreeMid(RBTreeNode root) {
        if (root == null) {
            return;
        }
        printTreeMid(root.left);
        System.out.print(root.value + (root.isBlack() ? "黑 ，" : "红 ，"));
        printTreeMid(root.right);
    }
}
