package algorithm.sort.queue;

import com.sun.tools.javac.util.Assert;

import java.util.Random;

public class CircularQueue<T> {
    private int capacity;
    private int n = 0;
    private int head = 0;
    private int tail = 0;
    private T[] data;

    private CircularQueue(int capacity) {
        data = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.n = capacity;
    }

    public boolean enqueue(T t) {
        if ((tail + 1) % n == head) {
            System.out.println("the queue is full ,can't enqueue data!");
            return false;
        }
        data[tail] = t;
        tail = (tail + 1) % n;
        return true;
    }

    public T dequeue() {
        if (head == tail) {
            System.out.println("the queue is empty,can't dequeue data!");
            return null;
        }
        T ret = data[head];
        head = (head + 1) % n;
        return ret;
    }

    public void printAll() {
        int headIndex = head & n;
        int tailIndex = tail % n;
        for (int i = headIndex; i < tailIndex; i++) {
            System.out.print(" " + data[i]);
        }
        System.out.println(" ");
    }

    public static void main(String[] args) throws InterruptedException {
        CircularQueue cq = new CircularQueue(5);
        Assert.check(cq.enqueue("1"));
        Assert.check(cq.enqueue("2"));
        Assert.check(cq.enqueue("3"));
        Assert.check(cq.enqueue("4"));
        Assert.check(!cq.enqueue("5"));
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

        System.out.println("=============================");
        Thread enThead = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (!cq.enqueue(i)) {//队列已满,暂停200毫秒后重新入队.
                        try {
                            Thread.currentThread().sleep(2000);
                            i--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            long time = (long) (new Random()).nextInt(2000);
                            try {
                                Thread.currentThread().sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        Thread deThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    Object ret = cq.dequeue();
                    if (ret != null) {
                        System.out.println(ret);
                    } else {//无数据可消费,暂停200毫秒后重新消费
                        try {
                            Thread.currentThread().sleep(2000);
                            i--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            long time = (long) (new Random()).nextInt(2000);
                            try {
                                Thread.currentThread().sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        enThead.start();
        deThread.start();
        Thread.currentThread().sleep(1000 * 20);

    }

}
