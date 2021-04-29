package com.timmy._review._02queue;

/**
 * 1.理解题意：循环队列实现
 * 2.模拟运行：
 * -循环队列的特性是，元素个数固定，队列的最后一个元素（队尾）的下一个元素是队头元素
 * -采用数组作为底层数据结构
 * -刚开始队头和队尾指针指向同一个对象（队列为空）
 * -当队列满时，队头和队尾也是指向同一个对象，这两种情况可通过一个变量used（已使用的元素个数）进行区分
 * -入队列时，先判断队列是否已经满了；没满，则插入到队尾中
 * -出队列，先判断队列为空；不为空，则队头元素出队列
 * 3。边界和细节问题：
 * -空队列和队列满时处理
 * -fornt 出队列后，指向下一个元素位置： front = (front + 1)% captical  -- 对容量取模操作
 * -rear 入队列，队尾指针移动到下一个元素位置： rear = (rear + 1)% captical
 * 4。边界与细节问题
 * -入队列与出队列边界问题
 * 5。复杂度分析
 * -时间：入队列，出队列 O（1）
 * -空间：数组可能保存容量大小的元素 O(n)
 * 6.总结：
 * -在固定容量的数据结构中，进行数据的存取，
 * -入队列：元素放在队尾位置，队尾指针往后移动
 * -出队列：取队头元素，队头元素指针后移
 * --注意边界问题
 * -元素遍历：从队头元素开始遍历，一共遍历used 个数
 */
public class _02MyCircularQueue {

    int[] queue;
    int captical;
    int front = 0;
    int rear = 0;
    int used = 0;

    public _02MyCircularQueue(int k) {
        queue = new int[k];
        captical = k;
    }

    /**
     * 入队列：
     * -先判断队列是否已满
     * -插入队尾
     * -队尾指针移动
     * -used变量+1
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        queue[rear] = value;
        rear = (rear + 1) % captical;
        used++;
        return true;
    }

    /**
     * 出队列：队头元素出队
     * -先判断队列是否为空
     * -不为空，则找到队头元素出队列
     * -队头元素指针移动下一位
     * -used--
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1 + captical) % captical;
        used--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[front];
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
        return used == 0;
    }

    public boolean isFull() {
        return used == captical;
    }

    public void print() {
        System.out.println("-------print--------");
        int curr;
        for (int i = 0; i < used; i++) {
            curr = (front + i) % captical;
            System.out.println("curr:" + curr + " ,value:" + queue[curr]);
        }
    }
}
