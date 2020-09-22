package algorithm.sort.n2;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 4, 5, 6, 1, 2, 3};
        sort(arr, arr.length);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            //find min value
            int minIdex = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIdex]) {
                    minIdex = j;
                }
            }
            int tmp = a[minIdex];
            a[minIdex] = a[i];
            a[i] = tmp;
        }
    }
}
