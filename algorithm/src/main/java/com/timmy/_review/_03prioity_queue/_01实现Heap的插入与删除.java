package com.timmy._review._03prioity_queue;

public class _01实现Heap的插入与删除 {

    public static void main(String[] args) {
        _01Heap heap = new _01Heap();
        heap.push(2);
        heap.push(12);
        heap.push(4);
        heap.push(7);
        heap.push(6);
        heap.push(5);
        heap.print();
        heap.pop();

        heap.print();
    }
}
