package algorithm.sort.linkedlist;

import com.sun.tools.javac.util.Assert;

public class CircularQueue {
    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;
    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为capacity的数组
    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
        // 队列满了
        if ((tail + 1) % n == head) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) return null;
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }

    public void printAll() {
        if (0 == n) return;
        for (int i = head; i % n != tail; i = (i + 1) % n) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);
        Assert.check(cq.enqueue("1"));
        Assert.check(cq.enqueue("2"));
        Assert.check(cq.enqueue("3"));
        Assert.check(cq.enqueue("4"));
        Assert.check(cq.enqueue("5"));
        Assert.check(!cq.enqueue("6"));
        cq.printAll();
        System.out.println("=============================");

        System.out.println(cq.dequeue());
        cq.printAll();
        System.out.println(cq.dequeue());
        cq.printAll();
        System.out.println(cq.dequeue());
        cq.printAll();
        System.out.println(cq.dequeue());
        cq.printAll();
        System.out.println(cq.dequeue());
        cq.printAll();

        //aq.printAll();
    }
}
