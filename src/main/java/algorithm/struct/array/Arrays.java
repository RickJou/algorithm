package algorithm.struct.array;

import com.sun.tools.javac.util.Assert;

public class Arrays<T> {
    private T[] data;
    private int size;

    Arrays(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    //获取容量
    public int getCapacity() {
        return data.length;
    }

    //获取用量
    public int count() {
        return size;
    }

    //数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //检查入参index位置
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("arguments failed! index >= 0 and index < size.");
        }
    }

    //add元素时校验
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("arguments failed! index >= 0 and index <= size.");
        }
    }

    //获取对应位置的元素
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }


    //获取对应元素下标,未找到返回-1
    public int find(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    //查看数组是否包含元素
    public boolean container(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    //扩容数组,时间复杂度O(n)
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
    }


    //修改指定位置的元素
    public void set(int index, T t) {
        checkIndex(index);
        data[index] = t;
    }


    //在指定位置插入元素,时间复杂度O(n+m)
    public void add(int index, T t) {
        checkIndexForAdd(index);
        if (size == data.length) {
            resize(2 * data.length);
        }
        //后移元素
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = t;
        size++;
    }

    //在头部插入元素
    public void addFirst(T t) {
        add(0, t);
    }

    //在尾部插入元素
    public void addLast(T t) {
        add(size, t);
    }

    //删除指定位置的元素
    public T remove(int index) {
        checkIndex(index);
        T res = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;

        //缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return res;
    }

    //删除第一个元素
    public T removeFirst() {
        return remove(0);
    }

    //删除末尾元素
    public T removeLast() {
        return remove(size - 1);
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

    public static void main(String[] args) {
        //初始化测试
        Arrays<Integer> arr = new Arrays<Integer>(2);
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
