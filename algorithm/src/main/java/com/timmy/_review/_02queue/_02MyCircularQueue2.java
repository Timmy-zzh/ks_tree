package com.timmy._review._02queue;

/**
 * 2.解题思路：
 * -_02MyCircularQueue类中，队列为空和队列为满时，头尾指针都一样，使用 used 变量进行区分，
 * --使用used方式，控制变量更多，在多线程编程情况下，想实现无锁编程则越加困难。
 * -新方法：循环队列的数组大小为k+1； 留出一个空位置，保证头尾节点不相连
 * -队列空时：头尾节点指针相等
 * -队列满时：队尾指针下一个为队头指针： front = (rear+1)%captical
 */
public class _02MyCircularQueue2 {

    int[] queue;
    int captical;
    int fornt = 0;
    int rear = 0;

    public _02MyCircularQueue2(int k) {
        queue = new int[k + 1];
        captical = k + 1;
    }

    /**
     * 入队列：
     * -先判断队列是否已满
     * -插入队尾
     * -队尾指针移动
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        queue[rear] = value;
        rear = (rear + 1) % captical;
        return true;
    }

    /**
     * 出队列：队头元素出队
     * -先判断队列是否为空
     * -不为空，则找到队头元素出队列
     * -队头元素指针移动下一位
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        fornt = (fornt + 1 + captical) % captical;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[fornt];
    }

    /**
     * 出队列：
     * -先判断队列是否为空
     * -找到队尾元素的指针位置：rear = (rear -1 + captial)%captical
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        //注意获取队尾元素的指针位置
        int rearIndex = (rear - 1 + captical) % captical;
        return queue[rearIndex];
    }

    public boolean isEmpty() {
        return fornt == rear;
    }

    public boolean isFull() {
        return fornt == (rear + 1) % captical;
    }

    public void print() {
        System.out.println("-------print--------");
        int curr;
        int count = rear >= fornt ? rear - fornt : rear + captical - fornt;
        System.out.println("rear:" + rear + " ,front:" + fornt + " ,count:" + count);
        for (int i = 0; i < count; i++) {
            curr = (fornt + i) % captical;
            System.out.println("curr:" + curr + " ,value:" + queue[curr]);
        }
    }
}
