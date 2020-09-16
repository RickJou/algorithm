package algorithm.sort.linkedlist.stack;

public class SampleBrowser {

    public static void main(String[] args) {
        SampleBrowser sb = new SampleBrowser();
        sb.open("https://www.1_github.com");
        sb.open("https://www.2_google.com");
        sb.open("https://www.3_baidu.com");

        sb.goBack();
        sb.goBack();
        sb.goBack();

        sb.goForWard();
        sb.goForWard();
        sb.goForWard();

        System.out.println("==========================================");

        sb.goBack();
        sb.goBack();
        sb.goBack();

        sb.goForWard();
        sb.goForWard();
        sb.goForWard();
    }


    private String currentUrl;
    private LinkedListBasedStack forwardStack = new LinkedListBasedStack();
    private LinkedListBasedStack backStack = new LinkedListBasedStack();

    public void open(String url) {
        if (currentUrl != null) {
            forwardStack.clear();
            backStack.push(this.currentUrl);
        }
        currentUrl = url;
        System.out.println("打开:" + this.currentUrl);
    }

    public void goForWard() {
        String url = (String) forwardStack.pop();
        if (url == null) {
            System.out.println("没有可以前进的页面");
            return;
        }
        System.out.println("forward url:" + url);
        backStack.push(this.currentUrl);
        currentUrl = url;
    }

    public void goBack() {
        String url = (String) backStack.pop();
        if (url == null) {
            System.out.println("没有可以后退的页面!");
            return;
        }
        System.out.println("back url:" + url);

        forwardStack.push(currentUrl);
        currentUrl = url;
    }


    class LinkedListBasedStack<T> {
        private Node<T> top;
        private int length;

        public void clear() {
            this.top = null;
            this.length = 0;
        }

        public void push(T value) {
            Node node = new Node(value, this.top);
            this.top = node;
            this.length++;
        }

        public T pop() {
            Node<T> node = this.top;
            if (node == null) {
                return null;
            }

            this.top = this.top.next;

            if (this.length > 0) {
                this.length--;
            }
            return node.value;
        }


        class Node<T> {
            T value;
            Node<T> next;

            public Node(T value) {
                this.value = value;
            }

            public Node(T value, Node<T> next) {
                this.value = value;
                this.next = next;
            }

            public T getValue() {
                return value;
            }

            public void setValue(T value) {
                this.value = value;
            }

            public Node<T> getNext() {
                return next;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }
        }

    }
}
