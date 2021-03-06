package algorithm.struct.array;

import com.sun.tools.javac.util.Assert;

public class Arrays5<T> {
    private T[] data;
    private int size;

    public Arrays5(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    public Arrays5() {
        this(8);
    }

    //获取当前容量
    public int getCapacity() {
        return data.length;
    }

    //获得当前数组大小
    public int count() {
        return size;
    }

    //通过下标设置元素
    public void set(int i, T t) {
        checkIndexForQuery(i);
        data[i] = t;
    }

    //获取指定下标的元素
    public T get(int i) {
        checkIndexForQuery(i);
        return data[i];
    }

    //是否包含元素
    public boolean container(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    //查找元素下标
    public int find(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }


    //插入元素
    public void add(int index, T t) {
        checkIndexForAdd(index);
        if (size == data.length) {
            resize(getCapacity() * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = t;
        size++;
    }

    public T remove(int index) {
        checkIndexForQuery(index);
        T tmp = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return tmp;
    }


    //扩容 or 缩容
    private void resize(int newCapacity) {
        T[] newArr = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = data[i];
        }
        data = newArr;
    }

    private void checkIndexForQuery(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("index must >= 0 and < size");
        }
    }

    private void checkIndexForAdd(int i) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("Failed remove element, index must >=0 and <= size");
        }
    }


    public static void main(String[] args) {
        //初始化测试
        Arrays5<Integer> arr = new Arrays5<Integer>(2);
        Assert.check(arr.getCapacity() == 2);
        Assert.check(arr.count() == 0);

        //边界测试
        arr.add(0, 1);
        Assert.check(arr.count() == 1);
        arr.add(0, 2);
        Assert.check(arr.count() == 2);
        Assert.check(arr.getCapacity() == 2);

        //新增扩容测试
        arr.add(0, 3);
        Assert.check(arr.getCapacity() == 4);
        Assert.check(arr.count() == 3);
        arr.add(0, 4);
        Assert.check(arr.get(0) == 4);
        Assert.check(arr.get(1) == 3);
        Assert.check(arr.get(2) == 2);
        Assert.check(arr.get(3) == 1);

        //修改测试
        arr.set(0, 10);
        arr.set(1, 20);
        arr.set(2, 30);
        arr.set(3, 40);
        Assert.check(arr.get(0) == 10);
        Assert.check(arr.get(1) == 20);
        Assert.check(arr.get(2) == 30);
        Assert.check(arr.get(3) == 40);

        //删除缩容测试
        arr.remove(3);
        arr.remove(2);
        arr.remove(1);
        Assert.check(arr.getCapacity() == 2);
        Assert.check(arr.count() == 1);

        //查询功能测试
        arr.remove(0);
        Assert.check(arr.count() == 0);
        for (int i = 0; i < 100; i++) {
            arr.add(arr.count(), i);
        }
        Assert.check(arr.container(99));
        Assert.check(arr.find(50) == 50);

        //删除
        for (int i = 0; i < 20; i++) {
            Assert.check(i == arr.remove(0));
        }
        Assert.check(arr.count() == 80);
        for (int i = 20; i < 50; i++) {
            Assert.check(i == arr.remove(0));
        }



        System.out.println(arr.toString());
    }
}
