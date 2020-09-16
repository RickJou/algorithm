package algorithm.struct.queue;

import com.sun.tools.javac.util.Assert;

public class ArrayQueue<T> {
    private T[] data;
    private int n =0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        data = (T[]) new Object[capacity];
        n = capacity;
    }

    public boolean enqueue(T t) {
        if (tail == n) {
            System.out.println("queue is full,can't enqueue!");
            return false;
        }
        data[tail++] = t;
        return true;
    }

    public T dequeue() {
        if (head == tail) {
            System.out.println("queue is empty!");
            return null;
        }

        return data[head++];
    }

    private boolean isEmpty(){
        return head == tail;
    }

    public void printAll() {
        for (int i = head; i < tail; i++) {
            System.out.print(" " + data[i]);
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        ArrayQueue aq = new ArrayQueue(5);
        Assert.check(aq.enqueue("1"));
        Assert.check(aq.enqueue("2"));
        Assert.check(aq.enqueue("3"));
        Assert.check(aq.enqueue("4"));
        Assert.check(aq.enqueue("5"));
        Assert.check(!aq.enqueue("6"));
        aq.printAll();
        System.out.println("=============================");

        System.out.println(aq.dequeue());
        aq.printAll();
        System.out.println(aq.dequeue());
        aq.printAll();
        System.out.println(aq.dequeue());
        aq.printAll();
        System.out.println(aq.dequeue());
        aq.printAll();
        System.out.println(aq.dequeue());
        aq.printAll();

        //aq.printAll();
    }
}
