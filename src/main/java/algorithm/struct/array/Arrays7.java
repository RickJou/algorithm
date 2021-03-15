package algorithm.struct.array;

import com.sun.tools.javac.util.Assert;

public class Arrays7<T> {
    private T[] data;
    private int size;

    public Arrays7(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    public Arrays7() {
        this(8);
    }

    //getCapacity
    public int getCapacity() {
        return this.data.length;
    }

    //count
    public int count() {
        return this.size;
    }

    //get
    public T get(int index) {
        checkIndexByQuery(index);
        return data[index];
    }

    //set
    public void set(int index, T t) {
        checkIndexByQuery(index);
        data[index] = t;
    }

    //find
    public int find(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public boolean container(T t) {
        return find(t) == -1 ? false : true;
    }

    //checkIndexByQuery
    private void checkIndexByQuery(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must >= 0 and < size");
        }
    }

    //checkIndexByAdd
    private void checkIndexByAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must >= 0 and <= size");
        }
    }

    //resize
    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    //add
    public void add(int index, T t) {
        checkIndexByAdd(index);
        if (data.length == size) {
            resize(data.length * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        size++;
        data[index] = t;
    }

    //remove
    public T remove(int index) {
        checkIndexByQuery(index);
        T tmp = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;

        if (data.length / 4 == size && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return tmp;
    }


    public static void main(String[] args) {
        //初始化测试
        Arrays7<Integer> arr = new Arrays7<Integer>(2);
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
