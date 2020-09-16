package algorithm.sort.queue;

import com.sun.tools.javac.util.Assert;

public class QueueBasedOnLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int capacity;
    private int count;

    public QueueBasedOnLinkedList(int capacity) {
        this.capacity = capacity;
    }

    public boolean enqueue(T t) {
        if (count == capacity) {
            System.out.println("the queue is full,can't enqueue data!");
            return false;
        }

        Node node = new Node(t, null);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        count++;
        return true;
    }

    public T dequeue() {
        if (head == null) {
            System.out.println("the queue is empty,can't dequeue data!");
            return null;
        }

        T ret = head.value;
        head = head.next;

        if (head == null) {//empty
            tail = null;
        }
        return ret;
    }

    public void printAll() {
        Node<T> node = head;
        while (node != null) {
            System.out.println(node.value + " ");
            node = head.next;
        }
    }

    class Node<T> {
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        QueueBasedOnLinkedList queue = new QueueBasedOnLinkedList(5);
        Assert.check(queue.enqueue("1"));
        Assert.check(queue.enqueue("2"));
        Assert.check(queue.enqueue("3"));
        Assert.check(queue.enqueue("4"));
        Assert.check(queue.enqueue("5"));
        Assert.check(!queue.enqueue("6"));

        Assert.check("1".equals(queue.dequeue()));
        Assert.check("2".equals(queue.dequeue()));
        Assert.check("3".equals(queue.dequeue()));
        Assert.check("4".equals(queue.dequeue()));
        Assert.check("5".equals(queue.dequeue()));
        Assert.check(null == queue.dequeue());

    }
}
