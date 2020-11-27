package algorithm.struct.array;

public class Arrays5<T> {
    private T[] data;
    private int size;

    public Arrays5(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    //获取当前容量
    public int getCapacity(){
        return this.size;
    }

}
