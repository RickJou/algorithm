package algorithm.struct.array;

import com.sun.tools.javac.util.Assert;

public class Arrays6<T> {

    private T[] data;
    private int size;

    public Arrays6(int capital) {
        this.size = 0;
        this.data = (T[]) new Object[capital];
    }

    public Arrays6() {
        this(8);
    }

    public int getCapacity() {
        return this.data.length;
    }


    public int count() {
        return this.size;
    }

    public void set(int i, T t) {
        checkIndexForQuery(i);
        this.data[i] = t;
    }

    public T get(int i) {
        checkIndexForQuery(i);
        return this.data[i];
    }

    public boolean container(T t) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    public int find(T t) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    //add
    public void add(int index, T t) {
        checkIndexForAdd(index);
        if (getCapacity() == this.size) {
            resize(getCapacity() * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = t;
        size++;
    }

    //remove
    public T remove(int index) {
        checkIndexForQuery(index);
        T obj = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (this.size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return obj;
    }

    //扩缩容 O(n)
    private void resize(int newCapital) {
        T[] newArr = (T[]) new Object[newCapital];
        for (int i = 0; i < this.size; i++) {
            newArr[i] = this.data[i];
        }
        this.data = newArr;
    }


    private void checkIndexForQuery(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("index must >=0 and < size");
        }
    }

    private void checkIndexForAdd(int i) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("index must >=0 and <= size");
        }
    }

    public static void main(String[] args) {
        //初始化测试
        Arrays6<Integer> arr = new Arrays6<Integer>(2);
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
