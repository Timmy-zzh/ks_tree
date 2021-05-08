package com.timmy._review._03prioity_queue;

/**
 * 大顶堆实现：
 * -底层使用数组数据结构
 * -堆是一棵完全二叉树：注意 父节点与左右子节点的下表规律关系
 * -实现插入元素push， 删除元素pop 功能
 * -上浮 / 下沉
 */
public class _01Heap {

    //底层数据结构--数组
    int[] arr;
    //当前数组的使用长度
    int index;
    int capacity;

    public _01Heap() {
        capacity = 100;
        arr = new int[capacity];
        index = 0;
    }

    public _01Heap(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        index = 0;
    }

    /**
     * 插入数据：
     * 1。新元素节点保存到数组最后位置index
     * 2。然后新元素节点进行上浮操作
     */
    public boolean push(int val) {
        if (isFull()) {
            return false;
        }
        arr[index++] = val;
        floating(index - 1);
        return true;
    }

    /**
     * 节点上浮：
     * -判断当前节点比父节点的值是否大
     * -如果大，则上浮，节点也需要上移
     */
    private void floating(int i) {
        int tmp = arr[i];

        while (i > 0) {     //i==0到根节点结束
            //父节点位置
            int par = (i - 1) / 2;
            if (arr[i] > arr[par]) {
                arr[i] = arr[par];
                i = par;
            } else {
                break;
            }
        }
        arr[i] = tmp;
    }

    /**
     * 堆顶元素出队：
     * 1。第一个元素获取后返回
     * 2。数组最后元素复制到第一个节点元素
     * 3。第一个节点元素进行下沉操作
     */
    public int pop() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int res = arr[0];
        arr[0] = arr[--index];
        sinking(0);
        return res;
    }

    /**
     * 下沉：
     * -判断当前节点是否比左右子节点小
     * -如果小，则需要下沉，选取左右子节点中值更大的节点，进行下沉，并且下标往下移动
     * 往下移动之前，需要先进行边界判断
     */
    private void sinking(int i) {
        int tmp = arr[i];
        //todo 先注意边界，判断左右子节点是否存在
        int j;
        while ((j = (2 * i) + 1) < index) {
            //找到左右节点中的更大值
            if ((2 * i) + 2 < index && arr[j] < arr[j + 1]) {
                j = (2 * i) + 2;
            }

            if (arr[j] > tmp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = tmp;
    }

    private boolean isEmpty() {
        return index == 0;
    }

    private boolean isFull() {
        return index == capacity;
    }

    public void print() {
        for (int i = 0; i < index; i++) {
            System.out.print(arr[i] + " ,");
        }
        System.out.println();
    }
}
