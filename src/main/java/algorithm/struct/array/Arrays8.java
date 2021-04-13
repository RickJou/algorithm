package algorithm.struct.array;

import com.sun.tools.javac.util.Assert;

public class Arrays8<T> {
    private T[] data;
    private int size;

    public Arrays8(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    public Arrays8() {
        this.data = (T[]) new Object[8];
        this.size = 0;
    }


    public int getCapacity() {
        return this.data.length;
    }

    public int count() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T get(int i) {
        checkIndex(i);
        return this.data[i];
    }


    public int find(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public boolean container(T t) {
        return find(t) != -1;
    }

    public void set(int i, T t) {
        checkIndex(i);
        this.data[i] = t;
    }

    public void add(int index, T t) {
        checkIndexForAdd(index);
        //扩容
        if (this.size == this.data.length) {
            resize(this.size * 2);
        }
        //后移元素
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = t;
        size++;
    }

    public void addFirst(T t) {
        this.add(0, t);
    }

    public void addLast(T t) {
        this.add(this.size, t);
    }

    public T remove(int index) {
        checkIndex(index);
        T tmp = data[index];
        //前移
        for (int i = index + 1; i < this.size; i++) {
            data[i - 1] = data[i];
        }
        this.size--;
        data[this.size] = null;

        //缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return tmp;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(this.size - 1);
    }

    //删除指定元素
    public void removeElement(T t) {
        int index = find(t);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (!isEmpty()) {
            sb.append(get(0));
            for (int i = 1; i < size; i++) {
                sb.append(", ");
                sb.append(get(i));
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("argument failed! index >=0 and index < size.");
        }
    }

    public void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("argument failed! index >=0 and index <= size.");
        }
    }

    public void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < this.size; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
    }


    public static void main(String[] args) {
        //初始化测试
        Arrays8<Integer> arr = new Arrays8<Integer>(2);
        Assert.check(arr.getCapacity() == 2);
        Assert.check(arr.count() == 0);

        //边界测试
        arr.addLast(1);
        Assert.check(arr.count() == 1);
        arr.addFirst(2);
        Assert.check(arr.getCapacity() == 2);
        Assert.check(arr.count() == 2);

        //新增扩容测试
        arr.addFirst(3);
        Assert.check(arr.getCapacity() == 4);
        Assert.check(arr.count() == 3);
        arr.addLast(4);
        Assert.check(arr.get(0) == 3);
        Assert.check(arr.get(1) == 2);
        Assert.check(arr.get(2) == 1);
        Assert.check(arr.get(3) == 4);

        //修改测试
        arr.set(0, 1);
        arr.set(1, 2);
        arr.set(2, 3);
        arr.set(3, 4);
        Assert.check(arr.get(0) == 1);
        Assert.check(arr.get(1) == 2);
        Assert.check(arr.get(2) == 3);
        Assert.check(arr.get(3) == 4);

        //删除缩容测试
        arr.remove(3);
        arr.remove(2);
        arr.remove(1);
        Assert.check(arr.getCapacity() == 2);
        Assert.check(arr.count() == 1);

        //查询功能测试
        arr.remove(0);
        Assert.check(arr.isEmpty());
        for (int i = 0; i < 100; i++) {
            arr.addLast(i + 1);
        }
        Assert.check(arr.container(99));
        Assert.check(arr.find(50) == 49);

        //删除
        for (int i = 1; i <= 20; i++) {
            arr.removeElement(i);
        }
        Assert.check(arr.count() == 80);
        for (int i = 21; i <= 50; i++) {
            Assert.check(i == arr.removeFirst());
        }

        for (int i = 100; i >= 51; i--) {
            Assert.check(i == arr.removeLast());
        }

        System.out.println(arr.toString());
    }

}
