package algorithm.struct.linkedlist.stack;

import com.sun.tools.javac.util.Assert;

public class StackBasedOnLinkedList<T> {
    private SNode head;

    public void push(T t) {
        SNode n = new SNode(t);

        if (head == null) {
            head = n;
        } else {
            n.next = head;
            head = n;
        }
    }

    public T pop() {
        if (head == null) {
            return null;
        }
        Object tmp = head.element;
        head = head.next;
        return (T) tmp;
    }


    class SNode<T> {
        T element;
        SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode() {
        }
    }

    public static void main(String[] args) {
        StackBasedOnLinkedList<Integer> stack = new StackBasedOnLinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        Assert.check(5 == stack.pop());
        Assert.check(4 == stack.pop());
        Assert.check(3 == stack.pop());
        Assert.check(2 == stack.pop());
        Assert.check(1 == stack.pop());
        Assert.check(null == stack.pop());
        System.out.print("Assert OK!");

    }
}