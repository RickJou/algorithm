package algorithm.sort.n2;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 4, 5, 6, 1, 2, 3};
        sort(arr, arr.length);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }
}
