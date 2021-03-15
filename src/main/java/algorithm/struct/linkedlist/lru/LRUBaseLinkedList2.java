package algorithm.struct.linkedlist.lru;

import com.sun.tools.javac.util.Assert;

public class LRUBaseLinkedList2<T> {

    //头结点
    private SNode headerNode;
    //总容量
    private int capacity;
    //默认总容量
    private int DEFAULT_CAPACITY = 10;
    //当前容量
    private int size;

    public LRUBaseLinkedList2(int capacity) {
        this.capacity = capacity;
        this.headerNode = new SNode();
        this.size = 0;
    }

    public LRUBaseLinkedList2() {
        this.capacity = DEFAULT_CAPACITY;
        this.headerNode = new SNode();
        this.size = 0;
    }

    /**
     * add
     * 1. 添加时,如果已满,则删除最后一个元素
     * 2. 添加元素,如果不存在则添加到队列头
     * 3. 添加元素,如果存在则删除,且添加到队列头
     */

    public void add(T t) {
        SNode pre = findElementPre(t);

        //存在则删除
        if (pre != null) {
            deleteNextNode(pre);
        } else {
            //不存在,但是队列满了,则删除最后一个
            if (this.size == this.capacity) {
                deleteElementAtEnd();
            }
        }
        //添加到头部
        insertElementAtStart(t);
    }

    //删除下一个元素
    private void deleteNextNode(SNode node) {
        node.setNext(node.getNext().getNext());
        size--;
    }

    //删除最后一个元素
    private void deleteElementAtEnd() {
        SNode preNode = headerNode;
        if (preNode.getNext() == null) {
            return;
        }
        while (preNode.getNext().getNext() != null) {
            preNode = preNode.getNext();
        }
        //gc
        preNode.getNext().setElement(null);
        preNode.setNext(null);
        size--;
    }

    //添加到队列头
    private void insertElementAtStart(T t) {
        SNode e = new SNode(t);
        e.setNext(this.headerNode.getNext());
        this.headerNode.setNext(e);
        size++;
    }

    //查找元素的前一个元素
    public SNode findElementPre(T t) {
        SNode preNode = headerNode;
        while (preNode.getNext() != null) {
            if (preNode.getNext().getElement().equals(t)) {
                return preNode;
            }
            preNode = preNode.getNext();
        }
        return null;
    }


    class SNode<T> {
        T element;
        SNode next;

        public SNode() {
        }

        public SNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public String printAll() {
        LRUBaseLinkedList2.SNode node = this.headerNode;
        StringBuffer sb = new StringBuffer();
        while (node.getNext() != null) {
            sb.append(node.getNext().getElement() + " ,");
            node = node.getNext();
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        LRUBaseLinkedList2 list = new LRUBaseLinkedList2(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        String str = list.printAll();
        System.out.println(str);
        list.add(1);
        list.add(2);
        list.add(3);
        str = list.printAll();
        System.out.println(str);

        Assert.check(str.equals("3 ,2 ,1 ,5 ,4 ,"));
        System.out.println("Assert OK!");

    }

}
