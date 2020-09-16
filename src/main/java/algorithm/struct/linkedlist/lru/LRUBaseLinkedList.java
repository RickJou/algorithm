package algorithm.struct.linkedlist.lru;

public class LRUBaseLinkedList<T> {

    private SNode head;//头结点
    private int DEFAULT_CAPACITY = 10;
    private int capacity;//链表容量
    private int length;//链表长度


    public LRUBaseLinkedList() {
        this.head = new SNode();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(int capacity) {
        this.head = new SNode();
        this.capacity = capacity;
        this.length = 0;
    }

    //获取元素的前一个节点
    private SNode findPreNode(T element) {
        SNode node = head;
        while (node.getNext() != null) {
            if (node.getNext().getElement().equals(element)) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    //删除前一个元素的下一个元素(当前元素)
    private void deleteElemOptim(SNode preNode) {
        preNode.setNext(preNode.getNext().getNext());
        length--;
    }

    //头部插入节点
    public void insertToHeadAtBegin(T data) {
        SNode node = new SNode(data);
        node.setNext(head.getNext());
        this.head.setNext(node);
    }

    //删除尾结点
    public void deleteElemAtEnd() {
        SNode ptr = this.head;
        //空链表
        if (ptr.getNext() == null) {
            return;
        }

        //倒数第二个节点
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        //gc
        ptr.getNext().setElement(null);
        ptr.setNext(null);
        length--;
    }

    //添加节点
    public void add(T t) {
        //缓存存在,则删除
        SNode preNode = findPreNode(t);
        if (preNode != null) {
            deleteElemOptim(preNode);
        } else {
            //不存在且列表已满
            if (this.capacity == this.length) {
                deleteElemAtEnd();
            }
        }
        //在表头添加
        insertToHeadAtBegin(t);
    }

    public void printAll() {
        SNode node = this.head;
        while (node.getNext() != null) {
            System.out.print(node.getNext().getElement() + " ,");
            node = node.getNext();
        }
        System.out.println();
    }

    public class SNode<T> {
        T element;
        SNode next;

        public SNode() {
            element = null;
            next = null;
        }

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
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


    public static void main(String[] args) {
        LRUBaseLinkedList list = new LRUBaseLinkedList(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.printAll();
        list.add(1);
        list.add(2);
        list.add(3);
        list.printAll();
    }


}
