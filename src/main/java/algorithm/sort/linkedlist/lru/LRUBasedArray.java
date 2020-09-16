package algorithm.sort.linkedlist.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于链表的lru算法
 */
public class LRUBasedArray<T> {
    private int DEFAULT_CAPACITY = (1 << 3);
    private int capacity;
    private int count;
    private T[] data;
    private Map<T, Integer> holder;

    private LRUBasedArray() {
        this.capacity = DEFAULT_CAPACITY;
        this.count = 0;
        this.data = (T[]) new Object[this.capacity];
        this.holder = new HashMap<T, Integer>(this.capacity);
    }

    private LRUBasedArray(Integer capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.data = (T[]) new Object[capacity];
        this.holder = new HashMap<T, Integer>(capacity);
    }

    public void offer(T t) {
        if (t == null) {
            throw new IllegalArgumentException("param can not is null!");
        }

        Integer index = holder.get(t);
        if (index == null) {
            if (isFull()) {
                removeAndCache(t);
            } else {
                cache(t, count);
            }
        } else {
            updateCache(index);
        }
    }

    private void removeAndCache(T t) {
        T tmp = data[--count];
        holder.remove(tmp);
        cache(t, 0);
    }

    private void cache(T t, int end) {
        rightShift(end);
        data[0] = t;
        holder.put(t, 0);
        count++;
    }

    private void updateCache(Integer end) {
        T tmp = data[end];
        rightShift(end);
        data[0] = tmp;
        holder.put(tmp, 0);
    }

    private void rightShift(Integer end) {
        for (int i = end - 1; i >= 0; i--) {
            data[i + 1] = data[i];
            holder.put(data[i], i + 1);
        }
    }

    private boolean isFull() {
        return count == capacity;
    }

    private boolean isEmpty() {
        return count == 0;
    }

    public void printAll() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.count; i++) {
            sb.append(data[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println("无参测试=============");
        LRUBasedArray arr = new LRUBasedArray();
        arr.offer("1");
        arr.offer("2");
        arr.offer("3");
        arr.offer("4");
        arr.offer("5");
        arr.printAll();
        arr.offer("5");
        arr.offer("4");
        arr.offer("3");
        arr.offer("2");
        arr.offer("1");
        arr.printAll();

        System.out.println("有参测试=============");
        arr = new LRUBasedArray(4);
        arr.offer("1");
        arr.offer("2");
        arr.offer("3");
        arr.offer("4");
        arr.offer("5");
        arr.printAll();
        arr.offer("5");
        arr.offer("4");
        arr.offer("3");
        arr.offer("2");
        arr.offer("1");
        arr.printAll();
    }
}
