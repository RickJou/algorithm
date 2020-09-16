package algorithm.sort.queue;

import com.sun.tools.javac.util.Assert;

public class DynamicArrayQueue<T> {
    private T[] data;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    private DynamicArrayQueue(int capacity) {
        n = capacity;
        data = (T[]) new Object[n];
    }

    public boolean enqueue(T t) {
        if (tail == n) {
            //tail == n and head ==0,the queue is full.
            if (head == 0) {
                return false;
            }
            //move element to array[0] ; O(n)
            for (int i = head; i < tail; i++) {
                data[i - head] = data[head];
            }
        }
        head = 0;
        tail -= head;
        return true;
    }

    public T dequeue() {
        if (head == tail) {
            return null;
        }
        return data[++head];
    }

    public void printAll() {
        for (int i = head; i < tail; i++) {
            System.out.print(" " + data[i]);
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        DynamicArrayQueue aq = new DynamicArrayQueue(5);
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
